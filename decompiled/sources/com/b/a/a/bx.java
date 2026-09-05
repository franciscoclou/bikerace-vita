package com.b.a.a;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class bx {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static aa f287a = aa.f253a;
    private URL c;
    private final String d;
    private ae e;
    private boolean f;
    private HttpURLConnection b = null;
    private boolean g = true;
    private boolean h = false;
    private int i = 8192;

    /* JADX INFO: Access modifiers changed from: private */
    public static String c(String str) {
        return (str == null || str.length() <= 0) ? XMLStreamWriterImpl.UTF_8 : str;
    }

    private static String c(CharSequence charSequence) {
        try {
            URL url = new URL(charSequence.toString());
            String host = url.getHost();
            int port = url.getPort();
            if (port != -1) {
                host = host + ':' + Integer.toString(port);
            }
            try {
                String aSCIIString = new URI(url.getProtocol(), host, url.getPath(), url.getQuery(), null).toASCIIString();
                int iIndexOf = aSCIIString.indexOf(63);
                if (iIndexOf > 0 && iIndexOf + 1 < aSCIIString.length()) {
                    return aSCIIString.substring(0, iIndexOf + 1) + aSCIIString.substring(iIndexOf + 1).replace("+", "%2B");
                }
                return aSCIIString;
            } catch (URISyntaxException e) {
                IOException iOException = new IOException("Parsing URI failed");
                iOException.initCause(e);
                throw new ac(iOException);
            }
        } catch (IOException e2) {
            throw new ac(e2);
        }
    }

    private static String a(CharSequence charSequence, Map<?, ?> map) {
        String string = charSequence.toString();
        if (map != null && !map.isEmpty()) {
            StringBuilder sb = new StringBuilder(string);
            if (string.indexOf(58) + 2 == string.lastIndexOf(47)) {
                sb.append('/');
            }
            int iIndexOf = string.indexOf(63);
            int length = sb.length() - 1;
            if (iIndexOf == -1) {
                sb.append('?');
            } else if (iIndexOf < length && string.charAt(length) != '&') {
                sb.append('&');
            }
            Iterator<Map.Entry<?, ?>> it = map.entrySet().iterator();
            Map.Entry<?, ?> next = it.next();
            sb.append(next.getKey().toString());
            sb.append('=');
            Object value = next.getValue();
            if (value != null) {
                sb.append(value);
            }
            while (it.hasNext()) {
                sb.append('&');
                Map.Entry<?, ?> next2 = it.next();
                sb.append(next2.getKey().toString());
                sb.append('=');
                Object value2 = next2.getValue();
                if (value2 != null) {
                    sb.append(value2);
                }
            }
            return sb.toString();
        }
        return string;
    }

    public static bx a(CharSequence charSequence, Map<?, ?> map, boolean z) {
        return new bx(c((CharSequence) a(charSequence, map)), "GET");
    }

    public static bx b(CharSequence charSequence, Map<?, ?> map, boolean z) {
        return new bx(c((CharSequence) a(charSequence, map)), "POST");
    }

    public static bx a(CharSequence charSequence) {
        return new bx(charSequence, "PUT");
    }

    public static bx b(CharSequence charSequence) {
        return new bx(charSequence, "DELETE");
    }

    private bx(CharSequence charSequence, String str) {
        try {
            this.c = new URL(charSequence.toString());
            this.d = str;
        } catch (MalformedURLException e) {
            throw new ac(e);
        }
    }

    private HttpURLConnection e() {
        try {
            HttpURLConnection httpURLConnectionA = f287a.a(this.c);
            httpURLConnectionA.setRequestMethod(this.d);
            return httpURLConnectionA;
        } catch (IOException e) {
            throw new ac(e);
        }
    }

    public final String toString() {
        return a().getRequestMethod() + ' ' + a().getURL();
    }

    public final HttpURLConnection a() {
        if (this.b == null) {
            this.b = e();
        }
        return this.b;
    }

    public final int b() {
        try {
            g();
            return a().getResponseCode();
        } catch (IOException e) {
            throw new ac(e);
        }
    }

    private String d(String str) {
        h();
        int headerFieldInt = a().getHeaderFieldInt("Content-Length", -1);
        ByteArrayOutputStream byteArrayOutputStream = headerFieldInt > 0 ? new ByteArrayOutputStream(headerFieldInt) : new ByteArrayOutputStream();
        try {
            a(new BufferedInputStream(f(), this.i), byteArrayOutputStream);
            return byteArrayOutputStream.toString(c(str));
        } catch (IOException e) {
            throw new ac(e);
        }
    }

    public final String c() {
        return d(c(a("Content-Type"), "charset"));
    }

    private InputStream f() {
        if (b() < 400) {
            try {
                return a().getInputStream();
            } catch (IOException e) {
                throw new ac(e);
            }
        }
        InputStream errorStream = a().getErrorStream();
        if (errorStream == null) {
            try {
                return a().getInputStream();
            } catch (IOException e2) {
                throw new ac(e2);
            }
        }
        return errorStream;
    }

    public final bx a(int i) {
        a().setConnectTimeout(10000);
        return this;
    }

    public final bx a(String str, String str2) {
        a().setRequestProperty(str, str2);
        return this;
    }

    public final bx a(Map.Entry<String, String> entry) {
        return a(entry.getKey(), entry.getValue());
    }

    public final String a(String str) {
        h();
        return a().getHeaderField(str);
    }

    private static String c(String str, String str2) {
        String strTrim;
        int length;
        if (str == null || str.length() == 0) {
            return null;
        }
        int length2 = str.length();
        int iIndexOf = str.indexOf(59) + 1;
        if (iIndexOf == 0 || iIndexOf == length2) {
            return null;
        }
        int iIndexOf2 = str.indexOf(59, iIndexOf);
        int i = iIndexOf2 == -1 ? length2 : iIndexOf2;
        while (iIndexOf < i) {
            int iIndexOf3 = str.indexOf(61, iIndexOf);
            if (iIndexOf3 != -1 && iIndexOf3 < i && str2.equals(str.substring(iIndexOf, iIndexOf3).trim()) && (length = (strTrim = str.substring(iIndexOf3 + 1, i).trim()).length()) != 0) {
                if (length > 2 && '\"' == strTrim.charAt(0) && '\"' == strTrim.charAt(length - 1)) {
                    return strTrim.substring(1, length - 1);
                }
                return strTrim;
            }
            int i2 = i + 1;
            int iIndexOf4 = str.indexOf(59, i2);
            if (iIndexOf4 == -1) {
                iIndexOf4 = length2;
            }
            int i3 = iIndexOf4;
            iIndexOf = i2;
            i = i3;
        }
        return null;
    }

    public final bx a(boolean z) {
        a().setUseCaches(false);
        return this;
    }

    private bx a(InputStream inputStream, OutputStream outputStream) {
        return new by(this, inputStream, this.g, inputStream, outputStream).call();
    }

    private bx g() {
        if (this.e != null) {
            if (this.f) {
                this.e.a("\r\n--00content0boundary00--\r\n");
            }
            if (this.g) {
                try {
                    this.e.close();
                } catch (IOException e) {
                }
            } else {
                this.e.close();
            }
            this.e = null;
        }
        return this;
    }

    private bx h() {
        try {
            return g();
        } catch (IOException e) {
            throw new ac(e);
        }
    }

    private bx i() {
        if (this.e == null) {
            a().setDoOutput(true);
            this.e = new ae(a().getOutputStream(), c(a().getRequestProperty("Content-Type"), "charset"), this.i);
        }
        return this;
    }

    private bx j() {
        if (!this.f) {
            this.f = true;
            a("Content-Type", "multipart/form-data; boundary=00content0boundary00").i();
            this.e.a("--00content0boundary00\r\n");
        } else {
            this.e.a("\r\n--00content0boundary00\r\n");
        }
        return this;
    }

    private bx a(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append("form-data; name=\"").append(str);
        if (str2 != null) {
            sb.append("\"; filename=\"").append(str2);
        }
        sb.append('\"');
        d("Content-Disposition", sb.toString());
        if (str3 != null) {
            d("Content-Type", str3);
        }
        return d("\r\n");
    }

    public final bx b(String str, String str2) {
        return b(str, (String) null, str2);
    }

    private bx b(String str, String str2, String str3) {
        return a(str, str2, (String) null, str3);
    }

    private bx a(String str, String str2, String str3, String str4) {
        try {
            j();
            a(str, str2, (String) null);
            this.e.a(str4);
            return this;
        } catch (IOException e) {
            throw new ac(e);
        }
    }

    public final bx a(String str, Number number) {
        return b(str, (String) null, number != null ? number.toString() : null);
    }

    /* JADX WARN: Code duplicated, block: B:25:0x001e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public final bx a(String str, String str2, String str3, File file) throws Throwable {
        BufferedInputStream bufferedInputStream;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                try {
                    bx bxVarA = a(str, str2, str3, bufferedInputStream);
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e) {
                    }
                    return bxVarA;
                } catch (IOException e2) {
                    e = e2;
                    throw new ac(e);
                }
            } catch (Throwable th) {
                th = th;
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e3) {
                    }
                }
                throw th;
            }
        } catch (IOException e4) {
            e = e4;
            bufferedInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream = null;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            throw th;
        }
    }

    public final bx a(String str, String str2, String str3, InputStream inputStream) {
        try {
            j();
            a(str, str2, str3);
            a(inputStream, this.e);
            return this;
        } catch (IOException e) {
            throw new ac(e);
        }
    }

    private bx d(String str, String str2) {
        return d((CharSequence) str).d(": ").d((CharSequence) str2).d("\r\n");
    }

    private bx d(CharSequence charSequence) {
        try {
            i();
            this.e.a(charSequence.toString());
            return this;
        } catch (IOException e) {
            throw new ac(e);
        }
    }

    public final String d() {
        return a().getRequestMethod();
    }
}

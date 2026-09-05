package com.google.a.a.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;

/* JADX INFO: compiled from: SimpleNetworkDispatcher.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class as implements n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f584a;
    private final HttpClient b;
    private final Context c;
    private ae d;
    private URL e;

    as(HttpClient httpClient, ae aeVar, Context context) {
        this.c = context.getApplicationContext();
        this.f584a = a("GoogleAnalytics", "3.0", Build.VERSION.RELEASE, aw.a(Locale.getDefault()), Build.MODEL, Build.ID);
        this.b = httpClient;
        this.d = aeVar;
    }

    as(HttpClient httpClient, Context context) {
        this(httpClient, ae.a(context), context);
    }

    @Override // com.google.a.a.a.n
    public boolean a() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.c.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        ah.c("...no network connectivity");
        return false;
    }

    @Override // com.google.a.a.a.n
    public int a(List<af> list) {
        boolean z;
        int i;
        int iMin = Math.min(list.size(), 40);
        boolean z2 = true;
        int i2 = 0;
        int i3 = 0;
        while (i2 < iMin) {
            af afVar = list.get(i2);
            URL urlA = a(afVar);
            if (urlA == null) {
                if (ah.a()) {
                    ah.d("No destination: discarding hit: " + afVar.a());
                } else {
                    ah.d("No destination: discarding hit.");
                }
                i = i3 + 1;
            } else {
                HttpHost httpHost = new HttpHost(urlA.getHost(), urlA.getPort(), urlA.getProtocol());
                String path = urlA.getPath();
                String strA = TextUtils.isEmpty(afVar.a()) ? "" : ag.a(afVar, System.currentTimeMillis());
                HttpEntityEnclosingRequest httpEntityEnclosingRequestA = a(strA, path);
                if (httpEntityEnclosingRequestA == null) {
                    i = i3 + 1;
                } else {
                    httpEntityEnclosingRequestA.addHeader("Host", httpHost.toHostString());
                    if (ah.a()) {
                        a(httpEntityEnclosingRequestA);
                    }
                    if (strA.length() > 8192) {
                        ah.d("Hit too long (> 8192 bytes)--not sent");
                        z = z2;
                    } else if (this.d.b()) {
                        ah.b("Dry run enabled. Hit not actually sent.");
                        z = z2;
                    } else {
                        if (z2) {
                            try {
                                try {
                                    t.b(this.c);
                                    z = false;
                                } catch (IOException e) {
                                    ah.d("Exception sending hit: " + e.getClass().getSimpleName());
                                    ah.d(e.getMessage());
                                    return i3;
                                }
                            } catch (ClientProtocolException e2) {
                                z = z2;
                                ah.d("ClientProtocolException sending hit; discarding hit...");
                                boolean z3 = z;
                                i = i3 + 1;
                                z2 = z3;
                                i2++;
                                i3 = i;
                            }
                        } else {
                            z = z2;
                        }
                        try {
                            HttpResponse httpResponseExecute = this.b.execute(httpHost, httpEntityEnclosingRequestA);
                            int statusCode = httpResponseExecute.getStatusLine().getStatusCode();
                            HttpEntity entity = httpResponseExecute.getEntity();
                            if (entity != null) {
                                entity.consumeContent();
                            }
                            if (statusCode != 200) {
                                ah.d("Bad response: " + httpResponseExecute.getStatusLine().getStatusCode());
                            }
                        } catch (ClientProtocolException e3) {
                            ah.d("ClientProtocolException sending hit; discarding hit...");
                        }
                    }
                    boolean z4 = z;
                    i = i3 + 1;
                    z2 = z4;
                }
            }
            i2++;
            i3 = i;
        }
        return i3;
    }

    private HttpEntityEnclosingRequest a(String str, String str2) {
        BasicHttpEntityEnclosingRequest basicHttpEntityEnclosingRequest;
        if (TextUtils.isEmpty(str)) {
            ah.d("Empty hit, discarding.");
            return null;
        }
        String str3 = str2 + "?" + str;
        if (str3.length() < 2036) {
            basicHttpEntityEnclosingRequest = new BasicHttpEntityEnclosingRequest("GET", str3);
        } else {
            basicHttpEntityEnclosingRequest = new BasicHttpEntityEnclosingRequest("POST", str2);
            try {
                basicHttpEntityEnclosingRequest.setEntity(new StringEntity(str));
            } catch (UnsupportedEncodingException e) {
                ah.d("Encoding error, discarding hit");
                return null;
            }
        }
        basicHttpEntityEnclosingRequest.addHeader("User-Agent", this.f584a);
        return basicHttpEntityEnclosingRequest;
    }

    private void a(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        int iAvailable;
        StringBuffer stringBuffer = new StringBuffer();
        for (Header header : httpEntityEnclosingRequest.getAllHeaders()) {
            stringBuffer.append(header.toString()).append("\n");
        }
        stringBuffer.append(httpEntityEnclosingRequest.getRequestLine().toString()).append("\n");
        if (httpEntityEnclosingRequest.getEntity() != null) {
            try {
                InputStream content = httpEntityEnclosingRequest.getEntity().getContent();
                if (content != null && (iAvailable = content.available()) > 0) {
                    byte[] bArr = new byte[iAvailable];
                    content.read(bArr);
                    stringBuffer.append("POST:\n");
                    stringBuffer.append(new String(bArr)).append("\n");
                }
            } catch (IOException e) {
                ah.c("Error Writing hit to log...");
            }
        }
        ah.c(stringBuffer.toString());
    }

    String a(String str, String str2, String str3, String str4, String str5, String str6) {
        return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", str, str2, str3, str4, str5, str6);
    }

    URL a(af afVar) {
        if (this.e != null) {
            return this.e;
        }
        try {
            return new URL("http:".equals(afVar.d()) ? "http://www.google-analytics.com/collect" : "https://ssl.google-analytics.com/collect");
        } catch (MalformedURLException e) {
            ah.a("Error trying to parse the hardcoded host url. This really shouldn't happen.");
            return null;
        }
    }

    @Override // com.google.a.a.a.n
    public void a(String str) {
        try {
            this.e = new URL(str);
        } catch (MalformedURLException e) {
            this.e = null;
        }
    }
}

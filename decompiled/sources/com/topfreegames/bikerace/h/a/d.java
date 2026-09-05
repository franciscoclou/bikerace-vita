package com.topfreegames.bikerace.h.a;

import android.os.AsyncTask;
import com.topfreegames.bikerace.ap;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipException;

/* JADX INFO: compiled from: UserLevelDownloader.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static ArrayList<AsyncTask<Void, Void, ?>> f1244a = new ArrayList<>();
    private static ArrayList<AsyncTask<Void, Void, ?>> b = new ArrayList<>();

    public static void a(String str, j jVar) {
        a(new e(jVar, str));
    }

    public static void b(String str, j jVar) {
        a(new e(jVar, e(str)));
    }

    public static void a(f fVar) {
        a(new g(fVar));
    }

    static void a(String str, String str2, k kVar) {
        a(new h(kVar, str, str2));
    }

    static void a(a aVar, k kVar) {
        a(aVar.a(), aVar.c(), kVar);
    }

    public static void a() {
        synchronized (f1244a) {
            int size = f1244a.size();
            for (int i = 0; i < size; i++) {
                f1244a.get(i).cancel(true);
            }
            f1244a.clear();
            b.clear();
        }
    }

    private static void a(AsyncTask<Void, Void, ?> asyncTask) {
        if (asyncTask != null) {
            synchronized (f1244a) {
                if (f1244a.size() < 2) {
                    asyncTask.execute(new Void[0]);
                    f1244a.add(asyncTask);
                } else {
                    b.add(asyncTask);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e() {
        if (b.size() > 0) {
            a(b.remove(0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static i b(String str) {
        i iVar = new i(null);
        iVar.b = c(b(str, true));
        iVar.f1248a = m.a(iVar.b);
        return iVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static a[] f() {
        byte[] bArrC = c(a(true));
        a[] aVarArrA = null;
        if (bArrC != null && (aVarArrA = a.a(new String(bArrC))) == null) {
            aVarArrA = a.a(new String(a(bArrC)));
        }
        return aVarArrA != null ? aVarArrA : new a[0];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] b(String str, String str2) {
        return c(str2);
    }

    private static byte[] c(String str) {
        try {
            InputStream inputStreamD = d(str);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[4096];
            while (true) {
                int i = inputStreamD.read(bArr);
                if (i >= 0) {
                    byteArrayOutputStream.write(bArr, 0, i);
                } else {
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            if (ap.d()) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private static InputStream d(String str) throws ProtocolException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setReadTimeout(10000);
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setDoInput(true);
        httpURLConnection.connect();
        return httpURLConnection.getInputStream();
    }

    private static String b(String str, boolean z) {
        Object[] objArr = new Object[3];
        objArr[0] = z ? "https" : "http";
        objArr[1] = "s3.amazonaws.com/bikerace-editor-facebook-prod.topfreegames.com";
        objArr[2] = str;
        return String.format("%s://%s/tracks/%s", objArr);
    }

    private static String a(boolean z) {
        Object[] objArr = new Object[2];
        objArr[0] = z ? "https" : "http";
        objArr[1] = "s3.amazonaws.com/bikerace-editor-facebook-prod.topfreegames.com";
        return String.format("%s://%s/featured/today", objArr);
    }

    private static String e(String str) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance("MD5").digest(("b869d9e6889f728cb9f5" + str).getBytes());
            StringBuffer stringBuffer = new StringBuffer(bArrDigest.length * 2);
            for (byte b2 : bArrDigest) {
                stringBuffer.append(String.format("%02x", Byte.valueOf(b2)));
            }
            return stringBuffer.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            if (ap.d()) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private static byte[] a(byte[] bArr) throws Throwable {
        GZIPInputStream gZIPInputStream;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] byteArray = null;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            try {
                gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        byte[] bArr2 = new byte[2048];
                        while (true) {
                            int i = gZIPInputStream.read(bArr2, 0, bArr2.length);
                            if (i == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr2, 0, i);
                        }
                        byteArray = byteArrayOutputStream.toByteArray();
                        try {
                            byteArrayInputStream.close();
                        } catch (IOException e) {
                        }
                        try {
                            gZIPInputStream.close();
                        } catch (IOException e2) {
                        }
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e3) {
                        }
                    } catch (ZipException e4) {
                        e = e4;
                        if (ap.d()) {
                            e.printStackTrace();
                        }
                        try {
                            byteArrayInputStream.close();
                        } catch (IOException e5) {
                        }
                        try {
                            gZIPInputStream.close();
                        } catch (IOException e6) {
                        }
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e7) {
                        }
                    } catch (IOException e8) {
                        e = e8;
                        if (ap.d()) {
                            e.printStackTrace();
                        }
                        try {
                            byteArrayInputStream.close();
                        } catch (IOException e9) {
                        }
                        try {
                            gZIPInputStream.close();
                        } catch (IOException e10) {
                        }
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e11) {
                        }
                    }
                } catch (ZipException e12) {
                    e = e12;
                    byteArrayOutputStream = null;
                } catch (IOException e13) {
                    e = e13;
                    byteArrayOutputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        byteArrayInputStream.close();
                    } catch (IOException e14) {
                    }
                    try {
                        gZIPInputStream.close();
                    } catch (IOException e15) {
                    }
                    try {
                        byteArray.close();
                        throw th;
                    } catch (IOException e16) {
                        throw th;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (ZipException e17) {
            e = e17;
            byteArrayOutputStream = null;
            gZIPInputStream = null;
        } catch (IOException e18) {
            e = e18;
            byteArrayOutputStream = null;
            gZIPInputStream = null;
        } catch (Throwable th4) {
            gZIPInputStream = null;
            th = th4;
        }
        return byteArray;
    }

    static String a(String str, boolean z) {
        Object[] objArr = new Object[3];
        objArr[0] = z ? "https" : "http";
        objArr[1] = "s3.amazonaws.com/bikerace-editor-facebook-prod.topfreegames.com";
        objArr[2] = str;
        return String.format("%s://%s/thumbs/%s.jpg", objArr);
    }
}

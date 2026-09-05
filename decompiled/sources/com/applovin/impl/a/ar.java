package com.applovin.impl.a;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ar {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d f190a;
    private final com.applovin.a.j b;

    ar(d dVar) {
        this.f190a = dVar;
        this.b = dVar.f();
    }

    private int a(Throwable th) {
        if (th instanceof SocketTimeoutException) {
            return -102;
        }
        if (!(th instanceof IOException)) {
            return th instanceof JSONException ? -104 : 0;
        }
        String message = th.getMessage();
        return (message == null || !message.toLowerCase().contains("authentication challenge")) ? -100 : 401;
    }

    private HttpURLConnection a(String str, String str2, int i) throws ProtocolException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setRequestMethod(str2);
        httpURLConnection.setConnectTimeout(i < 0 ? ((Integer) this.f190a.a(j.t)).intValue() : i);
        if (i < 0) {
            i = ((Integer) this.f190a.a(j.v)).intValue();
        }
        httpURLConnection.setReadTimeout(i);
        httpURLConnection.setDefaultUseCaches(false);
        httpURLConnection.setAllowUserInteraction(false);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }

    private static void a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception e) {
            }
        }
    }

    private void a(String str, int i, String str2, as asVar) {
        this.b.a("ConnectionManager", i + " received from from \"" + str2 + "\": " + str);
        if (i < 200 || i >= 300) {
            this.b.d("ConnectionManager", i + " error received from \"" + str2 + "\"");
            asVar.a(i);
            return;
        }
        JSONObject jSONObject = new JSONObject();
        if (i != 204 && str != null && str.length() > 2) {
            jSONObject = new JSONObject(str);
        }
        asVar.a(jSONObject, i);
    }

    private void a(String str, String str2, int i, long j) {
        this.b.b("ConnectionManager", "Successful " + str + " returned " + i + " in " + ((System.currentTimeMillis() - j) / 1000.0f) + " s over " + at.a(this.f190a) + " to \"" + str2 + "\"");
    }

    private void a(String str, String str2, int i, long j, Throwable th) {
        this.b.b("ConnectionManager", "Failed " + str + " returned " + i + " in " + ((System.currentTimeMillis() - j) / 1000.0f) + " s over " + at.a(this.f190a) + " to \"" + str2 + "\"", th);
    }

    private static void a(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e) {
            }
        }
    }

    void a(String str, int i, as asVar) throws Throwable {
        a(str, "GET", i, (JSONObject) null, asVar);
    }

    /* JADX WARN: Code duplicated, block: B:29:0x00da A[Catch: all -> 0x00f7, TRY_ENTER, TryCatch #2 {all -> 0x00f7, blocks: (B:15:0x0056, B:16:0x00a7, B:18:0x00ad, B:29:0x00da, B:30:0x00de, B:24:0x00ce), top: B:45:0x0026 }] */
    void a(String str, String str2, int i, JSONObject jSONObject, as asVar) throws Throwable {
        HttpURLConnection httpURLConnectionA;
        InputStream inputStream;
        InputStream inputStream2 = null;
        if (str == null) {
            throw new IllegalArgumentException("No endpoint specified");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("No method specified");
        }
        if (asVar == null) {
            throw new IllegalArgumentException("No callback specified");
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        int iA = 0;
        try {
            try {
                this.b.b("ConnectionManager", "Sending " + str2 + " request to \"" + str + "\"...");
                httpURLConnectionA = a(str, str2, i);
                if (jSONObject != null) {
                    try {
                        String string = jSONObject.toString();
                        this.b.a("ConnectionManager", "Request to \"" + str + "\" is " + string);
                        httpURLConnectionA.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                        httpURLConnectionA.setDoOutput(true);
                        httpURLConnectionA.setFixedLengthStreamingMode(string.getBytes().length);
                        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(httpURLConnectionA.getOutputStream(), "UTF8"));
                        printWriter.print(string);
                        printWriter.close();
                    } catch (Throwable th) {
                        th = th;
                        if (iA == 0) {
                            iA = a(th);
                        }
                        a(str2, str, iA, jCurrentTimeMillis, th);
                        asVar.a(iA);
                        a(inputStream2);
                        a(httpURLConnectionA);
                        return;
                    }
                }
                iA = httpURLConnectionA.getResponseCode();
                if (iA > 0) {
                    InputStream inputStream3 = httpURLConnectionA.getInputStream();
                    try {
                        String strA = at.a(inputStream3);
                        a(str2, str, iA, jCurrentTimeMillis);
                        a(strA, httpURLConnectionA.getResponseCode(), str, asVar);
                        inputStream = inputStream3;
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream2 = inputStream3;
                        a(inputStream2);
                        a(httpURLConnectionA);
                        throw th;
                    }
                } else {
                    a(str2, str, iA, jCurrentTimeMillis, (Throwable) null);
                    asVar.a(iA);
                    inputStream = null;
                }
                a(inputStream);
                a(httpURLConnectionA);
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            httpURLConnectionA = null;
        }
    }

    void a(String str, JSONObject jSONObject, as asVar) throws Throwable {
        a(str, "POST", -1, jSONObject, asVar);
    }
}

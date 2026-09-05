package com.flurry.sdk;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ce extends fi {
    String b;
    String c;
    String d;
    byte[] e;
    ch f;
    private static final String g = ce.class.getSimpleName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static int f514a = 15000;

    public ce(String str, String str2, String str3, byte[] bArr, ch chVar) {
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = bArr;
        this.f = chVar;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0090  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Thread] */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [org.apache.http.client.HttpClient] */
    /* JADX WARN: Type inference failed for: r2v2 */
    @Override // com.flurry.sdk.fi
    public void a() throws Throwable {
        HttpClient httpClientB;
        ?? r2 = 0;
        httpResponseExecute = null;
        HttpResponse httpResponseExecute = null;
        Long lValueOf = Long.valueOf(Thread.currentThread().getId());
        ?? CurrentThread = Thread.currentThread();
        CurrentThread.setName("DataSender Sending Executor Thread, id = " + lValueOf);
        try {
            try {
                ByteArrayEntity byteArrayEntity = new ByteArrayEntity(this.e);
                byteArrayEntity.setContentType("application/octet-stream");
                HttpPost httpPost = new HttpPost(this.b);
                httpPost.setEntity(byteArrayEntity);
                BasicHttpParams basicHttpParams = new BasicHttpParams();
                HttpConnectionParams.setConnectionTimeout(basicHttpParams, 10000);
                HttpConnectionParams.setSoTimeout(basicHttpParams, f514a);
                httpPost.getParams().setBooleanParameter("http.protocol.expect-continue", false);
                httpClientB = ew.b(basicHttpParams);
                try {
                    httpResponseExecute = httpClientB.execute(httpPost);
                    CurrentThread = httpClientB;
                    if (httpClientB != null) {
                        httpClientB.getConnectionManager().shutdown();
                    }
                } catch (Exception e) {
                    e = e;
                    ex.a(6, g, "Exception: ", e);
                    CurrentThread = httpClientB;
                    if (httpClientB != null) {
                        httpClientB.getConnectionManager().shutdown();
                    }
                }
            } catch (Throwable th) {
                th = th;
                r2 = CurrentThread;
                if (r2 != 0) {
                    r2.getConnectionManager().shutdown();
                }
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            httpClientB = null;
        } catch (Throwable th2) {
            th = th2;
            if (r2 != 0) {
                r2.getConnectionManager().shutdown();
            }
            throw th;
        }
        if (httpResponseExecute != null) {
            CurrentThread = httpClientB;
            if (httpResponseExecute.getStatusLine() != null) {
                CurrentThread = httpClientB;
                StatusLine statusLine = httpResponseExecute.getStatusLine();
                this.f.a(statusLine.getStatusCode(), statusLine.getReasonPhrase(), this.c, this.d);
                return;
            }
        }
        CurrentThread = httpClientB;
        CurrentThread = httpClientB;
        CurrentThread = httpClientB;
        this.f.a(this.c, this.d);
    }
}

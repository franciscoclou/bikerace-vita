package com.google.ads;

import android.content.Context;
import com.google.ads.util.AdUtil;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ae implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f619a;
    private final String b;

    public ae(String str, Context context) {
        this.b = str;
        this.f619a = context;
    }

    protected HttpURLConnection a(URL url) {
        return (HttpURLConnection) url.openConnection();
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            com.google.ads.util.b.a("Pinging URL: " + this.b);
            HttpURLConnection httpURLConnectionA = a(new URL(this.b));
            try {
                AdUtil.a(httpURLConnectionA, this.f619a);
                httpURLConnectionA.setInstanceFollowRedirects(true);
                httpURLConnectionA.connect();
                int responseCode = httpURLConnectionA.getResponseCode();
                if (responseCode < 200 || responseCode >= 300) {
                    com.google.ads.util.b.e("Did not receive 2XX (got " + responseCode + ") from pinging URL: " + this.b);
                }
            } finally {
                httpURLConnectionA.disconnect();
            }
        } catch (Throwable th) {
            com.google.ads.util.b.d("Unable to ping the URL: " + this.b, th);
        }
    }
}

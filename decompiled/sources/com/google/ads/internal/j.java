package com.google.ads.internal;

import android.content.Context;
import com.google.ads.util.AdUtil;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class j implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f682a;
    private Context b;

    public j(String str, Context context) {
        this.f682a = str;
        this.b = context;
    }

    protected HttpURLConnection a(URL url) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setInstanceFollowRedirects(true);
        AdUtil.a(httpURLConnection, this.b);
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        return httpURLConnection;
    }

    protected BufferedOutputStream a(HttpURLConnection httpURLConnection) {
        return new BufferedOutputStream(httpURLConnection.getOutputStream());
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            HttpURLConnection httpURLConnectionA = a(new URL(com.google.ads.m.a().b.a().h.a()));
            byte[] bytes = new a(this.f682a).a().toString().getBytes();
            httpURLConnectionA.setFixedLengthStreamingMode(bytes.length);
            try {
                BufferedOutputStream bufferedOutputStreamA = a(httpURLConnectionA);
                bufferedOutputStreamA.write(bytes);
                bufferedOutputStreamA.close();
                if (httpURLConnectionA.getResponseCode() != 200) {
                    com.google.ads.util.b.b("Got error response from BadAd backend: " + httpURLConnectionA.getResponseMessage());
                }
            } finally {
                httpURLConnectionA.disconnect();
            }
        } catch (IOException e) {
            com.google.ads.util.b.b("Error reporting bad ad.", e);
        }
    }

    public class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f683a;

        public a(String str) {
            this.f683a = str;
        }

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("debugHeader", this.f683a);
            } catch (JSONException e) {
                com.google.ads.util.b.b("Could not build ReportAdJson from inputs.", e);
            }
            return jSONObject;
        }
    }
}

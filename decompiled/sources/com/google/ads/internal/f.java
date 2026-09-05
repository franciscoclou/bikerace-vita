package com.google.ads.internal;

import android.content.Context;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.facebook.AppEventsConstants;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.util.AdUtil;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class f implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.google.ads.l f678a;
    private final a b;
    private volatile boolean c;
    private boolean d;
    private String e;
    private Thread f;

    public interface a {
        HttpURLConnection a(URL url);
    }

    f(com.google.ads.l lVar) {
        this(lVar, new a() { // from class: com.google.ads.internal.f.1
            @Override // com.google.ads.internal.f.a
            public HttpURLConnection a(URL url) {
                return (HttpURLConnection) url.openConnection();
            }
        });
    }

    f(com.google.ads.l lVar, a aVar) {
        this.f = null;
        this.f678a = lVar;
        this.b = aVar;
    }

    void a() {
        this.c = true;
    }

    private void a(HttpURLConnection httpURLConnection) {
        b(httpURLConnection);
        f(httpURLConnection);
        g(httpURLConnection);
        h(httpURLConnection);
        i(httpURLConnection);
        e(httpURLConnection);
        j(httpURLConnection);
        k(httpURLConnection);
        l(httpURLConnection);
        d(httpURLConnection);
        c(httpURLConnection);
        m(httpURLConnection);
        n(httpURLConnection);
    }

    private void b(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("X-Afma-Debug-Dialog");
        if (!TextUtils.isEmpty(headerField)) {
            this.f678a.b.a().f(headerField);
        }
    }

    private void c(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("Content-Type");
        if (!TextUtils.isEmpty(headerField)) {
            this.f678a.b.a().b(headerField);
        }
    }

    private void d(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("X-Afma-Mediation");
        if (!TextUtils.isEmpty(headerField)) {
            this.f678a.b.a().b(Boolean.valueOf(headerField).booleanValue());
        }
    }

    private void e(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("X-Afma-Interstitial-Timeout");
        if (!TextUtils.isEmpty(headerField)) {
            try {
                this.f678a.f696a.a().b.a().a((long) (Float.parseFloat(headerField) * 1000.0f));
            } catch (NumberFormatException e) {
                com.google.ads.util.b.d("Could not get timeout value: " + headerField, e);
            }
        }
    }

    private void f(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("X-Afma-Tracking-Urls");
        if (!TextUtils.isEmpty(headerField)) {
            for (String str : headerField.trim().split("\\s+")) {
                this.f678a.f696a.a().b.a().b(str);
            }
        }
    }

    private void g(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("X-Afma-Manual-Tracking-Urls");
        if (!TextUtils.isEmpty(headerField)) {
            for (String str : headerField.trim().split("\\s+")) {
                this.f678a.f696a.a().b.a().c(str);
            }
        }
    }

    private void h(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("X-Afma-Click-Tracking-Urls");
        if (!TextUtils.isEmpty(headerField)) {
            for (String str : headerField.trim().split("\\s+")) {
                this.f678a.b.a().a(str);
            }
        }
    }

    private void i(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("X-Afma-Refresh-Rate");
        if (!TextUtils.isEmpty(headerField)) {
            try {
                float f = Float.parseFloat(headerField);
                d dVarA = this.f678a.f696a.a().b.a();
                if (f > 0.0f) {
                    dVarA.a(f);
                    if (!dVarA.t()) {
                        dVarA.g();
                        return;
                    }
                    return;
                }
                if (dVarA.t()) {
                    dVarA.f();
                }
            } catch (NumberFormatException e) {
                com.google.ads.util.b.d("Could not get refresh value: " + headerField, e);
            }
        }
    }

    private void j(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("X-Afma-Orientation");
        if (!TextUtils.isEmpty(headerField)) {
            if (headerField.equals("portrait")) {
                this.f678a.b.a().a(AdUtil.b());
            } else if (headerField.equals("landscape")) {
                this.f678a.b.a().a(AdUtil.a());
            }
        }
    }

    private void k(HttpURLConnection httpURLConnection) {
        if (!TextUtils.isEmpty(httpURLConnection.getHeaderField("X-Afma-Doritos-Cache-Life"))) {
            try {
                this.f678a.f696a.a().b.a().b(Long.parseLong(httpURLConnection.getHeaderField("X-Afma-Doritos-Cache-Life")));
            } catch (NumberFormatException e) {
                com.google.ads.util.b.e("Got bad value of Doritos cookie cache life from header: " + httpURLConnection.getHeaderField("X-Afma-Doritos-Cache-Life") + ". Using default value instead.");
            }
        }
    }

    public void a(boolean z) {
        this.d = z;
    }

    private void l(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("Cache-Control");
        if (!TextUtils.isEmpty(headerField)) {
            this.f678a.b.a().c(headerField);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void m(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("X-Afma-Ad-Size");
        if (!TextUtils.isEmpty(headerField)) {
            try {
                String[] strArrSplit = headerField.split("x", 2);
                if (strArrSplit.length != 2) {
                    com.google.ads.util.b.e("Could not parse size header: " + headerField);
                    headerField = headerField;
                } else {
                    int i = Integer.parseInt(strArrSplit[0]);
                    int i2 = Integer.parseInt(strArrSplit[1]);
                    c cVarA = this.f678a.b.a();
                    cVarA.a(new AdSize(i, i2));
                    headerField = cVarA;
                }
            } catch (NumberFormatException e) {
                com.google.ads.util.b.e("Could not parse size header: " + headerField);
            }
        }
    }

    private void n(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("X-Afma-Disable-Activation-And-Scroll");
        if (!TextUtils.isEmpty(headerField)) {
            this.f678a.b.a().a(headerField.equals(AppEventsConstants.EVENT_PARAM_VALUE_YES));
        }
    }

    synchronized void a(String str) {
        if (this.f == null) {
            this.e = str;
            this.c = false;
            this.f = new Thread(this);
            this.f.start();
        }
    }

    private void a(HttpURLConnection httpURLConnection, int i) {
        if (300 <= i && i < 400) {
            String headerField = httpURLConnection.getHeaderField("Location");
            if (headerField == null) {
                com.google.ads.util.b.c("Could not get redirect location from a " + i + " redirect.");
                this.f678a.b.a().a(AdRequest.ErrorCode.INTERNAL_ERROR);
                a();
                return;
            } else {
                a(httpURLConnection);
                this.e = headerField;
                return;
            }
        }
        if (i == 200) {
            a(httpURLConnection);
            String strTrim = AdUtil.a(new InputStreamReader(httpURLConnection.getInputStream())).trim();
            com.google.ads.util.b.a("Response content is: " + strTrim);
            if (TextUtils.isEmpty(strTrim)) {
                com.google.ads.util.b.a("Response message is null or zero length: " + strTrim);
                this.f678a.b.a().a(AdRequest.ErrorCode.NO_FILL);
                a();
                return;
            } else {
                this.f678a.b.a().a(strTrim, this.e);
                a();
                return;
            }
        }
        if (i == 400) {
            com.google.ads.util.b.c("Bad request");
            this.f678a.b.a().a(AdRequest.ErrorCode.INVALID_REQUEST);
            a();
        } else {
            com.google.ads.util.b.c("Invalid response code: " + i);
            this.f678a.b.a().a(AdRequest.ErrorCode.INTERNAL_ERROR);
            a();
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            b();
        } catch (MalformedURLException e) {
            com.google.ads.util.b.b("Received malformed ad url from javascript.", e);
            this.f678a.b.a().a(AdRequest.ErrorCode.INTERNAL_ERROR);
        } catch (IOException e2) {
            com.google.ads.util.b.b("IOException connecting to ad url.", e2);
            this.f678a.b.a().a(AdRequest.ErrorCode.NETWORK_ERROR);
        } catch (Throwable th) {
            com.google.ads.util.b.b("An unknown error occurred in AdResponseLoader.", th);
            this.f678a.b.a().a(AdRequest.ErrorCode.INTERNAL_ERROR);
        }
    }

    private void b() {
        while (!this.c) {
            HttpURLConnection httpURLConnectionA = this.b.a(new URL(this.e));
            try {
                a(this.f678a.f696a.a().f.a(), httpURLConnectionA);
                AdUtil.a(httpURLConnectionA, this.f678a.f696a.a().f.a());
                httpURLConnectionA.setInstanceFollowRedirects(false);
                httpURLConnectionA.connect();
                a(httpURLConnectionA, httpURLConnectionA.getResponseCode());
                httpURLConnectionA.disconnect();
            } catch (Throwable th) {
                httpURLConnectionA.disconnect();
                throw th;
            }
        }
    }

    private void a(Context context, HttpURLConnection httpURLConnection) {
        String string = PreferenceManager.getDefaultSharedPreferences(context).getString("drt", "");
        if (this.d && !TextUtils.isEmpty(string)) {
            if (AdUtil.f712a == 8) {
                httpURLConnection.addRequestProperty("X-Afma-drt-Cookie", string);
            } else {
                httpURLConnection.addRequestProperty("Cookie", string);
            }
        }
    }
}

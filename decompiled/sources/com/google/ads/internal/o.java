package com.google.ads.internal;

import android.webkit.WebView;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class o implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ c f692a;
    private final String b;
    private final String c;
    private final WebView d;

    public o(c cVar, WebView webView, String str, String str2) {
        this.f692a = cVar;
        this.d = webView;
        this.b = str;
        this.c = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f692a.j.c.a(Boolean.valueOf(this.f692a.p));
        this.f692a.j.f696a.a().b.a().l().a(this.f692a.p);
        if (this.f692a.j.f696a.a().e.a() != null) {
            this.f692a.j.f696a.a().e.a().setOverlayEnabled(!this.f692a.p);
        }
        if (this.c != null) {
            this.d.loadDataWithBaseURL(this.b, this.c, "text/html", "utf-8", null);
        } else {
            this.d.loadUrl(this.b);
        }
    }
}

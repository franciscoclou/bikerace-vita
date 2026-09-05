package com.google.ads.internal;

import android.webkit.WebView;
import com.google.ads.AdRequest;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class m implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d f690a;
    private final WebView b;
    private final f c;
    private final AdRequest.ErrorCode d;
    private final boolean e;

    public m(d dVar, WebView webView, f fVar, AdRequest.ErrorCode errorCode, boolean z) {
        this.f690a = dVar;
        this.b = webView;
        this.c = fVar;
        this.d = errorCode;
        this.e = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.b != null) {
            this.b.stopLoading();
            this.b.destroy();
        }
        if (this.c != null) {
            this.c.a();
        }
        if (this.e) {
            this.f690a.l().stopLoading();
            if (this.f690a.i().i.a() != null) {
                this.f690a.i().i.a().setVisibility(8);
            }
        }
        this.f690a.a(this.d);
    }
}

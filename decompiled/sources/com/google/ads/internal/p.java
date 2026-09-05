package com.google.ads.internal;

import android.webkit.WebView;
import com.google.ads.AdSize;
import java.util.LinkedList;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class p implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ c f693a;
    private final d b;
    private final WebView c;
    private final LinkedList<String> d;
    private final int e;
    private final boolean f;
    private final String g;
    private final AdSize h;

    public p(c cVar, d dVar, WebView webView, LinkedList<String> linkedList, int i, boolean z, String str, AdSize adSize) {
        this.f693a = cVar;
        this.b = dVar;
        this.c = webView;
        this.d = linkedList;
        this.e = i;
        this.f = z;
        this.g = str;
        this.h = adSize;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.c != null) {
            this.c.stopLoading();
            this.c.destroy();
        }
        this.b.a(this.d);
        this.b.a(this.e);
        this.b.a(this.f);
        this.b.a(this.g);
        if (this.h != null) {
            this.f693a.j.f696a.a().g.a().b(this.h);
            this.b.l().setAdSize(this.h);
        }
        this.b.E();
    }
}

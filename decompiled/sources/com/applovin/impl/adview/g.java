package com.applovin.impl.adview;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class g implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ boolean f233a;
    final /* synthetic */ boolean b;
    final /* synthetic */ e c;

    g(e eVar, boolean z, boolean z2) {
        this.c = eVar;
        this.f233a = z;
        this.b = z2;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f233a && this.b) {
            this.c.l();
        } else {
            this.c.k();
        }
    }
}

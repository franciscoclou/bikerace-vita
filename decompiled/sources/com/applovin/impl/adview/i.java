package com.applovin.impl.adview;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class i implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ int f235a;
    final /* synthetic */ e b;

    i(e eVar, int i) {
        this.b = eVar;
        this.f235a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.b.g != null) {
            this.b.g.a(this.f235a);
        }
    }
}

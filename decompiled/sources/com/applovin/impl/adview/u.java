package com.applovin.impl.adview;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class u implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ a f247a;

    u(a aVar) {
        this.f247a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f247a.m != null) {
            a.b(this.f247a.h, this.f247a.m.g());
            this.f247a.h.a(this.f247a.m);
        }
    }
}

package com.applovin.impl.adview;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class q implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ a f243a;
    private final com.applovin.a.a b;

    public q(a aVar, com.applovin.a.a aVar2) {
        this.f243a = aVar;
        this.b = aVar2;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.applovin.a.b bVar = this.f243a.v;
        if (bVar == null || this.b == null) {
            return;
        }
        try {
            bVar.a(this.b);
        } catch (Throwable th) {
            this.f243a.d.c("AppLovinAdView", "Exception while notifying ad click listener", th);
        }
    }
}

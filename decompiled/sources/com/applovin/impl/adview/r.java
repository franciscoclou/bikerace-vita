package com.applovin.impl.adview;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class r implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ a f244a;
    private final com.applovin.a.a b;

    public r(a aVar, com.applovin.a.a aVar2) {
        this.f244a = aVar;
        this.b = aVar2;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.applovin.a.c cVar = this.f244a.t;
        if (cVar == null || this.b == null) {
            return;
        }
        try {
            cVar.a(this.b);
        } catch (Throwable th) {
            this.f244a.d.c("AppLovinAdView", "Exception while notifying ad display listener", th);
        }
    }
}

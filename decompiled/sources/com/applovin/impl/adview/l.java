package com.applovin.impl.adview;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class l implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ int f238a;
    final /* synthetic */ a b;

    l(a aVar, int i) {
        this.b = aVar;
        this.f238a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.b.s != null) {
                this.b.s.a(this.f238a);
            }
        } catch (Throwable th) {
            this.b.d.c("AppLovinAdView", "Exception while running app load  callback", th);
        }
    }
}

package com.applovin.adview;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class m implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ int f171a;
    final /* synthetic */ int b;
    final /* synthetic */ l c;

    m(l lVar, int i, int i2) {
        this.c = lVar;
        this.f171a = i;
        this.b = i2;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.c.f170a.e.d("AppLovinInterstitialActivity", "Video view error (" + this.f171a + "," + this.b + ") - showing close button.");
        this.c.f170a.g();
    }
}

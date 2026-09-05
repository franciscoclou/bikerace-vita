package com.applovin.adview;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class j implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ int f168a;
    final /* synthetic */ int b;
    final /* synthetic */ i c;

    j(i iVar, int i, int i2) {
        this.c = iVar;
        this.f168a = i;
        this.b = i2;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.c.f167a.f166a.e.d("AppLovinInterstitialActivity", "Media player error (" + this.f168a + "," + this.b + ") - showing close button.");
        this.c.f167a.f166a.g();
    }
}

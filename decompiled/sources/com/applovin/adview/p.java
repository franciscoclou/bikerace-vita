package com.applovin.adview;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class p implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ AppLovinInterstitialActivity f174a;

    p(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.f174a = appLovinInterstitialActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f174a.k();
    }
}

package com.applovin.adview;

import java.util.UUID;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class f implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ int f164a;
    final /* synthetic */ UUID b;
    final /* synthetic */ AppLovinInterstitialActivity c;

    f(AppLovinInterstitialActivity appLovinInterstitialActivity, int i, UUID uuid) {
        this.c = appLovinInterstitialActivity;
        this.f164a = i;
        this.b = uuid;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.c.a(this.f164a, this.b);
    }
}

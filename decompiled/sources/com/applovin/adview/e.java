package com.applovin.adview;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class e implements com.applovin.a.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ AppLovinInterstitialActivity f163a;

    e(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.f163a = appLovinInterstitialActivity;
    }

    @Override // com.applovin.a.c
    public void a(com.applovin.a.a aVar) {
        this.f163a.b(aVar);
    }

    @Override // com.applovin.a.c
    public void b(com.applovin.a.a aVar) {
        this.f163a.h = (com.applovin.impl.a.a) aVar;
        if (this.f163a.i) {
            return;
        }
        this.f163a.a(aVar);
    }
}

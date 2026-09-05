package com.applovin.adview;

import android.view.View;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class n implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ AppLovinInterstitialActivity f172a;

    n(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.f172a = appLovinInterstitialActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.f172a.b();
    }
}

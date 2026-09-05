package com.applovin.adview;

import android.view.animation.AlphaAnimation;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class o implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ AppLovinInterstitialActivity f173a;

    o(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.f173a = appLovinInterstitialActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.f173a.n) {
                this.f173a.r.setVisibility(0);
            } else {
                this.f173a.n = true;
                this.f173a.r.setVisibility(0);
                this.f173a.r.bringToFront();
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration(this.f173a.f.d());
                alphaAnimation.setRepeatCount(0);
                this.f173a.r.startAnimation(alphaAnimation);
            }
        } catch (Throwable th) {
            this.f173a.b();
        }
    }
}

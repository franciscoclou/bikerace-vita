package com.applovin.adview;

import android.view.animation.Animation;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class q implements Animation.AnimationListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ AppLovinInterstitialActivity f175a;

    q(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.f175a = appLovinInterstitialActivity;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        this.f175a.s.setVisibility(8);
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationRepeat(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationStart(Animation animation) {
    }
}

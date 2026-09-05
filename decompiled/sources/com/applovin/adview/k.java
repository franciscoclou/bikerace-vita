package com.applovin.adview;

import android.media.MediaPlayer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class k implements MediaPlayer.OnCompletionListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ AppLovinInterstitialActivity f169a;

    k(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.f169a = appLovinInterstitialActivity;
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        this.f169a.m = true;
        this.f169a.c();
    }
}

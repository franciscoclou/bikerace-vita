package com.applovin.adview;

import android.media.MediaPlayer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class l implements MediaPlayer.OnErrorListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ AppLovinInterstitialActivity f170a;

    l(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.f170a = appLovinInterstitialActivity;
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        this.f170a.o.post(new m(this, i, i2));
        return true;
    }
}

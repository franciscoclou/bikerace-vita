package com.applovin.adview;

import android.media.MediaPlayer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class h implements MediaPlayer.OnPreparedListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ AppLovinInterstitialActivity f166a;

    h(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.f166a = appLovinInterstitialActivity;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        this.f166a.q.a(mediaPlayer.getVideoWidth(), mediaPlayer.getVideoHeight());
        mediaPlayer.setDisplay(this.f166a.q.getHolder());
        mediaPlayer.setOnErrorListener(new i(this));
    }
}

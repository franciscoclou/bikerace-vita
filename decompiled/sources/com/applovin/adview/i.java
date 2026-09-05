package com.applovin.adview;

import android.media.MediaPlayer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class i implements MediaPlayer.OnErrorListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ h f167a;

    i(h hVar) {
        this.f167a = hVar;
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        this.f167a.f166a.o.post(new j(this, i, i2));
        return true;
    }
}

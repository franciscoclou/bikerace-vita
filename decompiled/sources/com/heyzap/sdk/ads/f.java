package com.heyzap.sdk.ads;

import android.media.MediaPlayer;
import com.facebook.internal.AnalyticsEvents;

/* JADX INFO: compiled from: FullscreenVideoView.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class f implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ e f782a;

    private f(e eVar) {
        this.f782a = eVar;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        if (this.f782a.f != null && this.f782a.f.isShowing()) {
            this.f782a.f.dismiss();
        }
        this.f782a.c();
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        String str;
        String str2;
        if (this.f782a.f != null) {
            this.f782a.f.dismiss();
            this.f782a.f = null;
        }
        this.f782a.e.cancel();
        this.f782a.e.purge();
        switch (i) {
            case 100:
                str = "Server Died.";
                break;
            default:
                str = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
                break;
        }
        switch (i2) {
            case -1010:
                str2 = "Unsupported.";
                break;
            case -1007:
                str2 = "Malformed.";
                break;
            case -1004:
                str2 = "Error IO.";
                break;
            case -110:
                str2 = "Timed Out.";
                break;
            case 200:
                str2 = "Not Valid for Progressive Playback.";
                break;
            default:
                str2 = "Unknown.";
                break;
        }
        com.heyzap.internal.k.b("MediaPlayer Error! What: " + str + " Extra: " + str2);
        if (this.f782a.j != null) {
            this.f782a.j.f();
            return true;
        }
        return true;
    }

    @Override // android.media.MediaPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        this.f782a.g = i;
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        this.f782a.e.cancel();
        this.f782a.e.purge();
        if (this.f782a.j != null) {
            this.f782a.j.e();
        }
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        this.f782a.a(i, i2);
    }
}

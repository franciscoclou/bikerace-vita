package com.heyzap.sdk.ads;

import android.view.SurfaceHolder;

/* JADX INFO: compiled from: FullscreenVideoView.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class h implements SurfaceHolder.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ e f784a;

    private h(e eVar) {
        this.f784a = eVar;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        try {
            this.f784a.k.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            this.f784a.k.setDisplay(surfaceHolder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        try {
            if (this.f784a.k != null && this.f784a.k.isPlaying()) {
                this.f784a.k.pause();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

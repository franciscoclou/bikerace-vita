package com.google.ads.internal;

import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class l implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final WeakReference<AdVideoView> f689a;

    public l(AdVideoView adVideoView) {
        this.f689a = new WeakReference<>(adVideoView);
    }

    @Override // java.lang.Runnable
    public void run() {
        AdVideoView adVideoView = this.f689a.get();
        if (adVideoView == null) {
            com.google.ads.util.b.d("The video must be gone, so cancelling the timeupdate task.");
        } else {
            adVideoView.f();
            com.google.ads.m.a().c.a().postDelayed(this, 250L);
        }
    }

    public void a() {
        com.google.ads.m.a().c.a().postDelayed(this, 250L);
    }
}

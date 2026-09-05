package com.google.ads;

import android.app.Activity;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class InterstitialAd implements Ad {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.google.ads.internal.d f616a;

    public InterstitialAd(Activity activity, String str) {
        this(activity, str, false);
    }

    public InterstitialAd(Activity activity, String str, boolean z) {
        this.f616a = new com.google.ads.internal.d(this, activity, null, str, null, z);
    }

    @Override // com.google.ads.Ad
    public boolean isReady() {
        return this.f616a.s();
    }

    @Override // com.google.ads.Ad
    public void loadAd(AdRequest adRequest) {
        this.f616a.a(adRequest);
    }

    public void show() {
        this.f616a.B();
    }

    @Override // com.google.ads.Ad
    public void setAdListener(AdListener adListener) {
        this.f616a.i().o.a(adListener);
    }

    protected void setAppEventListener(AppEventListener appEventListener) {
        this.f616a.i().p.a(appEventListener);
    }

    @Override // com.google.ads.Ad
    public void stopLoading() {
        this.f616a.C();
    }
}

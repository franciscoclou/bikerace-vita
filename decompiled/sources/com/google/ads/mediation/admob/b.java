package com.google.ads.mediation.admob;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class b implements AdListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ AdMobAdapter f703a;

    private b(AdMobAdapter adMobAdapter) {
        this.f703a = adMobAdapter;
    }

    @Override // com.google.ads.AdListener
    public void onReceiveAd(Ad ad) {
        this.f703a.b.onReceivedAd(this.f703a);
    }

    @Override // com.google.ads.AdListener
    public void onFailedToReceiveAd(Ad ad, AdRequest.ErrorCode errorCode) {
        this.f703a.b.onFailedToReceiveAd(this.f703a, errorCode);
    }

    @Override // com.google.ads.AdListener
    public void onPresentScreen(Ad ad) {
        this.f703a.b.onPresentScreen(this.f703a);
    }

    @Override // com.google.ads.AdListener
    public void onDismissScreen(Ad ad) {
        this.f703a.b.onDismissScreen(this.f703a);
    }

    @Override // com.google.ads.AdListener
    public void onLeaveApplication(Ad ad) {
        this.f703a.b.onLeaveApplication(this.f703a);
    }
}

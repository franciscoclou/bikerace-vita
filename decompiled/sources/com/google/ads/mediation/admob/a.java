package com.google.ads.mediation.admob;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class a implements AdListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ AdMobAdapter f702a;

    private a(AdMobAdapter adMobAdapter) {
        this.f702a = adMobAdapter;
    }

    @Override // com.google.ads.AdListener
    public void onReceiveAd(Ad ad) {
        this.f702a.f700a.onReceivedAd(this.f702a);
    }

    @Override // com.google.ads.AdListener
    public void onFailedToReceiveAd(Ad ad, AdRequest.ErrorCode errorCode) {
        this.f702a.f700a.onFailedToReceiveAd(this.f702a, errorCode);
    }

    @Override // com.google.ads.AdListener
    public void onPresentScreen(Ad ad) {
        this.f702a.f700a.onClick(this.f702a);
        this.f702a.f700a.onPresentScreen(this.f702a);
    }

    @Override // com.google.ads.AdListener
    public void onDismissScreen(Ad ad) {
        this.f702a.f700a.onDismissScreen(this.f702a);
    }

    @Override // com.google.ads.AdListener
    public void onLeaveApplication(Ad ad) {
        this.f702a.f700a.onLeaveApplication(this.f702a);
    }
}

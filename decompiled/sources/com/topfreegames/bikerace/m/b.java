package com.topfreegames.bikerace.m;

import android.view.View;
import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.ap;

/* JADX INFO: compiled from: AdUtils.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class b implements AdListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private AdView f1294a;

    public b(AdView adView) {
        this.f1294a = null;
        this.f1294a = adView;
    }

    @Override // com.google.ads.AdListener
    public void onFailedToReceiveAd(Ad ad, AdRequest.ErrorCode errorCode) {
        try {
            if (ap.d()) {
                System.err.println("Ad failed: " + ad.toString() + errorCode.toString());
            }
            this.f1294a.setVisibility(8);
        } catch (Exception e) {
        }
    }

    @Override // com.google.ads.AdListener
    public void onDismissScreen(Ad ad) {
    }

    @Override // com.google.ads.AdListener
    public void onLeaveApplication(Ad ad) {
    }

    @Override // com.google.ads.AdListener
    public void onPresentScreen(Ad ad) {
    }

    @Override // com.google.ads.AdListener
    public void onReceiveAd(Ad ad) {
        try {
            if (((BikeRaceApplication) this.f1294a.getContext().getApplicationContext()).a().c()) {
                this.f1294a.setVisibility(0);
                ((View) this.f1294a.getParent()).setVisibility(0);
            }
        } catch (Exception e) {
        }
    }
}

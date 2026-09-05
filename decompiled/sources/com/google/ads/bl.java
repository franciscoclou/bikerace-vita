package com.google.ads;

import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class bl implements MediationInterstitialListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final h f646a;

    bl(h hVar) {
        this.f646a = hVar;
    }

    @Override // com.google.ads.mediation.MediationInterstitialListener
    public void onReceivedAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        synchronized (this.f646a) {
            com.google.ads.util.a.a(mediationInterstitialAdapter, this.f646a.i());
            if (this.f646a.c()) {
                com.google.ads.util.b.e("Got an onReceivedAd() callback after loadAdTask is done from an interstitial adapter. Ignoring callback.");
            } else {
                this.f646a.a(true, g.a.AD);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationInterstitialListener
    public void onFailedToReceiveAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter, AdRequest.ErrorCode errorCode) {
        synchronized (this.f646a) {
            com.google.ads.util.a.a(mediationInterstitialAdapter, this.f646a.i());
            com.google.ads.util.b.a("Mediation adapter " + mediationInterstitialAdapter.getClass().getName() + " failed to receive ad with error code: " + errorCode);
            if (this.f646a.c()) {
                com.google.ads.util.b.b("Got an onFailedToReceiveAd() callback after loadAdTask is done from an interstitial adapter.  Ignoring callback.");
            } else {
                this.f646a.a(false, errorCode == AdRequest.ErrorCode.NO_FILL ? g.a.NO_FILL : g.a.ERROR);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationInterstitialListener
    public void onPresentScreen(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        synchronized (this.f646a) {
            this.f646a.j().a(this.f646a);
        }
    }

    @Override // com.google.ads.mediation.MediationInterstitialListener
    public void onDismissScreen(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        synchronized (this.f646a) {
            this.f646a.j().b(this.f646a);
        }
    }

    @Override // com.google.ads.mediation.MediationInterstitialListener
    public void onLeaveApplication(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        synchronized (this.f646a) {
            this.f646a.j().c(this.f646a);
        }
    }
}

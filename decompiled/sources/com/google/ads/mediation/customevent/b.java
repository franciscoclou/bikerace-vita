package com.google.ads.mediation.customevent;

import com.google.ads.AdRequest;
import com.google.ads.mediation.MediationInterstitialListener;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class b implements CustomEventInterstitialListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ CustomEventAdapter f707a;
    private final MediationInterstitialListener b;

    public b(CustomEventAdapter customEventAdapter, MediationInterstitialListener mediationInterstitialListener) {
        this.f707a = customEventAdapter;
        this.b = mediationInterstitialListener;
    }

    @Override // com.google.ads.mediation.customevent.CustomEventInterstitialListener
    public void onReceivedAd() {
        com.google.ads.util.b.a(a() + " called onReceivedAd.");
        this.b.onReceivedAd(this.f707a);
    }

    @Override // com.google.ads.mediation.customevent.CustomEventListener
    public void onFailedToReceiveAd() {
        com.google.ads.util.b.a(a() + " called onFailedToReceiveAd().");
        this.b.onFailedToReceiveAd(this.f707a, AdRequest.ErrorCode.NO_FILL);
    }

    @Override // com.google.ads.mediation.customevent.CustomEventListener
    public void onPresentScreen() {
        com.google.ads.util.b.a(a() + " called onPresentScreen().");
        this.b.onPresentScreen(this.f707a);
    }

    @Override // com.google.ads.mediation.customevent.CustomEventListener
    public void onDismissScreen() {
        com.google.ads.util.b.a(a() + " called onDismissScreen().");
        this.b.onDismissScreen(this.f707a);
    }

    @Override // com.google.ads.mediation.customevent.CustomEventListener
    public synchronized void onLeaveApplication() {
        com.google.ads.util.b.a(a() + " called onLeaveApplication().");
        this.b.onLeaveApplication(this.f707a);
    }

    private String a() {
        return "Interstitial custom event labeled '" + this.f707a.f704a + "'";
    }
}

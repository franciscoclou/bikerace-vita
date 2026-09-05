package com.google.ads.mediation.customevent;

import android.view.View;
import com.google.ads.AdRequest;
import com.google.ads.mediation.MediationBannerListener;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class a implements CustomEventBannerListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ CustomEventAdapter f706a;
    private View b;
    private final MediationBannerListener c;

    public a(CustomEventAdapter customEventAdapter, MediationBannerListener mediationBannerListener) {
        this.f706a = customEventAdapter;
        this.c = mediationBannerListener;
    }

    @Override // com.google.ads.mediation.customevent.CustomEventBannerListener
    public synchronized void onReceivedAd(View view) {
        com.google.ads.util.b.a(b() + " called onReceivedAd.");
        this.b = view;
        this.c.onReceivedAd(this.f706a);
    }

    @Override // com.google.ads.mediation.customevent.CustomEventListener
    public void onFailedToReceiveAd() {
        com.google.ads.util.b.a(b() + " called onFailedToReceiveAd().");
        this.c.onFailedToReceiveAd(this.f706a, AdRequest.ErrorCode.NO_FILL);
    }

    @Override // com.google.ads.mediation.customevent.CustomEventBannerListener
    public void onClick() {
        com.google.ads.util.b.a(b() + " called onClick().");
        this.c.onClick(this.f706a);
    }

    @Override // com.google.ads.mediation.customevent.CustomEventListener
    public void onPresentScreen() {
        com.google.ads.util.b.a(b() + " called onPresentScreen().");
        this.c.onPresentScreen(this.f706a);
    }

    @Override // com.google.ads.mediation.customevent.CustomEventListener
    public void onDismissScreen() {
        com.google.ads.util.b.a(b() + " called onDismissScreen().");
        this.c.onDismissScreen(this.f706a);
    }

    @Override // com.google.ads.mediation.customevent.CustomEventListener
    public synchronized void onLeaveApplication() {
        com.google.ads.util.b.a(b() + " called onLeaveApplication().");
        this.c.onLeaveApplication(this.f706a);
    }

    public synchronized View a() {
        return this.b;
    }

    private String b() {
        return "Banner custom event labeled '" + this.f706a.f704a + "'";
    }
}

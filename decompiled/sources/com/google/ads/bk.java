package com.google.ads;

import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class bk implements MediationBannerListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final h f645a;
    private boolean b;

    public bk(h hVar) {
        this.f645a = hVar;
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public void onReceivedAd(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        synchronized (this.f645a) {
            com.google.ads.util.a.a(mediationBannerAdapter, this.f645a.i());
            try {
                this.f645a.a(mediationBannerAdapter.getBannerView());
                if (!this.f645a.c()) {
                    this.b = false;
                    this.f645a.a(true, g.a.AD);
                } else {
                    this.b = true;
                    this.f645a.j().a(this.f645a, this.f645a.f());
                }
            } catch (Throwable th) {
                com.google.ads.util.b.b("Error while getting banner View from adapter (" + this.f645a.h() + "): ", th);
                if (!this.f645a.c()) {
                    this.f645a.a(false, g.a.EXCEPTION);
                }
            }
        }
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public void onFailedToReceiveAd(MediationBannerAdapter<?, ?> mediationBannerAdapter, AdRequest.ErrorCode errorCode) {
        synchronized (this.f645a) {
            com.google.ads.util.a.a(mediationBannerAdapter, this.f645a.i());
            com.google.ads.util.b.a("Mediation adapter " + mediationBannerAdapter.getClass().getName() + " failed to receive ad with error code: " + errorCode);
            if (!this.f645a.c()) {
                this.f645a.a(false, errorCode == AdRequest.ErrorCode.NO_FILL ? g.a.NO_FILL : g.a.ERROR);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public void onPresentScreen(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        synchronized (this.f645a) {
            this.f645a.j().a(this.f645a);
        }
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public void onDismissScreen(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        synchronized (this.f645a) {
            this.f645a.j().b(this.f645a);
        }
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public void onLeaveApplication(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        synchronized (this.f645a) {
            this.f645a.j().c(this.f645a);
        }
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public void onClick(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        synchronized (this.f645a) {
            com.google.ads.util.a.a(this.f645a.c());
            this.f645a.j().a(this.f645a, this.b);
        }
    }
}

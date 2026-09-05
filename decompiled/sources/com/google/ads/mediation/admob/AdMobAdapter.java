package com.google.ads.mediation.admob;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.ads.InterstitialAd;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.util.AdUtil;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AdMobAdapter implements MediationBannerAdapter<AdMobAdapterExtras, AdMobAdapterServerParameters>, MediationInterstitialAdapter<AdMobAdapterExtras, AdMobAdapterServerParameters> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private MediationBannerListener f700a;
    private MediationInterstitialListener b;
    private AdView c;
    private InterstitialAd d;

    private void a() {
        if (b()) {
            throw new IllegalStateException("Adapter has already been destroyed");
        }
    }

    private boolean b() {
        return this.c == null && this.d == null;
    }

    private AdRequest a(Activity activity, AdMobAdapterServerParameters adMobAdapterServerParameters, MediationAdRequest mediationAdRequest, AdMobAdapterExtras adMobAdapterExtras) {
        AdMobAdapterExtras adMobAdapterExtras2 = new AdMobAdapterExtras(adMobAdapterExtras);
        adMobAdapterExtras2.addExtra("_norefresh", "t");
        adMobAdapterExtras2.addExtra("gw", 1);
        if (adMobAdapterServerParameters.allowHouseAds != null) {
            adMobAdapterExtras2.addExtra("mad_hac", adMobAdapterServerParameters.allowHouseAds);
        }
        AdRequest networkExtras = new AdRequest().setBirthday(mediationAdRequest.getBirthday()).setGender(mediationAdRequest.getGender()).setKeywords(mediationAdRequest.getKeywords()).setLocation(mediationAdRequest.getLocation()).setNetworkExtras(adMobAdapterExtras2);
        if (mediationAdRequest.isTesting()) {
            networkExtras.addTestDevice(AdUtil.a((Context) activity));
        }
        return networkExtras;
    }

    protected AdView a(Activity activity, AdSize adSize, String str) {
        return new AdView(activity, adSize, str);
    }

    protected InterstitialAd a(Activity activity, String str) {
        return new InterstitialAd(activity, str);
    }

    @Override // com.google.ads.mediation.MediationAdapter
    public Class<AdMobAdapterExtras> getAdditionalParametersType() {
        return AdMobAdapterExtras.class;
    }

    @Override // com.google.ads.mediation.MediationAdapter
    public Class<AdMobAdapterServerParameters> getServerParametersType() {
        return AdMobAdapterServerParameters.class;
    }

    @Override // com.google.ads.mediation.MediationBannerAdapter
    public void requestBannerAd(MediationBannerListener mediationBannerListener, Activity activity, AdMobAdapterServerParameters adMobAdapterServerParameters, AdSize adSize, MediationAdRequest mediationAdRequest, AdMobAdapterExtras adMobAdapterExtras) {
        this.f700a = mediationBannerListener;
        if (!adSize.isAutoHeight() && !adSize.isFullWidth() && ((adMobAdapterExtras == null || !adMobAdapterExtras.getUseExactAdSize()) && (adSize = adSize.findBestSize(AdSize.BANNER, AdSize.IAB_BANNER, AdSize.IAB_LEADERBOARD, AdSize.IAB_MRECT, AdSize.IAB_WIDE_SKYSCRAPER)) == null)) {
            mediationBannerListener.onFailedToReceiveAd(this, AdRequest.ErrorCode.NO_FILL);
            return;
        }
        this.c = a(activity, adSize, adMobAdapterServerParameters.adUnitId);
        this.c.setAdListener(new a(this));
        this.c.loadAd(a(activity, adMobAdapterServerParameters, mediationAdRequest, adMobAdapterExtras));
    }

    @Override // com.google.ads.mediation.MediationBannerAdapter
    public View getBannerView() {
        return this.c;
    }

    @Override // com.google.ads.mediation.MediationAdapter
    public void destroy() {
        a();
        if (this.c != null) {
            this.c.stopLoading();
            this.c.destroy();
            this.c = null;
        }
        if (this.d != null) {
            this.d.stopLoading();
            this.d = null;
        }
    }

    @Override // com.google.ads.mediation.MediationInterstitialAdapter
    public void requestInterstitialAd(MediationInterstitialListener mediationInterstitialListener, Activity activity, AdMobAdapterServerParameters adMobAdapterServerParameters, MediationAdRequest mediationAdRequest, AdMobAdapterExtras adMobAdapterExtras) {
        this.b = mediationInterstitialListener;
        this.d = a(activity, adMobAdapterServerParameters.adUnitId);
        this.d.setAdListener(new b(this));
        this.d.loadAd(a(activity, adMobAdapterServerParameters, mediationAdRequest, adMobAdapterExtras));
    }

    @Override // com.google.ads.mediation.MediationInterstitialAdapter
    public void showInterstitial() {
        this.d.show();
    }
}

package com.google.ads.mediation.customevent;

import android.app.Activity;
import android.view.View;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.g;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class CustomEventAdapter implements MediationBannerAdapter<CustomEventExtras, CustomEventServerParameters>, MediationInterstitialAdapter<CustomEventExtras, CustomEventServerParameters> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f704a;
    private CustomEventBanner b = null;
    private a c = null;
    private CustomEventInterstitial d = null;

    @Override // com.google.ads.mediation.MediationBannerAdapter
    public void requestBannerAd(MediationBannerListener mediationBannerListener, Activity activity, CustomEventServerParameters customEventServerParameters, AdSize adSize, MediationAdRequest mediationAdRequest, CustomEventExtras customEventExtras) {
        com.google.ads.util.a.a((Object) this.f704a);
        this.f704a = customEventServerParameters.label;
        String str = customEventServerParameters.className;
        String str2 = customEventServerParameters.parameter;
        this.b = (CustomEventBanner) a(str, CustomEventBanner.class, this.f704a);
        if (this.b == null) {
            mediationBannerListener.onFailedToReceiveAd(this, AdRequest.ErrorCode.INTERNAL_ERROR);
            return;
        }
        com.google.ads.util.a.a(this.c);
        this.c = new a(this, mediationBannerListener);
        try {
            this.b.requestBannerAd(this.c, activity, this.f704a, str2, adSize, mediationAdRequest, customEventExtras == null ? null : customEventExtras.getExtra(this.f704a));
        } catch (Throwable th) {
            a("", th);
            mediationBannerListener.onFailedToReceiveAd(this, AdRequest.ErrorCode.INTERNAL_ERROR);
        }
    }

    @Override // com.google.ads.mediation.MediationBannerAdapter
    public View getBannerView() {
        com.google.ads.util.a.b(this.c);
        return this.c.a();
    }

    @Override // com.google.ads.mediation.MediationInterstitialAdapter
    public void showInterstitial() {
        com.google.ads.util.a.b(this.d);
        try {
            this.d.showInterstitial();
        } catch (Throwable th) {
            com.google.ads.util.b.b("Exception when showing custom event labeled '" + this.f704a + "'.", th);
        }
    }

    @Override // com.google.ads.mediation.MediationInterstitialAdapter
    public void requestInterstitialAd(MediationInterstitialListener mediationInterstitialListener, Activity activity, CustomEventServerParameters customEventServerParameters, MediationAdRequest mediationAdRequest, CustomEventExtras customEventExtras) {
        com.google.ads.util.a.a((Object) this.f704a);
        this.f704a = customEventServerParameters.label;
        String str = customEventServerParameters.className;
        String str2 = customEventServerParameters.parameter;
        this.d = (CustomEventInterstitial) a(str, CustomEventInterstitial.class, this.f704a);
        if (this.d == null) {
            mediationInterstitialListener.onFailedToReceiveAd(this, AdRequest.ErrorCode.INTERNAL_ERROR);
            return;
        }
        try {
            this.d.requestInterstitialAd(new b(this, mediationInterstitialListener), activity, this.f704a, str2, mediationAdRequest, customEventExtras == null ? null : customEventExtras.getExtra(this.f704a));
        } catch (Throwable th) {
            a("", th);
            mediationInterstitialListener.onFailedToReceiveAd(this, AdRequest.ErrorCode.INTERNAL_ERROR);
        }
    }

    @Override // com.google.ads.mediation.MediationAdapter
    public Class<CustomEventExtras> getAdditionalParametersType() {
        return CustomEventExtras.class;
    }

    @Override // com.google.ads.mediation.MediationAdapter
    public Class<CustomEventServerParameters> getServerParametersType() {
        return CustomEventServerParameters.class;
    }

    @Override // com.google.ads.mediation.MediationAdapter
    public void destroy() {
        if (this.b != null) {
            this.b.destroy();
        }
        if (this.d != null) {
            this.d.destroy();
        }
    }

    private void a(String str, Throwable th) {
        com.google.ads.util.b.b("Error during processing of custom event with label: '" + this.f704a + "'. Skipping custom event. " + str, th);
    }

    private <T> T a(String str, Class<T> cls, String str2) {
        try {
            return (T) g.a(str, cls);
        } catch (ClassCastException e) {
            a("Make sure your custom event implements the " + cls.getName() + " interface.", e);
            return null;
        } catch (ClassNotFoundException e2) {
            a("Make sure you created a visible class named: " + str + ". ", e2);
            return null;
        } catch (IllegalAccessException e3) {
            a("Make sure the default constructor for class " + str + " is visible. ", e3);
            return null;
        } catch (InstantiationException e4) {
            a("Make sure the name " + str + " does not denote an abstract class or an interface.", e4);
            return null;
        } catch (Throwable th) {
            a("", th);
            return null;
        }
    }
}

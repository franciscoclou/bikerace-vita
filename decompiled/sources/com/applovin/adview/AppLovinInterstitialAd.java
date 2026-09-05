package com.applovin.adview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AppLovinInterstitialAd extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b f161a;

    public AppLovinInterstitialAd(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f161a = null;
        if (context instanceof Activity) {
            com.applovin.a.k kVarB = com.applovin.a.k.b(context);
            if (kVarB != null && !kVarB.c()) {
                this.f161a = new com.applovin.impl.adview.b().a(kVarB, (Activity) context);
            }
        } else {
            Log.e("AppLovinSdk", "Unable to create AppLovin interstitial dialog. The view was not created from an activity.");
        }
        setVisibility(8);
    }

    public static b a(com.applovin.a.k kVar, Activity activity) {
        if (kVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        }
        return new com.applovin.impl.adview.b().a(kVar, activity);
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f161a != null) {
            this.f161a.a();
        }
    }
}

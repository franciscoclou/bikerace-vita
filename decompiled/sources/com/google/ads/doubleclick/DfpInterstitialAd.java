package com.google.ads.doubleclick;

import android.app.Activity;
import com.google.ads.AppEventListener;
import com.google.ads.InterstitialAd;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class DfpInterstitialAd extends InterstitialAd {
    public DfpInterstitialAd(Activity activity, String str) {
        super(activity, str);
    }

    public DfpInterstitialAd(Activity activity, String str, boolean z) {
        super(activity, str, z);
    }

    @Override // com.google.ads.InterstitialAd
    public void setAppEventListener(AppEventListener appEventListener) {
        super.setAppEventListener(appEventListener);
    }
}

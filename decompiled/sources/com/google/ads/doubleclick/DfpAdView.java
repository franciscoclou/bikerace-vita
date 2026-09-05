package com.google.ads.doubleclick;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.ads.AppEventListener;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class DfpAdView extends AdView {
    public DfpAdView(Activity activity, AdSize adSize, String str) {
        super(activity, adSize, str);
    }

    public DfpAdView(Activity activity, AdSize[] adSizeArr, String str) {
        super(activity, adSizeArr, str);
    }

    public DfpAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DfpAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.google.ads.AdView
    public void setAppEventListener(AppEventListener appEventListener) {
        super.setAppEventListener(appEventListener);
    }

    @Override // com.google.ads.AdView
    public void setSupportedAdSizes(AdSize... adSizeArr) {
        super.setSupportedAdSizes(adSizeArr);
    }

    public void enableManualImpressions(boolean z) {
        this.f615a.i().t.a(Boolean.valueOf(z));
    }

    public void recordImpression() {
        this.f615a.z();
    }

    public void resize(AdSize adSize) {
        this.f615a.l().setAdSize(adSize);
        this.f615a.i().g.a().b(adSize);
    }
}

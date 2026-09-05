package com.google.ads.doubleclick;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import com.google.ads.AdSize;
import com.google.ads.SwipeableAdListener;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class SwipeableDfpAdView extends DfpAdView {
    public SwipeableDfpAdView(Activity activity, AdSize adSize, String str) {
        super(activity, adSize, str);
    }

    public SwipeableDfpAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SwipeableDfpAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.google.ads.AdView
    public void setSwipeableEventListener(SwipeableAdListener swipeableAdListener) {
        super.setSwipeableEventListener(swipeableAdListener);
    }

    @Override // com.google.ads.doubleclick.DfpAdView
    public void resize(AdSize adSize) {
        super.resize(adSize);
        if (this.f615a.i().e.a().b()) {
            this.f615a.a(-1, -1, adSize.getWidth(), adSize.getHeight());
        }
    }
}

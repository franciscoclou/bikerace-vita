package com.topfreegames.bikerace.m;

import android.app.Activity;
import android.os.Build;
import android.view.ViewGroup;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.ap;
import com.topfreegames.bikerace.bb;

/* JADX INFO: compiled from: AdUtils.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {
    public static void a(Activity activity, ViewGroup viewGroup) {
        try {
            BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) activity.getApplicationContext();
            bb bbVarA = bikeRaceApplication.a(false);
            if (!Build.VERSION.RELEASE.startsWith("4") || (Build.VERSION.RELEASE.startsWith("4") && !((BikeRaceApplication) activity.getApplicationContext()).a(false).a())) {
                if (bikeRaceApplication.a().c() && bikeRaceApplication.e()) {
                    if (viewGroup.findViewWithTag("Ad") == null) {
                        String strTrim = activity.getString(2131099650).toLowerCase().trim();
                        AdSize adSize = AdSize.BANNER;
                        if ("BANNER".equals(strTrim)) {
                            adSize = AdSize.BANNER;
                        } else if ("IAB_LEADERBOARD".equals(strTrim)) {
                            adSize = AdSize.IAB_LEADERBOARD;
                        }
                        AdView adView = new AdView(activity, adSize, bbVarA.k());
                        viewGroup.addView(adView);
                        adView.setAdListener(new b(adView));
                        adView.setTag("Ad");
                        adView.loadAd(new AdRequest());
                        viewGroup.setVisibility(0);
                        adView.setVisibility(0);
                        return;
                    }
                    return;
                }
                viewGroup.setVisibility(8);
                return;
            }
            b(activity, viewGroup);
            viewGroup.setVisibility(8);
        } catch (Exception e) {
        }
    }

    public static void b(Activity activity, ViewGroup viewGroup) {
        try {
            try {
                AdView adView = (AdView) viewGroup.findViewWithTag("Ad");
                if (adView != null) {
                    try {
                        adView.stopLoading();
                    } catch (Exception e) {
                    }
                    viewGroup.removeAllViews();
                    viewGroup.removeAllViewsInLayout();
                    ViewGroup viewGroup2 = (ViewGroup) adView.getParent();
                    if (viewGroup2 != null) {
                        viewGroup2.removeAllViews();
                        viewGroup2.removeAllViewsInLayout();
                    }
                    try {
                        adView.destroy();
                    } catch (Exception e2) {
                    }
                }
            } catch (Error e3) {
                if (ap.d()) {
                    e3.printStackTrace();
                }
                ((BikeRaceApplication) activity.getApplicationContext()).d().a(a.class.getClass().getName(), "destroyAdView", e3);
                throw e3;
            }
        } catch (Exception e4) {
            if (ap.d()) {
                e4.printStackTrace();
            }
            ((BikeRaceApplication) activity.getApplicationContext()).d().a(a.class.getClass().getName(), "destroyAdView", e4);
        }
    }
}

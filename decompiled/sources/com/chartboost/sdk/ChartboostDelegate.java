package com.chartboost.sdk;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface ChartboostDelegate {
    void didCacheInterstitial(String str);

    void didCacheMoreApps();

    void didClickInterstitial(String str);

    void didClickMoreApps();

    void didCloseInterstitial(String str);

    void didCloseMoreApps();

    void didDismissInterstitial(String str);

    void didDismissMoreApps();

    void didFailToLoadInterstitial(String str);

    void didFailToLoadMoreApps();

    void didFailToLoadUrl(String str);

    void didShowInterstitial(String str);

    void didShowMoreApps();

    boolean shouldDisplayInterstitial(String str);

    boolean shouldDisplayLoadingViewForMoreApps();

    boolean shouldDisplayMoreApps();

    boolean shouldRequestInterstitial(String str);

    boolean shouldRequestInterstitialsInFirstSession();

    boolean shouldRequestMoreApps();
}

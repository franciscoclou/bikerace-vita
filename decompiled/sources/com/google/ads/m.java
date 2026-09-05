package com.google.ads;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class m extends com.google.ads.util.i {
    private static final m d = new m();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final com.google.ads.util.i.c<String> f697a = new com.google.ads.util.i.c<>("marketPackages", null);
    public final com.google.ads.util.i.b<a> b = new com.google.ads.util.i.b<>("constants", new a());
    public final com.google.ads.util.i.b<Handler> c = new com.google.ads.util.i.b<>("uiHandler", new Handler(Looper.getMainLooper()));

    public final class a extends com.google.ads.util.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final com.google.ads.util.i.c<String> f698a = new com.google.ads.util.i.c<>("ASDomains", null);
        public final com.google.ads.util.i.c<Integer> b = new com.google.ads.util.i.c<>("minHwAccelerationVersionBanner", 18);
        public final com.google.ads.util.i.c<Integer> c = new com.google.ads.util.i.c<>("minHwAccelerationVersionOverlay", 18);
        public final com.google.ads.util.i.c<Integer> d = new com.google.ads.util.i.c<>("minHwAccelerationVersionOverlay", 14);
        public final com.google.ads.util.i.c<String> e = new com.google.ads.util.i.c<>("mraidBannerPath", "http://media.admob.com/mraid/v1/mraid_app_banner.js");
        public final com.google.ads.util.i.c<String> f = new com.google.ads.util.i.c<>("mraidExpandedBannerPath", "http://media.admob.com/mraid/v1/mraid_app_expanded_banner.js");
        public final com.google.ads.util.i.c<String> g = new com.google.ads.util.i.c<>("mraidInterstitialPath", "http://media.admob.com/mraid/v1/mraid_app_interstitial.js");
        public final com.google.ads.util.i.c<String> h = new com.google.ads.util.i.c<>("badAdReportPath", "https://badad.googleplex.com/s/reportAd");
        public final com.google.ads.util.i.c<Long> i = new com.google.ads.util.i.c<>("appCacheMaxSize", 0L);
        public final com.google.ads.util.i.c<Long> j = new com.google.ads.util.i.c<>("appCacheMaxSizePaddingInBytes", 131072L);
        public final com.google.ads.util.i.c<Long> k = new com.google.ads.util.i.c<>("maxTotalAppCacheQuotaInBytes", 5242880L);
        public final com.google.ads.util.i.c<Long> l = new com.google.ads.util.i.c<>("maxTotalDatabaseQuotaInBytes", 5242880L);
        public final com.google.ads.util.i.c<Long> m = new com.google.ads.util.i.c<>("maxDatabaseQuotaPerOriginInBytes", 1048576L);
        public final com.google.ads.util.i.c<Long> n = new com.google.ads.util.i.c<>("databaseQuotaIncreaseStepInBytes", 131072L);
        public final com.google.ads.util.i.c<Boolean> o = new com.google.ads.util.i.c<>("isInitialized", false);
    }

    public static m a() {
        return d;
    }

    private m() {
    }
}

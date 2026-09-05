package com.c.a.a;

import android.app.Activity;
import android.util.Log;
import com.c.a.g;
import com.c.a.h;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.ChartboostDelegate;

/* JADX INFO: compiled from: ChartboostInterstitialProvider.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b implements h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f359a;
    private final String b;
    private g c;
    private Activity d;
    private Activity e;
    private boolean f = false;
    private ChartboostDelegate g = new ChartboostDelegate() { // from class: com.c.a.a.b.1
        @Override // com.chartboost.sdk.ChartboostDelegate
        public boolean shouldRequestMoreApps() {
            return false;
        }

        @Override // com.chartboost.sdk.ChartboostDelegate
        public boolean shouldRequestInterstitialsInFirstSession() {
            return true;
        }

        @Override // com.chartboost.sdk.ChartboostDelegate
        public boolean shouldRequestInterstitial(String str) {
            return true;
        }

        @Override // com.chartboost.sdk.ChartboostDelegate
        public boolean shouldDisplayMoreApps() {
            return false;
        }

        @Override // com.chartboost.sdk.ChartboostDelegate
        public boolean shouldDisplayLoadingViewForMoreApps() {
            return false;
        }

        @Override // com.chartboost.sdk.ChartboostDelegate
        public boolean shouldDisplayInterstitial(String str) {
            return true;
        }

        @Override // com.chartboost.sdk.ChartboostDelegate
        public void didShowMoreApps() {
        }

        @Override // com.chartboost.sdk.ChartboostDelegate
        public void didShowInterstitial(String str) {
            Log.d("Chartboost", "didShowInterstitial");
            if (b.this.c != null) {
                b.this.c.a(str);
            }
        }

        @Override // com.chartboost.sdk.ChartboostDelegate
        public void didFailToLoadUrl(String str) {
            Log.d("Chartboost", "didFailToLoadUrl");
        }

        @Override // com.chartboost.sdk.ChartboostDelegate
        public void didFailToLoadMoreApps() {
            Log.d("Chartboost", "didFailToLoadMoreApps");
        }

        @Override // com.chartboost.sdk.ChartboostDelegate
        public void didFailToLoadInterstitial(String str) {
            Log.d("Chartboost", "didFailToLoadInterstitial");
        }

        @Override // com.chartboost.sdk.ChartboostDelegate
        public void didDismissMoreApps() {
        }

        @Override // com.chartboost.sdk.ChartboostDelegate
        public void didDismissInterstitial(String str) {
            Log.d("Chartboost", "didDismissInterstitial");
            if (b.this.c != null) {
                b.this.c.c(str);
            }
        }

        @Override // com.chartboost.sdk.ChartboostDelegate
        public void didCloseMoreApps() {
        }

        @Override // com.chartboost.sdk.ChartboostDelegate
        public void didCloseInterstitial(String str) {
            Log.d("Chartboost", "didCloseInterstitial");
            if (b.this.c != null) {
                b.this.c.c(str);
            }
        }

        @Override // com.chartboost.sdk.ChartboostDelegate
        public void didClickMoreApps() {
        }

        @Override // com.chartboost.sdk.ChartboostDelegate
        public void didClickInterstitial(String str) {
            Log.d("Chartboost", "didClickInterstitial");
            if (b.this.c != null) {
                b.this.c.d(str);
            }
        }

        @Override // com.chartboost.sdk.ChartboostDelegate
        public void didCacheMoreApps() {
        }

        @Override // com.chartboost.sdk.ChartboostDelegate
        public void didCacheInterstitial(String str) {
            Log.d("Chartboost", "didCacheInterstitial");
        }
    };

    public b(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("Id cannot be null!");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("Signature cannot be null!");
        }
        this.f359a = str;
        this.b = str2;
    }

    @Override // com.c.a.h
    public void a(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity cannot be null!");
        }
        this.e = this.d;
        this.d = activity;
        Chartboost chartboostSharedChartboost = Chartboost.sharedChartboost();
        chartboostSharedChartboost.onCreate(activity, this.f359a, this.b, this.g);
        if (!this.f) {
            chartboostSharedChartboost.startSession();
            this.f = true;
        }
        chartboostSharedChartboost.setAnimationsOff(false);
        chartboostSharedChartboost.setImpressionsUseActivities(true);
        Log.d("Chartboost", "onActivityCreate (" + activity.getClass().getSimpleName() + ")");
    }

    @Override // com.c.a.h
    public void a(String str) {
        try {
            Log.d("Chartboost", "show");
            Chartboost.sharedChartboost().showInterstitial(str);
        } catch (Exception e) {
            Log.d("Chartboost", "show", e);
        }
    }

    @Override // com.c.a.h
    public void b(String str) {
        try {
            Log.d("Chartboost", "fetch");
            Chartboost.sharedChartboost().cacheInterstitial(str);
        } catch (Exception e) {
            Log.d("Chartboost", "fetch", e);
        }
    }

    @Override // com.c.a.h
    public boolean c(String str) {
        try {
            Log.d("Chartboost", "isAvailable: " + Chartboost.sharedChartboost().hasCachedInterstitial(str));
            return Chartboost.sharedChartboost().hasCachedInterstitial(str);
        } catch (Exception e) {
            Log.d("Chartboost", "show", e);
            return false;
        }
    }

    @Override // com.c.a.h
    public void a(g gVar) {
        this.c = gVar;
    }

    @Override // com.c.a.h
    public String a() {
        return "Chartboost";
    }

    @Override // com.c.a.h
    public void b() {
        try {
            Log.d("Chartboost", "onActivityStart (" + this.d.getClass().getSimpleName() + ")");
            Chartboost.sharedChartboost().onStart(this.d);
        } catch (Exception e) {
            Log.d("Chartboost", "onActivityStart", e);
        }
    }

    @Override // com.c.a.h
    public void c() {
        try {
            Activity activity = this.e == null ? this.d : this.e;
            Log.d("Chartboost", "onActivityStop (" + activity.getClass().getSimpleName() + ")");
            if (activity != null) {
                Chartboost.sharedChartboost().onStop(activity);
            }
        } catch (Exception e) {
            Log.d("Chartboost", "onActivityStop", e);
        }
    }

    @Override // com.c.a.h
    public void d() {
        try {
            Activity activity = this.e == null ? this.d : this.e;
            Log.d("Chartboost", "onActivityDestroy (" + activity.getClass().getSimpleName() + ")");
            if (activity != null) {
                Chartboost.sharedChartboost().onDestroy(activity);
                this.e = null;
            }
        } catch (Exception e) {
            Log.d("Chartboost", "onActivityDestroy", e);
        }
    }

    @Override // com.c.a.h
    public boolean e() {
        try {
            return !Chartboost.sharedChartboost().onBackPressed();
        } catch (Exception e) {
            Log.d("Chartboost", "onActivityBackPressed", e);
            return true;
        }
    }
}

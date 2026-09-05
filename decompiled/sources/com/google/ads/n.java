package com.google.ads;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import com.google.ads.internal.ActivationOverlay;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class n extends com.google.ads.util.i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final com.google.ads.util.i.b<Ad> f708a;
    public final com.google.ads.util.i.b<com.google.ads.internal.d> b;
    public final com.google.ads.util.i.d<Activity> c;
    public final com.google.ads.util.i.b<m> d;
    public final com.google.ads.util.i.b<ActivationOverlay> e;
    public final com.google.ads.util.i.b<Context> f;
    public final com.google.ads.util.i.b<com.google.ads.internal.h> g;
    public final com.google.ads.util.i.b<String> h;
    public final com.google.ads.util.i.b<ViewGroup> i;
    public final com.google.ads.util.i.b<AdView> j;
    public final com.google.ads.util.i.b<InterstitialAd> k;
    public final com.google.ads.util.i.c<l> l = new com.google.ads.util.i.c<>("currentAd", null);
    public final com.google.ads.util.i.c<l> m = new com.google.ads.util.i.c<>("nextAd", null);
    public final com.google.ads.util.i.c<AdListener> o = new com.google.ads.util.i.c<>("adListener");
    public final com.google.ads.util.i.c<AppEventListener> p = new com.google.ads.util.i.c<>("appEventListener");
    public final com.google.ads.util.i.c<SwipeableAdListener> q = new com.google.ads.util.i.c<>("swipeableEventListener");
    public final com.google.ads.util.i.c<ak> r = new com.google.ads.util.i.c<>("spamSignals", null);
    public final com.google.ads.util.i.c<al> s = new com.google.ads.util.i.c<>("spamSignalsUtil", null);
    public final com.google.ads.util.i.c<Boolean> t = new com.google.ads.util.i.c<>("usesManualImpressions", false);
    public final com.google.ads.util.i.c<AdSize[]> n = new com.google.ads.util.i.c<>("adSizes", null);

    public boolean a() {
        return !b();
    }

    public boolean b() {
        return this.g.a().a();
    }

    public n(m mVar, Ad ad, AdView adView, InterstitialAd interstitialAd, String str, Activity activity, Context context, ViewGroup viewGroup, com.google.ads.internal.h hVar, com.google.ads.internal.d dVar) {
        ActivationOverlay activationOverlay = null;
        this.d = new com.google.ads.util.i.b<>("appState", mVar);
        this.f708a = new com.google.ads.util.i.b<>("ad", ad);
        this.j = new com.google.ads.util.i.b<>("adView", adView);
        this.g = new com.google.ads.util.i.b<>("adType", hVar);
        this.h = new com.google.ads.util.i.b<>("adUnitId", str);
        this.c = new com.google.ads.util.i.d<>("activity", activity);
        this.k = new com.google.ads.util.i.b<>("interstitialAd", interstitialAd);
        this.i = new com.google.ads.util.i.b<>("bannerContainer", viewGroup);
        this.f = new com.google.ads.util.i.b<>("applicationContext", context);
        this.b = new com.google.ads.util.i.b<>("adManager", dVar);
        if (hVar != null && hVar.b()) {
            activationOverlay = new ActivationOverlay(this);
        }
        this.e = new com.google.ads.util.i.b<>("activationOverlay", activationOverlay);
    }
}

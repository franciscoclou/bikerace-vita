package com.heyzap.sdk.ads;

import android.app.Activity;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: InterstitialAd.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    long f791a;
    WeakReference<Activity> b;
    String c;

    public void a() {
        com.heyzap.internal.k.a("would like to display", Long.valueOf(this.f791a));
        Activity activity = this.b.get();
        if (activity != null && System.currentTimeMillis() < this.f791a) {
            p.a(activity, this.c);
        }
    }
}

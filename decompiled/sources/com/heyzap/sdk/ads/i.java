package com.heyzap.sdk.ads;

import android.app.Activity;
import android.content.Context;

/* JADX INFO: compiled from: HeyzapAds.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class i {
    private static boolean d = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f785a = null;
    public static String b = null;
    public static String c = "google";

    public static void a(Activity activity, int i, k kVar) {
        if (!a().booleanValue()) {
            com.heyzap.internal.k.a((Context) activity);
            u.f799a = activity.getApplicationContext();
            a(kVar);
            u.d();
            if (i > 0) {
                u.d().a(i);
            }
            d = true;
            com.heyzap.internal.c.a(activity, "heyzap-start");
            if ((i & 4) == 4 || com.heyzap.internal.l.a()) {
                c = "amazon";
            }
        }
    }

    public static void a(Activity activity) {
        a(activity, 0, null);
    }

    public static Boolean a() {
        return Boolean.valueOf(d && u.a().booleanValue());
    }

    public static void a(k kVar) {
        u.h = kVar;
    }
}

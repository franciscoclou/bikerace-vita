package com.flurry.sdk;

import android.text.TextUtils;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class fa {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f560a = fa.class.getSimpleName();

    public static fc a(String str) {
        fc fcVar;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            fcVar = (fc) Class.forName(str).getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            ex.a(5, f560a, "FlurryModule " + str + " is not available:", e);
            fcVar = null;
        }
        return fcVar;
    }
}

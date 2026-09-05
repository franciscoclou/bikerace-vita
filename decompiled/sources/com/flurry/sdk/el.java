package com.flurry.sdk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class el {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f537a = el.class.getSimpleName();

    public static PackageInfo a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 20815);
        } catch (PackageManager.NameNotFoundException e) {
            ex.a(f537a, "Cannot find package info for package: " + context.getPackageName());
            return null;
        }
    }

    public static ApplicationInfo b(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), XMLChar.MASK_NCNAME);
        } catch (PackageManager.NameNotFoundException e) {
            ex.a(f537a, "Cannot find application info for package: " + context.getPackageName());
            return null;
        }
    }

    public static String c(Context context) {
        PackageInfo packageInfoA = a(context);
        return (packageInfoA == null || packageInfoA.packageName == null) ? "" : packageInfoA.packageName;
    }

    public static String d(Context context) {
        PackageInfo packageInfoA = a(context);
        return (packageInfoA == null || packageInfoA.versionName == null) ? "" : packageInfoA.versionName;
    }

    public static Bundle e(Context context) {
        ApplicationInfo applicationInfoB = b(context);
        return (applicationInfoB == null || applicationInfoB.metaData == null) ? Bundle.EMPTY : applicationInfoB.metaData;
    }
}

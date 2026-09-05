package com.flurry.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ep {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f542a = ep.class.getSimpleName();
    private static String b;
    private static String c;

    public static void a(String str) {
        b = str;
    }

    public static String a() {
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        if (!TextUtils.isEmpty(c)) {
            return c;
        }
        c = b();
        return c;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x001b, code lost:
    
        r0 = com.facebook.internal.AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String b() {
        String string;
        try {
            Context contextB = eg.a().b();
            PackageInfo packageInfo = contextB.getPackageManager().getPackageInfo(contextB.getPackageName(), 0);
            if (packageInfo.versionName != null) {
                string = packageInfo.versionName;
            } else {
                string = packageInfo.versionCode != 0 ? Integer.toString(packageInfo.versionCode) : AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            }
        } catch (Throwable th) {
            ex.a(6, f542a, "", th);
        }
        return string;
    }
}

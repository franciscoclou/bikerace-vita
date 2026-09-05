package com.applovin.a;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class m {
    public static String a(Context context) {
        Bundle bundleF = f(context);
        if (bundleF == null) {
            return null;
        }
        String string = bundleF.getString("applovin.sdk.key");
        return string != null ? string : "";
    }

    public static l b(Context context) {
        l lVar = new l();
        lVar.a(c(context));
        lVar.a(d(context));
        lVar.a(e(context));
        return lVar;
    }

    public static boolean c(Context context) {
        Bundle bundleF = f(context);
        if (bundleF != null) {
            return bundleF.getBoolean("applovin.sdk.verbose_logging", false);
        }
        return false;
    }

    public static long d(Context context) {
        Bundle bundleF = f(context);
        if (bundleF != null) {
            return bundleF.getInt("applovin.sdk.ad_refresh_seconds", -100);
        }
        return -100L;
    }

    public static String e(Context context) {
        String string;
        Bundle bundleF = f(context);
        return (bundleF == null || (string = bundleF.getString("applovin.sdk.auto_preload_ad_sizes")) == null) ? "BANNER,INTER" : string;
    }

    protected static Bundle f(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), XMLChar.MASK_NCNAME).metaData;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("AppLovinSdk", "Unable to retrieve application metadata", e);
            return null;
        }
    }
}

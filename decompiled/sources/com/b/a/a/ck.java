package com.b.a.a;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ck implements cj {
    private static boolean b(int i) {
        return cp.f297a.h() <= i;
    }

    public static String a(Context context, boolean z) {
        String string;
        int iA;
        try {
            Context applicationContext = context.getApplicationContext();
            Bundle bundle = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), XMLChar.MASK_NCNAME).metaData;
            string = bundle != null ? bundle.getString("com.crashlytics.ApiKey") : null;
        } catch (Exception e) {
            cp.f297a.b().a("Crashlytics", "Caught non-fatal exception while retrieving apiKey: " + e);
        }
        if (ba.e(string) && (iA = ba.a(context, "com.crashlytics.ApiKey", "string")) != 0) {
            string = context.getResources().getString(iA);
        }
        if (ba.e(string)) {
            if (z || ba.f(context)) {
                throw new IllegalArgumentException("Crashlytics could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"com.crashlytics.ApiKey\" android:value=\"YOUR_API_KEY\"/>");
            }
            cp.f297a.b().a("Crashlytics", "Crashlytics could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"com.crashlytics.ApiKey\" android:value=\"YOUR_API_KEY\"/>", (Throwable) null);
        }
        return string;
    }

    public static int a(int i) {
        if (i >= 200 && i <= 299) {
            return 0;
        }
        if (i >= 300 && i <= 399) {
            return 1;
        }
        if (i < 400 || i > 499) {
            return i >= 500 ? 1 : 1;
        }
        return 0;
    }

    @Override // com.b.a.a.cj
    public final void a(String str, String str2, Throwable th) {
        if (b(6)) {
            Log.e(str, str2, th);
        }
    }

    @Override // com.b.a.a.cj
    public final void a(String str, String str2) {
        if (b(3)) {
            Log.d(str, str2, null);
        }
    }

    @Override // com.b.a.a.cj
    public final void b(String str, String str2) {
        if (b(4)) {
            Log.i(str, str2, null);
        }
    }

    @Override // com.b.a.a.cj
    public final void c(String str, String str2) {
        if (b(5)) {
            Log.w(str, str2, null);
        }
    }

    @Override // com.b.a.a.cj
    public final void d(String str, String str2) {
        a(str, str2, (Throwable) null);
    }

    @Override // com.b.a.a.cj
    public final void a(int i, String str, String str2) {
        a(i, str, str2, false);
    }

    public final void a(int i, String str, String str2, boolean z) {
        if (z || b(i)) {
            Log.println(i, str, str2);
        }
    }

    static /* synthetic */ Activity a(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        return null;
    }

    static /* synthetic */ Application b(Context context) {
        if (context instanceof Application) {
            return (Application) context;
        }
        if (context instanceof Activity) {
            return ((Activity) context).getApplication();
        }
        if (context instanceof Service) {
            return ((Service) context).getApplication();
        }
        if (context.getApplicationContext() instanceof Application) {
            return (Application) context.getApplicationContext();
        }
        return null;
    }
}

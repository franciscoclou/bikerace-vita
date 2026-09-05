package com.flurry.android;

import android.content.Context;
import android.location.Criteria;
import android.os.Build;
import com.flurry.sdk.bx;
import com.flurry.sdk.eg;
import com.flurry.sdk.eh;
import com.flurry.sdk.ex;
import com.flurry.sdk.ey;
import com.flurry.sdk.fh;
import java.util.Date;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class FlurryAgent {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f501a = FlurryAgent.class.getSimpleName();

    private FlurryAgent() {
    }

    public static void setVersionName(String str) {
        if (Build.VERSION.SDK_INT < 10) {
            ex.b(f501a, "Device SDK Version older than 10");
        } else if (str == null) {
            ex.b(f501a, "String versionName passed to setVersionName was null.");
        } else {
            eh.a().a("VesionName", (Object) str);
        }
    }

    public static int getAgentVersion() {
        return bx.a().b();
    }

    public static String getReleaseVersion() {
        return bx.a().g();
    }

    public static void setReportLocation(boolean z) {
        if (Build.VERSION.SDK_INT < 10) {
            ex.b(f501a, "Device SDK Version older than 10");
        } else {
            eh.a().a("ReportLocation", (Object) Boolean.valueOf(z));
        }
    }

    public static void setLogEnabled(boolean z) {
        if (Build.VERSION.SDK_INT < 10) {
            ex.b(f501a, "Device SDK Version older than 10");
        } else if (z) {
            ex.b();
        } else {
            ex.a();
        }
    }

    public static void setLogLevel(int i) {
        if (Build.VERSION.SDK_INT < 10) {
            ex.b(f501a, "Device SDK Version older than 10");
        } else {
            ex.a(i);
        }
    }

    public static void setContinueSessionMillis(long j) {
        if (Build.VERSION.SDK_INT < 10) {
            ex.b(f501a, "Device SDK Version older than 10");
        } else if (j < 5000) {
            ex.b(f501a, "Invalid time set for session resumption: " + j);
        } else {
            eh.a().a("ContinueSessionMillis", (Object) Long.valueOf(j));
        }
    }

    public static void setLogEvents(boolean z) {
        if (Build.VERSION.SDK_INT < 10) {
            ex.b(f501a, "Device SDK Version older than 10");
        } else {
            eh.a().a("LogEvents", (Object) Boolean.valueOf(z));
        }
    }

    public static void setUseHttps(boolean z) {
        if (Build.VERSION.SDK_INT < 10) {
            ex.b(f501a, "Device SDK Version older than 10");
        } else {
            eh.a().a("UseHttps", (Object) Boolean.valueOf(z));
        }
    }

    public static boolean getUseHttps() {
        return ((Boolean) eh.a().a("UseHttps")).booleanValue();
    }

    public static void setCaptureUncaughtExceptions(boolean z) {
        if (Build.VERSION.SDK_INT < 10) {
            ex.b(f501a, "Device SDK Version older than 10");
        } else {
            eh.a().a("CaptureUncaughtExceptions", (Object) Boolean.valueOf(z));
        }
    }

    public static void onStartSession(Context context, String str) {
        if (Build.VERSION.SDK_INT < 10) {
            ex.b(f501a, "Device SDK Version older than 10");
            return;
        }
        if (context == null) {
            throw new NullPointerException("Null context");
        }
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Api key not specified");
        }
        eg.a(context);
        try {
            bx.a().a(context, str);
        } catch (Throwable th) {
            ex.b(f501a, "", th);
        }
        ey.a().f(context);
    }

    public static void onEndSession(Context context) {
        if (Build.VERSION.SDK_INT < 10) {
            ex.b(f501a, "Device SDK Version older than 10");
            return;
        }
        if (context == null) {
            throw new NullPointerException("Null context");
        }
        ey.a().g(context);
        try {
            bx.a().a(context);
        } catch (Throwable th) {
            ex.b(f501a, "", th);
        }
    }

    public static void logEvent(String str) {
        if (Build.VERSION.SDK_INT < 10) {
            ex.b(f501a, "Device SDK Version older than 10");
            return;
        }
        if (str == null) {
            ex.b(f501a, "String eventId passed to logEvent was null.");
            return;
        }
        try {
            bx.a().a(str);
        } catch (Throwable th) {
            ex.b(f501a, "Failed to log event: " + str, th);
        }
    }

    public static void logEvent(String str, Map<String, String> map) {
        if (Build.VERSION.SDK_INT < 10) {
            ex.b(f501a, "Device SDK Version older than 10");
            return;
        }
        if (str == null) {
            ex.b(f501a, "String eventId passed to logEvent was null.");
            return;
        }
        if (map == null) {
            ex.b(f501a, "String parameters passed to logEvent was null.");
            return;
        }
        try {
            bx.a().a(str, map);
        } catch (Throwable th) {
            ex.b(f501a, "Failed to log event: " + str, th);
        }
    }

    public static void logEvent(String str, boolean z) {
        if (Build.VERSION.SDK_INT < 10) {
            ex.b(f501a, "Device SDK Version older than 10");
            return;
        }
        if (str == null) {
            ex.b(f501a, "String eventId passed to logEvent was null.");
            return;
        }
        try {
            bx.a().a(str, z);
        } catch (Throwable th) {
            ex.b(f501a, "Failed to log event: " + str, th);
        }
    }

    public static void logEvent(String str, Map<String, String> map, boolean z) {
        if (Build.VERSION.SDK_INT < 10) {
            ex.b(f501a, "Device SDK Version older than 10");
            return;
        }
        if (str == null) {
            ex.b(f501a, "String eventId passed to logEvent was null.");
            return;
        }
        if (map == null) {
            ex.b(f501a, "String parameters passed to logEvent was null.");
            return;
        }
        try {
            bx.a().a(str, map, z);
        } catch (Throwable th) {
            ex.b(f501a, "Failed to log event: " + str, th);
        }
    }

    public static void endTimedEvent(String str) {
        if (Build.VERSION.SDK_INT < 10) {
            ex.b(f501a, "Device SDK Version older than 10");
            return;
        }
        if (str == null) {
            ex.b(f501a, "String eventId passed to endTimedEvent was null.");
            return;
        }
        try {
            bx.a().b(str);
        } catch (Throwable th) {
            ex.b(f501a, "Failed to signify the end of event: " + str, th);
        }
    }

    public static void endTimedEvent(String str, Map<String, String> map) {
        if (Build.VERSION.SDK_INT < 10) {
            ex.b(f501a, "Device SDK Version older than 10");
            return;
        }
        if (str == null) {
            ex.b(f501a, "String eventId passed to endTimedEvent was null.");
            return;
        }
        if (map == null) {
            ex.b(f501a, "String eventId passed to endTimedEvent was null.");
            return;
        }
        try {
            bx.a().b(str, map);
        } catch (Throwable th) {
            ex.b(f501a, "Failed to signify the end of event: " + str, th);
        }
    }

    @Deprecated
    public static void onError(String str, String str2, String str3) {
        if (Build.VERSION.SDK_INT < 10) {
            ex.b(f501a, "Device SDK Version older than 10");
            return;
        }
        if (str == null) {
            ex.b(f501a, "String errorId passed to onError was null.");
            return;
        }
        if (str2 == null) {
            ex.b(f501a, "String message passed to onError was null.");
            return;
        }
        if (str3 == null) {
            ex.b(f501a, "String errorClass passed to onError was null.");
            return;
        }
        try {
            bx.a().a(str, str2, str3);
        } catch (Throwable th) {
            ex.b(f501a, "", th);
        }
    }

    public static void onError(String str, String str2, Throwable th) {
        if (Build.VERSION.SDK_INT < 10) {
            ex.b(f501a, "Device SDK Version older than 10");
            return;
        }
        if (str == null) {
            ex.b(f501a, "String errorId passed to onError was null.");
            return;
        }
        if (str2 == null) {
            ex.b(f501a, "String message passed to onError was null.");
            return;
        }
        if (th == null) {
            ex.b(f501a, "Throwable passed to onError was null.");
            return;
        }
        try {
            bx.a().a(str, str2, th);
        } catch (Throwable th2) {
            ex.b(f501a, "", th2);
        }
    }

    @Deprecated
    public static void onEvent(String str) {
        if (Build.VERSION.SDK_INT < 10) {
            ex.b(f501a, "Device SDK Version older than 10");
            return;
        }
        if (str == null) {
            ex.b(f501a, "String eventId passed to onEvent was null.");
            return;
        }
        try {
            bx.a().c(str);
        } catch (Throwable th) {
            ex.b(f501a, "", th);
        }
    }

    @Deprecated
    public static void onEvent(String str, Map<String, String> map) {
        if (Build.VERSION.SDK_INT < 10) {
            ex.b(f501a, "Device SDK Version older than 10");
            return;
        }
        if (str == null) {
            ex.b(f501a, "String eventId passed to onEvent was null.");
            return;
        }
        if (map == null) {
            ex.b(f501a, "Parameters Map passed to onEvent was null.");
            return;
        }
        try {
            bx.a().c(str, map);
        } catch (Throwable th) {
            ex.b(f501a, "", th);
        }
    }

    public static void onPageView() {
        if (Build.VERSION.SDK_INT < 10) {
            ex.b(f501a, "Device SDK Version older than 10");
            return;
        }
        try {
            bx.a().i();
        } catch (Throwable th) {
            ex.b(f501a, "", th);
        }
    }

    public static void setReportUrl(String str) {
        if (Build.VERSION.SDK_INT < 10) {
            ex.b(f501a, "Device SDK Version older than 10");
        } else {
            eh.a().a("ReportUrl", (Object) str);
        }
    }

    public static void setLocationCriteria(Criteria criteria) {
        if (Build.VERSION.SDK_INT < 10) {
            ex.b(f501a, "Device SDK Version older than 10");
        } else {
            eh.a().a("LocationCriteria", (Object) criteria);
        }
    }

    public static void setAge(int i) {
        if (Build.VERSION.SDK_INT < 10) {
            ex.b(f501a, "Device SDK Version older than 10");
        } else if (i > 0 && i < 110) {
            eh.a().a("Age", (Object) Long.valueOf(new Date(new Date(System.currentTimeMillis() - (((long) i) * 31449600000L)).getYear(), 1, 1).getTime()));
        }
    }

    public static void setGender(byte b) {
        if (Build.VERSION.SDK_INT < 10) {
            ex.b(f501a, "Device SDK Version older than 10");
        }
        switch (b) {
            case 0:
            case 1:
                eh.a().a("Gender", (Object) Byte.valueOf(b));
                break;
            default:
                eh.a().a("Gender", (Object) (byte) -1);
                break;
        }
    }

    public static void setUserId(String str) {
        if (Build.VERSION.SDK_INT < 10) {
            ex.b(f501a, "Device SDK Version older than 10");
        } else if (str == null) {
            ex.b(f501a, "String userId passed to setUserId was null.");
        } else {
            eh.a().a("UserId", (Object) fh.a(str));
        }
    }

    static void setInternalLoggingEnabled(boolean z) {
        bx.a().a(z);
    }
}

package com.heyzap.internal;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import com.facebook.internal.NativeProtocol;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: compiled from: Utils.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class l {
    private static HashMap<String, String> e;
    private static float b = -1.0f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static ExecutorService f761a = Executors.newSingleThreadExecutor();
    private static String c = "unknown";
    private static String d = "unknown";

    public static void a(Context context) {
        c(context);
    }

    public static String b(Context context) {
        if (d.equals("unknown") && context != null) {
            d = context.getPackageName();
        }
        return d;
    }

    public static String c(Context context) {
        if (c.equals("unknown") && context != null) {
            String str = Build.PRODUCT;
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (str != null && string != null) {
                c = str + "_" + string;
            }
        }
        return c;
    }

    static HashMap<String, String> d(Context context) {
        String str;
        if (e == null) {
            context.getResources().getDisplayMetrics();
            e = new HashMap<>();
            e.put("sdk_version", "6.3.4");
            e.put("android_version", Build.VERSION.SDK);
            e.put("game_package", b(context));
            e.put("device_id", c(context));
            e.put("device_model", Build.MODEL);
            e.put("device_type", Build.DEVICE);
            if (g(context)) {
                str = "tablet";
            } else {
                str = "phone";
            }
            e.put("device_form_factor", str);
        }
        if (a()) {
            e.put("sdk_platform", "amazon");
        } else {
            e.put("sdk_platform", "android");
        }
        return e;
    }

    public static boolean a() {
        return Build.MANUFACTURER.equals("Amazon") || (com.heyzap.sdk.ads.i.c != null && com.heyzap.sdk.ads.i.c.equals("amazon"));
    }

    public static int a(Context context, int i) {
        b = b > 0.0f ? b : context.getResources().getDisplayMetrics().density;
        return (int) ((i * b) + 0.5f);
    }

    public static int b(Context context, int i) {
        return a(context, i);
    }

    public static int a(Context context, float f) {
        if (b <= 0.0f) {
            b = context.getResources().getDisplayMetrics().density;
        }
        return (int) (b * f);
    }

    public static boolean e(Context context) {
        return a("com.heyzap.android", context);
    }

    public static boolean a(String str, Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(str);
            if (launchIntentForPackage == null || packageManager.queryIntentActivities(launchIntentForPackage, NativeProtocol.MESSAGE_GET_ACCESS_TOKEN_REQUEST).size() <= 0) {
                return false;
            }
            return true;
        } catch (Exception e2) {
            return false;
        }
    }

    public static void a(Context context, String str) {
        String str2 = String.format("market://details?id=%s&referrer=%s", "com.heyzap.android", c.b(context, str));
        Log.d("HeyzapSDK", "Sending player to market, uri: " + str2);
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str2));
        intent.addFlags(402653184);
        context.startActivity(intent);
    }

    public static boolean f(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        String packageName = context.getApplicationContext().getPackageName();
        try {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            for (int i = 0; i < runningAppProcesses.size(); i++) {
                ActivityManager.RunningAppProcessInfo runningAppProcessInfo = runningAppProcesses.get(i);
                if (runningAppProcessInfo.processName.equals(packageName) && runningAppProcessInfo.importance == 100) {
                    return true;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return false;
    }

    public static int b() {
        return Build.VERSION.SDK_INT;
    }

    public static boolean g(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    public static void a(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                a(file2);
            }
        }
        file.delete();
    }
}

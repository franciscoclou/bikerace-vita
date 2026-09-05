package com.google.android.a;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;

/* JADX INFO: compiled from: GCMRegistrar.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static b f732a;

    public static void a(Context context, String... strArr) {
        i(context);
        g(context);
        b(context, strArr);
    }

    static void b(Context context, String... strArr) {
        if (strArr == null || strArr.length == 0) {
            throw new IllegalArgumentException("No senderIds");
        }
        StringBuilder sb = new StringBuilder(strArr[0]);
        for (int i = 1; i < strArr.length; i++) {
            sb.append(',').append(strArr[i]);
        }
        String string = sb.toString();
        Log.v("GCMRegistrar", "Registering app " + context.getPackageName() + " of senders " + string);
        Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
        intent.setPackage("com.google.android.gsf");
        intent.putExtra("app", PendingIntent.getBroadcast(context, 0, new Intent(), 0));
        intent.putExtra("sender", string);
        context.startService(intent);
    }

    public static void a(Context context) {
        i(context);
        g(context);
        b(context);
    }

    static void b(Context context) {
        Log.v("GCMRegistrar", "Unregistering app " + context.getPackageName());
        Intent intent = new Intent("com.google.android.c2dm.intent.UNREGISTER");
        intent.setPackage("com.google.android.gsf");
        intent.putExtra("app", PendingIntent.getBroadcast(context, 0, new Intent(), 0));
        context.startService(intent);
    }

    private static synchronized void i(Context context) {
        if (f732a == null) {
            f732a = new b();
            String packageName = context.getPackageName();
            IntentFilter intentFilter = new IntentFilter("com.google.android.gcm.intent.RETRY");
            intentFilter.addCategory(packageName);
            Log.v("GCMRegistrar", "Registering receiver");
            context.registerReceiver(f732a, intentFilter, packageName + ".permission.C2D_MESSAGE", null);
        }
    }

    public static String c(Context context) {
        SharedPreferences sharedPreferencesK = k(context);
        String string = sharedPreferencesK.getString("regId", "");
        int i = sharedPreferencesK.getInt("appVersion", Integer.MIN_VALUE);
        int iJ = j(context);
        if (i != Integer.MIN_VALUE && i != iJ) {
            Log.v("GCMRegistrar", "App version changed from " + i + " to " + iJ + "; resetting registration id");
            e(context);
            return "";
        }
        return string;
    }

    public static boolean d(Context context) {
        return c(context).length() > 0;
    }

    static String e(Context context) {
        return a(context, "");
    }

    static String a(Context context, String str) {
        SharedPreferences sharedPreferencesK = k(context);
        String string = sharedPreferencesK.getString("regId", "");
        int iJ = j(context);
        Log.v("GCMRegistrar", "Saving regId on app version " + iJ);
        SharedPreferences.Editor editorEdit = sharedPreferencesK.edit();
        editorEdit.putString("regId", str);
        editorEdit.putInt("appVersion", iJ);
        editorEdit.commit();
        return string;
    }

    public static boolean f(Context context) {
        boolean z = k(context).getBoolean("onServer", false);
        Log.v("GCMRegistrar", "Is registered on server: " + z);
        return z;
    }

    private static int j(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Coult not get package name: " + e);
        }
    }

    static void g(Context context) {
        Log.d("GCMRegistrar", "resetting backoff for " + context.getPackageName());
        a(context, 3000);
    }

    static int h(Context context) {
        return k(context).getInt("backoff_ms", 3000);
    }

    static void a(Context context, int i) {
        SharedPreferences.Editor editorEdit = k(context).edit();
        editorEdit.putInt("backoff_ms", i);
        editorEdit.commit();
    }

    private static SharedPreferences k(Context context) {
        return context.getSharedPreferences("com.google.android.gcm", 0);
    }
}

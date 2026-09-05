package com.google.a.a.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/* JADX INFO: compiled from: AppFieldsDefaultProvider.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class i implements m {
    private static i e;
    private static Object f = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected String f590a;
    protected String b;
    protected String c;
    protected String d;

    public static void a(Context context) {
        synchronized (f) {
            if (e == null) {
                e = new i(context);
            }
        }
    }

    public static i a() {
        return e;
    }

    private i(Context context) {
        PackageManager packageManager = context.getPackageManager();
        this.c = context.getPackageName();
        this.d = packageManager.getInstallerPackageName(this.c);
        String string = this.c;
        String str = null;
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            if (packageInfo != null) {
                string = packageManager.getApplicationLabel(packageInfo.applicationInfo).toString();
                str = packageInfo.versionName;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            ah.a("Error retrieving package info: appName set to " + string);
        }
        this.f590a = string;
        this.b = str;
    }

    protected i() {
    }

    @Override // com.google.a.a.a.m
    public String a(String str) {
        if (str == null) {
            return null;
        }
        if (str.equals("&an")) {
            return this.f590a;
        }
        if (str.equals("&av")) {
            return this.b;
        }
        if (str.equals("&aid")) {
            return this.c;
        }
        if (str.equals("&aiid")) {
            return this.d;
        }
        return null;
    }
}

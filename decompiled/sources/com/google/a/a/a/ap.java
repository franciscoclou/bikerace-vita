package com.google.a.a.a;

import android.content.Context;
import android.util.DisplayMetrics;

/* JADX INFO: compiled from: ScreenResolutionDefaultProvider.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ap implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static ap f583a;
    private static Object b = new Object();
    private final Context c;

    public static void a(Context context) {
        synchronized (b) {
            if (f583a == null) {
                f583a = new ap(context);
            }
        }
    }

    public static ap a() {
        ap apVar;
        synchronized (b) {
            apVar = f583a;
        }
        return apVar;
    }

    protected ap(Context context) {
        this.c = context;
    }

    @Override // com.google.a.a.a.m
    public String a(String str) {
        if (str != null && str.equals("&sr")) {
            return b();
        }
        return null;
    }

    protected String b() {
        DisplayMetrics displayMetrics = this.c.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels + "x" + displayMetrics.heightPixels;
    }
}

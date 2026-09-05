package com.heyzap.internal;

import android.content.Context;
import android.util.Log;

/* JADX INFO: compiled from: Logger.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static boolean f759a = false;
    static boolean b = false;
    static String c = "HeyzapSDK";

    public static void a(final Context context) {
        new Thread(new Runnable() { // from class: com.heyzap.internal.k.1
            @Override // java.lang.Runnable
            public void run() {
                k.b = k.b || l.a("com.example.android.snake", context);
            }
        }).start();
    }

    public static void a(Object obj) {
        if (b) {
            if (obj != null) {
                a(obj.toString());
            } else {
                a("NULL");
            }
        }
    }

    public static void a(String str) {
        if (b) {
            if (str != null) {
                Log.d(c, str);
            } else {
                a("NULL");
            }
        }
    }

    public static void b(Object obj) {
        a(obj);
    }

    public static void b(String str) {
        a(str);
    }

    public static void a(String str, Object... objArr) {
        a(String.format(str, objArr));
    }

    public static void a(Object... objArr) {
        if (b && !f759a) {
            if (objArr == null) {
                b("null arguments");
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < objArr.length; i++) {
                sb.append(String.valueOf(objArr[i]));
                if (i + 1 < objArr.length) {
                    sb.append(", ");
                }
            }
            b(sb.toString());
        }
    }
}

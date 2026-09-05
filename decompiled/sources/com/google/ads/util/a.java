package com.google.ads.util;

import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f714a = Log.isLoggable("GoogleAdsAssertion", 3);

    public static void a(boolean z) {
        c(z, "Assertion failed.");
    }

    public static void a(boolean z, String str) {
        c(z, str);
    }

    public static void b(boolean z) {
        c(!z, "Assertion failed.");
    }

    public static void b(boolean z, String str) {
        c(!z, str);
    }

    public static void a(Object obj) {
        c(obj == null, "Assertion that an object is null failed.");
    }

    public static void b(Object obj) {
        c(obj != null, "Assertion that an object is not null failed.");
    }

    public static void a(Object obj, Object obj2) {
        c(obj == obj2, "Assertion that 'a' and 'b' refer to the same object failed.a: " + obj + ", b: " + obj2);
    }

    public static void a(String str) {
        c(!TextUtils.isEmpty(str), "Expected a non empty string, got: " + str);
    }

    /* JADX INFO: renamed from: com.google.ads.util.a$a, reason: collision with other inner class name */
    public class C0011a extends Error {
        public C0011a(String str) {
            super(str);
        }
    }

    private static void c(boolean z, String str) {
        if ((Log.isLoggable("GoogleAdsAssertion", 3) || f714a) && !z) {
            C0011a c0011a = new C0011a(str);
            Log.d("GoogleAdsAssertion", str, c0011a);
            throw c0011a;
        }
    }
}

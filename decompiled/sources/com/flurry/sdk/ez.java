package com.flurry.sdk;

import android.os.Build;
import android.text.TextUtils;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class ez extends fb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f559a;

    public ez(String str, int i) {
        super(a(str, i));
        this.f559a = i;
    }

    private static fc a(String str, int i) {
        if (!b(str, i)) {
            return null;
        }
        return fa.a(str);
    }

    private static boolean b(String str, int i) {
        return !TextUtils.isEmpty(str) && Build.VERSION.SDK_INT >= i;
    }
}

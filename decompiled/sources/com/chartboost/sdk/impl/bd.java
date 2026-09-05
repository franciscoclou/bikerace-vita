package com.chartboost.sdk.impl;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class bd {
    public static <T> T a(String str, T t) {
        if (t == null) {
            throw new a(str);
        }
        return t;
    }

    class a extends IllegalArgumentException {
        a(String str) {
            super(String.valueOf(str) + " should not be null!");
        }
    }
}

package com.chartboost.sdk.impl;

import java.util.regex.Pattern;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ag {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Pattern f416a = Pattern.compile("\\s+", 40);

    public class a extends RuntimeException {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final String f417a;

        a(String str) {
            super(str);
            this.f417a = str;
        }

        @Override // java.lang.Throwable
        public String toString() {
            return this.f417a;
        }
    }

    public static void a(int i, int i2) {
        if (i != i2) {
            throw new a(i + " != " + i2);
        }
    }
}

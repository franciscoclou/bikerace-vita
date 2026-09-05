package com.topfreegames.bikerace;

import java.util.Date;

/* JADX INFO: compiled from: TokenInvalidTimestampGambi.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class bm {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f1160a = false;
    private static boolean b = true;
    private static long c = 0;

    public static boolean a() {
        return f1160a && new Date().getTime() > c + 600000;
    }

    public static void b() {
        c = new Date().getTime();
        f1160a = false;
    }

    public static void c() {
        b = true;
    }

    public static boolean d() {
        return b;
    }
}

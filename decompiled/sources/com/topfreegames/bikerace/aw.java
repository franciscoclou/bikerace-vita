package com.topfreegames.bikerace;

/* JADX INFO: compiled from: MainConfig.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class aw {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String[] f1126a = {"challenge", "nudge", "newuser"};
    private static final String[] b = {" just sent you a challenge!", " just poked you!", " just started playing BikeRace!"};

    public static String a(com.topfreegames.bikerace.push.d dVar) {
        return f1126a[dVar.ordinal()];
    }

    public static String a() {
        return ap.d() ? "https://queue.amazonaws.com/797740695898/bikerace_push_notifications_stag" : "https://queue.amazonaws.com/797740695898/bikerace_push_notifications_prod";
    }

    public static String b() {
        return "327153145868";
    }

    public static String c() {
        return "sn";
    }

    public static String d() {
        return "fu";
    }

    public static String e() {
        return "fn";
    }

    public static String f() {
        return "mt";
    }

    public static int g() {
        return 2130837760;
    }

    public static String h() {
        return "alert";
    }
}

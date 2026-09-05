package com.google.a.a.a;

/* JADX INFO: compiled from: Log.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ah {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static ae f576a;

    public static void a(String str) {
        ai aiVarB = b();
        if (aiVarB != null) {
            aiVarB.d(str);
        }
    }

    public static void b(String str) {
        ai aiVarB = b();
        if (aiVarB != null) {
            aiVarB.b(str);
        }
    }

    public static void c(String str) {
        ai aiVarB = b();
        if (aiVarB != null) {
            aiVarB.a(str);
        }
    }

    public static void d(String str) {
        ai aiVarB = b();
        if (aiVarB != null) {
            aiVarB.c(str);
        }
    }

    public static boolean a() {
        if (b() != null) {
            return aj.VERBOSE.equals(b().a());
        }
        return false;
    }

    private static ai b() {
        if (f576a == null) {
            f576a = ae.a();
        }
        if (f576a != null) {
            return f576a.d();
        }
        return null;
    }
}

package org.c.f;

/* JADX INFO: compiled from: Base64Encoder.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static a f1628a;

    public abstract String a(byte[] bArr);

    public abstract String c();

    public static synchronized a a() {
        if (f1628a == null) {
            f1628a = d();
        }
        return f1628a;
    }

    private static a d() {
        return b.d() ? new b() : new c();
    }

    public static String b() {
        return a().c();
    }
}

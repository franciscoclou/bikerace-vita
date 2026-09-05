package com.google.ads.util;

import android.os.Build;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class d {
    static final d d = new d();
    static final d e = new d("unknown", "generic", "generic");
    static final d f = new d("unknown", "generic_x86", "Android");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f719a;
    public final String b;
    public final String c;

    d() {
        this.f719a = Build.BOARD;
        this.b = Build.DEVICE;
        this.c = Build.BRAND;
    }

    d(String str, String str2, String str3) {
        this.f719a = str;
        this.b = str2;
        this.c = str3;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return a(this.f719a, dVar.f719a) && a(this.b, dVar.b) && a(this.c, dVar.c);
    }

    private static boolean a(String str, String str2) {
        if (str != null) {
            return str.equals(str2);
        }
        return str == str2;
    }

    public int hashCode() {
        int iHashCode = this.f719a != null ? 0 + this.f719a.hashCode() : 0;
        if (this.b != null) {
            iHashCode += this.b.hashCode();
        }
        if (this.c != null) {
            return iHashCode + this.c.hashCode();
        }
        return iHashCode;
    }
}

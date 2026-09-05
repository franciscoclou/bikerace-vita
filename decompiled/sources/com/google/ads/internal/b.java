package com.google.ads.internal;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class b extends Exception {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f670a;

    public b(String str, boolean z) {
        super(str);
        this.f670a = z;
    }

    public b(String str, boolean z, Throwable th) {
        super(str, th);
        this.f670a = z;
    }

    public void a(String str) {
        com.google.ads.util.b.b(c(str));
        com.google.ads.util.b.a((String) null, this);
    }

    public void b(String str) {
        String strC = c(str);
        if (!this.f670a) {
            this = null;
        }
        throw new RuntimeException(strC, this);
    }

    public String c(String str) {
        if (this.f670a) {
            return str + ": " + getMessage();
        }
        return str;
    }
}

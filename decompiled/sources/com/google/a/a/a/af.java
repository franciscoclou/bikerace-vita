package com.google.a.a.a;

import android.text.TextUtils;

/* JADX INFO: compiled from: Hit.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class af {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f575a;
    private final long b;
    private final long c;
    private String d = "https:";

    String a() {
        return this.f575a;
    }

    void a(String str) {
        this.f575a = str;
    }

    long b() {
        return this.b;
    }

    long c() {
        return this.c;
    }

    af(String str, long j, long j2) {
        this.f575a = str;
        this.b = j;
        this.c = j2;
    }

    String d() {
        return this.d;
    }

    void b(String str) {
        if (str != null && !TextUtils.isEmpty(str.trim()) && str.toLowerCase().startsWith("http:")) {
            this.d = "http:";
        }
    }
}

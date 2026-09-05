package com.applovin.impl.a;

import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a extends com.applovin.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f176a;
    private final String b;
    private float c;
    private float d;
    private int e;
    private int f;
    private com.applovin.impl.adview.ad g;

    public a(String str, com.applovin.a.f fVar, com.applovin.a.g gVar, List list, String str2, b bVar, com.applovin.impl.adview.ad adVar, float f, float f2, int i, int i2, String str3) {
        super(str, fVar, gVar, list, str2);
        this.f = -1;
        this.f176a = bVar;
        this.f = i2;
        this.c = f;
        this.e = i;
        this.f = i2;
        this.b = str3;
        this.g = adVar;
        this.d = f2;
    }

    public b a() {
        return this.f176a;
    }

    public float b() {
        return this.c;
    }

    public float c() {
        return this.d;
    }

    public int d() {
        return this.e;
    }

    public com.applovin.impl.adview.ad e() {
        return this.g;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f == ((a) obj).f;
    }

    public int hashCode() {
        return this.f;
    }
}

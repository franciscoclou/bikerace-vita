package com.applovin.a;

import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f154a;
    private final f b;
    private final g c;
    private final List d;
    private final String e;

    public a(String str, f fVar, g gVar, List list, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("No html specified");
        }
        if (fVar == null) {
            throw new IllegalArgumentException("No size specified");
        }
        if (gVar == null) {
            throw new IllegalArgumentException("No type specified");
        }
        this.f154a = str;
        this.b = fVar;
        this.c = gVar;
        this.d = list;
        this.e = str2;
    }

    public String f() {
        return this.f154a;
    }

    public f g() {
        return this.b;
    }

    public g h() {
        return this.c;
    }

    public List i() {
        return this.d;
    }

    public String j() {
        return this.e;
    }
}

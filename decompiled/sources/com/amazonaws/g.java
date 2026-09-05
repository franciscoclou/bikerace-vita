package com.amazonaws;

import com.amazonaws.f.m;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f134a = m.c();
    private String b;
    private int c;
    private i d;
    private String e;
    private int f;
    private String g;
    private String h;
    private String i;
    private String j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;

    public g() {
        this.b = f134a;
        this.c = 3;
        this.d = i.HTTPS;
        this.e = null;
        this.f = -1;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = 50;
        this.l = 50000;
        this.m = 50000;
        this.n = 0;
        this.o = 0;
    }

    public g(g gVar) {
        this.b = f134a;
        this.c = 3;
        this.d = i.HTTPS;
        this.e = null;
        this.f = -1;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = 50;
        this.l = 50000;
        this.m = 50000;
        this.n = 0;
        this.o = 0;
        this.m = gVar.m;
        this.k = gVar.k;
        this.c = gVar.c;
        this.d = gVar.d;
        this.i = gVar.i;
        this.e = gVar.e;
        this.h = gVar.h;
        this.f = gVar.f;
        this.g = gVar.g;
        this.j = gVar.j;
        this.l = gVar.l;
        this.b = gVar.b;
        this.o = gVar.o;
        this.n = gVar.n;
    }

    public i a() {
        return this.d;
    }

    public void a(int i) {
        this.c = i;
    }

    public g b(int i) {
        a(i);
        return this;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.e;
    }

    public int d() {
        return this.f;
    }

    public String e() {
        return this.g;
    }

    public String f() {
        return this.h;
    }

    public String g() {
        return this.i;
    }

    public String h() {
        return this.j;
    }

    public int i() {
        return this.c;
    }

    public int j() {
        return this.l;
    }

    public int k() {
        return this.m;
    }

    public int[] l() {
        return new int[]{this.n, this.o};
    }
}

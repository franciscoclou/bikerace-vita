package com.topfreegames.bikerace.a;

import java.util.UnknownFormatConversionException;

/* JADX INFO: compiled from: Achievement.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private h f813a;
    private volatile b b;

    a(String str, int i, String str2, h hVar) {
        this(str, false, i, 0, str2, str2, true, hVar);
    }

    a(String str, int i, String str2, String str3, h hVar) {
        this(str, false, i, 0, str2, str3, true, hVar);
    }

    a(b bVar, h hVar) {
        this.b = new b();
        if (bVar == null) {
            throw new IllegalArgumentException("Data cannot be null!");
        }
        if (hVar == null) {
            throw new IllegalArgumentException("Mediator cannot be null! ");
        }
        this.b = bVar;
        this.f813a = hVar;
    }

    a(String str, boolean z, int i, int i2, String str2, String str3, boolean z2, h hVar) {
        this.b = new b();
        if (str == null) {
            throw new IllegalArgumentException("Id cannot be null!");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("Description cannot be null!");
        }
        if (str3 == null) {
            throw new IllegalArgumentException("Partial description cannot be null");
        }
        if (hVar != null) {
            this.b.d = z;
            this.b.f814a = str;
            this.b.g = i;
            this.b.h = i2;
            this.b.f = 0;
            this.b.b = str2;
            this.b.c = str3;
            this.b.e = z2;
            this.f813a = hVar;
            return;
        }
        throw new IllegalArgumentException("Mediator description cannot be null!");
    }

    public String a() {
        return this.b.f814a;
    }

    public boolean b() {
        return this.b.d;
    }

    void c() {
        a(this.b.h + 1);
    }

    void a(int i) {
        if (i >= 0 && this.b.h != i) {
            this.b.h = i;
            if (this.b.h > this.b.f) {
                this.b.f = this.b.h;
            }
            if (this.b.h >= this.b.g) {
                this.b.d = true;
                if (this.b.e) {
                    this.f813a.a(this);
                }
            }
            this.f813a.a();
        }
    }

    public int d() {
        return this.b.h;
    }

    public String e() {
        try {
            return String.format(this.b.b, Integer.valueOf(this.b.g), Integer.valueOf(this.b.h));
        } catch (UnknownFormatConversionException e) {
            try {
                return String.format(this.b.b, Integer.valueOf(this.b.g));
            } catch (UnknownFormatConversionException e2) {
                return this.b.b;
            }
        }
    }

    b f() {
        return this.b;
    }
}

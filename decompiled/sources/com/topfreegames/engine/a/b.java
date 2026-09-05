package com.topfreegames.engine.a;

import android.annotation.SuppressLint;
import android.util.FloatMath;

/* JADX INFO: compiled from: Vector2D.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
@SuppressLint({"FloatMath"})
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f1553a;
    public float b;

    public b() {
        this.f1553a = 0.0f;
        this.b = 0.0f;
    }

    public b(float f, float f2) {
        this.f1553a = f;
        this.b = f2;
    }

    public b(b bVar) {
        this.f1553a = bVar.f1553a;
        this.b = bVar.b;
    }

    public b a(float f, float f2) {
        this.f1553a = f;
        this.b = f2;
        return this;
    }

    public b a(b bVar) {
        this.f1553a = bVar.f1553a;
        this.b = bVar.b;
        return this;
    }

    public float[] a() {
        return new float[]{this.f1553a, this.b};
    }

    public b a(float f) {
        float fCos = FloatMath.cos(f);
        float fSin = FloatMath.sin(f);
        float f2 = this.f1553a;
        this.f1553a = (this.f1553a * fCos) - (this.b * fSin);
        this.b = (fCos * this.b) + (fSin * f2);
        return this;
    }

    public static int b() {
        return 8;
    }

    public b b(b bVar) {
        this.f1553a += bVar.f1553a;
        this.b += bVar.b;
        return this;
    }

    public b b(float f, float f2) {
        this.f1553a += f;
        this.b += f2;
        return this;
    }

    public b c(b bVar) {
        this.f1553a -= bVar.f1553a;
        this.b -= bVar.b;
        return this;
    }

    public b b(float f) {
        this.f1553a *= f;
        this.b *= f;
        return this;
    }

    public b c(float f) {
        this.f1553a /= f;
        this.b /= f;
        return this;
    }

    public float d(b bVar) {
        return (this.f1553a * bVar.f1553a) + (this.b * bVar.b);
    }

    public float c() {
        if (this.f1553a == 0.0f && this.b == 0.0f) {
            return 0.0f;
        }
        return FloatMath.sqrt((this.f1553a * this.f1553a) + (this.b * this.b));
    }

    public b d() {
        float fC = c();
        if (Float.compare(fC, 0.0f) != 0) {
            c(fC);
        }
        return this;
    }

    public b a(b bVar, b bVar2) {
        bVar2.a(bVar).d();
        bVar2.b(d(bVar2));
        a(bVar2);
        return this;
    }

    public float e(b bVar) {
        return (this.f1553a * bVar.b) - (this.b * bVar.f1553a);
    }

    public boolean f(b bVar) {
        return this.f1553a == bVar.f1553a && this.b == bVar.b;
    }
}

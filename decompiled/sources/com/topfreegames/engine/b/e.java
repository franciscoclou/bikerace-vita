package com.topfreegames.engine.b;

import android.opengl.Matrix;

/* JADX INFO: compiled from: Matrix.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float[] f1556a = new float[16];
    private float[] b = new float[16];
    private float[] c = new float[16];

    public e() {
        a();
    }

    public void a(float f, float f2, float f3) {
        Matrix.translateM(this.f1556a, 0, f, f2, f3);
    }

    public void a(float f, float f2, float f3, float f4) {
        System.arraycopy(this.f1556a, 0, this.c, 0, this.f1556a.length);
        Matrix.setRotateM(this.b, 0, f, f2, f3, f4);
        Matrix.multiplyMM(this.f1556a, 0, this.c, 0, this.b, 0);
    }

    public void b(float f, float f2, float f3) {
        Matrix.scaleM(this.f1556a, 0, f, f2, f3);
    }

    public void a() {
        Matrix.setIdentityM(this.f1556a, 0);
    }

    public float[] b() {
        return this.f1556a;
    }

    public void a(e eVar) {
        System.arraycopy(eVar.f1556a, 0, this.f1556a, 0, eVar.f1556a.length);
    }
}

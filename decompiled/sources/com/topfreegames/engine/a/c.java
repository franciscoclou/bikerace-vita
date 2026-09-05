package com.topfreegames.engine.a;

/* JADX INFO: compiled from: Vector3D.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f1554a = 0.0f;
    public float b = 0.0f;
    public float c = 0.0f;

    public c a(float f) {
        this.f1554a /= f;
        this.b /= f;
        this.c /= f;
        return this;
    }

    public c a(c cVar) {
        this.f1554a = cVar.f1554a;
        this.b = cVar.b;
        this.c = cVar.c;
        return this;
    }

    public c a(float f, float f2, float f3) {
        this.f1554a = f;
        this.b = f2;
        this.c = f3;
        return this;
    }
}

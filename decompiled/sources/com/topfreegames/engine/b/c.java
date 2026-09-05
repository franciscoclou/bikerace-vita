package com.topfreegames.engine.b;

/* JADX INFO: compiled from: Color.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f1555a;
    public float b;
    public float c;
    public float d;

    public c() {
        this.f1555a = 0.0f;
        this.b = 0.0f;
        this.c = 0.0f;
        this.d = 1.0f;
    }

    public c(float f, float f2, float f3) {
        this.f1555a = f;
        this.b = f2;
        this.c = f3;
        this.d = 1.0f;
    }

    public c(float f, float f2, float f3, float f4) {
        this.f1555a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
    }

    public void a(c cVar) {
        this.f1555a = cVar.f1555a;
        this.b = cVar.b;
        this.c = cVar.c;
        this.d = cVar.d;
    }
}

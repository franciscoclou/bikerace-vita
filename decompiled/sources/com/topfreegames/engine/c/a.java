package com.topfreegames.engine.c;

/* JADX INFO: compiled from: Body.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {
    public float c;
    public float d;
    public float e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public com.topfreegames.engine.a.b f1561a = new com.topfreegames.engine.a.b();
    public com.topfreegames.engine.a.b b = new com.topfreegames.engine.a.b();
    private com.topfreegames.engine.a.b f = new com.topfreegames.engine.a.b();
    private com.topfreegames.engine.a.b g = new com.topfreegames.engine.a.b();
    private com.topfreegames.engine.a.b h = new com.topfreegames.engine.a.b();

    public a(com.topfreegames.engine.a.b bVar, com.topfreegames.engine.a.b bVar2, float f, float f2, float f3) {
        this.c = 0.0f;
        this.d = 0.0f;
        this.e = 0.0f;
        this.f1561a.a(bVar);
        this.b.a(bVar2);
        this.c = f;
        this.d = f2;
        this.e = f3;
    }

    public void a(com.topfreegames.engine.a.b bVar, float f) {
        this.f.a(bVar).b(f);
        this.b.b(this.f);
    }

    public void b(com.topfreegames.engine.a.b bVar, float f) {
        this.g.a(bVar).c(this.c);
        a(this.g, f);
    }

    public void a(float f) {
        this.h.a(this.b).b(f);
        this.f1561a.b(this.h);
        this.d += this.e * f;
        this.d %= 360.0f;
    }
}

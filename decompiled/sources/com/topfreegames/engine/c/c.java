package com.topfreegames.engine.c;

/* JADX INFO: compiled from: SpringShockAbsorber.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float f1563a;
    private float b;
    private float c;
    private com.topfreegames.engine.a.b d = new com.topfreegames.engine.a.b();
    private com.topfreegames.engine.a.b e = new com.topfreegames.engine.a.b();
    private com.topfreegames.engine.a.b f = new com.topfreegames.engine.a.b();
    private com.topfreegames.engine.a.b g = new com.topfreegames.engine.a.b();
    private com.topfreegames.engine.a.b h = new com.topfreegames.engine.a.b();
    private com.topfreegames.engine.a.b i = new com.topfreegames.engine.a.b();
    private com.topfreegames.engine.a.b j = new com.topfreegames.engine.a.b();
    private com.topfreegames.engine.a.b k = new com.topfreegames.engine.a.b();

    public c(float f, float f2, float f3) {
        this.f1563a = 0.0f;
        this.b = 0.0f;
        this.c = 0.0f;
        this.f1563a = f;
        this.b = f2;
        this.c = f3;
    }

    private com.topfreegames.engine.a.b a(a aVar, a aVar2) {
        this.d.a(aVar2.f1561a).c(aVar.f1561a);
        this.e.a(this.d).b(this.c / this.d.c());
        this.f.a(this.d).c(this.e);
        this.h.a(this.f).b(-this.f1563a);
        this.g.a(aVar2.b).c(aVar.b).a(this.d, this.k);
        this.i.a(this.g.a(this.d, this.k)).b(-this.b);
        this.j.a(this.h).b(this.i);
        return this.j;
    }

    public float a(a aVar, a aVar2, float f) {
        this.j.a(a(aVar, aVar2));
        aVar2.b(this.j, f);
        this.j.b(-1.0f);
        aVar.b(this.j, f);
        return this.j.c();
    }

    public float a(a aVar, a aVar2, com.topfreegames.engine.a.b bVar, float f) {
        this.j.a(a(aVar, aVar2));
        float fC = this.j.c();
        this.j.a(bVar, this.k).b(-1.0f);
        aVar.b(this.j, f);
        return fC;
    }
}

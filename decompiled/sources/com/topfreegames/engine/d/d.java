package com.topfreegames.engine.d;

import android.annotation.SuppressLint;
import android.util.FloatMath;

/* JADX INFO: compiled from: CyclicSpriteSceneNode.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
@SuppressLint({"FloatMath"})
public class d extends i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected com.topfreegames.engine.a.b f1565a;
    protected float b;
    protected float c;

    @Override // com.topfreegames.engine.d.i, com.topfreegames.engine.d.j, com.topfreegames.engine.d.b, com.topfreegames.engine.d.e
    public /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    @Override // com.topfreegames.engine.d.i, com.topfreegames.engine.d.j, com.topfreegames.engine.d.b, com.topfreegames.engine.d.e
    public /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    public d(com.topfreegames.engine.b.d dVar, e eVar, com.topfreegames.engine.a.b bVar, com.topfreegames.engine.a.b bVar2, float f, com.topfreegames.engine.b.h hVar, float f2) {
        super(dVar, eVar, bVar, bVar2, f, hVar);
        this.f1565a = new com.topfreegames.engine.a.b();
        this.b = 0.0f;
        this.c = 0.0f;
        this.c = f2;
    }

    @Override // com.topfreegames.engine.d.j, com.topfreegames.engine.d.b
    protected void f() {
        if (this.f == null) {
            this.k.a();
        } else {
            this.k.a(this.f.e());
        }
        this.k.b(this.i.f1553a, this.i.b, 1.0f);
        this.k.a(this.j, 0.0f, 0.0f, 1.0f);
        this.k.a(this.h.f1553a, this.h.b, 0.0f);
        this.n.a(this.k);
    }

    @Override // com.topfreegames.engine.d.i, com.topfreegames.engine.d.j, com.topfreegames.engine.d.b
    protected void a() {
        this.b = ((this.m.b() * 2.0f) / this.m.a()) * this.c;
        float f = this.b * this.c;
        float f2 = this.b;
        this.f1565a.f1553a = FloatMath.ceil(this.g.f1553a / f);
        this.f1565a.b = 1.0f;
        float f3 = this.g.f1553a / 2.0f;
        for (int i = 0; i < this.f1565a.f1553a; i++) {
            this.o.left = (-f3) + (i * f);
            this.o.right = (-f3) + ((i + 1) * f);
            for (int i2 = 0; i2 < this.f1565a.b; i2++) {
                this.o.bottom = i2 * f2;
                this.o.top = (i2 + 1) * f2;
                this.n.a(this.o, this.m);
            }
        }
    }
}

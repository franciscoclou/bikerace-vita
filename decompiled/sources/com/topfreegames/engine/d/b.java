package com.topfreegames.engine.d;

import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: BaseSceneNode.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class b implements e {
    protected ArrayList<e> e = new ArrayList<>();
    protected e f = null;
    protected com.topfreegames.engine.a.b g = new com.topfreegames.engine.a.b();
    protected com.topfreegames.engine.a.b h = new com.topfreegames.engine.a.b();
    protected com.topfreegames.engine.a.b i = new com.topfreegames.engine.a.b(1.0f, 1.0f);
    protected float j = 0.0f;
    protected com.topfreegames.engine.b.e k = new com.topfreegames.engine.b.e();
    protected boolean l = true;
    protected com.topfreegames.engine.b.h m;
    protected com.topfreegames.engine.b.d n;

    protected abstract void a();

    protected abstract void f();

    protected abstract void g();

    protected abstract void h();

    protected b(com.topfreegames.engine.b.d dVar, e eVar) {
        this.n = null;
        b(eVar);
        this.n = dVar;
    }

    @Override // com.topfreegames.engine.d.e
    public final void a(e eVar) {
        if (eVar != null && !this.e.contains(eVar)) {
            this.e.add(eVar);
        }
    }

    @Override // com.topfreegames.engine.d.e
    public final void b(e eVar) {
        this.f = eVar;
        if (eVar != null) {
            eVar.a(this);
        }
    }

    @Override // com.topfreegames.engine.d.e
    public final void a(com.topfreegames.engine.a.b bVar) {
        if (bVar != null) {
            this.g.a(bVar);
        }
    }

    @Override // com.topfreegames.engine.d.e
    public final com.topfreegames.engine.a.b d() {
        return this.g;
    }

    @Override // com.topfreegames.engine.d.e
    public final void b(com.topfreegames.engine.a.b bVar) {
        if (bVar != null) {
            this.h.a(bVar);
        }
    }

    public final void a(float f) {
        this.j = f;
    }

    @Override // com.topfreegames.engine.d.e
    public final void c(com.topfreegames.engine.a.b bVar) {
        if (bVar != null) {
            this.i.a(bVar);
        }
    }

    @Override // com.topfreegames.engine.d.e
    public final void a(boolean z) {
        this.l = z;
    }

    public final void a(com.topfreegames.engine.b.h hVar) {
        this.m = hVar;
    }

    @Override // com.topfreegames.engine.d.e
    public final com.topfreegames.engine.b.e e() {
        return this.k;
    }

    @Override // com.topfreegames.engine.d.e
    public void b() {
        this.n = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.k = null;
        this.m = null;
    }

    @Override // com.topfreegames.engine.d.e
    public void c() {
        if (this.l) {
            f();
            a();
            g();
            h();
        }
    }
}

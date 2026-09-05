package com.topfreegames.engine.d;

/* JADX INFO: compiled from: MeshSceneNode.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class h extends j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected f f1568a;

    @Override // com.topfreegames.engine.d.j, com.topfreegames.engine.d.b, com.topfreegames.engine.d.e
    public /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    @Override // com.topfreegames.engine.d.j, com.topfreegames.engine.d.b, com.topfreegames.engine.d.e
    public /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    public h(com.topfreegames.engine.b.d dVar, e eVar, f fVar) {
        super(dVar, eVar);
        this.f1568a = null;
        this.f1568a = fVar;
    }

    @Override // com.topfreegames.engine.d.j, com.topfreegames.engine.d.b
    public void a() {
        for (int i = 0; i < this.f1568a.a(); i++) {
            this.n.a(this.f1568a.a(i));
        }
    }
}

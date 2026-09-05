package com.topfreegames.engine.d;

/* JADX INFO: compiled from: AnimatedSpriteSceneNode.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a extends i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected com.topfreegames.engine.b.h[] f1564a;
    protected long[] b;
    protected long c;
    protected int d;

    @Override // com.topfreegames.engine.d.i, com.topfreegames.engine.d.j, com.topfreegames.engine.d.b, com.topfreegames.engine.d.e
    public /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    public a(com.topfreegames.engine.b.d dVar, e eVar, com.topfreegames.engine.a.b bVar, com.topfreegames.engine.a.b bVar2, float f, com.topfreegames.engine.b.h[] hVarArr, long[] jArr) {
        super(dVar, eVar, bVar, bVar2, f, hVarArr[0]);
        this.f1564a = null;
        this.b = null;
        this.c = -1L;
        this.d = 0;
        this.f1564a = hVarArr;
        this.b = jArr;
        this.c = -this.b[0];
    }

    @Override // com.topfreegames.engine.d.i, com.topfreegames.engine.d.j, com.topfreegames.engine.d.b
    protected void a() {
        if (this.n.g() - this.c > this.b[this.d]) {
            this.d++;
            if (this.d >= this.f1564a.length) {
                this.d = 0;
            }
            a(this.f1564a[this.d]);
            this.c = this.n.f();
        }
        super.a();
    }

    @Override // com.topfreegames.engine.d.i, com.topfreegames.engine.d.j, com.topfreegames.engine.d.b, com.topfreegames.engine.d.e
    public void b() {
        super.b();
        this.f1564a = null;
        this.b = null;
    }
}

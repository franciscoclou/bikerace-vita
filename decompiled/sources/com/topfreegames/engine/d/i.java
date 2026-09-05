package com.topfreegames.engine.d;

import android.graphics.RectF;
import java.util.Iterator;

/* JADX INFO: compiled from: SpriteSceneNode.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class i extends j {
    protected RectF o;
    protected com.topfreegames.engine.b.c p;

    @Override // com.topfreegames.engine.d.j, com.topfreegames.engine.d.b, com.topfreegames.engine.d.e
    public /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    @Override // com.topfreegames.engine.d.j, com.topfreegames.engine.d.b, com.topfreegames.engine.d.e
    public /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    public i(com.topfreegames.engine.b.d dVar, e eVar, com.topfreegames.engine.a.b bVar, com.topfreegames.engine.a.b bVar2, float f, com.topfreegames.engine.b.h hVar) {
        super(dVar, eVar);
        this.o = new RectF();
        this.p = null;
        this.h.a(bVar);
        this.g.a(bVar2);
        this.j = f;
        this.m = hVar;
    }

    public void a(com.topfreegames.engine.b.c cVar) {
        this.p = cVar;
    }

    @Override // com.topfreegames.engine.d.j, com.topfreegames.engine.d.b
    protected void a() {
        float f = this.g.f1553a / 2.0f;
        float f2 = this.g.b / 2.0f;
        this.o.left = -f;
        this.o.right = f;
        this.o.top = f2;
        this.o.bottom = -f2;
        this.n.a(this.o, this.m, this.p);
    }

    @Override // com.topfreegames.engine.d.j, com.topfreegames.engine.d.b
    protected void g() {
        if (this.e.size() > 0) {
            Iterator<e> it = this.e.iterator();
            while (it.hasNext()) {
                it.next().c();
            }
        }
    }
}

package com.topfreegames.bikerace.h;

import android.graphics.RectF;
import java.util.ArrayList;

/* JADX INFO: compiled from: LevelMeshLoader.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected com.topfreegames.engine.b.h f1257a;
    protected float b = 0.05f;

    public w(com.topfreegames.engine.b.h hVar) {
        this.f1257a = null;
        this.f1257a = hVar;
    }

    public void a(float f) {
        this.b = f;
    }

    public com.topfreegames.engine.d.f a(Object obj) {
        int i = 0;
        b bVarA = ((a) obj).a();
        com.topfreegames.engine.d.f fVar = new com.topfreegames.engine.d.f();
        int iD = bVarA.d();
        for (int i2 = 0; i2 < iD - 1; i2++) {
            if (!bVarA.a(i2).b.f(bVarA.a(i2 + 1).f1168a)) {
                fVar.a(a(bVarA, i, i2));
                i = i2 + 1;
            }
        }
        fVar.a(a(bVarA, i, iD - 1));
        return fVar;
    }

    private com.topfreegames.engine.d.g a(b bVar, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        com.topfreegames.engine.a.b bVar2 = new com.topfreegames.engine.a.b();
        RectF rectFD = this.f1257a.d();
        while (i <= i2) {
            com.topfreegames.bikerace.d dVarA = bVar.a(i);
            bVar2.a(dVarA.f1168a).c(dVarA.b);
            if (bVar2.b > 0.0f) {
            }
            bVar2.a(1.5707964f).d().b(this.b);
            arrayList.add(new com.topfreegames.engine.b.i(new com.topfreegames.engine.a.b(dVarA.f1168a), new com.topfreegames.engine.a.b(rectFD.left, rectFD.top)));
            arrayList.add(new com.topfreegames.engine.b.i(new com.topfreegames.engine.a.b(dVarA.f1168a).b(bVar2), new com.topfreegames.engine.a.b(rectFD.left, rectFD.bottom)));
            arrayList.add(new com.topfreegames.engine.b.i(new com.topfreegames.engine.a.b(dVarA.b), new com.topfreegames.engine.a.b(rectFD.right, rectFD.top)));
            arrayList.add(new com.topfreegames.engine.b.i(new com.topfreegames.engine.a.b(dVarA.b).b(bVar2), new com.topfreegames.engine.a.b(rectFD.right, rectFD.bottom)));
            i++;
        }
        return new com.topfreegames.engine.d.g(arrayList, this.f1257a);
    }
}

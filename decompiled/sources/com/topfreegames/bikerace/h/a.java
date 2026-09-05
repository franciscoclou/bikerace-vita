package com.topfreegames.bikerace.h;

import com.topfreegames.bikerace.ap;

/* JADX INFO: compiled from: Level.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b f1241a;
    protected float[] b;
    protected com.topfreegames.engine.a.b c;
    protected com.topfreegames.engine.a.b d;
    protected com.topfreegames.bikerace.c.a[] e;

    public a(b bVar, float[] fArr) {
        this(bVar, fArr, null);
    }

    public a(b bVar, float[] fArr, com.topfreegames.bikerace.c.a[] aVarArr) {
        this(bVar, fArr, b(bVar), c(bVar), aVarArr);
    }

    protected a(b bVar, float[] fArr, com.topfreegames.engine.a.b bVar2, com.topfreegames.engine.a.b bVar3, com.topfreegames.bikerace.c.a[] aVarArr) {
        this.f1241a = null;
        this.b = new float[3];
        this.c = null;
        this.d = null;
        a(bVar);
        a(fArr);
        this.c = bVar2;
        this.d = bVar3;
        com.topfreegames.bikerace.c.d.a().b(aVarArr);
        this.e = aVarArr;
    }

    public float a(float f) {
        int iB = b(f);
        if (iB == 3) {
            return 0.0f;
        }
        return this.b[iB];
    }

    public int b(float f) {
        for (int i = 0; i < 3; i++) {
            if (((int) (this.b[i] * 100.0f)) < ((int) (100.0f * f))) {
                return i;
            }
        }
        return 3;
    }

    public b a() {
        return this.f1241a;
    }

    public com.topfreegames.bikerace.c.a[] b() {
        return this.e;
    }

    public com.topfreegames.engine.a.b c() {
        return this.c;
    }

    public com.topfreegames.engine.a.b d() {
        return this.d;
    }

    protected void a(b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Boards cannot be null!");
        }
        this.f1241a = bVar;
        if (ap.c()) {
            this.f1241a.e();
        }
    }

    protected void a(float[] fArr) {
        if (fArr == null) {
            throw new IllegalArgumentException("Stars times cannot be null!");
        }
        if (fArr.length != 3) {
            throw new IllegalArgumentException("Incorrect stars times format!");
        }
        this.b = fArr;
    }

    private static com.topfreegames.engine.a.b b(b bVar) {
        if (bVar == null || bVar.d() <= 0) {
            return null;
        }
        com.topfreegames.engine.a.b bVar2 = new com.topfreegames.engine.a.b(bVar.b().f1168a);
        bVar2.b(0.5f, 0.0f);
        return bVar2;
    }

    private static com.topfreegames.engine.a.b c(b bVar) {
        if (bVar == null || bVar.d() <= 0) {
            return null;
        }
        return new com.topfreegames.engine.a.b(bVar.c().f1168a);
    }
}

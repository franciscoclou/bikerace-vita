package com.topfreegames.bikerace;

import android.graphics.RectF;

/* JADX INFO: compiled from: Camera.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected com.topfreegames.engine.a.b f1170a = new com.topfreegames.engine.a.b();
    protected RectF b = new RectF();
    protected float c = 2.5f;
    protected com.topfreegames.engine.a.b d = new com.topfreegames.engine.a.b();

    public RectF a() {
        return this.b;
    }

    public com.topfreegames.engine.a.b b() {
        return this.f1170a;
    }

    public float c() {
        return this.c;
    }

    public void a(com.topfreegames.engine.a.b bVar, com.topfreegames.engine.a.b bVar2, float f, float f2, float f3, float f4, boolean z, float f5) {
        float fC;
        if (bVar == null) {
            throw new IllegalArgumentException("RefPos cannot be null!");
        }
        if (bVar2 == null) {
            throw new IllegalArgumentException("RefVel cannot be null!");
        }
        com.topfreegames.engine.a.b bVar3 = this.d;
        if (z) {
            fC = b(this.b, f5);
            bVar3.a((this.b.right + this.b.left) / 2.0f, (this.b.top + this.b.bottom) / 2.0f);
        } else {
            fC = 2.5f - (1.0f / (1.0f + (bVar2.c() / 6.0f)));
            bVar3.a(bVar);
            bVar3.f1553a += this.c * f;
            bVar3.b = (float) (((double) bVar3.b) + (((((double) (this.c * 1.6f)) * Math.atan(bVar2.b / 6.0f)) / 3.141592653589793d) / 2.0d));
            float f6 = (this.b.bottom + fC) - 0.2f;
            if (bVar3.b < f6) {
                bVar3.b = f6;
            }
        }
        this.c = ((((fC - this.c) * f3) * f2) / f4) + this.c;
        bVar3.c(this.f1170a).b(f3 * f2);
        this.f1170a.b(bVar3);
    }

    public void a(RectF rectF, float f) {
        if (rectF == null) {
            throw new IllegalArgumentException("Limits cannot be null!");
        }
        this.b.set(rectF);
        this.c = b(rectF, f);
        this.f1170a.a(rectF.right + rectF.left, rectF.top + rectF.bottom).c(2.0f);
    }

    private static float b(RectF rectF, float f) {
        float fMax = Math.max(Math.abs(rectF.width()) / f, Math.abs(rectF.height()));
        if (fMax > 1.4f) {
            return fMax / 1.94f;
        }
        return 1.0f;
    }
}

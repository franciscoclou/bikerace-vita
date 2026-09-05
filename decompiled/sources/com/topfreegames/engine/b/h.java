package com.topfreegames.engine.b;

import android.graphics.RectF;

/* JADX INFO: compiled from: Texture.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1559a;
    private RectF b;

    public h(int i, RectF rectF) {
        this.f1559a = i;
        this.b = rectF;
    }

    public float a() {
        return this.b.right - this.b.left;
    }

    public float b() {
        return this.b.bottom - this.b.top;
    }

    public int c() {
        return this.f1559a;
    }

    public RectF d() {
        return this.b;
    }
}

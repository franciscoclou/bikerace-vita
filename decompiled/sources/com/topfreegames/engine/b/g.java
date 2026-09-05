package com.topfreegames.engine.b;

import java.nio.FloatBuffer;

/* JADX INFO: compiled from: OpenGLES10VideoDriver.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ f f1558a;
    private FloatBuffer b;
    private FloatBuffer c;
    private FloatBuffer e = b.a(new float[4]);
    private FloatBuffer d = b.a(new float[6]);

    public g(f fVar) {
        this.f1558a = fVar;
        float[] fArr = new float[8];
        this.b = b.a(fArr);
        this.c = b.a(fArr);
    }

    public FloatBuffer a(float[] fArr) {
        this.b.clear();
        this.b.put(fArr);
        this.b.position(0);
        return this.b;
    }

    public FloatBuffer b(float[] fArr) {
        this.c.clear();
        this.c.put(fArr);
        this.c.position(0);
        return this.c;
    }
}

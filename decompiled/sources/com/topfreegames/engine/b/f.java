package com.topfreegames.engine.b;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.opengl.GLES10;
import android.opengl.GLUtils;
import java.util.ArrayList;

/* JADX INFO: compiled from: OpenGLES10VideoDriver.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class f implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.topfreegames.engine.a.b f1557a = new com.topfreegames.engine.a.b();
    private g b = new g(this);
    private ArrayList<Integer> c = new ArrayList<>();
    private long d = -1;
    private int e = -1;
    private float[] f = new float[4];
    private float[] g = new float[6];
    private float[] h = new float[8];
    private float[] i = new float[8];

    @Override // com.topfreegames.engine.b.d
    public void a() {
        GLES10.glMatrixMode(5888);
        GLES10.glLoadIdentity();
        GLES10.glMatrixMode(5889);
        GLES10.glLoadIdentity();
        GLES10.glEnable(3553);
        GLES10.glBlendFunc(1, 771);
        GLES10.glEnable(3042);
        GLES10.glDisable(3024);
        int[] iArr = new int[1];
        GLES10.glGetIntegerv(3379, iArr, 0);
        this.e = iArr[0];
    }

    @Override // com.topfreegames.engine.b.d
    public void b() {
        int size = this.c.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = this.c.get(i).intValue();
        }
        GLES10.glDeleteTextures(size, iArr, 0);
        this.c.clear();
    }

    @Override // com.topfreegames.engine.b.d
    public h a(Bitmap bitmap) {
        return a(bitmap, new RectF(0.0f, 0.0f, 1.0f, 1.0f));
    }

    public h a(Bitmap bitmap, RectF rectF) {
        if (bitmap == null || rectF == null) {
            return null;
        }
        int[] iArr = new int[1];
        GLES10.glGenTextures(1, iArr, 0);
        GLES10.glBindTexture(3553, iArr[0]);
        GLES10.glTexParameterf(3553, 10241, 9729.0f);
        GLES10.glTexParameterf(3553, 10240, 9729.0f);
        GLUtils.texImage2D(3553, 0, bitmap, 0);
        this.c.add(Integer.valueOf(iArr[0]));
        return new h(iArr[0], rectF);
    }

    @Override // com.topfreegames.engine.b.d
    public void a(h hVar) {
        int[] iArr = {hVar.c()};
        this.c.remove(Integer.valueOf(iArr[0]));
        GLES10.glDeleteTextures(1, iArr, 0);
    }

    @Override // com.topfreegames.engine.b.d
    public h a(h hVar, RectF rectF) {
        return new h(hVar.c(), rectF);
    }

    @Override // com.topfreegames.engine.b.d
    public void a(RectF rectF, h hVar, c cVar) {
        if (rectF != null && hVar != null) {
            this.h[0] = rectF.left;
            this.h[1] = rectF.bottom;
            this.h[2] = rectF.left;
            this.h[3] = rectF.top;
            this.h[4] = rectF.right;
            this.h[5] = rectF.bottom;
            this.h[6] = rectF.right;
            this.h[7] = rectF.top;
            RectF rectFD = hVar.d();
            this.i[0] = rectFD.left;
            this.i[1] = rectFD.bottom;
            this.i[2] = rectFD.left;
            this.i[3] = rectFD.top;
            this.i[4] = rectFD.right;
            this.i[5] = rectFD.bottom;
            this.i[6] = rectFD.right;
            this.i[7] = rectFD.top;
            GLES10.glEnableClientState(32884);
            GLES10.glEnableClientState(32888);
            if (cVar == null) {
                GLES10.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            } else {
                GLES10.glColor4f(cVar.f1555a, cVar.b, cVar.c, cVar.d);
            }
            GLES10.glVertexPointer(2, 5126, 0, this.b.a(this.h));
            GLES10.glTexCoordPointer(2, 5126, 0, this.b.b(this.i));
            b(hVar);
            GLES10.glDrawArrays(5, 0, 4);
            b((h) null);
            GLES10.glDisableClientState(32884);
            GLES10.glDisableClientState(32888);
        }
    }

    @Override // com.topfreegames.engine.b.d
    public void a(RectF rectF, h hVar) {
        a(rectF, hVar, null);
    }

    @Override // com.topfreegames.engine.b.d
    public void a(com.topfreegames.engine.d.g gVar) {
        if (gVar != null) {
            GLES10.glEnableClientState(32884);
            GLES10.glEnableClientState(32888);
            GLES10.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GLES10.glVertexPointer(2, 5126, 0, gVar.a());
            GLES10.glTexCoordPointer(2, 5126, 0, gVar.b());
            b(gVar.c());
            GLES10.glDrawArrays(5, 0, gVar.d());
            b((h) null);
            GLES10.glDisableClientState(32884);
            GLES10.glDisableClientState(32888);
        }
    }

    @Override // com.topfreegames.engine.b.d
    public void a(Rect rect) {
        GLES10.glViewport(rect.left, rect.top, rect.width(), rect.height());
    }

    @Override // com.topfreegames.engine.b.d
    public void a(e eVar) {
        GLES10.glMatrixMode(5888);
        GLES10.glLoadMatrixf(eVar.b(), 0);
    }

    @Override // com.topfreegames.engine.b.d
    public void b(e eVar) {
        GLES10.glMatrixMode(5889);
        GLES10.glLoadMatrixf(eVar.b(), 0);
    }

    @Override // com.topfreegames.engine.b.d
    public void a(boolean z, boolean z2) {
        int i = 16384;
        if (z2) {
            i = 16640;
            GLES10.glClearDepthf(1.0f);
        }
        GLES10.glClear(i);
        this.d = f();
    }

    @Override // com.topfreegames.engine.b.d
    public void e() {
        GLES10.glFlush();
    }

    @Override // com.topfreegames.engine.b.d
    public void a(c cVar) {
        GLES10.glClearColor(cVar.f1555a, cVar.b, cVar.c, cVar.d);
    }

    @Override // com.topfreegames.engine.b.d
    public void a(com.topfreegames.engine.a.b bVar) {
        if (bVar != null) {
            this.f1557a.a(bVar);
        }
    }

    @Override // com.topfreegames.engine.b.d
    public com.topfreegames.engine.a.b c() {
        return this.f1557a;
    }

    public void b(h hVar) {
        if (hVar == null) {
            GLES10.glBindTexture(3553, 0);
        } else {
            GLES10.glBindTexture(3553, hVar.c());
        }
    }

    @Override // com.topfreegames.engine.b.d
    public long f() {
        return System.currentTimeMillis();
    }

    @Override // com.topfreegames.engine.b.d
    public long g() {
        return this.d < 0 ? f() : this.d;
    }

    @Override // com.topfreegames.engine.b.d
    public float d() {
        return this.f1557a.f1553a / this.f1557a.b;
    }

    @Override // com.topfreegames.engine.b.d
    public int h() {
        return this.e;
    }
}

package com.chartboost.sdk.impl;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class r extends Animation {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float f487a;
    private final float b;
    private final float c;
    private final float d;
    private boolean e;
    private Camera f;

    public r(float f, float f2, float f3, float f4, boolean z) {
        this.e = true;
        this.f487a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
        this.e = z;
    }

    @Override // android.view.animation.Animation
    public void initialize(int i, int i2, int i3, int i4) {
        super.initialize(i, i2, i3, i4);
        this.f = new Camera();
    }

    @Override // android.view.animation.Animation
    protected void applyTransformation(float f, Transformation transformation) {
        float f2 = this.f487a + ((this.b - this.f487a) * f);
        Camera camera = this.f;
        Matrix matrix = transformation.getMatrix();
        camera.save();
        if (this.e) {
            camera.rotateY(f2);
        } else {
            camera.rotateX(f2);
        }
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-this.c, -this.d);
        matrix.postTranslate(this.c, this.d);
    }
}

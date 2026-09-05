package com.applovin.impl.adview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class j extends ac {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float f236a;
    private float b;
    private float c;
    private float d;
    private float e;

    public j(Context context) {
        super(context);
        this.f236a = 30.0f;
        this.b = 2.0f;
        this.c = 8.0f;
        this.d = 2.0f;
        this.e = 1.25f;
    }

    protected float a() {
        return this.f236a * this.e;
    }

    @Override // com.applovin.impl.adview.ac
    public void a(float f) {
        this.e = f;
    }

    protected float b() {
        return this.c * this.e;
    }

    protected float c() {
        return this.d * this.e;
    }

    protected float d() {
        return a() / 2.0f;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float fD = d();
        Paint paint = new Paint(1);
        paint.setColor(-16711681);
        paint.setARGB(80, 0, 0, 0);
        canvas.drawCircle(fD, fD, fD, paint);
        Paint paint2 = new Paint(1);
        paint2.setColor(-1);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(c());
        float fB = b();
        float fA = a() - fB;
        canvas.drawLine(fB, fB, fA, fA, paint2);
        canvas.drawLine(fB, fA, fA, fB, paint2);
    }
}

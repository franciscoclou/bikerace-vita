package com.applovin.impl.adview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class k extends ac {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float f237a;
    private float b;
    private float c;
    private float d;
    private float e;

    public k(Context context) {
        super(context);
        this.f237a = 30.0f;
        this.b = 2.0f;
        this.c = 10.0f;
        this.d = 3.0f;
        this.e = 1.25f;
    }

    protected float a() {
        return this.f237a * this.e;
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

    protected float e() {
        return this.b * this.e;
    }

    protected float f() {
        return d() - e();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float fD = d();
        Paint paint = new Paint(1);
        paint.setColor(-1);
        canvas.drawCircle(fD, fD, fD, paint);
        Paint paint2 = new Paint(1);
        paint2.setColor(-16777216);
        canvas.drawCircle(fD, fD, f(), paint2);
        Paint paint3 = new Paint(paint);
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setStrokeWidth(c());
        float fB = b();
        float fA = a() - fB;
        canvas.drawLine(fB, fB, fA, fA, paint3);
        canvas.drawLine(fB, fA, fA, fB, paint3);
    }
}

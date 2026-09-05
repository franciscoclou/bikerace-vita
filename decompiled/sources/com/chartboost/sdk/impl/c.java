package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class c extends LinearLayout implements h.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected View.OnClickListener f452a;
    private RectF b;
    private Paint c;
    private Paint d;
    private RectF e;

    public c(Context context) {
        super(context);
        this.b = new RectF();
        this.c = null;
        this.d = null;
        this.e = null;
        this.f452a = null;
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onDraw(Canvas canvas) {
        this.b.set(0.0f, 0.0f, getWidth(), a());
        a(canvas, this.b);
        b(canvas, this.b);
    }

    private void a(Canvas canvas, RectF rectF) {
        if (this.d == null) {
            this.d = new Paint();
            this.d.setStyle(Paint.Style.FILL);
            this.d.setAntiAlias(true);
        }
        if (this.e == null || Math.abs(rectF.top - this.e.top) > 0.1f || Math.abs(rectF.bottom - this.e.bottom) > 0.1f) {
            this.d.setShader(new LinearGradient(0.0f, rectF.top, 0.0f, rectF.bottom, -1447447, -2302756, Shader.TileMode.CLAMP));
        }
        canvas.drawRect(rectF, this.d);
    }

    private void b(Canvas canvas, RectF rectF) {
        if (this.c == null) {
            this.c = new Paint();
            this.c.setStyle(Paint.Style.FILL);
            this.c.setAntiAlias(true);
        }
        this.c.setColor(-723724);
        canvas.drawRect(rectF.left, rectF.top, rectF.right, rectF.top + 1.0f, this.c);
        this.c.setColor(-3355444);
        canvas.drawRect(rectF.left, rectF.bottom - 1.0f, rectF.right, rectF.bottom, this.c);
    }
}

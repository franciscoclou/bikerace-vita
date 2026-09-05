package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.widget.Button;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class f extends Button {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Path f453a;
    private Path b;
    private Path c;
    private RectF d;
    private Paint e;
    private Paint f;
    private Shader g;
    private Shader h;
    private int i;

    public f(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        float f = context.getResources().getDisplayMetrics().density;
        setTextSize(2, 13.0f);
        setShadowLayer(1.0f * f, 1.0f * f, 1.0f * f, -16757901);
        setTypeface(null, 1);
        setTextColor(-1);
        setClickable(true);
        setGravity(17);
        setPadding(Math.round(12.0f * f), Math.round(5.0f * f), Math.round(12.0f * f), Math.round(f * 5.0f));
        this.f453a = new Path();
        this.b = new Path();
        this.c = new Path();
        this.d = new RectF();
        this.e = new Paint();
        this.e.setStyle(Paint.Style.STROKE);
        this.e.setColor(-4496384);
        this.e.setAntiAlias(true);
        this.i = -1;
        this.f = new Paint();
        this.f.setStyle(Paint.Style.FILL);
        this.f.setAntiAlias(true);
        setBackgroundDrawable(new Drawable() { // from class: com.chartboost.sdk.impl.f.1

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            boolean f454a = false;

            @Override // android.graphics.drawable.Drawable
            public void draw(Canvas canvas) {
                float f2 = f.this.getContext().getResources().getDisplayMetrics().density;
                boolean z = false;
                for (int i : getState()) {
                    if (i == 16842919) {
                        z = true;
                    }
                }
                float f3 = 6.0f * f2;
                f.this.f453a.reset();
                f.this.d.set(getBounds());
                f.this.f453a.addRoundRect(f.this.d, f3, f3, Path.Direction.CW);
                f.this.a();
                f.this.f.setShader(z ? f.this.h : f.this.g);
                canvas.drawPath(f.this.f453a, f.this.f);
                float f4 = 1.0f * f2;
                f.this.b.reset();
                f.this.d.inset(f4 / 2.0f, f4 / 2.0f);
                f.this.b.addRoundRect(f.this.d, f3, f3, Path.Direction.CW);
                f.this.c.reset();
                f.this.d.offset(0.0f, f4 / 2.0f);
                f.this.c.addRoundRect(f.this.d, f3, f3, Path.Direction.CW);
                if (!z) {
                    f.this.e.setColor(-1);
                    canvas.drawPath(f.this.c, f.this.e);
                }
                f.this.e.setColor(-4496384);
                canvas.drawPath(f.this.b, f.this.e);
            }

            @Override // android.graphics.drawable.Drawable
            public void setAlpha(int i) {
                f.this.e.setAlpha(i);
                f.this.f.setAlpha(i);
            }

            @Override // android.graphics.drawable.Drawable
            public void setColorFilter(ColorFilter colorFilter) {
                f.this.e.setColorFilter(colorFilter);
                f.this.f.setColorFilter(colorFilter);
            }

            @Override // android.graphics.drawable.Drawable
            public int getOpacity() {
                return 1;
            }

            @Override // android.graphics.drawable.Drawable
            protected boolean onStateChange(int[] iArr) {
                boolean z = false;
                for (int i : iArr) {
                    if (i == 16842919) {
                        z = true;
                    }
                }
                if (this.f454a == z) {
                    return false;
                }
                this.f454a = z;
                invalidateSelf();
                return true;
            }

            @Override // android.graphics.drawable.Drawable
            public boolean isStateful() {
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        int height = getHeight();
        if (height != this.i) {
            this.i = height;
            this.g = new LinearGradient(0.0f, 0.0f, 0.0f, getHeight(), new int[]{-81366, -89600, -97280}, (float[]) null, Shader.TileMode.CLAMP);
            this.h = new LinearGradient(0.0f, 0.0f, 0.0f, getHeight(), new int[]{-97280, -89600, -81366}, (float[]) null, Shader.TileMode.CLAMP);
        }
    }
}

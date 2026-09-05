package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.view.View;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBOrientation;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class w extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Handler f496a;
    private float b;
    private long c;
    private Paint d;
    private Paint e;
    private Path f;
    private Path g;
    private RectF h;
    private RectF i;
    private Bitmap j;
    private Canvas k;
    private Runnable l;

    public w(Context context) {
        super(context);
        this.j = null;
        this.k = null;
        this.l = new Runnable() { // from class: com.chartboost.sdk.impl.w.1
            @Override // java.lang.Runnable
            public void run() {
                CBOrientation.Difference forcedOrientationDifference = Chartboost.sharedChartboost().getForcedOrientationDifference();
                float f = w.this.getContext().getResources().getDisplayMetrics().density;
                w.this.b += 1.0f * f;
                float width = (forcedOrientationDifference.isOdd() ? w.this.getWidth() : w.this.getHeight()) - (f * 9.0f);
                if (w.this.b > width) {
                    w.this.b -= width * 2.0f;
                }
                if (w.this.getWindowVisibility() == 0) {
                    w.this.invalidate();
                }
            }
        };
        a(context);
    }

    private void a(Context context) {
        float f = context.getResources().getDisplayMetrics().density;
        this.b = 0.0f;
        this.f496a = new Handler();
        this.c = (long) (System.nanoTime() / 1000000.0d);
        this.d = new Paint();
        this.d.setColor(-1);
        this.d.setStyle(Paint.Style.STROKE);
        this.d.setStrokeWidth(f * 3.0f);
        this.d.setAntiAlias(true);
        this.e = new Paint();
        this.e.setColor(-1);
        this.e.setStyle(Paint.Style.FILL);
        this.e.setAntiAlias(true);
        this.f = new Path();
        this.g = new Path();
        this.i = new RectF();
        this.h = new RectF();
        try {
            Method method = getClass().getMethod("setLayerType", Integer.TYPE, Paint.class);
            Object[] objArr = new Object[2];
            objArr[0] = 1;
            method.invoke(this, objArr);
        } catch (Exception e) {
        }
    }

    private boolean a(Canvas canvas) {
        try {
            return ((Boolean) Canvas.class.getMethod("isHardwareAccelerated", new Class[0]).invoke(canvas, new Object[0])).booleanValue();
        } catch (Exception e) {
            return false;
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Canvas canvas2;
        float f = getContext().getResources().getDisplayMetrics().density;
        boolean zA = a(canvas);
        if (!zA) {
            canvas2 = canvas;
            canvas = null;
        } else {
            if (this.j == null || this.j.getWidth() != canvas.getWidth() || this.j.getHeight() != canvas.getHeight()) {
                if (this.j != null && !this.j.isRecycled()) {
                    this.j.recycle();
                }
                try {
                    this.j = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
                    this.k = new Canvas(this.j);
                } catch (Throwable th) {
                    return;
                }
            }
            this.j.eraseColor(0);
            canvas2 = this.k;
        }
        CBOrientation.Difference forcedOrientationDifference = Chartboost.sharedChartboost().getForcedOrientationDifference();
        canvas2.save();
        if (forcedOrientationDifference.isReverse()) {
            canvas2.rotate(180.0f, getWidth() / 2.0f, getHeight() / 2.0f);
        }
        this.h.set(0.0f, 0.0f, getWidth(), getHeight());
        this.h.inset(1.5f * f, 1.5f * f);
        float width = (forcedOrientationDifference.isOdd() ? getWidth() : getHeight()) / 2.0f;
        canvas2.drawRoundRect(this.h, width, width, this.d);
        this.i.set(this.h);
        this.i.inset(3.0f * f, f * 3.0f);
        float fWidth = (forcedOrientationDifference.isOdd() ? this.i.width() : this.i.height()) / 2.0f;
        this.f.reset();
        this.f.addRoundRect(this.i, fWidth, fWidth, Path.Direction.CW);
        float fWidth2 = forcedOrientationDifference.isOdd() ? this.i.width() : this.i.height();
        this.g.reset();
        if (forcedOrientationDifference.isOdd()) {
            this.g.moveTo(fWidth2, 0.0f);
            this.g.lineTo(fWidth2, fWidth2);
            this.g.lineTo(0.0f, fWidth2 * 2.0f);
            this.g.lineTo(0.0f, fWidth2);
        } else {
            this.g.moveTo(0.0f, fWidth2);
            this.g.lineTo(fWidth2, fWidth2);
            this.g.lineTo(fWidth2 * 2.0f, 0.0f);
            this.g.lineTo(fWidth2, 0.0f);
        }
        this.g.close();
        canvas2.save();
        boolean z = true;
        try {
            canvas2.clipPath(this.f);
        } catch (UnsupportedOperationException e) {
            z = false;
        }
        if (z) {
            float f2 = -fWidth2;
            float f3 = this.b;
            while (true) {
                f2 += f3;
                if (f2 >= (forcedOrientationDifference.isOdd() ? this.i.height() : this.i.width()) + fWidth2) {
                    break;
                }
                float f4 = (forcedOrientationDifference.isOdd() ? this.i.top : this.i.left) + f2;
                canvas2.save();
                if (forcedOrientationDifference.isOdd()) {
                    canvas2.translate(this.i.left, f4);
                } else {
                    canvas2.translate(f4, this.i.top);
                }
                canvas2.drawPath(this.g, this.e);
                canvas2.restore();
                f3 = 2.0f * fWidth2;
            }
        }
        canvas2.restore();
        canvas2.restore();
        if (zA && canvas != null) {
            canvas.drawBitmap(this.j, 0.0f, 0.0f, (Paint) null);
        }
        long jMax = Math.max(0L, 16 - (((long) (System.nanoTime() / 1000000.0d)) - this.c));
        this.f496a.removeCallbacks(this.l);
        this.f496a.postDelayed(this.l, jMax);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.f496a.removeCallbacks(this.l);
        if (i == 0) {
            this.f496a.post(this.l);
        }
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        this.f496a.removeCallbacks(this.l);
        this.f496a.post(this.l);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        this.f496a.removeCallbacks(this.l);
        if (this.j != null && !this.j.isRecycled()) {
            this.j.recycle();
        }
        this.j = null;
    }
}

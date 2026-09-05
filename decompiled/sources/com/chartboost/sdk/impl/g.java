package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class g extends ImageView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private RectF f455a;
    private Paint b;
    private Xfermode c;

    public g(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        float f = getContext().getResources().getDisplayMetrics().density;
        this.c = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        this.f455a = new RectF();
        this.b = new Paint();
        this.b.setStyle(Paint.Style.STROKE);
        this.b.setColor(-1509949440);
        this.b.setStrokeWidth(Math.max(1.0f, f * 1.0f));
        this.b.setAntiAlias(true);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        float f = getContext().getResources().getDisplayMetrics().density;
        float f2 = 10.0f * f;
        float f3 = 1.0f * f;
        Drawable drawable = getDrawable();
        if (drawable instanceof BitmapDrawable) {
            Paint paint = ((BitmapDrawable) drawable).getPaint();
            this.f455a.set(drawable.getBounds());
            if (getImageMatrix() != null) {
                getImageMatrix().mapRect(this.f455a);
            }
            int iSaveLayer = canvas.saveLayer(this.f455a, null, 31);
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(-16777216);
            canvas.drawRoundRect(this.f455a, f2, f2, paint);
            Xfermode xfermode = paint.getXfermode();
            paint.setXfermode(this.c);
            super.onDraw(canvas);
            paint.setXfermode(xfermode);
            canvas.restoreToCount(iSaveLayer);
        } else {
            super.onDraw(canvas);
        }
        this.f455a.set(0.0f, 0.0f, getWidth(), getHeight());
        this.f455a.inset(f3 / 2.0f, f3 / 2.0f);
        canvas.drawRoundRect(this.f455a, f2, f2, this.b);
    }
}

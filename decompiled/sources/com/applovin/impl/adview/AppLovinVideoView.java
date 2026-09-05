package com.applovin.impl.adview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AppLovinVideoView extends VideoView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f218a;
    private int b;

    public AppLovinVideoView(Context context) {
        this(context, null);
    }

    public AppLovinVideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AppLovinVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f218a = 0;
        this.b = 0;
    }

    public void a(int i, int i2) {
        this.f218a = i;
        this.b = i2;
        getHolder().setFixedSize(i, i2);
        try {
            requestLayout();
            invalidate();
        } catch (Exception e) {
        }
    }

    @Override // android.widget.VideoView, android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        int iCeil;
        int iCeil2;
        if (this.f218a <= 0 || this.b <= 0) {
            super.onMeasure(i, i2);
            return;
        }
        float height = this.b / getHeight();
        float width = this.f218a / getWidth();
        if (height > width) {
            iCeil = (int) Math.ceil(this.b / height);
            iCeil2 = (int) Math.ceil(this.f218a / height);
        } else {
            iCeil = (int) Math.ceil(this.b / width);
            iCeil2 = (int) Math.ceil(this.f218a / width);
        }
        setMeasuredDimension(iCeil2, iCeil);
    }
}

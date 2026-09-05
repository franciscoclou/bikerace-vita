package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class q extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f485a;
    private boolean b;

    public q(Context context) {
        super(context);
        this.f485a = new a(context);
        this.f485a.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        addView(this.f485a);
        setFocusable(false);
    }

    public void a(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(510L);
        alphaAnimation.setFillAfter(true);
        view.startAnimation(alphaAnimation);
    }

    public void a() {
        a(this.f485a);
    }

    public void a(boolean z) {
        this.b = z;
        this.f485a.a();
    }

    class a extends View {
        public a(Context context) {
            super(context);
            setFocusable(false);
        }

        @Override // android.view.View
        protected void onSizeChanged(int i, int i2, int i3, int i4) {
            super.onSizeChanged(i, i2, i3, i4);
            a();
        }

        public void a() {
            GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[]{q.this.b ? -872415232 : -2013265920, q.this.b ? -2013265920 : -872415232});
            gradientDrawable.setGradientType(1);
            gradientDrawable.setGradientRadius(Math.min(getWidth(), getHeight()) / 2.0f);
            gradientDrawable.setGradientCenter(getWidth() / 2.0f, getHeight() / 2.0f);
            setBackgroundDrawable(gradientDrawable);
        }
    }
}

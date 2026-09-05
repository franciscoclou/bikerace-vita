package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Region;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBOrientation;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class v extends AbsoluteLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Matrix f495a;
    private Matrix b;
    private float[] c;
    private View d;
    private RelativeLayout e;

    public v(Context context) {
        super(context);
        this.f495a = new Matrix();
        this.b = new Matrix();
        this.c = new float[2];
        this.e = new RelativeLayout(context);
        addView(this.e, new AbsoluteLayout.LayoutParams(-1, -1, 0, 0));
        this.d = this.e;
    }

    public void a(View view) {
        a(view, new RelativeLayout.LayoutParams(-2, -2));
    }

    public void a(View view, RelativeLayout.LayoutParams layoutParams) {
        if (this.e != null) {
            this.e.addView(view, layoutParams);
            return;
        }
        throw new IllegalStateException("cannot call addViewToContainer() on CBRotatableContainer that was set up with a default view");
    }

    public v(Context context, View view) {
        super(context);
        this.f495a = new Matrix();
        this.b = new Matrix();
        this.c = new float[2];
        addView(view, new AbsoluteLayout.LayoutParams(-1, -1, 0, 0));
        this.d = view;
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        CBOrientation.Difference forcedOrientationDifference = Chartboost.sharedChartboost().getForcedOrientationDifference();
        ViewGroup.LayoutParams layoutParams = this.d.getLayoutParams();
        layoutParams.width = forcedOrientationDifference.isOdd() ? i2 : i;
        if (!forcedOrientationDifference.isOdd()) {
            i = i2;
        }
        layoutParams.height = i;
        this.d.setLayoutParams(layoutParams);
        this.d.measure(View.MeasureSpec.makeMeasureSpec(layoutParams.width, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824));
        this.d.layout(0, 0, layoutParams.width, layoutParams.height);
    }

    @Override // android.widget.AbsoluteLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (Chartboost.sharedChartboost().getForcedOrientationDifference().isOdd()) {
            super.onMeasure(i2, i);
            setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
        } else {
            super.onMeasure(i, i2);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        CBOrientation.Difference forcedOrientationDifference = Chartboost.sharedChartboost().getForcedOrientationDifference();
        int asInt = forcedOrientationDifference.getAsInt();
        if (forcedOrientationDifference == CBOrientation.Difference.ANGLE_0) {
            super.dispatchDraw(canvas);
            return;
        }
        canvas.save();
        canvas.clipRect(0.0f, 0.0f, getWidth(), getHeight(), Region.Op.REPLACE);
        try {
            ViewGroup viewGroup = (ViewGroup) getParent();
            try {
                ViewGroup viewGroup2 = (ViewGroup) viewGroup.getParent();
                if ((viewGroup2 instanceof ScrollView) || (viewGroup2 instanceof HorizontalScrollView)) {
                    viewGroup = viewGroup2;
                }
            } catch (Exception e) {
            }
            int left = getLeft() - viewGroup.getScrollX();
            int top = getTop() - viewGroup.getScrollY();
            canvas.clipRect(0 - left, 0 - top, viewGroup.getWidth() - left, viewGroup.getHeight() - top, Region.Op.INTERSECT);
        } catch (Exception e2) {
        }
        canvas.translate(getWidth() / 2.0f, getHeight() / 2.0f);
        canvas.rotate(asInt);
        if (forcedOrientationDifference.isOdd()) {
            canvas.translate((-getHeight()) / 2.0f, (-getWidth()) / 2.0f);
        } else {
            canvas.translate((-getWidth()) / 2.0f, (-getHeight()) / 2.0f);
        }
        this.f495a = canvas.getMatrix();
        this.f495a.invert(this.b);
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (Chartboost.sharedChartboost().getForcedOrientationDifference() == CBOrientation.Difference.ANGLE_0) {
            return super.dispatchTouchEvent(motionEvent);
        }
        float[] fArr = this.c;
        fArr[0] = motionEvent.getRawX();
        fArr[1] = motionEvent.getRawY();
        this.b.mapPoints(fArr);
        motionEvent.setLocation(fArr[0], fArr[1]);
        return super.dispatchTouchEvent(motionEvent);
    }

    public View a() {
        return this.d;
    }
}

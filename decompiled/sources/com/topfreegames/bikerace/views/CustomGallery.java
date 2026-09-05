package com.topfreegames.bikerace.views;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Gallery;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class CustomGallery extends Gallery {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f1357a;

    public CustomGallery(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public CustomGallery(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomGallery(Context context) {
        super(context);
    }

    @Override // android.widget.Gallery, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (Math.abs(SystemClock.uptimeMillis() - this.f1357a) > 150) {
            super.onLayout(z, i, i2, i3, i4);
        }
    }

    @Override // android.widget.Gallery, android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.f1357a = SystemClock.uptimeMillis();
        return super.onScroll(motionEvent, motionEvent2, f, f2);
    }

    @Override // android.widget.Gallery, android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }
}

package com.heyzap.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ClickThroughLayout extends FrameLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private View f747a;

    public ClickThroughLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        setClickable(true);
        setOnClickListener(new View.OnClickListener() { // from class: com.heyzap.internal.ClickThroughLayout.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ClickThroughLayout.this.f747a != null) {
                    ClickThroughLayout.this.f747a.performClick();
                }
            }
        });
    }

    public void setClickThroughElement(View view) {
        this.f747a = view;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f747a == null) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        this.f747a.onTouchEvent(motionEvent);
        return true;
    }
}

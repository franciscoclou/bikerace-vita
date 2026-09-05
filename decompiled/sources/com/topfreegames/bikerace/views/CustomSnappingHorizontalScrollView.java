package com.topfreegames.bikerace.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import java.util.ArrayList;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class CustomSnappingHorizontalScrollView extends HorizontalScrollView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private GestureDetector f1359a;
    private ArrayList<View> b;
    private LinearLayout c;
    private int d;
    private int e;

    public CustomSnappingHorizontalScrollView(Context context) {
        super(context);
        this.f1359a = null;
        this.b = null;
        this.c = null;
        this.d = -1;
        this.e = -1;
        d();
    }

    public CustomSnappingHorizontalScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1359a = null;
        this.b = null;
        this.c = null;
        this.d = -1;
        this.e = -1;
        d();
    }

    public CustomSnappingHorizontalScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1359a = null;
        this.b = null;
        this.c = null;
        this.d = -1;
        this.e = -1;
        d();
    }

    public void a(View view) {
        if (view != null) {
            this.c.addView(view);
            this.b.add(view);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = this.d;
            view.setLayoutParams(layoutParams);
        }
    }

    public void a() {
        this.b.clear();
        this.c.removeAllViews();
    }

    public void b() {
        int measuredWidth = getMeasuredWidth();
        this.e = this.e < this.b.size() + (-1) ? this.e + 1 : this.b.size() - 1;
        smoothScrollTo(this.e * measuredWidth, 0);
    }

    public void c() {
        int measuredWidth = getMeasuredWidth();
        this.e = this.e > 0 ? this.e - 1 : 0;
        smoothScrollTo(this.e * measuredWidth, 0);
    }

    private void d() {
        this.b = new ArrayList<>();
        this.d = ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getWidth();
        this.c = new LinearLayout(getContext());
        this.c.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.c.setOrientation(0);
        addView(this.c);
        this.f1359a = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.topfreegames.bikerace.views.CustomSnappingHorizontalScrollView.1
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0054 -> B:18:0x001f). Please report as a decompilation issue!!! */
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                boolean z = true;
                try {
                    if (motionEvent.getX() - motionEvent2.getX() > 3.0f && Math.abs(f) > 250.0f) {
                        CustomSnappingHorizontalScrollView.this.b();
                    } else if (motionEvent2.getX() - motionEvent.getX() > 3.0f && Math.abs(f) > 250.0f) {
                        CustomSnappingHorizontalScrollView.this.c();
                    } else {
                        z = false;
                    }
                } catch (Exception e) {
                    Log.d("Fling", "There was an error processing the Fling event:" + e.getMessage());
                }
                return z;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTapEvent(MotionEvent motionEvent) {
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return false;
            }
        });
        setOnTouchListener(new View.OnTouchListener() { // from class: com.topfreegames.bikerace.views.CustomSnappingHorizontalScrollView.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (CustomSnappingHorizontalScrollView.this.f1359a.onTouchEvent(motionEvent)) {
                    return true;
                }
                if (motionEvent.getAction() != 1 && motionEvent.getAction() != 3) {
                    return false;
                }
                int scrollX = CustomSnappingHorizontalScrollView.this.getScrollX();
                int measuredWidth = view.getMeasuredWidth();
                CustomSnappingHorizontalScrollView.this.e = (scrollX + (measuredWidth / 2)) / measuredWidth;
                CustomSnappingHorizontalScrollView.this.smoothScrollTo(CustomSnappingHorizontalScrollView.this.e * measuredWidth, 0);
                return true;
            }
        });
    }
}

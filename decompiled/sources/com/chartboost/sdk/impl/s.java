package com.chartboost.sdk.impl;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ScrollView f488a;
    private HorizontalScrollView b;
    private ViewGroup c;
    private LinearLayout d;
    private BaseAdapter e;
    private List<List<View>> f;
    private List<List<View>> g;
    private List<Integer> h;
    private int i;
    private DataSetObserver j = new DataSetObserver() { // from class: com.chartboost.sdk.impl.s.1
        @Override // android.database.DataSetObserver
        public void onChanged() {
            LinearLayout.LayoutParams layoutParams;
            int childCount = s.this.d.getChildCount();
            for (int i = 0; i < childCount; i++) {
                List list = (List) s.this.g.get(((Integer) s.this.h.get(i)).intValue());
                List list2 = (List) s.this.f.get(((Integer) s.this.h.get(i)).intValue());
                View childAt = s.this.d.getChildAt(i);
                list.remove(childAt);
                list2.add(childAt);
            }
            s.this.h.clear();
            s.this.d.removeAllViews();
            int count = s.this.e.getCount();
            for (int i2 = 0; i2 < count; i2++) {
                int itemViewType = s.this.e.getItemViewType(i2);
                List list3 = (List) s.this.g.get(itemViewType);
                List list4 = (List) s.this.f.get(itemViewType);
                s.this.h.add(Integer.valueOf(itemViewType));
                View view = null;
                if (!list4.isEmpty()) {
                    view = (View) list4.get(0);
                    list4.remove(0);
                }
                View view2 = s.this.e.getView(i2, view, s.this.d);
                list3.add(view2);
                if (s.this.i == 0) {
                    layoutParams = new LinearLayout.LayoutParams(-2, -1);
                } else {
                    layoutParams = new LinearLayout.LayoutParams(-1, -2);
                }
                if (i2 < count - 1) {
                    layoutParams.setMargins(0, 0, 0, 1);
                }
                s.this.d.addView(view2, layoutParams);
            }
            s.this.d.requestLayout();
        }
    };

    public s(Context context, int i) {
        this.d = new LinearLayout(context);
        this.i = i;
        this.d.setOrientation(i);
        if (i == 0) {
            this.c = a(context);
        } else {
            this.c = b(context);
        }
        this.c.addView(this.d);
        this.f = new ArrayList();
        this.g = new ArrayList();
        this.h = new ArrayList();
        this.d.setOnTouchListener(new View.OnTouchListener() { // from class: com.chartboost.sdk.impl.s.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                try {
                    View currentFocus = ((Activity) s.this.d.getContext()).getCurrentFocus();
                    if (currentFocus != null) {
                        ((InputMethodManager) s.this.d.getContext().getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getApplicationWindowToken(), 0);
                    }
                } catch (Exception e) {
                }
                return false;
            }
        });
    }

    private HorizontalScrollView a(Context context) {
        if (this.b == null) {
            this.b = new HorizontalScrollView(context);
            this.b.setFillViewport(true);
        }
        return this.b;
    }

    private ScrollView b(Context context) {
        if (this.f488a == null) {
            this.f488a = new ScrollView(context);
            this.f488a.setFillViewport(true);
        }
        return this.f488a;
    }

    public ViewGroup a() {
        return this.c;
    }

    public void a(BaseAdapter baseAdapter) {
        if (this.e != null) {
            this.e.unregisterDataSetObserver(this.j);
        }
        this.e = baseAdapter;
        this.e.registerDataSetObserver(this.j);
        this.d.removeAllViews();
        this.f.clear();
        this.g.clear();
        for (int i = 0; i < this.e.getViewTypeCount(); i++) {
            this.f.add(new ArrayList());
            this.g.add(new ArrayList());
        }
        this.h.clear();
        this.e.notifyDataSetChanged();
    }

    public void a(int i) {
        if (i != this.i) {
            this.i = i;
            this.d.setOrientation(i);
            this.c.removeView(this.d);
            if (i == 0) {
                this.c = a(d());
            } else {
                this.c = b(d());
            }
            this.c.addView(this.d);
        }
    }

    private Context d() {
        return this.d.getContext();
    }

    public LinearLayout b() {
        return this.d;
    }

    public void c() {
        if (this.c == this.f488a && this.f488a != null) {
            this.f488a.fullScroll(130);
        } else if (this.c == this.b && this.b != null) {
            this.b.fullScroll(66);
        }
    }
}

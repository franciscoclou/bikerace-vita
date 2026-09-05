package com.topfreegames.bikerace.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ShopItemLimitedTimeView extends DynamicLoadView {
    protected View.OnClickListener A;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected TextView f1381a;
    protected ImageView b;
    protected View c;
    protected ToggleButton d;
    protected ViewGroup e;
    protected ViewGroup f;
    protected TextView g;
    protected TextView h;
    protected View i;
    public View j;
    protected ImageView k;
    protected ImageView l;
    protected ViewStub m;
    protected String n;
    protected String o;
    protected int p;
    protected int q;
    protected int r;
    protected boolean s;
    protected boolean t;
    protected boolean u;
    protected int v;
    protected int w;
    protected int x;
    protected int y;
    protected View.OnClickListener z;

    public ShopItemLimitedTimeView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    public ShopItemLimitedTimeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1381a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = "";
        this.o = "";
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = true;
        this.t = false;
        this.u = false;
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.z = null;
        this.A = null;
        a(attributeSet);
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903091, this);
        this.m = (ViewStub) findViewById(2131296662);
    }

    public String getItemID() {
        return new String(this.n);
    }

    public void setPurchaseListener(View.OnClickListener onClickListener) {
        if (b()) {
            this.z = onClickListener;
            this.c.setOnClickListener(this.z);
        }
    }

    public void setSelectListener(View.OnClickListener onClickListener) {
        if (b()) {
            this.A = onClickListener;
            this.d.setOnClickListener(this.A);
        }
    }

    public void setPurchasable(boolean z) {
        if (b()) {
            this.s = z;
            if (this.s) {
                this.c.setVisibility(0);
                this.j.setOnClickListener(this.z);
            } else {
                this.c.setVisibility(8);
                this.j.setOnClickListener(this.A);
            }
        }
    }

    public void setSelectable(boolean z) {
        if (b()) {
            this.t = z;
            if (this.t) {
                this.d.setVisibility(0);
                this.j.setOnClickListener(this.A);
            } else {
                this.d.setVisibility(8);
                this.j.setOnClickListener(this.z);
            }
        }
    }

    @Override // android.view.View
    public void setSelected(boolean z) {
        if (b() && this.u) {
            this.d.setChecked(z);
        }
    }

    public void setItemPurchased(boolean z) {
        if (b()) {
            this.u = z;
            if (this.u) {
                setPurchasable(false);
                setSelectable(true);
                this.i.setVisibility(8);
            } else {
                setPurchasable(true);
                setSelectable(false);
            }
        }
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        if (b()) {
            super.setVisibility(i);
            if (this.s && !this.u) {
                this.c.setVisibility(i);
            } else {
                this.c.setVisibility(8);
            }
            if (this.t) {
                this.d.setVisibility(i);
            } else {
                this.d.setVisibility(8);
            }
        }
    }

    private void a(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, com.topfreegames.b.b.ShopItemView);
        this.n = typedArrayObtainStyledAttributes.getString(0);
        this.o = typedArrayObtainStyledAttributes.getString(1);
        this.p = typedArrayObtainStyledAttributes.getResourceId(2, 0);
        this.q = typedArrayObtainStyledAttributes.getResourceId(3, 0);
        this.s = typedArrayObtainStyledAttributes.getBoolean(5, true);
        this.t = typedArrayObtainStyledAttributes.getBoolean(6, false);
        typedArrayObtainStyledAttributes.recycle();
        TypedArray typedArrayObtainStyledAttributes2 = getContext().obtainStyledAttributes(attributeSet, com.topfreegames.b.b.ShopItemLimitedBikeView);
        this.v = typedArrayObtainStyledAttributes2.getResourceId(0, 0);
        this.w = typedArrayObtainStyledAttributes2.getResourceId(1, 0);
        this.r = typedArrayObtainStyledAttributes2.getResourceId(2, 0);
        this.x = typedArrayObtainStyledAttributes2.getColor(3, 0);
        this.y = typedArrayObtainStyledAttributes2.getColor(4, 0);
        typedArrayObtainStyledAttributes2.recycle();
    }

    public void a(com.topfreegames.bikerace.a.c cVar, boolean z) {
        if (b() && cVar != null) {
            this.f.removeAllViews();
            if (z) {
                cVar.a(true);
            }
            boolean z2 = this.u || cVar.d() || cVar.c() <= 0;
            if (z2) {
                this.f.removeAllViews();
                for (com.topfreegames.bikerace.a.a aVar : cVar.b()) {
                    this.f.addView(new s(getContext(), aVar, !z2, a(aVar)));
                }
            }
        }
    }

    public void a(com.topfreegames.bikerace.a.c cVar, boolean z, View.OnClickListener onClickListener) {
        if (b() && cVar != null) {
            this.f.removeAllViews();
            if (z) {
                cVar.a(true);
            }
            boolean z2 = this.u || cVar.d() || cVar.c() <= 0;
            if (z2) {
                this.f.removeAllViews();
                for (com.topfreegames.bikerace.a.a aVar : cVar.b()) {
                    if (aVar.a().equals("AchievEasterEggs")) {
                        this.f.addView(new s(getContext(), aVar, !z2, a(aVar), onClickListener));
                    } else {
                        this.f.addView(new s(getContext(), aVar, !z2, a(aVar)));
                    }
                }
            }
        }
    }

    private static boolean a(com.topfreegames.bikerace.a.a aVar) {
        return "AchievDieXTimes".equals(aVar.a()) || "AchievMultiplayerWinsLastLife".equals(aVar.a()) || "AchievEasterEggs".equals(aVar.a());
    }

    public void a(String str) {
        if (b()) {
            this.g.setText(str);
        }
    }

    public void setExpired(String str) {
        if (b()) {
            this.g.setText(str);
            this.h.setVisibility(8);
            this.e.setVisibility(8);
        }
    }

    public void d() {
        if (b()) {
            this.c.setVisibility(8);
        }
    }

    @Override // com.topfreegames.bikerace.views.DynamicLoadView
    protected void c() {
        this.m.inflate();
        this.f1381a = (TextView) findViewById(2131296663);
        this.b = (ImageView) findViewById(2131296666);
        this.c = findViewById(2131296673);
        this.d = (ToggleButton) findViewById(2131296675);
        this.e = (ViewGroup) findViewById(2131296670);
        this.f = (ViewGroup) findViewById(2131296671);
        this.i = findViewById(2131296667);
        this.g = (TextView) findViewById(2131296669);
        this.h = (TextView) findViewById(2131296668);
        this.j = findViewById(2131296661);
        this.k = (ImageView) findViewById(2131296665);
        this.l = (ImageView) findViewById(2131296664);
        this.f1381a.setText(String.valueOf(this.o) + " ");
        this.b.setImageDrawable(getContext().getResources().getDrawable(this.p));
        if (this.v != 0) {
            this.j.setBackgroundResource(this.v);
        }
        if (this.w != 0) {
            this.k.setImageResource(this.w);
        }
        if (this.r != 0) {
            this.l.setBackgroundResource(this.r);
        }
        this.f1381a.setTextColor(this.x);
        setPurchasable(this.s);
        setSelectable(this.t);
    }
}

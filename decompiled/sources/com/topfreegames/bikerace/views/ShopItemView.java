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
public class ShopItemView extends DynamicLoadView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected TextView f1383a;
    protected ImageView b;
    protected TextView c;
    protected View d;
    protected ToggleButton e;
    protected ViewGroup f;
    protected ViewGroup g;
    protected ViewGroup h;
    protected TextView i;
    protected View j;
    protected View k;
    protected ViewStub l;
    protected String m;
    protected String n;
    protected int o;
    protected int p;
    protected String q;
    protected boolean r;
    protected boolean s;
    protected boolean t;
    protected boolean u;
    protected View.OnClickListener v;
    protected View.OnClickListener w;

    public ShopItemView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    public ShopItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1383a = null;
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
        this.m = "";
        this.n = "";
        this.o = 0;
        this.p = 0;
        this.q = "";
        this.r = true;
        this.s = false;
        this.t = false;
        this.u = true;
        this.v = null;
        this.w = null;
        a(attributeSet);
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903093, this);
        this.l = (ViewStub) findViewById(2131296678);
        this.k = findViewById(2131296677);
    }

    public String getItemID() {
        return new String(this.m);
    }

    public void setPurchaseListener(View.OnClickListener onClickListener) {
        if (b()) {
            this.v = onClickListener;
            this.d.setOnClickListener(this.v);
        }
    }

    public void setSelectListener(View.OnClickListener onClickListener) {
        if (b()) {
            this.w = onClickListener;
            this.e.setOnClickListener(this.w);
        }
    }

    public void setPurchasable(boolean z) {
        if (b()) {
            this.r = z;
            if (this.r) {
                this.d.setVisibility(0);
                this.j.setOnClickListener(this.v);
            } else {
                this.d.setVisibility(8);
                this.j.setOnClickListener(this.w);
            }
        }
    }

    public void setSelectable(boolean z) {
        if (b()) {
            this.s = z;
            if (this.s) {
                this.e.setVisibility(0);
                this.j.setOnClickListener(this.w);
            } else {
                this.e.setVisibility(8);
                this.j.setOnClickListener(this.v);
            }
        }
    }

    private void setRevealed(boolean z) {
        if (b()) {
            this.u = z;
            if (this.u) {
                this.b.setImageDrawable(getContext().getResources().getDrawable(this.o));
                if (this.q == null || this.q.equals("")) {
                    this.g.setVisibility(0);
                    this.f.setVisibility(0);
                    this.i.setVisibility(8);
                    return;
                }
                return;
            }
            this.b.setImageDrawable(getContext().getResources().getDrawable(this.p));
            this.g.setVisibility(8);
            this.f.setVisibility(8);
            this.i.setVisibility(0);
        }
    }

    public void setItemPurchased(boolean z) {
        if (b()) {
            this.t = z;
            if (this.t) {
                setPurchasable(false);
                setSelectable(true);
                setRevealed(true);
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
            if (this.r && !this.t) {
                this.d.setVisibility(i);
            } else {
                this.d.setVisibility(8);
            }
            if (this.s) {
                this.e.setVisibility(i);
            } else {
                this.e.setVisibility(8);
            }
        }
    }

    @Override // android.view.View
    public void setSelected(boolean z) {
        if (b() && this.t) {
            this.e.setChecked(z);
        }
    }

    private void a(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, com.topfreegames.b.b.ShopItemView);
        this.m = typedArrayObtainStyledAttributes.getString(0);
        this.n = typedArrayObtainStyledAttributes.getString(1);
        this.o = typedArrayObtainStyledAttributes.getResourceId(2, 0);
        this.p = typedArrayObtainStyledAttributes.getResourceId(3, 0);
        this.q = typedArrayObtainStyledAttributes.getString(4);
        this.r = typedArrayObtainStyledAttributes.getBoolean(5, true);
        this.s = typedArrayObtainStyledAttributes.getBoolean(6, false);
        this.u = typedArrayObtainStyledAttributes.getBoolean(7, true);
        typedArrayObtainStyledAttributes.recycle();
    }

    public void a(com.topfreegames.bikerace.a.c cVar, boolean z) {
        if (b() && cVar != null) {
            this.g.removeAllViews();
            if (z) {
                cVar.a(true);
            }
            int iC = cVar.c();
            boolean z2 = this.t || cVar.d() || iC <= 0;
            if (z2) {
                this.g.removeAllViews();
                for (com.topfreegames.bikerace.a.a aVar : cVar.b()) {
                    this.g.addView(new s(getContext(), aVar, !z2, a(aVar)));
                }
            } else {
                TextView textView = this.i;
                Context context = getContext();
                Object[] objArr = new Object[2];
                objArr[0] = Integer.valueOf(iC);
                objArr[1] = iC > 1 ? "s" : "";
                textView.setText(context.getString(2131099863, objArr));
            }
            setRevealed(z2);
        }
    }

    private static boolean a(com.topfreegames.bikerace.a.a aVar) {
        return "AchievDieXTimes".equals(aVar.a()) || "AchievMultiplayerWinsLastLife".equals(aVar.a());
    }

    public void a(String str, View.OnClickListener onClickListener) {
        if (b()) {
            this.h.addView(new t(getContext(), str, onClickListener));
        }
    }

    public void d() {
        if (b()) {
            this.h.removeAllViews();
        }
    }

    @Override // com.topfreegames.bikerace.views.DynamicLoadView
    protected void c() {
        this.l.inflate();
        this.f1383a = (TextView) findViewById(2131296679);
        this.b = (ImageView) findViewById(2131296681);
        this.c = (TextView) findViewById(2131296682);
        this.d = findViewById(2131296687);
        this.e = (ToggleButton) findViewById(2131296675);
        this.f = (ViewGroup) findViewById(2131296684);
        this.g = (ViewGroup) findViewById(2131296685);
        this.h = (ViewGroup) findViewById(2131296686);
        this.i = (TextView) findViewById(2131296683);
        this.e = (ToggleButton) findViewById(2131296675);
        this.j = findViewById(2131296676);
        this.f1383a.setText(String.valueOf(this.n) + " ");
        if (this.q != null || !this.q.equals("")) {
            this.c.setText(this.q);
            this.g.setVisibility(8);
            this.f.setVisibility(8);
        }
        if (this.u) {
            this.b.setImageDrawable(getContext().getResources().getDrawable(this.p));
        } else {
            this.b.setImageDrawable(getContext().getResources().getDrawable(this.o));
        }
        setPurchasable(this.r);
        setSelectable(this.s);
        this.k.setVisibility(8);
    }

    public void setEnabledAndClickable(boolean z) {
        if (b()) {
            this.j.setEnabled(z);
            this.j.setClickable(z);
        }
    }
}

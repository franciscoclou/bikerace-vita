package com.topfreegames.bikerace.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class WorldItemView extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected View f1389a;
    protected ImageView b;
    protected TextView c;
    protected ImageView d;
    protected ImageView e;
    protected ImageView f;
    protected boolean g;
    protected boolean h;
    protected boolean i;
    protected int j;
    protected int k;
    protected int l;
    protected int m;
    protected boolean n;
    protected boolean o;
    protected boolean p;
    protected View.OnClickListener q;

    public WorldItemView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    public WorldItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1389a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = false;
        this.h = false;
        this.i = false;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = -1;
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = null;
        a(attributeSet);
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903103, this);
        this.f1389a = findViewById(2131296754);
        this.b = (ImageView) findViewById(2131296756);
        this.c = (TextView) findViewById(2131296757);
        this.d = (ImageView) findViewById(2131296758);
        this.e = (ImageView) findViewById(2131296759);
        this.f = (ImageView) findViewById(2131296760);
        setImage(this.j);
        setVisibility(this.g ? 8 : 0);
        setLocked(this.h);
        setNumberTwo(this.n);
        setNumberThree(this.o);
        a();
        this.f.setVisibility(this.p ? 0 : 8);
    }

    public void setCurrentStars(int i) {
        this.k = i;
        a();
    }

    public void setMaxStars(int i) {
        this.l = i;
        a();
    }

    public void setImage(int i) {
        this.j = i;
        this.b.setImageDrawable(getContext().getResources().getDrawable(this.j));
    }

    public int getWorldID() {
        return this.m;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.q = onClickListener;
        this.f1389a.setOnClickListener(onClickListener);
        this.b.setOnClickListener(onClickListener);
        this.c.setOnClickListener(onClickListener);
        this.e.setOnClickListener(onClickListener);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        this.f1389a.setVisibility(i);
        this.f1389a.setVisibility(i);
        this.b.setVisibility(i);
        TextView textView = this.c;
        if (this.i) {
            i = 8;
        }
        textView.setVisibility(i);
        this.e.setVisibility((this.n || this.o) ? 0 : 8);
    }

    public void setLocked(boolean z) {
        this.h = z;
        if (z) {
            this.d.setVisibility(0);
        } else {
            this.d.setVisibility(8);
        }
    }

    public void setNumberTwo(boolean z) {
        if (z) {
            this.e.setVisibility(0);
            this.e.setImageResource(2130837848);
        }
    }

    public void setNumberThree(boolean z) {
        if (z) {
            this.e.setVisibility(0);
            this.e.setImageResource(2130837849);
        }
    }

    public void a(boolean z) {
        if (!z) {
            this.f.setVisibility(8);
        }
    }

    private void a(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, com.topfreegames.b.b.WorldItemView);
        this.m = typedArrayObtainStyledAttributes.getInteger(0, 0);
        this.g = typedArrayObtainStyledAttributes.getBoolean(4, false);
        this.j = typedArrayObtainStyledAttributes.getResourceId(1, 0);
        this.k = typedArrayObtainStyledAttributes.getInt(2, 0);
        this.l = typedArrayObtainStyledAttributes.getInt(3, 0);
        this.h = typedArrayObtainStyledAttributes.getBoolean(6, false);
        this.i = typedArrayObtainStyledAttributes.getBoolean(5, false);
        this.n = typedArrayObtainStyledAttributes.getBoolean(7, false);
        this.o = typedArrayObtainStyledAttributes.getBoolean(8, false);
        this.p = typedArrayObtainStyledAttributes.getBoolean(9, false);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void a() {
        if (this.l > 0 && !this.i) {
            this.c.setText(String.valueOf(this.k) + "/" + this.l);
            this.c.setVisibility(0);
        } else {
            this.c.setVisibility(8);
        }
    }
}

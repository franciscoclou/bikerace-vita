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
public class WorldItemView_Halloween extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected View f1391a;
    protected ImageView b;
    protected TextView c;
    protected ImageView d;
    protected ImageView e;
    protected boolean f;
    protected boolean g;
    protected boolean h;
    protected int i;
    protected int j;
    protected int k;
    protected int l;
    protected boolean m;
    protected View.OnClickListener n;

    public WorldItemView_Halloween(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    public WorldItemView_Halloween(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1391a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = -1;
        this.m = false;
        this.n = null;
        a(attributeSet);
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903105, this);
        this.f1391a = findViewById(2131296754);
        this.b = (ImageView) findViewById(2131296756);
        this.c = (TextView) findViewById(2131296757);
        this.d = (ImageView) findViewById(2131296758);
        this.e = (ImageView) findViewById(2131296759);
        setImage(this.i);
        if (this.f) {
            setVisibility(8);
        } else {
            setVisibility(0);
        }
        setLocked(this.g);
        setNumberTwo(this.m);
        a();
    }

    public void setCurrentStars(int i) {
        this.j = i;
        a();
    }

    public void setMaxStars(int i) {
        this.k = i;
        a();
    }

    public void setImage(int i) {
        this.i = i;
        this.b.setImageDrawable(getContext().getResources().getDrawable(this.i));
    }

    public int getWorldID() {
        return this.l;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.n = onClickListener;
        this.f1391a.setOnClickListener(onClickListener);
        this.b.setOnClickListener(onClickListener);
        this.c.setOnClickListener(onClickListener);
        this.e.setOnClickListener(onClickListener);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        this.f1391a.setVisibility(i);
        this.f1391a.setVisibility(i);
        this.b.setVisibility(i);
        this.e.setVisibility(i);
        if (this.h) {
            this.c.setVisibility(8);
        } else {
            this.c.setVisibility(i);
        }
    }

    public void setLocked(boolean z) {
        this.g = z;
        if (z) {
            this.d.setVisibility(0);
        } else {
            this.d.setVisibility(8);
        }
    }

    public void setNumberTwo(boolean z) {
        this.m = z;
        if (z) {
            this.e.setVisibility(0);
        } else {
            this.e.setVisibility(8);
        }
    }

    private void a(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, com.topfreegames.b.b.WorldItemView);
        this.l = typedArrayObtainStyledAttributes.getInteger(0, 0);
        this.f = typedArrayObtainStyledAttributes.getBoolean(4, false);
        this.i = typedArrayObtainStyledAttributes.getResourceId(1, 0);
        this.j = typedArrayObtainStyledAttributes.getInt(2, 0);
        this.k = typedArrayObtainStyledAttributes.getInt(3, 0);
        this.g = typedArrayObtainStyledAttributes.getBoolean(6, false);
        this.h = typedArrayObtainStyledAttributes.getBoolean(5, false);
        this.m = typedArrayObtainStyledAttributes.getBoolean(7, false);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void a() {
        this.c.setText(String.valueOf(this.j) + "/" + this.k);
    }
}

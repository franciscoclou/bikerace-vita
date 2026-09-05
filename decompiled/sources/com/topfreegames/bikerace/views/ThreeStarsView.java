package com.topfreegames.bikerace.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ThreeStarsView extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected int f1386a;
    protected float b;
    protected float c;
    protected boolean d;
    protected boolean e;
    protected boolean f;
    protected boolean g;
    protected View h;
    protected ImageView i;
    protected ImageView j;
    protected ImageView k;

    public ThreeStarsView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    public ThreeStarsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1386a = 0;
        this.b = 0.0f;
        this.c = 0.0f;
        this.d = false;
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        a(attributeSet);
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903100, this);
        this.h = findViewById(2131296731);
        this.i = (ImageView) findViewById(2131296732);
        this.j = (ImageView) findViewById(2131296733);
        this.k = (ImageView) findViewById(2131296734);
        a(this.b, this.c);
        if (this.d) {
            setVisibility(8);
        } else {
            setVisibility(0);
        }
        a(this.f1386a);
    }

    private void a(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, com.topfreegames.b.b.ThreeStarsView);
        this.f1386a = typedArrayObtainStyledAttributes.getInteger(3, 0);
        this.b = typedArrayObtainStyledAttributes.getDimension(0, 10.0f);
        this.c = typedArrayObtainStyledAttributes.getDimension(1, 10.0f);
        this.d = typedArrayObtainStyledAttributes.getBoolean(2, false);
        this.e = typedArrayObtainStyledAttributes.getBoolean(4, false);
        this.f = typedArrayObtainStyledAttributes.getBoolean(5, false);
        this.g = typedArrayObtainStyledAttributes.getBoolean(6, false);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        this.h.setVisibility(i);
        this.i.setVisibility(i);
        this.j.setVisibility(i);
        this.k.setVisibility(i);
    }

    public void a(float f, float f2) {
        int i = (int) (f + 0.5f);
        int i2 = (int) (f2 + 0.5f);
        int i3 = this.g ? 0 : i2 / 4;
        int i4 = this.g ? i2 / 8 : 0;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.i.getLayoutParams();
        layoutParams.setMargins(i4, i3, i4, 0);
        layoutParams.width = i;
        layoutParams.height = i2;
        this.i.setLayoutParams(layoutParams);
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.j.getLayoutParams();
        layoutParams2.width = i;
        layoutParams2.height = i2;
        this.j.setLayoutParams(layoutParams2);
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.k.getLayoutParams();
        layoutParams3.setMargins(i4, i3, i4, 0);
        layoutParams3.width = i;
        layoutParams3.height = i2;
        this.k.setLayoutParams(layoutParams3);
    }

    public void a(int i) {
        if (i <= 3 && i >= 0) {
            int i2 = 2130837881;
            int i3 = 2130837880;
            if (this.e) {
                i3 = 2130837879;
            }
            if (this.f) {
                i2 = 2130837882;
            }
            if (i == 3) {
                this.i.setImageDrawable(getContext().getResources().getDrawable(i2));
                this.j.setImageDrawable(getContext().getResources().getDrawable(i2));
                this.k.setImageDrawable(getContext().getResources().getDrawable(i2));
            } else if (i == 2) {
                this.i.setImageDrawable(getContext().getResources().getDrawable(i2));
                this.j.setImageDrawable(getContext().getResources().getDrawable(i2));
                this.k.setImageDrawable(getContext().getResources().getDrawable(i3));
            } else if (i == 1) {
                this.i.setImageDrawable(getContext().getResources().getDrawable(i2));
                this.j.setImageDrawable(getContext().getResources().getDrawable(i3));
                this.k.setImageDrawable(getContext().getResources().getDrawable(i3));
            } else {
                this.i.setImageDrawable(getContext().getResources().getDrawable(i3));
                this.j.setImageDrawable(getContext().getResources().getDrawable(i3));
                this.k.setImageDrawable(getContext().getResources().getDrawable(i3));
            }
            this.f1386a = i;
        }
    }
}

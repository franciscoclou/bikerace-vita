package com.topfreegames.bikerace.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ShopStatusView extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected TextView f1385a;
    protected ImageView b;
    protected TextView c;
    protected TextView d;
    protected TextView e;
    protected TextView f;

    public ShopStatusView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    public ShopStatusView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1385a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903097, this);
        this.f1385a = (TextView) findViewById(2131296708);
        this.b = (ImageView) findViewById(2131296711);
        this.c = (TextView) findViewById(2131296712);
        this.e = (TextView) findViewById(2131296714);
        this.d = (TextView) findViewById(2131296713);
        this.f = (TextView) findViewById(2131296715);
    }

    public void setAvatar(Bitmap bitmap) {
        this.b.setImageBitmap(bitmap);
    }

    public void a(String str, int i, int i2, int i3, int i4) {
        if (str == null || str.equals("")) {
            str = "Guest";
        }
        this.f1385a.setText(String.valueOf(str) + " ");
        this.c.setText(Integer.toString(i));
        this.e.setText(Integer.toString(i3));
        this.d.setText(Integer.toString(i2));
        if (i4 >= 0) {
            this.f.setText(Integer.toString(i4));
        } else {
            this.f.setText("-");
        }
    }
}

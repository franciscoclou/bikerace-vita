package com.topfreegames.bikerace.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class UserLevelItemView extends RelativeLayout {
    private static Drawable l = null;
    private static Drawable m = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ImageView f1387a;
    private TextView b;
    private View c;
    private View d;
    private ImageView e;
    private TextView f;
    private View g;
    private View h;
    private ImageView i;
    private TextView j;
    private v k;

    public UserLevelItemView(Context context, AttributeSet attributeSet, int i) {
        this(context);
    }

    public UserLevelItemView(Context context) {
        super(context);
        this.f1387a = null;
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
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903058, this);
        this.c = findViewById(2131296312);
        this.d = findViewById(2131296324);
        this.b = (TextView) findViewById(2131296315);
        this.f1387a = (ImageView) findViewById(2131296314);
        if (l == null) {
            l = this.f1387a.getDrawable();
        }
        this.e = (ImageView) findViewById(2131296316);
        if (m == null) {
            m = this.e.getDrawable();
        }
        this.f = (TextView) findViewById(2131296318);
        this.g = findViewById(2131296317);
        this.h = findViewById(2131296319);
        this.i = (ImageView) findViewById(2131296322);
        this.j = (TextView) findViewById(2131296323);
        com.topfreegames.bikerace.activities.n.a(getContext(), this);
    }

    public void a(v vVar, u uVar) {
        this.c.setVisibility((uVar == u.FIRST || uVar == u.SINGLE) ? 0 : 8);
        this.d.setVisibility((uVar == u.LAST || uVar == u.SINGLE) ? 0 : 8);
        this.k = vVar;
        if (this.k.e()) {
            a();
        } else if (this.k.c()) {
            b();
        } else {
            c();
        }
    }

    private void a() {
        this.h.setVisibility(0);
        this.j.setText(getContext().getString(2131099932, com.topfreegames.bikerace.m.f.a(this.k.d().b().toUpperCase())));
        this.f1387a.setVisibility(8);
        this.e.setVisibility(8);
        this.g.setVisibility(8);
        this.f.setVisibility(8);
        this.b.setVisibility(8);
    }

    private void b() {
        this.h.setVisibility(8);
        this.f1387a.setVisibility(0);
        this.e.setVisibility(0);
        this.g.setVisibility(0);
        this.f.setText(String.valueOf(this.k.c.a(getContext())) + " ");
        this.f.setVisibility(0);
        this.b.setVisibility(8);
    }

    private void c() {
        this.h.setVisibility(8);
        this.f1387a.setVisibility(0);
        this.e.setVisibility(8);
        this.g.setVisibility(8);
        this.f.setVisibility(8);
        this.b.setText(String.valueOf(this.k.b()) + " ");
        this.b.setVisibility(0);
    }

    public void a(Bitmap bitmap) {
        if (bitmap == null) {
            this.e.setImageDrawable(m);
            this.i.setImageDrawable(m);
        } else {
            this.e.setImageBitmap(bitmap);
            this.i.setImageBitmap(bitmap);
        }
    }

    public void b(Bitmap bitmap) {
        if (bitmap != null) {
            this.f1387a.setImageBitmap(bitmap);
        } else {
            this.f1387a.setImageDrawable(l);
        }
    }

    public v getData() {
        return this.k;
    }
}

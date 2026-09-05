package com.topfreegames.bikerace.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.topfreegames.bikerace.au;
import java.text.DecimalFormat;
import java.util.ArrayList;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class MultiplayerResultView extends RelativeLayout implements com.topfreegames.e.a.h {
    private static /* synthetic */ int[] v;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected View f1377a;
    protected ImageView b;
    protected ImageView c;
    protected TextView d;
    protected TextView e;
    protected TextView f;
    protected TextView g;
    protected TextView h;
    protected TextView i;
    protected ImageView j;
    protected ImageView k;
    protected ImageView l;
    protected ImageView m;
    protected ImageView n;
    protected ImageView o;
    protected EditText p;
    protected View q;
    protected TextView r;
    protected View s;
    protected String t;
    protected String u;

    static /* synthetic */ int[] b() {
        int[] iArr = v;
        if (iArr == null) {
            iArr = new int[com.topfreegames.bikerace.p.valuesCustom().length];
            try {
                iArr[com.topfreegames.bikerace.p.LOSE.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[com.topfreegames.bikerace.p.SENT.ordinal()] = 4;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[com.topfreegames.bikerace.p.TIE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[com.topfreegames.bikerace.p.WIN.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            v = iArr;
        }
        return iArr;
    }

    public MultiplayerResultView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    public MultiplayerResultView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1377a = null;
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
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        a(attributeSet);
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2130903082, this);
        this.f1377a = findViewById(2131296526);
        this.j = (ImageView) findViewById(2131296529);
        this.b = (ImageView) findViewById(2131296534);
        this.c = (ImageView) findViewById(2131296542);
        this.d = (TextView) findViewById(2131296535);
        this.e = (TextView) findViewById(2131296543);
        this.f = (TextView) findViewById(2131296536);
        this.g = (TextView) findViewById(2131296544);
        this.h = (TextView) findViewById(2131296537);
        this.i = (TextView) findViewById(2131296539);
        this.k = (ImageView) findViewById(2131296533);
        this.l = (ImageView) findViewById(2131296541);
        this.m = (ImageView) findViewById(2131296548);
        this.n = (ImageView) findViewById(2131296545);
        this.o = (ImageView) findViewById(2131296546);
        this.p = (EditText) findViewById(2131296547);
        this.q = findViewById(2131296549);
        this.r = (TextView) findViewById(2131296531);
        this.s = findViewById(2131296530);
    }

    @Override // com.topfreegames.e.a.h
    public void a(Bitmap bitmap, String str) {
        post(new r(this, bitmap, str));
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
    }

    public void setPlayOnClickListener(View.OnClickListener onClickListener) {
        this.m.setOnClickListener(onClickListener);
    }

    public void setReplayOnClickListener(View.OnClickListener onClickListener) {
        this.n.setOnClickListener(onClickListener);
    }

    public void setRetryOnClickListener(View.OnClickListener onClickListener) {
        this.o.setOnClickListener(onClickListener);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        this.f1377a.setVisibility(i);
        this.k.setVisibility(4);
        this.l.setVisibility(4);
    }

    public void a(String str, String str2, float f, int i, String str3, String str4, float f2, int i2, boolean z, com.topfreegames.bikerace.p pVar, boolean z2, boolean z3) {
        this.d.setText(str2);
        a(this.f, f);
        this.h.setText(Integer.toString(i));
        this.e.setText(str4);
        a(this.g, f2);
        this.i.setText(Integer.toString(i2));
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        arrayList.add(str3);
        com.topfreegames.e.a.a.b().a(arrayList, this, this);
        this.t = str;
        this.u = str3;
        super.setVisibility(0);
        this.f1377a.setVisibility(0);
        if (z) {
            this.n.setVisibility(0);
            this.p.setVisibility(4);
            this.o.setVisibility(8);
        } else {
            this.n.setVisibility(8);
            if (au.b()) {
                this.p.setVisibility(0);
            } else {
                this.p.setVisibility(4);
            }
            if (au.a()) {
                this.o.setVisibility(0);
            } else {
                this.o.setVisibility(8);
            }
        }
        int i3 = 0;
        switch (b()[pVar.ordinal()]) {
            case 1:
                i3 = 2130837916;
                this.k.setVisibility(0);
                this.l.setVisibility(4);
                break;
            case 2:
                i3 = 2130837915;
                this.k.setVisibility(4);
                this.l.setVisibility(0);
                break;
            case 3:
                i3 = 2130837901;
                this.k.setVisibility(4);
                this.l.setVisibility(4);
                break;
            case 4:
                i3 = 2130837914;
                this.k.setVisibility(4);
                this.l.setVisibility(4);
                break;
        }
        this.j.setImageResource(i3);
        com.topfreegames.bikerace.worldcup.o oVarA = com.topfreegames.bikerace.worldcup.o.a();
        if (!z3 && oVarA.k()) {
            this.r.setText(getResources().getString(2131099706, Integer.valueOf(com.topfreegames.bikerace.worldcup.o.a(pVar))));
        } else {
            this.s.setVisibility(8);
        }
        com.topfreegames.bikerace.m.a.a((Activity) getContext(), (ViewGroup) this.q);
    }

    public void a() {
        setVisibility(8);
        com.topfreegames.bikerace.m.a.b((Activity) getContext(), (ViewGroup) this.q);
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i != 0) {
            com.topfreegames.e.a.a.b().a(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        com.topfreegames.e.a.a.b().a(this);
    }

    private void a(AttributeSet attributeSet) {
        getContext().obtainStyledAttributes(attributeSet, com.topfreegames.b.b.LevelItemView).recycle();
    }

    private void a(TextView textView, float f) {
        String str;
        if (f < 0.0f) {
            str = "Fail";
        } else if (f == 0.0f) {
            str = "---";
        } else {
            str = String.valueOf(new DecimalFormat("#.00").format(f).toString()) + "s";
        }
        textView.setText(str);
    }
}

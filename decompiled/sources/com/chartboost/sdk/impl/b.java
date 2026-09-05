package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Chartboost;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b extends com.chartboost.sdk.c {
    private static Point m = null;
    public com.chartboost.sdk.Libraries.a.C0000a h;
    public com.chartboost.sdk.Libraries.a.C0000a i;
    public com.chartboost.sdk.Libraries.a.C0000a j;
    public com.chartboost.sdk.Libraries.a.C0000a k;
    public com.chartboost.sdk.Libraries.a.C0000a l;

    /* JADX INFO: Access modifiers changed from: private */
    public Point b(Context context) {
        if (m == null) {
            m = new Point(-((int) (com.chartboost.sdk.Libraries.d.b(10, context) + 0.5f)), -((int) (com.chartboost.sdk.Libraries.d.b(10, context) + 0.5f)));
        }
        return m;
    }

    public class a extends com.chartboost.sdk.c.b {
        public ImageView c;
        public ImageView d;
        public Button e;
        public v f;

        private a(Context context) {
            super(context);
            setBackgroundColor(0);
            setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            this.f = new v(context);
            this.d = new ImageView(context);
            this.e = new Button(context);
            this.c = new ImageView(context);
            this.e.setOnClickListener(new View.OnClickListener() { // from class: com.chartboost.sdk.impl.b.a.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (b.this.f401a != null) {
                        b.this.f401a.a();
                    }
                }
            });
            this.d.setClickable(true);
            this.d.setOnClickListener(new View.OnClickListener() { // from class: com.chartboost.sdk.impl.b.a.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (b.this.b != null) {
                        b.this.b.a(null, null);
                    }
                }
            });
            this.f.a(this.c);
            this.f.a(this.d);
            this.f.a(this.e);
            addView(this.f, new RelativeLayout.LayoutParams(-1, -1));
        }

        /* synthetic */ a(b bVar, Context context, a aVar) {
            this(context);
        }

        @Override // com.chartboost.sdk.c.b
        protected void a(int i, int i2) {
            boolean zIsPortrait = Chartboost.sharedChartboost().getOrientation().isPortrait();
            com.chartboost.sdk.Libraries.a.C0000a c0000a = zIsPortrait ? b.this.h : b.this.i;
            com.chartboost.sdk.Libraries.a.C0000a c0000a2 = zIsPortrait ? b.this.j : b.this.k;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.width = (int) (c0000a2.c() / c0000a2.e());
            layoutParams.height = (int) (c0000a2.d() / c0000a2.e());
            Point pointA = a(zIsPortrait ? "frame-portrait" : "frame-landscape");
            layoutParams.leftMargin = Math.round(((i - layoutParams.width) / 2.0f) + pointA.x);
            layoutParams.topMargin = Math.round(pointA.y + ((i2 - layoutParams.height) / 2.0f));
            this.d.setId(100);
            layoutParams2.width = (int) (c0000a.c() / c0000a.e());
            layoutParams2.height = (int) (c0000a.d() / c0000a.e());
            Point pointA2 = a(zIsPortrait ? "ad-portrait" : "ad-landscape");
            layoutParams2.leftMargin = Math.round(((i - layoutParams2.width) / 2.0f) + pointA2.x);
            layoutParams2.topMargin = Math.round(pointA2.y + ((i2 - layoutParams2.height) / 2.0f));
            layoutParams3.width = (int) (b.this.l.c() / b.this.l.e());
            layoutParams3.height = (int) (b.this.l.d() / b.this.l.e());
            Point pointA3 = a("close");
            Point pointB = b.this.b(getContext());
            layoutParams3.leftMargin = layoutParams2.leftMargin + layoutParams2.width + Math.round(pointA3.x + pointB.x);
            layoutParams3.topMargin = Math.round(pointA3.y + pointB.y) + (layoutParams2.topMargin - layoutParams3.height);
            layoutParams3.leftMargin = Math.min(Math.max(0, layoutParams3.leftMargin), i - layoutParams3.width);
            layoutParams3.topMargin = Math.min(Math.max(0, layoutParams3.topMargin), i2 - layoutParams3.height);
            this.c.setLayoutParams(layoutParams);
            this.d.setLayoutParams(layoutParams2);
            this.e.setLayoutParams(layoutParams3);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(c0000a2.b());
            this.c.setScaleType(ImageView.ScaleType.FIT_CENTER);
            this.c.setImageDrawable(bitmapDrawable);
            BitmapDrawable bitmapDrawable2 = new BitmapDrawable(c0000a.b());
            this.d.setScaleType(ImageView.ScaleType.FIT_CENTER);
            this.d.setImageDrawable(bitmapDrawable2);
            this.e.setBackgroundDrawable(new BitmapDrawable(b.this.l.b()));
        }

        @Override // com.chartboost.sdk.c.b
        public void b() {
            super.b();
            this.d = null;
            this.c = null;
            this.e = null;
        }

        private Point a(String str) {
            JSONObject jSONObjectOptJSONObject;
            JSONObject jSONObjectOptJSONObject2 = b.this.f.optJSONObject(str);
            return (jSONObjectOptJSONObject2 == null || (jSONObjectOptJSONObject = jSONObjectOptJSONObject2.optJSONObject("offset")) == null) ? new Point(0, 0) : new Point(jSONObjectOptJSONObject.optInt("x", 0), jSONObjectOptJSONObject.optInt("y", 0));
        }
    }

    public b(com.chartboost.sdk.impl.a aVar) {
        super(aVar);
        this.e = 5;
        this.j = null;
        this.k = null;
        this.h = null;
        this.i = null;
        this.l = null;
    }

    @Override // com.chartboost.sdk.c
    protected com.chartboost.sdk.c.b a(Context context) {
        return new a(this, context, null);
    }

    @Override // com.chartboost.sdk.c
    public void a(JSONObject jSONObject) {
        super.a(jSONObject);
        o.b bVar = new o.b() { // from class: com.chartboost.sdk.impl.b.1
            @Override // com.chartboost.sdk.impl.o.b
            public void a(com.chartboost.sdk.Libraries.a.C0000a c0000a, Bundle bundle) {
                b.this.i = c0000a;
                b.this.a(c0000a);
            }
        };
        o.b bVar2 = new o.b() { // from class: com.chartboost.sdk.impl.b.2
            @Override // com.chartboost.sdk.impl.o.b
            public void a(com.chartboost.sdk.Libraries.a.C0000a c0000a, Bundle bundle) {
                b.this.h = c0000a;
                b.this.a(c0000a);
            }
        };
        o.b bVar3 = new o.b() { // from class: com.chartboost.sdk.impl.b.3
            @Override // com.chartboost.sdk.impl.o.b
            public void a(com.chartboost.sdk.Libraries.a.C0000a c0000a, Bundle bundle) {
                b.this.k = c0000a;
                b.this.a(c0000a);
            }
        };
        o.b bVar4 = new o.b() { // from class: com.chartboost.sdk.impl.b.4
            @Override // com.chartboost.sdk.impl.o.b
            public void a(com.chartboost.sdk.Libraries.a.C0000a c0000a, Bundle bundle) {
                b.this.j = c0000a;
                b.this.a(c0000a);
            }
        };
        o.b bVar5 = new o.b() { // from class: com.chartboost.sdk.impl.b.5
            @Override // com.chartboost.sdk.impl.o.b
            public void a(com.chartboost.sdk.Libraries.a.C0000a c0000a, Bundle bundle) {
                b.this.l = c0000a;
                b.this.a(c0000a);
            }
        };
        a("ad-landscape", bVar, true);
        a("ad-portrait", bVar2, true);
        a("frame-landscape", bVar3);
        a("frame-portrait", bVar4);
        a("close", bVar5);
    }

    @Override // com.chartboost.sdk.c
    public void c() {
        super.c();
        if (this.i != null) {
            this.i.a();
            this.i = null;
        }
        if (this.h != null) {
            this.h.a();
            this.h = null;
        }
        this.k = null;
        this.j = null;
        this.l = null;
    }
}

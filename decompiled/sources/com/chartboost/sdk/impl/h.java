package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBOrientation;
import com.facebook.internal.ServerProtocol;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class h extends com.chartboost.sdk.c {
    private static int h = 50;
    private static int i = 50;
    private static int j = 30;
    private List<JSONObject> k;
    private com.chartboost.sdk.Libraries.a.C0000a l;
    private com.chartboost.sdk.Libraries.a.C0000a m;
    private com.chartboost.sdk.Libraries.a.C0000a n;
    private SparseArray<com.chartboost.sdk.Libraries.a.C0000a> o;

    public interface b {
        int a();

        void a(JSONObject jSONObject, int i);
    }

    public class a extends com.chartboost.sdk.c.b {
        private static /* synthetic */ int[] k;
        private ImageView d;
        private ImageView e;
        private FrameLayout f;
        private s g;
        private v h;
        private v i;
        private C0007a j;

        static /* synthetic */ int[] c() {
            int[] iArr = k;
            if (iArr == null) {
                iArr = new int[CBOrientation.Difference.valuesCustom().length];
                try {
                    iArr[CBOrientation.Difference.ANGLE_0.ordinal()] = 1;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[CBOrientation.Difference.ANGLE_180.ordinal()] = 3;
                } catch (NoSuchFieldError e2) {
                }
                try {
                    iArr[CBOrientation.Difference.ANGLE_270.ordinal()] = 4;
                } catch (NoSuchFieldError e3) {
                }
                try {
                    iArr[CBOrientation.Difference.ANGLE_90.ordinal()] = 2;
                } catch (NoSuchFieldError e4) {
                }
                k = iArr;
            }
            return iArr;
        }

        private a(Context context) {
            super(context);
            setBackgroundColor(-1842205);
            this.f = new FrameLayout(context);
            this.e = new ImageView(context);
            this.d = new ImageView(context);
            this.g = new s(context, Chartboost.sharedChartboost().getForcedOrientationDifference().isOdd() ? 0 : 1);
            this.g.b().setBackgroundColor(-1842205);
            this.f.setFocusable(false);
            this.e.setFocusable(false);
            this.d.setFocusable(false);
            this.d.setClickable(true);
            this.h = new v(context, this.d);
            this.i = new v(context, this.f);
            addView(this.i);
            this.f.addView(this.e);
            addView(this.h);
            a(this.e);
            a(this.f);
            a(this.d);
            a(this.i);
            a(this.h);
            this.d.setOnClickListener(new View.OnClickListener() { // from class: com.chartboost.sdk.impl.h.a.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (h.this.f401a != null) {
                        h.this.f401a.a();
                    }
                }
            });
            this.j = new C0007a(context);
        }

        /* synthetic */ a(h hVar, Context context, a aVar) {
            this(context);
        }

        private void a(View view) {
            int i = 200;
            if (200 == getId()) {
                i = 201;
            }
            int i2 = i;
            View viewFindViewById = findViewById(i);
            while (viewFindViewById != null) {
                i2++;
                viewFindViewById = findViewById(i2);
            }
            view.setId(i2);
            view.setSaveEnabled(false);
        }

        @Override // com.chartboost.sdk.c.b
        protected void a(int i, int i2) {
            int i3;
            int i4;
            if (this.g.a() != null) {
                removeView(this.g.a());
            }
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2, 17);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
            final CBOrientation.Difference forcedOrientationDifference = Chartboost.sharedChartboost().getForcedOrientationDifference();
            layoutParams2.width = forcedOrientationDifference.isOdd() ? com.chartboost.sdk.Libraries.d.a(h.h, getContext()) : -1;
            layoutParams2.height = forcedOrientationDifference.isOdd() ? -1 : com.chartboost.sdk.Libraries.d.a(h.h, getContext());
            switch (c()[forcedOrientationDifference.ordinal()]) {
                case 2:
                    layoutParams2.addRule(11);
                    break;
                case 3:
                    layoutParams2.addRule(12);
                    break;
            }
            BitmapDrawable bitmapDrawable = new BitmapDrawable(h.this.n.b());
            bitmapDrawable.setTileModeX(Shader.TileMode.REPEAT);
            bitmapDrawable.setTileModeY(Shader.TileMode.CLAMP);
            this.f.setBackgroundDrawable(bitmapDrawable);
            if (h.this.m != null) {
                this.e.setImageBitmap(h.this.m.b());
                layoutParams.width = com.chartboost.sdk.Libraries.d.a(h.this.m.c(), getContext());
                layoutParams.height = com.chartboost.sdk.Libraries.d.a(Math.min(h.h, h.this.m.d()), getContext());
            }
            this.d.setImageBitmap(h.this.l.b());
            if (!forcedOrientationDifference.isOdd()) {
                i3 = h.i;
            } else {
                i3 = h.j;
            }
            layoutParams3.width = com.chartboost.sdk.Libraries.d.a(i3, getContext());
            if (forcedOrientationDifference.isOdd()) {
                i4 = h.i;
            } else {
                i4 = h.j;
            }
            layoutParams3.height = com.chartboost.sdk.Libraries.d.a(i4, getContext());
            switch (c()[forcedOrientationDifference.ordinal()]) {
                case 2:
                    layoutParams3.bottomMargin = com.chartboost.sdk.Libraries.d.a(10, getContext());
                    layoutParams3.rightMargin = com.chartboost.sdk.Libraries.d.a((h.h - h.j) / 2, getContext());
                    layoutParams3.addRule(11);
                    layoutParams3.addRule(12);
                    break;
                case 3:
                    layoutParams3.leftMargin = com.chartboost.sdk.Libraries.d.a(10, getContext());
                    layoutParams3.bottomMargin = com.chartboost.sdk.Libraries.d.a((h.h - h.j) / 2, getContext());
                    layoutParams3.addRule(12);
                    break;
                case 4:
                    layoutParams3.topMargin = com.chartboost.sdk.Libraries.d.a(10, getContext());
                    layoutParams3.leftMargin = com.chartboost.sdk.Libraries.d.a((h.h - h.j) / 2, getContext());
                    break;
                default:
                    layoutParams3.rightMargin = com.chartboost.sdk.Libraries.d.a(10, getContext());
                    layoutParams3.topMargin = com.chartboost.sdk.Libraries.d.a((h.h - h.j) / 2, getContext());
                    layoutParams3.addRule(11);
                    break;
            }
            layoutParams4.width = -1;
            layoutParams4.height = -1;
            switch (c()[forcedOrientationDifference.ordinal()]) {
                case 2:
                    layoutParams4.addRule(0, this.i.getId());
                    break;
                case 3:
                    layoutParams4.addRule(2, this.i.getId());
                    break;
                case 4:
                    layoutParams4.addRule(1, this.i.getId());
                    break;
                default:
                    layoutParams4.addRule(3, this.i.getId());
                    break;
            }
            this.g.a(forcedOrientationDifference.isOdd() ? 0 : 1);
            a(this.g.a());
            this.g.a(this.j);
            addView(this.g.a(), layoutParams4);
            if (forcedOrientationDifference == CBOrientation.Difference.ANGLE_180) {
                this.g.b().setGravity(80);
            } else if (forcedOrientationDifference == CBOrientation.Difference.ANGLE_90) {
                this.g.b().setGravity(5);
            } else {
                this.g.b().setGravity(0);
            }
            this.i.setLayoutParams(layoutParams2);
            this.e.setLayoutParams(layoutParams);
            this.e.setScaleType(ImageView.ScaleType.FIT_CENTER);
            this.h.setLayoutParams(layoutParams3);
            this.d.setScaleType(ImageView.ScaleType.FIT_CENTER);
            post(new Runnable() { // from class: com.chartboost.sdk.impl.h.a.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        a.this.f402a = true;
                        a.this.requestLayout();
                        a.this.g.a().requestLayout();
                        a.this.g.b().requestLayout();
                        a.this.f402a = false;
                        if (forcedOrientationDifference == CBOrientation.Difference.ANGLE_180 || forcedOrientationDifference == CBOrientation.Difference.ANGLE_90) {
                            a.this.g.c();
                        }
                    } catch (Exception e) {
                    }
                }
            });
        }

        @Override // com.chartboost.sdk.c.b
        public void b() {
            super.b();
            this.d = null;
            this.e = null;
            this.g = null;
        }

        /* JADX INFO: renamed from: com.chartboost.sdk.impl.h$a$a, reason: collision with other inner class name */
        public class C0007a extends ArrayAdapter<JSONObject> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            Context f462a;

            public C0007a(Context context) {
                super(context, 0, h.this.k);
                this.f462a = context;
            }

            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public View getView(int i, View view, ViewGroup viewGroup) {
                v vVar;
                CBOrientation.Difference forcedOrientationDifference = Chartboost.sharedChartboost().getForcedOrientationDifference();
                if (forcedOrientationDifference.isReverse()) {
                    i = (getCount() - 1) - i;
                }
                final JSONObject item = getItem(i);
                String strOptString = item.optString(ServerProtocol.DIALOG_PARAM_TYPE, "");
                b iVar = null;
                if (view == null) {
                    if (strOptString.equals("featured")) {
                        iVar = new d(this.f462a);
                    } else if (strOptString.equals("regular")) {
                        iVar = new e(this.f462a);
                    } else if (strOptString.equals("webview")) {
                        iVar = new i(this.f462a);
                    }
                    vVar = new v(this.f462a, (View) iVar);
                } else {
                    vVar = (v) view;
                    iVar = (b) vVar.a();
                }
                iVar.a(item, i);
                c cVar = (c) iVar;
                if (forcedOrientationDifference.isOdd()) {
                    vVar.setLayoutParams(new AbsListView.LayoutParams(iVar.a(), -1));
                } else {
                    vVar.setLayoutParams(new AbsListView.LayoutParams(-1, iVar.a()));
                }
                View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.chartboost.sdk.impl.h.a.a.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        String strOptString2 = item.optString("deep-link");
                        if (strOptString2 == null || strOptString2.equals("")) {
                            strOptString2 = item.optString("link");
                        }
                        if (h.this.b != null) {
                            h.this.b.a(strOptString2, item);
                        }
                    }
                };
                cVar.f452a = onClickListener;
                cVar.setOnClickListener(onClickListener);
                if (iVar instanceof e) {
                    ((e) iVar).b.setOnClickListener(onClickListener);
                }
                return vVar;
            }

            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public int getCount() {
                return h.this.k.size();
            }

            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public JSONObject getItem(int i) {
                return (JSONObject) h.this.k.get(i);
            }
        }
    }

    public h(com.chartboost.sdk.impl.a aVar) {
        super(aVar);
        this.e = 3;
        this.k = new ArrayList();
    }

    @Override // com.chartboost.sdk.c
    protected com.chartboost.sdk.c.b a(Context context) {
        return new a(this, context, null);
    }

    @Override // com.chartboost.sdk.c
    public void a(JSONObject jSONObject) {
        super.a(jSONObject);
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("cells");
        if (jSONArrayOptJSONArray == null) {
            if (this.d != null) {
                this.d.a();
                return;
            }
            return;
        }
        this.o = new SparseArray<>();
        o.b bVar = new o.b() { // from class: com.chartboost.sdk.impl.h.1
            @Override // com.chartboost.sdk.impl.o.b
            public void a(com.chartboost.sdk.Libraries.a.C0000a c0000a, Bundle bundle) {
                h.this.o.put(bundle.getInt("index"), c0000a);
                h.this.a(c0000a);
            }
        };
        for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i2);
            this.k.add(jSONObjectOptJSONObject);
            String strOptString = jSONObjectOptJSONObject.optString(ServerProtocol.DIALOG_PARAM_TYPE, "");
            if (strOptString.equals("regular")) {
                JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("assets");
                if (jSONObjectOptJSONObject2 != null) {
                    this.e++;
                    Bundle bundle = new Bundle();
                    bundle.putInt("index", i2);
                    a(jSONObjectOptJSONObject2, "icon", bVar, bundle);
                }
            } else if (strOptString.equals("featured")) {
                JSONObject jSONObjectOptJSONObject3 = jSONObjectOptJSONObject.optJSONObject("assets");
                if (jSONObjectOptJSONObject3 != null) {
                    this.e++;
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("index", i2);
                    a(jSONObjectOptJSONObject3, "portrait", bVar, bundle2);
                    this.e++;
                    Bundle bundle3 = new Bundle();
                    bundle3.putInt("index", i2);
                    a(jSONObjectOptJSONObject3, "landscape", bVar, bundle3);
                }
            } else {
                strOptString.equals("webview");
            }
        }
        o.b bVar2 = new o.b() { // from class: com.chartboost.sdk.impl.h.2
            @Override // com.chartboost.sdk.impl.o.b
            public void a(com.chartboost.sdk.Libraries.a.C0000a c0000a, Bundle bundle4) {
                h.this.l = c0000a;
                h.this.a(c0000a);
            }
        };
        o.b bVar3 = new o.b() { // from class: com.chartboost.sdk.impl.h.3
            @Override // com.chartboost.sdk.impl.o.b
            public void a(com.chartboost.sdk.Libraries.a.C0000a c0000a, Bundle bundle4) {
                h.this.m = c0000a;
                h.this.a(c0000a);
            }
        };
        o.b bVar4 = new o.b() { // from class: com.chartboost.sdk.impl.h.4
            @Override // com.chartboost.sdk.impl.o.b
            public void a(com.chartboost.sdk.Libraries.a.C0000a c0000a, Bundle bundle4) {
                h.this.n = c0000a;
                h.this.a(c0000a);
            }
        };
        a("close", bVar2);
        a("header-center", bVar3);
        a("header-tile", bVar4);
    }

    @Override // com.chartboost.sdk.c
    public void c() {
        super.c();
        this.k = null;
        this.l = null;
        this.n = null;
        this.m = null;
    }
}

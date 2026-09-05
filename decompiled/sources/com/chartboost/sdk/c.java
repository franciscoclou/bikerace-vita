package com.chartboost.sdk;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.chartboost.sdk.impl.o;
import com.chartboost.sdk.impl.u;
import com.facebook.internal.NativeProtocol;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class c {
    protected JSONObject f;
    protected com.chartboost.sdk.impl.a g;
    private int h;
    private int i;
    private int j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f401a = null;
    public InterfaceC0005c b = null;
    public a c = null;
    public a d = null;
    protected int e = 0;
    private b k = null;

    public interface a {
        void a();
    }

    /* JADX INFO: renamed from: com.chartboost.sdk.c$c, reason: collision with other inner class name */
    public interface InterfaceC0005c {
        void a(String str, JSONObject jSONObject);
    }

    protected abstract b a(Context context);

    public abstract class b extends RelativeLayout implements u.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected boolean f402a;

        protected abstract void a(int i, int i2);

        public b(Context context) {
            super(context);
            this.f402a = false;
            setFocusableInTouchMode(true);
            requestFocus();
            setOnTouchListener(new View.OnTouchListener() { // from class: com.chartboost.sdk.c.b.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
        }

        @Override // android.view.View
        protected void onSizeChanged(int i, int i2, int i3, int i4) {
            super.onSizeChanged(i, i2, i3, i4);
            if (!this.f402a) {
                if (Chartboost.sharedChartboost().getForcedOrientationDifference().isOdd()) {
                    b(i2, i);
                } else {
                    b(i, i2);
                }
            }
        }

        private boolean b(int i, int i2) {
            try {
                a(i, i2);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        @Override // com.chartboost.sdk.impl.u.a
        public void a() {
            a((Activity) getContext());
        }

        public void b() {
        }

        public boolean a(Activity activity) {
            int height;
            int width;
            try {
                width = getWidth();
                height = getHeight();
                if (width == 0 || height == 0) {
                    View viewFindViewById = activity.getWindow().findViewById(R.id.content);
                    if (viewFindViewById == null) {
                        viewFindViewById = activity.getWindow().getDecorView();
                    }
                    width = viewFindViewById.getWidth();
                    height = viewFindViewById.getHeight();
                }
            } catch (Exception e) {
                height = 0;
                width = 0;
            }
            if (width == 0 || height == 0) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                width = displayMetrics.widthPixels;
                height = displayMetrics.heightPixels;
            }
            if (!Chartboost.sharedChartboost().getForcedOrientationDifference().isOdd()) {
                int i = height;
                height = width;
                width = i;
            }
            return b(height, width);
        }
    }

    public c(com.chartboost.sdk.impl.a aVar) {
        this.g = aVar;
    }

    public void a(JSONObject jSONObject) {
        this.i = 0;
        this.j = 0;
        this.h = 0;
        this.f = jSONObject.optJSONObject("assets");
        if (this.f != null || this.d == null) {
            return;
        }
        this.d.a();
    }

    protected void a(String str, o.b bVar) {
        a(str, bVar, false);
    }

    protected void a(String str, o.b bVar, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("paramNoMemoryCache", z);
        a(this.f, str, bVar, bundle);
    }

    protected void a(JSONObject jSONObject, String str, o.b bVar, Bundle bundle) {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(str);
        if (jSONObjectOptJSONObject != null) {
            this.j++;
            String strOptString = jSONObjectOptJSONObject.optString(NativeProtocol.IMAGE_URL_KEY);
            String strOptString2 = jSONObjectOptJSONObject.optString("checksum");
            float fOptDouble = (float) jSONObjectOptJSONObject.optDouble("scale", 1.0d);
            Bundle bundle2 = bundle == null ? new Bundle() : bundle;
            bundle2.putFloat("paramAssetScale", fOptDouble);
            o.a().a(strOptString, strOptString2, bVar, null, bundle2);
            return;
        }
        a((com.chartboost.sdk.Libraries.a.C0000a) null);
    }

    protected void a(com.chartboost.sdk.Libraries.a.C0000a c0000a) {
        if (c0000a != null) {
            this.h++;
        }
        this.i++;
        if (this.i != this.e || a() || this.d == null) {
            return;
        }
        this.d.a();
    }

    public boolean a() {
        if (this.h != this.j) {
            return false;
        }
        if (this.c != null) {
            this.c.a();
        }
        return true;
    }

    public boolean b() {
        if (this.g.c != com.chartboost.sdk.impl.a.b.CBImpressionStateWaitingForDisplay) {
            return false;
        }
        Chartboost.sharedChartboost().a(this.g);
        Activity activityC = Chartboost.sharedChartboost().c();
        if (activityC == null) {
            this.k = null;
            return false;
        }
        this.k = a(activityC);
        if (this.k.a(activityC)) {
            return true;
        }
        this.k = null;
        return false;
    }

    public void c() {
        e();
        this.c = null;
        this.d = null;
        this.b = null;
        this.f401a = null;
        this.f = null;
    }

    public b d() {
        return this.k;
    }

    public void e() {
        if (this.k != null) {
            this.k.b();
        }
        this.k = null;
    }
}

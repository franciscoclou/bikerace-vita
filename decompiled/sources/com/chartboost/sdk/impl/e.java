package com.chartboost.sdk.impl;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.internal.NativeProtocol;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class e extends c implements h.b {
    private static int c = 50;
    private static int d = 10;
    public f b;
    private g e;
    private TextView f;

    public e(Context context) {
        super(context);
        this.e = new g(context);
        this.b = new f(context);
        this.f = new TextView(context);
        this.f.setTypeface(null, 1);
        this.f.setTextSize(2, 16.0f);
        this.f.setShadowLayer(1.0f, 1.0f, 1.0f, -1);
        this.f.setBackgroundColor(0);
        this.f.setTextColor(-16777216);
        this.f.setEllipsize(TextUtils.TruncateAt.END);
        setBackgroundColor(-3355444);
        setFocusable(false);
        addView(this.e);
        addView(this.f);
        addView(this.b);
    }

    @Override // com.chartboost.sdk.impl.h.b
    public void a(JSONObject jSONObject, int i) {
        JSONObject jSONObjectOptJSONObject;
        this.f.setText(jSONObject.optString("name", "Unknown App"));
        String strOptString = jSONObject.optString("deep-text");
        if (strOptString != null && !strOptString.equals("")) {
            this.b.setText(strOptString);
        } else {
            this.b.setText(jSONObject.optString("text", "VIEW"));
        }
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("assets");
        if (jSONObjectOptJSONObject2 != null && (jSONObjectOptJSONObject = jSONObjectOptJSONObject2.optJSONObject("icon")) != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            o.a().a(jSONObjectOptJSONObject.optString(NativeProtocol.IMAGE_URL_KEY), jSONObjectOptJSONObject.optString("checksum"), null, this.e, bundle);
        }
        b();
    }

    protected void b() {
        int iA = com.chartboost.sdk.Libraries.d.a(c, getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(iA, iA);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -1);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        int iA2 = com.chartboost.sdk.Libraries.d.a(d, getContext());
        layoutParams.setMargins(iA2, iA2, iA2, iA2);
        layoutParams2.setMargins(iA2, iA2, iA2, iA2);
        layoutParams3.setMargins(iA2, iA2, iA2, iA2);
        layoutParams2.weight = 1.0f;
        this.f.setGravity(16);
        layoutParams3.gravity = 16;
        this.e.setLayoutParams(layoutParams);
        this.e.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.f.setLayoutParams(layoutParams2);
        this.b.setLayoutParams(layoutParams3);
    }

    @Override // com.chartboost.sdk.impl.h.b
    public int a() {
        return com.chartboost.sdk.Libraries.d.a(c + (d * 2), getContext());
    }
}

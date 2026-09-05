package com.chartboost.sdk.impl;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import com.chartboost.sdk.Chartboost;
import com.facebook.internal.NativeProtocol;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class d extends c implements h.b {
    private static int b = 100;
    private static int c = 5;
    private g d;

    public d(Context context) {
        super(context);
        this.d = new g(context);
        addView(this.d, new LinearLayout.LayoutParams(-1, -1));
    }

    @Override // com.chartboost.sdk.impl.h.b
    public void a(JSONObject jSONObject, int i) {
        boolean zIsPortrait = Chartboost.sharedChartboost().getOrientation().isPortrait();
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("assets");
        if (jSONObjectOptJSONObject != null) {
            JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject(zIsPortrait ? "portrait" : "landscape");
            if (jSONObjectOptJSONObject2 != null) {
                Bundle bundle = new Bundle();
                bundle.putInt("index", i);
                o.a().a(jSONObjectOptJSONObject2.optString(NativeProtocol.IMAGE_URL_KEY), jSONObjectOptJSONObject2.optString("checksum"), null, this.d, bundle);
            }
        }
    }

    @Override // com.chartboost.sdk.impl.h.b
    public int a() {
        return com.chartboost.sdk.Libraries.d.a(b + (c * 2), getContext());
    }
}

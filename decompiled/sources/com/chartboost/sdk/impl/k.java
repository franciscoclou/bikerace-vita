package com.chartboost.sdk.impl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import com.chartboost.sdk.Chartboost;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f469a;
    private JSONObject b;
    private Map<String, Object> c;
    private Map<String, Object> d;
    private boolean f;
    private com.chartboost.sdk.Libraries.e.a g = null;
    private String e = "POST";

    public k(String str) {
        this.f469a = str;
    }

    public void a(String str, Object obj) {
        if (this.b == null) {
            this.b = new JSONObject();
        }
        try {
            this.b.put(str, obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void a(String str, String str2) {
        if (this.d == null) {
            this.d = new HashMap();
        }
        this.d.put(str, str2);
    }

    public void a(Context context) {
        Chartboost chartboostSharedChartboost = Chartboost.sharedChartboost();
        a("app", (Object) Chartboost.sharedChartboost().getAppID());
        if ("sdk".equals(Build.PRODUCT)) {
            a("model", (Object) "Android Simulator");
            a("identity", (Object) com.chartboost.sdk.Libraries.d.b());
        } else {
            a("model", (Object) Build.MODEL);
            a("identity", (Object) com.chartboost.sdk.Libraries.d.b());
        }
        a("device_type", (Object) (String.valueOf(Build.MANUFACTURER) + " " + Build.MODEL));
        a("os", (Object) ("Android " + Build.VERSION.RELEASE));
        a("country", (Object) Locale.getDefault().getCountry());
        a("language", (Object) Locale.getDefault().getLanguage());
        a("sdk", (Object) "3.4.0");
        a("framework", (Object) chartboostSharedChartboost.getFramework());
        a("timestamp", (Object) String.valueOf(Long.valueOf(new Date().getTime() / 1000).intValue()));
        a("session", Integer.valueOf(com.chartboost.sdk.Libraries.d.a().getInt("cbPrefSessionCount", 0)));
        int iB = m.b();
        if (iB != -1) {
            a("reachability", Integer.valueOf(iB));
        }
        b(context);
        a("scale", (Object) new StringBuilder().append(context.getResources().getDisplayMetrics().density).toString());
        try {
            String packageName = context.getPackageName();
            a("bundle", (Object) context.getPackageManager().getPackageInfo(packageName, XMLChar.MASK_NCNAME).versionName);
            a("bundle_id", (Object) packageName);
        } catch (Exception e) {
        }
    }

    public void b(String str, String str2) {
        String strB = com.chartboost.sdk.Libraries.b.b(com.chartboost.sdk.Libraries.b.a(String.format(Locale.US, "%s %s\n%s\n%s", this.e, a(), str2, b()).getBytes()));
        a("X-Chartboost-App", str);
        a("X-Chartboost-Signature", strB);
    }

    public String a() {
        return String.valueOf(this.f469a.startsWith("/") ? "" : "/") + this.f469a + com.chartboost.sdk.Libraries.d.a(this.c);
    }

    public String b() {
        return this.b.toString();
    }

    private void b(Context context) {
        int i;
        int width;
        int height = 0;
        try {
            if (context instanceof Activity) {
                Rect rect = new Rect();
                ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                if (Build.VERSION.SDK_INT < 9) {
                    rect.top = 0;
                }
                width = rect.width();
                try {
                    height = rect.height();
                } catch (Exception e) {
                    i = width;
                    width = i;
                }
            } else {
                width = 0;
            }
        } catch (Exception e2) {
            i = 0;
        }
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (width <= 0) {
            width = defaultDisplay.getWidth();
        }
        if (height <= 0) {
            height = defaultDisplay.getHeight();
        }
        a("w", (Object) new StringBuilder().append(width).toString());
        a("h", (Object) new StringBuilder().append(height).toString());
    }

    public String c() {
        return this.f469a;
    }

    public JSONObject d() {
        return this.b;
    }

    public Map<String, Object> e() {
        return this.d;
    }

    public boolean f() {
        return this.f;
    }

    public void a(boolean z) {
        this.f = z;
    }

    public com.chartboost.sdk.Libraries.e.a g() {
        return this.g;
    }

    public void a(com.chartboost.sdk.Libraries.e.g... gVarArr) {
        this.g = com.chartboost.sdk.Libraries.e.a(gVarArr);
    }

    public static k a(JSONObject jSONObject) {
        try {
            k kVar = new k(jSONObject.getString("path"));
            kVar.e = jSONObject.getString("method");
            kVar.c = com.chartboost.sdk.Libraries.d.a(jSONObject.optJSONObject("query"));
            kVar.b = jSONObject.optJSONObject("body");
            kVar.d = com.chartboost.sdk.Libraries.d.a(jSONObject.optJSONObject("headers"));
            kVar.f = jSONObject.getBoolean("ensureDelivery");
            return kVar;
        } catch (Exception e) {
            return null;
        }
    }

    public JSONObject h() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("path", this.f469a);
            jSONObject.put("method", this.e);
            jSONObject.put("query", com.chartboost.sdk.Libraries.d.b(this.c));
            jSONObject.put("body", this.b);
            jSONObject.put("headers", com.chartboost.sdk.Libraries.d.b(this.d));
            jSONObject.put("ensureDelivery", this.f);
            return jSONObject;
        } catch (Exception e) {
            return null;
        }
    }
}

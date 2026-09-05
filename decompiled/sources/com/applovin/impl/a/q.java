package com.applovin.impl.a;

import com.facebook.internal.ServerProtocol;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class q extends x {
    q(d dVar) {
        super("SubmitData", dVar);
    }

    static JSONArray a(Collection collection) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            ax axVar = (ax) it.next();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("package_name", axVar.c);
            jSONObject.put("created_at", axVar.d / 1000);
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    protected Collection a() {
        if (((Boolean) this.d.a(j.p)).booleanValue() && ah.a(j.q, this.d)) {
            return f().a();
        }
        return null;
    }

    protected void a(JSONObject jSONObject) {
        try {
            JSONObject jSONObjectA = at.a(jSONObject);
            m mVarG = this.d.g();
            mVarG.a(j.c, jSONObjectA.getString("device_id"));
            mVarG.a(j.e, jSONObjectA.getString("device_token"));
            mVarG.a(j.d, jSONObjectA.getString("publisher_id"));
            mVarG.b();
            at.a(jSONObjectA, this.d);
            if (jSONObjectA.has("adserver_parameters")) {
                mVarG.a(j.y, jSONObjectA.getJSONObject("adserver_parameters").toString());
            }
        } catch (JSONException e) {
            this.e.b(this.c, "Unable to parse API response", e);
        }
    }

    protected void b(JSONObject jSONObject) throws JSONException {
        au auVarF = f();
        ax axVarC = auVarF.c();
        ay ayVarB = auVarF.b();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("model", ayVarB.c);
        jSONObject2.put("os", ayVarB.d);
        jSONObject2.put("brand", ayVarB.e);
        jSONObject2.put("sdk_version", ayVarB.g);
        jSONObject2.put("revision", ayVarB.f);
        jSONObject2.put("country_code", ayVarB.h);
        jSONObject2.put("carrier", ayVarB.i);
        jSONObject2.put(ServerProtocol.DIALOG_PARAM_TYPE, "android");
        if (((Boolean) this.d.a(j.N)).booleanValue()) {
            jSONObject2.put("adid", ayVarB.b);
            jSONObject2.put("h_android_id", ah.a(ah.b(ayVarB.b), this.d));
            jSONObject2.put("h_udid", ah.a(ah.b(ayVarB.f196a), this.d));
            jSONObject2.put("h_nn_android_id", ah.a(ayVarB.b, this.d));
            jSONObject2.put("h_nn_udid", ah.a(ayVarB.f196a, this.d));
        }
        aw awVarE = auVarF.e();
        String str = awVarE.b;
        if (!awVarE.f194a && ah.d(str)) {
            jSONObject2.put("idfa", str);
        }
        Locale locale = ayVarB.j;
        if (locale != null) {
            jSONObject2.put("locale", locale.toString());
        }
        jSONObject.put("device_info", jSONObject2);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("package_name", axVarC.c);
        jSONObject3.put("app_name", axVarC.f195a);
        jSONObject3.put("app_version", axVarC.b);
        jSONObject3.put("created_at", axVarC.d / 1000);
        jSONObject3.put("applovin_sdk_version", "5.3.6-5.3.6");
        String str2 = (String) this.d.a(j.P);
        jSONObject3.put("first_install", str2);
        if (str2.equals("true")) {
            this.d.g().a(j.P, "false");
        }
        String str3 = (String) this.d.a(j.F);
        if (str3 != null && str3.length() > 0) {
            jSONObject3.put("plugin_version", str3);
        }
        jSONObject.put("app_info", jSONObject3);
        if (((Boolean) this.d.a(j.N)).booleanValue()) {
            Map mapA = ((ap) this.d.e()).a();
            if (mapA != null && !mapA.isEmpty()) {
                jSONObject.put("targeting", g.a(mapA));
            }
            jSONObject.put("stats", this.d.l().b());
        }
    }

    protected void c(JSONObject jSONObject) {
        r rVar = new r(this, "Repeat" + this.c, j.g, this.d, jSONObject);
        rVar.a(j.k);
        rVar.run();
    }

    @Override // java.lang.Runnable
    public void run() {
        Collection collectionA;
        try {
            this.e.b(this.c, "Submitting user data...");
            JSONObject jSONObject = new JSONObject();
            b(jSONObject);
            if (((Boolean) this.d.a(j.M)).booleanValue() && (collectionA = a()) != null) {
                jSONObject.put("vx", a(collectionA));
            }
            c(jSONObject);
        } catch (JSONException e) {
            this.e.b(this.c, "Unable to create JSON message with collected data", e);
        }
    }
}

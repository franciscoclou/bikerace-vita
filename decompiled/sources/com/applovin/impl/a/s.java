package com.applovin.impl.a;

import com.applovin.adview.AppLovinInterstitialActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class s extends x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.applovin.a.f f211a;
    private final com.applovin.a.g b;
    private final com.applovin.a.d g;
    private boolean h;

    s(com.applovin.a.f fVar, com.applovin.a.g gVar, com.applovin.a.d dVar, d dVar2) {
        super("FetchNextAd", dVar2);
        this.f211a = fVar;
        this.b = gVar;
        this.g = dVar;
    }

    private List a(List list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((ax) it.next()).c);
        }
        return arrayList;
    }

    private void a(o oVar) {
        if (System.currentTimeMillis() - oVar.b("ad_session_start") > ((Integer) this.d.a(j.x)).intValue() * 60000) {
            oVar.b("ad_session_start", System.currentTimeMillis());
            oVar.c("ad_imp_session");
        }
    }

    private void b(Map map) {
        map.put("api_did", this.d.a(j.c));
        map.put("sdk_key", this.d.a());
        map.put("sdk_version", "5.3.6-5.3.6");
        String str = (String) this.d.a(j.F);
        if (str != null && str.length() > 0) {
            map.put("plugin_version", str);
        }
        map.put("accept", g());
        map.put("preloading", String.valueOf(this.h));
        map.put("size", this.f211a.c());
        map.put("format", "json");
    }

    private void c(Map map) {
        if (((Boolean) this.d.a(j.N)).booleanValue()) {
            o oVarL = this.d.l();
            map.put("li", String.valueOf(oVarL.b("ad_imp")));
            map.put("si", String.valueOf(oVarL.b("ad_imp_session")));
        }
    }

    private void d(Map map) {
        Map mapA;
        if (!((Boolean) this.d.a(j.N)).booleanValue() || (mapA = ((ap) this.d.e()).a()) == null || mapA.isEmpty()) {
            return;
        }
        map.putAll(mapA);
    }

    private void e(Map map) {
        Map mapA = e.a(this.d);
        if (mapA.isEmpty()) {
            try {
                f(mapA);
                e.a(mapA, this.d);
            } catch (Exception e) {
                this.e.b(this.c, "Unable to populate device information", e);
            }
        }
        map.putAll(mapA);
        map.put("network", at.a(this.d));
        map.put("vz", ah.b(this.d.h().getPackageName(), this.d));
    }

    private void f(Map map) {
        au auVarF = f();
        ay ayVarB = auVarF.b();
        String str = ayVarB.f196a;
        if (ah.d(str)) {
            map.put("hudid", ah.a(ah.b(str), this.d));
        }
        String str2 = ayVarB.b;
        if (ah.d(str2)) {
            map.put("hadid", ah.a(ah.b(str2), this.d));
            map.put("adid", str2);
        }
        aw awVarE = auVarF.e();
        String str3 = awVarE.b;
        if (!awVarE.f194a && ah.d(str3)) {
            map.put("idfa", str3);
        }
        map.put("brand", ah.c(ayVarB.e));
        map.put("carrier", ah.c(ayVarB.i));
        map.put("locale", ayVarB.j.toString());
        map.put("model", ah.c(ayVarB.c));
        map.put("os", ah.c(ayVarB.d));
        map.put("platform", "android");
        map.put("revision", ah.c(ayVarB.f));
    }

    private String g() {
        return (aq.b() && aq.a(AppLovinInterstitialActivity.class, this.f) && aq.a("android.permission.WRITE_EXTERNAL_STORAGE", this.f)) ? "inter_size,custom_size,launch_app,multi_click,video" : "inter_size,custom_size,launch_app,multi_click";
    }

    private void g(Map map) {
        if (this.b != null) {
            map.put("require", this.b.a());
        }
    }

    protected void a(int i) {
        this.e.d(this.c, "Unable to fetch " + this.f211a + " ad: server returned " + i);
        try {
            if (this.g != null) {
                this.g.a(i);
            }
        } catch (Throwable th) {
            this.e.b(this.c, "Unable process a failure to recieve an ad", th);
        }
        at.b(i, this.d);
    }

    protected void a(StringBuffer stringBuffer) {
        if (((Boolean) this.d.a(j.p)).booleanValue()) {
            try {
                stringBuffer.append("&vx=").append(d());
            } catch (Exception e) {
                this.e.b(this.c, "Unable to populate vx field", e);
            }
        }
    }

    protected void a(Map map) {
        d(map);
        e(map);
        c(map);
        b(map);
        g(map);
    }

    protected void a(JSONObject jSONObject) {
        this.d.k().a(new ac(jSONObject, this.g, this.d), w.MAIN);
        at.a(jSONObject, this.d);
    }

    public void a(boolean z) {
        this.h = z;
    }

    @Override // com.applovin.impl.a.x
    void a_() {
        super.a_();
        a(-410);
    }

    protected String b() {
        HashMap map = new HashMap();
        a(map);
        StringBuffer stringBuffer = new StringBuffer(c());
        stringBuffer.append("?").append(ah.a(map));
        a(stringBuffer);
        return stringBuffer.toString();
    }

    protected String c() {
        return at.b("ad", this.d);
    }

    protected String d() {
        return ah.a(a(f().a()), ",", ((Integer) this.d.a(j.o)).intValue());
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.h) {
            this.e.a(this.c, "Preloading next ad...");
        } else {
            this.e.a(this.c, "Fetching next ad...");
        }
        o oVarL = this.d.l();
        oVarL.a("ad_req");
        a(oVarL);
        try {
            t tVar = new t(this, "RepeatFetchNextAd", j.i, this.d);
            tVar.a(j.l);
            tVar.run();
        } catch (Throwable th) {
            this.e.b(this.c, "Unable to fetch " + this.f211a + " ad", th);
            a(0);
        }
    }
}

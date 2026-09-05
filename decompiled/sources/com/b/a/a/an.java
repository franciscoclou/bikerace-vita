package com.b.a.a;

import java.util.HashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class an extends y implements ca {
    public an(String str, String str2, bu buVar) {
        this(str, str2, buVar, bw.GET);
    }

    private an(String str, String str2, bu buVar, bw bwVar) {
        super(str, str2, buVar, bwVar);
    }

    /* JADX WARN: Code duplicated, block: B:22:0x011f  */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.b.a.a.ca
    public final JSONObject a(ay ayVar) throws Throwable {
        bx bxVar;
        bx bxVarA;
        JSONObject jSONObject;
        try {
            try {
                HashMap map = new HashMap();
                map.put("build_version", ayVar.e);
                map.put("display_version", ayVar.d);
                map.put("source", Integer.toString(ayVar.f));
                if (ayVar.g != null) {
                    map.put("icon_hash", ayVar.g);
                }
                String str = ayVar.c;
                if (!ba.e(str)) {
                    map.put("instance", str);
                }
                bxVarA = a(map);
                try {
                    bxVarA = bxVarA.a("X-CRASHLYTICS-API-KEY", ayVar.f268a).a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").a("X-CRASHLYTICS-D", ayVar.b).a("X-CRASHLYTICS-API-CLIENT-VERSION", cm.a().f()).a("Accept", "application/json");
                    cm.a().b().a("Crashlytics", "Requesting settings from " + a());
                    cm.a().b().a("Crashlytics", "Settings query params were: " + map);
                    jSONObject = new JSONObject(bxVarA.c());
                    bxVar = bxVarA;
                    if (bxVarA != null) {
                        cj cjVarB = cm.a().b();
                        String str2 = "Settings request ID: " + bxVarA.a("X-REQUEST-ID");
                        cjVarB.a("Crashlytics", str2);
                        bxVar = str2;
                    }
                } catch (Exception e) {
                    e = e;
                    cm.a().b().a("Crashlytics", "Failed to retrieve settings from " + a(), e);
                    if (bxVarA != null) {
                        cj cjVarB2 = cm.a().b();
                        String str3 = "Settings request ID: " + bxVarA.a("X-REQUEST-ID");
                        cjVarB2.a("Crashlytics", str3);
                        jSONObject = null;
                        bxVar = str3;
                    } else {
                        jSONObject = null;
                        bxVar = bxVarA;
                    }
                }
            } catch (Throwable th) {
                th = th;
                if (bxVar != 0) {
                    cm.a().b().a("Crashlytics", "Settings request ID: " + bxVar.a("X-REQUEST-ID"));
                }
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            bxVarA = null;
        } catch (Throwable th2) {
            th = th2;
            bxVar = 0;
            if (bxVar != 0) {
                cm.a().b().a("Crashlytics", "Settings request ID: " + bxVar.a("X-REQUEST-ID"));
            }
            throw th;
        }
        return jSONObject;
    }
}

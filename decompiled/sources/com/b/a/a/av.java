package com.b.a.a;

import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class av {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ay f266a;
    private final ax b;
    private final bg c;
    private final am d;
    private final ca e;

    public av(ay ayVar, bg bgVar, ax axVar, am amVar, ca caVar) {
        this.f266a = ayVar;
        this.c = bgVar;
        this.b = axVar;
        this.d = amVar;
        this.e = caVar;
    }

    public aw a() {
        return a(au.USE_CACHE);
    }

    public aw a(au auVar) throws Throwable {
        aw awVar;
        Exception e;
        aw awVarA = null;
        try {
            if (!cm.a().g()) {
                awVarA = b(auVar);
            }
            if (awVarA == null) {
                try {
                    JSONObject jSONObjectA = this.e.a(this.f266a);
                    if (jSONObjectA != null) {
                        awVarA = this.b.a(this.c, jSONObjectA);
                        this.d.a(awVarA.f, jSONObjectA);
                        a(jSONObjectA, "Loaded settings: ");
                    }
                } catch (Exception e2) {
                    awVar = awVarA;
                    e = e2;
                    cm.a().b().a("Crashlytics", "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.", e);
                    return awVar;
                }
            }
            awVar = awVarA;
            if (awVar == null) {
                try {
                    return b(au.IGNORE_CACHE_EXPIRATION);
                } catch (Exception e3) {
                    e = e3;
                    cm.a().b().a("Crashlytics", "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.", e);
                    return awVar;
                }
            }
            return awVar;
        } catch (Exception e4) {
            awVar = null;
            e = e4;
        }
    }

    private aw b(au auVar) throws Throwable {
        aw awVarA;
        Exception e;
        try {
            if (!au.SKIP_CACHE_LOOKUP.equals(auVar)) {
                JSONObject jSONObjectA = this.d.a();
                if (jSONObjectA != null) {
                    awVarA = this.b.a(this.c, jSONObjectA);
                    if (awVarA != null) {
                        a(jSONObjectA, "Loaded cached settings: ");
                        long jA = this.c.a();
                        if (!au.IGNORE_CACHE_EXPIRATION.equals(auVar)) {
                            if (awVarA.f < jA) {
                                cm.a().b().a("Crashlytics", "Cached settings have expired.");
                            }
                        }
                        try {
                            cm.a().b().a("Crashlytics", "Returning cached settings.");
                            return awVarA;
                        } catch (Exception e2) {
                            e = e2;
                            cm.a().b().a("Crashlytics", "Failed to get cached settings", e);
                            return awVarA;
                        }
                    }
                    cm.a().b().a("Crashlytics", "Failed to transform cached settings data.", (Throwable) null);
                    return null;
                }
                cm.a().b().a("Crashlytics", "No cached settings data found.");
            }
            return null;
        } catch (Exception e3) {
            awVarA = null;
            e = e3;
        }
    }

    private void a(JSONObject jSONObject, String str) {
        if (!ba.e(cm.a().w())) {
            jSONObject = this.b.a(jSONObject);
        }
        cm.a().b().a("Crashlytics", str + jSONObject.toString());
    }
}

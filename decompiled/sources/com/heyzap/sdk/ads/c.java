package com.heyzap.sdk.ads;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: AbstractAd.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f779a = null;
    public static String e = "default";
    protected String b;
    public String c;
    public String d;
    private String g;
    private String h;
    private String i;
    private Integer l;
    private long n;
    private Boolean j = false;
    private Boolean k = false;
    private Boolean m = false;
    private long o = System.currentTimeMillis();
    protected int f = 0;

    public abstract void b();

    public c(JSONObject jSONObject) {
        this.n = 0L;
        this.g = jSONObject.optString("ad_strategy", this.g);
        this.h = jSONObject.optString("promoted_game_package", "");
        this.i = jSONObject.getString("impression_id");
        this.c = jSONObject.optString("click_url", null);
        this.d = jSONObject.optString("tag", e);
        this.n = jSONObject.optLong("refresh_time", 0L) * 1000;
        this.l = Integer.valueOf(jSONObject.optInt("creative_id", 0));
    }

    public Boolean a(String str) {
        if (this.m.booleanValue()) {
            com.heyzap.internal.k.b("Already sent click successfully.");
            return true;
        }
        if (System.currentTimeMillis() - u.d().b < u.c) {
            return false;
        }
        com.heyzap.a.g gVar = new com.heyzap.a.g();
        gVar.a("ad_strategy", this.g);
        gVar.a("promoted_game_package", this.h);
        if (str != null) {
            gVar.a("custom_game_package", str);
        }
        gVar.a("impression_id", this.i);
        if (this.d != null) {
            gVar.a("tag", this.d);
        }
        com.heyzap.internal.a.b(u.f799a, u.e + "/register_click", gVar, new com.heyzap.internal.b() { // from class: com.heyzap.sdk.ads.c.1
            @Override // com.heyzap.a.f
            public void a(JSONObject jSONObject) {
                try {
                    if (jSONObject.getInt("status") == 200) {
                        com.heyzap.internal.k.b("(CLICK) [" + c.this.d + "] Impression ID:" + c.this.i);
                        c.this.m = true;
                    }
                } catch (JSONException e2) {
                }
            }
        });
        return true;
    }

    public void a() {
        if (this.j.booleanValue()) {
            com.heyzap.internal.k.b("Already sent impression successfully.");
            return;
        }
        com.heyzap.a.g gVar = new com.heyzap.a.g();
        gVar.a("impression_id", this.i);
        gVar.a("promoted_android_package", this.h);
        if (this.d != null) {
            gVar.a("tag", this.d);
        }
        com.heyzap.internal.a.b(u.f799a, u.e + "/register_impression", gVar, new com.heyzap.internal.b() { // from class: com.heyzap.sdk.ads.c.2
            @Override // com.heyzap.a.f
            public void a(JSONObject jSONObject) {
                try {
                    if (jSONObject.getInt("status") == 200) {
                        com.heyzap.internal.k.b("(IMPRESSION) [" + c.this.d + "] Impression ID:" + c.this.i);
                        c.this.j = true;
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    public String c() {
        return this.d;
    }

    public String d() {
        return this.i;
    }

    public String e() {
        return this.h;
    }

    public String f() {
        return this.b;
    }

    public String g() {
        return this.b;
    }

    public Boolean h() {
        if (this.n > 0) {
            return Boolean.valueOf(System.currentTimeMillis() > this.o + this.n);
        }
        return false;
    }

    public Boolean a(Context context) {
        return Boolean.valueOf(com.heyzap.internal.l.a(this.h, context));
    }

    public int i() {
        return this.f;
    }

    public String toString() {
        return String.format("%s T:%s I:%s CID: %s", getClass().getName(), g(), d(), String.valueOf(this.l));
    }
}

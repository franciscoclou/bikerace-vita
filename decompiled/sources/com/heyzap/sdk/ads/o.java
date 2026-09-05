package com.heyzap.sdk.ads;

import org.json.JSONObject;

/* JADX INFO: compiled from: Interstitial.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class o extends c {
    public static String g = "interstitial";
    private String h;
    private Integer i;
    private int j;
    private int k;
    private Boolean l;
    private Boolean m;

    public o(JSONObject jSONObject) {
        super(jSONObject);
        this.b = g;
        this.h = jSONObject.getString("ad_html");
        this.j = jSONObject.optInt("ad_height");
        this.k = jSONObject.optInt("ad_width");
        if (this.j == 0 && jSONObject.optString("ad_height").equals("fill_parent")) {
            this.j = -1;
        }
        if (this.k == 0 && jSONObject.optString("ad_width").equals("fill_parent")) {
            this.k = -1;
        }
        this.l = Boolean.valueOf((this.j == 0 || this.k == 0) ? false : true);
        String strOptString = jSONObject.optString("required_orientation", "portrait");
        if (Boolean.valueOf(jSONObject.optBoolean("hide_on_orientation_change", true)).booleanValue()) {
            if (strOptString.equals("landscape")) {
                this.f = 2;
            } else if (strOptString.equals("portrait")) {
                this.f = 1;
            } else {
                this.f = 0;
            }
        }
        this.m = Boolean.valueOf(jSONObject.optBoolean("disable_global_touch", false));
        this.i = Integer.valueOf(jSONObject.optInt("background_overlay", -1));
    }

    public String j() {
        return this.h;
    }

    public int k() {
        return this.k;
    }

    public int l() {
        return this.j;
    }

    public Integer m() {
        if (this.i.intValue() == -1) {
            return 0;
        }
        return this.i;
    }

    @Override // com.heyzap.sdk.ads.c
    public void b() {
    }
}

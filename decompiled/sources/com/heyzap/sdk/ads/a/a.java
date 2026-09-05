package com.heyzap.sdk.ads.a;

import org.json.JSONObject;

/* JADX INFO: compiled from: DemoServer.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f770a;
    public int b;
    public String c;
    public String d;
    public int e;

    public a() {
    }

    public a(JSONObject jSONObject) {
        this.f770a = jSONObject.getString("host");
        this.b = jSONObject.getInt("port");
        this.c = jSONObject.optString("server_id");
        this.d = jSONObject.optString("location");
        this.e = jSONObject.optInt("wait_time");
    }

    public String a() {
        return "tcp://" + this.f770a + ":" + String.valueOf(this.b);
    }
}

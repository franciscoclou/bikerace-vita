package com.topfreegames.bikerace.h.a;

import com.topfreegames.bikerace.ap;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: FeaturedUserLevelInfo.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f1242a;
    private String b;
    private String c;

    public a(JSONObject jSONObject) {
        try {
            this.f1242a = jSONObject.getString("id");
            this.b = jSONObject.getString("title");
            this.c = jSONObject.getString("thumb_url");
        } catch (JSONException e) {
            if (ap.d()) {
                e.printStackTrace();
            }
        }
    }

    a(String str, String str2) {
        this.f1242a = str;
        this.b = "";
        this.c = str2;
    }

    public String a() {
        return this.f1242a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public static a[] a(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONArray("levels");
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(new a((JSONObject) jSONArray.get(i)));
            }
            return (a[]) arrayList.toArray(new a[0]);
        } catch (JSONException e) {
            if (ap.d()) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

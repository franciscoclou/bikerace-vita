package com.heyzap.a;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* JADX INFO: compiled from: JsonHttpResponseHandler.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class f extends d {
    public void a(JSONObject jSONObject) {
    }

    public void a(JSONArray jSONArray) {
    }

    @Override // com.heyzap.a.d
    protected void c(String str) {
        super.c(str);
        try {
            Object objD = d(str);
            if (objD instanceof JSONObject) {
                a((JSONObject) objD);
            } else {
                if (objD instanceof JSONArray) {
                    a((JSONArray) objD);
                    return;
                }
                throw new JSONException("Unexpected type " + objD.getClass().getName());
            }
        } catch (JSONException e) {
            a(e, str);
        }
    }

    protected Object d(String str) {
        return new JSONTokener(str).nextValue();
    }

    public void a(Throwable th, JSONObject jSONObject) {
    }

    public void a(Throwable th, JSONArray jSONArray) {
    }

    @Override // com.heyzap.a.d
    protected void c(Throwable th, String str) {
        super.c(th, str);
        if (str != null) {
            try {
                Object objD = d(str);
                if (objD instanceof JSONObject) {
                    a(th, (JSONObject) objD);
                } else if (objD instanceof JSONArray) {
                    a(th, (JSONArray) objD);
                }
                return;
            } catch (JSONException e) {
                a(th, str);
                return;
            }
        }
        a(th, "");
    }
}

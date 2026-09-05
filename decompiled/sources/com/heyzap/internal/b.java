package com.heyzap.internal;

import android.os.Message;
import android.text.format.Time;
import java.util.HashMap;
import org.apache.http.client.HttpResponseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: APIResponseHandler.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b extends com.heyzap.a.f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Time f752a;
    private j b;
    private com.heyzap.a.g c;
    private String d;
    private boolean e = false;
    private boolean f = true;

    static {
        f752a = null;
        f752a = new Time();
        f752a.setToNow();
    }

    @Override // com.heyzap.a.f
    public Object d(String str) {
        if (!this.f) {
            return null;
        }
        Object objD = super.d(str);
        if (objD instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) objD;
            if (jSONObject.has("display_error_message")) {
                e(jSONObject.getString("display_error_message"));
                return objD;
            }
            return objD;
        }
        return objD;
    }

    @Override // com.heyzap.a.f, com.heyzap.a.d
    protected void c(String str) {
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
            a(e);
        }
    }

    @Override // com.heyzap.a.d
    public void a(Message message) {
        try {
            super.a(message);
        } catch (Throwable th) {
            String string = "null";
            if (this.c != null) {
                string = this.c.toString();
            }
            HashMap map = new HashMap();
            map.put("http_client_url", this.d);
            map.put("http_client_params", string);
            th.printStackTrace();
            e("Something went wrong. Please try again later.");
        }
    }

    @Override // com.heyzap.a.d
    public void b(String str) {
        super.b(str);
    }

    @Override // com.heyzap.a.d
    public void b() {
        if (this.b != null) {
            try {
                this.b.dismiss();
            } catch (Throwable th) {
            }
        }
    }

    @Override // com.heyzap.a.d
    public void a(Throwable th) {
        if (this.e) {
            if ((th instanceof HttpResponseException) && ((HttpResponseException) th).getStatusCode() >= 400) {
                e("Heyzap is having a problem. Please try again later");
            } else {
                e("No internet connection");
            }
            th.printStackTrace();
        }
    }

    protected void e(String str) {
        if (this.e) {
        }
    }
}

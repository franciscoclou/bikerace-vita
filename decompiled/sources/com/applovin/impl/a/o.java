package com.applovin.impl.a;

import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d f208a;
    private final Map b = new HashMap();

    o(d dVar) {
        if (dVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.f208a = dVar;
    }

    void a() {
        synchronized (this.b) {
            this.b.clear();
        }
        d();
    }

    void a(String str) {
        a(str, 1L);
    }

    void a(String str, long j) {
        synchronized (this.b) {
            Long l = (Long) this.b.get(str);
            if (l == null) {
                l = 0L;
            }
            this.b.put(str, Long.valueOf(l.longValue() + j));
        }
        d();
    }

    long b(String str) {
        long jLongValue;
        synchronized (this.b) {
            Long l = (Long) this.b.get(str);
            if (l == null) {
                l = 0L;
            }
            jLongValue = l.longValue();
        }
        return jLongValue;
    }

    JSONObject b() {
        JSONObject jSONObject;
        synchronized (this.b) {
            jSONObject = new JSONObject();
            for (Map.Entry entry : this.b.entrySet()) {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            }
        }
        return jSONObject;
    }

    void b(String str, long j) {
        synchronized (this.b) {
            this.b.put(str, Long.valueOf(j));
        }
        d();
    }

    void c() {
        try {
            JSONObject jSONObject = new JSONObject(this.f208a.g().a().getString("stats", "{}"));
            synchronized (this.b) {
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    try {
                        String next = itKeys.next();
                        this.b.put(next, Long.valueOf(jSONObject.getLong(next)));
                    } catch (JSONException e) {
                    }
                }
            }
        } catch (Throwable th) {
            this.f208a.f().b("StatsManager", "Unable to load stats", th);
        }
    }

    void c(String str) {
        synchronized (this.b) {
            this.b.remove(str);
        }
        d();
    }

    void d() {
        try {
            SharedPreferences.Editor editorEdit = this.f208a.g().a().edit();
            editorEdit.putString("stats", b().toString());
            editorEdit.commit();
        } catch (JSONException e) {
            this.f208a.f().b("StatsManager", "Unable to save stats", e);
        }
    }
}

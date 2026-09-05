package com.applovin.impl.a;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d f206a;
    private final com.applovin.a.j b;
    private final Context c;
    private final Object[] d = new Object[j.b()];

    m(d dVar) {
        this.f206a = dVar;
        this.b = dVar.f();
        this.c = dVar.h();
    }

    private static l a(String str) {
        for (l lVar : j.a()) {
            if (lVar.b().equals(str)) {
                return lVar;
            }
        }
        return null;
    }

    private static Object a(String str, JSONObject jSONObject, Object obj) {
        if (obj instanceof Boolean) {
            return Boolean.valueOf(jSONObject.getBoolean(str));
        }
        if (obj instanceof Float) {
            return Float.valueOf((float) jSONObject.getDouble(str));
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(jSONObject.getInt(str));
        }
        if (obj instanceof Long) {
            return Long.valueOf(jSONObject.getLong(str));
        }
        if (obj instanceof String) {
            return jSONObject.getString(str);
        }
        throw new RuntimeException("SDK Error: unknown value type: " + obj.getClass());
    }

    private String e() {
        return "com.applovin.sdk." + ah.a(this.f206a.a()) + ".";
    }

    public SharedPreferences a() {
        if (this.c == null) {
            throw new IllegalArgumentException("No context specified");
        }
        return this.c.getSharedPreferences("com.applovin.sdk.1", 0);
    }

    public Object a(l lVar) {
        Object objA;
        if (lVar == null) {
            throw new IllegalArgumentException("No setting type specified");
        }
        synchronized (this.d) {
            Object obj = this.d[lVar.a()];
            objA = obj != null ? lVar.a(obj) : lVar.c();
        }
        return objA;
    }

    void a(com.applovin.a.l lVar) {
        this.b.b("SettingsManager", "Loading user-defined settings...");
        if (lVar == null) {
            return;
        }
        synchronized (this.d) {
            this.d[j.j.a()] = Boolean.valueOf(lVar.c());
            long jD = lVar.d();
            if (jD >= 0) {
                this.d[j.A.a()] = Long.valueOf(jD > 0 ? Math.max(30L, jD) : 0L);
                this.d[j.z.a()] = true;
            } else if (jD == -1) {
                this.d[j.z.a()] = false;
            }
            this.d[j.J.a()] = lVar.e();
            if (lVar instanceof f) {
                for (Map.Entry entry : ((f) lVar).b().entrySet()) {
                    this.d[((l) entry.getKey()).a()] = entry.getValue();
                }
            }
        }
    }

    public void a(l lVar, Object obj) {
        if (lVar == null) {
            throw new IllegalArgumentException("No setting type specified");
        }
        if (obj == null) {
            throw new IllegalArgumentException("No new value specified");
        }
        synchronized (this.d) {
            this.d[lVar.a()] = obj;
        }
        this.b.a("SettingsManager", "Setting update: " + lVar.b() + " set to \"" + obj + "\"");
    }

    void a(JSONObject jSONObject) {
        this.b.a("SettingsManager", "Loading settings from JSON array...");
        synchronized (this.d) {
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                if (next != null && next.length() > 0) {
                    try {
                        try {
                            l lVarA = a(next);
                            if (lVarA != null) {
                                Object objA = a(next, jSONObject, lVarA.c());
                                this.d[lVarA.a()] = objA;
                                this.b.a("SettingsManager", "Setting update: " + lVarA.b() + " set to \"" + objA + "\"");
                            } else {
                                this.b.c("SettingsManager", "Unknown setting recieved: " + next);
                            }
                        } catch (Throwable th) {
                            this.b.b("SettingsManager", "Unable to convert setting object ", th);
                        }
                    } catch (JSONException e) {
                        this.b.b("SettingsManager", "Unable to parse JSON settings array", e);
                    }
                }
            }
        }
    }

    void b() {
        if (this.c == null) {
            throw new IllegalArgumentException("No context specified");
        }
        this.b.b("SettingsManager", "Saving settings with the application...");
        String strE = e();
        SharedPreferences.Editor editorEdit = a().edit();
        synchronized (this.d) {
            for (l lVar : j.a()) {
                Object obj = this.d[lVar.a()];
                if (obj != null) {
                    String str = strE + lVar.b();
                    if (obj instanceof Boolean) {
                        editorEdit.putBoolean(str, ((Boolean) obj).booleanValue());
                    } else if (obj instanceof Float) {
                        editorEdit.putFloat(str, ((Float) obj).floatValue());
                    } else if (obj instanceof Integer) {
                        editorEdit.putInt(str, ((Integer) obj).intValue());
                    } else if (obj instanceof Long) {
                        editorEdit.putLong(str, ((Long) obj).longValue());
                    } else {
                        if (!(obj instanceof String)) {
                            throw new RuntimeException("SDK Error: unknown value: " + obj.getClass());
                        }
                        editorEdit.putString(str, (String) obj);
                    }
                }
            }
        }
        editorEdit.commit();
        this.b.a("SettingsManager", "Settings saved with the application.");
    }

    void c() {
        Object objValueOf;
        if (this.c == null) {
            throw new IllegalArgumentException("No context specified");
        }
        this.b.b("SettingsManager", "Loading settings saved with the application...");
        String strE = e();
        SharedPreferences sharedPreferencesA = a();
        synchronized (this.d) {
            for (l lVar : j.a()) {
                try {
                    String str = strE + lVar.b();
                    Object objC = lVar.c();
                    if (objC instanceof Boolean) {
                        objValueOf = Boolean.valueOf(sharedPreferencesA.getBoolean(str, ((Boolean) objC).booleanValue()));
                    } else if (objC instanceof Float) {
                        objValueOf = Float.valueOf(sharedPreferencesA.getFloat(str, ((Float) objC).floatValue()));
                    } else if (objC instanceof Integer) {
                        objValueOf = Integer.valueOf(sharedPreferencesA.getInt(str, ((Integer) objC).intValue()));
                    } else if (objC instanceof Long) {
                        objValueOf = Long.valueOf(sharedPreferencesA.getLong(str, ((Long) objC).longValue()));
                    } else {
                        if (!(objC instanceof String)) {
                            throw new RuntimeException("SDK Error: unknown value: " + objC.getClass());
                        }
                        objValueOf = sharedPreferencesA.getString(str, (String) objC);
                    }
                    this.d[lVar.a()] = objValueOf;
                } catch (Exception e) {
                    this.b.b("SettingsManager", "Unable to load \"" + lVar.b() + "\"", e);
                }
            }
        }
    }

    void d() {
        synchronized (this.d) {
            Arrays.fill(this.d, (Object) null);
        }
        SharedPreferences.Editor editorEdit = a().edit();
        editorEdit.clear();
        editorEdit.commit();
    }
}

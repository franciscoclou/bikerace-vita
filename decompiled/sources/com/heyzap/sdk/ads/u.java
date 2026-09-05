package com.heyzap.sdk.ads;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.StatFs;
import android.util.DisplayMetrics;
import com.facebook.AppEventsConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: Manager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Context f799a;
    public static k h;
    public static j i;
    private static volatile u m;
    public long b = 0;
    private int k = 0;
    private HashMap<String, c> l = new HashMap<>();
    public static long c = 1000;
    public static String d = "http://ads.heyzap.com/in_game_api/ads";
    public static String e = "http://ads.heyzap.com/in_game_api/ads";
    public static final Handler f = new Handler(Looper.getMainLooper());
    public static Boolean g = false;
    public static a j = null;

    private u() {
        com.heyzap.internal.k.b("Heyzap Ad Manager started.");
        SharedPreferences sharedPreferences = f799a.getSharedPreferences("com.heyzap.sdk.ads", 0);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        if (sharedPreferences.getBoolean("ran_once", false) ? false : true) {
            com.heyzap.internal.k.b("Running first run tasks");
            b();
            editorEdit.putBoolean("ran_once", true);
            editorEdit.commit();
        }
        c();
        g = true;
    }

    public static Boolean a() {
        return g;
    }

    public void b() {
        if (f799a != null) {
            com.heyzap.a.g gVar = new com.heyzap.a.g();
            if (com.heyzap.internal.l.a()) {
                gVar.a("platform", "amazon");
            } else {
                gVar.a("platform", "android");
            }
            final String strB = com.heyzap.internal.l.b(f799a);
            gVar.a("for_game_package", strB);
            com.heyzap.internal.a.b(f799a, e + "/register_new_game_install", gVar, new com.heyzap.internal.b() { // from class: com.heyzap.sdk.ads.u.1
                @Override // com.heyzap.a.f
                public void a(JSONObject jSONObject) {
                    try {
                        if (jSONObject.getInt("status") == 200) {
                            com.heyzap.internal.k.b("(SELF INSTALL) Package: " + strB);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            });
        }
    }

    protected void a(c cVar) {
        a(cVar.e() == null ? "null" : cVar.e());
    }

    protected void a(String str) {
        if (com.heyzap.internal.l.e(f799a)) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setAction("com.heyzap.android");
            intent.putExtra("from_ad_for_game_package", str);
            intent.putExtra("packageName", f799a.getPackageName());
            intent.addFlags(268435456);
            if (str != null) {
                intent.setComponent(new ComponentName("com.heyzap.android", "com.heyzap.android.activity.GameDetails"));
                intent.putExtra("game_package", str);
            } else {
                intent.setComponent(new ComponentName("com.heyzap.android", "com.heyzap.android.activity.CheckinHub"));
            }
            f799a.startActivity(intent);
            return;
        }
        com.heyzap.internal.l.a(f799a, String.format("action=ad_heyzap_logo&game_package=%s", str));
    }

    protected static void a(int i2, String str, String str2, Map<String, String> map, v vVar) {
        b(i2, str, str2, map, vVar, 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final int i2, final String str, final String str2, final Map<String, String> map, final v vVar, final int i3) {
        if (i3 < 1) {
            Throwable th = new Throwable("max_attempts");
            if (vVar != null) {
                vVar.a(null, str2, th);
                return;
            }
            return;
        }
        if (!com.heyzap.internal.d.a(f799a)) {
            vVar.a(null, str2, new Throwable("connection"));
            return;
        }
        com.heyzap.internal.a.b(f799a, d + "/fetch_ad", a(i2, f799a, str, str2, map), new com.heyzap.internal.b() { // from class: com.heyzap.sdk.ads.u.2
            /* JADX WARN: Code duplicated, block: B:132:0x02ae  */
            /* JADX WARN: Code duplicated, block: B:134:0x02b2  */
            /* JADX WARN: Code duplicated, block: B:138:0x02d3  */
            /* JADX WARN: Code duplicated, block: B:140:0x02d7 A[ADDED_TO_REGION] */
            /* JADX WARN: Code duplicated, block: B:34:0x0097 A[Catch: all -> 0x02fd, TRY_LEAVE, TryCatch #7 {all -> 0x02fd, blocks: (B:32:0x0093, B:34:0x0097), top: B:163:0x0093 }] */
            /* JADX WARN: Not initialized variable reg: 1, insn: 0x02f5: MOVE (r6 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:147:0x02f5 */
            @Override // com.heyzap.a.f
            public void a(JSONObject jSONObject) throws Throwable {
                c oVar;
                Exception exc;
                Boolean bool;
                Boolean bool2;
                Throwable th2;
                Boolean bool3 = true;
                try {
                    try {
                        try {
                            if (!jSONObject.has("status") || jSONObject.isNull("status") || jSONObject.getInt("status") > 200) {
                                throw new Exception("bad_response");
                            }
                            if (!jSONObject.has("impression_id") || jSONObject.isNull("impression_id")) {
                                throw new Exception("no_fill");
                            }
                            if (!jSONObject.has("promoted_game_package") || jSONObject.isNull("promoted_game_package") || jSONObject.getString("promoted_game_package").equals("")) {
                                throw new Exception("bad response");
                            }
                            if (com.heyzap.internal.l.a(jSONObject.getString("promoted_game_package"), u.f799a)) {
                                Map map2 = map;
                                int i4 = i3 - 1;
                                map2.put("rejected_impression_id", jSONObject.optString("impression_id"));
                                u.b(i2, str, str2, map2, vVar, i4);
                                if (0 != 0 && vVar != null) {
                                    vVar.a(null, str2, null);
                                    return;
                                }
                                if (vVar != null && bool3.booleanValue() && 0 != 0) {
                                    com.heyzap.internal.k.a("(FETCH) %s", null);
                                    u.d().b((c) null);
                                    vVar.a(null, str2, null);
                                    return;
                                } else {
                                    if (vVar != null && 0 == 0 && bool3.booleanValue()) {
                                        vVar.a(null, str2, new Throwable("Unknown Error"));
                                        return;
                                    }
                                    return;
                                }
                            }
                            if (jSONObject.has("ping_test")) {
                                com.heyzap.internal.k.b(jSONObject.get("ping_test"));
                                try {
                                    JSONObject jSONObject2 = jSONObject.getJSONObject("ping_test");
                                    JSONArray jSONArray = jSONObject2.getJSONArray("servers");
                                    int i5 = jSONObject2.getInt("ping_count");
                                    ArrayList arrayList = new ArrayList();
                                    for (int i6 = 0; i6 < jSONArray.length(); i6++) {
                                        arrayList.add(new com.heyzap.sdk.ads.a.a(jSONArray.getJSONObject(i6)));
                                    }
                                    new com.heyzap.sdk.ads.a.d(u.f799a, arrayList, i5, jSONObject.getString("impression_id"));
                                } catch (JSONException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            if (jSONObject.optString("creative_type", o.g).equals(w.g)) {
                                w wVar = new w(u.f799a, jSONObject, new d() { // from class: com.heyzap.sdk.ads.u.2.1
                                    @Override // com.heyzap.sdk.ads.d
                                    public void a(c cVar) {
                                        com.heyzap.internal.k.a("(FETCH) %s", cVar);
                                        u.d().b(cVar);
                                        vVar.a(cVar, str2, null);
                                    }

                                    @Override // com.heyzap.sdk.ads.d
                                    public void a(Throwable th3) {
                                        if (vVar != null) {
                                            vVar.a(null, str2, th3);
                                        }
                                    }
                                });
                                try {
                                    oVar = wVar;
                                    bool = false;
                                } catch (JSONException e3) {
                                    e = e3;
                                    oVar = wVar;
                                    bool = bool3;
                                    com.heyzap.internal.k.b(e);
                                    th2 = new Throwable("parse");
                                    if (th2 == null) {
                                    }
                                    if (vVar == null) {
                                    }
                                    if (vVar == null) {
                                        return;
                                    } else {
                                        return;
                                    }
                                } catch (Exception e4) {
                                    e = e4;
                                    oVar = wVar;
                                    try {
                                        com.heyzap.internal.k.b("Error in fetching ad: " + e.getMessage());
                                        if (!e.getMessage().equals("no_fill")) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            if (vVar != null) {
                                                vVar.a(null, str2, e);
                                            }
                                            if (e == null) {
                                            }
                                            if (vVar == null) {
                                            }
                                            if (vVar == null) {
                                                return;
                                            } else {
                                                return;
                                            }
                                        } catch (Throwable th3) {
                                            exc = e;
                                            th = th3;
                                            if (exc == null && vVar != null) {
                                                vVar.a(null, str2, exc);
                                            } else if (vVar == null && bool3.booleanValue() && oVar != null) {
                                                com.heyzap.internal.k.a("(FETCH) %s", oVar);
                                                u.d().b(oVar);
                                                vVar.a(oVar, str2, null);
                                            } else if (vVar != null && oVar == null && bool3.booleanValue()) {
                                                vVar.a(null, str2, new Throwable("Unknown Error"));
                                            }
                                            throw th;
                                        }
                                    } catch (Throwable th4) {
                                        th = th4;
                                        exc = null;
                                    }
                                } catch (Throwable th5) {
                                    th = th5;
                                    oVar = wVar;
                                    exc = null;
                                    if (exc == null) {
                                        if (vVar == null) {
                                            if (vVar != null) {
                                                vVar.a(null, str2, new Throwable("Unknown Error"));
                                            }
                                        } else if (vVar != null) {
                                            vVar.a(null, str2, new Throwable("Unknown Error"));
                                        }
                                    } else if (vVar == null) {
                                        if (vVar != null) {
                                            vVar.a(null, str2, new Throwable("Unknown Error"));
                                        }
                                    } else if (vVar != null) {
                                        vVar.a(null, str2, new Throwable("Unknown Error"));
                                    }
                                    throw th;
                                }
                            } else {
                                oVar = new o(jSONObject);
                                bool = bool3;
                            }
                            try {
                                oVar.d = str2;
                                if (0 != 0 && vVar != null) {
                                    vVar.a(null, str2, null);
                                    return;
                                }
                                if (vVar == null || !bool.booleanValue() || oVar == null) {
                                    if (vVar != null && oVar == null && bool.booleanValue()) {
                                        vVar.a(null, str2, new Throwable("Unknown Error"));
                                    }
                                } else {
                                    com.heyzap.internal.k.a("(FETCH) %s", oVar);
                                    u.d().b(oVar);
                                    vVar.a(oVar, str2, null);
                                }
                            } catch (JSONException e5) {
                                e = e5;
                                com.heyzap.internal.k.b(e);
                                th2 = new Throwable("parse");
                                if (th2 == null && vVar != null) {
                                    vVar.a(null, str2, th2);
                                    return;
                                }
                                if (vVar == null && bool.booleanValue() && oVar != null) {
                                    com.heyzap.internal.k.a("(FETCH) %s", oVar);
                                    u.d().b(oVar);
                                    vVar.a(oVar, str2, null);
                                } else if (vVar == null && oVar == null && bool.booleanValue()) {
                                    vVar.a(null, str2, new Throwable("Unknown Error"));
                                }
                            } catch (Exception e6) {
                                e = e6;
                                bool3 = bool;
                                com.heyzap.internal.k.b("Error in fetching ad: " + e.getMessage());
                                if (!e.getMessage().equals("no_fill") && !e.getMessage().equals("bad_response")) {
                                    e.printStackTrace();
                                }
                                if (vVar != null) {
                                    vVar.a(null, str2, e);
                                }
                                if (e == null && vVar != null) {
                                    vVar.a(null, str2, e);
                                    return;
                                }
                                if (vVar == null && bool3.booleanValue() && oVar != null) {
                                    com.heyzap.internal.k.a("(FETCH) %s", oVar);
                                    u.d().b(oVar);
                                    vVar.a(oVar, str2, null);
                                } else if (vVar == null && oVar == null && bool3.booleanValue()) {
                                    vVar.a(null, str2, new Throwable("Unknown Error"));
                                }
                            }
                        } catch (Throwable th6) {
                            th = th6;
                            bool3 = bool2;
                            exc = null;
                        }
                    } catch (JSONException e7) {
                        e = e7;
                        bool = bool3;
                        oVar = null;
                    }
                } catch (Exception e8) {
                    e = e8;
                    oVar = null;
                } catch (Throwable th7) {
                    th = th7;
                    oVar = null;
                    exc = null;
                }
            }

            @Override // com.heyzap.internal.b, com.heyzap.a.d
            public void a(Throwable th2) {
                com.heyzap.internal.k.b(th2);
                if (vVar != null) {
                    vVar.a(null, str2, th2);
                }
            }
        });
    }

    private static com.heyzap.a.g a(int i2, Context context, String str, String str2, Map<String, String> map) {
        int i3;
        int i4;
        com.heyzap.a.g gVar = new com.heyzap.a.g(map);
        if (i.b != null) {
            gVar.a("sdk_mediator", i.b);
        }
        if (i.f785a != null) {
            gVar.a("sdk_framework", i.f785a);
        }
        gVar.a("ping_test", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        switch (i2) {
            case 2:
                gVar.a("ad_unit", "incentivized");
                break;
            case 3:
                gVar.a("ad_unit", "video");
                break;
            default:
                gVar.a("ad_unit", "interstitial");
                break;
        }
        gVar.a("creative_type", str);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        gVar.a("connection_type", com.heyzap.internal.d.b(context));
        gVar.a("device_dpi", Float.toString(displayMetrics.density));
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            gVar.a("device_free_bytes", Long.toString(((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks())));
        } catch (Exception e2) {
            gVar.a("device_free_bytes", AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        int i5 = displayMetrics.heightPixels;
        int i6 = displayMetrics.widthPixels;
        if (map.get("orientation") == null) {
            gVar.a("orientation", displayMetrics.widthPixels > displayMetrics.heightPixels ? "landscape" : "portrait");
            i3 = i6;
            i4 = i5;
        } else if (!map.get("orientation").equals("landscape") || i5 <= i6) {
            i3 = i6;
            i4 = i5;
        } else {
            i3 = displayMetrics.heightPixels;
            i4 = displayMetrics.widthPixels;
        }
        gVar.a("device_width", i3);
        gVar.a("device_height", i4);
        gVar.a("supported_features", "chromeless,js_visibility_callback");
        gVar.a("ad_chrome", "true");
        if (str2 != null) {
            gVar.a("tag", str2);
        }
        return gVar;
    }

    protected void c() {
        String str = f799a.getCacheDir() + "/heyzap";
        try {
            if (new File(str).exists()) {
                com.heyzap.internal.l.a(new File(str));
            }
            File file = new File(str);
            file.mkdirs();
            file.deleteOnExit();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    protected void a(int i2) {
        this.k = i2;
    }

    public c b(String str) {
        try {
            c cVar = this.l.get(str);
            if (cVar == null) {
                return null;
            }
            Boolean boolA = cVar.a(f799a);
            Boolean boolH = cVar.h();
            if (boolA.booleanValue() || boolH.booleanValue()) {
                c(cVar);
                return null;
            }
            return cVar;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void b(c cVar) {
        if (f799a != null && cVar != null) {
            try {
                this.l.put(cVar.d(), cVar);
                String str = "impression." + cVar.e();
                SharedPreferences.Editor editorEdit = f799a.getSharedPreferences("com.heyzap.sdk.ads", 0).edit();
                editorEdit.putString(str, cVar.d());
                editorEdit.commit();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void c(c cVar) {
        if (cVar != null) {
            this.l.remove(cVar.d());
        }
    }

    protected static synchronized u d() {
        if (m == null) {
            m = new u();
        }
        return m;
    }

    public Object clone() {
        return null;
    }
}

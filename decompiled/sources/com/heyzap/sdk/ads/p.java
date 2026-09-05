package com.heyzap.sdk.ads;

import android.app.Activity;
import android.content.Intent;
import com.facebook.AppEventsConstants;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: InterstitialAd.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class p {
    private static Activity h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile HashMap<String, String> f789a = new HashMap<>();
    private static volatile HashMap<String, Boolean> b = new HashMap<>();
    private static String c = "interstitial,full_screen_interstitial,video,interstitial_video";
    private static String d = null;
    private static String e = null;
    private static String f = null;
    private static Boolean g = false;
    private static int i = 1;
    private static long j = 0;
    private static long k = 5000;
    private static HashMap<String, q> l = new HashMap<>();

    private static c a(String str, boolean z) {
        String strRemove;
        if (str == null || str.length() == 0) {
            str = c.e;
        }
        if (z) {
            strRemove = f789a.remove(str);
        } else {
            strRemove = f789a.get(str);
        }
        com.heyzap.internal.k.a("geting ad", str, Boolean.valueOf(z), strRemove, f789a, Integer.valueOf(f789a.size()));
        if (strRemove == null) {
            return null;
        }
        return u.d().b(strRemove);
    }

    private static Map<String, String> e() {
        HashMap map = new HashMap();
        if (g.booleanValue()) {
            map.put("debug", AppEventsConstants.EVENT_PARAM_VALUE_YES);
            map.put("use_random_strategy_v2", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        }
        if (d != null && !d.equals("")) {
            map.put("creative_id", d);
        }
        if (e != null && !e.equals("")) {
            map.put("campaign_id", e);
        }
        if (f != null && !f.equals("")) {
            map.put("creative_type", f);
        }
        return map;
    }

    public static void a(boolean z, String str) {
        if (str == null) {
            str = c.e;
        }
        c cVarA = a(str, false);
        Boolean boolValueOf = Boolean.valueOf(b.containsKey(str));
        if (cVarA == null || (z && !boolValueOf.booleanValue())) {
            Map<String, String> mapE = e();
            b.put(str, true);
            u.a(i, c, str, mapE, new v() { // from class: com.heyzap.sdk.ads.p.1
                @Override // com.heyzap.sdk.ads.v
                public void a(c cVar, String str2, Throwable th) {
                    p.b.remove(str2);
                    if (th == null) {
                        p.f789a.put(str2, cVar.d());
                        q qVar = (q) p.l.get(str2);
                        if (qVar != null) {
                            qVar.a();
                            p.l.remove(str2);
                        }
                        if (u.h != null) {
                            u.h.e(str2);
                            return;
                        }
                        return;
                    }
                    if (u.h != null) {
                        u.h.f(str2);
                    }
                }
            });
        }
    }

    public static void a(String str) {
        a(false, str);
    }

    public static Boolean b(String str) {
        if (!com.heyzap.internal.d.a(u.f799a)) {
            return false;
        }
        if (str == null || str.length() == 0) {
            str = c.e;
        }
        return Boolean.valueOf(a(str, false) != null);
    }

    public static void a(final Activity activity, String str) {
        final Class cls;
        h = activity;
        if (!com.heyzap.internal.d.a(activity)) {
            com.heyzap.internal.k.b("connectivity fail");
            if (u.h != null) {
                u.h.d(str);
                return;
            }
            return;
        }
        if (!i.a().booleanValue()) {
            i.a(activity);
        }
        String str2 = str == null ? c.e : str;
        if (System.currentTimeMillis() - j >= k) {
            j = System.currentTimeMillis();
            final c cVarA = a(str2, true);
            if (cVarA == null || cVarA.h().booleanValue()) {
                if (cVarA != null) {
                    u.d().c(cVarA);
                }
                if (u.h != null) {
                    u.h.d(str);
                }
                com.heyzap.internal.k.a("ad null or expired", cVarA);
                return;
            }
            if (cVarA.f() == w.g) {
                cls = HeyzapVideoActivity.class;
            } else {
                cls = HeyzapInterstitialActivity.class;
            }
            activity.runOnUiThread(new Runnable() { // from class: com.heyzap.sdk.ads.p.2
                @Override // java.lang.Runnable
                public void run() {
                    Intent intent = new Intent(activity, (Class<?>) cls);
                    intent.setFlags(536870912);
                    intent.putExtra("impression_id", cVarA.d());
                    intent.putExtra("ad_context", p.i);
                    intent.putExtra("action", 1);
                    activity.startActivity(intent);
                }
            });
        }
    }
}

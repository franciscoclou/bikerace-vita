package com.applovin.impl.a;

import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Map f200a = new HashMap();

    static Map a(d dVar) {
        return a("ad_data_cache", dVar);
    }

    private static Map a(String str, d dVar) {
        Map map;
        Map map2;
        SharedPreferences sharedPreferencesA;
        String string;
        Exception e;
        synchronized (f200a) {
            map = (Map) f200a.get(str);
        }
        if (map != null || (string = (sharedPreferencesA = dVar.g().a()).getString(str, "")) == null || string.length() <= 0) {
            map2 = map;
        } else {
            try {
                map2 = new HashMap();
                try {
                    for (String str2 : string.split("&")) {
                        a(str2, map2);
                    }
                    synchronized (f200a) {
                        f200a.put(str, map2);
                    }
                    dVar.f().a("AdDataCache", map2.size() + " " + str + " entries loaded from cache");
                } catch (Exception e2) {
                    e = e2;
                    dVar.f().b("AdDataCache", "Unable to load ad data", e);
                    SharedPreferences.Editor editorEdit = sharedPreferencesA.edit();
                    editorEdit.putString(str, "");
                    editorEdit.commit();
                }
            } catch (Exception e3) {
                map2 = map;
                e = e3;
            }
        }
        return map2 != null ? new HashMap(map2) : new HashMap();
    }

    private static void a(String str, Map map) {
        String[] strArrSplit = str.split("=");
        if (strArrSplit.length == 2) {
            map.put(strArrSplit[0], strArrSplit[1]);
        }
    }

    static void a(Map map, d dVar) {
        a(map, "ad_data_cache", dVar);
    }

    private static void a(Map map, String str, d dVar) {
        if (map == null) {
            throw new IllegalArgumentException("No ad aata specified");
        }
        if (dVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        try {
            synchronized (f200a) {
                Map map2 = (Map) f200a.get(str);
                if (map2 == null) {
                    map2 = new HashMap();
                }
                map2.clear();
                map2.putAll(map);
                f200a.put(str, map2);
            }
            SharedPreferences.Editor editorEdit = dVar.g().a().edit();
            editorEdit.putString(str, ah.a(map));
            editorEdit.commit();
            dVar.f().a("AdDataCache", map.size() + " " + str + " entries saved in cache");
        } catch (Exception e) {
            dVar.f().b("AdDataCache", "Unable to save ad data entries", e);
        }
    }
}

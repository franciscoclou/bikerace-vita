package com.google.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Map<String, AdSize> f647a = Collections.unmodifiableMap(new HashMap<String, AdSize>() { // from class: com.google.ads.c.1
        {
            put("banner", AdSize.BANNER);
            put("mrec", AdSize.IAB_MRECT);
            put("fullbanner", AdSize.IAB_BANNER);
            put("leaderboard", AdSize.IAB_LEADERBOARD);
            put("skyscraper", AdSize.IAB_WIDE_SKYSCRAPER);
        }
    });
    private final String b;
    private final String c;
    private final List<a> d;
    private final Integer e;
    private final Integer f;
    private final List<String> g;
    private final List<String> h;
    private final List<String> i;

    public static c a(String str) throws JSONException {
        List<String> listA;
        List<String> listA2;
        List<String> listA3;
        Integer num;
        Integer numValueOf;
        JSONObject jSONObject = new JSONObject(str);
        String string = jSONObject.getString("qdata");
        String string2 = jSONObject.has("ad_type") ? jSONObject.getString("ad_type") : null;
        JSONArray jSONArray = jSONObject.getJSONArray("ad_networks");
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(a(jSONArray.getJSONObject(i)));
        }
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("settings");
        if (jSONObjectOptJSONObject != null) {
            numValueOf = jSONObjectOptJSONObject.has("refresh") ? Integer.valueOf(jSONObjectOptJSONObject.getInt("refresh")) : null;
            Integer numValueOf2 = jSONObjectOptJSONObject.has("ad_network_timeout_millis") ? Integer.valueOf(jSONObjectOptJSONObject.getInt("ad_network_timeout_millis")) : null;
            listA2 = a(jSONObjectOptJSONObject, "imp_urls");
            listA3 = a(jSONObjectOptJSONObject, "click_urls");
            listA = a(jSONObjectOptJSONObject, "nofill_urls");
            num = numValueOf2;
        } else {
            listA = null;
            listA2 = null;
            listA3 = null;
            num = null;
            numValueOf = null;
        }
        return new c(string, string2, arrayList, numValueOf, num, listA2, listA3, listA);
    }

    public boolean a() {
        return this.f != null;
    }

    public int b() {
        return this.f.intValue();
    }

    public String c() {
        return this.b;
    }

    public boolean d() {
        return this.e != null;
    }

    public int e() {
        return this.e.intValue();
    }

    public List<a> f() {
        return this.d;
    }

    public List<String> g() {
        return this.g;
    }

    public List<String> h() {
        return this.h;
    }

    public List<String> i() {
        return this.i;
    }

    private static a a(JSONObject jSONObject) throws JSONException {
        HashMap map;
        String string = jSONObject.getString("id");
        String strOptString = jSONObject.optString("allocation_id", null);
        JSONArray jSONArray = jSONObject.getJSONArray("adapters");
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        List<String> listA = a(jSONObject, "imp_urls");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
        HashMap map2 = new HashMap(0);
        if (jSONObjectOptJSONObject != null) {
            map = new HashMap(jSONObjectOptJSONObject.length());
            Iterator<String> itKeys = jSONObjectOptJSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                map.put(next, jSONObjectOptJSONObject.getString(next));
            }
        } else {
            map = map2;
        }
        return new a(strOptString, string, arrayList, listA, map);
    }

    public com.google.ads.internal.h j() {
        if (this.c == null) {
            return null;
        }
        if ("interstitial".equals(this.c)) {
            return com.google.ads.internal.h.f680a;
        }
        AdSize adSize = f647a.get(this.c);
        if (adSize != null) {
            return com.google.ads.internal.h.a(adSize);
        }
        return null;
    }

    private static List<String> a(JSONObject jSONObject, String str) {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(str);
        if (jSONArrayOptJSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(jSONArrayOptJSONArray.length());
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            arrayList.add(jSONArrayOptJSONArray.getString(i));
        }
        return arrayList;
    }

    private c(String str, String str2, List<a> list, Integer num, Integer num2, List<String> list2, List<String> list3, List<String> list4) {
        com.google.ads.util.a.a(str);
        this.b = str;
        this.c = str2;
        this.d = list;
        this.e = num;
        this.f = num2;
        this.g = list2;
        this.h = list3;
        this.i = list4;
    }
}

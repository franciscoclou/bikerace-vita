package com.google.a.a.a;

import android.text.TextUtils;
import com.facebook.AppEventsConstants;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: MapBuilder.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ak {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Map<String, String> f578a = new HashMap();

    public ak a(String str, String str2) {
        ac.a().a(ad.MAP_BUILDER_SET);
        if (str != null) {
            this.f578a.put(str, str2);
        } else {
            ah.d(" MapBuilder.set() called with a null paramName.");
        }
        return this;
    }

    public ak a(Map<String, String> map) {
        ac.a().a(ad.MAP_BUILDER_SET_ALL);
        if (map != null) {
            this.f578a.putAll(new HashMap(map));
        }
        return this;
    }

    public Map<String, String> a() {
        return new HashMap(this.f578a);
    }

    public static ak b() {
        ac.a().a(ad.CONSTRUCT_APP_VIEW);
        ak akVar = new ak();
        akVar.a("&t", "appview");
        return akVar;
    }

    public static ak a(String str, String str2, String str3, Long l) {
        ac.a().a(ad.CONSTRUCT_EVENT);
        ak akVar = new ak();
        akVar.a("&t", "event");
        akVar.a("&ec", str);
        akVar.a("&ea", str2);
        akVar.a("&el", str3);
        akVar.a("&ev", l == null ? null : Long.toString(l.longValue()));
        return akVar;
    }

    public static ak a(String str, Boolean bool) {
        ac.a().a(ad.CONSTRUCT_EXCEPTION);
        ak akVar = new ak();
        akVar.a("&t", "exception");
        akVar.a("&exd", str);
        akVar.a("&exf", a(bool));
        return akVar;
    }

    public ak a(String str) {
        ac.a().a(ad.MAP_BUILDER_SET_CAMPAIGN_PARAMS);
        String strB = aw.b(str);
        if (!TextUtils.isEmpty(strB)) {
            Map<String, String> mapA = aw.a(strB);
            a("&cc", mapA.get("utm_content"));
            a("&cm", mapA.get("utm_medium"));
            a("&cn", mapA.get("utm_campaign"));
            a("&cs", mapA.get("utm_source"));
            a("&ck", mapA.get("utm_term"));
            a("&ci", mapA.get("utm_id"));
            a("&gclid", mapA.get("gclid"));
            a("&dclid", mapA.get("dclid"));
            a("&gmob_t", mapA.get("gmob_t"));
        }
        return this;
    }

    static String a(Boolean bool) {
        if (bool == null) {
            return null;
        }
        return bool.booleanValue() ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO;
    }
}

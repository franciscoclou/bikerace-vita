package com.google.a.a.a;

import android.text.TextUtils;
import com.facebook.AppEventsConstants;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: Tracker.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class au {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f586a;
    private final av b;
    private final Map<String, String> c;
    private long d;
    private long e;
    private final j f;
    private final ap g;
    private final i h;

    au(String str, String str2, av avVar) {
        this(str, str2, avVar, j.a(), ap.a(), i.a());
    }

    au(String str, String str2, av avVar, j jVar, ap apVar, i iVar) {
        this.c = new HashMap();
        this.d = 120000L;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Tracker name cannot be empty.");
        }
        this.f586a = str;
        this.b = avVar;
        this.c.put("&tid", str2);
        this.c.put("useSecure", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        this.f = jVar;
        this.g = apVar;
        this.h = iVar;
    }

    public void a(Map<String, String> map) {
        ac.a().a(ad.SEND);
        HashMap map2 = new HashMap();
        map2.putAll(this.c);
        if (map != null) {
            map2.putAll(map);
        }
        if (TextUtils.isEmpty((CharSequence) map2.get("&tid"))) {
            ah.d(String.format("Missing tracking id (%s) parameter.", "&tid"));
        }
        String str = (String) map2.get("&t");
        if (TextUtils.isEmpty(str)) {
            ah.d(String.format("Missing hit type (%s) parameter.", "&t"));
            str = "";
        }
        if (!str.equals("transaction") && !str.equals("item") && !b()) {
            ah.d("Too many hits sent too quickly, rate limiting invoked.");
        } else {
            this.b.a(map2);
        }
    }

    public void a(String str, String str2) {
        ac.a().a(ad.SET);
        if (str2 == null) {
            this.c.remove(str);
        } else {
            this.c.put(str, str2);
        }
    }

    synchronized boolean b() {
        boolean z;
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (this.d < 120000) {
            long j = jCurrentTimeMillis - this.e;
            if (j > 0) {
                this.d = Math.min(120000L, j + this.d);
            }
        }
        this.e = jCurrentTimeMillis;
        if (this.d >= 2000) {
            this.d -= 2000;
            z = true;
        } else {
            ah.d("Excessive tracking detected.  Tracking call ignored.");
            z = false;
        }
        return z;
    }
}

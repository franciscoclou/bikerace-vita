package com.applovin.impl.a;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ap implements com.applovin.a.n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d f189a;
    private final Context b;

    ap(d dVar) {
        if (dVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.f189a = dVar;
        this.b = dVar.h();
    }

    Map a() {
        HashMap map = new HashMap();
        Map<String, ?> all = this.b.getSharedPreferences("applovin.sdk.targeting", 0).getAll();
        if (all != null && all.size() > 0) {
            for (Map.Entry<String, ?> entry : all.entrySet()) {
                map.put(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        return map;
    }
}

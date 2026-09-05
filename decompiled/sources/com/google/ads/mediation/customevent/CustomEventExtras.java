package com.google.ads.mediation.customevent;

import com.google.ads.mediation.NetworkExtras;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class CustomEventExtras implements NetworkExtras {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final HashMap<String, Object> f705a = new HashMap<>();

    public CustomEventExtras addExtra(String str, Object obj) {
        this.f705a.put(str, obj);
        return this;
    }

    public CustomEventExtras clearExtras() {
        this.f705a.clear();
        return this;
    }

    public Object getExtra(String str) {
        return this.f705a.get(str);
    }

    public Object removeExtra(String str) {
        return this.f705a.remove(str);
    }
}

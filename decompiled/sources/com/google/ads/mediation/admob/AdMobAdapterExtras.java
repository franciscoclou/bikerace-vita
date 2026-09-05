package com.google.ads.mediation.admob;

import com.google.ads.mediation.NetworkExtras;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AdMobAdapterExtras implements NetworkExtras {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f701a;
    private Map<String, Object> b;

    public AdMobAdapterExtras() {
        this.f701a = false;
        clearExtras();
    }

    public AdMobAdapterExtras(AdMobAdapterExtras adMobAdapterExtras) {
        this();
        if (adMobAdapterExtras != null) {
            this.f701a = adMobAdapterExtras.f701a;
            this.b.putAll(adMobAdapterExtras.b);
        }
    }

    @Deprecated
    public AdMobAdapterExtras setPlusOneOptOut(boolean z) {
        return this;
    }

    @Deprecated
    public boolean getPlusOneOptOut() {
        return false;
    }

    public AdMobAdapterExtras setUseExactAdSize(boolean z) {
        this.f701a = z;
        return this;
    }

    public boolean getUseExactAdSize() {
        return this.f701a;
    }

    public Map<String, Object> getExtras() {
        return this.b;
    }

    public AdMobAdapterExtras setExtras(Map<String, Object> map) {
        if (map == null) {
            throw new IllegalArgumentException("Argument 'extras' may not be null");
        }
        this.b = map;
        return this;
    }

    public AdMobAdapterExtras clearExtras() {
        this.b = new HashMap();
        return this;
    }

    public AdMobAdapterExtras addExtra(String str, Object obj) {
        this.b.put(str, obj);
        return this;
    }
}

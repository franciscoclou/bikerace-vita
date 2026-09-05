package com.facebook.internal;

import com.facebook.Request;
import com.facebook.RequestBatch;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class CacheableRequestBatch extends RequestBatch {
    private String cacheKey;
    private boolean forceRoundTrip;

    public CacheableRequestBatch() {
    }

    public CacheableRequestBatch(Request... requestArr) {
        super(requestArr);
    }

    public final String getCacheKeyOverride() {
        return this.cacheKey;
    }

    public final void setCacheKeyOverride(String str) {
        this.cacheKey = str;
    }

    public final boolean getForceRoundTrip() {
        return this.forceRoundTrip;
    }

    public final void setForceRoundTrip(boolean z) {
        this.forceRoundTrip = z;
    }
}

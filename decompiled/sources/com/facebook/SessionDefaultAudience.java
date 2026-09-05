package com.facebook;

import com.facebook.internal.NativeProtocol;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum SessionDefaultAudience {
    NONE(null),
    ONLY_ME(NativeProtocol.AUDIENCE_ME),
    FRIENDS(NativeProtocol.AUDIENCE_FRIENDS),
    EVERYONE(NativeProtocol.AUDIENCE_EVERYONE);

    private final String nativeProtocolAudience;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static SessionDefaultAudience[] valuesCustom() {
        SessionDefaultAudience[] sessionDefaultAudienceArrValuesCustom = values();
        int length = sessionDefaultAudienceArrValuesCustom.length;
        SessionDefaultAudience[] sessionDefaultAudienceArr = new SessionDefaultAudience[length];
        System.arraycopy(sessionDefaultAudienceArrValuesCustom, 0, sessionDefaultAudienceArr, 0, length);
        return sessionDefaultAudienceArr;
    }

    SessionDefaultAudience(String str) {
        this.nativeProtocolAudience = str;
    }

    String getNativeProtocolAudience() {
        return this.nativeProtocolAudience;
    }
}

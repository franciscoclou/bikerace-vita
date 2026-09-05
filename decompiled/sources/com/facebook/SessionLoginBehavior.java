package com.facebook;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum SessionLoginBehavior {
    SSO_WITH_FALLBACK(true, true),
    SSO_ONLY(true, false),
    SUPPRESS_SSO(false, true);

    private final boolean allowsKatanaAuth;
    private final boolean allowsWebViewAuth;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static SessionLoginBehavior[] valuesCustom() {
        SessionLoginBehavior[] sessionLoginBehaviorArrValuesCustom = values();
        int length = sessionLoginBehaviorArrValuesCustom.length;
        SessionLoginBehavior[] sessionLoginBehaviorArr = new SessionLoginBehavior[length];
        System.arraycopy(sessionLoginBehaviorArrValuesCustom, 0, sessionLoginBehaviorArr, 0, length);
        return sessionLoginBehaviorArr;
    }

    SessionLoginBehavior(boolean z, boolean z2) {
        this.allowsKatanaAuth = z;
        this.allowsWebViewAuth = z2;
    }

    boolean allowsKatanaAuth() {
        return this.allowsKatanaAuth;
    }

    boolean allowsWebViewAuth() {
        return this.allowsWebViewAuth;
    }
}

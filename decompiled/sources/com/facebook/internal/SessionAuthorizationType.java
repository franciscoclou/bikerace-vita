package com.facebook.internal;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum SessionAuthorizationType {
    READ,
    PUBLISH;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static SessionAuthorizationType[] valuesCustom() {
        SessionAuthorizationType[] sessionAuthorizationTypeArrValuesCustom = values();
        int length = sessionAuthorizationTypeArrValuesCustom.length;
        SessionAuthorizationType[] sessionAuthorizationTypeArr = new SessionAuthorizationType[length];
        System.arraycopy(sessionAuthorizationTypeArrValuesCustom, 0, sessionAuthorizationTypeArr, 0, length);
        return sessionAuthorizationTypeArr;
    }
}

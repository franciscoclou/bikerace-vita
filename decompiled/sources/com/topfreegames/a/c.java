package com.topfreegames.a;

/* JADX INFO: compiled from: AnalyticsConfig.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum c {
    FLURRY,
    TOPAZ;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static c[] valuesCustom() {
        c[] cVarArrValuesCustom = values();
        int length = cVarArrValuesCustom.length;
        c[] cVarArr = new c[length];
        System.arraycopy(cVarArrValuesCustom, 0, cVarArr, 0, length);
        return cVarArr;
    }
}

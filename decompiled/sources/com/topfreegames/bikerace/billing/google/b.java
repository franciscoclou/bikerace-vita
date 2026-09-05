package com.topfreegames.bikerace.billing.google;

/* JADX INFO: compiled from: BillingConstants.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum b {
    PURCHASED,
    CANCELED,
    REFUNDED;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static b[] valuesCustom() {
        b[] bVarArrValuesCustom = values();
        int length = bVarArrValuesCustom.length;
        b[] bVarArr = new b[length];
        System.arraycopy(bVarArrValuesCustom, 0, bVarArr, 0, length);
        return bVarArr;
    }

    public static b a(int i) {
        b[] bVarArrValuesCustom = valuesCustom();
        return (i < 0 || i >= bVarArrValuesCustom.length) ? CANCELED : bVarArrValuesCustom[i];
    }
}

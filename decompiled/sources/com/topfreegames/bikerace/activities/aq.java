package com.topfreegames.bikerace.activities;

/* JADX INFO: compiled from: WorldCupShopActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
enum aq {
    REFUND,
    NOT_COMPLETED,
    REVOKED;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static aq[] valuesCustom() {
        aq[] aqVarArrValuesCustom = values();
        int length = aqVarArrValuesCustom.length;
        aq[] aqVarArr = new aq[length];
        System.arraycopy(aqVarArrValuesCustom, 0, aqVarArr, 0, length);
        return aqVarArr;
    }
}

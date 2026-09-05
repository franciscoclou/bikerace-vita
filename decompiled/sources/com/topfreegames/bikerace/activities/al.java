package com.topfreegames.bikerace.activities;

/* JADX INFO: compiled from: ShopActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
enum al {
    REFUND,
    NOT_COMPLETED,
    REVOKED;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static al[] valuesCustom() {
        al[] alVarArrValuesCustom = values();
        int length = alVarArrValuesCustom.length;
        al[] alVarArr = new al[length];
        System.arraycopy(alVarArrValuesCustom, 0, alVarArr, 0, length);
        return alVarArr;
    }
}

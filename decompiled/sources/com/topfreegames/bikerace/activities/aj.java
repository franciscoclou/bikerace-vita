package com.topfreegames.bikerace.activities;

/* JADX INFO: compiled from: ShopActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
enum aj {
    BILLING_UNAVAILABLE,
    RESTORE_OFFLINE,
    PURCHSE_REFUND,
    PURCHASE_COMPLETED,
    PURCHASE_NOT_COMPLETED,
    PURCHASE_FAILED,
    PURCHASE_CANCELED_BY_USER,
    PURCHASE_REVOKED,
    LIKE_ERROR,
    BIKE_UNLOCK,
    EASTER_EGGS_LOCATION;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static aj[] valuesCustom() {
        aj[] ajVarArrValuesCustom = values();
        int length = ajVarArrValuesCustom.length;
        aj[] ajVarArr = new aj[length];
        System.arraycopy(ajVarArrValuesCustom, 0, ajVarArr, 0, length);
        return ajVarArr;
    }
}

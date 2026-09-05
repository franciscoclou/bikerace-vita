package com.topfreegames.bikerace.activities;

/* JADX INFO: compiled from: WorldCupShopActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
enum ap {
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
    public static ap[] valuesCustom() {
        ap[] apVarArrValuesCustom = values();
        int length = apVarArrValuesCustom.length;
        ap[] apVarArr = new ap[length];
        System.arraycopy(apVarArrValuesCustom, 0, apVarArr, 0, length);
        return apVarArr;
    }
}

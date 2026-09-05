package com.topfreegames.bikerace.billing.google;

/* JADX INFO: compiled from: BillingConstants.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum c {
    RESULT_OK,
    RESULT_USER_CANCELED,
    RESULT_SERVICE_UNAVAILABLE,
    RESULT_BILLING_UNAVAILABLE,
    RESULT_ITEM_UNAVAILABLE,
    RESULT_DEVELOPER_ERROR,
    RESULT_ERROR;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static c[] valuesCustom() {
        c[] cVarArrValuesCustom = values();
        int length = cVarArrValuesCustom.length;
        c[] cVarArr = new c[length];
        System.arraycopy(cVarArrValuesCustom, 0, cVarArr, 0, length);
        return cVarArr;
    }

    public static c a(int i) {
        c[] cVarArrValuesCustom = valuesCustom();
        return (i < 0 || i >= cVarArrValuesCustom.length) ? RESULT_ERROR : cVarArrValuesCustom[i];
    }
}

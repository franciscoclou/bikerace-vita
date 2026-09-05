package com.topfreegames.e.a;

/* JADX INFO: compiled from: TopFacebookManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
enum b {
    LOGGING_IN,
    LOGGING_OUT,
    IDLE;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static b[] valuesCustom() {
        b[] bVarArrValuesCustom = values();
        int length = bVarArrValuesCustom.length;
        b[] bVarArr = new b[length];
        System.arraycopy(bVarArrValuesCustom, 0, bVarArr, 0, length);
        return bVarArr;
    }
}

package com.topfreegames.bikerace.activities;

/* JADX INFO: compiled from: MainActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
enum p {
    MULTI_SOON,
    MULTI_LOCKED,
    QUIT,
    EMERGENCY_LOCK,
    USER_LEVELS_LOCKED,
    CHOOSE_LANGUAGE;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static p[] valuesCustom() {
        p[] pVarArrValuesCustom = values();
        int length = pVarArrValuesCustom.length;
        p[] pVarArr = new p[length];
        System.arraycopy(pVarArrValuesCustom, 0, pVarArr, 0, length);
        return pVarArr;
    }
}

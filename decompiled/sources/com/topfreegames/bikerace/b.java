package com.topfreegames.bikerace;

/* JADX INFO: compiled from: Bike.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum b {
    ACCELERATING,
    BRAKING,
    IDLE,
    CRASHED;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static b[] valuesCustom() {
        b[] bVarArrValuesCustom = values();
        int length = bVarArrValuesCustom.length;
        b[] bVarArr = new b[length];
        System.arraycopy(bVarArrValuesCustom, 0, bVarArr, 0, length);
        return bVarArr;
    }
}

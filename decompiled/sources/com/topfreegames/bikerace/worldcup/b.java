package com.topfreegames.bikerace.worldcup;

/* JADX INFO: compiled from: BikePart.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum b {
    SUIT,
    HELMET,
    FRONT,
    BACK;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static b[] valuesCustom() {
        b[] bVarArrValuesCustom = values();
        int length = bVarArrValuesCustom.length;
        b[] bVarArr = new b[length];
        System.arraycopy(bVarArrValuesCustom, 0, bVarArr, 0, length);
        return bVarArr;
    }
}

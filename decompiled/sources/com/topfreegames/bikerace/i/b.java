package com.topfreegames.bikerace.i;

/* JADX INFO: compiled from: LocalizationHelper.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum b {
    ENGLISH,
    KOREAN,
    JAPANESE;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static b[] valuesCustom() {
        b[] bVarArrValuesCustom = values();
        int length = bVarArrValuesCustom.length;
        b[] bVarArr = new b[length];
        System.arraycopy(bVarArrValuesCustom, 0, bVarArr, 0, length);
        return bVarArr;
    }

    public static b a(int i) {
        try {
            return valuesCustom()[i];
        } catch (Exception e) {
            return ENGLISH;
        }
    }
}

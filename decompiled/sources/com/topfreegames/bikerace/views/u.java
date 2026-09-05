package com.topfreegames.bikerace.views;

/* JADX INFO: compiled from: UserLevelItemView.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum u {
    FIRST,
    MIDDLE,
    LAST,
    SINGLE;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static u[] valuesCustom() {
        u[] uVarArrValuesCustom = values();
        int length = uVarArrValuesCustom.length;
        u[] uVarArr = new u[length];
        System.arraycopy(uVarArrValuesCustom, 0, uVarArr, 0, length);
        return uVarArr;
    }
}

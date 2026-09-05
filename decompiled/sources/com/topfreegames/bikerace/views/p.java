package com.topfreegames.bikerace.views;

/* JADX INFO: compiled from: MultiplayerGameItemView.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum p {
    TOP,
    MIDDLE,
    BOTTOM,
    SINGLE;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static p[] valuesCustom() {
        p[] pVarArrValuesCustom = values();
        int length = pVarArrValuesCustom.length;
        p[] pVarArr = new p[length];
        System.arraycopy(pVarArrValuesCustom, 0, pVarArr, 0, length);
        return pVarArr;
    }
}

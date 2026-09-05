package com.topfreegames.bikerace.views;

/* JADX INFO: compiled from: GiftItemView.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum g {
    TOP,
    MIDDLE,
    BOTTOM,
    SINGLE;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static g[] valuesCustom() {
        g[] gVarArrValuesCustom = values();
        int length = gVarArrValuesCustom.length;
        g[] gVarArr = new g[length];
        System.arraycopy(gVarArrValuesCustom, 0, gVarArr, 0, length);
        return gVarArr;
    }
}

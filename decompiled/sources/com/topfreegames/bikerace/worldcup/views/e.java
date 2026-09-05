package com.topfreegames.bikerace.worldcup.views;

/* JADX INFO: compiled from: WorldCupShopTabView.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum e {
    BIKES,
    SLOT_ORDINARY,
    SLOT_RARE,
    GEM_SHOP;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static e[] valuesCustom() {
        e[] eVarArrValuesCustom = values();
        int length = eVarArrValuesCustom.length;
        e[] eVarArr = new e[length];
        System.arraycopy(eVarArrValuesCustom, 0, eVarArr, 0, length);
        return eVarArr;
    }
}

package com.topfreegames.bikerace.worldcup;

/* JADX INFO: compiled from: WorldCupViewManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum r {
    BIKE,
    SLOT_ORDINARY,
    SLOT_RARE,
    GEMSHOP,
    DAILY_BONUS;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static r[] valuesCustom() {
        r[] rVarArrValuesCustom = values();
        int length = rVarArrValuesCustom.length;
        r[] rVarArr = new r[length];
        System.arraycopy(rVarArrValuesCustom, 0, rVarArr, 0, length);
        return rVarArr;
    }
}

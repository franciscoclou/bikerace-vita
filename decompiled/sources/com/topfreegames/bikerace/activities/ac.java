package com.topfreegames.bikerace.activities;

/* JADX INFO: compiled from: OptionsActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
enum ac {
    ACCOUNT;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static ac[] valuesCustom() {
        ac[] acVarArrValuesCustom = values();
        int length = acVarArrValuesCustom.length;
        ac[] acVarArr = new ac[length];
        System.arraycopy(acVarArrValuesCustom, 0, acVarArr, 0, length);
        return acVarArr;
    }
}

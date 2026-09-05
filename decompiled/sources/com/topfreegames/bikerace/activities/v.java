package com.topfreegames.bikerace.activities;

/* JADX INFO: compiled from: MultiplayerMainActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
enum v {
    SMS,
    EMAIL;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static v[] valuesCustom() {
        v[] vVarArrValuesCustom = values();
        int length = vVarArrValuesCustom.length;
        v[] vVarArr = new v[length];
        System.arraycopy(vVarArrValuesCustom, 0, vVarArr, 0, length);
        return vVarArr;
    }
}

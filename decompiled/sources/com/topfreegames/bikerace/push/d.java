package com.topfreegames.bikerace.push;

/* JADX INFO: compiled from: PushConfig.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum d {
    CHALLENGE,
    POKE,
    FIRST_LOGIN;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static d[] valuesCustom() {
        d[] dVarArrValuesCustom = values();
        int length = dVarArrValuesCustom.length;
        d[] dVarArr = new d[length];
        System.arraycopy(dVarArrValuesCustom, 0, dVarArr, 0, length);
        return dVarArr;
    }
}

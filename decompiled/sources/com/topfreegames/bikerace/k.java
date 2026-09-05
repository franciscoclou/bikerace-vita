package com.topfreegames.bikerace;

/* JADX INFO: compiled from: Game.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
enum k {
    HELP,
    WAITING_START,
    RUNNING,
    RUNNING_CRASH,
    PAUSED;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static k[] valuesCustom() {
        k[] kVarArrValuesCustom = values();
        int length = kVarArrValuesCustom.length;
        k[] kVarArr = new k[length];
        System.arraycopy(kVarArrValuesCustom, 0, kVarArr, 0, length);
        return kVarArr;
    }
}

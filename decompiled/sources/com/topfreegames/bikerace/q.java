package com.topfreegames.bikerace;

/* JADX INFO: compiled from: Game.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum q {
    WATCHING,
    PLAYING_AGAINST,
    PLAYING_FIRST;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static q[] valuesCustom() {
        q[] qVarArrValuesCustom = values();
        int length = qVarArrValuesCustom.length;
        q[] qVarArr = new q[length];
        System.arraycopy(qVarArrValuesCustom, 0, qVarArr, 0, length);
        return qVarArr;
    }
}

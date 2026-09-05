package com.topfreegames.bikerace.multiplayer;

/* JADX INFO: compiled from: MultiplayerManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum v {
    GAME_START_VIA_REGULAR_SELECTION,
    GAME_START_AGAINST_RANDOM_USER,
    GAME_START_VIA_LINK;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static v[] valuesCustom() {
        v[] vVarArrValuesCustom = values();
        int length = vVarArrValuesCustom.length;
        v[] vVarArr = new v[length];
        System.arraycopy(vVarArrValuesCustom, 0, vVarArr, 0, length);
        return vVarArr;
    }
}

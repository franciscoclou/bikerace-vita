package com.topfreegames.bikerace;

/* JADX INFO: compiled from: RuntimeTestConfig.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum bi {
    FAKE_NUM_STARS,
    FAKE_NUM_MULTI_WINS,
    FAKE_NUM_GEMS,
    FAKE_NUM_COINS,
    MULTIPLAYER_FORCE_SELECT_HIGHEST_LEVEL;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static bi[] valuesCustom() {
        bi[] biVarArrValuesCustom = values();
        int length = biVarArrValuesCustom.length;
        bi[] biVarArr = new bi[length];
        System.arraycopy(biVarArrValuesCustom, 0, biVarArr, 0, length);
        return biVarArr;
    }
}

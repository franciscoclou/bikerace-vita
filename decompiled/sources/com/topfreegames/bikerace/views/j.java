package com.topfreegames.bikerace.views;

/* JADX INFO: compiled from: LevelItemView.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum j {
    UNLOCKED_NO_DELETE,
    UNLOCKED_DELETE,
    LOCKED,
    EMPTY;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static j[] valuesCustom() {
        j[] jVarArrValuesCustom = values();
        int length = jVarArrValuesCustom.length;
        j[] jVarArr = new j[length];
        System.arraycopy(jVarArrValuesCustom, 0, jVarArr, 0, length);
        return jVarArr;
    }
}

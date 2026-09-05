package com.topfreegames.bikerace;

/* JADX INFO: compiled from: GameAudio.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
enum x {
    MUSIC,
    SOUND_FX;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static x[] valuesCustom() {
        x[] xVarArrValuesCustom = values();
        int length = xVarArrValuesCustom.length;
        x[] xVarArr = new x[length];
        System.arraycopy(xVarArrValuesCustom, 0, xVarArr, 0, length);
        return xVarArr;
    }
}

package com.topfreegames.bikerace;

/* JADX INFO: compiled from: GameAudio.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
enum y {
    STOPPED,
    SLOW,
    ACCELERATING,
    FAST,
    DEACCELERATING;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static y[] valuesCustom() {
        y[] yVarArrValuesCustom = values();
        int length = yVarArrValuesCustom.length;
        y[] yVarArr = new y[length];
        System.arraycopy(yVarArrValuesCustom, 0, yVarArr, 0, length);
        return yVarArr;
    }
}

package com.topfreegames.bikerace.multiplayer;

/* JADX INFO: compiled from: MultiplayerManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
enum p {
    NOT_LOGGED_IN,
    LOGGED_IN_AS_GUEST,
    LOGGED_IN_AS_FACEBOOK_USER;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static p[] valuesCustom() {
        p[] pVarArrValuesCustom = values();
        int length = pVarArrValuesCustom.length;
        p[] pVarArr = new p[length];
        System.arraycopy(pVarArrValuesCustom, 0, pVarArr, 0, length);
        return pVarArr;
    }
}

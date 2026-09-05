package com.topfreegames.bikerace.activities;

/* JADX INFO: compiled from: MultiplayerRankingActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
enum x {
    NEW_MULTIPLAYER_GAME,
    FIND,
    LINK_UNAVAILABLE,
    SMS_UNAVAILABLE,
    SHARE_UNAVAILABLE,
    NO_EMAIL_CLIENT,
    NO_SMS_CLIENT;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static x[] valuesCustom() {
        x[] xVarArrValuesCustom = values();
        int length = xVarArrValuesCustom.length;
        x[] xVarArr = new x[length];
        System.arraycopy(xVarArrValuesCustom, 0, xVarArr, 0, length);
        return xVarArr;
    }
}

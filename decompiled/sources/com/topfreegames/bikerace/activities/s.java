package com.topfreegames.bikerace.activities;

/* JADX INFO: compiled from: MultiplayerMainActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
enum s {
    MAX_GAMES_REACHED,
    NEW_MULTIPLAYER_GAME,
    FIND,
    LINK_UNAVAILABLE,
    SMS_UNAVAILABLE,
    SHARE_UNAVAILABLE,
    NO_EMAIL_CLIENT,
    NO_SMS_CLIENT,
    GUEST_NAME_INPUT,
    WORLD_NOT_AVAILABLE,
    RANDOM_UNAVAILABLE,
    BIKE_UNLOCK,
    TIMESTAMP_ERROR,
    LINK_OPEN_ERROR;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static s[] valuesCustom() {
        s[] sVarArrValuesCustom = values();
        int length = sVarArrValuesCustom.length;
        s[] sVarArr = new s[length];
        System.arraycopy(sVarArrValuesCustom, 0, sVarArr, 0, length);
        return sVarArr;
    }
}

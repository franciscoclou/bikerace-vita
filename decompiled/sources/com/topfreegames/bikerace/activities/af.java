package com.topfreegames.bikerace.activities;

/* JADX INFO: compiled from: PlayActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
enum af {
    SKIP,
    RATING,
    MULTIPLAYER_LOCKED,
    MULTIPLAYER_UNLOCKED,
    SHOP_OFFER,
    UNLOCK_BIKE,
    RATING_FAILED_GOOGLE_PLAY,
    RATING_FAILED_AMAZON,
    RATING_FAILED_SAMSUNG,
    NEED_FB_LOGIN,
    GIFT_OFFLINE,
    RATING_EXTRA_STEP;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static af[] valuesCustom() {
        af[] afVarArrValuesCustom = values();
        int length = afVarArrValuesCustom.length;
        af[] afVarArr = new af[length];
        System.arraycopy(afVarArrValuesCustom, 0, afVarArr, 0, length);
        return afVarArr;
    }
}

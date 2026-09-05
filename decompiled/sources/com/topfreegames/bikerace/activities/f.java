package com.topfreegames.bikerace.activities;

/* JADX INFO: compiled from: CustomLevelsActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
enum f {
    OFFER_TRACKS,
    ERROR_DOWNLOAD_TRACK,
    ALREADY_HAS,
    LEVEL_EDITOR_INFO,
    NEED_FB_LOGIN,
    GIFTS,
    SHOULD_SEND_GIFT_BACK,
    EXPIRE_INFO,
    GIFT_OFFLINE;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static f[] valuesCustom() {
        f[] fVarArrValuesCustom = values();
        int length = fVarArrValuesCustom.length;
        f[] fVarArr = new f[length];
        System.arraycopy(fVarArrValuesCustom, 0, fVarArr, 0, length);
        return fVarArr;
    }
}

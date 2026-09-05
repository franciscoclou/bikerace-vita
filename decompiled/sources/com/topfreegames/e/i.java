package com.topfreegames.e;

/* JADX INFO: compiled from: TopFacebookRamdomAppUserRequestStatus.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum i {
    FAILED,
    EXCEEDED_MAX_DAILY_REQUESTS,
    SUCCESSED;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static i[] valuesCustom() {
        i[] iVarArrValuesCustom = values();
        int length = iVarArrValuesCustom.length;
        i[] iVarArr = new i[length];
        System.arraycopy(iVarArrValuesCustom, 0, iVarArr, 0, length);
        return iVarArr;
    }
}

package com.topfreegames.bikerace.activities;

/* JADX INFO: compiled from: MultiplayerRankingActivity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
enum aa {
    SMS,
    EMAIL;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static aa[] valuesCustom() {
        aa[] aaVarArrValuesCustom = values();
        int length = aaVarArrValuesCustom.length;
        aa[] aaVarArr = new aa[length];
        System.arraycopy(aaVarArrValuesCustom, 0, aaVarArr, 0, length);
        return aaVarArr;
    }
}

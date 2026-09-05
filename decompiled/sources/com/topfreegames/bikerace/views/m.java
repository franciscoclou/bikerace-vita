package com.topfreegames.bikerace.views;

/* JADX INFO: compiled from: MultiplayerFriendItemView.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum m {
    TOP,
    MIDDLE,
    BOTTOM,
    SINGLE;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static m[] valuesCustom() {
        m[] mVarArrValuesCustom = values();
        int length = mVarArrValuesCustom.length;
        m[] mVarArr = new m[length];
        System.arraycopy(mVarArrValuesCustom, 0, mVarArr, 0, length);
        return mVarArr;
    }
}

package com.topfreegames.e;

/* JADX INFO: compiled from: TopFacebookFriendTypes.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum e {
    ALL_FRIENDS,
    FRIENDS_HAVE_APP,
    FRIENDS_DONT_HAVE_APP;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static e[] valuesCustom() {
        e[] eVarArrValuesCustom = values();
        int length = eVarArrValuesCustom.length;
        e[] eVarArr = new e[length];
        System.arraycopy(eVarArrValuesCustom, 0, eVarArr, 0, length);
        return eVarArr;
    }
}

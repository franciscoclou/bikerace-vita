package com.topfreegames.e.b.a;

/* JADX INFO: compiled from: TopFacebookAppRequestManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
enum j {
    CREATE_APP_USER,
    CREATE_USER_USER,
    READ,
    DELETE;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static j[] valuesCustom() {
        j[] jVarArrValuesCustom = values();
        int length = jVarArrValuesCustom.length;
        j[] jVarArr = new j[length];
        System.arraycopy(jVarArrValuesCustom, 0, jVarArr, 0, length);
        return jVarArr;
    }
}

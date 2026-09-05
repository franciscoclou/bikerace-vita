package org.c.d;

/* JADX INFO: compiled from: Verb.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum l {
    GET,
    POST,
    PUT,
    DELETE,
    HEAD,
    OPTIONS,
    TRACE;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static l[] valuesCustom() {
        l[] lVarArrValuesCustom = values();
        int length = lVarArrValuesCustom.length;
        l[] lVarArr = new l[length];
        System.arraycopy(lVarArrValuesCustom, 0, lVarArr, 0, length);
        return lVarArr;
    }
}

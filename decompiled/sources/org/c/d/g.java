package org.c.d;

/* JADX INFO: compiled from: Request.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum g {
    CONTENT_JSON("application/json"),
    CONTENT_URL_FORM("application/x-www-form-urlencoded");

    private String c;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static g[] valuesCustom() {
        g[] gVarArrValuesCustom = values();
        int length = gVarArrValuesCustom.length;
        g[] gVarArr = new g[length];
        System.arraycopy(gVarArrValuesCustom, 0, gVarArr, 0, length);
        return gVarArr;
    }

    g(String str) {
        this.c = str;
    }

    public String a() {
        return this.c;
    }
}

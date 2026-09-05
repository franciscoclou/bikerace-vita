package com.facebook;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum HttpMethod {
    GET,
    POST,
    DELETE;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static HttpMethod[] valuesCustom() {
        HttpMethod[] httpMethodArrValuesCustom = values();
        int length = httpMethodArrValuesCustom.length;
        HttpMethod[] httpMethodArr = new HttpMethod[length];
        System.arraycopy(httpMethodArrValuesCustom, 0, httpMethodArr, 0, length);
        return httpMethodArr;
    }
}

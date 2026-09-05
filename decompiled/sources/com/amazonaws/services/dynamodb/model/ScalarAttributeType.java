package com.amazonaws.services.dynamodb.model;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum ScalarAttributeType {
    S("S"),
    N("N"),
    B("B");

    private String value;

    ScalarAttributeType(String str) {
        this.value = str;
    }

    public static ScalarAttributeType fromValue(String str) {
        if (str == null || "".equals(str)) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if ("S".equals(str)) {
            return S;
        }
        if ("N".equals(str)) {
            return N;
        }
        if ("B".equals(str)) {
            return B;
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}

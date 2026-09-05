package com.amazonaws.services.dynamodb.model;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum ReturnValue {
    NONE("NONE"),
    ALL_OLD("ALL_OLD"),
    UPDATED_OLD("UPDATED_OLD"),
    ALL_NEW("ALL_NEW"),
    UPDATED_NEW("UPDATED_NEW");

    private String value;

    ReturnValue(String str) {
        this.value = str;
    }

    public static ReturnValue fromValue(String str) {
        if (str == null || "".equals(str)) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if ("NONE".equals(str)) {
            return NONE;
        }
        if ("ALL_OLD".equals(str)) {
            return ALL_OLD;
        }
        if ("UPDATED_OLD".equals(str)) {
            return UPDATED_OLD;
        }
        if ("ALL_NEW".equals(str)) {
            return ALL_NEW;
        }
        if ("UPDATED_NEW".equals(str)) {
            return UPDATED_NEW;
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}

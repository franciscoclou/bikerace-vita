package com.amazonaws.services.dynamodb.model;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum AttributeAction {
    ADD("ADD"),
    PUT("PUT"),
    DELETE("DELETE");

    private String value;

    AttributeAction(String str) {
        this.value = str;
    }

    public static AttributeAction fromValue(String str) {
        if (str == null || "".equals(str)) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if ("ADD".equals(str)) {
            return ADD;
        }
        if ("PUT".equals(str)) {
            return PUT;
        }
        if ("DELETE".equals(str)) {
            return DELETE;
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}

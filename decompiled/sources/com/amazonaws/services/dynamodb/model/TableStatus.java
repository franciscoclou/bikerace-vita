package com.amazonaws.services.dynamodb.model;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum TableStatus {
    CREATING("CREATING"),
    UPDATING("UPDATING"),
    DELETING("DELETING"),
    ACTIVE("ACTIVE");

    private String value;

    TableStatus(String str) {
        this.value = str;
    }

    public static TableStatus fromValue(String str) {
        if (str == null || "".equals(str)) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if ("CREATING".equals(str)) {
            return CREATING;
        }
        if ("UPDATING".equals(str)) {
            return UPDATING;
        }
        if ("DELETING".equals(str)) {
            return DELETING;
        }
        if ("ACTIVE".equals(str)) {
            return ACTIVE;
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}

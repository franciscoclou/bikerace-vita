package com.amazonaws;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum i {
    HTTP("http"),
    HTTPS("https");

    private final String c;

    i(String str) {
        this.c = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.c;
    }
}

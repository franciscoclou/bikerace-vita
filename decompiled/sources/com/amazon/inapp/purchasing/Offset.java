package com.amazon.inapp.purchasing;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class Offset {
    private String _encodedOffset;
    private static final String BEGINNING_ENCODED = "BEGINNING";
    public static final Offset BEGINNING = new Offset(BEGINNING_ENCODED);

    Offset(String str) {
        this._encodedOffset = str;
    }

    public static Offset fromString(String str) {
        return BEGINNING_ENCODED.equals(str) ? BEGINNING : new Offset(str);
    }

    public String toString() {
        return this._encodedOffset;
    }
}

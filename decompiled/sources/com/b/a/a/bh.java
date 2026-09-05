package com.b.a.a;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum bh {
    DEVELOPER(1),
    USER_SIDELOAD(2),
    TEST_DISTRIBUTION(3),
    APP_STORE(4);

    private final int e;

    bh(int i) {
        this.e = i;
    }

    public final int a() {
        return this.e;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return Integer.toString(this.e);
    }

    public static bh a(String str) {
        if (str != null) {
            return APP_STORE;
        }
        return DEVELOPER;
    }
}

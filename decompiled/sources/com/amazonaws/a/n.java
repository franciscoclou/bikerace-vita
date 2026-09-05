package com.amazonaws.a;

import com.facebook.AppEventsConstants;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum n {
    V1(AppEventsConstants.EVENT_PARAM_VALUE_YES),
    V2("2");

    private String c;

    n(String str) {
        this.c = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.c;
    }
}

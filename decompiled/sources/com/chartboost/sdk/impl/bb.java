package com.chartboost.sdk.impl;

import java.io.Serializable;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class bb implements Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f437a;

    public String a() {
        return this.f437a;
    }

    public boolean equals(Object obj) {
        String str;
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof bb) {
            str = ((bb) obj).f437a;
        } else {
            if (!(obj instanceof String)) {
                return false;
            }
            str = (String) obj;
        }
        if (this.f437a != null) {
            if (this.f437a.equals(str)) {
                return true;
            }
        } else if (str == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.f437a != null) {
            return this.f437a.hashCode();
        }
        return 0;
    }

    public String toString() {
        return this.f437a;
    }
}

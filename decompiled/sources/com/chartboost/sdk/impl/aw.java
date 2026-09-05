package com.chartboost.sdk.impl;

import java.io.Serializable;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class aw implements Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final String f428a;

    public String a() {
        return this.f428a;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof aw)) {
            return false;
        }
        return this.f428a.equals(((aw) obj).f428a);
    }

    public int hashCode() {
        return this.f428a.hashCode();
    }

    public String toString() {
        return a();
    }
}

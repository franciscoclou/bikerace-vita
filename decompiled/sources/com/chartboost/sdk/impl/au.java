package com.chartboost.sdk.impl;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class au implements Serializable, Comparable<au> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final boolean f426a = Boolean.getBoolean("DEBUG.DBTIMESTAMP");
    final int b = 0;
    final Date c = null;

    public int a() {
        if (this.c == null) {
            return 0;
        }
        return (int) (this.c.getTime() / 1000);
    }

    public int b() {
        return this.b;
    }

    public String toString() {
        return "TS time:" + this.c + " inc:" + this.b;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(au auVar) {
        return a() != auVar.a() ? a() - auVar.a() : b() - auVar.b();
    }

    public int hashCode() {
        return ((this.b + 31) * 31) + a();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof au)) {
            return false;
        }
        au auVar = (au) obj;
        return a() == auVar.a() && b() == auVar.b();
    }
}

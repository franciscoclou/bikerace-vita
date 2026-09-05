package com.chartboost.sdk.impl;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ab {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Object f413a;
    final String b;

    public String toString() {
        return "{ \"$ref\" : \"" + this.b + "\", \"$id\" : \"" + this.f413a + "\" }";
    }

    public Object a() {
        return this.f413a;
    }

    public String b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ab abVar = (ab) obj;
        if (this.f413a == null ? abVar.f413a != null : !this.f413a.equals(abVar.f413a)) {
            return false;
        }
        if (this.b != null) {
            if (this.b.equals(abVar.b)) {
                return true;
            }
        } else if (abVar.b == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((this.f413a != null ? this.f413a.hashCode() : 0) * 31) + (this.b != null ? this.b.hashCode() : 0);
    }
}

package com.applovin.a;

import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final g f156a = new g("REGULAR");
    public static final g b = new g("VIDEOA");
    private final String c;

    public g(String str) {
        this.c = str;
    }

    public static g a(String str) {
        return str.toUpperCase().equals(b.a()) ? b : f156a;
    }

    public static Set b() {
        HashSet hashSet = new HashSet(2);
        hashSet.add(f156a);
        hashSet.add(b);
        return hashSet;
    }

    public String a() {
        return this.c.toUpperCase();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        g gVar = (g) obj;
        if (this.c != null) {
            if (this.c.equals(gVar.c)) {
                return true;
            }
        } else if (gVar.c == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.c != null) {
            return this.c.hashCode();
        }
        return 0;
    }

    public String toString() {
        return a();
    }
}

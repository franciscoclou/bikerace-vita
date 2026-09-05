package com.topfreegames.bikerace.b;

import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: User.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b {
    private String e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Set<String> f1130a = new HashSet();
    private Integer b = 0;
    private Integer c = 0;
    private Integer d = 0;
    private Set<String> f = new HashSet();
    private Set<String> g = new HashSet();

    public b() {
    }

    public b(String str) {
        this.e = str;
    }

    public Integer a() {
        return this.b;
    }

    public Integer b() {
        return this.c;
    }

    public String c() {
        return this.e;
    }

    public Set<String> d() {
        return this.g;
    }

    public Integer e() {
        return this.d;
    }

    public void a(Set<String> set) {
        this.f1130a = set;
    }

    public void a(Integer num) {
        this.b = num;
    }

    public void b(Integer num) {
        this.c = num;
    }

    public void a(String str) {
        this.e = str;
    }

    public void b(Set<String> set) {
        this.f = set;
    }

    public void c(Set<String> set) {
        this.g = set;
    }

    public void c(Integer num) {
        this.d = num;
    }

    public int hashCode() {
        return (((this.f == null ? 0 : this.f.hashCode()) + (((this.e == null ? 0 : this.e.hashCode()) + (((this.d == null ? 0 : this.d.hashCode()) + (((this.c == null ? 0 : this.c.hashCode()) + (((this.b == null ? 0 : this.b.hashCode()) + (((this.f1130a == null ? 0 : this.f1130a.hashCode()) + 31) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + (this.g != null ? this.g.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            b bVar = (b) obj;
            if (this.f1130a == null) {
                if (bVar.f1130a != null) {
                    return false;
                }
            } else if (!this.f1130a.equals(bVar.f1130a)) {
                return false;
            }
            if (this.b == null) {
                if (bVar.b != null) {
                    return false;
                }
            } else if (!this.b.equals(bVar.b)) {
                return false;
            }
            if (this.c == null) {
                if (bVar.c != null) {
                    return false;
                }
            } else if (!this.c.equals(bVar.c)) {
                return false;
            }
            if (this.d == null) {
                if (bVar.d != null) {
                    return false;
                }
            } else if (!this.d.equals(bVar.d)) {
                return false;
            }
            if (this.e == null) {
                if (bVar.e != null) {
                    return false;
                }
            } else if (!this.e.equals(bVar.e)) {
                return false;
            }
            if (this.f == null) {
                if (bVar.f != null) {
                    return false;
                }
            } else if (!this.f.equals(bVar.f)) {
                return false;
            }
            if (this.g == null) {
                return bVar.g == null;
            }
            return this.g.equals(bVar.g);
        }
        return false;
    }
}

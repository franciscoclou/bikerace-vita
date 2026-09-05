package org.c.d;

/* JADX INFO: compiled from: Parameter.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class d implements Comparable<d> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f1617a;
    private final String b;

    public d(String str, String str2) {
        this.f1617a = str;
        this.b = str2;
    }

    public String a() {
        return org.c.g.b.a(this.f1617a).concat("=").concat(org.c.g.b.a(this.b));
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return dVar.f1617a.equals(this.f1617a) && dVar.b.equals(this.b);
    }

    public int hashCode() {
        return this.f1617a.hashCode() + this.b.hashCode();
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(d dVar) {
        int iCompareTo = this.f1617a.compareTo(dVar.f1617a);
        return iCompareTo != 0 ? iCompareTo : this.b.compareTo(dVar.b);
    }
}

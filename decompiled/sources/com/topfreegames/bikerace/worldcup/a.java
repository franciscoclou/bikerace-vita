package com.topfreegames.bikerace.worldcup;

/* JADX INFO: compiled from: BikePart.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.topfreegames.bikerace.c f1422a;
    private b b;

    public a(com.topfreegames.bikerace.c cVar, b bVar) {
        this.f1422a = cVar;
        this.b = bVar;
    }

    public a(a aVar) {
        this.f1422a = aVar.f1422a;
        this.b = aVar.b;
    }

    public com.topfreegames.bikerace.c a() {
        return this.f1422a;
    }

    public b b() {
        return this.b;
    }

    public int c() {
        return e.a(this);
    }

    public int d() {
        return e.b(this);
    }

    public int hashCode() {
        return (((this.f1422a == null ? 0 : this.f1422a.hashCode()) + 31) * 31) + (this.b != null ? this.b.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f1422a == aVar.f1422a && this.b == aVar.b;
    }
}

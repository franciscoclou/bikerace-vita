package com.topfreegames.bikerace.views;

/* JADX INFO: compiled from: UserLevelItemView.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f1420a;
    private String b;
    private com.topfreegames.bikerace.f.a c;
    private boolean d = false;

    public v(com.topfreegames.bikerace.h.a.a aVar) {
        this.f1420a = null;
        this.b = null;
        this.c = null;
        if (aVar == null) {
            throw new IllegalArgumentException("Info cannot be null!");
        }
        this.f1420a = aVar.a();
        this.b = aVar.b();
        this.c = null;
    }

    public v(com.topfreegames.bikerace.f.a aVar) {
        this.f1420a = null;
        this.b = null;
        this.c = null;
        if (aVar == null) {
            throw new IllegalArgumentException("Gift cannot be null!");
        }
        this.f1420a = aVar.h();
        this.b = "";
        this.c = aVar;
    }

    public static v a(com.topfreegames.bikerace.f.a aVar) {
        v vVar = new v(aVar);
        vVar.d = true;
        return vVar;
    }

    public String a() {
        return this.f1420a;
    }

    public String b() {
        return this.b;
    }

    public boolean c() {
        return this.c != null;
    }

    public com.topfreegames.bikerace.f.a d() {
        return this.c;
    }

    public boolean e() {
        return this.d;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof v)) {
            return false;
        }
        v vVar = (v) obj;
        if (e() && vVar.e()) {
            return true;
        }
        if (e() || vVar.e()) {
            return false;
        }
        if (c() && vVar.c()) {
            com.topfreegames.bikerace.f.a aVarD = d();
            com.topfreegames.bikerace.f.a aVarD2 = vVar.d();
            if (aVarD == aVarD2) {
                return true;
            }
            return (aVarD == null || aVarD2 == null || !aVarD.a().equals(aVarD2.a())) ? false : true;
        }
        if (this.f1420a == null && vVar.f1420a == null) {
            return true;
        }
        return this.f1420a != null && this.f1420a.equals(vVar.a());
    }
}

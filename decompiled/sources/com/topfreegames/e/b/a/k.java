package com.topfreegames.e.b.a;

/* JADX INFO: compiled from: TopFacebookAppRequestManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private j f1519a;
    private Object b;

    public k(e eVar) {
        if (eVar == null) {
            throw new IllegalArgumentException("Handler cannot be null!");
        }
        this.f1519a = j.CREATE_USER_USER;
        this.b = eVar;
    }

    public k(n nVar) {
        if (nVar == null) {
            throw new IllegalArgumentException("Handler cannot be null!");
        }
        this.f1519a = j.READ;
        this.b = nVar;
    }

    public k(g gVar) {
        if (gVar == null) {
            throw new IllegalArgumentException("Handler cannot be null!");
        }
        this.f1519a = j.DELETE;
        this.b = gVar;
    }

    public j a() {
        return this.f1519a;
    }

    public Object b() {
        return this.b;
    }
}

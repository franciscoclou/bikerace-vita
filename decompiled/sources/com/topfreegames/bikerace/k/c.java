package com.topfreegames.bikerace.k;

import org.c.d.k;

/* JADX INFO: compiled from: RemoteData.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class c extends org.c.a.a.b {
    private c() {
    }

    /* synthetic */ c(c cVar) {
        this();
    }

    @Override // org.c.a.a.b
    public String a() {
        return "http://data-api.topfreegames.com/access_token";
    }

    @Override // org.c.a.a.b
    public String b() {
        return "http://data-api.topfreegames.com/request_token";
    }

    @Override // org.c.a.a.b
    public String a(k kVar) {
        return "http://data-api.topfreegames.com";
    }

    @Override // org.c.a.a.b
    public org.c.e.d c() {
        return new d(null);
    }
}

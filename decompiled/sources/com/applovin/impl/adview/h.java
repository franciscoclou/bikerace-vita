package com.applovin.impl.adview;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class h implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ com.applovin.a.a f234a;
    final /* synthetic */ e b;

    h(e eVar, com.applovin.a.a aVar) {
        this.b = eVar;
        this.f234a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.b.g != null) {
            this.b.g.a(this.f234a);
        }
    }
}

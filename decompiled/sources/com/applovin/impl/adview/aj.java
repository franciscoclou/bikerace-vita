package com.applovin.impl.adview;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class aj implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ com.applovin.a.a f227a;
    final /* synthetic */ af b;

    aj(af afVar, com.applovin.a.a aVar) {
        this.b = afVar;
        this.f227a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.c.a(this.f227a);
    }
}

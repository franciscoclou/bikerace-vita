package com.applovin.impl.adview;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class c implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ com.applovin.a.a f229a;
    final /* synthetic */ a b;

    c(a aVar, com.applovin.a.a aVar2) {
        this.b = aVar;
        this.f229a = aVar2;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.b.s != null) {
                this.b.s.a(this.f229a);
            }
        } catch (Throwable th) {
            this.b.d.e("AppLovinSdk", "Exception while running app load callback: " + th.getMessage());
        }
    }
}

package com.applovin.impl.adview;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class s implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ a f245a;

    s(a aVar) {
        this.f245a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f245a.m != null) {
            try {
                com.applovin.adview.b bVarA = new b().a(this.f245a.b, this.f245a.f219a);
                bVarA.a(new n(this.f245a));
                bVarA.a(new o(this.f245a));
                bVarA.a(new m(this.f245a));
                bVarA.a(this.f245a.m);
            } catch (Throwable th) {
            }
        }
    }
}

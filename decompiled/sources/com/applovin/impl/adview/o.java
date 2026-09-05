package com.applovin.impl.adview;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class o implements com.applovin.a.i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ a f241a;

    o(a aVar) {
        this.f241a = aVar;
    }

    @Override // com.applovin.a.i
    public void a(com.applovin.a.a aVar) {
        if (this.f241a.u != null) {
            this.f241a.u.a(aVar);
        }
    }

    @Override // com.applovin.a.i
    public void a(com.applovin.a.a aVar, double d, boolean z) {
        if (this.f241a.u != null) {
            this.f241a.u.a(aVar, d, z);
        }
    }
}

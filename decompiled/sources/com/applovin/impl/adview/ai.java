package com.applovin.impl.adview;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ai implements com.applovin.a.i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ e f226a;
    final /* synthetic */ af b;

    ai(af afVar, e eVar) {
        this.b = afVar;
        this.f226a = eVar;
    }

    @Override // com.applovin.a.i
    public void a(com.applovin.a.a aVar) {
        com.applovin.a.i iVarD = this.f226a.d();
        if (iVarD != null) {
            iVarD.a(aVar);
        }
    }

    @Override // com.applovin.a.i
    public void a(com.applovin.a.a aVar, double d, boolean z) {
        com.applovin.a.i iVarD = this.f226a.d();
        if (iVarD != null) {
            iVarD.a(aVar, d, z);
        }
    }
}

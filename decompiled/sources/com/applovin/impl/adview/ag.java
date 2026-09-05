package com.applovin.impl.adview;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ag implements com.applovin.a.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ e f224a;
    final /* synthetic */ af b;

    ag(af afVar, e eVar) {
        this.b = afVar;
        this.f224a = eVar;
    }

    @Override // com.applovin.a.c
    public void a(com.applovin.a.a aVar) {
        this.b.f223a.runOnUiThread(this.b.d);
        com.applovin.a.c cVarE = this.f224a.e();
        if (cVarE != null) {
            cVarE.a(aVar);
        }
        this.f224a.a(false);
    }

    @Override // com.applovin.a.c
    public void b(com.applovin.a.a aVar) {
        super/*android.app.Dialog*/.show();
        if (this.b.f) {
            return;
        }
        com.applovin.a.c cVarE = this.f224a.e();
        if (cVarE != null) {
            cVarE.b(aVar);
        }
        this.b.f = true;
    }
}

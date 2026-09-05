package com.applovin.impl.a;

import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class r extends ad {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ JSONObject f210a;
    final /* synthetic */ q b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    r(q qVar, String str, l lVar, d dVar, JSONObject jSONObject) {
        super(str, lVar, dVar);
        this.b = qVar;
        this.f210a = jSONObject;
    }

    @Override // com.applovin.impl.a.ad, com.applovin.impl.a.as
    public void a(int i) {
        at.a(i, this.d);
    }

    @Override // com.applovin.impl.a.ad
    protected void a(ar arVar, as asVar) throws Throwable {
        arVar.a(at.a("device", this.d), this.f210a, asVar);
    }

    @Override // com.applovin.impl.a.ad, com.applovin.impl.a.as
    public void a(JSONObject jSONObject, int i) {
        this.b.a(jSONObject);
    }
}

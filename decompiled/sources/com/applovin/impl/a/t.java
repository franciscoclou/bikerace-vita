package com.applovin.impl.a;

import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class t extends ad {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ s f212a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    t(s sVar, String str, l lVar, d dVar) {
        super(str, lVar, dVar);
        this.f212a = sVar;
    }

    @Override // com.applovin.impl.a.ad, com.applovin.impl.a.as
    public void a(int i) {
        this.f212a.a(i);
    }

    @Override // com.applovin.impl.a.ad
    protected void a(ar arVar, as asVar) throws Throwable {
        arVar.a(this.f212a.b().toString(), ((Integer) this.d.a(j.u)).intValue(), asVar);
    }

    @Override // com.applovin.impl.a.ad, com.applovin.impl.a.as
    public void a(JSONObject jSONObject, int i) {
        if (i == 200) {
            this.f212a.a(jSONObject);
        } else {
            this.f212a.a(i);
        }
    }
}

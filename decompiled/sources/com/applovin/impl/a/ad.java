package com.applovin.impl.a;

import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
abstract class ad extends x implements as {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f180a;
    private final as b;
    private l g;

    private ad(String str, int i, d dVar) {
        super(str, dVar);
        this.g = null;
        this.f180a = i;
        this.b = new ae(this, dVar, str);
    }

    ad(String str, l lVar, d dVar) {
        this(str, ((Integer) dVar.a(lVar)).intValue(), dVar);
    }

    static /* synthetic */ int b(ad adVar, int i) {
        int i2 = adVar.f180a - i;
        adVar.f180a = i2;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.g != null) {
            m mVarG = this.d.g();
            mVarG.a(this.g, this.g.c());
            mVarG.b();
        }
    }

    @Override // com.applovin.impl.a.as
    public void a(int i) {
    }

    protected abstract void a(ar arVar, as asVar);

    public void a(l lVar) {
        this.g = lVar;
    }

    @Override // com.applovin.impl.a.as
    public void a(JSONObject jSONObject, int i) {
    }

    @Override // java.lang.Runnable
    public void run() {
        a(this.d.i(), this.b);
    }
}

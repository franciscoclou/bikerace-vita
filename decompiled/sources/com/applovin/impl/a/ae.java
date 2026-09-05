package com.applovin.impl.a;

import android.util.Log;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ae implements as {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ d f181a;
    final /* synthetic */ String b;
    final /* synthetic */ ad c;

    ae(ad adVar, d dVar, String str) {
        this.c = adVar;
        this.f181a = dVar;
        this.b = str;
    }

    @Override // com.applovin.impl.a.as
    public void a(int i) {
        boolean z = i < 200 || i >= 500;
        boolean z2 = i != -103;
        if (!z || !z2 || this.c.f180a <= 0) {
            this.c.a(i);
            return;
        }
        long jLongValue = ((Long) this.f181a.a(j.n)).longValue();
        Log.w(this.b, "Unable to send requset due to server failure (code " + i + "). " + this.c.f180a + " attempts left, retrying in " + (jLongValue / 1000.0d) + " seconds...");
        ad.b(this.c, 1);
        if (this.c.f180a == 0) {
            this.c.b();
        }
        this.f181a.k().a(this.c, w.BACKGROUND, jLongValue);
    }

    @Override // com.applovin.impl.a.as
    public void a(JSONObject jSONObject, int i) {
        this.c.f180a = 0;
        this.c.a(jSONObject, i);
    }
}

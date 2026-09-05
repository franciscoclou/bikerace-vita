package com.topfreegames.bikerace.multiplayer;

import com.topfreegames.bikerace.au;

/* JADX INFO: compiled from: MultiplayerManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class w implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ o f1337a;
    private String b;
    private String c;
    private boolean d;
    private v e;

    public w(o oVar, String str, String str2, boolean z, v vVar) {
        this.f1337a = oVar;
        this.b = str;
        this.c = str2;
        this.d = z;
        this.e = vVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        l lVar;
        i iVarA = h.a(this.f1337a.u.d(), this.f1337a.u.c(this.f1337a.g()), this.f1337a.u.c(this.b), au.c(), 0, this.f1337a.b);
        com.topfreegames.bikerace.b.a aVar = new com.topfreegames.bikerace.b.a(this.f1337a.g(), this.f1337a.h(), this.b, this.c, Integer.valueOf(iVarA.b), Integer.valueOf(iVarA.f1312a), Integer.valueOf(au.c()));
        l lVarA = this.f1337a.a(aVar.a());
        if (lVarA == null) {
            l lVar2 = new l(aVar, this.f1337a.g());
            this.f1337a.u.b(lVar2);
            lVar = lVar2;
        } else {
            lVar = !lVarA.u() ? null : lVarA;
        }
        if (!this.d) {
            return;
        }
        this.f1337a.a(lVar, this.e);
    }
}

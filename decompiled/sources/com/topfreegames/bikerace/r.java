package com.topfreegames.bikerace;

/* JADX INFO: compiled from: Game.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class r implements n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ f f1351a;

    private r(f fVar) {
        this.f1351a = fVar;
    }

    /* synthetic */ r(f fVar, r rVar) {
        this(fVar);
    }

    @Override // com.topfreegames.bikerace.n
    public void a() {
        this.f1351a.Y.a();
        if (this.f1351a.o != k.WAITING_START) {
            this.f1351a.o = k.PAUSED;
        }
        this.f1351a.H.a(false);
        this.f1351a.J.b();
        this.f1351a.Z.c(this.f1351a.e(), this.f1351a.a(), !this.f1351a.f());
        af.a(this.f1351a.j, this.f1351a.p == l.MULTI_PLAYER, this.f1351a.e(), this.f1351a.a());
    }
}

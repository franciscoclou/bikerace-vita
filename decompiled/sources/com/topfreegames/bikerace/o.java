package com.topfreegames.bikerace;

/* JADX INFO: compiled from: Game.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class o implements n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ f f1344a;
    private int b;
    private int c;
    private int d;
    private int e;
    private boolean f;

    public o(f fVar, int i, int i2, boolean z) {
        this.f1344a = fVar;
        if (!com.topfreegames.bikerace.h.y.a(i + 1)) {
            throw new IllegalArgumentException("Invalid world id: " + i);
        }
        if (i2 < 0 || i2 >= com.topfreegames.bikerace.h.y.b(i + 1)) {
            throw new IllegalArgumentException("Invalid level id: " + i2);
        }
        this.b = i;
        this.c = i2;
        this.f = z;
        this.d = fVar.x;
        this.e = fVar.z;
    }

    @Override // com.topfreegames.bikerace.n
    public void a() {
        this.f1344a.y = this.d;
        this.f1344a.A = this.e;
        this.f1344a.z = this.c;
        this.f1344a.x = this.b;
        this.f1344a.Y.a();
        if (!this.f) {
            if (this.f1344a.x != this.f1344a.y) {
                if (this.f1344a.u.get(this.f1344a.x) == null || this.f1344a.e() == 999) {
                    this.f1344a.u.put(this.f1344a.x, com.topfreegames.bikerace.h.y.a(this.f1344a.e(), this.f1344a.i));
                }
                this.f1344a.t = (com.topfreegames.bikerace.h.x) this.f1344a.u.get(this.f1344a.x);
                this.f1344a.A = -2;
            }
            if (this.f1344a.z != this.f1344a.A) {
                this.f1344a.s = this.f1344a.t.a(this.f1344a.z);
                this.f1344a.A = this.f1344a.z;
            }
        } else {
            this.f1344a.H.a();
        }
        this.f1344a.n.a(this.f);
        this.f1344a.H.a(this.f1344a.i, this.f1344a.t.a(this.f1344a.I), this.f1344a.n.p(), this.f1344a.n.q());
        this.f1344a.H.a(this.f1344a.s);
        this.f1344a.Z.a(this.f1344a.e(), this.f1344a.a(), !this.f1344a.f());
        this.f1344a.n.h();
        this.f1344a.L.a(0.0f, 0.0f);
        this.f1344a.M.a(0.0f, 0.0f);
        this.f1344a.N = b.ACCELERATING;
        this.f1344a.O = -1;
        this.f1344a.P = -1;
        this.f1344a.H.a(false);
        com.topfreegames.bikerace.c.d.a().a(this.f1344a.s.b());
    }
}

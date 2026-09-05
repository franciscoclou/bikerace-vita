package com.topfreegames.bikerace;

import com.topfreegames.bikerace.activities.BikeRaceApplication;

/* JADX INFO: compiled from: Game.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class j implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ f f1267a;
    private int b;
    private ad c;
    private com.topfreegames.bikerace.multiplayer.al d;
    private com.topfreegames.bikerace.multiplayer.aj e;
    private float f;
    private com.topfreegames.bikerace.multiplayer.ak g;
    private c h;

    private j(f fVar) {
        this.f1267a = fVar;
        this.b = 1;
        this.c = ad.a(fVar.i.getApplicationContext());
        this.d = new com.topfreegames.bikerace.multiplayer.al();
        this.e = null;
        this.f = -1.0f;
        this.g = null;
        this.h = c.REGULAR;
    }

    /* synthetic */ j(f fVar, j jVar) {
        this(fVar);
    }

    @Override // com.topfreegames.bikerace.m
    public void a() {
        this.f1267a.o = k.WAITING_START;
        af.a(this.f1267a.j, this.f1267a.i.getResources().getString(2131099666), this.c.b(this.f1267a.x, this.f1267a.z), this.f1267a.p == l.SINGLE_PLAYER);
    }

    @Override // com.topfreegames.bikerace.m
    public boolean b() {
        return this.f1267a.r();
    }

    @Override // com.topfreegames.bikerace.m
    public void a(float f) {
        this.f1267a.a(f, this.f1267a.q);
        this.f1267a.H.a(this.f1267a.s, this.f1267a.q, this.f1267a.r);
    }

    @Override // com.topfreegames.bikerace.m
    public void c() {
        boolean z;
        boolean z2 = false;
        this.f1267a.o = k.PAUSED;
        this.f1267a.J.b();
        this.f1267a.Y.a();
        boolean zA = this.f1267a.X.a();
        final int iB = this.f1267a.s.b(this.f1267a.v);
        this.f1267a.X.a(this.f1267a.e(), this.f1267a.a(), iB);
        af.a(this.f1267a.j, this.f1267a.a(), this.f1267a.v, iB, this.f1267a.s.a(this.f1267a.v), this.f, this.f1267a.e() == 999);
        this.f1267a.Z.a(this.f1267a.e(), this.f1267a.a(), iB, this.f1267a.X.p());
        if ((this.f1267a.v < this.f || this.f < 0.0f) && this.d != null) {
            synchronized (this.d) {
                this.c.a(this.f1267a.x, this.f1267a.z, this.d.b(this.f1267a.q, this.f1267a.v), this.f1267a.v, this.f1267a.q.m);
            }
        }
        this.d = null;
        new Thread(new Runnable() { // from class: com.topfreegames.bikerace.j.1
            @Override // java.lang.Runnable
            public void run() {
                com.topfreegames.bikerace.a.f fVarA = com.topfreegames.bikerace.a.f.a(j.this.f1267a.i);
                int iE = j.this.f1267a.e();
                if (iB >= 3) {
                    if (iE != 1 || j.this.f1267a.a() != 6) {
                        fVarA.a("AchievTrickBackFlip", j.this.f1267a.F.b());
                    }
                    fVarA.a("AchievTrickFrontFlip", j.this.f1267a.F.c());
                    fVarA.a("AchievTrickWheely", j.this.f1267a.F.d());
                }
                fVarA.a("AchievGetStars", j.this.f1267a.X.p());
                if (iE == 999) {
                    fVarA.a("AchievGetStarsUserCreated", j.this.f1267a.X.b(iE));
                }
                String str = "";
                switch (iE) {
                    case 1:
                        str = "AchievAllStarsDesert";
                        break;
                    case 2:
                        str = "AchievAllStarsArtic";
                        break;
                    case 3:
                        str = "AchievAllStarsDunes";
                        break;
                    case 4:
                        str = "AchievAllStarsHills";
                        break;
                    case 5:
                        str = "AchievAllStarsBeach";
                        break;
                    case 6:
                        str = "AchievAllStarsSavanna";
                        break;
                    case 7:
                        str = "AchievAllStarsDesert2";
                        break;
                    case 8:
                        str = "AchievAllStarsArtic2";
                        break;
                    case 16:
                        str = "AchievAllStarsHalloween";
                        break;
                    case 17:
                        str = "AchievAllStarsThanksgiving";
                        break;
                    case 18:
                        str = "AchievAllStarsHoliday3";
                        break;
                    case 19:
                        str = "AchievAllStarsEaster";
                        break;
                }
                fVarA.a(str, j.this.f1267a.X.b(iE));
            }
        }).start();
        if (ap.e() || !zA || this.f1267a.X.a()) {
            z = true;
        } else {
            af.f(this.f1267a.j);
            z = false;
        }
        if (z && ap.g() && this.f1267a.Z.r() > this.f1267a.l.I() && !this.f1267a.Z.s() && iB == 3 && ((BikeRaceApplication) this.f1267a.i.getApplicationContext()).e()) {
            af.e(this.f1267a.j);
        } else {
            z2 = z;
        }
        if (z2) {
            new Thread(new Runnable() { // from class: com.topfreegames.bikerace.j.2
                @Override // java.lang.Runnable
                public void run() {
                    if (!j.this.f1267a.l.X() || !j.this.f1267a.aa.a()) {
                        return;
                    }
                    String strB = com.topfreegames.bikerace.j.b.c() ? j.this.f1267a.aa.b(j.this.f1267a.X) : j.this.f1267a.aa.a(j.this.f1267a.X);
                    if (strB != null) {
                        af.b(j.this.f1267a.j, strB);
                    } else {
                        af.h(j.this.f1267a.j);
                    }
                }
            }).start();
        }
        com.topfreegames.bikerace.g.a.g().m();
        this.f1267a.c(this.f1267a.f());
        s();
        this.f1267a.Y.f();
        com.topfreegames.bikerace.j.a.a().b(this.f1267a.x, this.f1267a.z);
    }

    @Override // com.topfreegames.bikerace.m
    public void d() {
        this.f1267a.o = k.PAUSED;
        this.f1267a.aa.b();
        com.topfreegames.bikerace.j.a.a().a(this.f1267a.x, this.f1267a.z);
        this.f1267a.b(false);
        synchronized (this.d) {
            this.d.b(this.f1267a.q, this.f1267a.v);
        }
    }

    @Override // com.topfreegames.bikerace.m
    public String e() {
        throw new RuntimeException("Invalid operation for single player mode");
    }

    @Override // com.topfreegames.bikerace.m
    public q f() {
        throw new RuntimeException("Invalid operation for single player mode");
    }

    @Override // com.topfreegames.bikerace.m
    public int g() {
        throw new RuntimeException("Invalid opertion for single player mode");
    }

    @Override // com.topfreegames.bikerace.m
    public void a(boolean z) {
        if (this.f1267a.s == null) {
            throw new IllegalStateException("Current level cannot be null!");
        }
        this.f1267a.F.a();
        this.g = this.c.a(this.f1267a.x, this.f1267a.z);
        this.f = this.c.b(this.f1267a.x, this.f1267a.z);
        this.h = this.c.c(this.f1267a.x, this.f1267a.z);
        this.e = null;
        this.f1267a.r = null;
        if (this.f1267a.X.f()) {
            this.e = new com.topfreegames.bikerace.multiplayer.aj(this.g, this.f);
            b(this.f1267a.s);
        } else {
            this.e = null;
        }
        a(this.f1267a.s);
        this.f1267a.v = 0.0f;
        this.f1267a.o = k.WAITING_START;
        this.d = new com.topfreegames.bikerace.multiplayer.al();
        this.d.a(this.f1267a.q, this.f1267a.v);
    }

    @Override // com.topfreegames.bikerace.m
    public void h() {
        if (this.f1267a.o == k.WAITING_START) {
            if (this.f1267a.e() != 1 || this.f1267a.a() != 1) {
                af.a(this.f1267a.j, this.f1267a.i.getResources().getString(2131099666), this.f, this.f1267a.p == l.SINGLE_PLAYER);
                if (this.f1267a.e() != 16) {
                    return;
                }
                this.f1267a.Y.g();
                return;
            }
            this.f1267a.o = k.HELP;
            af.b(this.f1267a.j);
            return;
        }
        af.a(this.f1267a.j, false, this.f1267a.e(), this.f1267a.a());
    }

    @Override // com.topfreegames.bikerace.m
    public boolean i() {
        this.f1267a.F.a();
        s();
        this.b = 1;
        int i = this.f1267a.z + 1;
        if (i >= com.topfreegames.bikerace.h.y.b(this.f1267a.e())) {
            return false;
        }
        this.f1267a.a(new o(this.f1267a, this.f1267a.x, i, false));
        return true;
    }

    @Override // com.topfreegames.bikerace.m
    public void j() {
        this.f1267a.F.a();
        this.b++;
        if (this.b == 5 && this.f1267a.X.b(this.f1267a.e(), this.f1267a.a()) <= 0 && this.f1267a.X.p() < 32) {
            af.d(this.f1267a.j);
        }
        this.f1267a.a(new o(this.f1267a, this.f1267a.x, this.f1267a.z, false));
    }

    @Override // com.topfreegames.bikerace.m
    public void b(float f) {
        this.f1267a.a(f);
        this.f1267a.F.a(this.f1267a.D, this.f1267a.E, this.f1267a.v, this.f1267a.q);
        this.f1267a.Y.a(this.f1267a.q, this.f1267a.D, f);
        if (this.f1267a.o == k.RUNNING) {
            if (this.d != null && !this.f1267a.t()) {
                synchronized (this.d) {
                    this.d.a(this.f1267a.q, this.f1267a.v);
                }
            }
            if (this.e == null || this.f1267a.r == null) {
                return;
            }
            this.e.a(this.f1267a.v, this.f1267a.r);
        }
    }

    @Override // com.topfreegames.bikerace.m
    public void k() {
        throw new RuntimeException("Invalid operation for single player mode");
    }

    @Override // com.topfreegames.bikerace.m
    public void l() {
        af.a(this.f1267a.j, false);
    }

    @Override // com.topfreegames.bikerace.m
    public void m() {
        this.f1267a.a(new r(this.f1267a, null));
    }

    @Override // com.topfreegames.bikerace.m
    public void n() {
        if (this.f1267a.o != k.WAITING_START) {
            l();
            this.f1267a.o = k.RUNNING;
        } else {
            af.a(this.f1267a.j, (String) null, 0.0f, this.f1267a.p == l.SINGLE_PLAYER);
        }
    }

    @Override // com.topfreegames.bikerace.m
    public void o() {
        this.c.c();
    }

    private void a(com.topfreegames.bikerace.h.a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("Level cannot be null!");
        }
        c cVarP = p();
        this.f1267a.q = a.a(cVarP, aVar.c());
        this.f1267a.w = 1.0f;
        this.f1267a.a(cVarP);
    }

    private void b(com.topfreegames.bikerace.h.a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("Level cannot be null!");
        }
        if (this.f > 0.0f) {
            this.f1267a.r = a.a(q(), aVar.c());
        } else {
            this.f1267a.r = null;
        }
    }

    private void s() {
        int i = this.f1267a.z + 1;
        int i2 = this.f1267a.x;
        if (i >= com.topfreegames.bikerace.h.y.b(this.f1267a.e())) {
            i = 0;
            i2++;
            if (i2 >= com.topfreegames.bikerace.h.y.b.length) {
                i2--;
            }
        }
        int i3 = i + 1;
        int i4 = i2 + 1;
        if (!this.f1267a.X.a(i4, i3)) {
            return;
        }
        this.f1267a.X.c(i4, i3);
    }

    @Override // com.topfreegames.bikerace.m
    public c p() {
        return this.f1267a.X.e();
    }

    @Override // com.topfreegames.bikerace.m
    public c q() {
        return this.h;
    }

    @Override // com.topfreegames.bikerace.m
    public void r() {
    }
}

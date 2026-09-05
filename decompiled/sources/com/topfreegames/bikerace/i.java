package com.topfreegames.bikerace;

/* JADX INFO: compiled from: Game.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class i implements m {
    private static /* synthetic */ int[] m;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ f f1260a;
    private com.topfreegames.bikerace.multiplayer.aj c;
    private com.topfreegames.bikerace.multiplayer.aj d;
    private com.topfreegames.bikerace.multiplayer.l e;
    private com.topfreegames.bikerace.multiplayer.d f;
    private int g;
    private q j;
    private String k;
    private boolean l;
    private com.topfreegames.bikerace.multiplayer.al b = new com.topfreegames.bikerace.multiplayer.al();
    private float h = -1.0f;
    private com.topfreegames.bikerace.multiplayer.ak i = null;

    static /* synthetic */ int[] s() {
        int[] iArr = m;
        if (iArr == null) {
            iArr = new int[com.topfreegames.bikerace.multiplayer.m.valuesCustom().length];
            try {
                iArr[com.topfreegames.bikerace.multiplayer.m.FINISHED.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[com.topfreegames.bikerace.multiplayer.m.READY.ordinal()] = 4;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[com.topfreegames.bikerace.multiplayer.m.SHOW_RESULT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[com.topfreegames.bikerace.multiplayer.m.WAITING.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            m = iArr;
        }
        return iArr;
    }

    public i(f fVar, com.topfreegames.bikerace.multiplayer.l lVar, com.topfreegames.bikerace.multiplayer.d dVar, int i, String str, boolean z) {
        this.f1260a = fVar;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = 0;
        this.j = q.PLAYING_FIRST;
        this.k = null;
        this.l = false;
        if (lVar == null) {
            throw new IllegalArgumentException("MultiplayerData cannot be null!");
        }
        if (dVar == null) {
            throw new IllegalArgumentException("Callback cannot be null!");
        }
        if (i < 0) {
            throw new IllegalArgumentException("TryNumber cannot be negative!");
        }
        if (str == null) {
            throw new IllegalArgumentException("UserName cannot be null!");
        }
        this.e = lVar;
        this.f = dVar;
        this.g = i;
        this.k = str;
        this.l = z;
        switch (s()[this.e.t().ordinal()]) {
            case 3:
                this.j = q.WATCHING;
                this.c = new com.topfreegames.bikerace.multiplayer.aj(new com.topfreegames.bikerace.multiplayer.ak(lVar.n()), lVar.o());
                this.d = new com.topfreegames.bikerace.multiplayer.aj(new com.topfreegames.bikerace.multiplayer.ak(lVar.h()), lVar.i().floatValue());
                return;
            case 4:
                if (this.e.h() == null) {
                    this.j = q.PLAYING_FIRST;
                    this.d = null;
                    return;
                } else {
                    this.j = q.PLAYING_AGAINST;
                    this.d = new com.topfreegames.bikerace.multiplayer.aj(new com.topfreegames.bikerace.multiplayer.ak(lVar.h()), lVar.i().floatValue());
                    return;
                }
            default:
                return;
        }
    }

    @Override // com.topfreegames.bikerace.m
    public void a() {
    }

    @Override // com.topfreegames.bikerace.m
    public boolean b() {
        if (this.j == q.WATCHING) {
            if (this.f1260a.v >= Math.max(Math.abs(this.e.i().floatValue()), 1.0f)) {
                return true;
            }
            return false;
        }
        return this.f1260a.r();
    }

    @Override // com.topfreegames.bikerace.m
    public void a(float f) {
        a aVar = this.f1260a.q;
        if (this.j == q.WATCHING) {
            aVar = this.f1260a.r;
        }
        this.f1260a.a(f, aVar);
        if (this.j != q.WATCHING) {
            if (this.j == q.PLAYING_FIRST) {
                this.f1260a.H.a(this.f1260a.s, this.f1260a.q, this.f1260a.r, 3 - this.g, 3);
                return;
            } else {
                this.f1260a.H.b(this.f1260a.s, this.f1260a.q, this.f1260a.r, 3 - this.g, 3);
                return;
            }
        }
        this.f1260a.H.b(this.f1260a.s, this.f1260a.q, this.f1260a.r);
    }

    @Override // com.topfreegames.bikerace.m
    public void c() {
        int iA;
        this.f1260a.o = k.PAUSED;
        this.f1260a.J.b();
        this.f1260a.Y.a();
        float fO = this.j == q.WATCHING ? this.e.o() : this.f1260a.v;
        p pVarA = a(fO, this.e.i().floatValue());
        this.f1260a.Z.a(this.f1260a.e(), this.f1260a.a(), this.e.p());
        af.a(this.f1260a.j, this.e.l(), this.k, fO, this.e.p(), this.e.f(), this.e.g(), this.e.i().floatValue(), this.e.j(), this.j == q.WATCHING, pVarA, this.l);
        if (this.j == q.WATCHING) {
            this.e.b(true);
        } else {
            synchronized (this.b) {
                this.b.a(this.f1260a.q, this.f1260a.v);
            }
            if (this.e.v()) {
                new Thread(new Runnable() { // from class: com.topfreegames.bikerace.i.1
                    @Override // java.lang.Runnable
                    public void run() {
                        com.topfreegames.bikerace.a.f fVarA = com.topfreegames.bikerace.a.f.a(i.this.f1260a.i);
                        boolean z = false;
                        if (fVarA.b("AchievCreateGameSMS").size() > 0 && i.this.f1260a.X.o() > 0) {
                            fVarA.c("AchievCreateGameSMS");
                            z = true;
                        }
                        if (!z && fVarA.b("AchievCreateGameEmail").size() > 0 && i.this.f1260a.X.n() > 0) {
                            fVarA.c("AchievCreateGameEmail");
                        }
                    }
                }).start();
            }
            synchronized (this.b) {
                if (this.e.q() != this.f1260a.e() || this.e.d() != this.f1260a.a()) {
                    if (ap.d()) {
                        System.out.println("Wrong World/Level for multiplayer");
                    }
                    this.f1260a.Z.b(this.e.q(), this.e.d(), this.f1260a.e(), this.f1260a.a());
                    this.e.a(this.f1260a.q.m, fO, this.b.b(this.f1260a.q, this.f1260a.v), null, this.k, this.f1260a.e(), this.f1260a.a());
                } else {
                    this.e.a(this.f1260a.q.m, fO, this.b.b(this.f1260a.q, this.f1260a.v), null, this.k);
                }
            }
            com.topfreegames.bikerace.j.a.a().b(this.f1260a.x, this.f1260a.z);
        }
        c cVarG = this.f1260a.r != null ? this.f1260a.r.g() : null;
        if (this.j == q.WATCHING) {
            iA = this.f1260a.F.a(this.e.b());
        } else {
            iA = 3 - this.g;
        }
        if (this.j == q.PLAYING_FIRST) {
            this.f1260a.F.a(this.e.b(), iA);
        }
        this.f1260a.c(this.f1260a.f());
        a(pVarA, cVarG, iA);
    }

    @Override // com.topfreegames.bikerace.m
    public void d() {
        this.f1260a.J.b();
        this.f1260a.Y.a();
        if (this.j == q.WATCHING) {
            return;
        }
        this.f1260a.o = k.PAUSED;
        if (this.f1260a.v > this.h && this.f1260a.v > 0.0f) {
            this.h = this.f1260a.v;
            synchronized (this.b) {
                this.i = this.b.b(this.f1260a.q, this.f1260a.v);
            }
        }
        if (this.j == q.PLAYING_FIRST && this.i != null) {
            this.d = new com.topfreegames.bikerace.multiplayer.aj(this.i, this.h);
        }
        if (this.g < 3) {
            this.f1260a.b(false);
        } else {
            float f = -Math.abs(this.h);
            p pVarA = a(f, this.e.i().floatValue());
            af.a(this.f1260a.j, this.e.l(), this.k, f, this.e.p(), this.e.f(), this.e.g(), this.e.i().floatValue(), this.e.j(), this.j == q.WATCHING, pVarA, false);
            if (this.e.v()) {
                new Thread(new Runnable() { // from class: com.topfreegames.bikerace.i.2
                    @Override // java.lang.Runnable
                    public void run() {
                        com.topfreegames.bikerace.a.f fVarA = com.topfreegames.bikerace.a.f.a(i.this.f1260a.i);
                        boolean z = false;
                        if (fVarA.b("AchievCreateGameSMS").size() > 0 && i.this.f1260a.X.o() > 0) {
                            fVarA.c("AchievCreateGameSMS");
                            z = true;
                        }
                        if (!z && fVarA.b("AchievCreateGameEmail").size() > 0 && i.this.f1260a.X.n() > 0) {
                            fVarA.c("AchievCreateGameEmail");
                        }
                    }
                }).start();
            }
            if (this.i == null) {
                this.i = this.b.b(this.f1260a.q, 0.0f);
            }
            if (this.e.q() != this.f1260a.e() || this.e.d() != this.f1260a.a()) {
                if (ap.d()) {
                    System.out.println("Wrong World/Level for multiplayer");
                }
                this.f1260a.Z.b(this.e.q(), this.e.d(), this.f1260a.e(), this.f1260a.a());
                this.e.a(this.f1260a.q.g(), f, this.i, null, this.k, this.f1260a.e(), this.f1260a.a());
            } else {
                this.e.a(this.f1260a.q.g(), f, this.i, null, this.k);
            }
            this.i = null;
            this.d = null;
            this.h = -1.0f;
            a(pVarA, this.f1260a.r != null ? this.f1260a.r.g() : null, 0);
            if (this.j == q.PLAYING_FIRST) {
                t();
                this.f1260a.F.a(this.e.b(), 0);
            }
        }
        com.topfreegames.bikerace.j.a.a().a(this.f1260a.x, this.f1260a.z);
    }

    @Override // com.topfreegames.bikerace.m
    public String e() {
        return this.e.b();
    }

    @Override // com.topfreegames.bikerace.m
    public q f() {
        return this.j;
    }

    @Override // com.topfreegames.bikerace.m
    public int g() {
        return this.g;
    }

    @Override // com.topfreegames.bikerace.m
    public void a(boolean z) {
        if (z) {
            return;
        }
        this.f1260a.v = 0.0f;
        if (this.g == 0) {
            this.h = -1.0f;
            this.i = null;
        }
        this.f1260a.o = k.WAITING_START;
        b(this.f1260a.s);
        this.b.a(this.f1260a.q, this.f1260a.v);
        if (this.j == q.PLAYING_FIRST && this.g == 0) {
            this.f1260a.r = null;
        } else {
            a(this.f1260a.s);
        }
    }

    @Override // com.topfreegames.bikerace.m
    public void h() {
        if (this.f1260a.o == k.WAITING_START) {
            if (this.j != q.WATCHING) {
                if (this.j == q.PLAYING_FIRST) {
                    af.a(this.f1260a.j, String.valueOf(this.f1260a.i.getResources().getString(2131099669)) + this.e.g(), -1.0f, this.f1260a.p == l.SINGLE_PLAYER);
                    return;
                } else {
                    if (this.j == q.PLAYING_AGAINST) {
                        af.a(this.f1260a.j, String.valueOf(this.f1260a.i.getResources().getString(2131099668)) + this.e.g(), -1.0f, this.f1260a.p == l.SINGLE_PLAYER);
                        return;
                    }
                    return;
                }
            }
            af.a(this.f1260a.j, this.f1260a.i.getResources().getString(2131099667));
            return;
        }
        af.a(this.f1260a.j, true, this.f1260a.e(), this.f1260a.a());
    }

    @Override // com.topfreegames.bikerace.m
    public boolean i() {
        throw new RuntimeException("Invalid opertion for multi player mode");
    }

    @Override // com.topfreegames.bikerace.m
    public void j() {
        if (this.j != q.WATCHING) {
            if (this.f1260a.v <= this.h || this.f1260a.v <= 0.0f) {
                if (this.f1260a.v == 0.0f && this.i == null) {
                    this.h = -1.0f;
                    synchronized (this.b) {
                        this.i = this.b.b(this.f1260a.q, 0.0f);
                    }
                }
            } else {
                this.h = this.f1260a.v;
                synchronized (this.b) {
                    this.i = this.b.b(this.f1260a.q, this.f1260a.v);
                }
            }
            if (this.j == q.PLAYING_FIRST) {
                this.d = new com.topfreegames.bikerace.multiplayer.aj(this.i, this.h);
            }
        }
        this.g++;
        if (this.g >= 3) {
            d();
            return;
        }
        this.f1260a.a(new o(this.f1260a, this.f1260a.x, this.f1260a.z, false));
        synchronized (this.b) {
            this.b.b(this.f1260a.q, this.f1260a.v);
        }
    }

    @Override // com.topfreegames.bikerace.m
    public void b(float f) {
        if (this.j == q.WATCHING) {
            this.c.a(this.f1260a.v, this.f1260a.q);
            this.f1260a.Y.c();
        } else {
            this.f1260a.a(f);
            this.f1260a.Y.a(this.f1260a.q, this.f1260a.D, f);
            if (this.b != null && !this.f1260a.t()) {
                synchronized (this.b) {
                    this.b.a(this.f1260a.q, this.f1260a.v);
                }
            }
        }
        if (this.d == null || this.f1260a.r == null) {
            return;
        }
        this.d.a(this.f1260a.v, this.f1260a.r);
    }

    @Override // com.topfreegames.bikerace.m
    public void k() {
        c();
    }

    @Override // com.topfreegames.bikerace.m
    public void l() {
        if (this.j != q.WATCHING) {
            if (this.g >= 3) {
                d();
                return;
            } else {
                af.a(this.f1260a.j, false);
                return;
            }
        }
        af.a(this.f1260a.j, true);
    }

    @Override // com.topfreegames.bikerace.m
    public void m() {
        if (this.f1260a.o != k.PAUSED) {
            if (this.j == q.WATCHING) {
                try {
                    this.f1260a.i();
                } catch (Exception e) {
                }
            } else {
                this.f1260a.a(new r(this.f1260a, null));
            }
        }
    }

    @Override // com.topfreegames.bikerace.m
    public void n() {
        if (this.f1260a.o != k.WAITING_START) {
            l();
            this.f1260a.o = k.RUNNING;
        } else {
            af.a(this.f1260a.j, (String) null, -1.0f, this.f1260a.p == l.SINGLE_PLAYER);
        }
    }

    @Override // com.topfreegames.bikerace.m
    public void o() {
        this.g++;
    }

    private void a(final p pVar, final c cVar, final int i) {
        if (pVar == null) {
            throw new IllegalArgumentException("Result cannot be null!");
        }
        if (pVar == p.SENT) {
            com.topfreegames.bikerace.g.a.g().n();
        }
        if (this.f != null) {
            new Thread(new Runnable() { // from class: com.topfreegames.bikerace.i.3
                @Override // java.lang.Runnable
                public void run() {
                    i.this.f.a(i.this.e, i.this.j, pVar, cVar, i.this.l, i);
                }
            }).start();
        }
    }

    private void t() {
        if (this.f != null) {
            new Thread(new Runnable() { // from class: com.topfreegames.bikerace.i.4
                @Override // java.lang.Runnable
                public void run() {
                    i.this.f.a();
                }
            }).start();
        }
    }

    private p a(float f, float f2) {
        p pVar;
        if (this.j == q.PLAYING_FIRST) {
            return p.SENT;
        }
        if (f < 0.0f && f2 < 0.0f) {
            pVar = p.TIE;
        } else if (f >= 0.0f || f2 < 0.0f) {
            pVar = ((f < 0.0f || f2 >= 0.0f) && f > f2) ? p.LOSE : p.WIN;
        } else {
            pVar = p.LOSE;
        }
        if (this.j == q.PLAYING_AGAINST) {
            if (pVar == p.WIN) {
                this.e.s();
                return pVar;
            }
            if (pVar == p.LOSE) {
                this.e.r();
                return pVar;
            }
            return pVar;
        }
        return pVar;
    }

    private void a(com.topfreegames.bikerace.h.a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("Level cannot be null!");
        }
        c cVarQ = q();
        if (cVarQ != null) {
            this.f1260a.r = a.a(cVarQ, aVar.c());
        }
    }

    private void b(com.topfreegames.bikerace.h.a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("Level cannot be null!");
        }
        c cVarP = p();
        this.f1260a.q = a.a(cVarP, aVar.c());
        this.f1260a.w = 1.0f;
        this.f1260a.a(cVarP);
    }

    @Override // com.topfreegames.bikerace.m
    public c p() {
        if (this.j == q.WATCHING) {
            return this.e.k();
        }
        return this.f1260a.X.e();
    }

    @Override // com.topfreegames.bikerace.m
    public c q() {
        if (this.j == q.PLAYING_FIRST) {
            if (this.g <= 0) {
                return null;
            }
            return this.f1260a.q.g();
        }
        return this.e.e();
    }

    @Override // com.topfreegames.bikerace.m
    public void r() {
        if (this.j == q.WATCHING && this.e.t() == com.topfreegames.bikerace.multiplayer.m.READY) {
            this.e.b(false);
        }
    }
}

package com.topfreegames.bikerace.worldcup;

import android.content.Context;
import com.topfreegames.bikerace.bb;
import com.topfreegames.bikerace.t;
import com.topfreegames.bikerace.z;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: WorldCupManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Map<com.topfreegames.bikerace.p, Integer> f1462a;
    private static o j;
    private Context b;
    private bb c;
    private z d;
    private f e;
    private t f;
    private c g;
    private h h;
    private HashMap<p, j> i = new HashMap<>();
    private final n k = new n() { // from class: com.topfreegames.bikerace.worldcup.o.1
        @Override // com.topfreegames.bikerace.worldcup.n
        public void a(List<Integer> list, Map<a, Integer> map) {
            if (map != null && list != null) {
                e.a(map);
                e.a(list);
                if (o.this.i != null) {
                    Iterator it = o.this.i.keySet().iterator();
                    while (it.hasNext()) {
                        ((j) o.this.i.get((p) it.next())).d();
                    }
                }
            }
        }
    };

    static {
        HashMap map = new HashMap();
        map.put(com.topfreegames.bikerace.p.WIN, 3);
        map.put(com.topfreegames.bikerace.p.TIE, 1);
        map.put(com.topfreegames.bikerace.p.LOSE, 0);
        map.put(com.topfreegames.bikerace.p.SENT, 0);
        f1462a = Collections.unmodifiableMap(map);
    }

    private o(Context context, bb bbVar, final z zVar, t tVar) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        if (bbVar == null) {
            throw new IllegalArgumentException("Remote config cannot be null!");
        }
        if (zVar == null) {
            throw new IllegalArgumentException("Game data cannot be nulL!");
        }
        this.c = bbVar;
        this.d = zVar;
        this.b = context;
        this.g = new c(zVar);
        this.f = tVar;
        this.e = new f(context, new g() { // from class: com.topfreegames.bikerace.worldcup.o.2
            @Override // com.topfreegames.bikerace.worldcup.g
            public void a(int i) {
                zVar.h(i);
            }
        });
        this.h = new h(bbVar);
        b();
    }

    public static o a() {
        o oVar;
        synchronized (com.topfreegames.bikerace.g.a.class) {
            if (j == null) {
                throw new IllegalStateException("Call init() first!");
            }
            oVar = j;
        }
        return oVar;
    }

    public static int a(com.topfreegames.bikerace.p pVar) {
        Integer num = f1462a.get(pVar);
        if (num == null) {
            num = 0;
        }
        return num.intValue();
    }

    public static void a(Context context, bb bbVar, z zVar, t tVar) {
        if (j == null) {
            synchronized (o.class) {
                if (j == null) {
                    j = new o(context, bbVar, zVar, tVar);
                }
            }
        }
    }

    public void b() {
        e.a(m.a(this.b, this.k));
        e.a(m.b(this.b, this.k));
        this.i.put(p.ORDINARY, new j(this.c.ag(), this.c.ah(), this.c.ai(), this.c.aj(), this.c.ak()));
        this.i.put(p.RARE, new j(this.c.al(), this.c.am(), this.c.an(), this.c.ao(), this.c.ap()));
        this.h.a(b(p.RARE), b(p.ORDINARY), this.g);
    }

    public boolean c() {
        return this.d.H() >= 3;
    }

    public boolean d() {
        return this.d.G() >= 1;
    }

    public boolean e() {
        return d() || c();
    }

    public boolean f() {
        return (this.d.a(com.topfreegames.bikerace.c.WORLDCUP_AUSTRALIA) && this.d.a(com.topfreegames.bikerace.c.WORLDCUP_BRAZIL) && this.d.a(com.topfreegames.bikerace.c.WORLDCUP_USA) && this.d.a(com.topfreegames.bikerace.c.WORLDCUP_FRANCE) && this.d.a(com.topfreegames.bikerace.c.WORLDCUP_GERMANY) && this.d.a(com.topfreegames.bikerace.c.WORLDCUP_JAPAN) && this.d.a(com.topfreegames.bikerace.c.WORLDCUP_NETHERLANDS) && this.d.a(com.topfreegames.bikerace.c.WORLDCUP_SPAIN) && this.d.a(com.topfreegames.bikerace.c.WORLDCUP_ENGLAND)) ? false : true;
    }

    public f g() {
        return this.e;
    }

    public int h() {
        return this.d.G();
    }

    public c i() {
        return this.g;
    }

    public int j() {
        return this.d.H();
    }

    public void a(z zVar, com.topfreegames.bikerace.p pVar) {
        if (t()) {
            zVar.j(a(pVar));
        }
    }

    public boolean k() {
        return t();
    }

    public boolean l() {
        return t();
    }

    public boolean m() {
        return t() || (e() && this.c.aC()) || f();
    }

    public boolean n() {
        return t();
    }

    public boolean o() {
        return t() || (c() && this.c.aC());
    }

    public boolean p() {
        return t() || (d() && this.c.aC());
    }

    public boolean q() {
        return t() || (e() && this.c.aC()) || f();
    }

    public boolean r() {
        return t();
    }

    public long s() {
        return this.c.ae() - com.topfreegames.c.a.a().getTime();
    }

    public boolean t() {
        return (this.c.ae() < com.topfreegames.c.a.a().getTime() || this.c.af() || this.d.a()) ? false : true;
    }

    public boolean a(p pVar) {
        j jVar = this.i.get(pVar);
        return this.d.H() >= jVar.b() && this.d.G() >= jVar.a();
    }

    public j b(p pVar) {
        return this.i.get(pVar);
    }

    public void a(p pVar, k kVar, final d dVar) {
        if (kVar == null) {
            throw new IllegalArgumentException("Handler cannot be null!");
        }
        if (a(pVar)) {
            j jVar = this.i.get(pVar);
            this.d.j(-jVar.b());
            this.d.g(jVar.a());
            kVar.a();
            com.topfreegames.bikerace.c cVarC = null;
            if (this.h.d() == pVar) {
                cVarC = this.h.c();
            }
            a aVarA = jVar.a(cVarC);
            if (this.g.a(aVarA)) {
                this.f.b(aVarA);
                int iC = jVar.c();
                if (iC > 0) {
                    this.d.j(iC);
                    kVar.a(iC, aVarA);
                    return;
                }
                return;
            }
            this.f.a(aVarA);
            a().i().a(aVarA, new d() { // from class: com.topfreegames.bikerace.worldcup.o.3
                @Override // com.topfreegames.bikerace.worldcup.d
                public void a(com.topfreegames.bikerace.c cVar) {
                    if (o.this.d.a(cVar)) {
                        o.this.d.d(cVar);
                        o.this.f.e(cVar);
                        dVar.a(cVar);
                    }
                }
            });
            kVar.a(aVarA);
        }
    }

    public void a(i iVar) {
        this.h.a(iVar);
        this.h.a();
        b();
    }

    public void u() {
        this.h.b();
    }

    public h v() {
        return this.h;
    }

    public p a(com.topfreegames.bikerace.c cVar) {
        j jVarB = b(p.RARE);
        for (a aVar : this.g.b(cVar)) {
            if (jVarB.a(aVar)) {
                return p.RARE;
            }
        }
        return p.ORDINARY;
    }

    public long w() {
        return this.c.ae() - 86399999;
    }
}

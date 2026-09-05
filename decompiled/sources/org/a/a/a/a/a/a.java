package org.a.a.a.a.a;

import java.util.Enumeration;
import org.a.a.a.a.a.b.t;

/* JADX INFO: compiled from: ClientComms.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f1569a = "@@VERSION@@";
    public static String b = "@@BUILDLEVEL@@";
    static final String l = a.class.getName();
    l c;
    f d;
    g e;
    d g;
    org.a.a.a.a.i h;
    private org.a.a.a.a.b n;
    private org.a.a.a.a.h o;
    private byte p;
    boolean j = false;
    Object k = new Object();
    private boolean q = false;
    org.a.a.a.a.b.a m = org.a.a.a.a.b.b.a("org.eclipse.paho.client.mqttv3.internal.nls.logcat", l);
    h i = new h(g().a());
    e f = new e(this);

    public a(org.a.a.a.a.b bVar, org.a.a.a.a.h hVar) {
        this.p = (byte) 3;
        this.p = (byte) 3;
        this.n = bVar;
        this.o = hVar;
        this.g = new d(hVar, this.i, this.f, this);
        this.f.a(this.g);
        this.m.a(g().a());
    }

    void a(t tVar, org.a.a.a.a.p pVar) throws org.a.a.a.a.k {
        this.m.b(l, "internalSend", "200", new Object[]{tVar.e(), tVar, pVar});
        if (pVar.e() == null) {
            pVar.f1606a.a(g());
            try {
                this.g.a(tVar, pVar);
                return;
            } catch (org.a.a.a.a.k e) {
                if (tVar instanceof org.a.a.a.a.a.b.o) {
                    this.g.a((org.a.a.a.a.a.b.o) tVar);
                }
                throw e;
            }
        }
        this.m.b(l, "internalSend", "213", new Object[]{tVar.e(), tVar, pVar});
        throw new org.a.a.a.a.k(32201);
    }

    public void b(t tVar, org.a.a.a.a.p pVar) throws org.a.a.a.a.k {
        if (b() || ((!b() && (tVar instanceof org.a.a.a.a.a.b.d)) || (e() && (tVar instanceof org.a.a.a.a.a.b.e)))) {
            a(tVar, pVar);
        } else {
            this.m.a(l, "sendNoWait", "208");
            throw i.a(32104);
        }
    }

    public void a() {
        synchronized (this.k) {
            if (!f()) {
                if (!d()) {
                    this.m.a(l, "close", "224");
                    if (c()) {
                        throw new org.a.a.a.a.k(32110);
                    }
                    if (b()) {
                        throw i.a(32100);
                    }
                    if (e()) {
                        this.q = true;
                        return;
                    }
                }
                this.p = (byte) 4;
                this.g.i();
                this.g = null;
                this.f = null;
                this.o = null;
                this.e = null;
                this.d = null;
                this.c = null;
                this.h = null;
                this.i = null;
            }
        }
    }

    public void a(org.a.a.a.a.i iVar, org.a.a.a.a.p pVar) {
        synchronized (this.k) {
            if (d() && !this.q) {
                this.m.a(l, "connect", "214");
                this.p = (byte) 1;
                this.h = iVar;
                org.a.a.a.a.a.b.d dVar = new org.a.a.a.a.a.b.d(this.n.a(), iVar.i(), iVar.c(), iVar.b(), iVar.a(), iVar.g(), iVar.f());
                this.g.a(iVar.c());
                this.g.a(iVar.i());
                this.i.a();
                new b(this, this, pVar, dVar).a();
            } else {
                this.m.b(l, "connect", "207", new Object[]{new Byte(this.p)});
                if (f() || this.q) {
                    throw new org.a.a.a.a.k(32111);
                }
                if (c()) {
                    throw new org.a.a.a.a.k(32110);
                }
                if (e()) {
                    throw new org.a.a.a.a.k(32102);
                }
                throw i.a(32100);
            }
        }
    }

    public void a(org.a.a.a.a.a.b.c cVar, org.a.a.a.a.k kVar) throws org.a.a.a.a.k {
        int iB_ = cVar.b_();
        synchronized (this.k) {
            try {
                if (iB_ == 0) {
                    this.m.a(l, "connectComplete", "215");
                    this.p = (byte) 0;
                } else {
                    this.m.b(l, "connectComplete", "204", new Object[]{new Integer(iB_)});
                    throw kVar;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void a(org.a.a.a.a.p pVar, org.a.a.a.a.k kVar) {
        synchronized (this.k) {
            if (!this.j && !this.q) {
                this.j = true;
                this.m.a(l, "shutdownConnection", "216");
                boolean z = b() || e();
                this.p = (byte) 2;
                if (pVar != null && !pVar.c()) {
                    pVar.f1606a.a(kVar);
                }
                if (this.f != null) {
                    this.f.a();
                }
                try {
                    if (this.c != null) {
                        this.c.d();
                    }
                } catch (Exception e) {
                }
                if (this.d != null) {
                    this.d.a();
                }
                this.i.a(new org.a.a.a.a.k(32102));
                org.a.a.a.a.p pVarB = b(pVar, kVar);
                try {
                    this.g.b(kVar);
                } catch (Exception e2) {
                }
                if (this.e != null) {
                    this.e.a();
                }
                try {
                    if (this.o != null) {
                        this.o.a();
                    }
                } catch (Exception e3) {
                }
                synchronized (this.k) {
                    this.m.a(l, "shutdownConnection", "217");
                    this.p = (byte) 3;
                    this.j = false;
                }
                if ((pVarB != null) & (this.f != null)) {
                    this.f.b(pVarB);
                }
                if (z && this.f != null) {
                    this.f.a(kVar);
                }
                synchronized (this.k) {
                    if (this.q) {
                        try {
                            a();
                        } catch (Exception e4) {
                        }
                    }
                }
            }
        }
    }

    private org.a.a.a.a.p b(org.a.a.a.a.p pVar, org.a.a.a.a.k kVar) {
        this.m.a(l, "handleOldTokens", "222");
        org.a.a.a.a.p pVar2 = null;
        if (pVar != null) {
            try {
                if (this.i.a(pVar.f1606a.m()) == null) {
                    this.i.a(pVar, pVar.f1606a.m());
                }
            } catch (Exception e) {
            }
        }
        Enumeration enumerationElements = this.g.a(kVar).elements();
        while (enumerationElements.hasMoreElements()) {
            org.a.a.a.a.p pVar3 = (org.a.a.a.a.p) enumerationElements.nextElement();
            if (!pVar3.f1606a.m().equals(org.a.a.a.a.a.b.e.f1576a) && !pVar3.f1606a.m().equals(org.a.a.a.a.a.b.d.f1575a)) {
                this.f.b(pVar3);
                pVar3 = pVar2;
            }
            pVar2 = pVar3;
        }
        return pVar2;
    }

    public void a(org.a.a.a.a.a.b.e eVar, long j, org.a.a.a.a.p pVar) {
        synchronized (this.k) {
            if (f()) {
                this.m.a(l, "disconnect", "223");
                throw i.a(32111);
            }
            if (d()) {
                this.m.a(l, "disconnect", "211");
                throw i.a(32101);
            }
            if (e()) {
                this.m.a(l, "disconnect", "219");
                throw i.a(32102);
            }
            if (Thread.currentThread() == this.f.d()) {
                this.m.a(l, "disconnect", "210");
                throw i.a(32107);
            }
            this.m.a(l, "disconnect", "218");
            this.p = (byte) 2;
            new c(this, eVar, j, pVar).a();
        }
    }

    public boolean b() {
        return this.p == 0;
    }

    public boolean c() {
        return this.p == 1;
    }

    public boolean d() {
        return this.p == 3;
    }

    public boolean e() {
        return this.p == 2;
    }

    public boolean f() {
        return this.p == 4;
    }

    public void a(org.a.a.a.a.f fVar) {
        this.f.a(fVar);
    }

    public void a(l lVar) {
        this.c = lVar;
    }

    protected void a(org.a.a.a.a.a.b.o oVar) {
        this.g.b(oVar);
    }

    public org.a.a.a.a.b g() {
        return this.n;
    }
}

package org.a.a.a.a.a;

import java.io.EOFException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.a.a.a.a.a.b.t;

/* JADX INFO: compiled from: ClientState.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class d {
    private static final String z = d.class.getName();
    private Hashtable b;
    private volatile Vector c;
    private volatile Vector d;
    private h e;
    private a f;
    private e g;
    private long h;
    private boolean i;
    private org.a.a.a.a.h j;
    private int l;
    private int m;
    private t t;
    private Hashtable w;
    private Hashtable x;
    private Hashtable y;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1587a = 0;
    private int k = 10;
    private Object n = new Object();
    private Object o = new Object();
    private boolean p = false;
    private long q = 0;
    private long r = 0;
    private long s = 0;
    private boolean u = false;
    private boolean v = false;
    private org.a.a.a.a.b.a A = org.a.a.a.a.b.b.a("org.eclipse.paho.client.mqttv3.internal.nls.logcat", z);

    protected d(org.a.a.a.a.h hVar, h hVar2, e eVar, a aVar) throws org.a.a.a.a.k {
        this.f = null;
        this.g = null;
        this.l = 0;
        this.m = 0;
        this.w = null;
        this.x = null;
        this.y = null;
        this.A.a(aVar.g().a());
        this.A.b(z, "<Init>", "");
        this.b = new Hashtable();
        this.c = new Vector(this.k);
        this.d = new Vector();
        this.w = new Hashtable();
        this.x = new Hashtable();
        this.y = new Hashtable();
        this.t = new org.a.a.a.a.a.b.i();
        this.m = 0;
        this.l = 0;
        this.j = hVar;
        this.g = eVar;
        this.e = hVar2;
        this.f = aVar;
        c();
    }

    protected void a(long j) {
        this.h = 1000 * j;
    }

    protected long a() {
        return this.h;
    }

    protected void a(boolean z2) {
        this.i = z2;
    }

    private String c(t tVar) {
        return "s-" + tVar.j();
    }

    private String d(t tVar) {
        return "sc-" + tVar.j();
    }

    private String e(t tVar) {
        return "r-" + tVar.j();
    }

    protected void b() {
        this.A.a(z, "clearState", ">");
        this.j.c();
        this.b.clear();
        this.c.clear();
        this.d.clear();
        this.w.clear();
        this.x.clear();
        this.y.clear();
        this.e.d();
    }

    private t a(String str, org.a.a.a.a.m mVar) throws org.a.a.a.a.k {
        t tVarA;
        try {
            tVarA = t.a(mVar);
        } catch (org.a.a.a.a.k e) {
            this.A.a(z, "restoreMessage", "602", new Object[]{str}, e);
            if (e.getCause() instanceof EOFException) {
                if (str == null) {
                    tVarA = null;
                } else {
                    this.j.b(str);
                    tVarA = null;
                }
            } else {
                throw e;
            }
        }
        this.A.b(z, "restoreMessage", "601", new Object[]{str, tVarA});
        return tVarA;
    }

    private void a(Vector vector, t tVar) {
        int iJ = tVar.j();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < vector.size()) {
                if (((t) vector.elementAt(i2)).j() <= iJ) {
                    i = i2 + 1;
                } else {
                    vector.insertElementAt(tVar, i2);
                    return;
                }
            } else {
                vector.addElement(tVar);
                return;
            }
        }
    }

    private Vector a(Vector vector) {
        Vector vector2 = new Vector();
        if (vector.size() == 0) {
            return vector2;
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i < vector.size()) {
            int iJ = ((t) vector.elementAt(i)).j();
            if (iJ - i4 > i3) {
                i3 = iJ - i4;
                i2 = i;
            }
            i++;
            i4 = iJ;
        }
        if (((t) vector.elementAt(0)).j() + (65535 - i4) > i3) {
            i2 = 0;
        }
        for (int i5 = i2; i5 < vector.size(); i5++) {
            vector2.addElement(vector.elementAt(i5));
        }
        for (int i6 = 0; i6 < i2; i6++) {
            vector2.addElement(vector.elementAt(i6));
        }
        return vector2;
    }

    protected void c() throws org.a.a.a.a.k {
        int i;
        Enumeration enumerationB = this.j.b();
        int i2 = this.f1587a;
        Vector vector = new Vector();
        this.A.a(z, "restoreState", "600");
        while (true) {
            i = i2;
            if (!enumerationB.hasMoreElements()) {
                break;
            }
            String str = (String) enumerationB.nextElement();
            t tVarA = a(str, this.j.a(str));
            if (tVarA == null) {
                i2 = i;
            } else if (str.startsWith("r-")) {
                this.A.b(z, "restoreState", "604", new Object[]{str, tVarA});
                this.y.put(new Integer(tVarA.j()), tVarA);
                i2 = i;
            } else if (str.startsWith("s-")) {
                org.a.a.a.a.a.b.o oVar = (org.a.a.a.a.a.b.o) tVarA;
                int iMax = Math.max(oVar.j(), i);
                if (this.j.c(d(oVar))) {
                    org.a.a.a.a.a.b.n nVar = (org.a.a.a.a.a.b.n) a(str, this.j.a(d(oVar)));
                    if (nVar != null) {
                        nVar.a(true);
                        this.A.b(z, "restoreState", "605", new Object[]{str, tVarA});
                        this.w.put(new Integer(nVar.j()), nVar);
                    } else {
                        this.A.b(z, "restoreState", "606", new Object[]{str, tVarA});
                    }
                } else {
                    oVar.a(true);
                    if (oVar.h().c() == 2) {
                        this.A.b(z, "restoreState", "607", new Object[]{str, tVarA});
                        this.w.put(new Integer(oVar.j()), oVar);
                    } else {
                        this.A.b(z, "restoreState", "608", new Object[]{str, tVarA});
                        this.x.put(new Integer(oVar.j()), oVar);
                    }
                }
                this.e.a(oVar).f1606a.a(this.f.g());
                this.b.put(new Integer(oVar.j()), new Integer(oVar.j()));
                i2 = iMax;
            } else {
                if (str.startsWith("sc-") && !this.j.c(c((org.a.a.a.a.a.b.n) tVarA))) {
                    vector.addElement(str);
                }
                i2 = i;
            }
        }
        Enumeration enumerationElements = vector.elements();
        while (enumerationElements.hasMoreElements()) {
            String str2 = (String) enumerationElements.nextElement();
            this.A.b(z, "restoreState", "609", new Object[]{str2});
            this.j.b(str2);
        }
        this.f1587a = i;
    }

    private void j() {
        this.c = new Vector(this.k);
        this.d = new Vector();
        Enumeration enumerationKeys = this.w.keys();
        while (enumerationKeys.hasMoreElements()) {
            Object objNextElement = enumerationKeys.nextElement();
            Object obj = this.w.get(objNextElement);
            if (obj instanceof org.a.a.a.a.a.b.o) {
                this.A.b(z, "restoreInflightMessages", "610", new Object[]{objNextElement});
                a(this.c, (org.a.a.a.a.a.b.o) obj);
            } else if (obj instanceof org.a.a.a.a.a.b.n) {
                this.A.b(z, "restoreInflightMessages", "611", new Object[]{objNextElement});
                a(this.d, (org.a.a.a.a.a.b.n) obj);
            }
        }
        Enumeration enumerationKeys2 = this.x.keys();
        while (enumerationKeys2.hasMoreElements()) {
            Object objNextElement2 = enumerationKeys2.nextElement();
            org.a.a.a.a.a.b.o oVar = (org.a.a.a.a.a.b.o) this.x.get(objNextElement2);
            this.A.b(z, "restoreInflightMessages", "612", new Object[]{objNextElement2});
            a(this.c, oVar);
        }
        this.d = a(this.d);
        this.c = a(this.c);
    }

    public void a(t tVar, org.a.a.a.a.p pVar) {
        if (tVar.g_() && tVar.j() == 0) {
            tVar.a(m());
        }
        if (pVar != null) {
            try {
                pVar.f1606a.a(tVar.j());
            } catch (Exception e) {
            }
        }
        if (tVar instanceof org.a.a.a.a.a.b.o) {
            synchronized (this.n) {
                if (this.l >= this.k) {
                    this.A.b(z, "send", "613", new Object[]{new Integer(this.l)});
                    throw new org.a.a.a.a.k(32202);
                }
                org.a.a.a.a.l lVarH = ((org.a.a.a.a.a.b.o) tVar).h();
                this.A.b(z, "send", "628", new Object[]{new Integer(tVar.j()), new Integer(lVarH.c()), tVar});
                switch (lVarH.c()) {
                    case 1:
                        this.x.put(new Integer(tVar.j()), tVar);
                        this.j.a(c(tVar), (org.a.a.a.a.a.b.o) tVar);
                        break;
                    case 2:
                        this.w.put(new Integer(tVar.j()), tVar);
                        this.j.a(c(tVar), (org.a.a.a.a.a.b.o) tVar);
                        break;
                }
                this.e.a(pVar, tVar);
                this.c.addElement(tVar);
                this.n.notifyAll();
            }
            return;
        }
        this.A.b(z, "send", "615", new Object[]{new Integer(tVar.j()), tVar});
        if (tVar instanceof org.a.a.a.a.a.b.d) {
            synchronized (this.n) {
                this.e.a(pVar, tVar);
                this.d.insertElementAt(tVar, 0);
                this.n.notifyAll();
            }
            return;
        }
        if (tVar instanceof org.a.a.a.a.a.b.i) {
            this.t = tVar;
        } else if (tVar instanceof org.a.a.a.a.a.b.n) {
            this.w.put(new Integer(tVar.j()), tVar);
            this.j.a(d(tVar), (org.a.a.a.a.a.b.n) tVar);
        } else if (tVar instanceof org.a.a.a.a.a.b.l) {
            this.j.b(e(tVar));
        }
        synchronized (this.n) {
            if (!(tVar instanceof org.a.a.a.a.a.b.b)) {
                this.e.a(pVar, tVar);
            }
            this.d.addElement(tVar);
            this.n.notifyAll();
        }
    }

    protected void a(org.a.a.a.a.a.b.o oVar) {
        synchronized (this.n) {
            this.A.b(z, "undo", "618", new Object[]{new Integer(oVar.j()), new Integer(oVar.h().c())});
            if (oVar.h().c() == 1) {
                this.x.remove(new Integer(oVar.j()));
            } else {
                this.w.remove(new Integer(oVar.j()));
            }
            this.c.removeElement(oVar);
            this.j.b(c(oVar));
            this.e.b(oVar);
            f();
        }
    }

    private void k() throws org.a.a.a.a.k {
        if (this.v && this.h > 0) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (this.u) {
                if (jCurrentTimeMillis - this.s >= this.h) {
                    this.A.a(z, "checkForActivity", "619", new Object[]{new Long(this.h), new Long(this.q), new Long(this.r)});
                    throw i.a(32000);
                }
            } else if (jCurrentTimeMillis - this.q >= this.h || jCurrentTimeMillis - this.r >= this.h) {
                this.A.b(z, "checkForActivity", "620", new Object[]{new Long(this.h), new Long(this.q), new Long(this.r)});
                this.u = true;
                this.s = jCurrentTimeMillis;
                this.e.a(new org.a.a.a.a.p(this.f.g().a()), this.t);
                this.d.insertElementAt(this.t, 0);
            }
        }
    }

    protected t d() {
        synchronized (this.n) {
            t tVar = null;
            while (tVar == null) {
                if (this.c.isEmpty() && this.d.isEmpty()) {
                    try {
                        this.A.b(z, "get", "644", new Object[]{new Long(e())});
                        this.n.wait(e());
                    } catch (InterruptedException e) {
                    }
                }
                if (!this.v && (this.d.isEmpty() || !(((t) this.d.elementAt(0)) instanceof org.a.a.a.a.a.b.d))) {
                    this.A.a(z, "get", "621");
                    return null;
                }
                k();
                if (!this.d.isEmpty()) {
                    t tVar2 = (t) this.d.elementAt(0);
                    this.d.removeElementAt(0);
                    if (tVar2 instanceof org.a.a.a.a.a.b.n) {
                        this.m++;
                        this.A.b(z, "get", "617", new Object[]{new Integer(this.m)});
                    }
                    f();
                    tVar = tVar2;
                } else if (!this.c.isEmpty()) {
                    if (this.l < this.k) {
                        t tVar3 = (t) this.c.elementAt(0);
                        this.c.removeElementAt(0);
                        this.l++;
                        this.A.b(z, "get", "623", new Object[]{new Integer(this.l)});
                        tVar = tVar3;
                    } else {
                        this.A.a(z, "get", "622");
                    }
                }
            }
            return tVar;
        }
    }

    long e() {
        long jA;
        long jA2 = a();
        if (this.v && a() > 0 && !this.u) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            long j = jCurrentTimeMillis - this.q;
            long j2 = jCurrentTimeMillis - this.r;
            if (j > j2) {
                jA = a() - j;
            } else {
                jA = a() - j2;
            }
            if (jA <= 0) {
                return 10L;
            }
            return jA;
        }
        return jA2;
    }

    protected void a(t tVar) {
        this.q = System.currentTimeMillis();
        this.A.b(z, "notifySent", "625", new Object[]{tVar.e()});
        org.a.a.a.a.p pVarA = this.e.a(tVar);
        pVarA.f1606a.h();
        if ((tVar instanceof org.a.a.a.a.a.b.o) && ((org.a.a.a.a.a.b.o) tVar).h().c() == 0) {
            pVarA.f1606a.a(null, null);
            this.g.b(pVarA);
            l();
            a(tVar.j());
            this.e.b(tVar);
            f();
        }
    }

    private void l() {
        synchronized (this.n) {
            this.l--;
            this.A.b(z, "decrementInFlight", "646", new Object[]{new Integer(this.l)});
            if (!f()) {
                this.n.notifyAll();
            }
        }
    }

    protected boolean f() {
        int iE = this.e.e();
        if (!this.p || iE != 0 || this.d.size() != 0 || !this.g.c()) {
            return false;
        }
        this.A.b(z, "checkQuiesceLock", "626", new Object[]{new Boolean(this.p), new Integer(this.l), new Integer(this.d.size()), new Integer(this.m), new Boolean(this.g.c()), new Integer(iE)});
        synchronized (this.o) {
            this.o.notifyAll();
        }
        return true;
    }

    protected void a(org.a.a.a.a.a.b.b bVar) throws org.a.a.a.a.k {
        this.r = System.currentTimeMillis();
        this.A.b(z, "notifyReceivedAck", "627", new Object[]{new Integer(bVar.j()), bVar});
        org.a.a.a.a.p pVarA = this.e.a(bVar);
        if (bVar instanceof org.a.a.a.a.a.b.m) {
            a(new org.a.a.a.a.a.b.n((org.a.a.a.a.a.b.m) bVar), pVarA);
        } else if ((bVar instanceof org.a.a.a.a.a.b.k) || (bVar instanceof org.a.a.a.a.a.b.l)) {
            a(bVar, pVarA, null);
        } else if (bVar instanceof org.a.a.a.a.a.b.j) {
            this.u = false;
            a(bVar, pVarA, null);
            this.e.b(bVar);
        } else if (bVar instanceof org.a.a.a.a.a.b.c) {
            int iB_ = ((org.a.a.a.a.a.b.c) bVar).b_();
            if (iB_ == 0) {
                synchronized (this.n) {
                    if (this.i) {
                        b();
                        this.e.a(pVarA, bVar);
                    }
                    this.m = 0;
                    this.l = 0;
                    j();
                    g();
                }
                this.f.a((org.a.a.a.a.a.b.c) bVar, (org.a.a.a.a.k) null);
                a(bVar, pVarA, null);
                this.e.b(bVar);
                synchronized (this.n) {
                    this.n.notifyAll();
                }
            } else {
                throw i.a(iB_);
            }
        } else {
            a(bVar, pVarA, null);
            a(bVar.j());
            this.e.b(bVar);
        }
        f();
    }

    protected void b(t tVar) {
        this.r = System.currentTimeMillis();
        this.A.b(z, "notifyReceivedMsg", "651", new Object[]{new Integer(tVar.j()), tVar});
        if (!this.p) {
            if (tVar instanceof org.a.a.a.a.a.b.o) {
                org.a.a.a.a.a.b.o oVar = (org.a.a.a.a.a.b.o) tVar;
                switch (oVar.h().c()) {
                    case 0:
                    case 1:
                        if (this.g != null) {
                            this.g.a(oVar);
                        }
                        break;
                    case 2:
                        this.j.a(e(tVar), (org.a.a.a.a.a.b.o) tVar);
                        this.y.put(new Integer(oVar.j()), oVar);
                        a(new org.a.a.a.a.a.b.m(oVar), (org.a.a.a.a.p) null);
                        break;
                }
            }
            if (tVar instanceof org.a.a.a.a.a.b.n) {
                org.a.a.a.a.a.b.o oVar2 = (org.a.a.a.a.a.b.o) this.y.get(new Integer(tVar.j()));
                if (oVar2 != null) {
                    if (this.g != null) {
                        this.g.a(oVar2);
                        return;
                    }
                    return;
                }
                a(new org.a.a.a.a.a.b.l(tVar.j()), (org.a.a.a.a.p) null);
            }
        }
    }

    protected void a(org.a.a.a.a.p pVar) {
        t tVarJ = pVar.f1606a.j();
        if (tVarJ != null && (tVarJ instanceof org.a.a.a.a.a.b.b)) {
            this.A.b(z, "notifyComplete", "629", new Object[]{new Integer(tVarJ.j()), pVar, tVarJ});
            org.a.a.a.a.a.b.b bVar = (org.a.a.a.a.a.b.b) tVarJ;
            if (bVar instanceof org.a.a.a.a.a.b.k) {
                this.j.b(c(tVarJ));
                this.x.remove(new Integer(bVar.j()));
                l();
                a(tVarJ.j());
                this.e.b(tVarJ);
                this.A.b(z, "notifyComplete", "650", new Object[]{new Integer(bVar.j())});
            } else if (bVar instanceof org.a.a.a.a.a.b.l) {
                this.j.b(c(tVarJ));
                this.j.b(d(tVarJ));
                this.w.remove(new Integer(bVar.j()));
                this.m--;
                l();
                a(tVarJ.j());
                this.e.b(tVarJ);
                this.A.b(z, "notifyComplete", "645", new Object[]{new Integer(bVar.j()), new Integer(this.m)});
            }
            f();
        }
    }

    protected void a(t tVar, org.a.a.a.a.p pVar, org.a.a.a.a.k kVar) {
        pVar.f1606a.a(tVar, kVar);
        if (tVar != null && (tVar instanceof org.a.a.a.a.a.b.b) && !(tVar instanceof org.a.a.a.a.a.b.m)) {
            this.A.b(z, "notifyResult", "648", new Object[]{pVar.f1606a.m(), tVar, kVar});
            this.g.b(pVar);
        }
        if (tVar == null) {
            this.A.b(z, "notifyResult", "649", new Object[]{pVar.f1606a.m(), kVar});
            this.g.b(pVar);
        }
    }

    public void g() {
        this.A.a(z, "connected", "631");
        this.v = true;
    }

    public Vector a(org.a.a.a.a.k kVar) {
        this.A.b(z, "resolveOldTokens", "632", new Object[]{kVar});
        if (kVar == null) {
            kVar = new org.a.a.a.a.k(32102);
        }
        Vector vectorC = this.e.c();
        Enumeration enumerationElements = vectorC.elements();
        while (enumerationElements.hasMoreElements()) {
            org.a.a.a.a.p pVar = (org.a.a.a.a.p) enumerationElements.nextElement();
            synchronized (pVar) {
                if (!pVar.c() && !pVar.f1606a.d() && pVar.b() == null) {
                    pVar.f1606a.a(kVar);
                }
            }
            if (!(pVar instanceof org.a.a.a.a.j)) {
                this.e.b(pVar.f1606a.m());
            }
        }
        return vectorC;
    }

    public void b(org.a.a.a.a.k kVar) {
        this.A.b(z, "disconnected", "633", new Object[]{kVar});
        this.v = false;
        try {
            if (this.i) {
                b();
            }
            this.c.clear();
            this.d.clear();
            this.u = false;
        } catch (org.a.a.a.a.k e) {
        }
    }

    private synchronized void a(int i) {
        this.b.remove(new Integer(i));
    }

    private synchronized int m() {
        int i = this.f1587a;
        int i2 = 0;
        do {
            this.f1587a++;
            if (this.f1587a > 65535) {
                this.f1587a = 1;
            }
            if (this.f1587a == i && (i2 = i2 + 1) == 2) {
                throw i.a(32001);
            }
        } while (this.b.containsKey(new Integer(this.f1587a)));
        Integer num = new Integer(this.f1587a);
        this.b.put(num, num);
        return this.f1587a;
    }

    public void b(long j) {
        if (j > 0) {
            this.A.b(z, "quiesce", "637", new Object[]{new Long(j)});
            synchronized (this.n) {
                this.p = true;
            }
            this.g.b();
            h();
            synchronized (this.o) {
                try {
                    int iE = this.e.e();
                    if (iE > 0 || this.d.size() > 0 || !this.g.c()) {
                        this.A.b(z, "quiesce", "639", new Object[]{new Integer(this.l), new Integer(this.d.size()), new Integer(this.m), new Integer(iE)});
                        this.o.wait(j);
                    }
                } catch (InterruptedException e) {
                }
            }
            synchronized (this.n) {
                this.c.clear();
                this.d.clear();
                this.p = false;
                this.l = 0;
            }
            this.A.a(z, "quiesce", "640");
        }
    }

    protected void h() {
        synchronized (this.n) {
            this.A.a(z, "notifyQueueLock", "638");
            this.n.notifyAll();
        }
    }

    protected void b(org.a.a.a.a.a.b.o oVar) {
        this.A.b(z, "deliveryComplete", "641", new Object[]{new Integer(oVar.j())});
        this.j.b(e(oVar));
        this.y.remove(new Integer(oVar.j()));
    }

    protected void i() {
        this.b.clear();
        this.c.clear();
        this.d.clear();
        this.w.clear();
        this.x.clear();
        this.y.clear();
        this.e.d();
        this.b = null;
        this.c = null;
        this.d = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.e = null;
        this.g = null;
        this.f = null;
        this.j = null;
        this.t = null;
    }
}

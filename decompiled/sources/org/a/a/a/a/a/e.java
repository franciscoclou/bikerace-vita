package org.a.a.a.a.a;

import java.util.Vector;

/* JADX INFO: compiled from: CommsCallback.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class e implements Runnable {
    private org.a.a.a.a.f e;
    private a f;
    private Thread k;
    private d n;
    private static int d = 10;
    static final String b = e.class.getName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f1588a = false;
    private boolean i = false;
    private Object j = new Object();
    private Object l = new Object();
    private Object m = new Object();
    org.a.a.a.a.b.a c = org.a.a.a.a.b.b.a("org.eclipse.paho.client.mqttv3.internal.nls.logcat", b);
    private Vector g = new Vector(d);
    private Vector h = new Vector(d);

    e(a aVar) {
        this.f = aVar;
        this.c.a(aVar.g().a());
    }

    public void a(d dVar) {
        this.n = dVar;
    }

    public void a(String str) {
        synchronized (this.j) {
            if (!this.f1588a) {
                this.g.clear();
                this.h.clear();
                this.f1588a = true;
                this.i = false;
                this.k = new Thread(this, str);
                this.k.start();
            }
        }
    }

    public void a() {
        synchronized (this.j) {
            if (this.f1588a) {
                this.c.a(b, "stop", "700");
                this.f1588a = false;
                if (!Thread.currentThread().equals(this.k)) {
                    try {
                        synchronized (this.l) {
                            this.c.a(b, "stop", "701");
                            this.l.notifyAll();
                        }
                        this.k.join();
                    } catch (InterruptedException e) {
                    }
                }
            }
            this.k = null;
            this.c.a(b, "stop", "703");
        }
    }

    public void a(org.a.a.a.a.f fVar) {
        this.e = fVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        while (this.f1588a) {
            try {
                try {
                    synchronized (this.l) {
                        try {
                            if ((this.f1588a & this.g.isEmpty()) && this.h.isEmpty()) {
                                this.c.a(b, "run", "704");
                                this.l.wait();
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                } catch (InterruptedException e) {
                }
                if (this.f1588a) {
                    if (!this.h.isEmpty()) {
                        c((org.a.a.a.a.p) this.h.elementAt(0));
                        this.h.removeElementAt(0);
                    }
                    if (!this.g.isEmpty()) {
                        b((org.a.a.a.a.a.b.o) this.g.elementAt(0));
                        this.g.removeElementAt(0);
                    }
                }
                if (this.i) {
                    this.n.f();
                }
                synchronized (this.m) {
                    try {
                        this.c.a(b, "run", "706");
                        this.m.notifyAll();
                    } catch (Throwable th2) {
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                this.c.a(b, "run", "714", null, th3);
                this.f1588a = false;
                this.f.a((org.a.a.a.a.p) null, new org.a.a.a.a.k(th3));
            }
        }
    }

    private void c(org.a.a.a.a.p pVar) {
        synchronized (pVar) {
            this.c.b(b, "handleActionComplete", "705", new Object[]{pVar.f1606a.m()});
            pVar.f1606a.f();
            if (!pVar.f1606a.n()) {
                if (this.e != null && (pVar instanceof org.a.a.a.a.j) && pVar.c()) {
                    this.e.a((org.a.a.a.a.j) pVar);
                }
                a(pVar);
            }
            if ((pVar instanceof org.a.a.a.a.j) && pVar.c()) {
                pVar.f1606a.a(true);
            }
            if (pVar.c()) {
                this.n.a(pVar);
            }
        }
    }

    public void a(org.a.a.a.a.k kVar) {
        try {
            if (this.e != null && kVar != null) {
                this.c.b(b, "connectionLost", "708", new Object[]{kVar});
                this.e.a(kVar);
            }
        } catch (Throwable th) {
            this.c.b(b, "connectionLost", "720", new Object[]{th});
        }
    }

    public void a(org.a.a.a.a.p pVar) {
        org.a.a.a.a.a aVarD;
        if (pVar != null && (aVarD = pVar.d()) != null) {
            if (pVar.b() == null) {
                this.c.b(b, "fireActionEvent", "716", new Object[]{pVar.f1606a.m()});
                aVarD.a(pVar);
            } else {
                this.c.b(b, "fireActionEvent", "716", new Object[]{pVar.f1606a.m()});
                aVarD.a(pVar, pVar.b());
            }
        }
    }

    public void a(org.a.a.a.a.a.b.o oVar) {
        if (this.e != null) {
            synchronized (this.m) {
                if (!this.i && this.g.size() >= d) {
                    try {
                        this.c.a(b, "messageArrived", "709");
                        this.m.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
            if (!this.i) {
                this.g.addElement(oVar);
                synchronized (this.l) {
                    this.c.a(b, "messageArrived", "710");
                    this.l.notifyAll();
                }
            }
        }
    }

    public void b() {
        this.i = true;
        synchronized (this.m) {
            this.c.a(b, "quiesce", "711");
            this.m.notifyAll();
        }
    }

    public boolean c() {
        return this.i && this.h.size() == 0 && this.g.size() == 0;
    }

    private void b(org.a.a.a.a.a.b.o oVar) throws org.a.a.a.a.k {
        if (this.e != null) {
            String strG = oVar.g();
            this.c.b(b, "handleMessage", "713", new Object[]{new Integer(oVar.j()), strG});
            this.e.a(strG, oVar.h());
            if (oVar.h().c() == 1) {
                this.f.a(new org.a.a.a.a.a.b.k(oVar), new org.a.a.a.a.p(this.f.g().a()));
            } else if (oVar.h().c() == 2) {
                this.f.a(oVar);
                this.f.a(new org.a.a.a.a.a.b.l(oVar), new org.a.a.a.a.p(this.f.g().a()));
            }
        }
    }

    public void b(org.a.a.a.a.p pVar) {
        if (this.f1588a) {
            this.h.addElement(pVar);
            synchronized (this.l) {
                this.c.b(b, "asyncOperationComplete", "715", new Object[]{pVar.f1606a.m()});
                this.l.notifyAll();
            }
            return;
        }
        try {
            c(pVar);
        } catch (Throwable th) {
            this.c.a(b, "asyncOperationComplete", "719", null, th);
            System.err.println("problem in asyncopcomplete " + th);
            th.printStackTrace();
            this.f.a((org.a.a.a.a.p) null, new org.a.a.a.a.k(th));
        }
    }

    protected Thread d() {
        return this.k;
    }
}

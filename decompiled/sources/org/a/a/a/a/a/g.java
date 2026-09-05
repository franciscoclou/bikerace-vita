package org.a.a.a.a.a;

import java.io.OutputStream;
import org.a.a.a.a.a.b.t;

/* JADX INFO: compiled from: CommsSender.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class g implements Runnable {
    private static final String h = g.class.getName();
    private d c;
    private org.a.a.a.a.a.b.g d;
    private a e;
    private h f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f1590a = false;
    private Object b = new Object();
    private Thread g = null;
    private org.a.a.a.a.b.a i = org.a.a.a.a.b.b.a("org.eclipse.paho.client.mqttv3.internal.nls.logcat", h);

    public g(a aVar, d dVar, h hVar, OutputStream outputStream) {
        this.c = null;
        this.e = null;
        this.f = null;
        this.d = new org.a.a.a.a.a.b.g(outputStream);
        this.e = aVar;
        this.c = dVar;
        this.f = hVar;
        this.i.a(aVar.g().a());
    }

    public void a(String str) {
        synchronized (this.b) {
            if (!this.f1590a) {
                this.f1590a = true;
                this.g = new Thread(this, str);
                this.g.start();
            }
        }
    }

    public void a() {
        synchronized (this.b) {
            this.i.a(h, "stop", "800");
            if (this.f1590a) {
                this.f1590a = false;
                if (!Thread.currentThread().equals(this.g)) {
                    try {
                        this.c.h();
                        this.g.join();
                    } catch (InterruptedException e) {
                    }
                }
            }
            this.g = null;
            this.i.a(h, "stop", "801");
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        t tVarD = null;
        while (this.f1590a && this.d != null) {
            try {
                tVarD = this.c.d();
                if (tVarD != null) {
                    this.i.b(h, "run", "802", new Object[]{tVarD.e(), tVarD});
                    if (tVarD instanceof org.a.a.a.a.a.b.b) {
                        this.d.a(tVarD);
                        this.d.flush();
                    } else {
                        org.a.a.a.a.p pVarA = this.f.a(tVarD);
                        if (pVarA != null) {
                            synchronized (pVarA) {
                                try {
                                    this.d.a(tVarD);
                                    this.d.flush();
                                    this.c.a(tVarD);
                                } catch (Throwable th) {
                                    throw th;
                                }
                            }
                        } else {
                            continue;
                        }
                    }
                } else {
                    this.i.a(h, "run", "803");
                    this.f1590a = false;
                }
            } catch (org.a.a.a.a.k e) {
                a(tVarD, e);
            } catch (Exception e2) {
                a(tVarD, e2);
            }
        }
        this.i.a(h, "run", "805");
    }

    private void a(t tVar, Exception exc) {
        org.a.a.a.a.k kVar;
        this.i.a(h, "handleRunException", "804", null, exc);
        if (!(exc instanceof org.a.a.a.a.k)) {
            kVar = new org.a.a.a.a.k(32109, exc);
        } else {
            kVar = (org.a.a.a.a.k) exc;
        }
        this.f1590a = false;
        this.e.a((org.a.a.a.a.p) null, kVar);
    }
}

package org.a.a.a.a.a;

import java.io.IOException;
import java.io.InputStream;
import org.a.a.a.a.a.b.t;

/* JADX INFO: compiled from: CommsReceiver.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class f implements Runnable {
    private static final String h = f.class.getName();
    private d c;
    private a d;
    private org.a.a.a.a.a.b.f e;
    private h f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f1589a = false;
    private Object b = new Object();
    private Thread g = null;
    private org.a.a.a.a.b.a i = org.a.a.a.a.b.b.a("org.eclipse.paho.client.mqttv3.internal.nls.logcat", h);

    public f(a aVar, d dVar, h hVar, InputStream inputStream) {
        this.c = null;
        this.d = null;
        this.f = null;
        this.e = new org.a.a.a.a.a.b.f(inputStream);
        this.d = aVar;
        this.c = dVar;
        this.f = hVar;
        this.i.a(aVar.g().a());
    }

    public void a(String str) {
        this.i.a(h, "start", "855");
        synchronized (this.b) {
            if (!this.f1589a) {
                this.f1589a = true;
                this.g = new Thread(this, str);
                this.g.start();
            }
        }
    }

    public void a() {
        synchronized (this.b) {
            this.i.a(h, "stop", "850");
            if (this.f1589a) {
                this.f1589a = false;
                if (!Thread.currentThread().equals(this.g)) {
                    try {
                        this.g.join();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
        this.g = null;
        this.i.a(h, "stop", "851");
    }

    @Override // java.lang.Runnable
    public void run() {
        org.a.a.a.a.p pVarA = null;
        while (this.f1589a && this.e != null) {
            try {
                this.i.a(h, "run", "852");
                t tVarA = this.e.a();
                if (tVarA instanceof org.a.a.a.a.a.b.b) {
                    pVarA = this.f.a(tVarA);
                    if (pVarA != null) {
                        synchronized (pVarA) {
                            this.c.a((org.a.a.a.a.a.b.b) tVarA);
                        }
                    } else {
                        throw new org.a.a.a.a.k(6);
                    }
                } else {
                    this.c.b(tVarA);
                }
                pVarA = pVarA;
            } catch (IOException e) {
                this.i.a(h, "run", "853");
                this.f1589a = false;
                if (!this.d.e()) {
                    this.d.a(pVarA, new org.a.a.a.a.k(32109, e));
                }
            } catch (org.a.a.a.a.k e2) {
                org.a.a.a.a.p pVar = pVarA;
                this.i.a(h, "run", "856", null, e2);
                this.f1589a = false;
                this.d.a(pVar, e2);
                pVarA = pVar;
            }
        }
        this.i.a(h, "run", "854");
    }
}

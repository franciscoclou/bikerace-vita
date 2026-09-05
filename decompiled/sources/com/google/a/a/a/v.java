package com.google.a.a.a;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.analytics.internal.Command;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Timer;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX INFO: compiled from: GAServiceProxy.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class v implements ar, d, e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private volatile long f602a;
    private volatile w b;
    private volatile a c;
    private f d;
    private f e;
    private final ae f;
    private final h g;
    private final Context h;
    private final Queue<z> i;
    private volatile int j;
    private volatile Timer k;
    private volatile Timer l;
    private volatile Timer m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private k r;
    private long s;

    v(Context context, h hVar, f fVar, ae aeVar) {
        this.i = new ConcurrentLinkedQueue();
        this.s = 300000L;
        this.e = fVar;
        this.h = context;
        this.g = hVar;
        this.f = aeVar;
        this.r = new k() { // from class: com.google.a.a.a.v.1
            @Override // com.google.a.a.a.k
            public long a() {
                return System.currentTimeMillis();
            }
        };
        this.j = 0;
        this.b = w.DISCONNECTED;
    }

    v(Context context, h hVar) {
        this(context, hVar, null, ae.a(context));
    }

    @Override // com.google.a.a.a.ar
    public void a(Map<String, String> map, long j, String str, List<Command> list) {
        ah.c("putHit called");
        this.i.add(new z(map, j, str, list));
        h();
    }

    @Override // com.google.a.a.a.ar
    public void c() {
        switch (this.b) {
            case CONNECTED_LOCAL:
                i();
                break;
            case CONNECTED_SERVICE:
                break;
            default:
                this.n = true;
                break;
        }
    }

    public void d() {
        ah.c("clearHits called");
        this.i.clear();
        switch (this.b) {
            case CONNECTED_LOCAL:
                this.d.a(0L);
                this.o = false;
                break;
            case CONNECTED_SERVICE:
                this.c.a();
                this.o = false;
                break;
            default:
                this.o = true;
                break;
        }
    }

    @Override // com.google.a.a.a.ar
    public synchronized void e() {
        if (!this.q) {
            ah.c("setForceLocalDispatch called.");
            this.q = true;
            switch (this.b) {
                case CONNECTED_SERVICE:
                    l();
                    break;
                case CONNECTING:
                    this.p = true;
                    break;
            }
        }
    }

    private Timer a(Timer timer) {
        if (timer != null) {
            timer.cancel();
            return null;
        }
        return null;
    }

    private void g() {
        this.k = a(this.k);
        this.l = a(this.l);
        this.m = a(this.m);
    }

    @Override // com.google.a.a.a.ar
    public void f() {
        if (this.c == null) {
            this.c = new b(this.h, this, this);
            k();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public synchronized void h() {
        if (!Thread.currentThread().equals(this.g.d())) {
            this.g.c().add(new Runnable() { // from class: com.google.a.a.a.v.2
                @Override // java.lang.Runnable
                public void run() {
                    v.this.h();
                }
            });
        } else {
            if (this.o) {
                d();
            }
            switch (this.b) {
                case CONNECTED_LOCAL:
                    while (!this.i.isEmpty()) {
                        z zVarPoll = this.i.poll();
                        ah.c("Sending hit to store  " + zVarPoll);
                        this.d.a(zVarPoll.a(), zVarPoll.b(), zVarPoll.c(), zVarPoll.d());
                    }
                    if (this.n) {
                        i();
                    }
                    break;
                case CONNECTED_SERVICE:
                    while (!this.i.isEmpty()) {
                        z zVarPeek = this.i.peek();
                        ah.c("Sending hit to service   " + zVarPeek);
                        if (!this.f.b()) {
                            this.c.a(zVarPeek.a(), zVarPeek.b(), zVarPeek.c(), zVarPeek.d());
                        } else {
                            ah.c("Dry run enabled. Hit not actually sent to service.");
                        }
                        this.i.poll();
                    }
                    this.f602a = this.r.a();
                    break;
                case DISCONNECTED:
                    ah.c("Need to reconnect");
                    if (!this.i.isEmpty()) {
                        k();
                    }
                    break;
            }
        }
    }

    private void i() {
        this.d.a();
        this.n = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void j() {
        if (this.b != w.CONNECTED_LOCAL) {
            g();
            ah.c("falling back to local store");
            if (this.e != null) {
                this.d = this.e;
            } else {
                u uVarA = u.a();
                uVarA.a(this.h, this.g);
                this.d = uVarA.b();
            }
            this.b = w.CONNECTED_LOCAL;
            h();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void k() {
        if (!this.q && this.c != null && this.b != w.CONNECTED_LOCAL) {
            try {
                this.j++;
                a(this.l);
                this.b = w.CONNECTING;
                this.l = new Timer("Failed Connect");
                this.l.schedule(new y(this), 3000L);
                ah.c("connecting to Analytics service");
                this.c.b();
            } catch (SecurityException e) {
                ah.d("security exception on connectToService");
                j();
            }
        } else {
            ah.d("client not initialized.");
            j();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void l() {
        if (this.c != null && this.b == w.CONNECTED_SERVICE) {
            this.b = w.PENDING_DISCONNECT;
            this.c.c();
        }
    }

    @Override // com.google.a.a.a.d
    public synchronized void a() {
        this.l = a(this.l);
        this.j = 0;
        ah.c("Connected to service");
        this.b = w.CONNECTED_SERVICE;
        if (this.p) {
            l();
            this.p = false;
        } else {
            h();
            this.m = a(this.m);
            this.m = new Timer("disconnect check");
            this.m.schedule(new x(this), this.s);
        }
    }

    @Override // com.google.a.a.a.d
    public synchronized void b() {
        if (this.b == w.PENDING_DISCONNECT) {
            ah.c("Disconnected from service");
            g();
            this.b = w.DISCONNECTED;
        } else {
            ah.c("Unexpected disconnect.");
            this.b = w.PENDING_CONNECTION;
            if (this.j < 2) {
                m();
            } else {
                j();
            }
        }
    }

    @Override // com.google.a.a.a.e
    public synchronized void a(int i, Intent intent) {
        this.b = w.PENDING_CONNECTION;
        if (this.j < 2) {
            ah.d("Service unavailable (code=" + i + "), will retry.");
            m();
        } else {
            ah.d("Service unavailable (code=" + i + "), using local store.");
            j();
        }
    }

    private void m() {
        this.k = a(this.k);
        this.k = new Timer("Service Reconnect");
        this.k.schedule(new aa(this), 5000L);
    }
}

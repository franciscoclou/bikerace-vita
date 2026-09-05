package com.topfreegames.bikerace.worldcup;

import com.topfreegames.bikerace.bb;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: compiled from: IncreasedChanceManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final com.topfreegames.bikerace.c[] f1453a = {com.topfreegames.bikerace.c.WORLDCUP_USA, com.topfreegames.bikerace.c.WORLDCUP_ENGLAND, com.topfreegames.bikerace.c.WORLDCUP_BRAZIL, com.topfreegames.bikerace.c.WORLDCUP_AUSTRALIA};
    private bb b;
    private com.topfreegames.bikerace.c c;
    private long d = -1;
    private ArrayList<i> e = new ArrayList<>();
    private Timer f;
    private j g;
    private j h;
    private c i;

    h(bb bbVar) {
        if (bbVar == null) {
            throw new IllegalArgumentException("Config cannot be null!");
        }
        this.b = bbVar;
    }

    void a() {
        f();
        TimerTask timerTask = new TimerTask() { // from class: com.topfreegames.bikerace.worldcup.h.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (com.topfreegames.c.a.a().getTime() <= h.this.d) {
                    long jE = h.this.e();
                    synchronized (h.this.e) {
                        Iterator it = h.this.e.iterator();
                        while (it.hasNext()) {
                            ((i) it.next()).a(jE);
                        }
                    }
                    return;
                }
                h.this.j();
                h.this.k();
                long jE2 = h.this.e();
                synchronized (h.this.e) {
                    Iterator it2 = h.this.e.iterator();
                    while (it2.hasNext()) {
                        ((i) it2.next()).a(h.this.c, h.this.d(), jE2);
                    }
                }
            }
        };
        timerTask.run();
        this.f = new Timer();
        this.f.schedule(timerTask, 0L, 1000L);
    }

    void b() {
        synchronized (this.e) {
            this.e.clear();
        }
        f();
    }

    public com.topfreegames.bikerace.c c() {
        return this.c;
    }

    void a(j jVar, j jVar2, c cVar) {
        this.g = jVar;
        this.h = jVar2;
        this.i = cVar;
    }

    public p d() {
        for (a aVar : this.i.b(this.c)) {
            if (this.g.a(aVar)) {
                return p.RARE;
            }
        }
        for (a aVar2 : c.c(this.c)) {
            if (!this.g.a(aVar2)) {
                return p.ORDINARY;
            }
        }
        return p.RARE;
    }

    public long e() {
        return this.d - com.topfreegames.c.a.a().getTime();
    }

    public void a(i iVar) {
        synchronized (this.e) {
            this.e.add(iVar);
        }
    }

    public void b(i iVar) {
        synchronized (this.e) {
            this.e.remove(iVar);
        }
    }

    private void f() {
        if (this.f != null) {
            this.f.cancel();
            this.f = null;
        }
    }

    private int g() {
        return h() % this.b.ar().length;
    }

    private int h() {
        return Math.max(0, (int) ((com.topfreegames.c.a.a().getTime() - this.b.aq()) / this.b.as()));
    }

    private int i() {
        long time = com.topfreegames.c.a.a().getTime();
        return Math.max(0, (int) ((this.b.ae() - time) / this.b.as()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        long time = com.topfreegames.c.a.a().getTime();
        long jAs = this.b.as();
        long jAe = this.b.ae() - com.topfreegames.c.a.a().getTime();
        if (jAe >= 0 && f1453a.length > jAe / jAs) {
            this.c = f1453a[i()];
        } else if (l.b(time)) {
            this.c = com.topfreegames.bikerace.c.WORLDCUP_USA;
        } else {
            this.c = this.b.ar()[g()];
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        this.d = (((long) (h() + 1)) * this.b.as()) + this.b.aq();
    }

    public long a(com.topfreegames.bikerace.c cVar) {
        long time = com.topfreegames.c.a.a().getTime();
        long jAe = this.b.ae();
        com.topfreegames.bikerace.c[] cVarArrAr = this.b.ar();
        int i = 0;
        while (true) {
            if (i < cVarArrAr.length) {
                if (cVar == cVarArrAr[i]) {
                    break;
                }
                i++;
            } else {
                i = 0;
                break;
            }
        }
        long jAs = this.b.as();
        long jAq = this.b.aq();
        long j = ((long) i) * jAs;
        while (true) {
            j += jAq;
            if (j >= jAe || time <= j) {
                break;
            }
            jAq = ((long) cVarArrAr.length) * jAs;
        }
        if (j > jAe) {
            return 0L;
        }
        return j;
    }
}

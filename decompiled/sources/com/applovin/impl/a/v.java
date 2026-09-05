package com.applovin.impl.a;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final d f214a;
    protected final com.applovin.a.j b;
    private final ScheduledExecutorService c = a("main");
    private final ScheduledExecutorService d = a("back");

    v(d dVar) {
        this.f214a = dVar;
        this.b = dVar.f();
    }

    private static void a(Runnable runnable, long j, ScheduledExecutorService scheduledExecutorService) {
        if (j > 0) {
            scheduledExecutorService.schedule(runnable, j, TimeUnit.MILLISECONDS);
        } else {
            scheduledExecutorService.submit(runnable);
        }
    }

    protected ScheduledExecutorService a(String str) {
        return Executors.newScheduledThreadPool(1, new y(this, str));
    }

    void a(u uVar, long j) {
        if (uVar == null) {
            throw new IllegalArgumentException("No task specified");
        }
        a(uVar, j, this.c);
    }

    void a(x xVar, w wVar) {
        a(xVar, wVar, 0L);
    }

    void a(x xVar, w wVar, long j) {
        if (xVar == null) {
            throw new IllegalArgumentException("No task specified");
        }
        if (j < 0) {
            throw new IllegalArgumentException("Invalid delay specified: " + j);
        }
        this.b.a(xVar.c, "Scheduling " + xVar.c + " on " + wVar + " queue in " + j + "ms.");
        ab abVar = new ab(this, xVar, wVar);
        if (wVar == w.MAIN) {
            a(abVar, j, this.c);
        } else {
            a(abVar, j, this.d);
        }
    }
}

package com.b.a.a;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class i implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ScheduledExecutorService f301a;
    private final j b;
    private final bu c;
    private ScheduledFuture<?> d;
    private int e = -1;
    private m f;

    public i(ScheduledExecutorService scheduledExecutorService, j jVar, bu buVar) {
        this.f301a = scheduledExecutorService;
        this.b = jVar;
        this.c = buVar;
    }

    @Override // com.b.a.a.t
    public final void a() {
        int size;
        Exception e;
        if (this.f == null) {
            ba.c("skipping analytics files send because we don't yet know the target endpoint");
            return;
        }
        ba.c("Sending all analytics files");
        List<File> listB = this.b.b();
        int i = 0;
        while (listB.size() > 0) {
            try {
                boolean zA = this.f.a(ck.a(c.a().w(), false), listB);
                if (zA) {
                    size = listB.size() + i;
                    try {
                        this.b.a(listB);
                        i = size;
                    } catch (Exception e2) {
                        e = e2;
                        ba.d("Crashlytics failed to send batch of analytics files to server: " + e.getMessage());
                        i = size;
                    }
                }
                Locale locale = Locale.US;
                Object[] objArr = new Object[2];
                objArr[0] = Integer.valueOf(listB.size());
                objArr[1] = zA ? "succeeded" : "did not succeed";
                ba.c(String.format(locale, "attempt to send batch of %d analytics files %s", objArr));
                if (!zA) {
                    break;
                } else {
                    listB = this.b.b();
                }
            } catch (Exception e3) {
                size = i;
                e = e3;
            }
        }
        if (i == 0) {
            this.b.d();
        }
    }

    private void a(int i, int i2) {
        try {
            x xVar = new x(this.b, this);
            ba.c("Scheduling time based file roll over every " + i2 + " seconds");
            this.d = this.f301a.scheduleAtFixedRate(xVar, i, i2, TimeUnit.SECONDS);
        } catch (RejectedExecutionException e) {
            ba.d("Crashlytics failed to schedule time based analytics file roll over");
        }
    }

    @Override // com.b.a.a.t
    public final void c() {
        if (this.d != null) {
            ba.c("Cancelling time-based rollover because no events are currently being generated.");
            this.d.cancel(false);
            this.d = null;
        }
    }

    @Override // com.b.a.a.t
    public final void a(aj ajVar, String str) {
        this.f = new g(str, ajVar.f258a, this.c);
        this.b.a(ajVar);
        this.e = ajVar.b;
        a(0, this.e);
    }

    @Override // com.b.a.a.t
    public final void b() {
        this.b.c();
    }

    @Override // com.b.a.a.t
    public final void a(u uVar) throws Throwable {
        ba.c(uVar.toString());
        try {
            this.b.a(uVar);
        } catch (IOException e) {
            ba.d("Crashlytics failed to write session event.");
        }
        boolean z = this.e != -1;
        boolean z2 = this.d == null;
        if (!z || !z2) {
            return;
        }
        a(this.e, this.e);
    }

    @Override // com.b.a.a.t
    public final void d() throws Throwable {
        try {
            this.b.a();
        } catch (IOException e) {
            ba.d("Crashlytics failed to roll analytics file over.");
        }
    }
}

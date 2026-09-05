package com.b.a.a;

import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class bm extends az {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ String f277a;
    private /* synthetic */ ExecutorService b;
    private /* synthetic */ long c;
    private /* synthetic */ TimeUnit d;

    bm(String str, ExecutorService executorService, long j, TimeUnit timeUnit) {
        this.f277a = str;
        this.b = executorService;
        this.c = j;
        this.d = timeUnit;
    }

    @Override // com.b.a.a.az
    public final void a() {
        try {
            cm.a().b().a("Crashlytics", "Executing shutdown hook for " + this.f277a);
            this.b.shutdown();
            if (!this.b.awaitTermination(this.c, this.d)) {
                cm.a().b().a("Crashlytics", this.f277a + " did not shut down in the allocated time. Requesting immediate shutdown.");
                this.b.shutdownNow();
            }
        } catch (InterruptedException e) {
            cm.a().b().a("Crashlytics", String.format(Locale.US, "Interrupted while waiting for %s to shut down. Requesting immediate shutdown.", this.f277a));
            this.b.shutdownNow();
        }
    }
}

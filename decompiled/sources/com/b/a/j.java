package com.b.a;

import com.b.a.a.cm;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class j implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ Runnable f343a;

    j(bc bcVar, Runnable runnable) {
        this.f343a = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.f343a.run();
        } catch (Exception e) {
            cm.a().b().a("Crashlytics", "Failed to execute task.", e);
        }
    }
}

package com.b.a;

import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class bb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f334a;
    private final CountDownLatch b;

    private bb(d dVar) {
        this.f334a = false;
        this.b = new CountDownLatch(1);
    }

    /* synthetic */ bb(d dVar, byte b) {
        this(dVar);
    }

    final void a(boolean z) {
        this.f334a = z;
        this.b.countDown();
    }

    final boolean a() {
        return this.f334a;
    }

    final void b() {
        try {
            this.b.await();
        } catch (InterruptedException e) {
        }
    }
}

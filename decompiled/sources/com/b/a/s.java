package com.b.a;

import java.util.Date;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class s implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ Date f349a;
    private /* synthetic */ Thread b;
    private /* synthetic */ Throwable c;
    private /* synthetic */ bc d;

    s(bc bcVar, Date date, Thread thread, Throwable th) {
        this.d = bcVar;
        this.f349a = date;
        this.b = thread;
        this.c = th;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        if (!this.d.m.get()) {
            bc.b(this.d, this.f349a, this.b, this.c);
        }
    }
}

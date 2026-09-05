package com.b.a;

import java.util.Date;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class q implements Callable<Void> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ Date f347a;
    private /* synthetic */ Thread b;
    private /* synthetic */ Throwable c;
    private /* synthetic */ bc d;

    q(bc bcVar, Date date, Thread thread, Throwable th) {
        this.d = bcVar;
        this.f347a = date;
        this.b = thread;
        this.c = th;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Void call() throws Throwable {
        bc.a(this.d, this.f347a, this.b, this.c);
        return null;
    }
}

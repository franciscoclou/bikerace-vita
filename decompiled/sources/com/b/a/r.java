package com.b.a;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class r implements Callable<Boolean> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ bc f348a;

    r(bc bcVar) {
        this.f348a = bcVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Boolean call() throws Throwable {
        if (this.f348a.m.get()) {
            return false;
        }
        this.f348a.l();
        this.f348a.k();
        return true;
    }
}

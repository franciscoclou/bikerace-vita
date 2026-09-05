package com.b.a;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class bf implements Callable<Void> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ bc f337a;

    bf(bc bcVar) {
        this.f337a = bcVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Void call() throws Throwable {
        if (this.f337a.g()) {
            return null;
        }
        this.f337a.k();
        return null;
    }
}

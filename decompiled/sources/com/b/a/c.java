package com.b.a;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class c implements Callable<Boolean> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ bc f338a;

    c(bc bcVar) {
        this.f338a = bcVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Boolean call() {
        return Boolean.valueOf(this.f338a.l.exists());
    }
}

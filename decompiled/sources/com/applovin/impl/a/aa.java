package com.applovin.impl.a;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class aa implements Thread.UncaughtExceptionHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ y f177a;

    aa(y yVar) {
        this.f177a = yVar;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        this.f177a.f216a.b.b("TaskManager", "Caught unhandled exception", th);
    }
}

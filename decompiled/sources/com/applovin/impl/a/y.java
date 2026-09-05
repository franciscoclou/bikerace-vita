package com.applovin.impl.a;

import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class y implements ThreadFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ v f216a;
    private final String b;

    public y(v vVar, String str) {
        this.f216a = vVar;
        this.b = str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, "AppLovinSdk:" + this.b + ":" + ah.a(this.f216a.f214a.a()));
        thread.setDaemon(true);
        thread.setPriority(10);
        thread.setUncaughtExceptionHandler(new aa(this));
        return thread;
    }
}

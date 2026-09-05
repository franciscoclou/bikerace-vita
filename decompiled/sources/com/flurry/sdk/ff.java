package com.flurry.sdk;

import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ff implements ThreadFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ThreadGroup f564a;
    private final int b;

    public ff(String str, int i) {
        this.f564a = new ThreadGroup(str);
        this.b = i;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(this.f564a, runnable);
        thread.setName(this.f564a.getName() + ":" + thread.getId());
        thread.setPriority(this.b);
        return thread;
    }
}

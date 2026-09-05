package com.b.a.a;

import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class bk implements ThreadFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ String f275a;
    private /* synthetic */ AtomicLong b;

    bk(String str, AtomicLong atomicLong) {
        this.f275a = str;
        this.b = atomicLong;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread threadNewThread = Executors.defaultThreadFactory().newThread(new bl(this, runnable));
        threadNewThread.setName(String.format(Locale.US, this.f275a, Long.valueOf(this.b.getAndIncrement())));
        return threadNewThread;
    }
}

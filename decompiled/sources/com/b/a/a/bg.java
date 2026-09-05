package com.b.a.a;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class bg {
    public long a() {
        return System.currentTimeMillis();
    }

    public static ExecutorService a(String str) {
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor(c(str));
        a(str, executorServiceNewSingleThreadExecutor);
        return executorServiceNewSingleThreadExecutor;
    }

    public static ScheduledExecutorService b(String str) {
        ScheduledExecutorService scheduledExecutorServiceNewSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor(c(str));
        a(str, scheduledExecutorServiceNewSingleThreadScheduledExecutor);
        return scheduledExecutorServiceNewSingleThreadScheduledExecutor;
    }

    public static ThreadFactory c(String str) {
        return new bk(str, new AtomicLong(1L));
    }

    private static void a(String str, ExecutorService executorService) {
        Runtime.getRuntime().addShutdownHook(new Thread(new bm(str, executorService, 2L, TimeUnit.SECONDS), "Crashlytics Shutdown Hook for " + str));
    }
}

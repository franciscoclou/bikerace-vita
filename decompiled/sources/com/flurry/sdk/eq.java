package com.flurry.sdk;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class eq {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static eq f543a;
    private final Map<Thread.UncaughtExceptionHandler, Void> c = new WeakHashMap();
    private final Thread.UncaughtExceptionHandler b = Thread.getDefaultUncaughtExceptionHandler();

    public static synchronized eq a() {
        if (f543a == null) {
            f543a = new eq();
        }
        return f543a;
    }

    public void a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        synchronized (this.c) {
            this.c.put(uncaughtExceptionHandler, null);
        }
    }

    private Set<Thread.UncaughtExceptionHandler> b() {
        Set<Thread.UncaughtExceptionHandler> setKeySet;
        synchronized (this.c) {
            setKeySet = this.c.keySet();
        }
        return setKeySet;
    }

    private eq() {
        Thread.setDefaultUncaughtExceptionHandler(new a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Thread thread, Throwable th) {
        Iterator<Thread.UncaughtExceptionHandler> it = b().iterator();
        while (it.hasNext()) {
            it.next().uncaughtException(thread, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Thread thread, Throwable th) {
        if (this.b != null) {
            this.b.uncaughtException(thread, th);
        }
    }

    final class a implements Thread.UncaughtExceptionHandler {
        private a() {
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            eq.this.a(thread, th);
            eq.this.b(thread, th);
        }
    }
}

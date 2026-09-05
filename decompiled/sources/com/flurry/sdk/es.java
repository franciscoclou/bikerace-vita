package com.flurry.sdk;

import com.facebook.widget.PlacePickerFragment;
import com.flurry.sdk.fj;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class es<T extends fj> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f546a = es.class.getSimpleName();
    private final ek<Object, T> b;
    private final HashMap<T, Object> c;
    private final HashMap<T, Future<?>> d;
    private final ThreadPoolExecutor e;

    public es(String str) {
        this(str, 1, PlacePickerFragment.DEFAULT_RADIUS_IN_METERS);
    }

    public es(String str, int i, int i2) {
        this(str, i, i, i2, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
    }

    public es(String str, int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue) {
        this.b = new ek<>();
        this.c = new HashMap<>();
        this.d = new HashMap<>();
        this.e = new ThreadPoolExecutor(i, i2, j, timeUnit, blockingQueue) { // from class: com.flurry.sdk.es.1
            @Override // java.util.concurrent.ThreadPoolExecutor
            protected void beforeExecute(Thread thread, Runnable runnable) {
                final fj fjVar;
                super.beforeExecute(thread, runnable);
                if (runnable instanceof a) {
                    fjVar = (fj) ((a) runnable).a();
                } else if (runnable instanceof fj) {
                    fjVar = (fj) runnable;
                } else {
                    ex.a(6, es.f546a, "Unknown runnable class: " + runnable.getClass().getName());
                    return;
                }
                new fi() { // from class: com.flurry.sdk.es.1.1
                    @Override // com.flurry.sdk.fi
                    public void a() {
                        fjVar.d();
                    }
                }.run();
            }

            @Override // java.util.concurrent.ThreadPoolExecutor
            protected void afterExecute(Runnable runnable, Throwable th) {
                final fj fjVar;
                super.afterExecute(runnable, th);
                if (runnable instanceof a) {
                    fjVar = (fj) ((a) runnable).a();
                } else if (runnable instanceof fj) {
                    fjVar = (fj) runnable;
                } else {
                    ex.a(6, es.f546a, "Unknown runnable class: " + runnable.getClass().getName());
                    return;
                }
                synchronized (es.this.d) {
                    es.this.d.remove(fjVar);
                }
                es.this.a(fjVar);
                new fi() { // from class: com.flurry.sdk.es.1.2
                    @Override // com.flurry.sdk.fi
                    public void a() {
                        fjVar.b();
                    }
                }.run();
            }

            @Override // java.util.concurrent.AbstractExecutorService
            protected <V> RunnableFuture<V> newTaskFor(Callable<V> callable) {
                throw new UnsupportedOperationException("Callable not supported");
            }

            @Override // java.util.concurrent.AbstractExecutorService
            protected <V> RunnableFuture<V> newTaskFor(Runnable runnable, V v) {
                a aVar = new a(runnable, v);
                synchronized (es.this.d) {
                    es.this.d.put((fj) runnable, aVar);
                }
                return aVar;
            }
        };
        this.e.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy() { // from class: com.flurry.sdk.es.2
            @Override // java.util.concurrent.ThreadPoolExecutor.DiscardPolicy, java.util.concurrent.RejectedExecutionHandler
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                final fj fjVar;
                super.rejectedExecution(runnable, threadPoolExecutor);
                if (runnable instanceof a) {
                    fjVar = (fj) ((a) runnable).a();
                } else if (runnable instanceof fj) {
                    fjVar = (fj) runnable;
                } else {
                    ex.a(6, es.f546a, "Unknown runnable class: " + runnable.getClass().getName());
                    return;
                }
                synchronized (es.this.d) {
                    es.this.d.remove(fjVar);
                }
                es.this.a(fjVar);
                new fi() { // from class: com.flurry.sdk.es.2.1
                    @Override // com.flurry.sdk.fi
                    public void a() {
                        fjVar.c();
                    }
                }.run();
            }
        });
        this.e.setThreadFactory(new ff(str, 1));
    }

    public synchronized void a(Object obj, T t) {
        if (obj != null && t != null) {
            b(obj, t);
            this.e.submit(t);
        }
    }

    public synchronized long a(Object obj) {
        long size = 0;
        synchronized (this) {
            if (obj != null) {
                List<T> listA = this.b.a(obj);
                if (listA != null) {
                    size = listA.size();
                }
            }
        }
        return size;
    }

    private synchronized void b(Object obj, T t) {
        this.b.a(obj, t);
        this.c.put(t, obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(T t) {
        c(this.c.get(t), t);
    }

    private synchronized void c(Object obj, T t) {
        this.b.b(obj, t);
        this.c.remove(t);
    }

    class a<V> extends FutureTask<V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final WeakReference<Callable<V>> f552a;
        private final WeakReference<Runnable> b;

        public a(Runnable runnable, V v) {
            super(runnable, v);
            this.f552a = null;
            this.b = new WeakReference<>(runnable);
        }

        public Runnable a() {
            return this.b.get();
        }
    }
}

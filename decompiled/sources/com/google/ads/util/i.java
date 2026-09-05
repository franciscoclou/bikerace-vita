package com.google.ads.util;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Object f728a = new Object();
    private static int b = 0;
    private static HashMap<Class<?>, Integer> c = new HashMap<>();
    private final ArrayList<a<?>> d = new ArrayList<>();
    public final int u;

    public i() {
        synchronized (f728a) {
            int i = b;
            b = i + 1;
            this.u = i;
            Integer num = c.get(getClass());
            if (num == null) {
                c.put(getClass(), 1);
            } else {
                c.put(getClass(), Integer.valueOf(num.intValue() + 1));
            }
        }
        com.google.ads.util.b.d("State created: " + toString());
    }

    protected void finalize() throws Throwable {
        synchronized (f728a) {
            c.put(getClass(), Integer.valueOf(c.get(getClass()).intValue() - 1));
        }
        super.finalize();
    }

    public String toString() {
        return getClass().getSimpleName() + "[" + this.u + "]";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(a<?> aVar) {
        this.d.add(aVar);
    }

    public abstract class a<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected T f729a;
        protected final String b;

        private a(i iVar, String str) {
            this(str, (Object) null);
        }

        private a(String str, T t) {
            this.b = str;
            i.this.a(this);
            this.f729a = t;
        }

        public String toString() {
            return i.this.toString() + "." + this.b + " = " + this.f729a;
        }
    }

    public final class c<T> extends a<T> {
        private boolean e;

        public c(String str) {
            super(str);
            this.e = false;
            this.e = false;
        }

        public c(String str, T t) {
            super(str, t);
            this.e = false;
            this.e = false;
        }

        public synchronized T a() {
            return this.f729a;
        }

        public synchronized void a(T t) {
            com.google.ads.util.b.d("State changed - " + i.this.toString() + "." + this.b + ": '" + t + "' <-- '" + this.f729a + "'.");
            this.f729a = t;
            this.e = true;
        }

        @Override // com.google.ads.util.i.a
        public String toString() {
            return super.toString() + (this.e ? " (*)" : "");
        }
    }

    public final class b<T> extends a<T> {
        public b(String str, T t) {
            super(str, t);
        }

        public T a() {
            return this.f729a;
        }

        @Override // com.google.ads.util.i.a
        public String toString() {
            return super.toString() + " (!)";
        }
    }

    public final class d<T> extends a<WeakReference<T>> {
        public d(String str, T t) {
            super(str, new WeakReference(t));
        }

        public T a() {
            return (T) ((WeakReference) this.f729a).get();
        }

        @Override // com.google.ads.util.i.a
        public String toString() {
            return i.this.toString() + "." + this.b + " = " + a() + " (?)";
        }
    }
}

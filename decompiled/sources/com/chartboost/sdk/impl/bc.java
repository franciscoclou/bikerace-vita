package com.chartboost.sdk.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
abstract class bc<K, V, M extends Map<K, V>> implements Serializable, ConcurrentMap<K, V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private volatile M f438a;
    private final transient Lock b = new ReentrantLock();
    private final h<K, V> c;

    abstract <N extends Map<? extends K, ? extends V>> M a(N n);

    protected <N extends Map<? extends K, ? extends V>> bc(N n, h.a aVar) {
        this.f438a = (M) bd.a("delegate", a((Map) bd.a("map", n)));
        this.c = ((h.a) bd.a("viewType", aVar)).a(this);
    }

    @Override // java.util.Map
    public final void clear() {
        this.b.lock();
        try {
            b(a(Collections.emptyMap()));
        } finally {
            this.b.unlock();
        }
    }

    @Override // java.util.Map
    public final V remove(Object obj) {
        this.b.lock();
        try {
            if (this.f438a.containsKey(obj)) {
                Map mapA = a();
                try {
                    V v = (V) mapA.remove(obj);
                    b(mapA);
                    this.b.unlock();
                    return v;
                } catch (Throwable th) {
                    b(mapA);
                    throw th;
                }
            }
            this.b.unlock();
            return null;
        } catch (Throwable th2) {
            this.b.unlock();
            throw th2;
        }
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public boolean remove(Object obj, Object obj2) {
        Lock lock;
        this.b.lock();
        try {
            if (this.f438a.containsKey(obj) && a(obj2, this.f438a.get(obj))) {
                Map mapA = a();
                mapA.remove(obj);
                b(mapA);
                return true;
            }
            return false;
        } finally {
            this.b.unlock();
        }
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public boolean replace(K k, V v, V v2) {
        Lock lock;
        this.b.lock();
        try {
            if (this.f438a.containsKey(k) && a(v, this.f438a.get(k))) {
                Map mapA = a();
                mapA.put(k, v2);
                b(mapA);
                return true;
            }
            return false;
        } finally {
            this.b.unlock();
        }
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public V replace(K k, V v) {
        this.b.lock();
        try {
            if (this.f438a.containsKey(k)) {
                Map mapA = a();
                try {
                    V v2 = (V) mapA.put(k, v);
                    b(mapA);
                    this.b.unlock();
                    return v2;
                } catch (Throwable th) {
                    b(mapA);
                    throw th;
                }
            }
            this.b.unlock();
            return null;
        } catch (Throwable th2) {
            this.b.unlock();
            throw th2;
        }
    }

    @Override // java.util.Map
    public final V put(K k, V v) {
        this.b.lock();
        try {
            Map mapA = a();
            try {
                V v2 = (V) mapA.put(k, v);
                b(mapA);
                this.b.unlock();
                return v2;
            } catch (Throwable th) {
                b(mapA);
                throw th;
            }
        } catch (Throwable th2) {
            this.b.unlock();
            throw th2;
        }
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public V putIfAbsent(K k, V v) {
        V v2;
        this.b.lock();
        try {
            if (!this.f438a.containsKey(k)) {
                Map mapA = a();
                try {
                    v2 = (V) mapA.put(k, v);
                    b(mapA);
                    this.b.unlock();
                } catch (Throwable th) {
                    b(mapA);
                    throw th;
                }
            } else {
                v2 = (V) this.f438a.get(k);
                this.b.unlock();
            }
            return v2;
        } catch (Throwable th2) {
            this.b.unlock();
            throw th2;
        }
    }

    @Override // java.util.Map
    public final void putAll(Map<? extends K, ? extends V> map) {
        this.b.lock();
        try {
            Map mapA = a();
            mapA.putAll(map);
            b(mapA);
        } finally {
            this.b.unlock();
        }
    }

    protected M a() {
        this.b.lock();
        try {
            return (M) a(this.f438a);
        } finally {
            this.b.unlock();
        }
    }

    protected void b(M m) {
        this.f438a = m;
    }

    @Override // java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        return this.c.b();
    }

    @Override // java.util.Map
    public final Set<K> keySet() {
        return this.c.a();
    }

    @Override // java.util.Map
    public final Collection<V> values() {
        return this.c.c();
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return this.f438a.containsKey(obj);
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return this.f438a.containsValue(obj);
    }

    @Override // java.util.Map
    public final V get(Object obj) {
        return (V) this.f438a.get(obj);
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.f438a.isEmpty();
    }

    @Override // java.util.Map
    public final int size() {
        return this.f438a.size();
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        return this.f438a.equals(obj);
    }

    @Override // java.util.Map
    public final int hashCode() {
        return this.f438a.hashCode();
    }

    public String toString() {
        return this.f438a.toString();
    }

    class d extends a<K> implements Set<K> {
        private d() {
        }

        /* synthetic */ d(bc bcVar, d dVar) {
            this();
        }

        @Override // com.chartboost.sdk.impl.bc.a
        Collection<K> a() {
            return bc.this.f438a.keySet();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Collection, java.util.Set
        public void clear() {
            bc.this.b.lock();
            try {
                Map mapA = bc.this.a();
                mapA.keySet().clear();
                bc.this.b(mapA);
            } finally {
                bc.this.b.unlock();
            }
        }

        @Override // java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return bc.this.remove(obj) != null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> collection) {
            bc.this.b.lock();
            try {
                Map mapA = bc.this.a();
                try {
                    boolean zRemoveAll = mapA.keySet().removeAll(collection);
                    bc.this.b(mapA);
                    bc.this.b.unlock();
                    return zRemoveAll;
                } catch (Throwable th) {
                    bc.this.b(mapA);
                    throw th;
                }
            } catch (Throwable th2) {
                bc.this.b.unlock();
                throw th2;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> collection) {
            bc.this.b.lock();
            try {
                Map mapA = bc.this.a();
                try {
                    boolean zRetainAll = mapA.keySet().retainAll(collection);
                    bc.this.b(mapA);
                    bc.this.b.unlock();
                    return zRetainAll;
                } catch (Throwable th) {
                    bc.this.b(mapA);
                    throw th;
                }
            } catch (Throwable th2) {
                bc.this.b.unlock();
                throw th2;
            }
        }
    }

    final class g extends a<V> {
        private g() {
        }

        /* synthetic */ g(bc bcVar, g gVar) {
            this();
        }

        @Override // com.chartboost.sdk.impl.bc.a
        Collection<V> a() {
            return bc.this.f438a.values();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Collection
        public void clear() {
            bc.this.b.lock();
            try {
                Map mapA = bc.this.a();
                mapA.values().clear();
                bc.this.b(mapA);
            } finally {
                bc.this.b.unlock();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Collection
        public boolean remove(Object obj) {
            bc.this.b.lock();
            try {
                if (!contains(obj)) {
                    bc.this.b.unlock();
                    return false;
                }
                Map mapA = bc.this.a();
                try {
                    boolean zRemove = mapA.values().remove(obj);
                    bc.this.b(mapA);
                    bc.this.b.unlock();
                    return zRemove;
                } catch (Throwable th) {
                    bc.this.b(mapA);
                    throw th;
                }
            } catch (Throwable th2) {
                bc.this.b.unlock();
                throw th2;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            bc.this.b.lock();
            try {
                Map mapA = bc.this.a();
                try {
                    boolean zRemoveAll = mapA.values().removeAll(collection);
                    bc.this.b(mapA);
                    bc.this.b.unlock();
                    return zRemoveAll;
                } catch (Throwable th) {
                    bc.this.b(mapA);
                    throw th;
                }
            } catch (Throwable th2) {
                bc.this.b.unlock();
                throw th2;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            bc.this.b.lock();
            try {
                Map mapA = bc.this.a();
                try {
                    boolean zRetainAll = mapA.values().retainAll(collection);
                    bc.this.b(mapA);
                    bc.this.b.unlock();
                    return zRetainAll;
                } catch (Throwable th) {
                    bc.this.b(mapA);
                    throw th;
                }
            } catch (Throwable th2) {
                bc.this.b.unlock();
                throw th2;
            }
        }
    }

    class b extends a<Map.Entry<K, V>> implements Set<Map.Entry<K, V>> {
        private b() {
        }

        /* synthetic */ b(bc bcVar, b bVar) {
            this();
        }

        @Override // com.chartboost.sdk.impl.bc.a
        Collection<Map.Entry<K, V>> a() {
            return bc.this.f438a.entrySet();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Collection, java.util.Set
        public void clear() {
            bc.this.b.lock();
            try {
                Map mapA = bc.this.a();
                mapA.entrySet().clear();
                bc.this.b(mapA);
            } finally {
                bc.this.b.unlock();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            bc.this.b.lock();
            try {
                if (!contains(obj)) {
                    bc.this.b.unlock();
                    return false;
                }
                Map mapA = bc.this.a();
                try {
                    boolean zRemove = mapA.entrySet().remove(obj);
                    bc.this.b(mapA);
                    bc.this.b.unlock();
                    return zRemove;
                } catch (Throwable th) {
                    bc.this.b(mapA);
                    throw th;
                }
            } catch (Throwable th2) {
                bc.this.b.unlock();
                throw th2;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> collection) {
            bc.this.b.lock();
            try {
                Map mapA = bc.this.a();
                try {
                    boolean zRemoveAll = mapA.entrySet().removeAll(collection);
                    bc.this.b(mapA);
                    bc.this.b.unlock();
                    return zRemoveAll;
                } catch (Throwable th) {
                    bc.this.b(mapA);
                    throw th;
                }
            } catch (Throwable th2) {
                bc.this.b.unlock();
                throw th2;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> collection) {
            bc.this.b.lock();
            try {
                Map mapA = bc.this.a();
                try {
                    boolean zRetainAll = mapA.entrySet().retainAll(collection);
                    bc.this.b(mapA);
                    bc.this.b.unlock();
                    return zRetainAll;
                } catch (Throwable th) {
                    bc.this.b(mapA);
                    throw th;
                }
            } catch (Throwable th2) {
                bc.this.b.unlock();
                throw th2;
            }
        }
    }

    class f<T> implements Iterator<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Iterator<T> f443a;

        public f(Iterator<T> it) {
            this.f443a = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f443a.hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            return this.f443a.next();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public abstract class a<E> implements Collection<E> {
        abstract Collection<E> a();

        protected a() {
        }

        @Override // java.util.Collection
        public final boolean contains(Object obj) {
            return a().contains(obj);
        }

        @Override // java.util.Collection
        public final boolean containsAll(Collection<?> collection) {
            return a().containsAll(collection);
        }

        @Override // java.util.Collection, java.lang.Iterable
        public final Iterator<E> iterator() {
            return new f(a().iterator());
        }

        @Override // java.util.Collection
        public final boolean isEmpty() {
            return a().isEmpty();
        }

        @Override // java.util.Collection
        public final int size() {
            return a().size();
        }

        @Override // java.util.Collection
        public final Object[] toArray() {
            return a().toArray();
        }

        @Override // java.util.Collection
        public final <T> T[] toArray(T[] tArr) {
            return (T[]) a().toArray(tArr);
        }

        @Override // java.util.Collection
        public int hashCode() {
            return a().hashCode();
        }

        @Override // java.util.Collection
        public boolean equals(Object obj) {
            return a().equals(obj);
        }

        public String toString() {
            return a().toString();
        }

        @Override // java.util.Collection
        public final boolean add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public final boolean addAll(Collection<? extends E> collection) {
            throw new UnsupportedOperationException();
        }
    }

    private boolean a(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public abstract class h<K, V> {
        abstract Set<K> a();

        abstract Set<Map.Entry<K, V>> b();

        abstract Collection<V> c();

        h() {
        }

        public enum a {
            STABLE { // from class: com.chartboost.sdk.impl.bc.h.a.1
                @Override // com.chartboost.sdk.impl.bc.h.a
                <K, V, M extends Map<K, V>> h<K, V> a(bc<K, V, M> bcVar) {
                    bcVar.getClass();
                    return bcVar.new c();
                }
            },
            LIVE { // from class: com.chartboost.sdk.impl.bc.h.a.2
                @Override // com.chartboost.sdk.impl.bc.h.a
                <K, V, M extends Map<K, V>> h<K, V> a(bc<K, V, M> bcVar) {
                    bcVar.getClass();
                    return bcVar.new e();
                }
            };

            /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
            public static a[] valuesCustom() {
                a[] aVarArrValuesCustom = values();
                int length = aVarArrValuesCustom.length;
                a[] aVarArr = new a[length];
                System.arraycopy(aVarArrValuesCustom, 0, aVarArr, 0, length);
                return aVarArr;
            }

            abstract <K, V, M extends Map<K, V>> h<K, V> a(bc<K, V, M> bcVar);

            /* synthetic */ a(a aVar) {
                this();
            }
        }
    }

    final class c extends h<K, V> implements Serializable {
        c() {
        }

        @Override // com.chartboost.sdk.impl.bc.h
        public Set<K> a() {
            return Collections.unmodifiableSet(bc.this.f438a.keySet());
        }

        @Override // com.chartboost.sdk.impl.bc.h
        public Set<Map.Entry<K, V>> b() {
            return Collections.unmodifiableSet(bc.this.f438a.entrySet());
        }

        @Override // com.chartboost.sdk.impl.bc.h
        public Collection<V> c() {
            return Collections.unmodifiableCollection(bc.this.f438a.values());
        }
    }

    final class e extends h<K, V> implements Serializable {
        private final transient bc<K, V, M>.d b;
        private final transient bc<K, V, M>.b c;
        private final transient bc<K, V, M>.g d;

        /* JADX WARN: Multi-variable type inference failed */
        e() {
            this.b = new d(bc.this, null);
            this.c = new b(bc.this, 0 == true ? 1 : 0);
            this.d = new g(bc.this, 0 == true ? 1 : 0);
        }

        @Override // com.chartboost.sdk.impl.bc.h
        public Set<K> a() {
            return this.b;
        }

        @Override // com.chartboost.sdk.impl.bc.h
        public Set<Map.Entry<K, V>> b() {
            return this.c;
        }

        @Override // com.chartboost.sdk.impl.bc.h
        public Collection<V> c() {
            return this.d;
        }
    }
}

package com.chartboost.sdk.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class bg<K, V> implements bi<K, V>, Map<K, V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ConcurrentMap<K, V> f449a;
    private final bi<K, V> b;

    public static <K, V> Map<K, V> a(bi<K, V> biVar) {
        return new bg(bh.c(), biVar);
    }

    bg(ConcurrentMap<K, V> concurrentMap, bi<K, V> biVar) {
        this.f449a = (ConcurrentMap) bd.a("map", concurrentMap);
        this.b = (bi) bd.a("function", biVar);
    }

    @Override // java.util.Map
    public V get(Object obj) {
        while (true) {
            V v = this.f449a.get(obj);
            if (v == null) {
                V vA = this.b.a(obj);
                if (vA == null) {
                    return null;
                }
                this.f449a.putIfAbsent(obj, vA);
            } else {
                return v;
            }
        }
    }

    @Override // com.chartboost.sdk.impl.bi
    public V a(K k) {
        return get(k);
    }

    @Override // java.util.Map
    public int size() {
        return this.f449a.size();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.f449a.isEmpty();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.f449a.containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.f449a.containsValue(obj);
    }

    @Override // java.util.Map
    public V put(K k, V v) {
        return this.f449a.put(k, v);
    }

    @Override // java.util.Map
    public V remove(Object obj) {
        return this.f449a.remove(obj);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        this.f449a.putAll(map);
    }

    @Override // java.util.Map
    public void clear() {
        this.f449a.clear();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return this.f449a.keySet();
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return this.f449a.values();
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return this.f449a.entrySet();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return this.f449a.equals(obj);
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.f449a.hashCode();
    }
}

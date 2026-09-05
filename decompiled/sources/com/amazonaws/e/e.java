package com.amazonaws.e;

import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class e<K, V> implements Map.Entry<K, V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private K f101a;
    private V b;

    public K a(K k) {
        this.f101a = k;
        return this.f101a;
    }

    @Override // java.util.Map.Entry
    public K getKey() {
        return this.f101a;
    }

    @Override // java.util.Map.Entry
    public V getValue() {
        return this.b;
    }

    @Override // java.util.Map.Entry
    public V setValue(V v) {
        this.b = v;
        return this.b;
    }
}

package com.chartboost.sdk.impl;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
abstract class bh<K, V> extends bc<K, V, Map<K, V>> {
    public static <K, V> a<K, V> b() {
        return new a<>();
    }

    public class a<K, V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private bc.h.a f450a = bc.h.a.STABLE;
        private final Map<K, V> b = new HashMap();

        a() {
        }

        public bh<K, V> a() {
            return new b(this.b, this.f450a);
        }
    }

    public static <K, V> bh<K, V> c() {
        return b().a();
    }

    protected bh(Map<? extends K, ? extends V> map, bc.h.a aVar) {
        super(map, aVar);
    }

    class b<K, V> extends bh<K, V> {
        b(Map<? extends K, ? extends V> map, bc.h.a aVar) {
            super(map, aVar);
        }

        @Override // com.chartboost.sdk.impl.bc
        public <N extends Map<? extends K, ? extends V>> Map<K, V> a(N n) {
            return new HashMap(n);
        }
    }
}

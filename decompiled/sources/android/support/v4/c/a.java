package android.support.v4.c;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: ArrayMap.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a<K, V> extends l<K, V> implements Map<K, V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    f<K, V> f25a;

    private f<K, V> b() {
        if (this.f25a == null) {
            this.f25a = new f<K, V>() { // from class: android.support.v4.c.a.1
                @Override // android.support.v4.c.f
                protected int a() {
                    return a.this.h;
                }

                @Override // android.support.v4.c.f
                protected Object a(int i, int i2) {
                    return a.this.g[(i << 1) + i2];
                }

                @Override // android.support.v4.c.f
                protected int a(Object obj) {
                    return obj == null ? a.this.a() : a.this.a(obj, obj.hashCode());
                }

                @Override // android.support.v4.c.f
                protected int b(Object obj) {
                    return a.this.a(obj);
                }

                @Override // android.support.v4.c.f
                protected Map<K, V> b() {
                    return a.this;
                }

                @Override // android.support.v4.c.f
                protected void a(K k, V v) {
                    a.this.put(k, v);
                }

                @Override // android.support.v4.c.f
                protected V a(int i, V v) {
                    return a.this.a(i, v);
                }

                @Override // android.support.v4.c.f
                protected void a(int i) {
                    a.this.d(i);
                }

                @Override // android.support.v4.c.f
                protected void c() {
                    a.this.clear();
                }
            };
        }
        return this.f25a;
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        a(this.h + map.size());
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return b().d();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return b().e();
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return b().f();
    }
}

package android.support.v4.c;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX INFO: compiled from: MapCollections.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class j<K, V> implements Iterator<Map.Entry<K, V>>, Map.Entry<K, V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f33a;
    final /* synthetic */ f d;
    boolean c = false;
    int b = -1;

    j(f fVar) {
        this.d = fVar;
        this.f33a = fVar.a() - 1;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.b < this.f33a;
    }

    @Override // java.util.Iterator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Map.Entry<K, V> next() {
        this.b++;
        this.c = true;
        return this;
    }

    @Override // java.util.Iterator
    public void remove() {
        if (!this.c) {
            throw new IllegalStateException();
        }
        this.b--;
        this.f33a--;
        this.c = false;
        this.d.a(this.b);
    }

    @Override // java.util.Map.Entry
    public K getKey() {
        if (!this.c) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        return (K) this.d.a(this.b, 0);
    }

    @Override // java.util.Map.Entry
    public V getValue() {
        if (!this.c) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        return (V) this.d.a(this.b, 1);
    }

    @Override // java.util.Map.Entry
    public V setValue(V v) {
        if (!this.c) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        return (V) this.d.a(this.b, v);
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!this.c) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return b.a(entry.getKey(), this.d.a(this.b, 0)) && b.a(entry.getValue(), this.d.a(this.b, 1));
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        if (!this.c) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        Object objA = this.d.a(this.b, 0);
        Object objA2 = this.d.a(this.b, 1);
        return (objA2 != null ? objA2.hashCode() : 0) ^ (objA == null ? 0 : objA.hashCode());
    }

    public final String toString() {
        return getKey() + "=" + getValue();
    }
}

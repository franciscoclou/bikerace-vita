package android.support.v4.c;

import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: LruCache.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class e<K, V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final LinkedHashMap<K, V> f29a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;

    public e(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.c = i;
        this.f29a = new LinkedHashMap<>(0, 0.75f, true);
    }

    public final V a(K k) {
        V vPut;
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            V v = this.f29a.get(k);
            if (v != null) {
                this.g++;
                return v;
            }
            this.h++;
            V vC = c(k);
            if (vC == null) {
                return null;
            }
            synchronized (this) {
                this.e++;
                vPut = this.f29a.put(k, vC);
                if (vPut != null) {
                    this.f29a.put(k, vPut);
                } else {
                    this.b += c(k, vC);
                }
            }
            if (vPut != null) {
                a(false, k, vC, vPut);
                return vPut;
            }
            a(this.c);
            return vC;
        }
    }

    public final V a(K k, V v) {
        V vPut;
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.d++;
            this.b += c(k, v);
            vPut = this.f29a.put(k, v);
            if (vPut != null) {
                this.b -= c(k, vPut);
            }
        }
        if (vPut != null) {
            a(false, k, vPut, v);
        }
        a(this.c);
        return vPut;
    }

    public void a(int i) {
        K key;
        V value;
        while (true) {
            synchronized (this) {
                if (this.b < 0 || (this.f29a.isEmpty() && this.b != 0)) {
                    break;
                }
                if (this.b > i && !this.f29a.isEmpty()) {
                    Map.Entry<K, V> next = this.f29a.entrySet().iterator().next();
                    key = next.getKey();
                    value = next.getValue();
                    this.f29a.remove(key);
                    this.b -= c(key, value);
                    this.f++;
                } else {
                    return;
                }
            }
            a(true, key, value, null);
        }
        throw new IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
    }

    public final V b(K k) {
        V vRemove;
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            vRemove = this.f29a.remove(k);
            if (vRemove != null) {
                this.b -= c(k, vRemove);
            }
        }
        if (vRemove != null) {
            a(false, k, vRemove, null);
        }
        return vRemove;
    }

    protected void a(boolean z, K k, V v, V v2) {
    }

    protected V c(K k) {
        return null;
    }

    private int c(K k, V v) {
        int iB = b(k, v);
        if (iB < 0) {
            throw new IllegalStateException("Negative size: " + k + "=" + v);
        }
        return iB;
    }

    protected int b(K k, V v) {
        return 1;
    }

    public final void a() {
        a(-1);
    }

    public final synchronized String toString() {
        String str;
        synchronized (this) {
            int i = this.g + this.h;
            str = String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.c), Integer.valueOf(this.g), Integer.valueOf(this.h), Integer.valueOf(i != 0 ? (this.g * 100) / i : 0));
        }
        return str;
    }
}

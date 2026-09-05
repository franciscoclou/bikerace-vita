package com.flurry.sdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ek<K, V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<K, List<V>> f536a = new HashMap();
    private int b;

    public List<V> a(K k) {
        if (k == null) {
            return null;
        }
        return a((Object) k, false);
    }

    public void a(K k, V v) {
        if (k != null) {
            a((Object) k, true).add(v);
        }
    }

    public boolean b(K k, V v) {
        List<V> listA;
        boolean zRemove = false;
        if (k != null && (listA = a((Object) k, false)) != null) {
            zRemove = listA.remove(v);
            if (listA.size() == 0) {
                this.f536a.remove(k);
            }
        }
        return zRemove;
    }

    private List<V> a(K k, boolean z) {
        List<V> arrayList = this.f536a.get(k);
        if (z && arrayList == null) {
            if (this.b > 0) {
                arrayList = new ArrayList<>(this.b);
            } else {
                arrayList = new ArrayList<>();
            }
            this.f536a.put(k, arrayList);
        }
        return arrayList;
    }
}

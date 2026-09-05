package com.amazonaws.f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f128a;
    private Map<Integer, com.amazonaws.l> b = new HashMap();
    private List<Object> c;

    public i(int i) {
        this.f128a = i;
        this.c = new ArrayList(i);
    }

    private void a() {
        this.b.remove(this.c.remove(0));
    }

    private void a(int i, com.amazonaws.l lVar) {
        this.b.put(Integer.valueOf(i), lVar);
        this.c.add(Integer.valueOf(i));
    }

    public synchronized void a(Object obj, com.amazonaws.l lVar) {
        if (obj != null) {
            if (this.b.size() >= this.f128a) {
                a();
            }
            a(System.identityHashCode(obj), lVar);
        }
    }
}

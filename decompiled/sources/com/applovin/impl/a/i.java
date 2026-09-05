package com.applovin.impl.a;

import java.util.LinkedList;
import java.util.Queue;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f203a;
    private Queue b;
    private Object c;

    i(int i) {
        this.f203a = i > 10 ? 10 : i;
        this.b = new LinkedList();
        this.c = new Object();
    }

    int a() {
        int size;
        synchronized (this.c) {
            size = this.b.size();
        }
        return size;
    }

    void a(com.applovin.a.a aVar) {
        synchronized (this.c) {
            if (!c()) {
                this.b.offer(aVar);
            }
        }
    }

    int b() {
        return this.f203a;
    }

    boolean c() {
        boolean z;
        synchronized (this.c) {
            z = a() >= this.f203a;
        }
        return z;
    }

    boolean d() {
        boolean z;
        synchronized (this.c) {
            z = a() == 0;
        }
        return z;
    }

    com.applovin.a.a e() {
        com.applovin.a.a aVar;
        try {
            synchronized (this.c) {
                try {
                    aVar = !d() ? (com.applovin.a.a) this.b.poll() : null;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return aVar;
        } catch (Exception e) {
            return null;
        }
    }
}

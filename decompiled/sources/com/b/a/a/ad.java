package com.b.a.a;

import java.io.IOException;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class ad<V> implements Callable<V> {
    protected abstract void a();

    protected abstract V b();

    protected ad() {
    }

    @Override // java.util.concurrent.Callable
    public V call() throws Throwable {
        boolean z = true;
        try {
            try {
                V vB = b();
                try {
                    a();
                    return vB;
                } catch (IOException e) {
                    throw new ac(e);
                }
            } catch (ac e2) {
                throw e2;
            }
        } catch (IOException e3) {
            throw new ac(e3);
        } catch (Throwable th) {
            th = th;
            z = false;
            try {
                a();
            } catch (IOException e4) {
                if (!z) {
                    throw new ac(e4);
                }
            }
            throw th;
        }
    }
}

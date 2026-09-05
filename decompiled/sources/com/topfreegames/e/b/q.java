package com.topfreegames.e.b;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* JADX INFO: compiled from: TopFacebookUserInfoRequestsManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class q implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static q f1538a;
    private ExecutorService b = Executors.newSingleThreadExecutor();
    private LinkedList<n> c = new LinkedList<>();
    private Hashtable<String, n> d = new Hashtable<>();
    private Hashtable<n, List<WeakReference<p>>> e = new Hashtable<>();
    private List<WeakReference<Future<Boolean>>> f = new ArrayList();

    private q() {
    }

    public static q b() {
        if (f1538a == null) {
            f1538a = new q();
        }
        return f1538a;
    }

    public void a(n nVar) {
        if (nVar != null) {
            this.f.add(new WeakReference<>(this.b.submit(new r(this, nVar))));
        }
    }

    @Override // com.topfreegames.e.b.p
    public void a(com.topfreegames.e.l lVar, n nVar, boolean z, boolean z2) {
        synchronized (this.c) {
            if (nVar != null) {
                this.c.remove(nVar);
                this.d.remove(nVar.b());
                List<WeakReference<p>> list = this.e.get(nVar);
                if (list != null) {
                    Iterator<WeakReference<p>> it = list.iterator();
                    while (it.hasNext()) {
                        p pVar = it.next().get();
                        if (pVar != null) {
                            pVar.a(lVar, nVar, z, z2);
                        }
                    }
                }
                if (!z2) {
                    this.e.remove(nVar);
                }
                if (this.c.size() > 0) {
                    this.c.get(0).e();
                }
            }
        }
    }

    public void c() {
        synchronized (this.f) {
            Iterator<WeakReference<Future<Boolean>>> it = this.f.iterator();
            while (it.hasNext()) {
                Future<Boolean> future = it.next().get();
                if (future != null) {
                    future.cancel(true);
                }
            }
            this.f.clear();
        }
        synchronized (this.c) {
            this.c.clear();
        }
    }

    @Override // com.topfreegames.e.b.e
    public void a() {
        synchronized (this.e) {
            Iterator<List<WeakReference<p>>> it = this.e.values().iterator();
            while (it.hasNext()) {
                Iterator<WeakReference<p>> it2 = it.next().iterator();
                while (it2.hasNext()) {
                    p pVar = it2.next().get();
                    if (pVar != null) {
                        pVar.a();
                    }
                }
            }
        }
    }
}

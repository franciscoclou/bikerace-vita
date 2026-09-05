package com.topfreegames.e.b;

import android.graphics.Bitmap;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* JADX INFO: compiled from: TopFacebookPictureRequestsManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static c f1525a;
    private ExecutorService b = Executors.newSingleThreadExecutor();
    private LinkedList<a> c = new LinkedList<>();
    private Hashtable<String, a> d = new Hashtable<>();
    private Hashtable<a, b> e = new Hashtable<>();
    private List<WeakReference<Future<Boolean>>> f = new ArrayList(0);

    private c() {
    }

    public static c b() {
        if (f1525a == null) {
            f1525a = new c();
        }
        return f1525a;
    }

    public void a(a aVar) {
        if (aVar != null) {
            this.f.add(new WeakReference<>(this.b.submit(new d(this, aVar))));
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

    @Override // com.topfreegames.e.b.b
    public void a(Bitmap bitmap, String str, a aVar, boolean z) {
        synchronized (this.c) {
            if (aVar != null) {
                this.c.remove(aVar);
                b bVar = this.e.get(aVar);
                if (bVar != null) {
                    bVar.a(bitmap, str, aVar, z);
                    if (!z) {
                        this.e.remove(aVar);
                    }
                }
                if (this.c.size() > 0) {
                    this.c.get(0).c();
                }
            }
        }
    }

    @Override // com.topfreegames.e.b.e
    public void a() {
        synchronized (this.e) {
            Iterator<b> it = this.e.values().iterator();
            while (it.hasNext()) {
                it.next().a();
            }
        }
    }
}

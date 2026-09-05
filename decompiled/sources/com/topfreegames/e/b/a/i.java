package com.topfreegames.e.b.a;

import android.app.Activity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* JADX INFO: compiled from: TopFacebookAppRequestManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static i f1517a = null;
    private ExecutorService b;
    private List<WeakReference<Future<Boolean>>> c;

    private i() {
        this.b = null;
        this.c = null;
        this.b = Executors.newSingleThreadExecutor();
        this.c = new ArrayList(0);
    }

    public static i a() {
        if (f1517a == null) {
            synchronized (i.class) {
                f1517a = new i();
            }
        }
        return f1517a;
    }

    public void a(String str, String str2, String str3, Activity activity, d dVar) {
        if (str == null) {
            throw new IllegalArgumentException("To cannot be null!");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("Message cannot be null!");
        }
        if (activity == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        a(new k(new e(dVar, str, str2, str3, 1500L, activity)));
    }

    public void a(String str, f fVar) {
        if (str == null) {
            throw new IllegalArgumentException("ID cannot be null!");
        }
        a(new k(new g(fVar, str, 1500L)));
    }

    public void a(String str, m mVar) {
        if (str == null) {
            throw new IllegalArgumentException("ID cannot be null!");
        }
        a(new k(new n(mVar, str, 1500L)));
    }

    private void a(k kVar) {
        if (kVar != null) {
            this.c.add(new WeakReference<>(this.b.submit(new l(this, kVar))));
        }
    }
}

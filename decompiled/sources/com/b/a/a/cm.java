package com.b.a.a;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class cm extends ci {
    private boolean c;
    private File d;
    private Application e;
    private WeakReference<Activity> f;
    private String g;
    private AtomicReference<cj> b = new AtomicReference<>();
    private int h = 4;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private bz f294a = new b(cf.f292a);
    private ConcurrentHashMap<Class<? extends cl>, cl> i = new ConcurrentHashMap<>();

    public static cm a() {
        return cp.f297a;
    }

    cm() {
    }

    public static synchronized void a(Context context, cl... clVarArr) {
        if (!cp.f297a.x()) {
            cm cmVar = cp.f297a;
            cmVar.e = ck.b(context);
            cm cmVarA = cmVar.a(ck.a(context));
            for (cl clVar : clVarArr) {
                if (!cmVarA.i.containsKey(clVarArr)) {
                    cmVarA.i.putIfAbsent((Class<? extends cl>) clVar.getClass(), clVar);
                }
            }
            cmVarA.b(context);
        }
    }

    public final cj b() {
        cj cjVar = this.b.get();
        if (cjVar == null) {
            ck ckVar = new ck();
            if (!this.b.compareAndSet(null, ckVar)) {
                return this.b.get();
            }
            return ckVar;
        }
        return cjVar;
    }

    public final void a(cj cjVar) {
        this.b.set(cjVar);
    }

    public final Application c() {
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public cm a(Activity activity) {
        this.f = new WeakReference<>(activity);
        return this;
    }

    public final Activity d() {
        if (this.f != null) {
            return this.f.get();
        }
        return null;
    }

    @Override // com.b.a.a.ci
    protected final void e() {
        Context contextW = w();
        this.d = new File(contextW.getFilesDir(), "com.crashlytics.sdk.android");
        if (!this.d.exists()) {
            this.d.mkdirs();
        }
        if (Build.VERSION.SDK_INT >= 14) {
            cn.a(new cn(this, (byte) 0), this.e);
        }
        if (this.c && Log.isLoggable("CrashlyticsInternal", 3)) {
            StringBuilder sb = new StringBuilder();
            for (cl clVar : this.i.values()) {
                long jNanoTime = System.nanoTime();
                clVar.b(contextW);
                sb.append("sdkPerfStart.").append(clVar.getClass().getName()).append('=').append(System.nanoTime() - jNanoTime).append('\n');
            }
            Log.d("CrashlyticsInternal", sb.toString());
            return;
        }
        Iterator<cl> it = this.i.values().iterator();
        while (it.hasNext()) {
            it.next().b(contextW);
        }
    }

    public final String f() {
        return "1.1.11.10";
    }

    public final <T extends cl> T a(Class<T> cls) {
        return (T) this.i.get(cls);
    }

    public final boolean g() {
        return this.c;
    }

    public final int h() {
        return this.h;
    }

    public final File i() {
        return this.d;
    }

    public final String j() {
        return this.g;
    }
}

package com.b.a.a;

import android.app.Activity;
import android.os.Looper;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class n implements bj {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    t f304a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;
    private final ScheduledExecutorService j;

    public n(String str, String str2, String str3, String str4, String str5, String str6, String str7, j jVar, bu buVar) {
        this(str, str2, str3, str4, str5, str6, str7, jVar, bg.b("Crashlytics SAM"), buVar);
    }

    n(String str, String str2, String str3, String str4, String str5, String str6, String str7, j jVar, ScheduledExecutorService scheduledExecutorService, bu buVar) {
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = str5;
        this.g = str6;
        this.h = str7;
        this.i = UUID.randomUUID().toString();
        this.j = scheduledExecutorService;
        this.f304a = new i(scheduledExecutorService, jVar, buVar);
        jVar.a(this);
    }

    public final void a(String str) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("onCrash called from main thread!!!");
        }
        try {
            this.j.submit(new o(this, str)).get();
        } catch (Exception e) {
            ba.d("Crashlytics failed to run analytics task");
        }
    }

    public final void b() {
        a(u.a(this.b, this.i, this.c, this.d, this.e, this.f, this.g, this.h, v.INSTALL, new HashMap()), true);
    }

    public final void a(Activity activity) {
        a(v.CREATE, activity, false);
    }

    public final void b(Activity activity) {
        a(v.DESTROY, activity, false);
    }

    public final void b(String str) {
        a(u.a(this.b, this.i, this.c, this.d, this.e, this.f, this.g, this.h, v.ERROR, Collections.singletonMap("sessionId", str)), false);
    }

    public final void c(Activity activity) {
        a(v.PAUSE, activity, false);
    }

    public final void d(Activity activity) {
        a(v.RESUME, activity, false);
    }

    public final void e(Activity activity) {
        a(v.SAVE_INSTANCE_STATE, activity, false);
    }

    public final void f(Activity activity) {
        a(v.START, activity, false);
    }

    public final void g(Activity activity) {
        a(v.STOP, activity, false);
    }

    private void a(v vVar, Activity activity, boolean z) {
        a(u.a(this.b, this.i, this.c, this.d, this.e, this.f, this.g, this.h, vVar, Collections.singletonMap("activity", activity.getClass().getName())), false);
    }

    private void a(u uVar, boolean z) {
        a(new p(this, uVar, z));
    }

    final void a(aj ajVar, String str) {
        a(new q(this, ajVar, str));
    }

    @Override // com.b.a.a.bj
    public final void c() {
        a(new r(this));
    }

    void a() {
        a(new s(this));
    }

    private void a(Runnable runnable) {
        try {
            this.j.submit(runnable);
        } catch (Exception e) {
            ba.d("Crashlytics failed to submit analytics task");
        }
    }
}

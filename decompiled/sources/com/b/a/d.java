package com.b.a;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.b.a.a.bh;
import com.b.a.a.bn;
import com.b.a.a.bu;
import com.b.a.a.ck;
import com.b.a.a.cl;
import com.b.a.a.cm;
import com.facebook.AppEventsConstants;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class d extends cl {
    private static ContextWrapper j;
    private static String k;
    private static String l;
    private static String m;
    private static String n;
    private static String o;
    private static String p;
    private static String q;
    private static boolean r = false;
    private static t s = null;
    private static bu t;
    private static float u;
    private static d v;
    private e c;
    private bc d;
    private String i;
    private bn e = null;
    private String f = null;
    private String g = null;
    private String h = null;
    private final ConcurrentHashMap<String, String> b = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final long f339a = System.currentTimeMillis();

    static /* synthetic */ int a(d dVar, float f, int i) {
        return (int) (i * f);
    }

    static /* synthetic */ boolean a(d dVar, Activity activity, com.b.a.a.ap apVar) {
        ab abVar = new ab(activity, apVar);
        bb bbVar = new bb(dVar, (byte) 0);
        activity.runOnUiThread(new aw(dVar, activity, bbVar, abVar, apVar));
        cm.a().b().a("Crashlytics", "Waiting for user opt-in.");
        bbVar.b();
        return bbVar.a();
    }

    public static void a(Context context) {
        a(context, 1.0f);
    }

    public static void a(Context context, float f) {
        u = f;
        if (!com.b.a.a.ba.d(context)) {
            cm.a().a(new com.b.a.a.a());
        }
        cm.a(context, a(), new com.b.a.a.c());
    }

    public static synchronized d a() {
        d dVar;
        dVar = (d) cm.a().a(d.class);
        if (dVar == null) {
            if (v == null) {
                v = new d();
            }
            dVar = v;
        }
        return dVar;
    }

    public static void a(Throwable th) {
        d dVarA = a();
        if (dVarA == null || dVarA.d == null) {
            cm.a().b().a("Crashlytics", "Crashlytics must be initialized by calling Crashlytics.start(Context) prior to logging exceptions.", (Throwable) null);
        } else if (th == null) {
            cm.a().b().a(5, "Crashlytics", "Crashlytics is ignoring a request to log a null exception.");
        } else {
            dVarA.d.a(Thread.currentThread(), th);
        }
    }

    static void a(String str) {
        com.b.a.a.c cVar = (com.b.a.a.c) cm.a().a(com.b.a.a.c.class);
        if (cVar != null) {
            cVar.a(new com.b.a.a.bf(str));
        }
    }

    static void b(String str) {
        com.b.a.a.c cVar = (com.b.a.a.c) cm.a().a(com.b.a.a.c.class);
        if (cVar != null) {
            cVar.a(new com.b.a.a.be(str));
        }
    }

    final Map<String, String> b() {
        return Collections.unmodifiableMap(this.b);
    }

    @Deprecated
    public static String c() {
        return a().f();
    }

    final bn d() {
        return this.e;
    }

    @Override // com.b.a.a.ci
    protected final void e() {
        Context contextW = super.w();
        String strA = ck.a(contextW, false);
        if (strA != null) {
            try {
                a(strA, contextW, u);
            } catch (f e) {
                throw e;
            } catch (Exception e2) {
                cm.a().b().a("Crashlytics", "Crashlytics was not started due to an exception during initialization", e2);
            }
        }
    }

    public final String f() {
        return cm.a().f();
    }

    static String g() {
        return k;
    }

    static String h() {
        return l;
    }

    static String i() {
        return o;
    }

    static String j() {
        return n;
    }

    static String k() {
        return m;
    }

    static String l() {
        return com.b.a.a.ba.a(j, "com.crashlytics.ApiEndpoint");
    }

    final boolean m() {
        return ((Boolean) com.b.a.a.ar.a().a(new as(this), false)).booleanValue();
    }

    static boolean n() {
        return com.b.a.a.ba.a().getBoolean("always_send_reports_opt_in", false);
    }

    static void a(boolean z) {
        com.b.a.a.ba.a().edit().putBoolean("always_send_reports_opt_in", true).commit();
    }

    final bc o() {
        return this.d;
    }

    final String p() {
        if (this.e.a()) {
            return this.f;
        }
        return null;
    }

    final String q() {
        if (this.e.a()) {
            return this.g;
        }
        return null;
    }

    final String r() {
        if (this.e.a()) {
            return this.h;
        }
        return null;
    }

    final boolean s() {
        return ((Boolean) com.b.a.a.ar.a().a(new at(this), true)).booleanValue();
    }

    final z t() {
        return (z) com.b.a.a.ar.a().a(new au(this), null);
    }

    final com.b.a.a.aq u() {
        return (com.b.a.a.aq) com.b.a.a.ar.a().a(new av(this), null);
    }

    private synchronized void a(String str, Context context, float f) {
        boolean zF = false;
        synchronized (this) {
            if (j != null) {
                cm.a().b().a("Crashlytics", "Crashlytics already started, ignoring re-initialization attempt.");
            } else {
                p = str;
                j = new ContextWrapper(context.getApplicationContext());
                t = new bu(cm.a().b());
                cm.a().b().b("Crashlytics", "Initializing Crashlytics " + c());
                try {
                    k = j.getPackageName();
                    PackageManager packageManager = j.getPackageManager();
                    l = packageManager.getInstallerPackageName(k);
                    cm.a().b().a("Crashlytics", "Installer package name is: " + l);
                    PackageInfo packageInfo = packageManager.getPackageInfo(k, 0);
                    n = Integer.toString(packageInfo.versionCode);
                    o = packageInfo.versionName == null ? "0.0" : packageInfo.versionName;
                    m = context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
                    q = Integer.toString(context.getApplicationInfo().targetSdkVersion);
                    this.i = com.b.a.a.ba.i(context);
                } catch (Exception e) {
                    cm.a().b().a("Crashlytics", "Error setting up app properties", e);
                }
                this.e = new bn(j);
                this.e.h();
                new al(this.i, com.b.a.a.ba.a((Context) j, "com.crashlytics.RequireBuildId", true)).a(str, k);
                try {
                    cm.a().b().a("Crashlytics", "Installing exception handler...");
                    this.d = new bc(Thread.getDefaultUncaughtExceptionHandler(), this.c, this.i);
                    zF = this.d.f();
                    this.d.d();
                    this.d.c();
                    this.d.h();
                    Thread.setDefaultUncaughtExceptionHandler(this.d);
                    cm.a().b().a("Crashlytics", "Successfully installed exception handler.");
                } catch (Exception e2) {
                    cm.a().b().a("Crashlytics", "There was a problem installing the exception handler.", e2);
                }
                CountDownLatch countDownLatch = new CountDownLatch(1);
                new Thread(new ba(this, context, f, countDownLatch), "Crashlytics Initializer").start();
                if (zF) {
                    cm.a().b().a("Crashlytics", "Crashlytics detected incomplete initialization on previous app launch. Will initialize synchronously.");
                    try {
                        if (!countDownLatch.await(4000L, TimeUnit.MILLISECONDS)) {
                            cm.a().b().c("Crashlytics", "Crashlytics initialization was not completed in the allotted time.");
                        }
                    } catch (InterruptedException e3) {
                        cm.a().b().a("Crashlytics", "Crashlytics was interrupted during initialization.", e3);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(Context context, float f) throws Throwable {
        com.b.a.a.aw awVarB;
        boolean z;
        boolean z2;
        boolean zD;
        boolean zB = true;
        boolean z3 = false;
        String strG = com.b.a.a.ba.g(w());
        try {
            com.b.a.a.ar.a().a(context, t, n, o, l()).c();
            awVarB = com.b.a.a.ar.a().b();
        } catch (Exception e) {
            cm.a().b().a("Crashlytics", "Error dealing with settings", e);
            awVarB = null;
        }
        if (awVarB != null) {
            try {
                com.b.a.a.al alVar = awVarB.f267a;
                if ("new".equals(alVar.f259a)) {
                    if (new x(l(), alVar.b, t).a(a(ac.a(w(), strG)))) {
                        zD = com.b.a.a.ar.a().d();
                    } else {
                        cm.a().b().a("Crashlytics", "Failed to create app with Crashlytics service.", (Throwable) null);
                        zD = false;
                    }
                } else if ("configured".equals(alVar.f259a)) {
                    zD = com.b.a.a.ar.a().d();
                } else {
                    if (alVar.d) {
                        cm.a().b().a("Crashlytics", "Server says an update is required - forcing a full App update.");
                        new ai(l(), alVar.b, t).a(a(ac.a(w(), strG)));
                    }
                    zD = true;
                }
                z = zD;
            } catch (Exception e2) {
                cm.a().b().a("Crashlytics", "Error performing auto configuration.", e2);
                z = false;
            }
            try {
                z2 = awVarB.d.b;
            } catch (Exception e3) {
                cm.a().b().a("Crashlytics", "Error getting collect reports setting.", e3);
                z2 = false;
            }
        } else {
            z2 = false;
            z = false;
        }
        if (z && z2) {
            try {
                zB = this.d.b() & true;
                z zVarT = t();
                if (zVarT != null) {
                    new af(zVarT).a(f);
                }
            } catch (Exception e4) {
                cm.a().b().a("Crashlytics", "Error sending crash report", e4);
            }
        } else {
            z3 = true;
        }
        if (z3) {
            cm.a().b().a("Crashlytics", "Crash reporting disabled.");
        }
        return zB;
    }

    private ak a(ac acVar) {
        return new ak(p, k, o, n, com.b.a.a.ba.a(this.i), m, bh.a(l).a(), q, AppEventsConstants.EVENT_PARAM_VALUE_NO, acVar);
    }
}

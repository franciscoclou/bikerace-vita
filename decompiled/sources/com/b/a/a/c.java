package com.b.a.a;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import java.io.File;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c extends cl {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f290a;
    private String b;
    private String c;
    private bn d;
    private ai e;
    private long f;
    private bu g;
    private n h;

    /* JADX WARN: Code duplicated, block: B:30:0x00fa  */
    static /* synthetic */ void a(c cVar) {
        boolean z;
        Context contextW = cVar.w();
        try {
            j jVar = new j(new w(), new bg(), new bi(cm.a().i(), "session_analytics.tap", "session_analytics_to_send"));
            String strB = cVar.d.b();
            String strG = cVar.d.g();
            String strC = cVar.d.c();
            String strD = cVar.d.d();
            Application applicationC = cm.a().c();
            if (applicationC == null || Build.VERSION.SDK_INT < 14) {
                cVar.h = new n(contextW.getPackageName(), strB, strG, strC, strD, cVar.b, cVar.c, jVar, cVar.g);
            } else {
                cVar.h = new e(applicationC, contextW.getPackageName(), strB, strG, strC, strD, cVar.b, cVar.c, jVar, cVar.g);
            }
            long j = cVar.f;
            if (cVar.e.a().getBoolean("analytics_launched", false)) {
                z = false;
            } else {
                if (System.currentTimeMillis() - j < 3600000) {
                    z = true;
                } else {
                    z = false;
                }
            }
            if (z) {
                cm.a().b().a("Crashlytics", "First launch");
                if (cVar.h != null) {
                    cVar.h.b();
                    cVar.e.a(cVar.e.b().putBoolean("analytics_launched", true));
                }
            }
        } catch (Exception e) {
            ba.d("Crashlytics failed to initialize session analytics.");
        }
        try {
            ar.a().a(contextW, cVar.g, cVar.b, cVar.c, cVar.b()).c();
            aw awVarB = ar.a().b();
            if (awVarB != null) {
                if (awVarB.d.c) {
                    cVar.h.a(awVarB.e, cVar.b());
                } else {
                    ba.c("Disabling analytics collection based on settings flag value.");
                    cVar.h.a();
                }
            }
        } catch (Exception e2) {
            cm.a().b().a("Crashlytics", "Error dealing with settings", e2);
        }
    }

    public static c a() {
        return (c) cm.a().a(c.class);
    }

    @Override // com.b.a.a.ci
    protected final void e() {
        try {
            this.g = new bu(cm.a().b());
            this.e = new ai(cm.a().a(c.class));
            Context contextW = w();
            PackageManager packageManager = contextW.getPackageManager();
            this.d = new bn(contextW);
            this.f290a = contextW.getPackageName();
            PackageInfo packageInfo = packageManager.getPackageInfo(this.f290a, 0);
            this.b = Integer.toString(packageInfo.versionCode);
            this.c = packageInfo.versionName == null ? "0.0" : packageInfo.versionName;
            if (Build.VERSION.SDK_INT >= 9) {
                this.f = packageInfo.firstInstallTime;
            } else {
                this.f = new File(contextW.getPackageManager().getApplicationInfo(contextW.getPackageName(), 0).sourceDir).lastModified();
            }
        } catch (Exception e) {
            cm.a().b().a("Crashlytics", "Error setting up app properties", e);
        }
        new Thread(new d(this), "Crashlytics Initializer").start();
    }

    private String b() {
        return ba.a(w(), "com.crashlytics.ApiEndpoint");
    }

    public final void a(bf bfVar) {
        if (this.h != null) {
            this.h.b(bfVar.a());
        }
    }

    public final void a(be beVar) {
        if (this.h != null) {
            this.h.a(beVar.a());
        }
    }
}

package com.applovin.impl.a;

import android.content.Context;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class u implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final d f213a;
    protected final com.applovin.a.j b;
    protected final Context c;

    u(d dVar) {
        this.f213a = dVar;
        this.c = dVar.h();
        this.b = dVar.f();
    }

    private void c() {
        String str = (String) this.f213a.a(j.J);
        ai aiVar = (ai) this.f213a.d();
        if (str.length() > 0) {
            String[] strArrSplit = str.split(",");
            for (String str2 : strArrSplit) {
                aiVar.a(com.applovin.a.f.a(str2), com.applovin.a.g.f156a);
            }
        }
        if (((Boolean) this.f213a.a(j.K)).booleanValue()) {
            aiVar.a(com.applovin.a.f.c, com.applovin.a.g.b);
        }
    }

    protected boolean a() {
        if (au.a("android.permission.INTERNET", this.c)) {
            return true;
        }
        this.b.e("TaskInitializeSdk", "Unable to enable AppLovin SDK: no android.permission.INTERNET");
        return false;
    }

    protected void b() {
        if (ah.a(j.m, this.f213a)) {
            this.f213a.k().a(new q(this.f213a), w.BACKGROUND, 1500L);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.b.a("TaskInitializeSdk", "Initializing AppLovin SDK 5.3.6-5.3.6...");
        try {
            if (a()) {
                o oVarL = this.f213a.l();
                oVarL.c();
                oVarL.c("ad_imp_session");
                b();
                c();
                if (((String) this.f213a.a(j.P)).equals("unknown")) {
                    this.f213a.g().a(j.P, "true");
                }
                this.f213a.a(true);
            } else {
                this.f213a.a(false);
            }
        } catch (Throwable th) {
            this.b.b("TaskInitializeSdk", "Unable to intialize SDK, disabling the SDK", th);
            this.f213a.a(false);
        } finally {
            this.b.a("TaskInitializeSdk", "AppLovin SDK 5.3.6-5.3.6 initialization " + (this.f213a.b() ? "succeeded" : "failed") + " in " + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms");
        }
    }
}

package com.applovin.impl.a;

import android.content.Context;
import android.os.Build;
import android.util.Log;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class d extends com.applovin.a.k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f199a;
    private com.applovin.a.l b;
    private Context c;
    private com.applovin.a.j d;
    private v e;
    private m f;
    private ar g;
    private o h;
    private ap i;
    private ai j;
    private boolean k = true;
    private boolean l = false;
    private boolean m = false;
    private boolean n = false;
    private boolean o = false;

    private static boolean q() {
        return (Build.VERSION.RELEASE.startsWith("1.") || Build.VERSION.RELEASE.startsWith("2.0") || Build.VERSION.RELEASE.startsWith("2.1")) ? false : true;
    }

    Object a(l lVar) {
        return this.f.a(lVar);
    }

    @Override // com.applovin.a.k
    public String a() {
        return this.f199a;
    }

    @Override // com.applovin.a.k
    protected void a(String str, com.applovin.a.l lVar, Context context) {
        this.f199a = str;
        this.b = lVar;
        this.c = context;
        try {
            an anVar = new an();
            this.d = anVar;
            this.f = new m(this);
            this.e = new v(this);
            this.g = new ar(this);
            this.h = new o(this);
            this.j = new ai(this);
            this.i = new ap(this);
            if (!q()) {
                this.n = true;
                Log.e("AppLovinSdk", "Unable to initalize AppLovin SDK: Android SDK version has to be at least level 8");
            }
            if (str == null || str.length() < 1) {
                this.o = true;
                Log.e("AppLovinSdk", "Unable to find AppLovin SDK key. Please add     meta-data android:name=\"applovin.sdk.key\" android:value=\"YOUR_SDK_KEY_HERE\" into AndroidManifest.xml.");
            }
            if (c()) {
                a(false);
                return;
            }
            anVar.a(this.f);
            if (lVar instanceof f) {
                anVar.a(((f) lVar).a());
            }
            this.f.c();
            if (((Boolean) this.f.a(j.b)).booleanValue()) {
                this.f.a(lVar);
                this.f.a(j.b, false);
                this.f.b();
            }
            o();
        } catch (Throwable th) {
            Log.e("AppLovinSdk", "Failed to load AppLovin SDK, ad serving will be disabled", th);
            a(false);
        }
    }

    void a(boolean z) {
        this.k = false;
        this.l = z;
        this.m = true;
    }

    public boolean b() {
        return this.l;
    }

    @Override // com.applovin.a.k
    public boolean c() {
        return this.n || this.o;
    }

    @Override // com.applovin.a.k
    public com.applovin.a.e d() {
        return this.j;
    }

    public com.applovin.a.n e() {
        return this.i;
    }

    @Override // com.applovin.a.k
    public com.applovin.a.j f() {
        return this.d;
    }

    public m g() {
        return this.f;
    }

    public Context h() {
        return this.c;
    }

    public ar i() {
        return this.g;
    }

    @Override // com.applovin.a.k
    public void j() {
    }

    v k() {
        return this.e;
    }

    o l() {
        return this.h;
    }

    boolean m() {
        return this.k;
    }

    boolean n() {
        return this.m;
    }

    void o() {
        this.k = true;
        this.e.a(new u(this), 0L);
    }

    void p() {
        this.f.d();
        this.f.b();
        this.h.a();
    }
}

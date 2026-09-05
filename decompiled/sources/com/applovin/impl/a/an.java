package com.applovin.impl.a;

import android.util.Log;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class an implements com.applovin.a.j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private m f188a;
    private ao b;

    an() {
    }

    void a(ao aoVar) {
        this.b = aoVar;
    }

    void a(m mVar) {
        this.f188a = mVar;
    }

    @Override // com.applovin.a.j
    public void a(String str, String str2) {
        if (a()) {
            Log.d("AppLovinSdk", "[" + str + "] " + str2);
        }
        if (this.b != null) {
            this.b.a("DEBUG  [" + str + "] " + str2);
        }
    }

    @Override // com.applovin.a.j
    public void a(String str, String str2, Throwable th) {
        if (a()) {
            Log.w("AppLovinSdk", "[" + str + "] " + str2, th);
        }
        if (this.b != null) {
            this.b.a("WARN  [" + str + "] " + str2);
        }
    }

    protected boolean a() {
        if (this.f188a != null) {
            return ((Boolean) this.f188a.a(j.j)).booleanValue();
        }
        return false;
    }

    @Override // com.applovin.a.j
    public void b(String str, String str2) {
        if (a()) {
            Log.i("AppLovinSdk", "[" + str + "] " + str2);
        }
        if (this.b != null) {
            this.b.a("INFO  [" + str + "] " + str2);
        }
    }

    @Override // com.applovin.a.j
    public void b(String str, String str2, Throwable th) {
        if (a()) {
            Log.e("AppLovinSdk", "[" + str + "] " + str2, th);
        }
        if (this.b != null) {
            this.b.a("ERROR  [" + str + "] " + str2 + (th != null ? ": " + th.getMessage() : ""));
        }
    }

    @Override // com.applovin.a.j
    public void c(String str, String str2) {
        a(str, str2, null);
    }

    @Override // com.applovin.a.j
    public void c(String str, String str2, Throwable th) {
        Log.e("AppLovinSdk", "[" + str + "] " + str2, th);
        if (this.b != null) {
            this.b.a("USER  [" + str + "] " + str2 + (th != null ? ": " + th.getMessage() : ""));
        }
    }

    @Override // com.applovin.a.j
    public void d(String str, String str2) {
        b(str, str2, null);
    }

    @Override // com.applovin.a.j
    public void e(String str, String str2) {
        c(str, str2, null);
    }
}

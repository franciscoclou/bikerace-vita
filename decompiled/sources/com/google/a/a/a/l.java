package com.google.a.a.a;

import android.util.Log;

/* JADX INFO: compiled from: DefaultLoggerImpl.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class l implements ai {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private aj f593a = aj.INFO;

    l() {
    }

    @Override // com.google.a.a.a.ai
    public void a(String str) {
        if (this.f593a.ordinal() <= aj.VERBOSE.ordinal()) {
            Log.v("GAV3", e(str));
        }
    }

    @Override // com.google.a.a.a.ai
    public void b(String str) {
        if (this.f593a.ordinal() <= aj.INFO.ordinal()) {
            Log.i("GAV3", e(str));
        }
    }

    @Override // com.google.a.a.a.ai
    public void c(String str) {
        if (this.f593a.ordinal() <= aj.WARNING.ordinal()) {
            Log.w("GAV3", e(str));
        }
    }

    @Override // com.google.a.a.a.ai
    public void d(String str) {
        if (this.f593a.ordinal() <= aj.ERROR.ordinal()) {
            Log.e("GAV3", e(str));
        }
    }

    @Override // com.google.a.a.a.ai
    public void a(aj ajVar) {
        this.f593a = ajVar;
    }

    @Override // com.google.a.a.a.ai
    public aj a() {
        return this.f593a;
    }

    private String e(String str) {
        return Thread.currentThread().toString() + ": " + str;
    }
}

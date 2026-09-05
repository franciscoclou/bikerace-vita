package com.applovin.impl.a;

import android.content.Context;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
abstract class x implements Runnable {
    protected final String c;
    protected final d d;
    protected final com.applovin.a.j e;
    protected final Context f;

    x(String str, d dVar) {
        if (dVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.d = dVar;
        this.c = str == null ? getClass().getSimpleName() : str;
        this.e = dVar.f();
        this.f = dVar.h();
    }

    void a_() {
    }

    String e() {
        return this.c;
    }

    protected au f() {
        return new au(this.d);
    }
}

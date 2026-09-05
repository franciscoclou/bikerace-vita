package com.b.a.a;

import java.util.Collections;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class o implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ String f305a;
    private /* synthetic */ n b;

    o(n nVar, String str) {
        this.b = nVar;
        this.f305a = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.b.f304a.a(u.a(this.b.b, this.b.i, this.b.c, this.b.d, this.b.e, this.b.f, this.b.g, this.b.h, v.CRASH, Collections.singletonMap("sessionId", this.f305a)));
        } catch (Exception e) {
            ba.d("Crashlytics failed to record crash event");
        }
    }
}

package com.google.ads;

import android.os.SystemClock;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private c f648a = null;
    private long b = -1;

    public boolean a() {
        return this.f648a != null && SystemClock.elapsedRealtime() < this.b;
    }

    public void a(c cVar, int i) {
        this.f648a = cVar;
        this.b = TimeUnit.MILLISECONDS.convert(i, TimeUnit.SECONDS) + SystemClock.elapsedRealtime();
    }

    public c b() {
        return this.f648a;
    }
}

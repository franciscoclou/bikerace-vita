package com.google.ads.internal;

import android.content.Context;
import com.google.ads.AdSize;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h f680a = new h(null, true);
    private AdSize b;
    private boolean c;
    private final boolean d;

    private h(AdSize adSize, boolean z) {
        this.b = adSize;
        this.d = z;
    }

    public static h a(AdSize adSize, Context context) {
        return new h(AdSize.createAdSize(adSize, context), false);
    }

    public static h a(AdSize adSize) {
        return a(adSize, null);
    }

    public boolean a() {
        return this.d;
    }

    public boolean b() {
        return this.c;
    }

    public AdSize c() {
        return this.b;
    }

    public void b(AdSize adSize) {
        if (!this.d) {
            this.b = adSize;
        }
    }

    public void a(boolean z) {
        this.c = z;
    }
}

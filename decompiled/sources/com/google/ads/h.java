package com.google.ads;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationInterstitialAdapter;
import java.util.HashMap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final com.google.ads.internal.h f661a;
    private final f b;
    private boolean c;
    private boolean d;
    private g.a e;
    private final e f;
    private MediationAdapter<?, ?> g;
    private boolean h;
    private boolean i;
    private View j;
    private final String k;
    private final AdRequest l;
    private final HashMap<String, String> m;

    public h(e eVar, com.google.ads.internal.h hVar, f fVar, String str, AdRequest adRequest, HashMap<String, String> map) {
        com.google.ads.util.a.b(TextUtils.isEmpty(str));
        this.f = eVar;
        this.f661a = hVar;
        this.b = fVar;
        this.k = str;
        this.l = adRequest;
        this.m = map;
        this.c = false;
        this.d = false;
        this.e = null;
        this.g = null;
        this.h = false;
        this.i = false;
        this.j = null;
    }

    public f a() {
        return this.b;
    }

    public synchronized void a(Activity activity) {
        com.google.ads.util.a.b(this.h, "startLoadAdTask has already been called.");
        this.h = true;
        m.a().c.a().post(new bi(this, activity, this.k, this.l, this.m));
    }

    public synchronized void b() {
        com.google.ads.util.a.a(this.h, "destroy() called but startLoadAdTask has not been called.");
        m.a().c.a().post(new Runnable() { // from class: com.google.ads.h.1
            @Override // java.lang.Runnable
            public void run() {
                if (h.this.l()) {
                    com.google.ads.util.a.b(h.this.g);
                    try {
                        h.this.g.destroy();
                        com.google.ads.util.b.a("Called destroy() for adapter with class: " + h.this.g.getClass().getName());
                    } catch (Throwable th) {
                        com.google.ads.util.b.b("Error while destroying adapter (" + h.this.h() + "):", th);
                    }
                }
            }
        });
    }

    public synchronized boolean c() {
        return this.c;
    }

    public synchronized boolean d() {
        com.google.ads.util.a.a(this.c, "isLoadAdTaskSuccessful() called when isLoadAdTaskDone() is false.");
        return this.d;
    }

    public synchronized g.a e() {
        return this.e == null ? g.a.TIMEOUT : this.e;
    }

    public synchronized View f() {
        com.google.ads.util.a.a(this.c, "getAdView() called when isLoadAdTaskDone() is false.");
        return this.j;
    }

    public synchronized void g() {
        com.google.ads.util.a.a(this.f661a.a());
        try {
            final MediationInterstitialAdapter mediationInterstitialAdapter = (MediationInterstitialAdapter) this.g;
            m.a().c.a().post(new Runnable() { // from class: com.google.ads.h.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        mediationInterstitialAdapter.showInterstitial();
                    } catch (Throwable th) {
                        com.google.ads.util.b.b("Error while telling adapter (" + h.this.h() + ") ad to show interstitial: ", th);
                    }
                }
            });
        } catch (ClassCastException e) {
            com.google.ads.util.b.b("In Ambassador.show(): ambassador.adapter does not implement the MediationInterstitialAdapter interface.", e);
        }
    }

    public synchronized String h() {
        return this.g != null ? this.g.getClass().getName() : "\"adapter was not created.\"";
    }

    synchronized void a(boolean z, g.a aVar) {
        this.d = z;
        this.c = true;
        this.e = aVar;
        notify();
    }

    synchronized void a(MediationAdapter<?, ?> mediationAdapter) {
        this.g = mediationAdapter;
    }

    synchronized MediationAdapter<?, ?> i() {
        return this.g;
    }

    e j() {
        return this.f;
    }

    synchronized void a(View view) {
        this.j = view;
    }

    synchronized void k() {
        this.i = true;
    }

    synchronized boolean l() {
        return this.i;
    }
}

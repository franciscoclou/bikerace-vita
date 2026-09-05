package com.google.ads;

import android.app.Activity;
import android.os.SystemClock;
import android.view.View;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.google.ads.internal.d f650a;
    private h b;
    private final Object c;
    private Thread d;
    private final Object e;
    private boolean f;
    private final Object g;

    public e(com.google.ads.internal.d dVar) {
        this.b = null;
        this.c = new Object();
        this.d = null;
        this.e = new Object();
        this.f = false;
        this.g = new Object();
        com.google.ads.util.a.b(dVar);
        this.f650a = dVar;
    }

    public boolean a() {
        boolean z;
        synchronized (this.e) {
            z = this.d != null;
        }
        return z;
    }

    public void b() {
        synchronized (this.g) {
            this.f = true;
            d(null);
            synchronized (this.e) {
                if (this.d != null) {
                    this.d.interrupt();
                }
            }
        }
    }

    public void a(final c cVar, final AdRequest adRequest) {
        synchronized (this.e) {
            if (a()) {
                com.google.ads.util.b.c("Mediation thread is not done executing previous mediation  request. Ignoring new mediation request");
                return;
            }
            if (cVar.d()) {
                this.f650a.a(cVar.e());
                if (!this.f650a.t()) {
                    this.f650a.g();
                }
            } else if (this.f650a.t()) {
                this.f650a.f();
            }
            a(cVar, this.f650a);
            this.d = new Thread(new Runnable() { // from class: com.google.ads.e.1
                @Override // java.lang.Runnable
                public void run() {
                    e.this.b(cVar, adRequest);
                    synchronized (e.this.e) {
                        e.this.d = null;
                    }
                }
            });
            this.d.start();
        }
    }

    public static boolean a(c cVar, com.google.ads.internal.d dVar) {
        if (cVar.j() == null) {
            return true;
        }
        if (dVar.i().b()) {
            if (cVar.j().a()) {
                return true;
            }
            com.google.ads.util.b.e("InterstitialAd received a mediation response corresponding to a non-interstitial ad. Make sure you specify 'interstitial' as the ad-type in the mediation UI.");
            return false;
        }
        AdSize adSizeC = dVar.i().g.a().c();
        if (cVar.j().a()) {
            com.google.ads.util.b.e("AdView received a mediation response corresponding to an interstitial ad. Make sure you specify the banner ad size corresponding to the AdSize you used in your AdView  (" + adSizeC + ") in the ad-type field in the mediation UI.");
            return false;
        }
        AdSize adSizeC2 = cVar.j().c();
        if (adSizeC2 == adSizeC) {
            return true;
        }
        com.google.ads.util.b.e("Mediation server returned ad size: '" + adSizeC2 + "', while the AdView was created with ad size: '" + adSizeC + "'. Using the ad-size passed to the AdView on creation.");
        return false;
    }

    private boolean a(h hVar, String str) {
        if (e() == hVar) {
            return true;
        }
        com.google.ads.util.b.c("GWController: ignoring callback to " + str + " from non showing ambassador with adapter class: '" + hVar.h() + "'.");
        return false;
    }

    public void a(h hVar, final boolean z) {
        if (a(hVar, "onAdClicked()")) {
            final f fVarA = hVar.a();
            m.a().c.a().post(new Runnable() { // from class: com.google.ads.e.2
                @Override // java.lang.Runnable
                public void run() {
                    e.this.f650a.a(fVarA, z);
                }
            });
        }
    }

    public void a(h hVar, final View view) {
        if (e() != hVar) {
            com.google.ads.util.b.c("GWController: ignoring onAdRefreshed() callback from non-showing ambassador (adapter class name is '" + hVar.h() + "').");
            return;
        }
        this.f650a.n().a(g.a.AD);
        final f fVarA = this.b.a();
        m.a().c.a().post(new Runnable() { // from class: com.google.ads.e.3
            @Override // java.lang.Runnable
            public void run() {
                e.this.f650a.a(view, e.this.b, fVarA, true);
            }
        });
    }

    public void a(h hVar) {
        if (a(hVar, "onPresentScreen")) {
            m.a().c.a().post(new Runnable() { // from class: com.google.ads.e.4
                @Override // java.lang.Runnable
                public void run() {
                    e.this.f650a.v();
                }
            });
        }
    }

    public void b(h hVar) {
        if (a(hVar, "onDismissScreen")) {
            m.a().c.a().post(new Runnable() { // from class: com.google.ads.e.5
                @Override // java.lang.Runnable
                public void run() {
                    e.this.f650a.u();
                }
            });
        }
    }

    public void c(h hVar) {
        if (a(hVar, "onLeaveApplication")) {
            m.a().c.a().post(new Runnable() { // from class: com.google.ads.e.6
                @Override // java.lang.Runnable
                public void run() {
                    e.this.f650a.w();
                }
            });
        }
    }

    public boolean c() {
        com.google.ads.util.a.a(this.f650a.i().b());
        h hVarE = e();
        if (hVarE != null) {
            hVarE.g();
            return true;
        }
        com.google.ads.util.b.b("There is no ad ready to show.");
        return false;
    }

    protected e() {
        this.b = null;
        this.c = new Object();
        this.d = null;
        this.e = new Object();
        this.f = false;
        this.g = new Object();
        this.f650a = null;
    }

    private boolean d() {
        boolean z;
        synchronized (this.g) {
            z = this.f;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final c cVar, AdRequest adRequest) {
        synchronized (this.e) {
            com.google.ads.util.a.a(Thread.currentThread(), this.d);
        }
        List<a> listF = cVar.f();
        long jB = cVar.a() ? cVar.b() : 10000L;
        for (a aVar : listF) {
            com.google.ads.util.b.a("Looking to fetch ads from network: " + aVar.b());
            List<String> listC = aVar.c();
            HashMap<String, String> mapE = aVar.e();
            List<String> listD = aVar.d();
            String strA = aVar.a();
            String strB = aVar.b();
            String strC = cVar.c();
            if (listD == null) {
                listD = cVar.g();
            }
            f fVar = new f(strA, strB, strC, listD, cVar.h(), cVar.i());
            for (String str : listC) {
                Activity activityA = this.f650a.i().c.a();
                if (activityA == null) {
                    com.google.ads.util.b.a("Activity is null while mediating.  Terminating mediation thread.");
                    return;
                }
                this.f650a.n().c();
                if (!a(str, activityA, adRequest, fVar, mapE, jB)) {
                    if (d()) {
                        com.google.ads.util.b.a("GWController.destroy() called. Terminating mediation thread.");
                        return;
                    }
                } else {
                    return;
                }
            }
        }
        m.a().c.a().post(new Runnable() { // from class: com.google.ads.e.7
            @Override // java.lang.Runnable
            public void run() {
                e.this.f650a.b(cVar);
            }
        });
    }

    private boolean a(String str, Activity activity, AdRequest adRequest, final f fVar, HashMap<String, String> map, long j) {
        final h hVar = new h(this, this.f650a.i().g.a(), fVar, str, adRequest, map);
        synchronized (hVar) {
            hVar.a(activity);
            while (!hVar.c() && j > 0) {
                try {
                    long jElapsedRealtime = SystemClock.elapsedRealtime();
                    hVar.wait(j);
                    j -= SystemClock.elapsedRealtime() - jElapsedRealtime;
                } catch (InterruptedException e) {
                    com.google.ads.util.b.a("Interrupted while waiting for ad network to load ad using adapter class: " + str);
                }
            }
            this.f650a.n().a(hVar.e());
            if (hVar.c() && hVar.d()) {
                final View viewF = this.f650a.i().b() ? null : hVar.f();
                m.a().c.a().post(new Runnable() { // from class: com.google.ads.e.8
                    @Override // java.lang.Runnable
                    public void run() {
                        if (!e.this.e(hVar)) {
                            e.this.f650a.a(viewF, hVar, fVar, false);
                        } else {
                            com.google.ads.util.b.a("Trying to switch GWAdNetworkAmbassadors, but GWController().destroy() has been called. Destroying the new ambassador and terminating mediation.");
                        }
                    }
                });
                return true;
            }
            if (!hVar.c()) {
                com.google.ads.util.b.a("Timeout occurred in adapter class: " + hVar.h());
            }
            hVar.b();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(h hVar) {
        boolean z;
        synchronized (this.g) {
            if (d()) {
                hVar.b();
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    private h e() {
        h hVar;
        synchronized (this.c) {
            hVar = this.b;
        }
        return hVar;
    }

    public void d(h hVar) {
        synchronized (this.c) {
            if (this.b != hVar) {
                if (this.b != null) {
                    this.b.b();
                }
                this.b = hVar;
            }
        }
    }
}

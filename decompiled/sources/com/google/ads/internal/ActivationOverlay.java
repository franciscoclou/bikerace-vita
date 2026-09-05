package com.google.ads.internal;

import com.google.ads.util.AdUtil;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ActivationOverlay extends AdWebView {
    private volatile boolean b;
    private boolean c;
    private int d;
    private int e;
    private final i f;

    public ActivationOverlay(com.google.ads.n nVar) {
        super(nVar, null);
        this.b = true;
        this.c = true;
        this.d = 0;
        this.e = 0;
        if (AdUtil.f712a < nVar.d.a().b.a().c.a().intValue()) {
            com.google.ads.util.b.a("Disabling hardware acceleration for an activation overlay.");
            g();
        }
        this.f = i.a(nVar.b.a(), a.c, true, true);
        setWebViewClient(this.f);
    }

    public void setOverlayEnabled(boolean z) {
        if (!z) {
            com.google.ads.m.a().c.a().post(new Runnable() { // from class: com.google.ads.internal.ActivationOverlay.1
                @Override // java.lang.Runnable
                public void run() {
                    ActivationOverlay.this.f666a.j.a().removeView(this);
                }
            });
        }
        this.b = z;
    }

    public void setOverlayActivated(boolean z) {
        this.c = z;
    }

    public boolean a() {
        return this.b;
    }

    public boolean b() {
        return this.c;
    }

    public int c() {
        return this.e;
    }

    public void setYPosition(int i) {
        this.e = i;
    }

    public int d() {
        return this.d;
    }

    public void setXPosition(int i) {
        this.d = i;
    }

    public i e() {
        return this.f;
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i) {
        return false;
    }

    @Override // android.view.View
    public boolean canScrollVertically(int i) {
        return false;
    }
}

package com.chartboost.sdk;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.chartboost.sdk.impl.p;
import com.chartboost.sdk.impl.t;
import com.chartboost.sdk.impl.u;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Chartboost f397a;
    private WeakReference<Activity> b;
    private t c;
    private boolean d = false;
    private boolean e = false;
    private boolean f = false;
    private u g;
    private u h;

    protected b(Chartboost chartboost, Activity activity) {
        this.f397a = chartboost;
        this.b = new WeakReference<>(activity);
    }

    protected void a(Activity activity) {
        this.b = new WeakReference<>(activity);
    }

    protected void a(a aVar) {
        if (aVar.f399a) {
            d();
        } else if (aVar.b != null) {
            b(aVar.b);
        }
    }

    private void b(com.chartboost.sdk.impl.a aVar) {
        if (!this.e) {
            aVar.c = com.chartboost.sdk.impl.a.b.CBImpressionStateWaitingForDisplay;
            Activity activity = this.b.get();
            if (!aVar.f.b() || activity == null) {
                if (aVar.f.d != null) {
                    aVar.f.d.a();
                    return;
                }
                return;
            }
            if (aVar.i) {
                aVar.i = false;
                this.g = new u(activity, aVar.f.d());
                activity.addContentView(this.g, new FrameLayout.LayoutParams(-1, -1));
                aVar.c = com.chartboost.sdk.impl.a.b.CBImpressionStateDisplayedByDefaultController;
                aVar.h = this.g;
                this.e = true;
                return;
            }
            this.g = new u(activity, aVar.f.d());
            activity.addContentView(this.g, new FrameLayout.LayoutParams(-1, -1));
            this.g.b().a();
            p.b bVar = p.b.CBAnimationTypePerspectiveRotate;
            if (aVar.d == com.chartboost.sdk.impl.a.c.CBImpressionTypeMoreApps) {
                bVar = p.b.CBAnimationTypePerspectiveZoom;
            }
            p.b bVarA = p.b.a(aVar.f406a.optInt("animation"));
            if (bVarA != null) {
                bVar = bVarA;
            }
            if (this.f397a.getAnimationsOff()) {
                bVar = p.b.CBAnimationTypeNone;
            }
            aVar.c = com.chartboost.sdk.impl.a.b.CBImpressionStateDisplayedByDefaultController;
            aVar.h = this.g;
            p.a(bVar, aVar);
            this.e = true;
            ChartboostDelegate delegate = this.f397a.getDelegate();
            if (delegate != null) {
                if (aVar.d == com.chartboost.sdk.impl.a.c.CBImpressionTypeInterstitial) {
                    delegate.didShowInterstitial(aVar.e);
                } else if (aVar.d == com.chartboost.sdk.impl.a.c.CBImpressionTypeMoreApps) {
                    delegate.didShowMoreApps();
                }
            }
        }
    }

    public void a(com.chartboost.sdk.impl.a aVar, boolean z) {
        this.e = false;
        if (!z) {
            this.f = true;
        }
        aVar.c = com.chartboost.sdk.impl.a.b.CBImpressionStateWaitingForDismissal;
        p.b bVar = p.b.CBAnimationTypePerspectiveRotate;
        if (aVar.d == com.chartboost.sdk.impl.a.c.CBImpressionTypeMoreApps) {
            bVar = p.b.CBAnimationTypePerspectiveZoom;
        }
        p.b bVarA = p.b.a(aVar.f406a.optInt("animation"));
        if (bVarA != null) {
            bVar = bVarA;
        }
        if (this.f397a.getAnimationsOff()) {
            bVar = p.b.CBAnimationTypeNone;
        }
        p.b(bVar, aVar, c(aVar));
    }

    public void a(com.chartboost.sdk.impl.a aVar) {
        this.e = false;
        new RunnableC0004b(aVar, true).run();
        if (aVar.c == com.chartboost.sdk.impl.a.b.CBImpressionStateDisplayedByDefaultController) {
            aVar.c = com.chartboost.sdk.impl.a.b.CBImpressionStateWaitingForDisplay;
        } else {
            aVar.c = com.chartboost.sdk.impl.a.b.CBImpressionStateOther;
        }
        aVar.c();
        try {
            ((ViewGroup) this.g.getParent()).removeView(this.g);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.g = null;
    }

    private void d() {
        Activity activity = this.b.get();
        if (activity != null) {
            this.c = new t(activity);
            this.h = new u(activity, this.c);
            this.h.b().a(true);
            activity.addContentView(this.h, new FrameLayout.LayoutParams(-1, -1));
            this.h.b().a();
            this.h.b().a((View) this.c.getParent());
            this.d = true;
        }
    }

    public void a(boolean z) {
        if (a()) {
            try {
                ((ViewGroup) this.h.getParent()).removeView(this.h);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.c = null;
            this.h = null;
            this.d = false;
            Activity activity = this.b.get();
            if (!this.f && z && this.f397a.getImpressionsUseActivities() && !this.e && activity != null && (activity instanceof CBImpressionActivity)) {
                this.f397a.d();
                activity.finish();
            }
        }
    }

    public void b(com.chartboost.sdk.impl.a aVar, boolean z) {
        Activity activity;
        if (this.g != null) {
            try {
                ((ViewGroup) this.g.getParent()).removeView(this.g);
            } catch (Exception e) {
                e.printStackTrace();
            }
            aVar.b();
            this.g = null;
            this.f = false;
            if (this.f397a.getImpressionsUseActivities() && !z && !this.d && (activity = this.b.get()) != null && (activity instanceof CBImpressionActivity)) {
                activity.finish();
            }
        }
    }

    /* JADX INFO: renamed from: com.chartboost.sdk.b$b, reason: collision with other inner class name */
    class RunnableC0004b implements Runnable {
        private com.chartboost.sdk.impl.a b;
        private boolean c;

        public RunnableC0004b(com.chartboost.sdk.impl.a aVar, boolean z) {
            this.b = aVar;
            this.c = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.b.c == com.chartboost.sdk.impl.a.b.CBImpressionStateWaitingForDismissal) {
                this.b.c = com.chartboost.sdk.impl.a.b.CBImpressionStateOther;
                b.this.b(this.b, this.c);
            }
        }
    }

    private p.a c(com.chartboost.sdk.impl.a aVar) {
        return new p.a() { // from class: com.chartboost.sdk.b.1
            @Override // com.chartboost.sdk.impl.p.a
            public void a(com.chartboost.sdk.impl.a aVar2) {
                b.this.f397a.c.post(b.this.new RunnableC0004b(aVar2, false));
            }
        };
    }

    protected boolean a() {
        return this.d;
    }

    protected boolean b() {
        return this.e;
    }

    protected boolean c() {
        return a() || b();
    }

    public final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected boolean f399a;
        protected com.chartboost.sdk.impl.a b;

        protected a(boolean z, com.chartboost.sdk.impl.a aVar) {
            this.f399a = z;
            this.b = aVar;
        }

        public a(com.chartboost.sdk.impl.a aVar) {
            this.f399a = false;
            this.b = aVar;
        }
    }
}

package com.applovin.impl.adview;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewDatabase;
import android.widget.RelativeLayout;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a implements com.applovin.adview.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Activity f219a;
    private com.applovin.a.k b;
    private com.applovin.a.e c;
    private com.applovin.a.j d;
    private com.applovin.a.f e;
    private ab f;
    private v g;
    private y h;
    private com.applovin.a.a i;
    private Runnable j;
    private Runnable k;
    private Runnable l;
    private volatile com.applovin.a.a m = null;
    private final AtomicReference n = new AtomicReference();
    private volatile boolean o = false;
    private volatile boolean p = true;
    private volatile boolean q = false;
    private volatile boolean r = false;
    private volatile com.applovin.a.d s;
    private volatile com.applovin.a.c t;
    private volatile com.applovin.a.i u;
    private volatile com.applovin.a.b v;

    private void a(ViewGroup viewGroup, com.applovin.a.k kVar, com.applovin.a.f fVar, String str, Context context) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("No parent view specified");
        }
        if (kVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        if (fVar == null) {
            throw new IllegalArgumentException("No ad size specified");
        }
        if (!(context instanceof Activity)) {
            throw new IllegalArgumentException("Specified context is not an activity");
        }
        this.b = kVar;
        this.c = kVar.d();
        this.d = kVar.f();
        this.e = fVar;
        this.f219a = (Activity) context;
        this.i = com.applovin.impl.a.ah.a();
        this.f = new ab(this, kVar);
        this.l = new p(this);
        this.j = new u(this);
        this.k = new s(this);
        this.g = new v(this, kVar);
        if (!a(context)) {
            this.d.e("AppLovinAdView", "Web view database is corrupt, AdView not loaded");
            return;
        }
        this.h = g();
        viewGroup.setBackgroundColor(0);
        viewGroup.addView(this.h);
        b(this.h, fVar);
        this.h.setVisibility(8);
        a(new t(this));
        this.o = true;
    }

    private void a(Runnable runnable) {
        this.f219a.runOnUiThread(runnable);
    }

    private static boolean a(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 11) {
                return true;
            }
            WebViewDatabase webViewDatabase = WebViewDatabase.getInstance(context);
            Method declaredMethod = WebViewDatabase.class.getDeclaredMethod("getCacheTotalSize", new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(webViewDatabase, new Object[0]);
            return true;
        } catch (IllegalAccessException e) {
            Log.e("AppLovinAdView", "Error invoking getCacheTotalSize()", e);
            return true;
        } catch (IllegalArgumentException e2) {
            Log.e("AppLovinAdView", "Error invoking getCacheTotalSize()", e2);
            return true;
        } catch (NoSuchMethodException e3) {
            Log.e("AppLovinAdView", "Error invoking getCacheTotalSize()", e3);
            return true;
        } catch (InvocationTargetException e4) {
            Log.e("AppLovinAdView", "getCacheTotalSize() reported exception", e4);
            return false;
        } catch (Throwable th) {
            Log.e("AppLovinAdView", "Unexpected error while checking DB state", th);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(View view, com.applovin.a.f fVar) {
        int iApplyDimension;
        int iApplyDimension2;
        DisplayMetrics displayMetrics = view.getResources().getDisplayMetrics();
        if (fVar.c().equals(com.applovin.a.f.c.c())) {
            iApplyDimension = -1;
        } else {
            iApplyDimension = fVar.a() == -1 ? displayMetrics.widthPixels : (int) TypedValue.applyDimension(1, fVar.a(), displayMetrics);
        }
        if (fVar.c().equals(com.applovin.a.f.c.c())) {
            iApplyDimension2 = -1;
        } else {
            iApplyDimension2 = fVar.b() == -1 ? displayMetrics.heightPixels : (int) TypedValue.applyDimension(1, fVar.b(), displayMetrics);
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        }
        layoutParams.width = iApplyDimension;
        layoutParams.height = iApplyDimension2;
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.addRule(10);
            layoutParams2.addRule(9);
        }
        view.setLayoutParams(layoutParams);
    }

    private y g() {
        y yVar = new y(this.f, this.b, this.f219a);
        yVar.setBackgroundColor(0);
        yVar.setWillNotCacheDrawing(false);
        return yVar;
    }

    @Override // com.applovin.adview.a
    public void a() {
        if (this.b == null || this.g == null || this.f219a == null || !this.o) {
            Log.i("AppLovinSdk", "Unable to load next ad: AppLovinAdView is not initialized.");
        } else {
            this.c.a(this.e, this.g);
        }
    }

    @Override // com.applovin.adview.a
    public void a(int i) {
        if (this.o && this.p) {
            if (i == 8 || i == 4) {
                e();
            } else if (i == 0) {
                f();
            }
        }
    }

    @Override // com.applovin.adview.a
    public void a(ViewGroup viewGroup, Context context, com.applovin.a.f fVar, com.applovin.a.k kVar, AttributeSet attributeSet) {
        com.applovin.a.f fVarA;
        if (viewGroup == null) {
            throw new IllegalArgumentException("No parent view specified");
        }
        if (context == null) {
            Log.e("AppLovinSdk", "Unable to create AppLovinAdView: no context provided. Please use a different constructor for this view.");
            return;
        }
        if (fVar == null) {
            fVarA = w.a(attributeSet);
            if (fVarA == null) {
                fVarA = com.applovin.a.f.f155a;
            }
        } else {
            fVarA = fVar;
        }
        String strA = w.a(attributeSet, context);
        com.applovin.a.k kVarB = kVar == null ? com.applovin.a.k.b(context) : kVar;
        if (kVarB == null || kVarB.c()) {
            return;
        }
        a(viewGroup, kVarB, fVarA, strA, context);
        if (w.b(attributeSet)) {
            a();
        }
    }

    public void a(WebView webView) {
        if (this.m != null) {
            webView.setVisibility(0);
            try {
                if (this.t != null) {
                    this.t.b(this.m);
                }
            } catch (Throwable th) {
                this.d.c("AppLovinAdView", "Exception while notifying ad display listener", th);
            }
        }
    }

    @Override // com.applovin.adview.a
    public void a(com.applovin.a.a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("No ad specified");
        }
        if (!this.o) {
            Log.i("AppLovinSdk", "Unable to render ad: AppLovinAdView is not initialized.");
            return;
        }
        if (aVar == this.m) {
            this.d.c("AppLovinAdView", "Ad for \"" + aVar.i() + "\" is already showing, ignoring");
            return;
        }
        this.d.a("AppLovinAdView", "Rendering " + aVar.g() + " ad for \"" + aVar.i() + "\"...");
        a(new r(this, this.m));
        this.n.set(null);
        this.m = aVar;
        if (aVar.g() == this.e) {
            a(this.j);
        } else if (aVar.g() == com.applovin.a.f.c) {
            a(this.l);
            a(this.k);
        }
        new com.applovin.impl.a.p(this.b).a();
    }

    @Override // com.applovin.adview.a
    public void a(com.applovin.a.b bVar) {
        this.v = bVar;
    }

    @Override // com.applovin.adview.a
    public void a(com.applovin.a.c cVar) {
        this.t = cVar;
    }

    @Override // com.applovin.adview.a
    public void a(com.applovin.a.d dVar) {
        this.s = dVar;
    }

    @Override // com.applovin.adview.a
    public void a(com.applovin.a.i iVar) {
        this.u = iVar;
    }

    @Override // com.applovin.adview.a
    public void a(boolean z) {
        this.p = z;
    }

    @Override // com.applovin.adview.a
    public void b() {
        if (this.c != null) {
            this.c.a(this.g, c());
        }
        if (this.h != null) {
            this.h.destroy();
        }
        this.q = true;
    }

    protected void b(int i) {
        if (!this.q) {
            this.c.b(this.g, this.e);
            a(this.l);
        }
        a(new l(this, i));
    }

    void b(WebView webView) {
        a(this.l);
        a(new r(this, this.m));
        this.m = null;
    }

    void b(com.applovin.a.a aVar) {
        new com.applovin.impl.a.z(this.c).a(aVar);
        a(new q(this, aVar));
    }

    @Override // com.applovin.adview.a
    public com.applovin.a.f c() {
        return this.e;
    }

    protected void c(com.applovin.a.a aVar) {
        if (aVar == null) {
            this.d.d("AppLovinAdView", "No provided when to the view controller");
            b(-1);
            return;
        }
        this.r = true;
        if (this.q) {
            this.n.set(aVar);
            this.d.a("AppLovinAdView", "Ad view has paused when an ad was recieved, ad saved for later");
        } else {
            this.c.b(this.g, this.e);
            a(aVar);
        }
        a(new c(this, aVar));
    }

    @Override // com.applovin.adview.a
    public void d() {
        if (this.o) {
            a(new r(this, this.m));
            if (this.p) {
                b();
            }
        }
    }

    public void e() {
        if (this.o) {
            this.c.a(this.g, c());
            com.applovin.a.a aVar = this.m;
            a(this.i);
            if (aVar != null) {
                this.n.set(aVar);
            }
            this.q = true;
        }
    }

    public void f() {
        if (this.o) {
            if (this.r) {
                this.c.b(this.g, this.e);
            }
            com.applovin.a.a aVar = (com.applovin.a.a) this.n.getAndSet(null);
            if (aVar != null) {
                a(aVar);
            }
            this.q = false;
        }
    }
}

package com.applovin.impl.adview;

import android.app.Activity;
import android.content.Intent;
import com.applovin.adview.AppLovinInterstitialActivity;
import com.applovin.impl.a.aq;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class e implements com.applovin.adview.b {
    private static volatile boolean l;
    private final String d;
    private final com.applovin.a.k e;
    private final Activity f;
    private volatile com.applovin.a.d g;
    private volatile com.applovin.a.c h;
    private volatile com.applovin.a.i i;
    private volatile com.applovin.a.b j;
    private volatile com.applovin.a.a k;
    private volatile com.applovin.impl.a.b m;
    private volatile ae n;
    private static final Map c = Collections.synchronizedMap(new HashMap());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile boolean f231a = false;
    public static volatile boolean b = false;

    e(com.applovin.a.k kVar, Activity activity) {
        if (kVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        }
        this.e = kVar;
        this.d = UUID.randomUUID().toString();
        f231a = true;
        b = false;
        this.f = activity;
        c.put(this.d, this);
    }

    public static e a(String str) {
        return (e) c.get(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        this.f.runOnUiThread(new i(this, i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(com.applovin.a.a aVar) {
        this.f.runOnUiThread(new h(this, aVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        af afVar = new af(this.e, this.f);
        afVar.a(this);
        this.n = afVar;
        afVar.a(this.k);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        Intent intent = new Intent(this.f, (Class<?>) AppLovinInterstitialActivity.class);
        intent.putExtra("com.applovin.interstitial.wrapper_id", this.d);
        AppLovinInterstitialActivity.f160a = this;
        this.f.startActivity(intent);
        a(true);
    }

    @Override // com.applovin.adview.b
    public void a() {
        this.e.d().a(com.applovin.a.f.c, new f(this));
    }

    @Override // com.applovin.adview.b
    public void a(com.applovin.a.a aVar) {
        if (f()) {
            this.e.f().e("AppLovinInterstitialAdDialog", "Attempted to show an interstitial while one is already displayed; ignoring.");
            return;
        }
        this.k = aVar;
        this.m = aVar instanceof com.applovin.impl.a.a ? ((com.applovin.impl.a.a) aVar).a() : com.applovin.impl.a.b.DEFAULT;
        if (!com.applovin.impl.a.ah.d(aVar.j()) || com.applovin.impl.a.ah.a(aVar.j(), this.f)) {
            this.f.runOnUiThread(new g(this, aq.a(AppLovinInterstitialActivity.class, this.f), this.m == com.applovin.impl.a.b.ACTIVITY_LANDSCAPE || this.m == com.applovin.impl.a.b.ACTIVITY_PORTRAIT));
        }
    }

    @Override // com.applovin.adview.b
    public void a(com.applovin.a.b bVar) {
        this.j = bVar;
    }

    @Override // com.applovin.adview.b
    public void a(com.applovin.a.c cVar) {
        this.h = cVar;
    }

    @Override // com.applovin.adview.b
    public void a(com.applovin.a.i iVar) {
        this.i = iVar;
    }

    public void a(ae aeVar) {
        this.n = aeVar;
    }

    public void a(boolean z) {
        l = z;
    }

    public com.applovin.a.k b() {
        return this.e;
    }

    public com.applovin.a.a c() {
        return this.k;
    }

    public com.applovin.a.i d() {
        return this.i;
    }

    public com.applovin.a.c e() {
        return this.h;
    }

    public boolean f() {
        return l;
    }

    public com.applovin.a.b g() {
        return this.j;
    }

    public com.applovin.impl.a.b h() {
        return this.m;
    }

    public void i() {
        f231a = false;
        b = true;
        c.remove(this.d);
    }

    public boolean j() {
        return new com.applovin.impl.a.n(this.e).a();
    }
}

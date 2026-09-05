package com.c.a.a;

import android.app.Activity;
import android.util.Log;
import com.applovin.a.d;
import com.applovin.a.f;
import com.applovin.a.k;
import com.applovin.adview.AppLovinInterstitialAd;
import com.c.a.g;
import com.c.a.h;

/* JADX INFO: compiled from: AppLovinInterstitialProvider.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a implements h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private k f355a;
    private com.applovin.a.a b;
    private com.applovin.adview.b c;
    private g d;
    private Activity e;
    private Activity f;

    @Override // com.c.a.h
    public void a(final String str) {
        try {
            Log.d("AppLovin", "show");
            if (this.b != null) {
                this.c = AppLovinInterstitialAd.a(this.f355a, this.e);
                this.c.a(new com.applovin.a.b() { // from class: com.c.a.a.a.1
                    @Override // com.applovin.a.b
                    public void a(com.applovin.a.a aVar) {
                        Log.d("AppLovin", "adClicked");
                        if (a.this.d != null) {
                            a.this.d.d(str);
                        }
                    }
                });
                this.c.a(new com.applovin.a.c() { // from class: com.c.a.a.a.2
                    @Override // com.applovin.a.c
                    public void a(com.applovin.a.a aVar) {
                        Log.d("AppLovin", "adHidden");
                        if (a.this.d != null) {
                            a.this.d.c(str);
                        }
                    }

                    @Override // com.applovin.a.c
                    public void b(com.applovin.a.a aVar) {
                        Log.d("AppLovin", "adDisplayed");
                        if (a.this.d != null) {
                            a.this.d.a(str);
                        }
                    }
                });
                this.c.a(this.b);
                this.b = null;
            }
        } catch (Exception e) {
            Log.d("AppLovin", "show", e);
        }
    }

    @Override // com.c.a.h
    public void b(String str) {
        try {
            Log.d("AppLovin", "fetch");
            this.f355a.d().a(f.c, new d() { // from class: com.c.a.a.a.3
                @Override // com.applovin.a.d
                public void a(int i) {
                    a.this.b = null;
                    if (i == 202) {
                        Log.d("AppLovin", "No-fill");
                    } else {
                        Log.d("AppLovin", "Error loading");
                    }
                }

                @Override // com.applovin.a.d
                public void a(com.applovin.a.a aVar) {
                    a.this.b = aVar;
                    Log.d("AppLovin", "Loaded");
                }
            });
        } catch (Exception e) {
            Log.d("AppLovin", "fetch", e);
        }
    }

    @Override // com.c.a.h
    public boolean c(String str) {
        Log.d("AppLovin", "isAvailable: " + (this.b != null));
        return this.b != null;
    }

    @Override // com.c.a.h
    public void a(g gVar) {
        this.d = gVar;
    }

    @Override // com.c.a.h
    public String a() {
        return "AppLovin";
    }

    @Override // com.c.a.h
    public void a(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity cannot be null!");
        }
        try {
            Log.d("AppLovin", "onActivityCreate (" + activity.getClass().getSimpleName() + ")");
            k.a(activity);
            this.f355a = k.b(activity);
            this.f = this.e;
            this.e = activity;
        } catch (Exception e) {
            Log.d("AppLovin", "onActivityCreate", e);
        }
    }

    @Override // com.c.a.h
    public void b() {
    }

    @Override // com.c.a.h
    public void c() {
    }

    @Override // com.c.a.h
    public void d() {
        try {
            Activity activity = this.f == null ? this.e : this.f;
            Log.d("AppLovin", "onActivityDestroy (" + activity.getClass().getSimpleName() + ")");
            if (activity != null) {
                this.f = null;
            }
        } catch (Exception e) {
            Log.d("AppLovin", "onActivityDestroy", e);
        }
    }

    @Override // com.c.a.h
    public boolean e() {
        return true;
    }
}

package com.c.a.a;

import android.app.Activity;
import com.c.a.g;
import com.c.a.h;
import com.heyzap.sdk.ads.i;
import com.heyzap.sdk.ads.k;
import com.heyzap.sdk.ads.p;

/* JADX INFO: compiled from: HeyZapInterstitialProvider.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c implements h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private g f361a;
    private Activity b;
    private k c = new k() { // from class: com.c.a.a.c.1
        @Override // com.heyzap.sdk.ads.k
        public void a(String str) {
            if (c.this.f361a != null) {
                c.this.f361a.a(str);
            }
        }

        @Override // com.heyzap.sdk.ads.k
        public void c(String str) {
            if (c.this.f361a != null) {
                c.this.f361a.c(str);
            }
        }

        @Override // com.heyzap.sdk.ads.k
        public void d(String str) {
            if (c.this.f361a != null) {
                c.this.f361a.b(str);
            }
        }

        @Override // com.heyzap.sdk.ads.k
        public void f(String str) {
        }

        @Override // com.heyzap.sdk.ads.k
        public void b(String str) {
            if (c.this.f361a != null) {
                c.this.f361a.d(str);
            }
        }

        @Override // com.heyzap.sdk.ads.k
        public void e(String str) {
        }

        @Override // com.heyzap.sdk.ads.k
        public void b() {
        }

        @Override // com.heyzap.sdk.ads.k
        public void a() {
        }
    };

    @Override // com.c.a.h
    public void a(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity cannot be null!");
        }
        i.a(activity, 1, this.c);
        this.b = activity;
    }

    @Override // com.c.a.h
    public void a(String str) {
        if (this.b != null && !this.b.isFinishing()) {
            p.a(this.b, str);
        }
    }

    @Override // com.c.a.h
    public void b(String str) {
        p.a(str);
    }

    @Override // com.c.a.h
    public boolean c(String str) {
        return p.b(str).booleanValue();
    }

    @Override // com.c.a.h
    public void a(g gVar) {
        this.f361a = gVar;
    }

    @Override // com.c.a.h
    public String a() {
        return "HeyZap";
    }

    @Override // com.c.a.h
    public void b() {
    }

    @Override // com.c.a.h
    public void c() {
    }

    @Override // com.c.a.h
    public void d() {
    }

    @Override // com.c.a.h
    public boolean e() {
        return true;
    }
}

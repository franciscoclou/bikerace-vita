package com.topfreegames.bikerace.billing.google;

import android.os.Handler;
import android.util.Log;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: GoogleBillingManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class j implements com.topfreegames.bikerace.billing.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static BillingService f1150a = null;
    static int b = 0;
    private com.topfreegames.bikerace.billing.a c;
    private com.topfreegames.bikerace.billing.c d;
    private k e;
    private final String f = "BillingManager";
    private boolean g = false;

    public j(com.topfreegames.bikerace.billing.a aVar, List<String> list) {
        this.d = null;
        this.e = null;
        this.c = aVar;
        this.d = new com.topfreegames.bikerace.billing.c(this.c.getApplicationContext(), list);
        this.e = new k(this, this.c, new Handler());
        b++;
        if (f1150a == null) {
            f1150a = new BillingService();
            f1150a.a(this.c.getApplicationContext());
        }
        m.a(this.e, this.d);
        if (!this.d.b()) {
            a();
        }
    }

    @Override // com.topfreegames.bikerace.billing.b
    public void a() {
        f1150a.b();
    }

    @Override // com.topfreegames.bikerace.billing.b
    public boolean b() {
        try {
            return f1150a.a();
        } catch (Exception e) {
            Log.e("BillingManager", "Exception while checking billing support: " + e.toString());
            return false;
        }
    }

    @Override // com.topfreegames.bikerace.billing.b
    public boolean a(String str) {
        boolean zA;
        Exception e;
        try {
            zA = f1150a.a(str, (String) null);
            if (!zA) {
                try {
                    this.c.c(str);
                } catch (Exception e2) {
                    e = e2;
                    Log.e("BillingManager", "Exception while requesting purchase: " + e.toString());
                    this.c.c(str);
                }
            }
        } catch (Exception e3) {
            zA = false;
            e = e3;
        }
        return zA;
    }

    @Override // com.topfreegames.bikerace.billing.b
    public Map<String, Integer> c() {
        return this.d.a();
    }

    @Override // com.topfreegames.bikerace.billing.b
    public void d() {
        if (!this.g) {
            b--;
            if (b <= 0 && f1150a != null) {
                f1150a.c();
                f1150a = null;
            }
            m.b(this.e, this.d);
            this.e = null;
            this.d = null;
            this.g = true;
        }
    }
}

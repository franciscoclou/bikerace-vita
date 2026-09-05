package com.topfreegames.bikerace.billing.a;

import android.content.SharedPreferences;
import android.util.Log;
import com.amazon.inapp.purchasing.Offset;
import com.amazon.inapp.purchasing.PurchasingManager;
import com.amazon.inapp.purchasing.PurchasingObserver;
import com.topfreegames.bikerace.billing.c;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: AmazonBillingManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a implements com.topfreegames.bikerace.billing.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.topfreegames.bikerace.billing.a f1139a;
    private c b;
    private PurchasingObserver c;
    private SharedPreferences d;

    public a(com.topfreegames.bikerace.billing.a aVar, List<String> list) {
        this.f1139a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        if (aVar == null) {
            throw new IllegalArgumentException("Billing Activity cannot be null!");
        }
        if (list == null) {
            throw new IllegalArgumentException("Products cannot be null!");
        }
        this.f1139a = aVar;
        this.b = new c(this.f1139a.getApplicationContext(), list);
        this.c = new b(this, this.f1139a.getApplicationContext());
        PurchasingManager.registerObserver(this.c);
        this.d = aVar.getSharedPreferences("com.topfreegames.bikerace.amazonbilling", 0);
        a(false);
    }

    private void a(final boolean z) {
        new Thread(new Runnable() { // from class: com.topfreegames.bikerace.billing.a.a.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    PurchasingManager.initiatePurchaseUpdatesRequest(z ? Offset.BEGINNING : Offset.fromString(a.this.d.getString("offset", Offset.BEGINNING.toString())));
                } catch (Exception e) {
                    Log.d("AmazonBilling", Log.getStackTraceString(e));
                    if (a.this.f1139a != null) {
                        a.this.f1139a.h();
                    }
                }
            }
        }).start();
    }

    @Override // com.topfreegames.bikerace.billing.b
    public void a() {
        a(true);
    }

    @Override // com.topfreegames.bikerace.billing.b
    public boolean b() {
        return true;
    }

    @Override // com.topfreegames.bikerace.billing.b
    public boolean a(String str) {
        try {
            PurchasingManager.initiatePurchaseRequest(str);
            return true;
        } catch (Exception e) {
            Log.d("AmazonBilling", Log.getStackTraceString(e));
            if (this.f1139a != null) {
                this.f1139a.c(str);
            }
            return false;
        }
    }

    @Override // com.topfreegames.bikerace.billing.b
    public Map<String, Integer> c() {
        return this.b.a();
    }

    @Override // com.topfreegames.bikerace.billing.b
    public void d() {
        this.f1139a = null;
        this.b = null;
        this.c = null;
    }
}

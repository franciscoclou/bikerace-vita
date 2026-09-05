package com.topfreegames.bikerace.billing.google;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/* JADX INFO: compiled from: BillingService.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class h extends d {
    public final String c;
    public final String d;
    final /* synthetic */ BillingService e;

    @Override // com.topfreegames.bikerace.billing.google.d
    public /* bridge */ /* synthetic */ int a() {
        return super.a();
    }

    @Override // com.topfreegames.bikerace.billing.google.d
    public /* bridge */ /* synthetic */ boolean b() {
        return super.b();
    }

    @Override // com.topfreegames.bikerace.billing.google.d
    public /* bridge */ /* synthetic */ boolean c() {
        return super.c();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(BillingService billingService, String str, String str2) {
        super(billingService, -1);
        this.e = billingService;
        this.c = str;
        this.d = str2;
    }

    @Override // com.topfreegames.bikerace.billing.google.d
    protected long d() {
        Bundle bundleA = a("REQUEST_PURCHASE");
        bundleA.putString("ITEM_ID", this.c);
        if (this.d != null) {
            bundleA.putString("DEVELOPER_PAYLOAD", this.d);
        }
        Bundle bundleA2 = BillingService.f1145a.a(bundleA);
        PendingIntent pendingIntent = (PendingIntent) bundleA2.getParcelable("PURCHASE_INTENT");
        if (pendingIntent == null) {
            Log.e("BillingService", "Error with requestPurchase");
            return a.f1146a;
        }
        m.a(pendingIntent, new Intent());
        return bundleA2.getLong("REQUEST_ID", a.f1146a);
    }

    @Override // com.topfreegames.bikerace.billing.google.d
    protected void a(c cVar) {
        m.a(this.e, this, cVar);
    }
}

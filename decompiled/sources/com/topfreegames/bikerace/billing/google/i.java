package com.topfreegames.bikerace.billing.google;

import android.os.Bundle;
import android.os.RemoteException;

/* JADX INFO: compiled from: BillingService.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class i extends d {
    long c;
    final /* synthetic */ BillingService d;

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
    public i(BillingService billingService) {
        super(billingService, -1);
        this.d = billingService;
    }

    @Override // com.topfreegames.bikerace.billing.google.d
    protected long d() {
        this.c = n.a();
        Bundle bundleA = a("RESTORE_TRANSACTIONS");
        bundleA.putLong("NONCE", this.c);
        return BillingService.f1145a.a(bundleA).getLong("REQUEST_ID", a.f1146a);
    }

    @Override // com.topfreegames.bikerace.billing.google.d
    protected void a(RemoteException remoteException) {
        super.a(remoteException);
        n.a(this.c);
    }

    @Override // com.topfreegames.bikerace.billing.google.d
    protected void a(c cVar) {
        m.a(this.d, this, cVar);
    }
}

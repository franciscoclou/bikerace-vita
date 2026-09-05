package com.topfreegames.bikerace.billing.google;

import android.os.Bundle;
import android.os.RemoteException;

/* JADX INFO: compiled from: BillingService.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class g extends d {
    long c;
    final String[] d;
    final /* synthetic */ BillingService e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(BillingService billingService, int i, String[] strArr) {
        super(billingService, i);
        this.e = billingService;
        this.d = strArr;
    }

    @Override // com.topfreegames.bikerace.billing.google.d
    protected long d() {
        this.c = n.a();
        Bundle bundleA = a("GET_PURCHASE_INFORMATION");
        bundleA.putLong("NONCE", this.c);
        bundleA.putStringArray("NOTIFY_IDS", this.d);
        return BillingService.f1145a.a(bundleA).getLong("REQUEST_ID", a.f1146a);
    }

    @Override // com.topfreegames.bikerace.billing.google.d
    protected void a(RemoteException remoteException) {
        super.a(remoteException);
        n.a(this.c);
    }
}

package com.topfreegames.bikerace.billing.google;

import android.os.Bundle;

/* JADX INFO: compiled from: BillingService.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class f extends d {
    final String[] c;
    final /* synthetic */ BillingService d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(BillingService billingService, int i, String[] strArr) {
        super(billingService, i);
        this.d = billingService;
        this.c = strArr;
    }

    @Override // com.topfreegames.bikerace.billing.google.d
    protected long d() {
        Bundle bundleA = a("CONFIRM_NOTIFICATIONS");
        bundleA.putStringArray("NOTIFY_IDS", this.c);
        return BillingService.f1145a.a(bundleA).getLong("REQUEST_ID", a.f1146a);
    }
}

package com.topfreegames.bikerace.billing.google;

/* JADX INFO: compiled from: BillingService.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class e extends d {
    final /* synthetic */ BillingService c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(BillingService billingService) {
        super(billingService, -1);
        this.c = billingService;
    }

    @Override // com.topfreegames.bikerace.billing.google.d
    protected long d() {
        m.a(BillingService.f1145a.a(a("CHECK_BILLING_SUPPORTED")).getInt("RESPONSE_CODE") == c.RESULT_OK.ordinal());
        return a.f1146a;
    }
}

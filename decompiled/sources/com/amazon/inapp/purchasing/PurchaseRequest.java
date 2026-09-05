package com.amazon.inapp.purchasing;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class PurchaseRequest extends Request {
    private final String _sku;

    PurchaseRequest(String str) {
        Validator.validateNotNull(str, "sku");
        this._sku = str;
    }

    @Override // com.amazon.inapp.purchasing.Request
    Runnable getRunnable() {
        return new Runnable() { // from class: com.amazon.inapp.purchasing.PurchaseRequest.1
            @Override // java.lang.Runnable
            public void run() {
                ImplementationFactory.getRequestHandler().sendPurchaseRequest(PurchaseRequest.this._sku, PurchaseRequest.this.getRequestId());
            }
        };
    }
}

package com.amazon.inapp.purchasing;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class PurchaseUpdatesRequest extends Request {
    private final Offset _offset;

    PurchaseUpdatesRequest(Offset offset) {
        Validator.validateNotNull(offset, "offset");
        this._offset = offset;
    }

    @Override // com.amazon.inapp.purchasing.Request
    Runnable getRunnable() {
        return new Runnable() { // from class: com.amazon.inapp.purchasing.PurchaseUpdatesRequest.1
            @Override // java.lang.Runnable
            public void run() {
                ImplementationFactory.getRequestHandler().sendPurchaseUpdatesRequest(PurchaseUpdatesRequest.this._offset, PurchaseUpdatesRequest.this.getRequestId());
            }
        };
    }
}

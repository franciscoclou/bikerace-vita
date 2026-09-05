package com.amazon.inapp.purchasing;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class ContentDownloadRequest extends Request {
    private final String _location;
    private final String _sku;

    ContentDownloadRequest(String str, String str2) {
        Validator.validateNotNull(str, "sku");
        Validator.validateNotNull(str2, "location");
        this._sku = str;
        this._location = str2;
    }

    @Override // com.amazon.inapp.purchasing.Request
    Runnable getRunnable() {
        return new Runnable() { // from class: com.amazon.inapp.purchasing.ContentDownloadRequest.1
            @Override // java.lang.Runnable
            public void run() {
                ImplementationFactory.getRequestHandler().sendContentDownloadRequest(ContentDownloadRequest.this._sku, ContentDownloadRequest.this._location, ContentDownloadRequest.this.getRequestId());
            }
        };
    }
}

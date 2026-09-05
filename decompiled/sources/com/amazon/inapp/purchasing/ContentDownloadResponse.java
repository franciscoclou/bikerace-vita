package com.amazon.inapp.purchasing;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class ContentDownloadResponse {
    private final ContentDownloadRequestStatus _contentDownloadRequestStatus;
    private final int _percentComplete;
    private final String _requestId;

    public enum ContentDownloadRequestStatus {
        IN_PROGRESS,
        COMPLETE,
        FAILED,
        INVALID_LOCATION,
        INVALID_SKU
    }

    ContentDownloadResponse(String str, int i, ContentDownloadRequestStatus contentDownloadRequestStatus) {
        Validator.validateNotNull(str, "requestId");
        Validator.validateNotNull(contentDownloadRequestStatus, "contentDownloadRequestStatus");
        this._requestId = str;
        this._percentComplete = i;
        this._contentDownloadRequestStatus = contentDownloadRequestStatus;
    }

    public ContentDownloadRequestStatus getContentDownloadRequestStatus() {
        return this._contentDownloadRequestStatus;
    }

    public int getPercentComplete() {
        return this._percentComplete;
    }

    public String getRequestId() {
        return this._requestId;
    }
}

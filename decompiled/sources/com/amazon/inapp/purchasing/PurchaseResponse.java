package com.amazon.inapp.purchasing;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class PurchaseResponse {
    private static final String TO_STRING_FORMAT = "(%s, requestId: \"%s\", purchaseRequestStatus: \"%s\", userId: \"%s\", receipt: %s)";
    private final PurchaseRequestStatus _purchaseRequestStatus;
    private final Receipt _receipt;
    private final String _requestId;
    private final String _userId;

    public enum PurchaseRequestStatus {
        SUCCESSFUL,
        FAILED,
        INVALID_SKU,
        ALREADY_ENTITLED
    }

    PurchaseResponse(String str, String str2, Receipt receipt, PurchaseRequestStatus purchaseRequestStatus) {
        Validator.validateNotNull(str, "requestId");
        Validator.validateNotNull(purchaseRequestStatus, "purchaseRequestStatus");
        if (purchaseRequestStatus == PurchaseRequestStatus.SUCCESSFUL) {
            Validator.validateNotNull(receipt, "receipt");
            Validator.validateNotNull(str2, "userId");
        }
        this._requestId = str;
        this._userId = str2;
        this._receipt = receipt;
        this._purchaseRequestStatus = purchaseRequestStatus;
    }

    public PurchaseRequestStatus getPurchaseRequestStatus() {
        return this._purchaseRequestStatus;
    }

    public Receipt getReceipt() {
        return this._receipt;
    }

    public String getRequestId() {
        return this._requestId;
    }

    public String getUserId() {
        return this._userId;
    }

    public String toString() {
        return String.format(TO_STRING_FORMAT, super.toString(), this._requestId, this._purchaseRequestStatus, this._userId, this._receipt);
    }
}

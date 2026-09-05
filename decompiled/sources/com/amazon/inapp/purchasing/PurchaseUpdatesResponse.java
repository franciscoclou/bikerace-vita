package com.amazon.inapp.purchasing;

import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class PurchaseUpdatesResponse {
    private static final String TO_STRING_FORMAT = "(%s, requestId: \"%s\", purchaseUpdatesRequestStatus: \"%s\", userId: \"%s\", receipts: %s, revokedSkus: %s, offset: \"%s\", isMore: \"%b\")";
    private final boolean _isMore;
    private final Offset _offset;
    private final PurchaseUpdatesRequestStatus _purchaseUpdatesRequestStatus;
    private final Set<Receipt> _receipts;
    private final String _requestId;
    private final Set<String> _revokedSkus;
    private final String _userId;

    public enum PurchaseUpdatesRequestStatus {
        SUCCESSFUL,
        FAILED
    }

    PurchaseUpdatesResponse(String str, String str2, PurchaseUpdatesRequestStatus purchaseUpdatesRequestStatus, Set<Receipt> set, Set<String> set2, Offset offset, boolean z) {
        Validator.validateNotNull(str, "requestId");
        Validator.validateNotNull(purchaseUpdatesRequestStatus, "purchaseUpdatesRequestStatus");
        Validator.validateNotNull(offset, "offset");
        if (PurchaseUpdatesRequestStatus.SUCCESSFUL == purchaseUpdatesRequestStatus) {
            Validator.validateNotNull(str2, "userId");
            Validator.validateNotNull(set, "receipts");
            Validator.validateNotNull(set2, "revokedSkus");
        }
        this._requestId = str;
        this._userId = str2;
        this._purchaseUpdatesRequestStatus = purchaseUpdatesRequestStatus;
        this._receipts = set == null ? new HashSet<>() : set;
        this._revokedSkus = set2 == null ? new HashSet<>() : set2;
        this._offset = offset;
        this._isMore = z;
    }

    public Offset getOffset() {
        return this._offset;
    }

    public PurchaseUpdatesRequestStatus getPurchaseUpdatesRequestStatus() {
        return this._purchaseUpdatesRequestStatus;
    }

    public Set<Receipt> getReceipts() {
        return this._receipts;
    }

    public String getRequestId() {
        return this._requestId;
    }

    public Set<String> getRevokedSkus() {
        return this._revokedSkus;
    }

    public String getUserId() {
        return this._userId;
    }

    public boolean isMore() {
        return this._isMore;
    }

    public String toString() {
        return String.format(TO_STRING_FORMAT, super.toString(), this._requestId, this._purchaseUpdatesRequestStatus, this._userId, this._receipts, this._revokedSkus, this._offset, Boolean.valueOf(this._isMore));
    }
}

package com.amazon.inapp.purchasing;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class GetUserIdResponse {
    private static final String TO_STRING_FORMAT = "(%s, requestId: \"%s\", getUserIdRequestStatus: \"%s\", userId: \"%s\")";
    private final GetUserIdRequestStatus _getUserIdRequestStatus;
    private final String _requestId;
    private final String _userId;

    public enum GetUserIdRequestStatus {
        SUCCESSFUL,
        FAILED
    }

    GetUserIdResponse(String str, GetUserIdRequestStatus getUserIdRequestStatus, String str2) {
        Validator.validateNotNull(str, "requestId");
        Validator.validateNotNull(getUserIdRequestStatus, "getUserIdRequestStatus");
        if (GetUserIdRequestStatus.SUCCESSFUL == getUserIdRequestStatus) {
            Validator.validateNotNull(str2, "userId");
        }
        this._requestId = str;
        this._userId = str2;
        this._getUserIdRequestStatus = getUserIdRequestStatus;
    }

    public String getRequestId() {
        return this._requestId;
    }

    public String getUserId() {
        return this._userId;
    }

    public GetUserIdRequestStatus getUserIdRequestStatus() {
        return this._getUserIdRequestStatus;
    }

    public String toString() {
        return String.format(TO_STRING_FORMAT, super.toString(), this._requestId, this._getUserIdRequestStatus, this._userId);
    }
}

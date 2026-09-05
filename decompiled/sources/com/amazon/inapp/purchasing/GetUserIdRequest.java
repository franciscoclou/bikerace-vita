package com.amazon.inapp.purchasing;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class GetUserIdRequest extends Request {
    GetUserIdRequest() {
    }

    @Override // com.amazon.inapp.purchasing.Request
    Runnable getRunnable() {
        return new Runnable() { // from class: com.amazon.inapp.purchasing.GetUserIdRequest.1
            @Override // java.lang.Runnable
            public void run() {
                ImplementationFactory.getRequestHandler().sendGetUserIdRequest(GetUserIdRequest.this.getRequestId());
            }
        };
    }
}

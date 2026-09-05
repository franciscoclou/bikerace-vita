package com.amazon.inapp.purchasing;

import java.util.UUID;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
abstract class Request {
    private final String _requestId = UUID.randomUUID().toString();

    Request() {
    }

    String getRequestId() {
        return this._requestId;
    }

    abstract Runnable getRunnable();
}

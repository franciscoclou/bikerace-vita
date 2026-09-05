package com.amazon.inapp.purchasing;

import android.os.Handler;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class HandlerAdapter {
    private Handler _handler;

    HandlerAdapter(Handler handler) {
        this._handler = handler;
    }

    boolean post(Runnable runnable) {
        return this._handler.post(runnable);
    }
}

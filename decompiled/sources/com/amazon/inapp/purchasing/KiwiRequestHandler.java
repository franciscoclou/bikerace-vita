package com.amazon.inapp.purchasing;

import com.amazon.android.Kiwi;
import java.util.Set;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class KiwiRequestHandler implements RequestHandler {
    static final String HANDLER_THREAD_NAME = "KiwiRequestHandlerHandlerThread";
    private static final String TAG = "KiwiRequestHandler";
    private final HandlerAdapter _handler = HandlerManager.getHandlerAdapter(HANDLER_THREAD_NAME);

    KiwiRequestHandler() {
    }

    @Override // com.amazon.inapp.purchasing.RequestHandler
    public void sendContentDownloadRequest(String str, String str2, String str3) {
    }

    @Override // com.amazon.inapp.purchasing.RequestHandler
    public void sendGetUserIdRequest(final String str) {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "sendGetUserIdRequest");
        }
        this._handler.post(new Runnable() { // from class: com.amazon.inapp.purchasing.KiwiRequestHandler.1
            @Override // java.lang.Runnable
            public void run() {
                Kiwi.addCommandToCommandTaskPipeline(new KiwiGetUserIdCommandTask(str));
            }
        });
    }

    @Override // com.amazon.inapp.purchasing.RequestHandler
    public void sendItemDataRequest(final Set<String> set, final String str) {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "sendItemDataRequest");
        }
        this._handler.post(new Runnable() { // from class: com.amazon.inapp.purchasing.KiwiRequestHandler.3
            @Override // java.lang.Runnable
            public void run() {
                Kiwi.addCommandToCommandTaskPipeline(new KiwiGetItemDataRequestCommandTask(set, str));
            }
        });
    }

    @Override // com.amazon.inapp.purchasing.RequestHandler
    public void sendPurchaseRequest(final String str, final String str2) {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "sendPurchaseRequest");
        }
        this._handler.post(new Runnable() { // from class: com.amazon.inapp.purchasing.KiwiRequestHandler.2
            @Override // java.lang.Runnable
            public void run() {
                Kiwi.addCommandToCommandTaskPipeline(new KiwiPurchaseRequestCommandTask(str, str2));
            }
        });
    }

    @Override // com.amazon.inapp.purchasing.RequestHandler
    public void sendPurchaseResponseReceivedRequest(final String str) {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "sendPurchaseResponseReceivedRequest");
        }
        this._handler.post(new Runnable() { // from class: com.amazon.inapp.purchasing.KiwiRequestHandler.5
            @Override // java.lang.Runnable
            public void run() {
                Kiwi.addCommandToCommandTaskPipeline(new KiwiResponseReceivedCommandTask(str));
            }
        });
    }

    @Override // com.amazon.inapp.purchasing.RequestHandler
    public void sendPurchaseUpdatesRequest(final Offset offset, final String str) {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "sendPurchaseUpdatesRequest");
        }
        this._handler.post(new Runnable() { // from class: com.amazon.inapp.purchasing.KiwiRequestHandler.4
            @Override // java.lang.Runnable
            public void run() {
                Kiwi.addCommandToCommandTaskPipeline(new KiwiPurchaseUpdatesCommandTask(offset, str));
            }
        });
    }
}

package com.amazon.inapp.purchasing;

import android.content.Context;
import android.content.Intent;
import com.amazon.android.Kiwi;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class KiwiResponseHandler implements ResponseHandler {
    static final String HANDLER_THREAD_NAME = "KiwiResponseHandlerHandlerThread";
    private static final String KEY_REQUEST_ID = "requestId";
    private static final String KEY_RESPONSE_TYPE = "response_type";
    private static final String TAG = "KiwiResponseHandler";
    private final HandlerAdapter _handler = HandlerManager.getHandlerAdapter(HANDLER_THREAD_NAME);

    class PurchaseResponseHandlerRunnable extends ResponseHandlerRunnable {
        public PurchaseResponseHandlerRunnable(Context context, Intent intent) {
            super(context, intent);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (Logger.isTraceOn()) {
                Logger.trace(KiwiResponseHandler.TAG, "PurchaseResponseHandlerRunnable.run()");
            }
            String string = getIntent().getExtras().getString(KiwiResponseHandler.KEY_REQUEST_ID);
            if (Logger.isTraceOn()) {
                Logger.trace(KiwiResponseHandler.TAG, "PurchaseResponseHandlerRunnable.run: requestId: " + string);
            }
            if (string == null || string.trim().length() <= 0) {
                return;
            }
            Kiwi.addCommandToCommandTaskPipeline(new KiwiPurchaseResponseCommandTask(string));
        }
    }

    abstract class ResponseHandlerRunnable implements Runnable {
        private final Context _context;
        private final Intent _intent;

        public ResponseHandlerRunnable(Context context, Intent intent) {
            this._context = context;
            this._intent = intent;
        }

        protected final Context getContext() {
            return this._context;
        }

        protected final Intent getIntent() {
            return this._intent;
        }
    }

    enum ResponseType {
        purchase_response,
        item_response,
        updates_response
    }

    KiwiResponseHandler() {
    }

    @Override // com.amazon.inapp.purchasing.ResponseHandler
    public void handleResponse(Context context, Intent intent) {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "handleResponse");
        }
        String string = intent.getExtras().getString(KEY_RESPONSE_TYPE);
        if (string == null) {
            if (Logger.isTraceOn()) {
                Logger.trace(TAG, "Invalid response type: null");
                return;
            }
            return;
        }
        try {
            ResponseType responseTypeValueOf = ResponseType.valueOf(string);
            if (Logger.isTraceOn()) {
                Logger.trace(TAG, "Found response type: " + responseTypeValueOf);
            }
            PurchaseResponseHandlerRunnable purchaseResponseHandlerRunnable = null;
            switch (responseTypeValueOf) {
                case purchase_response:
                    purchaseResponseHandlerRunnable = new PurchaseResponseHandlerRunnable(context, intent);
                    break;
            }
            if (purchaseResponseHandlerRunnable != null) {
                this._handler.post(purchaseResponseHandlerRunnable);
            }
        } catch (IllegalArgumentException e) {
            if (Logger.isTraceOn()) {
                Logger.trace(TAG, "Invlid response type: " + string);
            }
        }
    }
}

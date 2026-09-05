package com.amazon.inapp.purchasing;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class PurchasingManager {
    public static final String BUILD_ID = "1.0.3";
    public static final int ITEM_DATA_REQUEST_MAX_SKUS = 100;
    private static final String TAG = "PurchasingManager";
    private static PurchasingObserver registeredPurchasingObserver;
    static final String HANDLER_THREAD_NAME = "PurchasingManagerHandlerThread";
    private static final HandlerAdapter HANDLER = HandlerManager.getHandlerAdapter(HANDLER_THREAD_NAME);

    static {
        HANDLER.post(new Runnable() { // from class: com.amazon.inapp.purchasing.PurchasingManager.1
            @Override // java.lang.Runnable
            public void run() {
                Log.i(PurchasingManager.TAG, "Purchasing Framework initialization complete. Build ID 1.0.3");
            }
        });
    }

    private PurchasingManager() {
    }

    private static void checkObserverRegistered() {
        if (registeredPurchasingObserver == null) {
            throw new IllegalStateException("You must register a PurchasingObserver before invoking this operation");
        }
    }

    static Context getObserverContext() {
        if (registeredPurchasingObserver == null) {
            return null;
        }
        return registeredPurchasingObserver.getContext();
    }

    static PurchasingObserver getPurchasingObserver() {
        return registeredPurchasingObserver;
    }

    public static String initiateGetUserIdRequest() {
        checkObserverRegistered();
        return initiateRequest(new GetUserIdRequest());
    }

    public static String initiateItemDataRequest(Set<String> set) {
        checkObserverRegistered();
        return initiateRequest(new ItemDataRequest(new HashSet(set)));
    }

    public static String initiatePurchaseRequest(String str) {
        checkObserverRegistered();
        return initiateRequest(new PurchaseRequest(str));
    }

    public static String initiatePurchaseUpdatesRequest(Offset offset) {
        checkObserverRegistered();
        return initiateRequest(new PurchaseUpdatesRequest(offset));
    }

    private static String initiateRequest(Request request) {
        HANDLER.post(request.getRunnable());
        return request.getRequestId();
    }

    public static void registerObserver(final PurchasingObserver purchasingObserver) {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "PurchasingObserver registered: " + purchasingObserver);
        }
        if (purchasingObserver == null) {
            throw new IllegalArgumentException("Provided PurchasingObserver must not be null");
        }
        registeredPurchasingObserver = purchasingObserver;
        HANDLER.post(new Runnable() { // from class: com.amazon.inapp.purchasing.PurchasingManager.2
            @Override // java.lang.Runnable
            public void run() {
                new Handler(PurchasingManager.getObserverContext().getMainLooper()).post(new Runnable() { // from class: com.amazon.inapp.purchasing.PurchasingManager.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        purchasingObserver.onSdkAvailable(ImplementationFactory.isSandboxMode());
                    }
                });
            }
        });
    }
}

package com.amazon.inapp.purchasing;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class HandlerManager {
    private static volatile Map<String, HandlerAdapter> HANDLER_ADAPTERS = new HashMap();
    private static volatile HandlerAdapter MAIN_HANDLER_ADAPTER = null;

    HandlerManager() {
    }

    static HandlerAdapter getHandlerAdapter(String str) {
        if (!HANDLER_ADAPTERS.containsKey(str)) {
            synchronized (HandlerManager.class) {
                if (!HANDLER_ADAPTERS.containsKey(str)) {
                    HandlerThread handlerThread = new HandlerThread(str);
                    handlerThread.start();
                    HANDLER_ADAPTERS.put(str, new HandlerAdapter(new Handler(handlerThread.getLooper())));
                }
            }
        }
        return HANDLER_ADAPTERS.get(str);
    }

    static HandlerAdapter getMainHandlerAdapter() {
        if (MAIN_HANDLER_ADAPTER == null) {
            synchronized (HandlerManager.class) {
                if (MAIN_HANDLER_ADAPTER == null) {
                    MAIN_HANDLER_ADAPTER = new HandlerAdapter(new Handler(PurchasingManager.getObserverContext().getMainLooper()));
                }
            }
        }
        return MAIN_HANDLER_ADAPTER;
    }

    static void setHandlerAdapter(String str, HandlerAdapter handlerAdapter) {
        HANDLER_ADAPTERS.put(str, handlerAdapter);
    }

    static void setMainHandlerAdapter(HandlerAdapter handlerAdapter) {
        MAIN_HANDLER_ADAPTER = handlerAdapter;
    }
}

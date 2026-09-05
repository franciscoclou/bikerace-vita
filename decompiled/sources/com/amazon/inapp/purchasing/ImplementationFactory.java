package com.amazon.inapp.purchasing;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class ImplementationFactory {
    private static volatile ImplementationRegistry IMPLEMENTATION_REGISTRY;
    private static volatile boolean IS_SANDBOX_MODE;
    private static volatile boolean IS_SANDBOX_MODE_CHECKED;
    private static volatile LogHandler LOG_HANDLER_INSTANCE;
    private static volatile RequestHandler REQUEST_HANDLER_INSTANCE;
    private static volatile ResponseHandler RESPONSE_HANDLER_INSTANCE;

    ImplementationFactory() {
    }

    private static ImplementationRegistry getImplementationRegistry() {
        if (IMPLEMENTATION_REGISTRY == null) {
            synchronized (ImplementationFactory.class) {
                if (IMPLEMENTATION_REGISTRY == null) {
                    if (isSandboxMode()) {
                        IMPLEMENTATION_REGISTRY = new SandboxImplementationRegistry();
                    } else {
                        IMPLEMENTATION_REGISTRY = new KiwiImplementationRegistry();
                    }
                }
            }
        }
        return IMPLEMENTATION_REGISTRY;
    }

    private static <T> T getInstance(Class<T> cls, T t) {
        if (t == null) {
            synchronized (ImplementationFactory.class) {
                if (t == null) {
                    try {
                        t = getImplementationRegistry().getImplementation(cls).newInstance();
                    } catch (Exception e) {
                    }
                }
            }
        }
        return t;
    }

    static LogHandler getLogHandler() {
        return LOG_HANDLER_INSTANCE != null ? LOG_HANDLER_INSTANCE : (LogHandler) getInstance(LogHandler.class, LOG_HANDLER_INSTANCE);
    }

    static RequestHandler getRequestHandler() {
        return REQUEST_HANDLER_INSTANCE != null ? REQUEST_HANDLER_INSTANCE : (RequestHandler) getInstance(RequestHandler.class, REQUEST_HANDLER_INSTANCE);
    }

    static ResponseHandler getResponseHandler() {
        return RESPONSE_HANDLER_INSTANCE != null ? RESPONSE_HANDLER_INSTANCE : (ResponseHandler) getInstance(ResponseHandler.class, RESPONSE_HANDLER_INSTANCE);
    }

    static boolean isSandboxMode() {
        if (IS_SANDBOX_MODE_CHECKED) {
            return IS_SANDBOX_MODE;
        }
        synchronized (ImplementationFactory.class) {
            if (IS_SANDBOX_MODE_CHECKED) {
                return IS_SANDBOX_MODE;
            }
            try {
                ImplementationFactory.class.getClassLoader().loadClass("com.amazon.android.Kiwi");
                IS_SANDBOX_MODE = false;
            } catch (Throwable th) {
                IS_SANDBOX_MODE = true;
            }
            IS_SANDBOX_MODE_CHECKED = true;
            return IS_SANDBOX_MODE;
        }
    }
}

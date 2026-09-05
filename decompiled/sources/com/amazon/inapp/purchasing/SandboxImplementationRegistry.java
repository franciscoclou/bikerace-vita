package com.amazon.inapp.purchasing;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class SandboxImplementationRegistry implements ImplementationRegistry {
    private static final Map<Class, Class> classMap = new HashMap();

    static {
        classMap.put(RequestHandler.class, SandboxRequestHandler.class);
        classMap.put(ResponseHandler.class, SandboxResponseHandler.class);
        classMap.put(LogHandler.class, SandboxLogHandler.class);
    }

    SandboxImplementationRegistry() {
    }

    @Override // com.amazon.inapp.purchasing.ImplementationRegistry
    public <T> Class<T> getImplementation(Class<T> cls) {
        return classMap.get(cls);
    }
}

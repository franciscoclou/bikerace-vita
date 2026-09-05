package com.amazonaws.f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {
    private static final Log e = LogFactory.getLog("com.amazonaws.latency");
    private static final Object f = "=";
    private static final Object g = ", ";
    private final Map<String, List<Object>> c = new HashMap();
    private final Map<String, Long> d = new HashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final l f116a = new l();
    private final boolean b = a();

    private static boolean a() {
        return System.getProperty("com.amazonaws.sdk.enableRuntimeProfiling") != null;
    }

    public void a(String str) {
        if (this.b) {
            this.d.put(str, Long.valueOf(System.nanoTime()));
        }
    }

    public void a(String str, long j) {
        if (this.b) {
            this.f116a.a(str, j);
        }
    }

    public void a(String str, Object obj) {
        List<Object> arrayList = this.c.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.c.put(str, arrayList);
        }
        arrayList.add(obj);
    }

    public void b(String str) {
        if (this.b) {
            Long l = this.d.get(str);
            if (l == null) {
                throw new IllegalStateException("Trying to end an event which was never started. " + str);
            }
            this.f116a.a(str, new l(l.longValue(), System.nanoTime()));
        }
    }
}

package com.heyzap.internal;

import android.content.Context;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/* JADX INFO: compiled from: APIClient.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {
    private static com.heyzap.a.j c;
    private static ThreadPoolExecutor b = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static com.heyzap.a.a f751a = new com.heyzap.a.a();

    static {
        f751a.a((ThreadPoolExecutor) Executors.newFixedThreadPool(1));
    }

    public static synchronized void a(Context context) {
        if (c == null) {
            c = new com.heyzap.a.j(context);
            f751a.a(c);
        }
    }

    public static void a(Context context, String str, com.heyzap.a.g gVar) {
        a(context, str, gVar, null);
    }

    public static void a(Context context, String str, com.heyzap.a.g gVar, com.heyzap.a.d dVar) {
        a(context);
        com.heyzap.a.g gVarA = a(gVar, context);
        k.a("endpoint", str);
        k.a("params", gVarA);
        f751a.a(context, a(str), gVarA, dVar);
    }

    public static void b(Context context, String str, com.heyzap.a.g gVar, com.heyzap.a.d dVar) {
        a(context);
        com.heyzap.a.g gVarA = a(gVar, context);
        k.a("params", gVarA);
        String strA = a(str);
        k.a("using url", strA);
        f751a.b(context, strA, gVarA, dVar);
    }

    private static String a(String str) {
        if (str == null || !str.startsWith("/")) {
            return (str == null || !str.startsWith("http://")) ? "http://android.heyzap.com/in_game_api/sdk/" + str : str;
        }
        return "http://android.heyzap.com" + str;
    }

    /* JADX WARN: Code duplicated, block: B:9:0x001c A[Catch: all -> 0x0032, LOOP:0: B:7:0x0016->B:9:0x001c, LOOP_END, TRY_LEAVE, TryCatch #0 {, blocks: (B:5:0x0005, B:6:0x000a, B:7:0x0016, B:9:0x001c), top: B:16:0x0005 }] */
    public static synchronized com.heyzap.a.g a(com.heyzap.a.g gVar, Context context) {
        if (gVar == null) {
            gVar = new com.heyzap.a.g();
            for (Map.Entry<String, String> entry : l.d(context).entrySet()) {
                gVar.a(entry.getKey(), entry.getValue());
            }
        } else {
            while (r3.hasNext()) {
                gVar.a(entry.getKey(), entry.getValue());
            }
        }
        throw th;
        return gVar;
    }
}

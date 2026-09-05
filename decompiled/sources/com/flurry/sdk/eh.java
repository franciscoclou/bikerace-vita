package com.flurry.sdk;

import android.location.Criteria;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class eh {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Integer f533a = 157;
    public static final String b = null;
    public static final Boolean c = true;
    public static final Boolean d = false;
    public static final String e = null;
    public static final Boolean f = true;
    public static final Criteria g = null;
    public static final Long h = 10000L;
    public static final Boolean i = true;
    public static final Long j = 0L;
    public static final Byte k = (byte) -1;
    private static ei l;

    public static synchronized ei a() {
        if (l == null) {
            l = new ei();
            b();
        }
        return l;
    }

    private static void b() {
        if (l == null) {
            l = new ei();
        }
        l.a("AgentVersion", (Object) f533a);
        l.a("VesionName", (Object) b);
        l.a("CaptureUncaughtExceptions", (Object) c);
        l.a("UseHttps", (Object) d);
        l.a("ReportUrl", (Object) e);
        l.a("ReportLocation", (Object) f);
        l.a("LocationCriteria", (Object) g);
        l.a("ContinueSessionMillis", (Object) h);
        l.a("LogEvents", (Object) i);
        l.a("Age", (Object) j);
        l.a("Gender", (Object) k);
        l.a("UserId", (Object) "");
    }
}

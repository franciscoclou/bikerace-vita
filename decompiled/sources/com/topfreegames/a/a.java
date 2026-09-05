package com.topfreegames.a;

import android.content.Context;
import com.flurry.android.FlurryAgent;
import java.util.Map;

/* JADX INFO: compiled from: Analytics.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final c f809a = b.a();

    public static void a(Context context) {
        if (f809a == c.FLURRY) {
            if (b.c()) {
                FlurryAgent.setLogEnabled(true);
            } else {
                FlurryAgent.setLogEnabled(false);
            }
            FlurryAgent.setUseHttps(true);
            FlurryAgent.onStartSession(context, b.b());
            return;
        }
        c cVar = c.TOPAZ;
    }

    public static void b(Context context) {
        if (f809a == c.FLURRY) {
            FlurryAgent.onEndSession(context);
        } else {
            c cVar = c.TOPAZ;
        }
    }

    public static void a(String str) {
        if (f809a == c.FLURRY) {
            FlurryAgent.logEvent(str);
        } else {
            c cVar = c.TOPAZ;
        }
    }

    public static void a(String str, Map<String, String> map) {
        if (f809a == c.FLURRY) {
            FlurryAgent.logEvent(str, map);
        } else {
            c cVar = c.TOPAZ;
        }
    }

    public static void a(String str, String str2, String str3) {
        if (f809a == c.FLURRY) {
            FlurryAgent.onError(str, str3, str2);
        } else {
            c cVar = c.TOPAZ;
        }
    }
}

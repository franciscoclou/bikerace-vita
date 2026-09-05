package com.heyzap.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.facebook.internal.ServerProtocol;
import java.net.URLEncoder;

/* JADX INFO: compiled from: Analytics.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f753a = false;
    private static String b = "";

    public static synchronized void a(Context context, String str) {
        Log.d("HeyzapSDK", "Tracking " + str + " event.");
        if (!f753a) {
            b(context);
            f753a = true;
        }
        com.heyzap.a.g gVar = new com.heyzap.a.g();
        gVar.a("track_hash", b);
        gVar.a(ServerProtocol.DIALOG_PARAM_TYPE, str);
        a.a(context, "/mobile/track_sdk_event", gVar);
    }

    public static String b(Context context, String str) {
        String str2;
        String strC = c(context);
        if (strC != null) {
            str2 = "utm_medium=device&utm_source=heyzap_track&utm_campaign=" + strC;
        } else {
            str2 = "utm_medium=device&utm_source=sdk&utm_campaign=" + context.getPackageName();
        }
        if (str != null) {
            str2 = str2 + "&" + str;
        }
        return URLEncoder.encode(str2);
    }

    private static void b(final Context context) {
        l.a(context);
        new Thread(new Runnable() { // from class: com.heyzap.internal.c.1
            @Override // java.lang.Runnable
            public void run() {
                String strC = c.c(context);
                if (strC != null) {
                    String unused = c.b = strC;
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String c(Context context) {
        SharedPreferences sharedPreferences;
        if (b == null && (sharedPreferences = context.getSharedPreferences("heyzap_button_analytics_id", 0)) != null) {
            b = sharedPreferences.getString("heyzap_button_analytics_id", null);
        }
        if (b == null || !b.trim().equals("")) {
            return b;
        }
        return null;
    }
}

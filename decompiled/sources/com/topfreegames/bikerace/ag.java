package com.topfreegames.bikerace;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.util.Map;

/* JADX INFO: compiled from: GoogleAnalyticsHelper.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ag {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f1119a = false;

    private static Map<String, String> a(Uri uri) {
        com.google.a.a.a.ak akVar = new com.google.a.a.a.ak();
        if (uri == null) {
            return akVar.a();
        }
        if (uri.getQueryParameter("utm_source") != null) {
            akVar.a(uri.toString());
        } else if (uri.getAuthority() != null) {
            akVar.a("&cm", "referral");
            akVar.a("&cs", uri.getAuthority());
        }
        return akVar.a();
    }

    public static void a(Intent intent) {
        com.google.a.a.a.ak.b().a(a(intent.getData()));
    }

    public static void a(Activity activity) {
        com.google.a.a.a.o.a((Context) activity).a(activity);
    }

    public static void b(Activity activity) {
        com.google.a.a.a.o.a((Context) activity).b(activity);
    }

    public static void a(Activity activity, int i) {
        com.google.a.a.a.o oVarA = com.google.a.a.a.o.a((Context) activity);
        switch (i) {
            case 0:
                oVarA.a(com.google.a.a.a.ak.a("Retention", "Rate", "Init", null).a());
                break;
            case 1:
                oVarA.a(com.google.a.a.a.ak.a("Retention", "Rate", Integer.toString(1), null).a());
                break;
            case 7:
                oVarA.a(com.google.a.a.a.ak.a("Retention", "Rate", Integer.toString(7), null).a());
                break;
            case 28:
                oVarA.a(com.google.a.a.a.ak.a("Retention", "Rate", Integer.toString(28), null).a());
                break;
        }
    }
}

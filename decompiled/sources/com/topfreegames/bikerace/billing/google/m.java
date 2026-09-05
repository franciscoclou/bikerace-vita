package com.topfreegames.bikerace.billing.google;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* JADX INFO: compiled from: ResponseHandler.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static l f1154a;
    private static com.topfreegames.bikerace.billing.c b;

    public static synchronized void a(l lVar, com.topfreegames.bikerace.billing.c cVar) {
        f1154a = lVar;
        b = cVar;
    }

    public static synchronized void b(l lVar, com.topfreegames.bikerace.billing.c cVar) {
        if (f1154a == lVar) {
            f1154a = null;
        }
        if (b == cVar) {
            b = null;
        }
    }

    public static void a(boolean z) {
        if (f1154a != null) {
            f1154a.a(z);
        }
    }

    public static void a(PendingIntent pendingIntent, Intent intent) {
        if (f1154a != null) {
            f1154a.a(pendingIntent, intent);
        }
    }

    public static void a(Context context, final b bVar, final String str, final String str2, final long j, final String str3) {
        new Thread(new Runnable() { // from class: com.topfreegames.bikerace.billing.google.m.1
            @Override // java.lang.Runnable
            public void run() {
                if (m.b != null) {
                    int iA = m.b.a(str2, str, bVar, j, str3);
                    synchronized (m.class) {
                        if (m.f1154a != null) {
                            m.f1154a.b(bVar, str, iA, j, str3);
                        }
                    }
                    return;
                }
                Log.e("ResponseHandler", "There is not a registered database to be updated");
            }
        }).start();
    }

    public static void a(Context context, h hVar, c cVar) {
        if (f1154a != null) {
            f1154a.a(hVar, cVar);
        }
    }

    public static void a(Context context, i iVar, c cVar) {
        if (f1154a != null) {
            f1154a.a(iVar, cVar);
        }
    }
}

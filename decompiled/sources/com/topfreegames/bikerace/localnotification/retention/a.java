package com.topfreegames.bikerace.localnotification.retention;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.bb;

/* JADX INFO: compiled from: RetentionLocalNotitification.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {
    public static void a(final BikeRaceApplication bikeRaceApplication) {
        new Thread(new Runnable() { // from class: com.topfreegames.bikerace.localnotification.retention.a.1
            @Override // java.lang.Runnable
            public void run() {
                int[] iArrD;
                try {
                    bb bbVarA = bikeRaceApplication.a(false);
                    if (bbVarA.b() && (iArrD = bbVarA.d()) != null) {
                        for (int i : iArrD) {
                            a.b(bikeRaceApplication, (((long) i) * 86400000) + System.currentTimeMillis(), "com.topfreegames.bikerace.localnotification.ret", i + 304823);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }).run();
    }

    public static void b(final BikeRaceApplication bikeRaceApplication) {
        new Thread(new Runnable() { // from class: com.topfreegames.bikerace.localnotification.retention.a.2
            @Override // java.lang.Runnable
            public void run() {
                for (int i = 0; i < 90; i++) {
                    a.b(bikeRaceApplication, 304823 + i, "com.topfreegames.bikerace.localnotification.ret");
                }
            }
        }).run();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, long j, String str, int i) {
        ((AlarmManager) context.getSystemService("alarm")).set(0, j, PendingIntent.getBroadcast(context, i, new Intent(str), 134217728));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, int i, String str) {
        ((AlarmManager) context.getSystemService("alarm")).cancel(PendingIntent.getBroadcast(context, i, new Intent(str), 134217728));
    }
}

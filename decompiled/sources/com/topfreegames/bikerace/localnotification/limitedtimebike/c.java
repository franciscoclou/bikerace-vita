package com.topfreegames.bikerace.localnotification.limitedtimebike;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.facebook.internal.ServerProtocol;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.bb;

/* JADX INFO: compiled from: NotificationScheduler.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c {
    public static void a(Context context) {
        c(context);
        d(context);
        e(context);
    }

    private static void c(Context context) {
        final Context applicationContext = context.getApplicationContext();
        new Thread(new Runnable() { // from class: com.topfreegames.bikerace.localnotification.limitedtimebike.c.1
            @Override // java.lang.Runnable
            public void run() {
                SharedPreferences sharedPreferencesF = c.f(applicationContext);
                bb bbVarA = ((BikeRaceApplication) applicationContext).a(false);
                c.b(applicationContext, sharedPreferencesF, "com.topfreegames.bikerace.halloween_notification", 1);
                if (c.b(applicationContext, sharedPreferencesF, bbVarA, "com.topfreegames.bikerace.halloween_notification", 1)) {
                    long jG = bbVarA.g() - 86400000;
                    if (jG > com.topfreegames.c.a.a().getTime()) {
                        c.b(applicationContext, sharedPreferencesF, bbVarA.i(), jG, "com.topfreegames.bikerace.halloween_notification", 1, b.HALLOWEEN_BIKE_EXPIRE);
                    }
                }
            }
        }).start();
    }

    private static void d(Context context) {
        final Context applicationContext = context.getApplicationContext();
        new Thread(new Runnable() { // from class: com.topfreegames.bikerace.localnotification.limitedtimebike.c.2
            @Override // java.lang.Runnable
            public void run() {
                SharedPreferences sharedPreferencesF = c.f(applicationContext);
                bb bbVarA = ((BikeRaceApplication) applicationContext).a(false);
                if (c.b(applicationContext, sharedPreferencesF, bbVarA, "com.topfreegames.bikerace.thanksgiving_notification", 1)) {
                    long J = bbVarA.J() - 86400000;
                    if (J > com.topfreegames.c.a.a().getTime()) {
                        c.b(applicationContext, sharedPreferencesF, bbVarA.L(), J, "com.topfreegames.bikerace.thanksgiving_notification", 1, b.THANKSGIVING_BIKE_EXPIRE);
                    }
                }
            }
        }).start();
    }

    private static void e(Context context) {
        final Context applicationContext = context.getApplicationContext();
        new Thread(new Runnable() { // from class: com.topfreegames.bikerace.localnotification.limitedtimebike.c.3
            @Override // java.lang.Runnable
            public void run() {
                SharedPreferences sharedPreferencesF = c.f(applicationContext);
                bb bbVarA = ((BikeRaceApplication) applicationContext).a(false);
                if (c.b(applicationContext, sharedPreferencesF, bbVarA, "com.topfreegames.bikerace.holiday_notification", 1)) {
                    long jN = bbVarA.N() - 86400000;
                    if (jN > com.topfreegames.c.a.a().getTime()) {
                        c.b(applicationContext, sharedPreferencesF, bbVarA.P(), jN, "com.topfreegames.bikerace.holiday_notification", 1, b.HOLIDAY_BIKE_EXPIRE);
                    }
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(Context context, SharedPreferences sharedPreferences, bb bbVar, String str, int i) {
        return PendingIntent.getBroadcast(context, 0, new Intent(str), 536870912) == null && sharedPreferences.getInt(str, 0) < i && bbVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, SharedPreferences sharedPreferences, String str, int i) {
        if (PendingIntent.getBroadcast(context, 0, new Intent(str), 536870912) != null && sharedPreferences.getInt(str, 0) == 0) {
            sharedPreferences.edit().putInt(str, i).commit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SharedPreferences f(Context context) {
        return context.getSharedPreferences("com.topfreegames.bikerace.localnotification", 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, SharedPreferences sharedPreferences, String str, long j, String str2, int i, b bVar) {
        Intent intent = new Intent(str2);
        intent.putExtra("msg", str);
        intent.putExtra(ServerProtocol.DIALOG_PARAM_TYPE, bVar.ordinal());
        ((AlarmManager) context.getSystemService("alarm")).set(0, j, PendingIntent.getBroadcast(context, 192837, intent, 134217728));
        sharedPreferences.edit().putInt(str2, i).commit();
    }
}

package com.topfreegames.bikerace.worldcup.localnotification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.facebook.internal.ServerProtocol;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.bb;
import com.topfreegames.bikerace.worldcup.h;
import com.topfreegames.bikerace.worldcup.l;
import com.topfreegames.bikerace.worldcup.o;

/* JADX INFO: compiled from: BikePromoLocalNotitification.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {
    public static void a(final BikeRaceApplication bikeRaceApplication) {
        new Thread(new Runnable() { // from class: com.topfreegames.bikerace.worldcup.localnotification.a.1
            @Override // java.lang.Runnable
            public void run() {
                int i = 0;
                try {
                    SharedPreferences sharedPreferences = bikeRaceApplication.getSharedPreferences("com.topfreegames.bikerace.worldcup.localnotification", 0);
                    h hVarV = o.a().v();
                    bb bbVarA = bikeRaceApplication.a(false);
                    if (bbVarA.b() && (!sharedPreferences.getBoolean("scheduled", false) || !sharedPreferences.getBoolean("scheduled_new", false))) {
                        c[] cVarArrValuesCustom = c.valuesCustom();
                        while (true) {
                            int i2 = i;
                            if (i2 >= cVarArrValuesCustom.length) {
                                break;
                            }
                            long jA = hVarV.a(cVarArrValuesCustom[i2].a());
                            if (jA > com.topfreegames.c.a.a().getTime() && cVarArrValuesCustom[i2] != c.LASTDAY_BIKEPROMO && cVarArrValuesCustom[i2] != c.JULY_4) {
                                int iOrdinal = 66666 + cVarArrValuesCustom[i2].ordinal();
                                a.b(bikeRaceApplication, iOrdinal, "com.topfreegames.bikerace.worldcup.localnotification");
                                a.b(bikeRaceApplication, jA, "com.topfreegames.bikerace.worldcup.localnotification", iOrdinal, cVarArrValuesCustom[i2]);
                            }
                            i = i2 + 1;
                        }
                        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                        editorEdit.putBoolean("scheduled", true);
                        editorEdit.putBoolean("scheduled_new", true);
                        editorEdit.commit();
                    }
                    long jAe = bbVarA.ae();
                    if (bbVarA.b() && sharedPreferences.getLong("lastday", 0L) != jAe && jAe > com.topfreegames.c.a.a().getTime()) {
                        a.b(bikeRaceApplication, o.a().w(), "com.topfreegames.bikerace.worldcup.localnotification", 88888, c.LASTDAY_BIKEPROMO);
                        sharedPreferences.edit().putLong("lastday", jAe).commit();
                    }
                    long jB = l.b();
                    if (!bbVarA.b() || sharedPreferences.getBoolean("4hjulyscheduled", false) || jB <= com.topfreegames.c.a.a().getTime()) {
                        return;
                    }
                    a.b(bikeRaceApplication, jB, "com.topfreegames.bikerace.worldcup.localnotification", 44444, c.JULY_4);
                    sharedPreferences.edit().putBoolean("4hjulyscheduled", true).commit();
                } catch (Exception e) {
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, long j, String str, int i, c cVar) {
        Intent intent = new Intent(str);
        intent.putExtra(ServerProtocol.DIALOG_PARAM_TYPE, cVar.ordinal());
        ((AlarmManager) context.getSystemService("alarm")).set(0, j, PendingIntent.getBroadcast(context, i, intent, 134217728));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, int i, String str) {
        ((AlarmManager) context.getSystemService("alarm")).cancel(PendingIntent.getBroadcast(context, i, new Intent(str), 134217728));
    }
}

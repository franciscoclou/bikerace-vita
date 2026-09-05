package com.topfreegames.bikerace.worldcup.localnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import com.facebook.internal.ServerProtocol;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.activities.MainActivity;
import com.topfreegames.bikerace.bb;
import com.topfreegames.bikerace.worldcup.l;
import com.topfreegames.bikerace.worldcup.o;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AlarmReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        int intExtra = intent.getIntExtra(ServerProtocol.DIALOG_PARAM_TYPE, -1);
        if (intExtra >= 0 && intExtra < c.valuesCustom().length) {
            c cVar = c.valuesCustom()[intExtra];
            BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) context.getApplicationContext();
            bb bbVarA = bikeRaceApplication.a(false);
            bikeRaceApplication.a();
            long time = com.topfreegames.c.a.a().getTime();
            if (o.a().t()) {
                if (cVar == c.LASTDAY_BIKEPROMO) {
                    a(context, b.a(bikeRaceApplication, cVar.a()));
                } else if (cVar == c.JULY_4) {
                    a(context, b.c(bikeRaceApplication, cVar.a()));
                } else if (!l.a(time, bbVarA)) {
                    a(context, b.b(bikeRaceApplication, cVar.a()));
                }
            }
        }
    }

    private static void a(Context context, final String str) {
        final Context applicationContext = context.getApplicationContext();
        if (((BikeRaceApplication) context.getApplicationContext()).a(false).b()) {
            new Thread(new Runnable() { // from class: com.topfreegames.bikerace.worldcup.localnotification.AlarmReceiver.1
                @Override // java.lang.Runnable
                public void run() {
                    Notification notification = new Notification(2130837760, str, System.currentTimeMillis());
                    notification.flags |= 16;
                    Intent intent = new Intent(applicationContext, (Class<?>) MainActivity.class);
                    intent.setAction("android.intent.action.MAIN");
                    intent.addCategory("android.intent.category.LAUNCHER");
                    intent.putExtra("com.topfreegames.bikeraces.WorldCupShop", true);
                    int iRandom = (int) (Math.random() * 1.0E7d);
                    notification.setLatestEventInfo(applicationContext, "Bike Race", str, PendingIntent.getActivity(applicationContext, iRandom, intent, 0));
                    PowerManager.WakeLock wakeLockNewWakeLock = ((PowerManager) applicationContext.getSystemService("power")).newWakeLock(268435466, "");
                    wakeLockNewWakeLock.acquire();
                    ((NotificationManager) applicationContext.getSystemService("notification")).notify(iRandom, notification);
                    try {
                        Thread.sleep(5000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        wakeLockNewWakeLock.release();
                    }
                }
            }).start();
        }
    }
}

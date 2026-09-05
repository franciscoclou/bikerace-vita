package com.topfreegames.bikerace.localnotification.retention;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.activities.MainActivity;
import com.topfreegames.bikerace.bb;
import com.topfreegames.bikerace.z;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AlarmReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) context.getApplicationContext();
        bb bbVarA = bikeRaceApplication.a(false);
        if (bbVarA.b()) {
            z zVarA = bikeRaceApplication.a();
            String strC = bbVarA.c();
            if (strC == null) {
                int iQ = zVarA.q();
                if (iQ == 1) {
                    strC = context.getString(2131100017);
                } else if (iQ > 1) {
                    strC = context.getString(2131100016, Integer.valueOf(iQ));
                } else {
                    int iR = zVarA.r();
                    if (iR == 1) {
                        strC = context.getString(2131100019);
                    } else if (iR > 1) {
                        strC = context.getString(2131100018, Integer.valueOf(iR));
                    } else {
                        strC = context.getString(2131100020);
                    }
                }
            }
            a(bikeRaceApplication, strC);
        }
    }

    private static void a(Context context, final String str) {
        final Context applicationContext = context.getApplicationContext();
        if (((BikeRaceApplication) context.getApplicationContext()).a(false).b()) {
            new Thread(new Runnable() { // from class: com.topfreegames.bikerace.localnotification.retention.AlarmReceiver.1
                @Override // java.lang.Runnable
                public void run() {
                    Notification notification = new Notification(2130837760, str, System.currentTimeMillis());
                    notification.flags |= 16;
                    Intent intent = new Intent(applicationContext, (Class<?>) MainActivity.class);
                    intent.setAction("android.intent.action.MAIN");
                    intent.addCategory("android.intent.category.LAUNCHER");
                    intent.putExtra("com.topfreegames.bikerace.RetentionNotification", true);
                    intent.putExtra("com.topfreegames.bikerace.OpenMulti", false);
                    notification.setLatestEventInfo(applicationContext, "Bike Race", str, PendingIntent.getActivity(applicationContext, 1, intent, 0));
                    int iRandom = (int) (Math.random() * 1.0E7d);
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

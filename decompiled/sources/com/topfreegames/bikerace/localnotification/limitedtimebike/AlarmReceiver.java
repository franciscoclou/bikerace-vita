package com.topfreegames.bikerace.localnotification.limitedtimebike;

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
import com.topfreegames.bikerace.am;
import com.topfreegames.bikerace.ao;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AlarmReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Context applicationContext = context.getApplicationContext();
        String stringExtra = intent.getStringExtra("msg");
        int intExtra = intent.getIntExtra(ServerProtocol.DIALOG_PARAM_TYPE, -1);
        if (intExtra == b.HALLOWEEN_BIKE_EXPIRE.ordinal() && ((BikeRaceApplication) applicationContext).a().a(com.topfreegames.bikerace.c.HALLOWEEN) && am.b(applicationContext) && am.a(applicationContext)) {
            a(applicationContext, stringExtra);
        }
        if (intExtra == b.THANKSGIVING_BIKE_EXPIRE.ordinal() && ((BikeRaceApplication) applicationContext).a().a(com.topfreegames.bikerace.c.THANKSGIVING) && ao.b(applicationContext) && ao.a(applicationContext)) {
            a(applicationContext, stringExtra);
        }
    }

    private static void a(Context context, final String str) {
        final Context applicationContext = context.getApplicationContext();
        if (((BikeRaceApplication) context.getApplicationContext()).a(false).b()) {
            new Thread(new Runnable() { // from class: com.topfreegames.bikerace.localnotification.limitedtimebike.AlarmReceiver.1
                @Override // java.lang.Runnable
                public void run() {
                    Notification notification = new Notification(2130837760, str, System.currentTimeMillis());
                    notification.flags |= 16;
                    Intent intent = new Intent(applicationContext, (Class<?>) MainActivity.class);
                    intent.setAction("android.intent.action.MAIN");
                    intent.addCategory("android.intent.category.LAUNCHER");
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

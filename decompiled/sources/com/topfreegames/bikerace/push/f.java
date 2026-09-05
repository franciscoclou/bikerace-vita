package com.topfreegames.bikerace.push;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import com.topfreegames.bikerace.activities.MainActivity;

/* JADX INFO: compiled from: PushNotification.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class f {
    f() {
    }

    public static void a(Context context, String str) {
        Context applicationContext = context.getApplicationContext();
        Notification notification = new Notification(c.i(), str, System.currentTimeMillis());
        notification.flags |= 16;
        Intent intent = new Intent(applicationContext, (Class<?>) MainActivity.class);
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.putExtra("com.topfreegames.bikerace.OpenMulti", true);
        int iHashCode = str.hashCode();
        notification.setLatestEventInfo(applicationContext, "Bike Race", str, PendingIntent.getActivity(applicationContext, iHashCode, intent, 0));
        PowerManager.WakeLock wakeLockNewWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(268435466, f.class.getName());
        wakeLockNewWakeLock.acquire();
        ((NotificationManager) applicationContext.getSystemService("notification")).notify(iHashCode, notification);
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            wakeLockNewWakeLock.release();
        }
    }

    public static void a(Context context) {
        ((NotificationManager) context.getApplicationContext().getSystemService("notification")).cancelAll();
    }
}

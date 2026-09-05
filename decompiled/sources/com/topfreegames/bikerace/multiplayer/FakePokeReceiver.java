package com.topfreegames.bikerace.multiplayer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.activities.MainActivity;
import com.topfreegames.bikerace.ap;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class FakePokeReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        l lVarA;
        try {
            BikeRaceApplication bikeRaceApplication = (BikeRaceApplication) context.getApplicationContext();
            o oVarC = bikeRaceApplication.c();
            String stringExtra = intent.getStringExtra("com.topfreegames.bikerace.MultiplayerGameId");
            if (stringExtra != null && bikeRaceApplication.a(false).aw() && (lVarA = oVarC.a(stringExtra)) != null && b.a(lVarA)) {
                a(context, String.format(bikeRaceApplication.getResources().getString(2131100116), lVarA.g()), stringExtra);
            }
        } catch (Exception e) {
            if (ap.d()) {
                e.printStackTrace();
            }
        }
    }

    private void a(Context context, String str, String str2) {
        Context applicationContext = context.getApplicationContext();
        Notification notification = new Notification(2130837760, str, System.currentTimeMillis());
        notification.flags |= 16;
        Intent intent = new Intent(applicationContext, (Class<?>) MainActivity.class);
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.putExtra("com.topfreegames.bikerace.OpenMulti", true);
        notification.setLatestEventInfo(applicationContext, "Bike Race", str, PendingIntent.getActivity(applicationContext, 0, intent, 0));
        int iRandom = (int) (Math.random() * 1.0E7d);
        PowerManager.WakeLock wakeLockNewWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(268435466, "");
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
}

package com.topfreegames.bikerace.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/* JADX INFO: compiled from: PushNotificationWorldCupRedirector.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ah {
    public static void a(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("com.topfreegames.bikerace.worldcup.redirect", 0).edit();
        editorEdit.putBoolean("WorldCupOpen", true);
        editorEdit.commit();
    }

    public static boolean a(c cVar, Class<?> cls) {
        if (cVar == null) {
            throw new IllegalArgumentException("Activity cannot be null!");
        }
        SharedPreferences sharedPreferences = cVar.getSharedPreferences("com.topfreegames.bikerace.worldcup.redirect", 0);
        boolean z = sharedPreferences.getBoolean("WorldCupOpen", false);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putBoolean("WorldCupOpen", false);
        editorEdit.commit();
        boolean z2 = (!cls.equals(WorldCupShopActivity.class)) & z & (cls.equals(PlayActivity.class) ? false : true);
        if (z2) {
            Intent intent = new Intent();
            intent.setClass(cVar, WorldCupShopActivity.class);
            intent.putExtra("com.topfreegames.bikerace.ReturnToActivity", MainActivity.class);
            cVar.a(intent, 2130968587, 2130968583);
        }
        return z2;
    }
}

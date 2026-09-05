package com.topfreegames.bikerace.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/* JADX INFO: compiled from: PushNotificationMultiplayerRedirector.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ag {
    public static void a(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("com.topfreegames.bikerace.redirect", 0).edit();
        editorEdit.putBoolean("OpenMulti", true);
        editorEdit.commit();
    }

    public static boolean a(c cVar, Class<?> cls) {
        if (cVar == null) {
            throw new IllegalArgumentException("Activity cannot be null!");
        }
        SharedPreferences sharedPreferences = cVar.getSharedPreferences("com.topfreegames.bikerace.redirect", 0);
        boolean z = sharedPreferences.getBoolean("OpenMulti", false);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putBoolean("OpenMulti", false);
        editorEdit.commit();
        boolean z2 = (!cls.equals(MultiplayerMainActivity.class)) & z & (cls.equals(PlayActivity.class) ? false : true);
        if (z2) {
            Intent intent = new Intent();
            intent.setClass(cVar, MultiplayerMainActivity.class);
            intent.putExtra("com.topfreegames.bikerace.CallingActivity", cls);
            cVar.a(intent, 2130968587, 2130968583);
        }
        return z2;
    }
}

package com.google.ads;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.Date;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class at {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final com.google.ads.internal.a f628a = com.google.ads.internal.a.f669a.b();

    public static boolean a(final Context context, long j) {
        if (!a(context, j, PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()))) {
            return false;
        }
        new Thread(new Runnable() { // from class: com.google.ads.at.1
            @Override // java.lang.Runnable
            public void run() {
                SharedPreferences.Editor editorEdit = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).edit();
                editorEdit.putString("drt", "");
                editorEdit.putLong("drt_ts", 0L);
                editorEdit.commit();
            }
        }).start();
        return true;
    }

    static boolean a(Context context, long j, SharedPreferences sharedPreferences) {
        return (sharedPreferences.contains("drt") && sharedPreferences.contains("drt_ts") && sharedPreferences.getLong("drt_ts", 0L) >= new Date().getTime() - j) ? false : true;
    }

    public static void a(Activity activity) {
        new Thread(new bh(activity)).start();
    }
}

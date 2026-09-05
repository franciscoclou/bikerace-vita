package com.google.ads;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.util.Date;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class bh implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final WeakReference<Activity> f643a;
    private final SharedPreferences.Editor b;

    public bh(Activity activity) {
        this(activity, null);
    }

    bh(Activity activity, SharedPreferences.Editor editor) {
        this.f643a = new WeakReference<>(activity);
        this.b = editor;
    }

    private SharedPreferences.Editor a(Context context) {
        return this.b == null ? PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).edit() : this.b;
    }

    @Override // java.lang.Runnable
    public void run() {
        String string;
        try {
            Activity activity = this.f643a.get();
            if (activity == null) {
                com.google.ads.util.b.a("Activity was null while making a doritos cookie request.");
                return;
            }
            Cursor cursorQuery = activity.getContentResolver().query(as.f627a, as.b, null, null, null);
            if (cursorQuery != null && cursorQuery.moveToFirst() && cursorQuery.getColumnNames().length > 0) {
                string = cursorQuery.getString(cursorQuery.getColumnIndex(cursorQuery.getColumnName(0)));
            } else {
                com.google.ads.util.b.a("Google+ app not installed, not storing doritos cookie");
                string = null;
            }
            SharedPreferences.Editor editorA = a(activity);
            if (!TextUtils.isEmpty(string)) {
                editorA.putString("drt", string);
                editorA.putLong("drt_ts", new Date().getTime());
            } else {
                editorA.putString("drt", "");
                editorA.putLong("drt_ts", 0L);
            }
            editorA.commit();
        } catch (Throwable th) {
            com.google.ads.util.b.d("An unknown error occurred while sending a doritos request.", th);
        }
    }
}

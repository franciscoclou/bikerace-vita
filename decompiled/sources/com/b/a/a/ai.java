package com.b.a.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ai {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f257a;
    private final Context b;

    public ai(cl clVar) {
        if (clVar.w() == null) {
            throw new IllegalStateException("Cannot get directory before context has been set. Call Sdk.start() first");
        }
        this.b = clVar.w();
        this.f257a = clVar.getClass().getName();
    }

    public SharedPreferences a() {
        return this.b.getSharedPreferences(this.f257a, 0);
    }

    public SharedPreferences.Editor b() {
        return a().edit();
    }

    public boolean a(SharedPreferences.Editor editor) {
        if (Build.VERSION.SDK_INT < 9) {
            return editor.commit();
        }
        editor.apply();
        return true;
    }
}

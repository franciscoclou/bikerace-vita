package com.b.a;

import android.content.Context;
import android.graphics.BitmapFactory;
import com.b.a.a.cm;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class ac {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f316a;
    public final int b;
    public final int c;
    public final int d;

    private ac(String str, int i, int i2, int i3) {
        this.f316a = str;
        this.b = i;
        this.c = i2;
        this.d = i3;
    }

    public static ac a(Context context, String str) {
        if (str != null) {
            try {
                int iH = com.b.a.a.ba.h(context);
                cm.a().b().a("Crashlytics", "App icon resource ID is " + iH);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(context.getResources(), iH, options);
                return new ac(str, iH, options.outWidth, options.outHeight);
            } catch (Exception e) {
                cm.a().b().a("Crashlytics", "Failed to load icon", e);
            }
        }
        return null;
    }
}

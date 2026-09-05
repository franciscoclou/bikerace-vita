package com.applovin.impl.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.StrictMode;
import android.view.Display;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class aq {
    public static Point a(Activity activity) {
        Point point = new Point();
        point.x = 320;
        point.y = 240;
        try {
            Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
            if (Build.VERSION.SDK_INT >= 13) {
                defaultDisplay.getSize(point);
            } else {
                point.x = defaultDisplay.getWidth();
                point.y = defaultDisplay.getHeight();
            }
        } catch (Throwable th) {
        }
        return point;
    }

    static void a() {
        try {
            if (Build.VERSION.SDK_INT >= 9) {
                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
            }
        } catch (Throwable th) {
        }
    }

    public static boolean a(Class cls, Context context) {
        return context.getPackageManager().resolveActivity(new Intent(context, (Class<?>) cls), 0) != null;
    }

    public static boolean a(String str, Context context) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    public static boolean b() {
        return Build.VERSION.SDK_INT >= 15;
    }
}

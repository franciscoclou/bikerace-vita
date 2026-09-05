package com.topfreegames.bikerace.push;

import android.content.Context;
import android.content.SharedPreferences;
import com.topfreegames.bikerace.activities.BikeRaceApplication;

/* JADX INFO: compiled from: PushManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static a f1348a = null;

    public static void a(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        f.a(context);
    }

    public static void a(a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("Argument must not be null.");
        }
        if (c.b()) {
            f1348a = aVar;
        }
    }

    private static boolean e(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null.");
        }
        return c.b() && com.google.android.a.c.d(context) && com.google.android.a.c.f(context) && com.google.android.a.c.c(context) != "";
    }

    public static void b(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null.");
        }
        if (c.b()) {
            if (f1348a == null) {
                throw new IllegalStateException("Manager was not initializaed!");
            }
            if (!e(context)) {
                f(context);
            }
        }
    }

    public static void c(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null.");
        }
        if (c.b()) {
            if (f1348a == null) {
                throw new IllegalStateException("Manager was not initializaed!");
            }
            b(context, h(context), i(context));
            g(context);
        }
    }

    public static void a(Context context, String str) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null.");
        }
        if (str == null) {
            throw new IllegalArgumentException("User Id must not be null.");
        }
        if (c.b()) {
            if (f1348a == null) {
                throw new IllegalStateException("Manager was not initializaed!");
            }
            if (h(context) != str) {
                b(context, h(context), i(context));
            }
            d(context, str);
            a(context, str, false);
            if (c.b()) {
                if (!com.google.android.a.c.d(context) && com.google.android.a.c.c(context) == "") {
                    f(context);
                } else {
                    a(context, str, com.google.android.a.c.c(context));
                }
            }
        }
    }

    public static void b(Context context, String str) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null.");
        }
        if (str == null) {
            throw new IllegalArgumentException("User Id must not be null.");
        }
        if (c.b()) {
            if (f1348a == null) {
                throw new IllegalArgumentException("Manager was not initialized!");
            }
            b(context, str, i(context));
        }
    }

    static void c(Context context, String str) {
        String strH;
        if (c.b() && (strH = h(context)) != "") {
            a(context, strH, str);
        }
    }

    private static void f(Context context) {
        if (c.b()) {
            com.google.android.a.c.a(context, c.d());
        }
    }

    private static void g(Context context) {
        if (c.b()) {
            com.google.android.a.c.a(context);
        }
    }

    private static void d(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("com.topfreegames.bikerace.push", 0).edit();
        editorEdit.putString("curUser", str);
        editorEdit.commit();
    }

    private static String h(Context context) {
        return context.getSharedPreferences("com.topfreegames.bikerace.push", 0).getString("curUser", "");
    }

    private static void a(Context context, String str, boolean z) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("com.topfreegames.bikerace.push", 0).edit();
        editorEdit.putBoolean(str, z);
        editorEdit.commit();
    }

    private static void e(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("com.topfreegames.bikerace.push", 0).edit();
        editorEdit.remove(str);
        editorEdit.commit();
    }

    private static String i(Context context) {
        if (c.b()) {
            return com.google.android.a.c.c(context);
        }
        return null;
    }

    private static void a(Context context, String str, String str2) {
        if (f1348a == null) {
            ((BikeRaceApplication) context.getApplicationContext()).c();
        }
        if (c.b()) {
            if (f1348a == null) {
                throw new IllegalStateException("You must set the Application Server Register first.");
            }
            try {
                f1348a.a(str, str2);
                a(context, str, true);
            } catch (b e) {
                c(context, str, str2);
            }
        }
    }

    private static void b(Context context, String str, String str2) {
        if (c.b()) {
            if (f1348a == null) {
                throw new IllegalStateException("You must set the Application Server Register first.");
            }
            try {
                f1348a.b(str, str2);
                e(context, str);
            } catch (b e) {
                d(context, str, str2);
            }
        }
    }

    private static void c(Context context, String str, String str2) {
        if (c.a()) {
            System.out.println("PushRegister: will retry register for " + str + " (" + str2 + ")");
        }
    }

    private static void d(Context context, String str, String str2) {
        if (c.a()) {
            System.out.println("PushRegister: will retry unregister for " + str + " (" + str2 + ")");
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.topfreegames.bikerace.push", 0);
        String string = sharedPreferences.getString("pendUnreg", "");
        String str3 = String.valueOf(string) + string + str + "," + str2 + ";";
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putString("com.topfreegames.bikerace.push", str3);
        editorEdit.commit();
    }

    private static String[] j(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.topfreegames.bikerace.push", 0);
        String string = sharedPreferences.getString("pendUnreg", "");
        if (string == "") {
            return null;
        }
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        String strSubstring = string.substring(0, string.indexOf(";"));
        System.out.println(strSubstring);
        editorEdit.putString("com.topfreegames.bikerace.push", string.substring(string.indexOf(";") + 1));
        editorEdit.commit();
        return strSubstring.split(",");
    }

    static void d(Context context) {
        String[] strArrJ = j(context);
        if (strArrJ != null) {
            b(context, strArrJ[0], strArrJ[1]);
        }
    }
}

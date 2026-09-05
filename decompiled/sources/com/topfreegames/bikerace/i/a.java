package com.topfreegames.bikerace.i;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import java.util.Locale;

/* JADX INFO: compiled from: LocalizationHelper.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Locale f1265a = Locale.getDefault();
    private static Locale b = null;
    private static /* synthetic */ int[] c;

    static /* synthetic */ int[] d() {
        int[] iArr = c;
        if (iArr == null) {
            iArr = new int[b.valuesCustom().length];
            try {
                iArr[b.ENGLISH.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[b.JAPANESE.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[b.KOREAN.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            c = iArr;
        }
        return iArr;
    }

    public static void a(Context context) {
        a(((BikeRaceApplication) context.getApplicationContext()).a().h(), context);
    }

    public static void a(b bVar, Context context) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        switch (d()[bVar.ordinal()]) {
            case 1:
                configuration.locale = Locale.US;
                break;
            case 2:
                configuration.locale = Locale.KOREA;
                break;
            case 3:
                configuration.locale = Locale.JAPAN;
                break;
        }
        b = configuration.locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        if (((BikeRaceApplication) context.getApplicationContext()).a().h() != bVar) {
            ((BikeRaceApplication) context.getApplicationContext()).a().a(bVar);
        }
    }

    public static b a() {
        if (b != null) {
            if (b.equals(Locale.KOREA) || b.equals(Locale.KOREAN)) {
                return b.KOREAN;
            }
            if (b.equals(Locale.JAPAN) || b.equals(Locale.JAPANESE)) {
                return b.JAPANESE;
            }
        }
        return b.ENGLISH;
    }

    public static b b() {
        if (f1265a != null) {
            if (f1265a.equals(Locale.KOREA) || f1265a.equals(Locale.KOREAN)) {
                return b.KOREAN;
            }
            if (f1265a.equals(Locale.JAPAN) || f1265a.equals(Locale.JAPANESE)) {
                return b.JAPANESE;
            }
        }
        return b.ENGLISH;
    }

    public static void a(Context context, Configuration configuration) {
        if (b != null) {
            configuration.locale = b;
            Locale.setDefault(b);
            context.getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
        }
    }

    public static b c() {
        return a().equals(b.ENGLISH) ? b() : b.ENGLISH;
    }
}

package com.topfreegames.bikerace;

import android.content.Context;
import com.topfreegames.bikerace.activities.BikeRaceApplication;
import java.util.Locale;

/* JADX INFO: compiled from: LimitedTimeBikeManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ak {
    /* JADX INFO: Access modifiers changed from: private */
    public static String c(long j) {
        long time = j - com.topfreegames.c.a.a().getTime();
        if (time < 0) {
            return "Time expired";
        }
        long j2 = time / 86400000;
        long j3 = time - (86400000 * j2);
        long j4 = j3 / 3600000;
        long j5 = j3 - (3600000 * j4);
        long j6 = j5 / 60000;
        long j7 = (j5 - (60000 * j6)) / 1000;
        if (j2 > 0) {
            return String.format(Locale.US, "%dd %dh %dm %ds", Long.valueOf(j2), Long.valueOf(j4), Long.valueOf(j6), Long.valueOf(j7));
        }
        if (j4 > 0) {
            return String.format(Locale.US, "%dh %dm %ds", Long.valueOf(j4), Long.valueOf(j6), Long.valueOf(j7));
        }
        return j6 > 0 ? String.format(Locale.US, "%dm %ds", Long.valueOf(j6), Long.valueOf(j7)) : String.format(Locale.US, "%ds", Long.valueOf(j7));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long d(long j) {
        return j - com.topfreegames.c.a.a().getTime() < 0 ? -1L : 1000L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(Context context, c cVar, boolean z, long j, long j2) {
        if (!((BikeRaceApplication) context.getApplicationContext()).a().a(cVar)) {
            return true;
        }
        if (z) {
            return false;
        }
        long time = com.topfreegames.c.a.a().getTime();
        return time >= j && time < j2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(Context context, c cVar, boolean z, long j) {
        if (((BikeRaceApplication) context.getApplicationContext()).a().a(cVar)) {
            return z && j <= com.topfreegames.c.a.a().getTime();
        }
        return true;
    }
}

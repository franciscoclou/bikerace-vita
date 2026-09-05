package com.topfreegames.bikerace;

import android.content.Context;
import com.topfreegames.bikerace.activities.BikeRaceApplication;

/* JADX INFO: compiled from: LimitedTimeBikeManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class an {
    public static boolean a(Context context) {
        bb bbVarA = ((BikeRaceApplication) context.getApplicationContext()).a(false);
        return ak.b(context, c.SANTA, bbVarA.aa(), bbVarA.Q(), bbVarA.N());
    }

    public static boolean b(Context context) {
        bb bbVarA = ((BikeRaceApplication) context.getApplicationContext()).a(false);
        return ak.b(context, c.SANTA, bbVarA.ab(), bbVarA.Q());
    }

    public static long c(Context context) {
        return ak.d(((BikeRaceApplication) context.getApplicationContext()).a(false).N());
    }

    public static String d(Context context) {
        return ak.c(((BikeRaceApplication) context.getApplicationContext()).a(false).N());
    }
}

package com.topfreegames.bikerace;

import android.content.Context;
import com.topfreegames.bikerace.activities.BikeRaceApplication;

/* JADX INFO: compiled from: LimitedTimeBikeManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ao {
    public static boolean a(Context context) {
        bb bbVarA = ((BikeRaceApplication) context.getApplicationContext()).a(false);
        return ak.b(context, c.THANKSGIVING, bbVarA.Y(), bbVarA.M(), bbVarA.J());
    }

    public static boolean b(Context context) {
        bb bbVarA = ((BikeRaceApplication) context.getApplicationContext()).a(false);
        return ak.b(context, c.THANKSGIVING, bbVarA.Z(), bbVarA.M());
    }

    public static long c(Context context) {
        return ak.d(((BikeRaceApplication) context.getApplicationContext()).a(false).J());
    }

    public static String d(Context context) {
        return ak.c(((BikeRaceApplication) context.getApplicationContext()).a(false).J());
    }
}

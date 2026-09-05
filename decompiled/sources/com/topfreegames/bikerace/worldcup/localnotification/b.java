package com.topfreegames.bikerace.worldcup.localnotification;

import com.topfreegames.bikerace.activities.BikeRaceApplication;
import com.topfreegames.bikerace.worldcup.l;

/* JADX INFO: compiled from: LocalNotificationConfig.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b {
    public static String a(BikeRaceApplication bikeRaceApplication, com.topfreegames.bikerace.c cVar) {
        String strAu = bikeRaceApplication.a(false).au();
        return strAu.contains("%s") ? String.format(strAu, l.a(bikeRaceApplication, cVar)) : strAu;
    }

    public static String b(BikeRaceApplication bikeRaceApplication, com.topfreegames.bikerace.c cVar) {
        String strAt = bikeRaceApplication.a(false).at();
        return strAt.contains("%s") ? String.format(strAt, l.a(bikeRaceApplication, cVar)) : strAt;
    }

    public static String c(BikeRaceApplication bikeRaceApplication, com.topfreegames.bikerace.c cVar) {
        String string = bikeRaceApplication.getResources().getString(2131100021);
        return string.contains("%s") ? String.format(string, l.a(bikeRaceApplication, cVar)) : string;
    }
}

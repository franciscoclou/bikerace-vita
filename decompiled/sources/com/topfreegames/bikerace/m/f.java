package com.topfreegames.bikerace.m;

/* JADX INFO: compiled from: MultiplayerUtils.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class f {
    public static String a(String str) {
        if (str != null) {
            String[] strArrSplit = str.split(" ");
            if (strArrSplit.length > 1) {
                return String.format("%s %s.", strArrSplit[0], Character.valueOf(strArrSplit[strArrSplit.length - 1].charAt(0)));
            }
            return str;
        }
        return str;
    }
}

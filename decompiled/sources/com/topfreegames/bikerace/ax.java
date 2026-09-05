package com.topfreegames.bikerace;

/* JADX INFO: compiled from: MainConfig.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class ax {
    public static String a() {
        if (ap.d()) {
            return "http://s3.staging-topfreegames.com/bikerace/android_debug.xml";
        }
        if (ap.t()) {
            return "http://s3.topfreegames.com.s3.amazonaws.com/bikerace/android_prod_pro.xml";
        }
        return "http://s3.topfreegames.com.s3.amazonaws.com/bikerace/android_prod_3_1.xml";
    }
}

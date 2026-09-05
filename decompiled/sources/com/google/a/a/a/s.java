package com.google.a.a.a;

import android.os.Build;
import java.io.File;

/* JADX INFO: compiled from: FutureApis.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class s {
    public static int a() {
        try {
            return Integer.parseInt(Build.VERSION.SDK);
        } catch (NumberFormatException e) {
            ah.a("Invalid version number: " + Build.VERSION.SDK);
            return 0;
        }
    }

    static boolean a(String str) {
        if (a() < 9) {
            return false;
        }
        File file = new File(str);
        file.setReadable(false, false);
        file.setWritable(false, false);
        file.setReadable(true, true);
        file.setWritable(true, true);
        return true;
    }
}

package com.topfreegames.c;

import android.os.SystemClock;
import com.facebook.widget.PlacePickerFragment;
import java.util.Date;

/* JADX INFO: compiled from: NtpTime.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static b f1488a = null;

    /* JADX WARN: Code duplicated, block: B:17:0x0048 A[Catch: Exception -> 0x0060, all -> 0x006a, TRY_ENTER, TRY_LEAVE, TryCatch #0 {Exception -> 0x0060, blocks: (B:4:0x0003, B:6:0x0007, B:8:0x001a, B:10:0x0026, B:12:0x0032, B:14:0x003e, B:17:0x0048), top: B:27:0x0003, outer: #1 }] */
    public static synchronized Date a() {
        Date date;
        try {
            if (f1488a == null) {
                f1488a = new b();
                if (!f1488a.a("0.pool.ntp.org", PlacePickerFragment.DEFAULT_RADIUS_IN_METERS) && !f1488a.a("1.pool.ntp.org", PlacePickerFragment.DEFAULT_RADIUS_IN_METERS) && !f1488a.a("2.pool.ntp.org", PlacePickerFragment.DEFAULT_RADIUS_IN_METERS) && !f1488a.a("3.pool.ntp.org", PlacePickerFragment.DEFAULT_RADIUS_IN_METERS)) {
                    f1488a = null;
                    date = new Date();
                } else {
                    date = new Date((f1488a.a() + SystemClock.elapsedRealtime()) - f1488a.b());
                }
            } else {
                date = new Date((f1488a.a() + SystemClock.elapsedRealtime()) - f1488a.b());
            }
        } catch (Exception e) {
            f1488a = null;
            date = new Date();
        }
        return date;
    }
}

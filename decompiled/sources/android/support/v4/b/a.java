package android.support.v4.b;

import android.os.Build;
import android.os.Parcelable;

/* JADX INFO: compiled from: ParcelableCompat.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {
    public static <T> Parcelable.Creator<T> a(c<T> cVar) {
        if (Build.VERSION.SDK_INT >= 13) {
            e.a(cVar);
        }
        return new b(cVar);
    }
}

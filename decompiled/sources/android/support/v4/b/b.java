package android.support.v4.b;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: ParcelableCompat.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class b<T> implements Parcelable.Creator<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c<T> f23a;

    public b(c<T> cVar) {
        this.f23a = cVar;
    }

    @Override // android.os.Parcelable.Creator
    public T createFromParcel(Parcel parcel) {
        return this.f23a.a(parcel, null);
    }

    @Override // android.os.Parcelable.Creator
    public T[] newArray(int i) {
        return this.f23a.a(i);
    }
}

package android.support.v4.b;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: ParcelableCompatHoneycombMR2.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class d<T> implements Parcelable.ClassLoaderCreator<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c<T> f24a;

    public d(c<T> cVar) {
        this.f24a = cVar;
    }

    @Override // android.os.Parcelable.Creator
    public T createFromParcel(Parcel parcel) {
        return this.f24a.a(parcel, null);
    }

    @Override // android.os.Parcelable.ClassLoaderCreator
    public T createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return this.f24a.a(parcel, classLoader);
    }

    @Override // android.os.Parcelable.Creator
    public T[] newArray(int i) {
        return this.f24a.a(i);
    }
}

package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: compiled from: BackStackRecord.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new Parcelable.Creator<BackStackState>() { // from class: android.support.v4.app.BackStackState.1
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BackStackState createFromParcel(Parcel parcel) {
            return new BackStackState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BackStackState[] newArray(int i) {
            return new BackStackState[i];
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final int[] f5a;
    final int b;
    final int c;
    final String d;
    final int e;
    final int f;
    final CharSequence g;
    final int h;
    final CharSequence i;

    public BackStackState(k kVar, b bVar) {
        int size = 0;
        for (c cVar = bVar.b; cVar != null; cVar = cVar.f13a) {
            if (cVar.i != null) {
                size += cVar.i.size();
            }
        }
        this.f5a = new int[size + (bVar.d * 7)];
        if (!bVar.k) {
            throw new IllegalStateException("Not on back stack");
        }
        int i = 0;
        for (c cVar2 = bVar.b; cVar2 != null; cVar2 = cVar2.f13a) {
            int i2 = i + 1;
            this.f5a[i] = cVar2.c;
            int i3 = i2 + 1;
            this.f5a[i2] = cVar2.d != null ? cVar2.d.mIndex : -1;
            int i4 = i3 + 1;
            this.f5a[i3] = cVar2.e;
            int i5 = i4 + 1;
            this.f5a[i4] = cVar2.f;
            int i6 = i5 + 1;
            this.f5a[i5] = cVar2.g;
            int i7 = i6 + 1;
            this.f5a[i6] = cVar2.h;
            if (cVar2.i != null) {
                int size2 = cVar2.i.size();
                int i8 = i7 + 1;
                this.f5a[i7] = size2;
                int i9 = 0;
                while (i9 < size2) {
                    this.f5a[i8] = cVar2.i.get(i9).mIndex;
                    i9++;
                    i8++;
                }
                i = i8;
            } else {
                i = i7 + 1;
                this.f5a[i7] = 0;
            }
        }
        this.b = bVar.i;
        this.c = bVar.j;
        this.d = bVar.m;
        this.e = bVar.o;
        this.f = bVar.p;
        this.g = bVar.q;
        this.h = bVar.r;
        this.i = bVar.s;
    }

    public BackStackState(Parcel parcel) {
        this.f5a = parcel.createIntArray();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readString();
        this.e = parcel.readInt();
        this.f = parcel.readInt();
        this.g = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.h = parcel.readInt();
        this.i = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
    }

    public b a(k kVar) {
        b bVar = new b(kVar);
        int i = 0;
        int i2 = 0;
        while (i2 < this.f5a.length) {
            c cVar = new c();
            int i3 = i2 + 1;
            cVar.c = this.f5a[i2];
            if (k.f17a) {
                Log.v("FragmentManager", "Instantiate " + bVar + " op #" + i + " base fragment #" + this.f5a[i3]);
            }
            int i4 = i3 + 1;
            int i5 = this.f5a[i3];
            if (i5 >= 0) {
                cVar.d = kVar.f.get(i5);
            } else {
                cVar.d = null;
            }
            int i6 = i4 + 1;
            cVar.e = this.f5a[i4];
            int i7 = i6 + 1;
            cVar.f = this.f5a[i6];
            int i8 = i7 + 1;
            cVar.g = this.f5a[i7];
            int i9 = i8 + 1;
            cVar.h = this.f5a[i8];
            int i10 = i9 + 1;
            int i11 = this.f5a[i9];
            if (i11 > 0) {
                cVar.i = new ArrayList<>(i11);
                int i12 = 0;
                while (i12 < i11) {
                    if (k.f17a) {
                        Log.v("FragmentManager", "Instantiate " + bVar + " set remove fragment #" + this.f5a[i10]);
                    }
                    cVar.i.add(kVar.f.get(this.f5a[i10]));
                    i12++;
                    i10++;
                }
            }
            bVar.a(cVar);
            i++;
            i2 = i10;
        }
        bVar.i = this.b;
        bVar.j = this.c;
        bVar.m = this.d;
        bVar.o = this.e;
        bVar.k = true;
        bVar.p = this.f;
        bVar.q = this.g;
        bVar.r = this.h;
        bVar.s = this.i;
        bVar.a(1);
        return bVar;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.f5a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeString(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        TextUtils.writeToParcel(this.g, parcel, 0);
        parcel.writeInt(this.h);
        TextUtils.writeToParcel(this.i, parcel, 0);
    }
}

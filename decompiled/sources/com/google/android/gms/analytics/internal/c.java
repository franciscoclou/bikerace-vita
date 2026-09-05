package com.google.android.gms.analytics.internal;

import android.os.IBinder;
import android.os.Parcel;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: IAnalyticsService.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class c implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private IBinder f734a;

    c(IBinder iBinder) {
        this.f734a = iBinder;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.f734a;
    }

    @Override // com.google.android.gms.analytics.internal.a
    public void a(Map map, long j, String str, List<Command> list) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.google.android.gms.analytics.internal.IAnalyticsService");
            parcelObtain.writeMap(map);
            parcelObtain.writeLong(j);
            parcelObtain.writeString(str);
            parcelObtain.writeTypedList(list);
            this.f734a.transact(1, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // com.google.android.gms.analytics.internal.a
    public void a() {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.google.android.gms.analytics.internal.IAnalyticsService");
            this.f734a.transact(2, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }
}

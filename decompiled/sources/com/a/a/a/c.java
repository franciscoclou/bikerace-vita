package com.a.a.a;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* JADX INFO: compiled from: IMarketBillingService.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class c implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private IBinder f74a;

    c(IBinder iBinder) {
        this.f74a = iBinder;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.f74a;
    }

    @Override // com.a.a.a.a
    public Bundle a(Bundle bundle) {
        Bundle bundle2;
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.android.vending.billing.IMarketBillingService");
            if (bundle != null) {
                parcelObtain.writeInt(1);
                bundle.writeToParcel(parcelObtain, 0);
            } else {
                parcelObtain.writeInt(0);
            }
            this.f74a.transact(1, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            if (parcelObtain2.readInt() != 0) {
                bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(parcelObtain2);
            } else {
                bundle2 = null;
            }
            return bundle2;
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }
}

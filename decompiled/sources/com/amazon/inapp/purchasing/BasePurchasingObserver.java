package com.amazon.inapp.purchasing;

import android.content.Context;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class BasePurchasingObserver extends PurchasingObserver {
    public BasePurchasingObserver(Context context) {
        super(context);
    }

    @Override // com.amazon.inapp.purchasing.PurchasingObserver
    public void onGetUserIdResponse(GetUserIdResponse getUserIdResponse) {
    }

    @Override // com.amazon.inapp.purchasing.PurchasingObserver
    public void onItemDataResponse(ItemDataResponse itemDataResponse) {
    }

    @Override // com.amazon.inapp.purchasing.PurchasingObserver
    public void onPurchaseResponse(PurchaseResponse purchaseResponse) {
    }

    @Override // com.amazon.inapp.purchasing.PurchasingObserver
    public void onPurchaseUpdatesResponse(PurchaseUpdatesResponse purchaseUpdatesResponse) {
    }

    @Override // com.amazon.inapp.purchasing.PurchasingObserver
    public void onSdkAvailable(boolean z) {
    }
}

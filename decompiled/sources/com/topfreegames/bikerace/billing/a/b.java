package com.topfreegames.bikerace.billing.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.amazon.inapp.purchasing.BasePurchasingObserver;
import com.amazon.inapp.purchasing.GetUserIdResponse;
import com.amazon.inapp.purchasing.ItemDataResponse;
import com.amazon.inapp.purchasing.Offset;
import com.amazon.inapp.purchasing.PurchaseResponse;
import com.amazon.inapp.purchasing.PurchaseUpdatesResponse;
import com.amazon.inapp.purchasing.PurchasingManager;
import com.amazon.inapp.purchasing.Receipt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: AmazonBillingManager.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class b extends BasePurchasingObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ a f1141a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(a aVar, Context context) {
        super(context);
        this.f1141a = aVar;
    }

    @Override // com.amazon.inapp.purchasing.BasePurchasingObserver, com.amazon.inapp.purchasing.PurchasingObserver
    public void onGetUserIdResponse(GetUserIdResponse getUserIdResponse) {
        super.onGetUserIdResponse(getUserIdResponse);
    }

    @Override // com.amazon.inapp.purchasing.BasePurchasingObserver, com.amazon.inapp.purchasing.PurchasingObserver
    public void onItemDataResponse(ItemDataResponse itemDataResponse) {
        super.onItemDataResponse(itemDataResponse);
    }

    @Override // com.amazon.inapp.purchasing.BasePurchasingObserver, com.amazon.inapp.purchasing.PurchasingObserver
    public void onPurchaseResponse(final PurchaseResponse purchaseResponse) {
        new Thread(new Runnable() { // from class: com.topfreegames.bikerace.billing.a.b.1
            private static /* synthetic */ int[] c;

            static /* synthetic */ int[] a() {
                int[] iArr = c;
                if (iArr == null) {
                    iArr = new int[PurchaseResponse.PurchaseRequestStatus.values().length];
                    try {
                        iArr[PurchaseResponse.PurchaseRequestStatus.ALREADY_ENTITLED.ordinal()] = 4;
                    } catch (NoSuchFieldError e) {
                    }
                    try {
                        iArr[PurchaseResponse.PurchaseRequestStatus.FAILED.ordinal()] = 2;
                    } catch (NoSuchFieldError e2) {
                    }
                    try {
                        iArr[PurchaseResponse.PurchaseRequestStatus.INVALID_SKU.ordinal()] = 3;
                    } catch (NoSuchFieldError e3) {
                    }
                    try {
                        iArr[PurchaseResponse.PurchaseRequestStatus.SUCCESSFUL.ordinal()] = 1;
                    } catch (NoSuchFieldError e4) {
                    }
                    c = iArr;
                }
                return iArr;
            }

            @Override // java.lang.Runnable
            public void run() {
                switch (a()[purchaseResponse.getPurchaseRequestStatus().ordinal()]) {
                    case 1:
                        String sku = purchaseResponse.getReceipt().getSku();
                        int iA = b.this.f1141a.b.a(sku) + 1;
                        b.this.f1141a.b.a(sku, iA);
                        if (b.this.f1141a.f1139a != null) {
                            b.this.f1141a.f1139a.a(sku, iA);
                        }
                        break;
                    case 2:
                        if (b.this.f1141a.f1139a != null) {
                            b.this.f1141a.f1139a.h();
                        }
                        break;
                    case 3:
                        Log.e("AmazonBilling", "Invalid SKU");
                        break;
                }
            }
        }).start();
    }

    @Override // com.amazon.inapp.purchasing.BasePurchasingObserver, com.amazon.inapp.purchasing.PurchasingObserver
    public void onPurchaseUpdatesResponse(final PurchaseUpdatesResponse purchaseUpdatesResponse) {
        new Thread(new Runnable() { // from class: com.topfreegames.bikerace.billing.a.b.2
            @Override // java.lang.Runnable
            public void run() {
                if (purchaseUpdatesResponse.getPurchaseUpdatesRequestStatus() == PurchaseUpdatesResponse.PurchaseUpdatesRequestStatus.SUCCESSFUL) {
                    Set<String> revokedSkus = purchaseUpdatesResponse.getRevokedSkus();
                    Set<Receipt> receipts = purchaseUpdatesResponse.getReceipts();
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    Iterator<Receipt> it = receipts.iterator();
                    while (it.hasNext()) {
                        String sku = it.next().getSku();
                        arrayList.add(sku);
                        b.this.f1141a.b.a(sku, b.this.f1141a.b.a(sku) + 1);
                    }
                    if (b.this.f1141a.f1139a != null) {
                        b.this.f1141a.f1139a.a(arrayList);
                    }
                    for (String str : revokedSkus) {
                        if (!arrayList.contains(str)) {
                            b.this.f1141a.b.a(str, 0);
                            arrayList2.add(str);
                        }
                    }
                    if (b.this.f1141a.f1139a != null) {
                        b.this.f1141a.f1139a.b(arrayList2);
                    }
                    Offset offset = purchaseUpdatesResponse.getOffset();
                    SharedPreferences.Editor editorEdit = b.this.f1141a.d.edit();
                    editorEdit.putString("offset", offset.toString());
                    editorEdit.commit();
                    if (purchaseUpdatesResponse.isMore()) {
                        PurchasingManager.initiatePurchaseUpdatesRequest(offset);
                        return;
                    }
                    return;
                }
                Log.e("AmazonBilling", "Could not restore");
            }
        }).start();
    }

    @Override // com.amazon.inapp.purchasing.BasePurchasingObserver, com.amazon.inapp.purchasing.PurchasingObserver
    public void onSdkAvailable(boolean z) {
        super.onSdkAvailable(z);
    }
}

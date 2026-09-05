package com.amazon.inapp.purchasing;

import android.content.Intent;
import android.os.Bundle;
import java.util.Collection;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class SandboxRequestHandler implements RequestHandler {
    private static final String TAG = "SandboxRequestHandler";

    /* JADX INFO: renamed from: com.amazon.inapp.purchasing.SandboxRequestHandler$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ String val$location;
        final /* synthetic */ String val$sku;

        AnonymousClass1(String str, String str2) {
            this.val$sku = str;
            this.val$location = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (Logger.isTraceOn()) {
                Logger.trace(SandboxRequestHandler.TAG, "Running runnable for sentContentDonwloadRequest with sku " + this.val$sku + " and location " + this.val$location);
            }
            if (PurchasingManager.getPurchasingObserver() != null) {
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.inapp.purchasing.SandboxRequestHandler$2, reason: invalid class name */
    class AnonymousClass2 implements Runnable {
        final /* synthetic */ String val$requestId;
        final /* synthetic */ PurchaseResponse val$response;
        final /* synthetic */ String val$sku;

        AnonymousClass2(String str, String str2, PurchaseResponse purchaseResponse) {
            this.val$requestId = str;
            this.val$sku = str2;
            this.val$response = purchaseResponse;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (Logger.isTraceOn()) {
                Logger.trace(SandboxRequestHandler.TAG, "Running Runnable for sendPurchaseRequest with requestId: " + this.val$requestId + " sku: " + this.val$sku);
            }
            PurchasingObserver purchasingObserver = PurchasingManager.getPurchasingObserver();
            if (purchasingObserver != null) {
                purchasingObserver.onPurchaseResponse(this.val$response);
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.inapp.purchasing.SandboxRequestHandler$3, reason: invalid class name */
    class AnonymousClass3 implements Runnable {
        final /* synthetic */ ItemDataResponse val$response;
        final /* synthetic */ Set val$skus;

        AnonymousClass3(Set set, ItemDataResponse itemDataResponse) {
            this.val$skus = set;
            this.val$response = itemDataResponse;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (Logger.isTraceOn()) {
                Logger.trace(SandboxRequestHandler.TAG, "Running Runnable for sendItemDataRequest with SKUs: " + this.val$skus);
            }
            PurchasingObserver purchasingObserver = PurchasingManager.getPurchasingObserver();
            if (purchasingObserver != null) {
                purchasingObserver.onItemDataResponse(this.val$response);
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.inapp.purchasing.SandboxRequestHandler$4, reason: invalid class name */
    class AnonymousClass4 implements Runnable {
        final /* synthetic */ String val$location;
        final /* synthetic */ ContentDownloadResponse val$response;
        final /* synthetic */ String val$sku;

        AnonymousClass4(String str, String str2, ContentDownloadResponse contentDownloadResponse) {
            this.val$sku = str;
            this.val$location = str2;
            this.val$response = contentDownloadResponse;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (Logger.isTraceOn()) {
                Logger.trace(SandboxRequestHandler.TAG, "Running runnable for sentContentDonwloadRequest with sku " + this.val$sku + " and location " + this.val$location);
            }
            PurchasingObserver purchasingObserver = PurchasingManager.getPurchasingObserver();
            if (purchasingObserver != null) {
                purchasingObserver.onContentDownloadResponse(this.val$response);
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.inapp.purchasing.SandboxRequestHandler$5, reason: invalid class name */
    class AnonymousClass5 implements Runnable {
        final /* synthetic */ Offset val$offset;
        final /* synthetic */ PurchaseUpdatesResponse val$response;

        AnonymousClass5(Offset offset, PurchaseUpdatesResponse purchaseUpdatesResponse) {
            this.val$offset = offset;
            this.val$response = purchaseUpdatesResponse;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (Logger.isTraceOn()) {
                Logger.trace(SandboxRequestHandler.TAG, "Running runnable for sendPurchaseUpdatesRequest with offset " + this.val$offset.toString());
            }
            PurchasingObserver purchasingObserver = PurchasingManager.getPurchasingObserver();
            if (purchasingObserver != null) {
                purchasingObserver.onPurchaseUpdatesResponse(this.val$response);
            }
        }
    }

    SandboxRequestHandler() {
    }

    @Override // com.amazon.inapp.purchasing.RequestHandler
    public void sendContentDownloadRequest(String str, String str2, String str3) {
    }

    @Override // com.amazon.inapp.purchasing.RequestHandler
    public void sendGetUserIdRequest(String str) {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "sendGetUserIdRequest");
        }
        try {
            Bundle bundle = new Bundle();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("requestId", str);
            jSONObject.put("packageName", PurchasingManager.getObserverContext().getPackageName());
            bundle.putString("userInput", jSONObject.toString());
            Intent intent = new Intent("com.amazon.testclient.iap.appUserId");
            intent.addFlags(268435456);
            intent.putExtras(bundle);
            PurchasingManager.getObserverContext().startService(intent);
        } catch (JSONException e) {
            Logger.error(TAG, "Error in sendGetUserIdRequest.");
        }
    }

    @Override // com.amazon.inapp.purchasing.RequestHandler
    public void sendItemDataRequest(Set<String> set, String str) {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "sendItemDataRequest");
        }
        try {
            Bundle bundle = new Bundle();
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray((Collection) set);
            jSONObject.put("requestId", str);
            jSONObject.put("packageName", PurchasingManager.getObserverContext().getPackageName());
            jSONObject.put("skus", jSONArray);
            bundle.putString("itemDataInput", jSONObject.toString());
            Intent intent = new Intent("com.amazon.testclient.iap.itemData");
            intent.addFlags(268435456);
            intent.putExtras(bundle);
            PurchasingManager.getObserverContext().startService(intent);
        } catch (JSONException e) {
            Logger.error(TAG, "Error in sendItemDataRequest.");
        }
    }

    @Override // com.amazon.inapp.purchasing.RequestHandler
    public void sendPurchaseRequest(String str, String str2) {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "sendPurchaseRequest");
        }
        try {
            Bundle bundle = new Bundle();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("sku", str);
            jSONObject.put("requestId", str2);
            jSONObject.put("packageName", PurchasingManager.getObserverContext().getPackageName());
            bundle.putString("purchaseInput", jSONObject.toString());
            Intent intent = new Intent("com.amazon.testclient.iap.purchase");
            intent.addFlags(268435456);
            intent.putExtras(bundle);
            PurchasingManager.getObserverContext().startService(intent);
        } catch (JSONException e) {
            Logger.error(TAG, "Error in sendPurchaseRequest.");
        }
    }

    @Override // com.amazon.inapp.purchasing.RequestHandler
    public void sendPurchaseResponseReceivedRequest(String str) {
    }

    @Override // com.amazon.inapp.purchasing.RequestHandler
    public void sendPurchaseUpdatesRequest(Offset offset, String str) {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "sendPurchaseUpdatesRequest");
        }
        try {
            Bundle bundle = new Bundle();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("requestId", str);
            jSONObject.put("packageName", PurchasingManager.getObserverContext().getPackageName());
            jSONObject.put("offset", offset.toString());
            bundle.putString("purchaseUpdatesInput", jSONObject.toString());
            Intent intent = new Intent("com.amazon.testclient.iap.purchaseUpdates");
            intent.addFlags(268435456);
            intent.putExtras(bundle);
            PurchasingManager.getObserverContext().startService(intent);
        } catch (JSONException e) {
            Logger.error(TAG, "Error in sendPurchaseUpdatesRequest.");
        }
    }
}

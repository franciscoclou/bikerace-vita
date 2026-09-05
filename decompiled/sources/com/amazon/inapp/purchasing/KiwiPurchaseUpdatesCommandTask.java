package com.amazon.inapp.purchasing;

import com.amazon.venezia.command.SuccessResult;
import java.util.HashSet;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class KiwiPurchaseUpdatesCommandTask extends KiwiBaseCommandTask {
    private static final String COMMAND_NAME = "purchase_updates";
    private static final String COMMAND_VERSION = "1.0";
    private static final String TAG = "KiwiPurchaseUpdatesCommandTask";
    private final Offset _offset;

    KiwiPurchaseUpdatesCommandTask(Offset offset, String str) {
        super(COMMAND_NAME, "1.0", str);
        this._offset = offset;
        addCommandData("cursor", Offset.BEGINNING.equals(this._offset) ? null : this._offset.toString());
    }

    private PurchaseUpdatesResponse getFailedResponse() {
        return new PurchaseUpdatesResponse(getRequestId(), null, PurchaseUpdatesResponse.PurchaseUpdatesRequestStatus.FAILED, null, null, this._offset, false);
    }

    private void notifyObserver(final PurchaseUpdatesResponse purchaseUpdatesResponse) {
        postRunnableToMainLooper(new Runnable() { // from class: com.amazon.inapp.purchasing.KiwiPurchaseUpdatesCommandTask.1
            @Override // java.lang.Runnable
            public void run() {
                PurchasingObserver purchasingObserver = PurchasingManager.getPurchasingObserver();
                if (purchasingObserver != null) {
                    if (Logger.isTraceOn()) {
                        Logger.trace(KiwiPurchaseUpdatesCommandTask.TAG, "Invoking onPurchaseUpdatesResponse with " + purchaseUpdatesResponse);
                    }
                    purchasingObserver.onPurchaseUpdatesResponse(purchaseUpdatesResponse);
                }
            }
        });
    }

    protected void onSuccess(SuccessResult successResult) {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "onSuccess");
        }
        Map data = successResult.getData();
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "data: " + data);
        }
        String str = (String) data.get("errorMessage");
        String str2 = (String) data.get("userId");
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "onSuccess: errorMessage: \"" + str + "\"");
        }
        if (isNullOrEmpty(str)) {
            try {
                HashSet hashSet = new HashSet();
                HashSet hashSet2 = new HashSet();
                JSONArray jSONArray = new JSONArray((String) data.get("receipts"));
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    Receipt receiptFromReceiptJson = getReceiptFromReceiptJson(jSONObject);
                    if (verifyReceipt(str2, receiptFromReceiptJson, jSONObject)) {
                        hashSet.add(receiptFromReceiptJson);
                    }
                }
                JSONArray jSONArray2 = new JSONArray((String) data.get("revocations"));
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    hashSet2.add(jSONArray2.getString(i2));
                }
                String str3 = (String) data.get("cursor");
                notifyObserver(new PurchaseUpdatesResponse(getRequestId(), str2, PurchaseUpdatesResponse.PurchaseUpdatesRequestStatus.SUCCESSFUL, hashSet, hashSet2, isNullOrEmpty(str3) ? Offset.BEGINNING : Offset.fromString(str3), "true".equalsIgnoreCase((String) data.get("hasMore"))));
            } catch (JSONException e) {
                if (Logger.isErrorOn()) {
                    Logger.error(TAG, "Error parsing purchase updates JSON: " + e.getMessage());
                }
                sendFailedResponse();
            }
        }
    }

    @Override // com.amazon.inapp.purchasing.KiwiBaseCommandTask
    protected void sendFailedResponse() {
        notifyObserver(getFailedResponse());
    }
}

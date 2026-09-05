package com.amazon.inapp.purchasing;

import com.amazon.venezia.command.SuccessResult;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class KiwiGetItemDataRequestCommandTask extends KiwiBaseCommandTask {
    private static final String COMMAND_NAME = "getItem_data";
    private static final String COMMAND_VERSION = "1.0";
    private static final String TAG = "KiwiGetItemDataRequestCommandTask";
    private final Set<String> _skus;

    KiwiGetItemDataRequestCommandTask(Set<String> set, String str) {
        super(COMMAND_NAME, "1.0", str);
        this._skus = set;
        addCommandData("skus", this._skus);
    }

    private ItemDataResponse getFailedResponse() {
        return new ItemDataResponse(getRequestId(), null, ItemDataResponse.ItemDataRequestStatus.FAILED, null);
    }

    private void notifyObserver(final ItemDataResponse itemDataResponse) {
        postRunnableToMainLooper(new Runnable() { // from class: com.amazon.inapp.purchasing.KiwiGetItemDataRequestCommandTask.1
            @Override // java.lang.Runnable
            public void run() {
                PurchasingObserver purchasingObserver = PurchasingManager.getPurchasingObserver();
                if (purchasingObserver != null) {
                    if (Logger.isTraceOn()) {
                        Logger.trace(KiwiGetItemDataRequestCommandTask.TAG, "Invoking onItemDataResponse with " + itemDataResponse);
                    }
                    purchasingObserver.onItemDataResponse(itemDataResponse);
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
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "onSuccess: errorMessage: \"" + str + "\"");
        }
        if (!isNullOrEmpty(str)) {
            if (Logger.isTraceOn()) {
                Logger.trace(TAG, "found error message: " + str);
            }
            sendFailedResponse();
            return;
        }
        HashSet hashSet = new HashSet();
        HashMap map = new HashMap();
        for (String str2 : this._skus) {
            if (data.containsKey(str2)) {
                try {
                    JSONObject jSONObject = new JSONObject((String) data.get(str2));
                    map.put(str2, new Item(str2, jSONObject.optString("price"), Item.ItemType.valueOf(jSONObject.getString("itemType")), jSONObject.getString("title"), jSONObject.getString("description"), jSONObject.getString("iconUrl")));
                } catch (JSONException e) {
                    hashSet.add(str2);
                    if (Logger.isErrorOn()) {
                        Logger.error(TAG, "Error parsing JSON for SKU " + str2 + ": " + e.getMessage());
                    }
                }
            } else {
                hashSet.add(str2);
            }
        }
        notifyObserver(new ItemDataResponse(getRequestId(), hashSet, hashSet.isEmpty() ? ItemDataResponse.ItemDataRequestStatus.SUCCESSFUL : ItemDataResponse.ItemDataRequestStatus.SUCCESSFUL_WITH_UNAVAILABLE_SKUS, map));
    }

    @Override // com.amazon.inapp.purchasing.KiwiBaseCommandTask
    protected void sendFailedResponse() {
        notifyObserver(getFailedResponse());
    }
}

package com.amazon.inapp.purchasing;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class SandboxResponseHandler implements ResponseHandler {
    private static final String TAG = "SandboxResponseHandler";
    private final HandlerAdapter _handler = HandlerManager.getMainHandlerAdapter();

    SandboxResponseHandler() {
    }

    private Item getItem(String str, JSONObject jSONObject) {
        return new Item(str, jSONObject.optString("price"), Item.ItemType.valueOf(jSONObject.optString("itemType")), jSONObject.optString("title"), jSONObject.optString("description"), jSONObject.optString("smallIconUrl"));
    }

    private ItemDataResponse getItemDataResponse(Intent intent) {
        Exception exc;
        HashSet hashSet;
        ItemDataResponse.ItemDataRequestStatus itemDataRequestStatus;
        String str;
        HashSet hashSet2;
        HashMap map = null;
        ItemDataResponse.ItemDataRequestStatus itemDataRequestStatusValueOf = ItemDataResponse.ItemDataRequestStatus.FAILED;
        try {
            JSONObject jSONObject = new JSONObject(intent.getStringExtra("itemDataOutput"));
            String strOptString = jSONObject.optString("requestId");
            try {
                itemDataRequestStatusValueOf = ItemDataResponse.ItemDataRequestStatus.valueOf(jSONObject.optString("status"));
                if (itemDataRequestStatusValueOf != ItemDataResponse.ItemDataRequestStatus.FAILED) {
                    hashSet = new HashSet();
                    try {
                        HashMap map2 = new HashMap();
                        try {
                            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("unavailableSkus");
                            if (jSONArrayOptJSONArray != null) {
                                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                                    hashSet.add(jSONArrayOptJSONArray.getString(i));
                                }
                            }
                            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("items");
                            if (jSONObjectOptJSONObject != null) {
                                Iterator<String> itKeys = jSONObjectOptJSONObject.keys();
                                while (itKeys.hasNext()) {
                                    String next = itKeys.next();
                                    map2.put(next, getItem(next, jSONObjectOptJSONObject.optJSONObject(next)));
                                }
                            }
                            map = map2;
                            hashSet2 = hashSet;
                        } catch (Exception e) {
                            map = map2;
                            itemDataRequestStatus = itemDataRequestStatusValueOf;
                            str = strOptString;
                            exc = e;
                            Log.e(TAG, "Error parsing item data output", exc);
                        }
                    } catch (Exception e2) {
                        itemDataRequestStatus = itemDataRequestStatusValueOf;
                        str = strOptString;
                        exc = e2;
                    }
                } else {
                    hashSet2 = null;
                }
                hashSet = hashSet2;
                itemDataRequestStatus = itemDataRequestStatusValueOf;
                str = strOptString;
            } catch (Exception e3) {
                hashSet = null;
                ItemDataResponse.ItemDataRequestStatus itemDataRequestStatus2 = itemDataRequestStatusValueOf;
                str = strOptString;
                exc = e3;
                itemDataRequestStatus = itemDataRequestStatus2;
            }
        } catch (Exception e4) {
            exc = e4;
            hashSet = null;
            itemDataRequestStatus = itemDataRequestStatusValueOf;
            str = null;
        }
        return new ItemDataResponse(str, hashSet, itemDataRequestStatus, map);
    }

    private PurchaseResponse getPurchaseResponse(Intent intent) {
        String strOptString;
        String strOptString2;
        PurchaseResponse.PurchaseRequestStatus purchaseRequestStatusValueOf;
        Exception e;
        Receipt receipt = null;
        PurchaseResponse.PurchaseRequestStatus purchaseRequestStatus = PurchaseResponse.PurchaseRequestStatus.FAILED;
        try {
            JSONObject jSONObject = new JSONObject(intent.getStringExtra("purchaseOutput"));
            strOptString2 = jSONObject.optString("requestId");
            try {
                strOptString = jSONObject.optString("userId");
                try {
                    purchaseRequestStatusValueOf = PurchaseResponse.PurchaseRequestStatus.valueOf(jSONObject.optString("purchaseStatus"));
                    try {
                        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("receipt");
                        if (jSONObjectOptJSONObject != null) {
                            receipt = getReceipt(jSONObjectOptJSONObject);
                        }
                    } catch (Exception e2) {
                        e = e2;
                        Log.e(TAG, "Error parsing purchase output", e);
                    }
                } catch (Exception e3) {
                    purchaseRequestStatusValueOf = purchaseRequestStatus;
                    e = e3;
                }
            } catch (Exception e4) {
                strOptString = null;
                e = e4;
                purchaseRequestStatusValueOf = purchaseRequestStatus;
            }
        } catch (Exception e5) {
            strOptString = null;
            strOptString2 = null;
            purchaseRequestStatusValueOf = purchaseRequestStatus;
            e = e5;
        }
        return new PurchaseResponse(strOptString2, strOptString, receipt, purchaseRequestStatusValueOf);
    }

    private PurchaseUpdatesResponse getPurchaseUpdatesResponse(Intent intent) {
        Exception exc;
        HashSet hashSet;
        HashSet hashSet2;
        boolean zOptBoolean;
        String str;
        String str2;
        String str3;
        HashSet hashSet3;
        String str4 = null;
        HashSet hashSet4 = null;
        PurchaseUpdatesResponse.PurchaseUpdatesRequestStatus purchaseUpdatesRequestStatusValueOf = PurchaseUpdatesResponse.PurchaseUpdatesRequestStatus.FAILED;
        try {
            JSONObject jSONObject = new JSONObject(intent.getStringExtra("purchaseUpdatesOutput"));
            String strOptString = jSONObject.optString("requestId");
            try {
                purchaseUpdatesRequestStatusValueOf = PurchaseUpdatesResponse.PurchaseUpdatesRequestStatus.valueOf(jSONObject.optString("status"));
                String strOptString2 = jSONObject.optString("offset");
                try {
                    zOptBoolean = jSONObject.optBoolean("isMore");
                    try {
                        String strOptString3 = jSONObject.optString("userId");
                        try {
                            if (purchaseUpdatesRequestStatusValueOf == PurchaseUpdatesResponse.PurchaseUpdatesRequestStatus.SUCCESSFUL) {
                                HashSet hashSet5 = new HashSet();
                                try {
                                    hashSet3 = new HashSet();
                                    try {
                                        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("receipts");
                                        if (jSONArrayOptJSONArray != null) {
                                            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                                                hashSet5.add(getReceipt(jSONArrayOptJSONArray.optJSONObject(i)));
                                            }
                                        }
                                        JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("revokedSkus");
                                        if (jSONArrayOptJSONArray2 != null) {
                                            for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                                                hashSet3.add(jSONArrayOptJSONArray2.getString(i2));
                                            }
                                        }
                                        hashSet4 = hashSet5;
                                    } catch (Exception e) {
                                        str4 = strOptString;
                                        exc = e;
                                        str = strOptString3;
                                        hashSet = hashSet3;
                                        str2 = strOptString2;
                                        hashSet2 = hashSet5;
                                        Log.e(TAG, "Error parsing purchase updates output", exc);
                                        str3 = str2;
                                    }
                                } catch (Exception e2) {
                                    str2 = strOptString2;
                                    hashSet2 = hashSet5;
                                    str = strOptString3;
                                    hashSet = null;
                                    str4 = strOptString;
                                    exc = e2;
                                }
                            } else {
                                hashSet3 = null;
                            }
                            str = strOptString3;
                            hashSet = hashSet3;
                            hashSet2 = hashSet4;
                            str4 = strOptString;
                            str3 = strOptString2;
                        } catch (Exception e3) {
                            str = strOptString3;
                            hashSet = null;
                            str4 = strOptString;
                            exc = e3;
                            str2 = strOptString2;
                            hashSet2 = null;
                        }
                    } catch (Exception e4) {
                        hashSet = null;
                        str = null;
                        str4 = strOptString;
                        exc = e4;
                        str2 = strOptString2;
                        hashSet2 = null;
                    }
                } catch (Exception e5) {
                    hashSet = null;
                    zOptBoolean = false;
                    str = null;
                    str2 = strOptString2;
                    hashSet2 = null;
                    str4 = strOptString;
                    exc = e5;
                }
            } catch (Exception e6) {
                hashSet = null;
                hashSet2 = null;
                zOptBoolean = false;
                str = null;
                str4 = strOptString;
                exc = e6;
                str2 = null;
            }
        } catch (Exception e7) {
            exc = e7;
            hashSet = null;
            hashSet2 = null;
            zOptBoolean = false;
            str = null;
            str2 = null;
        }
        return new PurchaseUpdatesResponse(str4, str, purchaseUpdatesRequestStatusValueOf, hashSet2, hashSet, Offset.fromString(str3), zOptBoolean);
    }

    private Receipt getReceipt(JSONObject jSONObject) throws ParseException {
        SubscriptionPeriod subscriptionPeriod;
        Date date = null;
        String strOptString = jSONObject.optString("sku");
        Item.ItemType itemTypeValueOf = Item.ItemType.valueOf(jSONObject.optString("itemType"));
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("subscripionPeriod");
        if (itemTypeValueOf == Item.ItemType.SUBSCRIPTION) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date date2 = simpleDateFormat.parse(jSONObjectOptJSONObject.optString("startTime"));
            String strOptString2 = jSONObjectOptJSONObject.optString("endTime");
            if (strOptString2 != null && strOptString2.length() != 0) {
                date = simpleDateFormat.parse(strOptString2);
            }
            subscriptionPeriod = new SubscriptionPeriod(date2, date);
        } else {
            subscriptionPeriod = null;
        }
        return new Receipt(strOptString, itemTypeValueOf, false, subscriptionPeriod, jSONObject.optString("token"));
    }

    private GetUserIdResponse getUserIdResponse(Intent intent) {
        String strOptString;
        Exception e;
        GetUserIdResponse.GetUserIdRequestStatus getUserIdRequestStatusValueOf;
        String strOptString2;
        GetUserIdResponse.GetUserIdRequestStatus getUserIdRequestStatus;
        GetUserIdResponse.GetUserIdRequestStatus getUserIdRequestStatus2 = GetUserIdResponse.GetUserIdRequestStatus.FAILED;
        try {
            JSONObject jSONObject = new JSONObject(intent.getStringExtra("userOutput"));
            strOptString = jSONObject.optString("requestId");
            try {
                getUserIdRequestStatusValueOf = GetUserIdResponse.GetUserIdRequestStatus.valueOf(jSONObject.optString("status"));
                try {
                    strOptString2 = getUserIdRequestStatusValueOf == GetUserIdResponse.GetUserIdRequestStatus.SUCCESSFUL ? jSONObject.optString("userId") : null;
                    getUserIdRequestStatus = getUserIdRequestStatusValueOf;
                } catch (Exception e2) {
                    e = e2;
                    Log.e(TAG, "Error parsing userid output", e);
                    GetUserIdResponse.GetUserIdRequestStatus getUserIdRequestStatus3 = getUserIdRequestStatusValueOf;
                    strOptString2 = null;
                    getUserIdRequestStatus = getUserIdRequestStatus3;
                }
            } catch (Exception e3) {
                getUserIdRequestStatusValueOf = getUserIdRequestStatus2;
                e = e3;
            }
        } catch (Exception e4) {
            strOptString = null;
            e = e4;
            getUserIdRequestStatusValueOf = getUserIdRequestStatus2;
        }
        return new GetUserIdResponse(strOptString, getUserIdRequestStatus, strOptString2);
    }

    private void handleItemDataResponse(Intent intent) {
        final ItemDataResponse itemDataResponse = getItemDataResponse(intent);
        this._handler.post(new Runnable() { // from class: com.amazon.inapp.purchasing.SandboxResponseHandler.2
            @Override // java.lang.Runnable
            public void run() {
                if (Logger.isTraceOn()) {
                    Logger.trace(SandboxResponseHandler.TAG, "Running Runnable for itemDataResponse with requestId: " + itemDataResponse.getRequestId());
                }
                PurchasingObserver purchasingObserver = PurchasingManager.getPurchasingObserver();
                if (purchasingObserver != null) {
                    purchasingObserver.onItemDataResponse(itemDataResponse);
                }
            }
        });
    }

    private void handlePurchaseResponse(Intent intent) {
        final PurchaseResponse purchaseResponse = getPurchaseResponse(intent);
        this._handler.post(new Runnable() { // from class: com.amazon.inapp.purchasing.SandboxResponseHandler.4
            @Override // java.lang.Runnable
            public void run() {
                if (Logger.isTraceOn()) {
                    Logger.trace(SandboxResponseHandler.TAG, "Running Runnable for purchaseResponse with requestId: " + purchaseResponse.getRequestId());
                }
                PurchasingObserver purchasingObserver = PurchasingManager.getPurchasingObserver();
                if (purchasingObserver != null) {
                    purchasingObserver.onPurchaseResponse(purchaseResponse);
                }
            }
        });
    }

    private void handlePurchaseUpdatesResponse(Intent intent) {
        final PurchaseUpdatesResponse purchaseUpdatesResponse = getPurchaseUpdatesResponse(intent);
        this._handler.post(new Runnable() { // from class: com.amazon.inapp.purchasing.SandboxResponseHandler.1
            @Override // java.lang.Runnable
            public void run() {
                if (Logger.isTraceOn()) {
                    Logger.trace(SandboxResponseHandler.TAG, "Running Runnable for purchaseUpdatesResponse with requestId: " + purchaseUpdatesResponse.getRequestId());
                }
                PurchasingObserver purchasingObserver = PurchasingManager.getPurchasingObserver();
                if (purchasingObserver != null) {
                    purchasingObserver.onPurchaseUpdatesResponse(purchaseUpdatesResponse);
                }
            }
        });
    }

    private void handleUserIdResponse(Intent intent) {
        final GetUserIdResponse userIdResponse = getUserIdResponse(intent);
        this._handler.post(new Runnable() { // from class: com.amazon.inapp.purchasing.SandboxResponseHandler.3
            @Override // java.lang.Runnable
            public void run() {
                if (Logger.isTraceOn()) {
                    Logger.trace(SandboxResponseHandler.TAG, "Running Runnable for userIdResponse with requestId: " + userIdResponse.getRequestId());
                }
                PurchasingObserver purchasingObserver = PurchasingManager.getPurchasingObserver();
                if (purchasingObserver != null) {
                    purchasingObserver.onGetUserIdResponse(userIdResponse);
                }
            }
        });
    }

    @Override // com.amazon.inapp.purchasing.ResponseHandler
    public void handleResponse(Context context, Intent intent) {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "handleResponse");
        }
        try {
            String string = intent.getExtras().getString("responseType");
            if (string.equalsIgnoreCase("com.amazon.testclient.iap.purchase")) {
                handlePurchaseResponse(intent);
                return;
            }
            if (string.equalsIgnoreCase("com.amazon.testclient.iap.appUserId")) {
                handleUserIdResponse(intent);
            } else if (string.equalsIgnoreCase("com.amazon.testclient.iap.itemData")) {
                handleItemDataResponse(intent);
            } else if (string.equalsIgnoreCase("com.amazon.testclient.iap.purchaseUpdates")) {
                handlePurchaseUpdatesResponse(intent);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error handling response.", e);
        }
    }
}

package com.amazon.inapp.purchasing;

import com.amazon.android.Kiwi;
import com.amazon.android.framework.exception.KiwiException;
import com.amazon.android.framework.prompt.PromptContent;
import com.amazon.android.framework.prompt.PromptManager;
import com.amazon.android.framework.task.command.AbstractCommandTask;
import com.amazon.android.licensing.LicenseFailurePromptContentMapper;
import com.amazon.venezia.command.FailureResult;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
abstract class KiwiBaseCommandTask extends AbstractCommandTask {
    private static final String DATE_FORMAT = "MM/dd/yyyy HH:mm:ss";
    protected static final String FALSE = "false";
    protected static final String KEY_CURSOR = "cursor";
    protected static final String KEY_DESCRIPTION = "description";
    protected static final String KEY_ERROR_MESSAGE = "errorMessage";
    protected static final String KEY_HAS_CONTENT = "hasContent";
    protected static final String KEY_HAS_MORE = "hasMore";
    protected static final String KEY_ICON_URL = "iconUrl";
    protected static final String KEY_ITEM_TYPE = "itemType";
    protected static final String KEY_ORDER_STATUS = "orderStatus";
    protected static final String KEY_PERIOD_DATE_END = "endDate";
    protected static final String KEY_PERIOD_DATE_START = "startDate";
    protected static final String KEY_PRICE = "price";
    protected static final String KEY_PURCHASE_ITEM_INTENT = "purchaseItemIntent";
    protected static final String KEY_RECEIPT = "receipt";
    protected static final String KEY_RECEIPTS = "receipts";
    protected static final String KEY_REQUEST_ID = "requestId";
    protected static final String KEY_REVOCATIONS = "revocations";
    protected static final String KEY_SDK_VERSION = "sdkVersion";
    protected static final String KEY_SIGNATURE = "signature";
    protected static final String KEY_SKU = "sku";
    protected static final String KEY_SKUS = "skus";
    protected static final String KEY_TITLE = "title";
    protected static final String KEY_TOKEN = "token";
    protected static final String KEY_USER_ID = "userId";
    protected static final String SDK_VERSION = "1.0";
    private static final String TAG = "KiwiBaseCommandTask";
    protected static final String TRUE = "true";
    private final String _commandName;
    private final String _commandVersion;
    private final String _requestId;
    private LicenseFailurePromptContentMapper mapper = new LicenseFailurePromptContentMapper();
    private final Map<String, Object> _commandData = new HashMap();

    KiwiBaseCommandTask(String str, String str2, String str3) {
        this._requestId = str3;
        this._commandName = str;
        this._commandVersion = str2;
        this._commandData.put(KEY_REQUEST_ID, this._requestId);
        this._commandData.put(KEY_SDK_VERSION, "1.0");
    }

    protected void addCommandData(String str, Object obj) {
        this._commandData.put(str, obj);
    }

    protected Map<String, Object> getCommandData() {
        return this._commandData;
    }

    protected String getCommandName() {
        return this._commandName;
    }

    protected String getCommandVersion() {
        return this._commandVersion;
    }

    protected Date getDateFromString(String str) throws JSONException {
        try {
            Date date = new SimpleDateFormat(DATE_FORMAT).parse(str);
            if (0 == date.getTime()) {
                return null;
            }
            return date;
        } catch (ParseException e) {
            throw new JSONException(e.getMessage());
        }
    }

    protected Receipt getReceiptFromReceiptJson(JSONObject jSONObject) throws JSONException {
        String string = jSONObject.getString(KEY_SKU);
        Item.ItemType itemTypeValueOf = Item.ItemType.valueOf(jSONObject.getString(KEY_ITEM_TYPE));
        return new Receipt(string, itemTypeValueOf, false, Item.ItemType.SUBSCRIPTION == itemTypeValueOf ? getSubscriptionPeriodFromReceiptJson(jSONObject) : null, jSONObject.getString(KEY_TOKEN));
    }

    protected String getRequestId() {
        return this._requestId;
    }

    protected SubscriptionPeriod getSubscriptionPeriodFromReceiptJson(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.has(KEY_PERIOD_DATE_START)) {
            return null;
        }
        Date dateFromString = getDateFromString(jSONObject.getString(KEY_PERIOD_DATE_START));
        String strOptString = jSONObject.optString(KEY_PERIOD_DATE_END);
        return new SubscriptionPeriod(dateFromString, isNullOrEmpty(strOptString) ? null : getDateFromString(strOptString));
    }

    protected boolean isExecutionNeeded() {
        return true;
    }

    protected boolean isNullOrEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    protected void onException(KiwiException kiwiException) {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "onException, result: " + kiwiException.getMessage());
        }
        PromptManager promptManager = Kiwi.getPromptManager();
        PromptContent map = this.mapper.map(kiwiException);
        if (map != null) {
            promptManager.present(new FailurePrompt(map));
        }
        sendFailedResponse();
    }

    protected void onFailure(FailureResult failureResult) {
        if (failureResult == null) {
            if (Logger.isTraceOn()) {
                Logger.trace(TAG, "onFailure: null result");
            }
        } else {
            if (Logger.isTraceOn()) {
                Logger.trace(TAG, "onFailure: result message: " + failureResult.getDisplayableMessage());
            }
            Kiwi.getPromptManager().present(new FailurePrompt(new PromptContent(failureResult.getDisplayableName(), failureResult.getDisplayableMessage(), failureResult.getButtonLabel(), failureResult.show())));
            sendFailedResponse();
        }
    }

    protected void postRunnableToMainLooper(Runnable runnable) {
        HandlerManager.getMainHandlerAdapter().post(runnable);
    }

    protected abstract void sendFailedResponse();

    protected boolean verifyReceipt(String str, Receipt receipt, JSONObject jSONObject) {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "Validating receipt: " + receipt);
        }
        String strOptString = jSONObject.optString(KEY_SIGNATURE);
        if (isNullOrEmpty(strOptString)) {
            if (!Logger.isTraceOn()) {
                return false;
            }
            Logger.error(TAG, "a signature was not found in the receipt for request ID " + getRequestId());
            return false;
        }
        boolean zVerifySignature = verifySignature(str, receipt.getPurchaseToken(), strOptString);
        if (!Logger.isTraceOn()) {
            return zVerifySignature;
        }
        Logger.error(TAG, "signature verification " + (zVerifySignature ? "succeeded" : "failed") + " for request ID " + getRequestId());
        return zVerifySignature;
    }

    protected boolean verifySignature(String str, String str2, String str3) {
        return Kiwi.isSignedByKiwi(str + "-" + str2, str3);
    }
}

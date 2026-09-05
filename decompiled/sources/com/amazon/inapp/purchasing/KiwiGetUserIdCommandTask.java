package com.amazon.inapp.purchasing;

import com.amazon.venezia.command.SuccessResult;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class KiwiGetUserIdCommandTask extends KiwiBaseCommandTask {
    private static final String COMMAND_NAME = "get_userId";
    private static final String COMMAND_VERSION = "1.0";
    private static final String TAG = "KiwiGetUserIdCommandTask";

    KiwiGetUserIdCommandTask(String str) {
        super(COMMAND_NAME, "1.0", str);
    }

    private void notifyObserver(final GetUserIdResponse getUserIdResponse) {
        postRunnableToMainLooper(new Runnable() { // from class: com.amazon.inapp.purchasing.KiwiGetUserIdCommandTask.1
            @Override // java.lang.Runnable
            public void run() {
                PurchasingObserver purchasingObserver = PurchasingManager.getPurchasingObserver();
                if (purchasingObserver != null) {
                    if (Logger.isTraceOn()) {
                        Logger.trace(KiwiGetUserIdCommandTask.TAG, "Invoking onGetUserIdResponse with " + getUserIdResponse);
                    }
                    purchasingObserver.onGetUserIdResponse(getUserIdResponse);
                }
            }
        });
    }

    protected void onSuccess(SuccessResult successResult) {
        String str;
        GetUserIdResponse.GetUserIdRequestStatus getUserIdRequestStatus;
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "onSuccess");
        }
        Map data = successResult.getData();
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "data: " + data);
        }
        String str2 = (String) data.get("userId");
        if (isNullOrEmpty(str2)) {
            if (Logger.isTraceOn()) {
                Logger.trace(TAG, "found null or empty user ID");
            }
            str = null;
            getUserIdRequestStatus = GetUserIdResponse.GetUserIdRequestStatus.FAILED;
        } else {
            str = str2;
            getUserIdRequestStatus = GetUserIdResponse.GetUserIdRequestStatus.SUCCESSFUL;
        }
        notifyObserver(new GetUserIdResponse(getRequestId(), getUserIdRequestStatus, str));
    }

    @Override // com.amazon.inapp.purchasing.KiwiBaseCommandTask
    protected void sendFailedResponse() {
        notifyObserver(new GetUserIdResponse(getRequestId(), GetUserIdResponse.GetUserIdRequestStatus.FAILED, null));
    }
}

package com.amazon.inapp.purchasing;

import com.amazon.venezia.command.SuccessResult;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
final class KiwiResponseReceivedCommandTask extends KiwiBaseCommandTask {
    private static final String COMMAND_NAME = "response_received";
    private static final String COMMAND_VERSION = "1.0";
    private static final String TAG = "KiwiResponseReceivedCommandTask";

    KiwiResponseReceivedCommandTask(String str) {
        super(COMMAND_NAME, "1.0", str);
    }

    protected void onSuccess(SuccessResult successResult) {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "onSuccess");
        }
    }

    @Override // com.amazon.inapp.purchasing.KiwiBaseCommandTask
    protected void sendFailedResponse() {
    }
}

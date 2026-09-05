package com.amazon.inapp.purchasing;

import com.amazon.android.framework.util.KiwiLogger;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class KiwiLogHandler implements LogHandler {
    private static KiwiLogger LOGGER = new KiwiLogger("In App Purchasing SDK - Production Mode");

    KiwiLogHandler() {
    }

    private static String buildLogMessage(String str, String str2) {
        return str + ": " + str2;
    }

    @Override // com.amazon.inapp.purchasing.LogHandler
    public void error(String str, String str2) {
        LOGGER.error(buildLogMessage(str, str2));
    }

    @Override // com.amazon.inapp.purchasing.LogHandler
    public boolean isErrorOn() {
        return KiwiLogger.ERROR_ON;
    }

    @Override // com.amazon.inapp.purchasing.LogHandler
    public boolean isTestOn() {
        return KiwiLogger.isTestEnabled();
    }

    @Override // com.amazon.inapp.purchasing.LogHandler
    public boolean isTraceOn() {
        return KiwiLogger.TRACE_ON;
    }

    @Override // com.amazon.inapp.purchasing.LogHandler
    public void test(String str, String str2) {
        LOGGER.test(buildLogMessage(str, str2));
    }

    @Override // com.amazon.inapp.purchasing.LogHandler
    public void trace(String str, String str2) {
        LOGGER.trace(buildLogMessage(str, str2));
    }
}

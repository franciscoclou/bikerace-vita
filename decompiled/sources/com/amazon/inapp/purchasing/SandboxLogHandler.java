package com.amazon.inapp.purchasing;

import android.util.Log;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class SandboxLogHandler implements LogHandler {
    private static final boolean ERROR_ON = true;
    private static final boolean TEST_ON = true;
    private static final boolean TRACE_ON = true;

    SandboxLogHandler() {
    }

    private static String buildLogMessage(String str) {
        return "In App Purchasing SDK - Sandbox Mode: " + str;
    }

    @Override // com.amazon.inapp.purchasing.LogHandler
    public void error(String str, String str2) {
        Log.e(str, buildLogMessage(str2));
    }

    @Override // com.amazon.inapp.purchasing.LogHandler
    public boolean isErrorOn() {
        return true;
    }

    @Override // com.amazon.inapp.purchasing.LogHandler
    public boolean isTestOn() {
        return true;
    }

    @Override // com.amazon.inapp.purchasing.LogHandler
    public boolean isTraceOn() {
        return true;
    }

    @Override // com.amazon.inapp.purchasing.LogHandler
    public void test(String str, String str2) {
        Log.v(str, buildLogMessage(str2));
    }

    @Override // com.amazon.inapp.purchasing.LogHandler
    public void trace(String str, String str2) {
        Log.d(str, buildLogMessage(str2));
    }
}

package com.amazon.inapp.purchasing;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class Logger {
    Logger() {
    }

    static void error(String str, String str2) {
        if (isErrorOn()) {
            ImplementationFactory.getLogHandler().error(str, str2);
        }
    }

    static boolean isErrorOn() {
        return ImplementationFactory.getLogHandler().isErrorOn();
    }

    static boolean isTestOn() {
        return ImplementationFactory.getLogHandler().isTestOn();
    }

    static boolean isTraceOn() {
        return ImplementationFactory.getLogHandler().isTraceOn();
    }

    static void test(String str, String str2) {
        if (isTestOn()) {
            ImplementationFactory.getLogHandler().test(str, str2);
        }
    }

    static void trace(String str, String str2) {
        if (isTraceOn()) {
            ImplementationFactory.getLogHandler().trace(str, str2);
        }
    }
}

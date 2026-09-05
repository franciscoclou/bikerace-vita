package com.facebook;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public enum LoggingBehavior {
    REQUESTS,
    INCLUDE_ACCESS_TOKENS,
    INCLUDE_RAW_RESPONSES,
    CACHE,
    APP_EVENTS,
    DEVELOPER_ERRORS;


    @Deprecated
    public static final LoggingBehavior INSIGHTS = APP_EVENTS;

    /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
    public static LoggingBehavior[] valuesCustom() {
        LoggingBehavior[] loggingBehaviorArrValuesCustom = values();
        int length = loggingBehaviorArrValuesCustom.length;
        LoggingBehavior[] loggingBehaviorArr = new LoggingBehavior[length];
        System.arraycopy(loggingBehaviorArrValuesCustom, 0, loggingBehaviorArr, 0, length);
        return loggingBehaviorArr;
    }
}

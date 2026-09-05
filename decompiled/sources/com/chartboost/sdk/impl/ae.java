package com.chartboost.sdk.impl;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ae {
    public static String a(Object obj) {
        StringBuilder sb = new StringBuilder();
        a(obj, sb);
        return sb.toString();
    }

    public static void a(Object obj, StringBuilder sb) {
        af.a().a(obj, sb);
    }

    static void a(StringBuilder sb, String str) {
        sb.append("\"");
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\\') {
                sb.append("\\\\");
            } else if (cCharAt == '\"') {
                sb.append("\\\"");
            } else if (cCharAt == '\n') {
                sb.append("\\n");
            } else if (cCharAt == '\r') {
                sb.append("\\r");
            } else if (cCharAt == '\t') {
                sb.append("\\t");
            } else if (cCharAt == '\b') {
                sb.append("\\b");
            } else if (cCharAt >= ' ') {
                sb.append(cCharAt);
            }
        }
        sb.append("\"");
    }
}

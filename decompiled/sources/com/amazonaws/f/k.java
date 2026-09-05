package com.amazonaws.f;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final e f130a = new e();

    public static String a(Integer num) {
        return Integer.toString(num.intValue());
    }

    public static String a(String str) {
        return str;
    }

    public static String a(String str, String... strArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            sb.append(strArr[i].toString());
            if (i < strArr.length - 1) {
                sb.append(str);
            }
        }
        return sb.toString();
    }
}

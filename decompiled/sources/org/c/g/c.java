package org.c.g;

import java.util.regex.Pattern;

/* JADX INFO: compiled from: Preconditions.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Pattern f1632a = Pattern.compile("^[a-zA-Z][a-zA-Z0-9+.-]*://\\S+");

    public static void a(Object obj, String str) {
        a(obj != null, str);
    }

    public static void a(String str, String str2) {
        a((str == null || str.trim().equals("")) ? false : true, str2);
    }

    private static void a(boolean z, String str) {
        if (str == null || str.trim().length() <= 0) {
            str = "Received an invalid parameter";
        }
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }
}

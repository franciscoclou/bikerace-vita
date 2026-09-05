package com.applovin.a;

import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f f155a = new f(-1, 50, "BANNER");
    public static final f b = new f(-1, 75, "LEADER");
    public static final f c = new f(-1, -1, "INTER");
    public static final f d = new f(-1, -1, "MREC");
    private final int e;
    private final int f;
    private final String g;

    f(int i, int i2, String str) {
        if (i < 0 && i != -1) {
            throw new IllegalArgumentException("Ad width must be a positive number. Number provided: " + i);
        }
        if (i > 9999) {
            throw new IllegalArgumentException("Ad width must be less then 9999. Number provided: " + i);
        }
        if (i2 < 0 && i2 != -1) {
            throw new IllegalArgumentException("Ad height must be a positive number. Number provided: " + i2);
        }
        if (i2 > 9999) {
            throw new IllegalArgumentException("Ad height must be less then 9999. Number provided: " + i2);
        }
        if (str == null) {
            throw new IllegalArgumentException("No label specified");
        }
        if (str.length() > 9) {
            throw new IllegalArgumentException("Provided label is too long. Label provided: " + str);
        }
        this.e = i;
        this.f = i2;
        this.g = str;
    }

    public static f a(String str) {
        if (str == null || str.length() < 1) {
            return null;
        }
        String lowerCase = str.toLowerCase();
        if (lowerCase.equals("banner")) {
            return f155a;
        }
        if (lowerCase.equals("interstitial") || lowerCase.equals("inter")) {
            return c;
        }
        if (lowerCase.equals("mrec")) {
            return d;
        }
        if (lowerCase.equals("leader")) {
            return b;
        }
        String[] strArrSplit = str.split("x");
        return strArrSplit.length == 2 ? new f(b(strArrSplit[0]), b(strArrSplit[1]), str) : new f(0, 0, str);
    }

    private static int b(String str) {
        if ("span".equalsIgnoreCase(str)) {
            return -1;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static Set d() {
        HashSet hashSet = new HashSet(4);
        hashSet.add(f155a);
        hashSet.add(d);
        hashSet.add(c);
        hashSet.add(b);
        return hashSet;
    }

    public int a() {
        return this.e;
    }

    public int b() {
        return this.f;
    }

    public String c() {
        return this.g.toUpperCase();
    }

    public String toString() {
        return c();
    }
}

package com.applovin.impl.a;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f205a = 0;
    private final int b;
    private final String c;
    private final Object d;

    private l(String str, Object obj) {
        if (str == null) {
            throw new IllegalArgumentException("No name specified");
        }
        if (obj == null) {
            throw new IllegalArgumentException("No default value specified");
        }
        this.c = str;
        this.d = obj;
        this.b = f205a;
        f205a++;
    }

    public int a() {
        return this.b;
    }

    Object a(Object obj) {
        return this.d.getClass().cast(obj);
    }

    public String b() {
        return this.c;
    }

    public Object c() {
        return this.d;
    }
}

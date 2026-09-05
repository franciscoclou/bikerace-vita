package com.google.ads;

import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f617a;
    private final String b;
    private final List<String> c;
    private final List<String> d;
    private final HashMap<String, String> e;

    public a(String str, String str2, List<String> list, List<String> list2, HashMap<String, String> map) {
        com.google.ads.util.a.a(str2);
        if (str != null) {
            com.google.ads.util.a.a(str);
        }
        this.f617a = str;
        this.b = str2;
        this.c = list;
        this.e = map;
        this.d = list2;
    }

    public String a() {
        return this.f617a;
    }

    public String b() {
        return this.b;
    }

    public List<String> c() {
        return this.c;
    }

    public List<String> d() {
        return this.d;
    }

    public HashMap<String, String> e() {
        return this.e;
    }
}

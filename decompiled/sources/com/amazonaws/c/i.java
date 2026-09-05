package com.amazonaws.c;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.methods.HttpRequestBase;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.amazonaws.j<?> f90a;
    private final HttpRequestBase b;
    private String c;
    private int d;
    private InputStream e;
    private Map<String, String> f = new HashMap();

    public i(com.amazonaws.j<?> jVar, HttpRequestBase httpRequestBase) {
        this.f90a = jVar;
        this.b = httpRequestBase;
    }

    public com.amazonaws.j<?> a() {
        return this.f90a;
    }

    public void a(int i) {
        this.d = i;
    }

    public void a(InputStream inputStream) {
        this.e = inputStream;
    }

    public void a(String str) {
        this.c = str;
    }

    public void a(String str, String str2) {
        this.f.put(str, str2);
    }

    public Map<String, String> b() {
        return this.f;
    }

    public InputStream c() {
        return this.e;
    }

    public int d() {
        return this.d;
    }
}

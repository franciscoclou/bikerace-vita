package org.c.d;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: Response.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1621a;
    private String b;
    private String c;
    private InputStream d;
    private Map<String, String> e;
    private boolean f;

    i(HttpURLConnection httpURLConnection) {
        try {
            httpURLConnection.connect();
            this.f1621a = httpURLConnection.getResponseCode();
            this.b = httpURLConnection.getResponseMessage();
            this.e = a(httpURLConnection);
            this.d = a() ? httpURLConnection.getInputStream() : httpURLConnection.getErrorStream();
        } catch (UnknownHostException e) {
            throw new org.c.b.b("The IP address of a host could not be determined.", e);
        }
    }

    private String h() {
        this.c = org.c.g.d.a(c());
        return this.c;
    }

    private Map<String, String> a(HttpURLConnection httpURLConnection) {
        HashMap map = new HashMap();
        for (String str : httpURLConnection.getHeaderFields().keySet()) {
            map.put(str, httpURLConnection.getHeaderFields().get(str).get(0));
        }
        return map;
    }

    public boolean a() {
        return d() >= 200 && d() < 400;
    }

    public String b() {
        return this.c != null ? this.c : h();
    }

    public InputStream c() {
        return this.d;
    }

    public int d() {
        return this.f1621a;
    }

    public String e() {
        return this.b;
    }

    public String a(String str) {
        return this.e.get(str);
    }

    public void f() {
        this.f = true;
    }

    public boolean g() {
        return this.f;
    }
}

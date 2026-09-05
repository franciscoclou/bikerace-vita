package org.c.d;

import java.io.OutputStream;

/* JADX INFO: compiled from: OAuthConfig.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f1614a;
    private final String b;
    private final String c;
    private final j d;
    private final String e;
    private final OutputStream f;

    public a(String str, String str2, String str3, j jVar, String str4, OutputStream outputStream) {
        this.f1614a = str;
        this.b = str2;
        this.c = str3;
        this.d = jVar;
        this.e = str4;
        this.f = outputStream;
    }

    public String a() {
        return this.f1614a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public j d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public boolean f() {
        return this.e != null;
    }

    public void a(String str) {
        if (this.f != null) {
            try {
                this.f.write((String.valueOf(str) + "\n").getBytes("UTF8"));
            } catch (Exception e) {
                throw new RuntimeException("there were problems while writing to the debug stream", e);
            }
        }
    }
}

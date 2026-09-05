package org.c.d;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: Request.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static h f1619a = new h() { // from class: org.c.d.f.1
        @Override // org.c.d.h
        public void a(f fVar) {
        }
    };
    private String b;
    private l c;
    private HttpURLConnection h;
    private String i;
    private String g = null;
    private byte[] j = null;
    private boolean k = false;
    private boolean l = true;
    private Long m = null;
    private Long n = null;
    private g o = g.CONTENT_URL_FORM;
    private e d = new e();
    private e e = new e();
    private Map<String, String> f = new HashMap();

    public f(l lVar, String str) {
        this.c = lVar;
        this.b = str;
    }

    public void a(g gVar) {
        this.o = gVar;
    }

    public i a(h hVar) {
        try {
            a();
            return b(hVar);
        } catch (Exception e) {
            throw new org.c.b.a(e);
        }
    }

    public i c() {
        return a(f1619a);
    }

    private void a() {
        String strD = d();
        if (this.h == null) {
            System.setProperty("http.keepAlive", this.k ? "true" : "false");
            this.h = (HttpURLConnection) new URL(strD).openConnection();
            this.h.setInstanceFollowRedirects(this.l);
        }
    }

    public String d() {
        return this.d.a(this.b);
    }

    i b(h hVar) throws IOException {
        this.h.setRequestMethod(this.c.name());
        if (this.m != null) {
            this.h.setConnectTimeout(this.m.intValue());
        }
        if (this.n != null) {
            this.h.setReadTimeout(this.n.intValue());
        }
        a(this.h);
        if (this.c.equals(l.PUT) || this.c.equals(l.POST)) {
            a(this.h, j());
        }
        hVar.a(this);
        return new i(this.h);
    }

    void a(HttpURLConnection httpURLConnection) {
        for (String str : this.f.keySet()) {
            httpURLConnection.setRequestProperty(str, this.f.get(str));
        }
    }

    void a(HttpURLConnection httpURLConnection, byte[] bArr) throws IOException {
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(bArr.length));
        if (httpURLConnection.getRequestProperty("Content-Type") == null) {
            httpURLConnection.setRequestProperty("Content-Type", this.o.a());
        }
        httpURLConnection.setDoOutput(true);
        httpURLConnection.getOutputStream().write(bArr);
    }

    public void b(String str, String str2) {
        this.f.put(str, str2);
    }

    public void c(String str, String str2) {
        this.e.a(str, str2);
    }

    public void d(String str, String str2) {
        this.d.a(str, str2);
    }

    public void a(String str) {
        this.g = str;
    }

    public e e() {
        try {
            e eVar = new e();
            eVar.b(new URL(this.b).getQuery());
            eVar.a(this.d);
            return eVar;
        } catch (MalformedURLException e) {
            throw new org.c.b.b("Malformed URL", e);
        }
    }

    public e f() {
        return this.e;
    }

    public String g() {
        return this.b;
    }

    public String h() {
        if (this.b.startsWith("http://") && (this.b.endsWith(":80") || this.b.contains(":80/"))) {
            return this.b.replaceAll("\\?.*", "").replaceAll(":80", "");
        }
        if (this.b.startsWith("https://") && (this.b.endsWith(":443") || this.b.contains(":443/"))) {
            return this.b.replaceAll("\\?.*", "").replaceAll(":443", "");
        }
        return this.b.replaceAll("\\?.*", "");
    }

    public String i() {
        return this.g;
    }

    byte[] j() {
        if (this.j != null) {
            return this.j;
        }
        try {
            return (this.g != null ? this.g : this.e.b()).getBytes(l());
        } catch (UnsupportedEncodingException e) {
            throw new org.c.b.b("Unsupported Charset: " + l(), e);
        }
    }

    public l k() {
        return this.c;
    }

    public String l() {
        return this.i == null ? Charset.defaultCharset().name() : this.i;
    }

    public void a(int i, TimeUnit timeUnit) {
        this.n = Long.valueOf(timeUnit.toMillis(i));
    }

    public String toString() {
        return String.format("@Request(%s %s)", k(), g());
    }
}

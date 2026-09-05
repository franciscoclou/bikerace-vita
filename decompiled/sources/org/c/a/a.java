package org.c.a;

import java.io.OutputStream;
import org.c.d.j;
import org.c.g.c;

/* JADX INFO: compiled from: ServiceBuilder.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f1612a;
    private String b;
    private org.c.a.a.a d;
    private String e;
    private String c = "oob";
    private j f = j.Header;
    private OutputStream g = null;

    public a a(org.c.a.a.a aVar) {
        c.a(aVar, "Api cannot be null");
        this.d = aVar;
        return this;
    }

    public a a(String str) {
        c.a(str, "Invalid Api key");
        this.f1612a = str;
        return this;
    }

    public a b(String str) {
        c.a(str, "Invalid Api secret");
        this.b = str;
        return this;
    }

    public org.c.e.c a() {
        c.a(this.d, "You must specify a valid api through the provider() method");
        c.a(this.f1612a, "You must provide an api key");
        c.a(this.b, "You must provide an api secret");
        return this.d.a(new org.c.d.a(this.f1612a, this.b, this.c, this.f, this.e, this.g));
    }
}

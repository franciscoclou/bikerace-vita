package org.a.a.a.a.a;

import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: compiled from: LocalNetworkModule.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class j implements l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Class f1592a;
    private String b;
    private Object c;

    public j(String str) {
        this.b = str;
    }

    @Override // org.a.a.a.a.a.l
    public void a() throws org.a.a.a.a.k {
        if (!i.a("com.ibm.mqttdirect.modules.local.bindings.LocalListener")) {
            throw i.a(32103);
        }
        try {
            this.f1592a = Class.forName("com.ibm.mqttdirect.modules.local.bindings.LocalListener");
            this.c = this.f1592a.getMethod("connect", String.class).invoke(null, this.b);
        } catch (Exception e) {
        }
        if (this.c == null) {
            throw i.a(32103);
        }
    }

    @Override // org.a.a.a.a.a.l
    public InputStream b() {
        try {
            return (InputStream) this.f1592a.getMethod("getClientInputStream", new Class[0]).invoke(this.c, new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    @Override // org.a.a.a.a.a.l
    public OutputStream c() {
        try {
            return (OutputStream) this.f1592a.getMethod("getClientOutputStream", new Class[0]).invoke(this.c, new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    @Override // org.a.a.a.a.a.l
    public void d() {
        if (this.c != null) {
            try {
                this.f1592a.getMethod("close", new Class[0]).invoke(this.c, new Object[0]);
            } catch (Exception e) {
            }
        }
    }
}

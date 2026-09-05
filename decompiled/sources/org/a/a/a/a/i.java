package org.a.a.a.a;

import java.util.Properties;
import javax.net.SocketFactory;

/* JADX INFO: compiled from: MqttConnectOptions.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class i {
    private String d;
    private char[] e;
    private SocketFactory f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1603a = 60;
    private String b = null;
    private l c = null;
    private Properties g = null;
    private boolean h = true;
    private int i = 30;

    public char[] a() {
        return this.e;
    }

    public String b() {
        return this.d;
    }

    public int c() {
        return this.f1603a;
    }

    public int d() {
        return this.i;
    }

    public SocketFactory e() {
        return this.f;
    }

    public void a(SocketFactory socketFactory) {
        this.f = socketFactory;
    }

    public String f() {
        return this.b;
    }

    public l g() {
        return this.c;
    }

    public Properties h() {
        return this.g;
    }

    public boolean i() {
        return this.h;
    }

    public Properties j() {
        Properties properties = new Properties();
        properties.put("CleanSession", new Boolean(i()));
        properties.put("ConTimeout", new Integer(d()));
        properties.put("KeepAliveInterval", new Integer(c()));
        properties.put("UserName", b() == null ? "null" : b());
        properties.put("WillDestination", f() == null ? "null" : f());
        if (e() == null) {
            properties.put("SocketFactory", "null");
        } else {
            properties.put("SocketFactory", e());
        }
        if (h() == null) {
            properties.put("SSLProperties", "null");
        } else {
            properties.put("SSLProperties", h());
        }
        return properties;
    }

    public String toString() {
        return org.a.a.a.a.d.a.a(j(), "Connection options");
    }
}

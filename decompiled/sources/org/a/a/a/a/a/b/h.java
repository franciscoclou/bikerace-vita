package org.a.a.a.a.a.b;

/* JADX INFO: compiled from: MqttPersistableWireMessage.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class h extends t implements org.a.a.a.a.m {
    public h(byte b) {
        super(b);
    }

    @Override // org.a.a.a.a.m
    public byte[] a() throws org.a.a.a.a.n {
        try {
            return k();
        } catch (org.a.a.a.a.k e) {
            throw new org.a.a.a.a.n(e.getCause());
        }
    }

    @Override // org.a.a.a.a.m
    public int b() {
        return a().length;
    }

    @Override // org.a.a.a.a.m
    public int c() {
        return 0;
    }

    @Override // org.a.a.a.a.m
    public byte[] d() throws org.a.a.a.a.n {
        try {
            return e_();
        } catch (org.a.a.a.a.k e) {
            throw new org.a.a.a.a.n(e.getCause());
        }
    }

    @Override // org.a.a.a.a.m
    public int c_() {
        return 0;
    }

    @Override // org.a.a.a.a.m
    public int f() {
        return 0;
    }
}

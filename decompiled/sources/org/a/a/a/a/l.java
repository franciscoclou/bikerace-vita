package org.a.a.a.a;

/* JADX INFO: compiled from: MqttMessage.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class l {
    private byte[] b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f1605a = true;
    private int c = 1;
    private boolean d = false;
    private boolean e = false;

    public static void a(int i) {
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException();
        }
    }

    public l() {
        a(new byte[0]);
    }

    public l(byte[] bArr) {
        a(bArr);
    }

    public byte[] a() {
        return this.b;
    }

    public void a(byte[] bArr) {
        d();
        if (bArr == null) {
            throw new NullPointerException();
        }
        this.b = bArr;
    }

    public boolean b() {
        return this.d;
    }

    public void a(boolean z) {
        d();
        this.d = z;
    }

    public int c() {
        return this.c;
    }

    public void b(int i) {
        d();
        a(i);
        this.c = i;
    }

    public String toString() {
        return new String(this.b);
    }

    protected void d() {
        if (!this.f1605a) {
            throw new IllegalStateException();
        }
    }

    protected void b(boolean z) {
        this.e = z;
    }

    public boolean e() {
        return this.e;
    }
}

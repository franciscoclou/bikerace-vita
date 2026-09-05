package org.a.a.a.a.a.b;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: compiled from: MqttPublish.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class o extends h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private org.a.a.a.a.l f1579a;
    private String d;
    private byte[] e;

    public o(String str, org.a.a.a.a.l lVar) {
        super((byte) 3);
        this.e = null;
        this.d = str;
        this.f1579a = lVar;
    }

    public o(byte b, byte[] bArr) throws IOException {
        super((byte) 3);
        this.e = null;
        this.f1579a = new p();
        this.f1579a.b((b >> 1) & 3);
        if ((b & 1) == 1) {
            this.f1579a.a(true);
        }
        if ((b & 8) == 8) {
            ((p) this.f1579a).b(true);
        }
        a aVar = new a(new ByteArrayInputStream(bArr));
        DataInputStream dataInputStream = new DataInputStream(aVar);
        this.d = b(dataInputStream);
        if (this.f1579a.c() > 0) {
            this.b = dataInputStream.readUnsignedShort();
        }
        byte[] bArr2 = new byte[bArr.length - aVar.a()];
        dataInputStream.readFully(bArr2);
        dataInputStream.close();
        this.f1579a.a(bArr2);
    }

    @Override // org.a.a.a.a.a.b.t
    protected byte d_() {
        byte bC = (byte) (this.f1579a.c() << 1);
        if (this.f1579a.b()) {
            bC = (byte) (bC | 1);
        }
        if (this.f1579a.e() || this.c) {
            return (byte) (bC | 8);
        }
        return bC;
    }

    public String g() {
        return this.d;
    }

    public org.a.a.a.a.l h() {
        return this.f1579a;
    }

    protected static byte[] a(org.a.a.a.a.l lVar) {
        return lVar.a();
    }

    @Override // org.a.a.a.a.a.b.t
    public byte[] e_() {
        if (this.e == null) {
            this.e = a(this.f1579a);
        }
        return this.e;
    }

    @Override // org.a.a.a.a.a.b.h, org.a.a.a.a.m
    public int c_() {
        try {
            return e_().length;
        } catch (org.a.a.a.a.k e) {
            return 0;
        }
    }

    @Override // org.a.a.a.a.a.b.t
    public void a(int i) {
        super.a(i);
        if (this.f1579a instanceof p) {
            ((p) this.f1579a).c(i);
        }
    }

    @Override // org.a.a.a.a.a.b.t
    protected byte[] f_() throws org.a.a.a.a.k {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            a(dataOutputStream, this.d);
            if (this.f1579a.c() > 0) {
                dataOutputStream.writeShort(this.b);
            }
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new org.a.a.a.a.k(e);
        }
    }

    @Override // org.a.a.a.a.a.b.t
    public boolean g_() {
        return true;
    }
}

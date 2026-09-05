package org.a.a.a.a.a.b;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: compiled from: MqttSubscribe.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class r extends t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String[] f1582a;
    private int[] d;

    public r(String[] strArr, int[] iArr) {
        super((byte) 8);
        this.f1582a = strArr;
        this.d = iArr;
        if (strArr.length != iArr.length) {
            throw new IllegalArgumentException();
        }
        for (int i : iArr) {
            org.a.a.a.a.l.a(i);
        }
    }

    @Override // org.a.a.a.a.a.b.t
    protected byte d_() {
        return (byte) ((this.c ? 8 : 0) | 2);
    }

    @Override // org.a.a.a.a.a.b.t
    protected byte[] f_() throws org.a.a.a.a.k {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeShort(this.b);
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new org.a.a.a.a.k(e);
        }
    }

    @Override // org.a.a.a.a.a.b.t
    public byte[] e_() throws org.a.a.a.a.k {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            for (int i = 0; i < this.f1582a.length; i++) {
                a(dataOutputStream, this.f1582a[i]);
                dataOutputStream.writeByte(this.d[i]);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new org.a.a.a.a.k(e);
        }
    }
}

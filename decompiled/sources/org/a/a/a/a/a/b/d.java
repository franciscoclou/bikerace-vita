package org.a.a.a.a.a.b;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: compiled from: MqttConnect.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class d extends t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f1575a = "Con";
    private String d;
    private boolean e;
    private org.a.a.a.a.l f;
    private String g;
    private char[] h;
    private int i;
    private String j;

    public d(String str, boolean z, int i, String str2, char[] cArr, org.a.a.a.a.l lVar, String str3) {
        super((byte) 1);
        this.d = str;
        this.e = z;
        this.i = i;
        this.g = str2;
        this.h = cArr;
        this.f = lVar;
        this.j = str3;
    }

    @Override // org.a.a.a.a.a.b.t
    protected byte d_() {
        return (byte) 0;
    }

    @Override // org.a.a.a.a.a.b.t
    protected byte[] f_() throws org.a.a.a.a.k {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            a(dataOutputStream, "MQIsdp");
            dataOutputStream.write(3);
            byte bC = 0;
            if (this.e) {
                bC = (byte) 2;
            }
            if (this.f != null) {
                bC = (byte) (((byte) (bC | 4)) | (this.f.c() << 3));
                if (this.f.b()) {
                    bC = (byte) (bC | 32);
                }
            }
            if (this.g != null) {
                bC = (byte) (bC | 128);
                if (this.h != null) {
                    bC = (byte) (bC | 64);
                }
            }
            dataOutputStream.write(bC);
            dataOutputStream.writeShort(this.i);
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
            a(dataOutputStream, this.d);
            if (this.f != null) {
                a(dataOutputStream, this.j);
                dataOutputStream.writeShort(this.f.a().length);
                dataOutputStream.write(this.f.a());
            }
            if (this.g != null) {
                a(dataOutputStream, this.g);
                if (this.h != null) {
                    a(dataOutputStream, new String(this.h));
                }
            }
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new org.a.a.a.a.k(e);
        }
    }

    @Override // org.a.a.a.a.a.b.t
    public boolean g_() {
        return false;
    }

    @Override // org.a.a.a.a.a.b.t
    public String e() {
        return new String(f1575a);
    }
}

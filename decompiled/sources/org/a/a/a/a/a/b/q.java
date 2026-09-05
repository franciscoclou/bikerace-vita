package org.a.a.a.a.a.b;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/* JADX INFO: compiled from: MqttSuback.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class q extends b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int[] f1581a;

    public q(byte b, byte[] bArr) {
        super((byte) 9);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.b = dataInputStream.readUnsignedShort();
        int i = 0;
        this.f1581a = new int[bArr.length - 2];
        for (int i2 = dataInputStream.read(); i2 != -1; i2 = dataInputStream.read()) {
            this.f1581a[i] = i2;
            i++;
        }
        dataInputStream.close();
    }

    @Override // org.a.a.a.a.a.b.t
    protected byte[] f_() {
        return new byte[0];
    }
}

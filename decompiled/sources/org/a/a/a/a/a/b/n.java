package org.a.a.a.a.a.b;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/* JADX INFO: compiled from: MqttPubRel.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class n extends h {
    public n(m mVar) {
        super((byte) 6);
        a(mVar.j());
    }

    public n(byte b, byte[] bArr) {
        super((byte) 6);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.b = dataInputStream.readUnsignedShort();
        dataInputStream.close();
    }

    @Override // org.a.a.a.a.a.b.t
    protected byte[] f_() {
        return l();
    }

    @Override // org.a.a.a.a.a.b.t
    protected byte d_() {
        return (byte) ((this.c ? 8 : 0) | 2);
    }
}

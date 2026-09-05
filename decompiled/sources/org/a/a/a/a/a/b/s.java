package org.a.a.a.a.a.b;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/* JADX INFO: compiled from: MqttUnsubAck.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class s extends b {
    public s(byte b, byte[] bArr) {
        super((byte) 11);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.b = dataInputStream.readUnsignedShort();
        dataInputStream.close();
    }

    @Override // org.a.a.a.a.a.b.t
    protected byte[] f_() {
        return new byte[0];
    }
}

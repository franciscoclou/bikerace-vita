package org.a.a.a.a.a.b;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/* JADX INFO: compiled from: MqttPubAck.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class k extends b {
    public k(byte b, byte[] bArr) {
        super((byte) 4);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.b = dataInputStream.readUnsignedShort();
        dataInputStream.close();
    }

    public k(o oVar) {
        super((byte) 4);
        this.b = oVar.j();
    }

    @Override // org.a.a.a.a.a.b.t
    protected byte[] f_() {
        return l();
    }
}

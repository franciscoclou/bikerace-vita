package org.a.a.a.a.a.b;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/* JADX INFO: compiled from: MqttPubComp.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class l extends b {
    public l(byte b, byte[] bArr) {
        super((byte) 7);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.b = dataInputStream.readUnsignedShort();
        dataInputStream.close();
    }

    public l(o oVar) {
        super((byte) 7);
        this.b = oVar.j();
    }

    public l(int i) {
        super((byte) 7);
        this.b = i;
    }

    @Override // org.a.a.a.a.a.b.t
    protected byte[] f_() {
        return l();
    }
}

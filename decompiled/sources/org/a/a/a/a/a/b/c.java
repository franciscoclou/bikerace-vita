package org.a.a.a.a.a.b;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/* JADX INFO: compiled from: MqttConnack.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c extends b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1574a;

    public c(byte b, byte[] bArr) throws IOException {
        super((byte) 2);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        dataInputStream.readByte();
        this.f1574a = dataInputStream.readUnsignedByte();
        dataInputStream.close();
    }

    public int b_() {
        return this.f1574a;
    }

    @Override // org.a.a.a.a.a.b.t
    protected byte[] f_() {
        return new byte[0];
    }

    @Override // org.a.a.a.a.a.b.t
    public boolean g_() {
        return false;
    }

    @Override // org.a.a.a.a.a.b.t
    public String e() {
        return new String("Con");
    }
}

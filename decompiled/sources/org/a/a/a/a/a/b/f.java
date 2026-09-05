package org.a.a.a.a.a.b;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: MqttInputStream.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class f extends InputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DataInputStream f1577a;

    public f(InputStream inputStream) {
        this.f1577a = new DataInputStream(inputStream);
    }

    @Override // java.io.InputStream
    public int read() {
        return this.f1577a.read();
    }

    @Override // java.io.InputStream
    public int available() {
        return this.f1577a.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f1577a.close();
    }

    public t a() throws org.a.a.a.a.k, IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte b = this.f1577a.readByte();
        byte b2 = (byte) ((b >>> 4) & 15);
        if (b2 < 1 || b2 > 14) {
            throw org.a.a.a.a.a.i.a(32108);
        }
        long jA = t.a(this.f1577a).a();
        byteArrayOutputStream.write(b);
        byteArrayOutputStream.write(t.a(jA));
        byte[] bArr = new byte[(int) (((long) byteArrayOutputStream.size()) + jA)];
        this.f1577a.readFully(bArr, byteArrayOutputStream.size(), bArr.length - byteArrayOutputStream.size());
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        System.arraycopy(byteArray, 0, bArr, 0, byteArray.length);
        return t.a(bArr);
    }
}

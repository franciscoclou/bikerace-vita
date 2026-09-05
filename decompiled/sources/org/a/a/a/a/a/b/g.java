package org.a.a.a.a.a.b;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: compiled from: MqttOutputStream.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class g extends OutputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private BufferedOutputStream f1578a;

    public g(OutputStream outputStream) {
        this.f1578a = new BufferedOutputStream(outputStream);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f1578a.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.f1578a.flush();
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
        this.f1578a.write(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.f1578a.write(bArr, i, i2);
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.f1578a.write(i);
    }

    public void a(t tVar) throws org.a.a.a.a.k, IOException {
        byte[] bArrK = tVar.k();
        byte[] bArrE_ = tVar.e_();
        this.f1578a.write(bArrK, 0, bArrK.length);
        this.f1578a.write(bArrE_, 0, bArrE_.length);
    }
}

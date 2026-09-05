package org.a.a.a.a.a.b;

import java.io.InputStream;

/* JADX INFO: compiled from: MultiByteArrayInputStream.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class u extends InputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private byte[] f1584a;
    private int b;
    private int c;
    private byte[] d;
    private int e;
    private int f;
    private int g = 0;

    public u(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        this.f1584a = bArr;
        this.d = bArr2;
        this.b = i;
        this.e = i3;
        this.c = i2;
        this.f = i4;
    }

    @Override // java.io.InputStream
    public int read() {
        int i;
        if (this.g < this.c) {
            i = this.f1584a[this.b + this.g];
        } else if (this.g < this.c + this.f) {
            i = this.d[(this.e + this.g) - this.c];
        } else {
            return -1;
        }
        if (i < 0) {
            i += 256;
        }
        this.g++;
        return i;
    }
}

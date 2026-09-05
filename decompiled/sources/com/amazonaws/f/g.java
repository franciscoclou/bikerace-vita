package com.amazonaws.f;

import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class g extends FilterInputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private byte[] f126a;
    private boolean b;

    public g(InputStream inputStream) {
        super(new BufferedInputStream(inputStream));
        this.f126a = new byte[200];
        this.b = false;
    }

    private int a(String str) {
        h hVar = new h(str);
        if (!hVar.a("xmlns")) {
            return -1;
        }
        hVar.b(" ");
        if (!hVar.a("=")) {
            return -1;
        }
        hVar.b(" ");
        if (hVar.a("\"") && hVar.c("\"")) {
            return str.length() - hVar.a().length();
        }
        return -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int i = this.in.read();
        if (i != 120 || this.b) {
            return i;
        }
        this.f126a[0] = (byte) i;
        this.in.mark(this.f126a.length);
        int i2 = this.in.read(this.f126a, 1, this.f126a.length - 1);
        this.in.reset();
        int iA = a(new String(this.f126a, 0, i2 + 1));
        if (iA <= 0) {
            return i;
        }
        for (int i3 = 0; i3 < iA - 1; i3++) {
            this.in.read();
        }
        int i4 = this.in.read();
        this.b = true;
        return i4;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = read();
            if (i4 == -1) {
                if (i3 == 0) {
                    return -1;
                }
                return i3;
            }
            bArr[i3 + i] = (byte) i4;
        }
        return i2;
    }
}

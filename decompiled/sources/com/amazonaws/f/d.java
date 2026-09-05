package com.amazonaws.f;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class d extends FilterInputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f124a;

    public d(InputStream inputStream) {
        super(inputStream);
        this.f124a = 0L;
    }

    public long a() {
        return this.f124a;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int i = super.read();
        this.f124a = (i >= 0 ? 1L : 0L) + this.f124a;
        return i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = super.read(bArr, i, i2);
        this.f124a = (i3 >= 0 ? i3 : 0L) + this.f124a;
        return i3;
    }
}

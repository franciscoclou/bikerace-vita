package com.chartboost.sdk.impl;

import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class aq extends ar {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f422a;
    private int b;
    private byte[] c = new byte[512];

    @Override // com.chartboost.sdk.impl.ar, java.io.OutputStream
    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        b(i2);
        System.arraycopy(bArr, i, this.c, this.f422a, i2);
        this.f422a += i2;
        this.b = Math.max(this.f422a, this.b);
    }

    @Override // com.chartboost.sdk.impl.ar, java.io.OutputStream
    public void write(int i) {
        b(1);
        byte[] bArr = this.c;
        int i2 = this.f422a;
        this.f422a = i2 + 1;
        bArr[i2] = (byte) (i & 255);
        this.b = Math.max(this.f422a, this.b);
    }

    @Override // com.chartboost.sdk.impl.ar
    public int a() {
        return this.f422a;
    }

    @Override // com.chartboost.sdk.impl.ar
    public void a(int i) {
        this.f422a = i;
    }

    @Override // com.chartboost.sdk.impl.ar
    public int b() {
        return this.b;
    }

    @Override // com.chartboost.sdk.impl.ar
    public int a(OutputStream outputStream) throws IOException {
        outputStream.write(this.c, 0, this.b);
        return this.b;
    }

    void b(int i) {
        int i2 = this.f422a + i;
        if (i2 >= this.c.length) {
            int length = this.c.length * 2;
            if (length <= i2) {
                length = i2 + XMLChar.MASK_NCNAME;
            }
            byte[] bArr = new byte[length];
            System.arraycopy(this.c, 0, bArr, 0, this.b);
            this.c = bArr;
        }
    }
}

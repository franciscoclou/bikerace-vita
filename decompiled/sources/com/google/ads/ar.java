package com.google.ads;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ar {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final byte[] f626a = new byte[256];
    private int b;
    private int c;

    public ar(byte[] bArr) {
        for (int i = 0; i < 256; i++) {
            this.f626a[i] = (byte) i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 256; i3++) {
            i2 = (i2 + this.f626a[i3] + bArr[i3 % bArr.length]) & 255;
            byte b = this.f626a[i3];
            this.f626a[i3] = this.f626a[i2];
            this.f626a[i2] = b;
        }
        this.b = 0;
        this.c = 0;
    }

    public void a(byte[] bArr) {
        int i = this.b;
        int i2 = this.c;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) & 255;
            i2 = (i2 + this.f626a[i]) & 255;
            byte b = this.f626a[i];
            this.f626a[i] = this.f626a[i2];
            this.f626a[i2] = b;
            bArr[i3] = (byte) (bArr[i3] ^ this.f626a[(this.f626a[i] + this.f626a[i2]) & 255]);
        }
        this.b = i;
        this.c = i2;
    }
}

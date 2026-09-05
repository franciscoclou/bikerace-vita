package com.flurry.sdk;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.zip.CRC32;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class er extends MessageDigest {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private CRC32 f545a;

    public er() {
        super("CRC");
        this.f545a = new CRC32();
    }

    @Override // java.security.MessageDigestSpi
    protected void engineReset() {
        this.f545a.reset();
    }

    @Override // java.security.MessageDigestSpi
    protected void engineUpdate(byte b) {
        this.f545a.update(b);
    }

    @Override // java.security.MessageDigestSpi
    protected void engineUpdate(byte[] bArr, int i, int i2) {
        this.f545a.update(bArr, i, i2);
    }

    @Override // java.security.MessageDigestSpi
    protected byte[] engineDigest() {
        long value = this.f545a.getValue();
        return new byte[]{(byte) (((-16777216) & value) >> 24), (byte) ((16711680 & value) >> 16), (byte) ((65280 & value) >> 8), (byte) ((value & 255) >> 0)};
    }

    public byte[] a() {
        return engineDigest();
    }

    public int b() {
        return ByteBuffer.wrap(engineDigest()).getInt();
    }
}

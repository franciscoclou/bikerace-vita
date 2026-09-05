package com.google.ads;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b {
    private static b c = null;
    private BigInteger b = BigInteger.ONE;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final BigInteger f636a = d();

    public static synchronized b a() {
        if (c == null) {
            c = new b();
        }
        return c;
    }

    public synchronized BigInteger b() {
        return this.f636a;
    }

    public synchronized BigInteger c() {
        BigInteger bigInteger;
        bigInteger = this.b;
        this.b = this.b.add(BigInteger.ONE);
        return bigInteger;
    }

    private b() {
    }

    private static BigInteger d() {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            UUID uuidRandomUUID = UUID.randomUUID();
            messageDigest.update(a(uuidRandomUUID.getLeastSignificantBits()));
            messageDigest.update(a(uuidRandomUUID.getMostSignificantBits()));
            byte[] bArr = new byte[9];
            bArr[0] = 0;
            System.arraycopy(messageDigest.digest(), 0, bArr, 1, 8);
            return new BigInteger(bArr);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Cannot find MD5 message digest algorithm.");
        }
    }

    private static byte[] a(long j) {
        return BigInteger.valueOf(j).toByteArray();
    }
}

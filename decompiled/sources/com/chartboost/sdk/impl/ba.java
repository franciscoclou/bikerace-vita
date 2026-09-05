package com.chartboost.sdk.impl;

import com.facebook.AppEventsConstants;
import com.flurry.android.Constants;
import java.io.Serializable;
import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.util.Enumeration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ba implements Serializable, Comparable<ba> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final Logger f436a = Logger.getLogger("org.bson.ObjectId");
    private static AtomicInteger f = new AtomicInteger(new Random().nextInt());
    private static final int g;
    final int b;
    final int c;
    final int d;
    boolean e;

    static {
        int iNextInt;
        try {
            try {
                StringBuilder sb = new StringBuilder();
                Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    sb.append(networkInterfaces.nextElement().toString());
                }
                iNextInt = sb.toString().hashCode() << 16;
            } catch (Throwable th) {
                f436a.log(Level.WARNING, th.getMessage(), th);
                iNextInt = new Random().nextInt() << 16;
            }
            f436a.fine("machine piece post: " + Integer.toHexString(iNextInt));
            int iNextInt2 = new Random().nextInt();
            ClassLoader classLoader = ba.class.getClassLoader();
            int iHashCode = (Integer.toHexString(iNextInt2) + Integer.toHexString(classLoader != null ? System.identityHashCode(classLoader) : 0)).hashCode() & 65535;
            f436a.fine(new StringBuilder("process piece: ").append(Integer.toHexString(iHashCode)).toString());
            g = iHashCode | iNextInt;
            f436a.fine(new StringBuilder("machine : ").append(Integer.toHexString(g)).toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean a(String str) {
        int length;
        if (str == null || (length = str.length()) != 24) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if ((cCharAt < '0' || cCharAt > '9') && ((cCharAt < 'a' || cCharAt > 'f') && (cCharAt < 'A' || cCharAt > 'F'))) {
                return false;
            }
        }
        return true;
    }

    public static ba a(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof ba) {
            return (ba) obj;
        }
        if (obj instanceof String) {
            String string = obj.toString();
            if (a(string)) {
                return new ba(string);
            }
        }
        return null;
    }

    public ba(String str) {
        this(str, false);
    }

    public ba(String str, boolean z) {
        if (!a(str)) {
            throw new IllegalArgumentException("invalid ObjectId [" + str + "]");
        }
        str = z ? b(str) : str;
        byte[] bArr = new byte[12];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) Integer.parseInt(str.substring(i * 2, (i * 2) + 2), 16);
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        this.b = byteBufferWrap.getInt();
        this.c = byteBufferWrap.getInt();
        this.d = byteBufferWrap.getInt();
        this.e = false;
    }

    public ba(int i, int i2, int i3) {
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = false;
    }

    public ba() {
        this.b = (int) (System.currentTimeMillis() / 1000);
        this.c = g;
        this.d = f.getAndIncrement();
        this.e = true;
    }

    public int hashCode() {
        return this.b + (this.c * 111) + (this.d * 17);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        ba baVarA = a(obj);
        if (baVarA == null) {
            return false;
        }
        return this.b == baVarA.b && this.c == baVarA.c && this.d == baVarA.d;
    }

    public String a() {
        byte[] bArrB = b();
        StringBuilder sb = new StringBuilder(24);
        for (byte b : bArrB) {
            String hexString = Integer.toHexString(b & Constants.UNKNOWN);
            if (hexString.length() == 1) {
                sb.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public byte[] b() {
        byte[] bArr = new byte[12];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.putInt(this.b);
        byteBufferWrap.putInt(this.c);
        byteBufferWrap.putInt(this.d);
        return bArr;
    }

    static String a(String str, int i) {
        return str.substring(i * 2, (i * 2) + 2);
    }

    public static String b(String str) {
        if (!a(str)) {
            throw new IllegalArgumentException("invalid object id: " + str);
        }
        StringBuilder sb = new StringBuilder(24);
        for (int i = 7; i >= 0; i--) {
            sb.append(a(str, i));
        }
        for (int i2 = 11; i2 >= 8; i2--) {
            sb.append(a(str, i2));
        }
        return sb.toString();
    }

    public String toString() {
        return a();
    }

    int a(int i, int i2) {
        long j = (((long) i) & 4294967295L) - (((long) i2) & 4294967295L);
        if (j < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) j;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(ba baVar) {
        if (baVar == null) {
            return -1;
        }
        int iA = a(this.b, baVar.b);
        if (iA == 0) {
            int iA2 = a(this.c, baVar.c);
            return iA2 == 0 ? a(this.d, baVar.d) : iA2;
        }
        return iA;
    }

    public int c() {
        return this.b;
    }

    public int d() {
        return this.c;
    }

    public int e() {
        return this.d;
    }
}

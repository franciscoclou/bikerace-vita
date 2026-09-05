package org.a.a.a.a.a.a;

import com.flurry.android.Constants;

/* JADX INFO: compiled from: SimpleBase64Encoder.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final char[] f1571a = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    public static String a(byte[] bArr) {
        int length = bArr.length;
        StringBuffer stringBuffer = new StringBuffer(((length + 2) / 3) * 4);
        int i = 0;
        while (length >= 3) {
            stringBuffer.append(a(((bArr[i] & Constants.UNKNOWN) << 16) | ((bArr[i + 1] & Constants.UNKNOWN) << 8) | (bArr[i + 2] & Constants.UNKNOWN), 4));
            i += 3;
            length -= 3;
        }
        if (length == 2) {
            stringBuffer.append(a(((bArr[i] & Constants.UNKNOWN) << 8) | (bArr[i + 1] & Constants.UNKNOWN), 3));
        }
        if (length == 1) {
            stringBuffer.append(a(bArr[i] & Constants.UNKNOWN, 2));
        }
        return stringBuffer.toString();
    }

    public static byte[] a(String str) {
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        byte[] bArr = new byte[(length * 3) / 4];
        int i = 0;
        int i2 = length;
        int i3 = 0;
        while (i2 >= 4) {
            long jA = a(bytes, i3, 4);
            i2 -= 4;
            i3 += 4;
            for (int i4 = 2; i4 >= 0; i4--) {
                bArr[i + i4] = (byte) (255 & jA);
                jA >>= 8;
            }
            i += 3;
        }
        if (i2 == 3) {
            long jA2 = a(bytes, i3, 3);
            for (int i5 = 1; i5 >= 0; i5--) {
                bArr[i + i5] = (byte) (255 & jA2);
                jA2 >>= 8;
            }
        }
        if (i2 == 2) {
            bArr[i] = (byte) (a(bytes, i3, 2) & 255);
        }
        return bArr;
    }

    private static final String a(long j, int i) {
        StringBuffer stringBuffer = new StringBuffer(i);
        while (i > 0) {
            i--;
            stringBuffer.append(f1571a[(int) (63 & j)]);
            j >>= 6;
        }
        return stringBuffer.toString();
    }

    private static final long a(byte[] bArr, int i, int i2) {
        int i3 = 0;
        long j = 0;
        while (i2 > 0) {
            i2--;
            int i4 = i + 1;
            byte b = bArr[i];
            long j2 = b == 47 ? 1L : 0L;
            if (b >= 48 && b <= 57) {
                j2 = (b + 2) - 48;
            }
            if (b >= 65 && b <= 90) {
                j2 = (b + 12) - 65;
            }
            if (b >= 97 && b <= 122) {
                j2 = (b + 38) - 97;
            }
            j += j2 << i3;
            i3 += 6;
            i = i4;
        }
        return j;
    }
}

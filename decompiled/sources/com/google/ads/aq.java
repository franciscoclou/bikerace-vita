package com.google.ads;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class aq {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final char[] f625a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private static final char[] b = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".toCharArray();
    private static final byte[] c = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9};
    private static final byte[] d = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9};

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static char[] a(byte[] bArr, int i, int i2, char[] cArr, int i3, char[] cArr2) {
        int i4 = (i2 > 2 ? (bArr[i + 2] << 24) >>> 24 : 0) | (i2 > 1 ? (bArr[i + 1] << 24) >>> 16 : 0) | (i2 > 0 ? (bArr[i] << 24) >>> 8 : 0);
        switch (i2) {
            case 1:
                cArr[i3] = cArr2[i4 >>> 18];
                cArr[i3 + 1] = cArr2[(i4 >>> 12) & 63];
                cArr[i3 + 2] = '=';
                cArr[i3 + 3] = '=';
                return cArr;
            case 2:
                cArr[i3] = cArr2[i4 >>> 18];
                cArr[i3 + 1] = cArr2[(i4 >>> 12) & 63];
                cArr[i3 + 2] = cArr2[(i4 >>> 6) & 63];
                cArr[i3 + 3] = '=';
                return cArr;
            case 3:
                cArr[i3] = cArr2[i4 >>> 18];
                cArr[i3 + 1] = cArr2[(i4 >>> 12) & 63];
                cArr[i3 + 2] = cArr2[(i4 >>> 6) & 63];
                cArr[i3 + 3] = cArr2[i4 & 63];
                return cArr;
            default:
                return cArr;
        }
    }

    public static String a(byte[] bArr, boolean z) {
        return a(bArr, 0, bArr.length, b, z);
    }

    public static String a(byte[] bArr, int i, int i2, char[] cArr, boolean z) {
        char[] cArrA = a(bArr, i, i2, cArr, Integer.MAX_VALUE);
        int length = cArrA.length;
        while (!z && length > 0 && cArrA[length - 1] == '=') {
            length--;
        }
        return new String(cArrA, 0, length);
    }

    public static char[] a(byte[] bArr, int i, int i2, char[] cArr, int i3) {
        int i4 = ((i2 + 2) / 3) * 4;
        char[] cArr2 = new char[i4 + (i4 / i3)];
        int i5 = i2 - 2;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i8 < i5) {
            int i9 = ((bArr[i8 + i] << 24) >>> 8) | ((bArr[(i8 + 1) + i] << 24) >>> 16) | ((bArr[(i8 + 2) + i] << 24) >>> 24);
            cArr2[i7] = cArr[i9 >>> 18];
            cArr2[i7 + 1] = cArr[(i9 >>> 12) & 63];
            cArr2[i7 + 2] = cArr[(i9 >>> 6) & 63];
            cArr2[i7 + 3] = cArr[i9 & 63];
            int i10 = i6 + 4;
            if (i10 == i3) {
                cArr2[i7 + 4] = '\n';
                i7++;
                i10 = 0;
            }
            i8 += 3;
            i7 += 4;
            i6 = i10;
        }
        if (i8 < i2) {
            a(bArr, i8 + i, i2 - i8, cArr2, i7, cArr);
            if (i6 + 4 == i3) {
                cArr2[i7 + 4] = '\n';
                i7++;
            }
            int i11 = i7 + 4;
        }
        return cArr2;
    }

    private static int a(char[] cArr, int i, byte[] bArr, int i2, byte[] bArr2) {
        if (cArr[i + 2] == '=') {
            bArr[i2] = (byte) ((((bArr2[cArr[i]] << 24) >>> 6) | ((bArr2[cArr[i + 1]] << 24) >>> 12)) >>> 16);
            return 1;
        }
        if (cArr[i + 3] == '=') {
            int i3 = ((bArr2[cArr[i]] << 24) >>> 6) | ((bArr2[cArr[i + 1]] << 24) >>> 12) | ((bArr2[cArr[i + 2]] << 24) >>> 18);
            bArr[i2] = (byte) (i3 >>> 16);
            bArr[i2 + 1] = (byte) (i3 >>> 8);
            return 2;
        }
        int i4 = ((bArr2[cArr[i]] << 24) >>> 6) | ((bArr2[cArr[i + 1]] << 24) >>> 12) | ((bArr2[cArr[i + 2]] << 24) >>> 18) | ((bArr2[cArr[i + 3]] << 24) >>> 24);
        bArr[i2] = (byte) (i4 >> 16);
        bArr[i2 + 1] = (byte) (i4 >> 8);
        bArr[i2 + 2] = (byte) i4;
        return 3;
    }

    public static byte[] a(String str) {
        char[] charArray = str.toCharArray();
        return a(charArray, 0, charArray.length);
    }

    public static byte[] a(char[] cArr, int i, int i2) {
        return a(cArr, i, i2, c);
    }

    public static byte[] a(char[] cArr, int i, int i2, byte[] bArr) {
        int iA;
        byte[] bArr2 = new byte[((i2 * 3) / 4) + 2];
        int iA2 = 0;
        char[] cArr2 = new char[4];
        boolean z = false;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            char c2 = cArr[i3 + i];
            char c3 = (char) (c2 & 127);
            byte b2 = bArr[c3];
            if (c3 == c2 && b2 < -5) {
                throw new ap("Bad Base64 input character at " + i3 + ": " + ((int) cArr[i3 + i]) + "(decimal)");
            }
            if (b2 < -1) {
                iA = iA2;
            } else if (c3 == '=') {
                if (z) {
                    iA = iA2;
                } else {
                    if (i3 < 2) {
                        throw new ap("Invalid padding char found in position " + i3);
                    }
                    z = true;
                    char c4 = (char) (cArr[(i2 - 1) + i] & 127);
                    if (c4 != '=' && c4 != '\n') {
                        throw new ap("encoded value has invalid trailing char");
                    }
                    iA = iA2;
                }
            } else {
                if (z) {
                    throw new ap("Data found after trailing padding char at index " + i3);
                }
                int i5 = i4 + 1;
                cArr2[i4] = c3;
                if (i5 == 4) {
                    iA = iA2 + a(cArr2, 0, bArr2, iA2, bArr);
                    i4 = 0;
                } else {
                    i4 = i5;
                    iA = iA2;
                }
            }
            i3++;
            iA2 = iA;
        }
        if (i4 != 0) {
            if (i4 == 1) {
                throw new ap("single trailing character at offset " + (i2 - 1));
            }
            cArr2[i4] = '=';
            iA2 += a(cArr2, 0, bArr2, iA2, bArr);
        }
        byte[] bArr3 = new byte[iA2];
        System.arraycopy(bArr2, 0, bArr3, 0, iA2);
        return bArr3;
    }
}

package org.apache.commons.a.a;

/* JADX INFO: compiled from: Base64.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final byte[] f1607a = "\r\n".getBytes();
    private static byte[] b = new byte[255];
    private static byte[] c = new byte[64];

    static {
        int i = 0;
        for (int i2 = 0; i2 < 255; i2++) {
            b[i2] = -1;
        }
        for (int i3 = 90; i3 >= 65; i3--) {
            b[i3] = (byte) (i3 - 65);
        }
        for (int i4 = 122; i4 >= 97; i4--) {
            b[i4] = (byte) ((i4 - 97) + 26);
        }
        for (int i5 = 57; i5 >= 48; i5--) {
            b[i5] = (byte) ((i5 - 48) + 52);
        }
        b[43] = 62;
        b[47] = 63;
        for (int i6 = 0; i6 <= 25; i6++) {
            c[i6] = (byte) (i6 + 65);
        }
        int i7 = 26;
        int i8 = 0;
        while (i7 <= 51) {
            c[i7] = (byte) (i8 + 97);
            i7++;
            i8++;
        }
        int i9 = 52;
        while (i9 <= 61) {
            c[i9] = (byte) (i + 48);
            i9++;
            i++;
        }
        c[62] = 43;
        c[63] = 47;
    }

    private static boolean a(byte b2) {
        return b2 == 61 || b[b2] != -1;
    }

    public static byte[] a(byte[] bArr) {
        return a(bArr, false);
    }

    public static byte[] a(byte[] bArr, boolean z) {
        int length;
        int i;
        int length2;
        int length3;
        int length4 = bArr.length * 8;
        int i2 = length4 % 24;
        int i3 = length4 / 24;
        int iCeil = 0;
        if (i2 != 0) {
            length = (i3 + 1) * 4;
        } else {
            length = i3 * 4;
        }
        if (z) {
            iCeil = f1607a.length == 0 ? 0 : (int) Math.ceil(length / 76.0f);
            length += f1607a.length * iCeil;
        }
        byte[] bArr2 = new byte[length];
        int i4 = 76;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i6 < i3) {
            int i8 = i6 * 3;
            byte b2 = bArr[i8];
            byte b3 = bArr[i8 + 1];
            byte b4 = bArr[i8 + 2];
            byte b5 = (byte) (b3 & 15);
            byte b6 = (byte) (b2 & 3);
            byte b7 = (b2 & (-128)) == 0 ? (byte) (b2 >> 2) : (byte) ((b2 >> 2) ^ 192);
            byte b8 = (b3 & (-128)) == 0 ? (byte) (b3 >> 4) : (byte) ((b3 >> 4) ^ 240);
            byte b9 = (b4 & (-128)) == 0 ? (byte) (b4 >> 6) : (byte) ((b4 >> 6) ^ 252);
            bArr2[i7] = c[b7];
            bArr2[i7 + 1] = c[b8 | (b6 << 4)];
            bArr2[i7 + 2] = c[b9 | (b5 << 2)];
            bArr2[i7 + 3] = c[b4 & 63];
            int i9 = i7 + 4;
            if (z && i9 == i4) {
                System.arraycopy(f1607a, 0, bArr2, i9, f1607a.length);
                i = i5 + 1;
                length2 = ((i + 1) * 76) + (f1607a.length * i);
                length3 = f1607a.length + i9;
            } else {
                i = i5;
                length2 = i4;
                length3 = i9;
            }
            i6++;
            i7 = length3;
            i4 = length2;
            i5 = i;
        }
        int i10 = i6 * 3;
        if (i2 == 8) {
            byte b10 = bArr[i10];
            byte b11 = (byte) (b10 & 3);
            bArr2[i7] = c[(b10 & (-128)) == 0 ? (byte) (b10 >> 2) : (byte) ((b10 >> 2) ^ 192)];
            bArr2[i7 + 1] = c[b11 << 4];
            bArr2[i7 + 2] = 61;
            bArr2[i7 + 3] = 61;
        } else if (i2 == 16) {
            byte b12 = bArr[i10];
            byte b13 = bArr[i10 + 1];
            byte b14 = (byte) (b13 & 15);
            byte b15 = (byte) (b12 & 3);
            byte b16 = (b12 & (-128)) == 0 ? (byte) (b12 >> 2) : (byte) ((b12 >> 2) ^ 192);
            byte b17 = (b13 & (-128)) == 0 ? (byte) (b13 >> 4) : (byte) ((b13 >> 4) ^ 240);
            bArr2[i7] = c[b16];
            bArr2[i7 + 1] = c[b17 | (b15 << 4)];
            bArr2[i7 + 2] = c[b14 << 2];
            bArr2[i7 + 3] = 61;
        }
        if (z && i5 < iCeil) {
            System.arraycopy(f1607a, 0, bArr2, length - f1607a.length, f1607a.length);
        }
        return bArr2;
    }

    public static byte[] b(byte[] bArr) {
        byte[] bArrC = c(bArr);
        if (bArrC.length == 0) {
            return new byte[0];
        }
        int length = bArrC.length / 4;
        int length2 = bArrC.length;
        while (bArrC[length2 - 1] == 61) {
            length2--;
            if (length2 == 0) {
                return new byte[0];
            }
        }
        byte[] bArr2 = new byte[length2 - length];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 4;
            byte b2 = bArrC[i3 + 2];
            byte b3 = bArrC[i3 + 3];
            byte b4 = b[bArrC[i3]];
            byte b5 = b[bArrC[i3 + 1]];
            if (b2 != 61 && b3 != 61) {
                byte b6 = b[b2];
                byte b7 = b[b3];
                bArr2[i] = (byte) ((b4 << 2) | (b5 >> 4));
                bArr2[i + 1] = (byte) (((b5 & 15) << 4) | ((b6 >> 2) & 15));
                bArr2[i + 2] = (byte) ((b6 << 6) | b7);
            } else if (b2 == 61) {
                bArr2[i] = (byte) ((b5 >> 4) | (b4 << 2));
            } else if (b3 == 61) {
                byte b8 = b[b2];
                bArr2[i] = (byte) ((b4 << 2) | (b5 >> 4));
                bArr2[i + 1] = (byte) (((b5 & 15) << 4) | ((b8 >> 2) & 15));
            }
            i += 3;
        }
        return bArr2;
    }

    static byte[] c(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            if (a(bArr[i2])) {
                bArr2[i] = bArr[i2];
                i++;
            }
        }
        byte[] bArr3 = new byte[i];
        System.arraycopy(bArr2, 0, bArr3, 0, i);
        return bArr3;
    }
}

package org.apache.commons.a.a;

/* JADX INFO: compiled from: Hex.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final char[] f1608a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static byte[] a(char[] cArr) throws org.apache.commons.a.a {
        int i = 0;
        int length = cArr.length;
        if ((length & 1) != 0) {
            throw new org.apache.commons.a.a("Odd number of characters.");
        }
        byte[] bArr = new byte[length >> 1];
        int i2 = 0;
        while (i < length) {
            int iA = a(cArr[i], i) << 4;
            int i3 = i + 1;
            int iA2 = iA | a(cArr[i3], i3);
            i = i3 + 1;
            bArr[i2] = (byte) (iA2 & 255);
            i2++;
        }
        return bArr;
    }

    protected static int a(char c, int i) throws org.apache.commons.a.a {
        int iDigit = Character.digit(c, 16);
        if (iDigit == -1) {
            throw new org.apache.commons.a.a(new StringBuffer().append("Illegal hexadecimal charcter ").append(c).append(" at index ").append(i).toString());
        }
        return iDigit;
    }

    public static char[] a(byte[] bArr) {
        int i = 0;
        int length = bArr.length;
        char[] cArr = new char[length << 1];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr[i] = f1608a[(bArr[i2] & 240) >>> 4];
            i = i3 + 1;
            cArr[i3] = f1608a[bArr[i2] & 15];
        }
        return cArr;
    }
}

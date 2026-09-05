package com.google.ads.util;

import com.flurry.android.Constants;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ boolean f717a;

    public abstract class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public byte[] f718a;
        public int b;
    }

    static {
        f717a = !c.class.desiredAssertionStatus();
    }

    public static byte[] a(String str) {
        return a(str.getBytes(), 0);
    }

    public static byte[] a(byte[] bArr, int i) {
        return a(bArr, 0, bArr.length, i);
    }

    public static byte[] a(byte[] bArr, int i, int i2, int i3) {
        b bVar = new b(i3, new byte[(i2 * 3) / 4]);
        if (!bVar.a(bArr, i, i2, true)) {
            throw new IllegalArgumentException("bad base-64");
        }
        if (bVar.b == bVar.f718a.length) {
            return bVar.f718a;
        }
        byte[] bArr2 = new byte[bVar.b];
        System.arraycopy(bVar.f718a, 0, bArr2, 0, bVar.b);
        return bArr2;
    }

    public class b extends a {
        private static final int[] c = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private static final int[] d = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private int e;
        private int f;
        private final int[] g;

        public b(int i, byte[] bArr) {
            this.f718a = bArr;
            this.g = (i & 8) == 0 ? c : d;
            this.e = 0;
            this.f = 0;
        }

        public boolean a(byte[] bArr, int i, int i2, boolean z) {
            int i3;
            if (this.e == 6) {
                return false;
            }
            int i4 = i2 + i;
            int i5 = this.e;
            int i6 = this.f;
            int i7 = 0;
            byte[] bArr2 = this.f718a;
            int[] iArr = this.g;
            int i8 = i;
            while (true) {
                if (i8 < i4) {
                    if (i5 == 0) {
                        while (i8 + 4 <= i4 && (i6 = (iArr[bArr[i8] & Constants.UNKNOWN] << 18) | (iArr[bArr[i8 + 1] & Constants.UNKNOWN] << 12) | (iArr[bArr[i8 + 2] & Constants.UNKNOWN] << 6) | iArr[bArr[i8 + 3] & Constants.UNKNOWN]) >= 0) {
                            bArr2[i7 + 2] = (byte) i6;
                            bArr2[i7 + 1] = (byte) (i6 >> 8);
                            bArr2[i7] = (byte) (i6 >> 16);
                            i7 += 3;
                            i8 += 4;
                        }
                        if (i8 >= i4) {
                            i3 = i6;
                        }
                    }
                    int i9 = i8 + 1;
                    int i10 = iArr[bArr[i8] & Constants.UNKNOWN];
                    switch (i5) {
                        case 0:
                            if (i10 >= 0) {
                                i5++;
                                i6 = i10;
                            } else if (i10 != -1) {
                                this.e = 6;
                                return false;
                            }
                            i5 = i5;
                            i8 = i9;
                            break;
                        case 1:
                            if (i10 >= 0) {
                                i6 = (i6 << 6) | i10;
                                i5++;
                            } else if (i10 != -1) {
                                this.e = 6;
                                return false;
                            }
                            i5 = i5;
                            i8 = i9;
                            break;
                        case 2:
                            if (i10 >= 0) {
                                i6 = (i6 << 6) | i10;
                                i5++;
                            } else if (i10 == -2) {
                                bArr2[i7] = (byte) (i6 >> 4);
                                i5 = 4;
                                i7++;
                            } else if (i10 != -1) {
                                this.e = 6;
                                return false;
                            }
                            i5 = i5;
                            i8 = i9;
                            break;
                        case 3:
                            if (i10 >= 0) {
                                i6 = (i6 << 6) | i10;
                                bArr2[i7 + 2] = (byte) i6;
                                bArr2[i7 + 1] = (byte) (i6 >> 8);
                                bArr2[i7] = (byte) (i6 >> 16);
                                i7 += 3;
                                i5 = 0;
                            } else if (i10 == -2) {
                                bArr2[i7 + 1] = (byte) (i6 >> 2);
                                bArr2[i7] = (byte) (i6 >> 10);
                                i7 += 2;
                                i5 = 5;
                            } else if (i10 != -1) {
                                this.e = 6;
                                return false;
                            }
                            i5 = i5;
                            i8 = i9;
                            break;
                        case 4:
                            if (i10 == -2) {
                                i5++;
                            } else if (i10 != -1) {
                                this.e = 6;
                                return false;
                            }
                            i5 = i5;
                            i8 = i9;
                            break;
                        case 5:
                            if (i10 != -1) {
                                this.e = 6;
                                return false;
                            }
                            i5 = i5;
                            i8 = i9;
                            break;
                        default:
                            i5 = i5;
                            i8 = i9;
                            break;
                    }
                } else {
                    i3 = i6;
                }
            }
            if (!z) {
                this.e = i5;
                this.f = i3;
                this.b = i7;
                return true;
            }
            switch (i5) {
                case 1:
                    this.e = 6;
                    return false;
                case 2:
                    bArr2[i7] = (byte) (i3 >> 4);
                    i7++;
                    break;
                case 3:
                    int i11 = i7 + 1;
                    bArr2[i7] = (byte) (i3 >> 10);
                    i7 = i11 + 1;
                    bArr2[i11] = (byte) (i3 >> 2);
                    break;
                case 4:
                    this.e = 6;
                    return false;
            }
            this.e = i5;
            this.b = i7;
            return true;
        }
    }

    private c() {
    }
}

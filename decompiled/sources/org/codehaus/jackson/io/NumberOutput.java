package org.codehaus.jackson.io;

import com.facebook.AppEventsConstants;
import com.facebook.widget.PlacePickerFragment;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class NumberOutput {
    static final byte[] FULL_TRIPLETS_B;
    private static final char NULL_CHAR = 0;
    static final String[] sSmallIntStrs;
    static final String[] sSmallIntStrs2;
    private static int MILLION = 1000000;
    private static int BILLION = 1000000000;
    private static long TEN_BILLION_L = 10000000000L;
    private static long THOUSAND_L = 1000;
    private static long MIN_INT_AS_LONG = -2147483648L;
    private static long MAX_INT_AS_LONG = 2147483647L;
    static final String SMALLEST_LONG = String.valueOf(Long.MIN_VALUE);
    static final char[] LEADING_TRIPLETS = new char[4000];
    static final char[] FULL_TRIPLETS = new char[4000];

    static {
        int i = 0;
        int i2 = 0;
        while (i < 10) {
            char c = (char) (i + 48);
            char c2 = i == 0 ? (char) 0 : c;
            int i3 = 0;
            while (i3 < 10) {
                char c3 = (char) (i3 + 48);
                char c4 = (i == 0 && i3 == 0) ? (char) 0 : c3;
                int i4 = i2;
                for (int i5 = 0; i5 < 10; i5++) {
                    char c5 = (char) (i5 + 48);
                    LEADING_TRIPLETS[i4] = c2;
                    LEADING_TRIPLETS[i4 + 1] = c4;
                    LEADING_TRIPLETS[i4 + 2] = c5;
                    FULL_TRIPLETS[i4] = c;
                    FULL_TRIPLETS[i4 + 1] = c3;
                    FULL_TRIPLETS[i4 + 2] = c5;
                    i4 += 4;
                }
                i3++;
                i2 = i4;
            }
            i++;
        }
        FULL_TRIPLETS_B = new byte[4000];
        for (int i6 = 0; i6 < 4000; i6++) {
            FULL_TRIPLETS_B[i6] = (byte) FULL_TRIPLETS[i6];
        }
        sSmallIntStrs = new String[]{AppEventsConstants.EVENT_PARAM_VALUE_NO, AppEventsConstants.EVENT_PARAM_VALUE_YES, "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        sSmallIntStrs2 = new String[]{"-1", "-2", "-3", "-4", "-5", "-6", "-7", "-8", "-9", "-10"};
    }

    public static int outputInt(int i, char[] cArr, int i2) {
        int iOutputLeadingTriplet;
        if (i < 0) {
            if (i == Integer.MIN_VALUE) {
                return outputLong(i, cArr, i2);
            }
            cArr[i2] = '-';
            i = -i;
            i2++;
        }
        if (i < MILLION) {
            if (i < 1000) {
                if (i < 10) {
                    int i3 = i2 + 1;
                    cArr[i2] = (char) (i + 48);
                    return i3;
                }
                return outputLeadingTriplet(i, cArr, i2);
            }
            int i4 = i / PlacePickerFragment.DEFAULT_RADIUS_IN_METERS;
            return outputFullTriplet(i - (i4 * PlacePickerFragment.DEFAULT_RADIUS_IN_METERS), cArr, outputLeadingTriplet(i4, cArr, i2));
        }
        boolean z = i >= BILLION;
        if (z) {
            i -= BILLION;
            if (i >= BILLION) {
                i -= BILLION;
                cArr[i2] = '2';
                i2++;
            } else {
                cArr[i2] = '1';
                i2++;
            }
        }
        int i5 = i / PlacePickerFragment.DEFAULT_RADIUS_IN_METERS;
        int i6 = i - (i5 * PlacePickerFragment.DEFAULT_RADIUS_IN_METERS);
        int i7 = i5 / PlacePickerFragment.DEFAULT_RADIUS_IN_METERS;
        int i8 = i5 - (i7 * PlacePickerFragment.DEFAULT_RADIUS_IN_METERS);
        if (z) {
            iOutputLeadingTriplet = outputFullTriplet(i7, cArr, i2);
        } else {
            iOutputLeadingTriplet = outputLeadingTriplet(i7, cArr, i2);
        }
        return outputFullTriplet(i6, cArr, outputFullTriplet(i8, cArr, iOutputLeadingTriplet));
    }

    public static int outputInt(int i, byte[] bArr, int i2) {
        int iOutputLeadingTriplet;
        if (i < 0) {
            if (i == Integer.MIN_VALUE) {
                return outputLong(i, bArr, i2);
            }
            bArr[i2] = 45;
            i = -i;
            i2++;
        }
        if (i < MILLION) {
            if (i < 1000) {
                if (i < 10) {
                    int i3 = i2 + 1;
                    bArr[i2] = (byte) (i + 48);
                    return i3;
                }
                return outputLeadingTriplet(i, bArr, i2);
            }
            int i4 = i / PlacePickerFragment.DEFAULT_RADIUS_IN_METERS;
            return outputFullTriplet(i - (i4 * PlacePickerFragment.DEFAULT_RADIUS_IN_METERS), bArr, outputLeadingTriplet(i4, bArr, i2));
        }
        boolean z = i >= BILLION;
        if (z) {
            i -= BILLION;
            if (i >= BILLION) {
                i -= BILLION;
                bArr[i2] = 50;
                i2++;
            } else {
                bArr[i2] = 49;
                i2++;
            }
        }
        int i5 = i / PlacePickerFragment.DEFAULT_RADIUS_IN_METERS;
        int i6 = i - (i5 * PlacePickerFragment.DEFAULT_RADIUS_IN_METERS);
        int i7 = i5 / PlacePickerFragment.DEFAULT_RADIUS_IN_METERS;
        int i8 = i5 - (i7 * PlacePickerFragment.DEFAULT_RADIUS_IN_METERS);
        if (z) {
            iOutputLeadingTriplet = outputFullTriplet(i7, bArr, i2);
        } else {
            iOutputLeadingTriplet = outputLeadingTriplet(i7, bArr, i2);
        }
        return outputFullTriplet(i6, bArr, outputFullTriplet(i8, bArr, iOutputLeadingTriplet));
    }

    public static int outputLong(long j, char[] cArr, int i) {
        if (j < 0) {
            if (j > MIN_INT_AS_LONG) {
                return outputInt((int) j, cArr, i);
            }
            if (j == Long.MIN_VALUE) {
                int length = SMALLEST_LONG.length();
                SMALLEST_LONG.getChars(0, length, cArr, i);
                return i + length;
            }
            cArr[i] = '-';
            j = -j;
            i++;
        } else if (j <= MAX_INT_AS_LONG) {
            return outputInt((int) j, cArr, i);
        }
        int iCalcLongStrLength = i + calcLongStrLength(j);
        int i2 = iCalcLongStrLength;
        while (j > MAX_INT_AS_LONG) {
            i2 -= 3;
            long j2 = j / THOUSAND_L;
            outputFullTriplet((int) (j - (THOUSAND_L * j2)), cArr, i2);
            j = j2;
        }
        int i3 = i2;
        int i4 = (int) j;
        while (i4 >= 1000) {
            int i5 = i3 - 3;
            int i6 = i4 / PlacePickerFragment.DEFAULT_RADIUS_IN_METERS;
            outputFullTriplet(i4 - (i6 * PlacePickerFragment.DEFAULT_RADIUS_IN_METERS), cArr, i5);
            i4 = i6;
            i3 = i5;
        }
        outputLeadingTriplet(i4, cArr, i);
        return iCalcLongStrLength;
    }

    public static int outputLong(long j, byte[] bArr, int i) {
        if (j < 0) {
            if (j > MIN_INT_AS_LONG) {
                return outputInt((int) j, bArr, i);
            }
            if (j == Long.MIN_VALUE) {
                int length = SMALLEST_LONG.length();
                int i2 = 0;
                int i3 = i;
                while (i2 < length) {
                    bArr[i3] = (byte) SMALLEST_LONG.charAt(i2);
                    i2++;
                    i3++;
                }
                return i3;
            }
            bArr[i] = 45;
            j = -j;
            i++;
        } else if (j <= MAX_INT_AS_LONG) {
            return outputInt((int) j, bArr, i);
        }
        int iCalcLongStrLength = i + calcLongStrLength(j);
        int i4 = iCalcLongStrLength;
        while (j > MAX_INT_AS_LONG) {
            i4 -= 3;
            long j2 = j / THOUSAND_L;
            outputFullTriplet((int) (j - (THOUSAND_L * j2)), bArr, i4);
            j = j2;
        }
        int i5 = i4;
        int i6 = (int) j;
        while (i6 >= 1000) {
            int i7 = i5 - 3;
            int i8 = i6 / PlacePickerFragment.DEFAULT_RADIUS_IN_METERS;
            outputFullTriplet(i6 - (i8 * PlacePickerFragment.DEFAULT_RADIUS_IN_METERS), bArr, i7);
            i6 = i8;
            i5 = i7;
        }
        outputLeadingTriplet(i6, bArr, i);
        return iCalcLongStrLength;
    }

    public static String toString(int i) {
        if (i < sSmallIntStrs.length) {
            if (i >= 0) {
                return sSmallIntStrs[i];
            }
            int i2 = (-i) - 1;
            if (i2 < sSmallIntStrs2.length) {
                return sSmallIntStrs2[i2];
            }
        }
        return Integer.toString(i);
    }

    public static String toString(long j) {
        return (j > 2147483647L || j < -2147483648L) ? Long.toString(j) : toString((int) j);
    }

    public static String toString(double d) {
        return Double.toString(d);
    }

    private static int outputLeadingTriplet(int i, char[] cArr, int i2) {
        int i3 = i << 2;
        int i4 = i3 + 1;
        char c = LEADING_TRIPLETS[i3];
        if (c != 0) {
            cArr[i2] = c;
            i2++;
        }
        int i5 = i4 + 1;
        char c2 = LEADING_TRIPLETS[i4];
        if (c2 != 0) {
            cArr[i2] = c2;
            i2++;
        }
        int i6 = i2 + 1;
        cArr[i2] = LEADING_TRIPLETS[i5];
        return i6;
    }

    private static int outputLeadingTriplet(int i, byte[] bArr, int i2) {
        int i3 = i << 2;
        int i4 = i3 + 1;
        char c = LEADING_TRIPLETS[i3];
        if (c != 0) {
            bArr[i2] = (byte) c;
            i2++;
        }
        int i5 = i4 + 1;
        char c2 = LEADING_TRIPLETS[i4];
        if (c2 != 0) {
            bArr[i2] = (byte) c2;
            i2++;
        }
        int i6 = i2 + 1;
        bArr[i2] = (byte) LEADING_TRIPLETS[i5];
        return i6;
    }

    private static int outputFullTriplet(int i, char[] cArr, int i2) {
        int i3 = i << 2;
        int i4 = i2 + 1;
        int i5 = i3 + 1;
        cArr[i2] = FULL_TRIPLETS[i3];
        int i6 = i4 + 1;
        cArr[i4] = FULL_TRIPLETS[i5];
        int i7 = i6 + 1;
        cArr[i6] = FULL_TRIPLETS[i5 + 1];
        return i7;
    }

    private static int outputFullTriplet(int i, byte[] bArr, int i2) {
        int i3 = i << 2;
        int i4 = i2 + 1;
        int i5 = i3 + 1;
        bArr[i2] = FULL_TRIPLETS_B[i3];
        int i6 = i4 + 1;
        bArr[i4] = FULL_TRIPLETS_B[i5];
        int i7 = i6 + 1;
        bArr[i6] = FULL_TRIPLETS_B[i5 + 1];
        return i7;
    }

    private static int calcLongStrLength(long j) {
        int i = 10;
        for (long j2 = TEN_BILLION_L; j >= j2 && i != 19; j2 = (j2 << 1) + (j2 << 3)) {
            i++;
        }
        return i;
    }
}

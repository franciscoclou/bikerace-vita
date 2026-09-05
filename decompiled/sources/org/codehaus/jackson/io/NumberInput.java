package org.codehaus.jackson.io;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class NumberInput {
    static final long L_BILLION = 1000000000;
    public static final String NASTY_SMALL_DOUBLE = "2.2250738585072012e-308";
    static final String MIN_LONG_STR_NO_SIGN = String.valueOf(Long.MIN_VALUE).substring(1);
    static final String MAX_LONG_STR = String.valueOf(Long.MAX_VALUE);

    public static final int parseInt(char[] cArr, int i, int i2) {
        int i3 = cArr[i] - '0';
        int i4 = i2 + i;
        int i5 = i + 1;
        if (i5 < i4) {
            int i6 = (i3 * 10) + (cArr[i5] - '0');
            int i7 = i5 + 1;
            if (i7 < i4) {
                int i8 = (i6 * 10) + (cArr[i7] - '0');
                int i9 = i7 + 1;
                if (i9 < i4) {
                    int i10 = (i8 * 10) + (cArr[i9] - '0');
                    int i11 = i9 + 1;
                    if (i11 < i4) {
                        int i12 = (i10 * 10) + (cArr[i11] - '0');
                        int i13 = i11 + 1;
                        if (i13 < i4) {
                            int i14 = (i12 * 10) + (cArr[i13] - '0');
                            int i15 = i13 + 1;
                            if (i15 < i4) {
                                int i16 = (i14 * 10) + (cArr[i15] - '0');
                                int i17 = i15 + 1;
                                if (i17 < i4) {
                                    int i18 = (i16 * 10) + (cArr[i17] - '0');
                                    int i19 = i17 + 1;
                                    if (i19 < i4) {
                                        return (i18 * 10) + (cArr[i19] - '0');
                                    }
                                    return i18;
                                }
                                return i16;
                            }
                            return i14;
                        }
                        return i12;
                    }
                    return i10;
                }
                return i8;
            }
            return i6;
        }
        return i3;
    }

    public static final int parseInt(String str) {
        int i = 1;
        char cCharAt = str.charAt(0);
        int length = str.length();
        boolean z = cCharAt == '-';
        if (z) {
            if (length == 1 || length > 10) {
                return Integer.parseInt(str);
            }
            cCharAt = str.charAt(1);
            i = 2;
        } else if (length > 9) {
            return Integer.parseInt(str);
        }
        if (cCharAt > '9' || cCharAt < '0') {
            return Integer.parseInt(str);
        }
        int i2 = cCharAt - '0';
        if (i < length) {
            int i3 = i + 1;
            char cCharAt2 = str.charAt(i);
            if (cCharAt2 > '9' || cCharAt2 < '0') {
                return Integer.parseInt(str);
            }
            i2 = (i2 * 10) + (cCharAt2 - '0');
            if (i3 < length) {
                int i4 = i3 + 1;
                char cCharAt3 = str.charAt(i3);
                if (cCharAt3 > '9' || cCharAt3 < '0') {
                    return Integer.parseInt(str);
                }
                i2 = (i2 * 10) + (cCharAt3 - '0');
                if (i4 < length) {
                    while (true) {
                        int i5 = i4 + 1;
                        char cCharAt4 = str.charAt(i4);
                        if (cCharAt4 > '9' || cCharAt4 < '0') {
                            break;
                        }
                        i2 = (i2 * 10) + (cCharAt4 - '0');
                        if (i5 < length) {
                            i4 = i5;
                        }
                    }
                    return Integer.parseInt(str);
                }
            }
        }
        return z ? -i2 : i2;
    }

    public static final long parseLong(char[] cArr, int i, int i2) {
        int i3 = i2 - 9;
        return (((long) parseInt(cArr, i, i3)) * L_BILLION) + ((long) parseInt(cArr, i3 + i, 9));
    }

    public static final long parseLong(String str) {
        return str.length() <= 9 ? parseInt(str) : Long.parseLong(str);
    }

    public static final boolean inLongRange(char[] cArr, int i, int i2, boolean z) {
        String str = z ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
        int length = str.length();
        if (i2 < length) {
            return true;
        }
        if (i2 > length) {
            return false;
        }
        for (int i3 = 0; i3 < length; i3++) {
            int iCharAt = cArr[i + i3] - str.charAt(i3);
            if (iCharAt != 0) {
                return iCharAt < 0;
            }
        }
        return true;
    }

    public static final boolean inLongRange(String str, boolean z) {
        String str2 = z ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
        int length = str2.length();
        int length2 = str.length();
        if (length2 < length) {
            return true;
        }
        if (length2 > length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            int iCharAt = str.charAt(i) - str2.charAt(i);
            if (iCharAt != 0) {
                return iCharAt < 0;
            }
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0049  */
    public static int parseAsInt(String str, int i) {
        String strTrim;
        int length;
        int length2;
        String strSubstring;
        int i2 = 0;
        if (str != null && (length = (strTrim = str.trim()).length()) != 0) {
            if (0 >= length) {
                length2 = length;
                strSubstring = strTrim;
            } else {
                char cCharAt = strTrim.charAt(0);
                if (cCharAt == '+') {
                    strSubstring = strTrim.substring(1);
                    length2 = strSubstring.length();
                } else if (cCharAt == '-') {
                    i2 = 1;
                    length2 = length;
                    strSubstring = strTrim;
                } else {
                    length2 = length;
                    strSubstring = strTrim;
                }
            }
            while (i2 < length2) {
                char cCharAt2 = strSubstring.charAt(i2);
                if (cCharAt2 <= '9' && cCharAt2 >= '0') {
                    i2++;
                } else {
                    try {
                        return (int) parseDouble(strSubstring);
                    } catch (NumberFormatException e) {
                        return i;
                    }
                }
            }
            try {
                return Integer.parseInt(strSubstring);
            } catch (NumberFormatException e2) {
                return i;
            }
        }
        return i;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0049  */
    public static long parseAsLong(String str, long j) {
        String strTrim;
        int length;
        int length2;
        String strSubstring;
        int i = 0;
        if (str != null && (length = (strTrim = str.trim()).length()) != 0) {
            if (0 >= length) {
                length2 = length;
                strSubstring = strTrim;
            } else {
                char cCharAt = strTrim.charAt(0);
                if (cCharAt == '+') {
                    strSubstring = strTrim.substring(1);
                    length2 = strSubstring.length();
                } else if (cCharAt == '-') {
                    i = 1;
                    length2 = length;
                    strSubstring = strTrim;
                } else {
                    length2 = length;
                    strSubstring = strTrim;
                }
            }
            while (i < length2) {
                char cCharAt2 = strSubstring.charAt(i);
                if (cCharAt2 <= '9' && cCharAt2 >= '0') {
                    i++;
                } else {
                    try {
                        return (long) parseDouble(strSubstring);
                    } catch (NumberFormatException e) {
                        return j;
                    }
                }
            }
            try {
                return Long.parseLong(strSubstring);
            } catch (NumberFormatException e2) {
                return j;
            }
        }
        return j;
    }

    public static double parseAsDouble(String str, double d) {
        if (str != null) {
            String strTrim = str.trim();
            if (strTrim.length() != 0) {
                try {
                    return parseDouble(strTrim);
                } catch (NumberFormatException e) {
                    return d;
                }
            }
            return d;
        }
        return d;
    }

    public static final double parseDouble(String str) {
        if (NASTY_SMALL_DOUBLE.equals(str)) {
            return Double.MIN_NORMAL;
        }
        return Double.parseDouble(str);
    }
}

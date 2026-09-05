package org.codehaus.jackson.util;

import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import java.util.Arrays;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class CharTypes {
    private static final byte[] HEX_BYTES;
    private static final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();
    static final int[] sHexValues;
    static final int[] sInputCodes;
    static final int[] sInputCodesComment;
    static final int[] sInputCodesJsNames;
    static final int[] sInputCodesUtf8;
    static final int[] sInputCodesUtf8JsNames;
    static final int[] sOutputEscapes128;

    static {
        int i;
        int length = HEX_CHARS.length;
        HEX_BYTES = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            HEX_BYTES[i2] = (byte) HEX_CHARS[i2];
        }
        int[] iArr = new int[256];
        for (int i3 = 0; i3 < 32; i3++) {
            iArr[i3] = -1;
        }
        iArr[34] = 1;
        iArr[92] = 1;
        sInputCodes = iArr;
        int[] iArr2 = new int[sInputCodes.length];
        System.arraycopy(sInputCodes, 0, iArr2, 0, sInputCodes.length);
        for (int i4 = 128; i4 < 256; i4++) {
            if ((i4 & 224) == 192) {
                i = 2;
            } else if ((i4 & 240) == 224) {
                i = 3;
            } else {
                i = (i4 & 248) == 240 ? 4 : -1;
            }
            iArr2[i4] = i;
        }
        sInputCodesUtf8 = iArr2;
        int[] iArr3 = new int[256];
        Arrays.fill(iArr3, -1);
        for (int i5 = 33; i5 < 256; i5++) {
            if (Character.isJavaIdentifierPart((char) i5)) {
                iArr3[i5] = 0;
            }
        }
        iArr3[64] = 0;
        iArr3[35] = 0;
        iArr3[42] = 0;
        iArr3[45] = 0;
        iArr3[43] = 0;
        sInputCodesJsNames = iArr3;
        int[] iArr4 = new int[256];
        System.arraycopy(sInputCodesJsNames, 0, iArr4, 0, sInputCodesJsNames.length);
        Arrays.fill(iArr4, XMLChar.MASK_NCNAME, XMLChar.MASK_NCNAME, 0);
        sInputCodesUtf8JsNames = iArr4;
        sInputCodesComment = new int[256];
        System.arraycopy(sInputCodesUtf8, XMLChar.MASK_NCNAME, sInputCodesComment, XMLChar.MASK_NCNAME, XMLChar.MASK_NCNAME);
        Arrays.fill(sInputCodesComment, 0, 32, -1);
        sInputCodesComment[9] = 0;
        sInputCodesComment[10] = 10;
        sInputCodesComment[13] = 13;
        sInputCodesComment[42] = 42;
        int[] iArr5 = new int[XMLChar.MASK_NCNAME];
        for (int i6 = 0; i6 < 32; i6++) {
            iArr5[i6] = -1;
        }
        iArr5[34] = 34;
        iArr5[92] = 92;
        iArr5[8] = 98;
        iArr5[9] = 116;
        iArr5[12] = 102;
        iArr5[10] = 110;
        iArr5[13] = 114;
        sOutputEscapes128 = iArr5;
        sHexValues = new int[XMLChar.MASK_NCNAME];
        Arrays.fill(sHexValues, -1);
        for (int i7 = 0; i7 < 10; i7++) {
            sHexValues[i7 + 48] = i7;
        }
        for (int i8 = 0; i8 < 6; i8++) {
            sHexValues[i8 + 97] = i8 + 10;
            sHexValues[i8 + 65] = i8 + 10;
        }
    }

    public static final int[] getInputCodeLatin1() {
        return sInputCodes;
    }

    public static final int[] getInputCodeUtf8() {
        return sInputCodesUtf8;
    }

    public static final int[] getInputCodeLatin1JsNames() {
        return sInputCodesJsNames;
    }

    public static final int[] getInputCodeUtf8JsNames() {
        return sInputCodesUtf8JsNames;
    }

    public static final int[] getInputCodeComment() {
        return sInputCodesComment;
    }

    public static final int[] get7BitOutputEscapes() {
        return sOutputEscapes128;
    }

    public static int charToHex(int i) {
        if (i > 127) {
            return -1;
        }
        return sHexValues[i];
    }

    public static void appendQuoted(StringBuilder sb, String str) {
        int[] iArr = sOutputEscapes128;
        int length = iArr.length;
        int length2 = str.length();
        for (int i = 0; i < length2; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt >= length || iArr[cCharAt] == 0) {
                sb.append(cCharAt);
            } else {
                sb.append('\\');
                int i2 = iArr[cCharAt];
                if (i2 < 0) {
                    sb.append('u');
                    sb.append('0');
                    sb.append('0');
                    int i3 = -(i2 + 1);
                    sb.append(HEX_CHARS[i3 >> 4]);
                    sb.append(HEX_CHARS[i3 & 15]);
                } else {
                    sb.append((char) i2);
                }
            }
        }
    }

    public static char[] copyHexChars() {
        return (char[]) HEX_CHARS.clone();
    }

    public static byte[] copyHexBytes() {
        return (byte[]) HEX_BYTES.clone();
    }
}

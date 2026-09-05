package org.codehaus.jackson.io;

import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import com.facebook.internal.NativeProtocol;
import java.lang.ref.SoftReference;
import org.codehaus.jackson.util.BufferRecycler;
import org.codehaus.jackson.util.ByteArrayBuilder;
import org.codehaus.jackson.util.CharTypes;
import org.codehaus.jackson.util.TextBuffer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class JsonStringEncoder {
    private static final int INT_0 = 48;
    private static final int INT_BACKSLASH = 92;
    private static final int INT_U = 117;
    private static final int SURR1_FIRST = 55296;
    private static final int SURR1_LAST = 56319;
    private static final int SURR2_FIRST = 56320;
    private static final int SURR2_LAST = 57343;
    protected ByteArrayBuilder _byteBuilder;
    protected final char[] _quoteBuffer = new char[6];
    protected TextBuffer _textBuffer;
    private static final char[] HEX_CHARS = CharTypes.copyHexChars();
    private static final byte[] HEX_BYTES = CharTypes.copyHexBytes();
    protected static final ThreadLocal<SoftReference<JsonStringEncoder>> _threadEncoder = new ThreadLocal<>();

    public JsonStringEncoder() {
        this._quoteBuffer[0] = '\\';
        this._quoteBuffer[2] = '0';
        this._quoteBuffer[3] = '0';
    }

    public static JsonStringEncoder getInstance() {
        SoftReference<JsonStringEncoder> softReference = _threadEncoder.get();
        JsonStringEncoder jsonStringEncoder = softReference == null ? null : softReference.get();
        if (jsonStringEncoder == null) {
            JsonStringEncoder jsonStringEncoder2 = new JsonStringEncoder();
            _threadEncoder.set(new SoftReference<>(jsonStringEncoder2));
            return jsonStringEncoder2;
        }
        return jsonStringEncoder;
    }

    public char[] quoteAsString(String str) {
        int i;
        TextBuffer textBuffer = this._textBuffer;
        if (textBuffer == null) {
            textBuffer = new TextBuffer(null);
            this._textBuffer = textBuffer;
        }
        char[] cArrEmptyAndGetCurrentSegment = textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = CharTypes.get7BitOutputEscapes();
        int length = iArr.length;
        int length2 = str.length();
        int i2 = 0;
        int i3 = 0;
        loop0: while (i3 < length2) {
            while (true) {
                char cCharAt = str.charAt(i3);
                if (cCharAt >= length || iArr[cCharAt] == 0) {
                    if (i2 >= cArrEmptyAndGetCurrentSegment.length) {
                        cArrEmptyAndGetCurrentSegment = textBuffer.finishCurrentSegment();
                        i = 0;
                    } else {
                        i = i2;
                    }
                    i2 = i + 1;
                    cArrEmptyAndGetCurrentSegment[i] = cCharAt;
                    i3++;
                    if (i3 >= length2) {
                        break loop0;
                    }
                }
            }
            int i4 = i3 + 1;
            int i_appendSingleEscape = _appendSingleEscape(iArr[str.charAt(i3)], this._quoteBuffer);
            if (i2 + i_appendSingleEscape > cArrEmptyAndGetCurrentSegment.length) {
                int length3 = cArrEmptyAndGetCurrentSegment.length - i2;
                if (length3 > 0) {
                    System.arraycopy(this._quoteBuffer, 0, cArrEmptyAndGetCurrentSegment, i2, length3);
                }
                cArrEmptyAndGetCurrentSegment = textBuffer.finishCurrentSegment();
                i_appendSingleEscape -= length3;
                System.arraycopy(this._quoteBuffer, length3, cArrEmptyAndGetCurrentSegment, i2, i_appendSingleEscape);
            } else {
                System.arraycopy(this._quoteBuffer, 0, cArrEmptyAndGetCurrentSegment, i2, i_appendSingleEscape);
            }
            i2 += i_appendSingleEscape;
            i3 = i4;
        }
        textBuffer.setCurrentLength(i2);
        return textBuffer.contentsAsArray();
    }

    public byte[] quoteAsUTF8(String str) {
        int i;
        int i2;
        byte[] bArrFinishCurrentSegment;
        int i3;
        int i4;
        int i5;
        int i6;
        ByteArrayBuilder byteArrayBuilder = this._byteBuilder;
        if (byteArrayBuilder == null) {
            byteArrayBuilder = new ByteArrayBuilder((BufferRecycler) null);
            this._byteBuilder = byteArrayBuilder;
        }
        int length = str.length();
        byte[] bArrResetAndGetFirstSegment = byteArrayBuilder.resetAndGetFirstSegment();
        int i_appendByteEscape = 0;
        int i7 = 0;
        loop0: while (i7 < length) {
            int[] iArr = CharTypes.get7BitOutputEscapes();
            while (true) {
                char cCharAt = str.charAt(i7);
                if (cCharAt > 127 || iArr[cCharAt] != 0) {
                    break;
                }
                if (i_appendByteEscape >= bArrResetAndGetFirstSegment.length) {
                    bArrResetAndGetFirstSegment = byteArrayBuilder.finishCurrentSegment();
                    i6 = 0;
                } else {
                    i6 = i_appendByteEscape;
                }
                i_appendByteEscape = i6 + 1;
                bArrResetAndGetFirstSegment[i6] = (byte) cCharAt;
                i7++;
                if (i7 >= length) {
                    break loop0;
                }
            }
            if (i_appendByteEscape >= bArrResetAndGetFirstSegment.length) {
                bArrResetAndGetFirstSegment = byteArrayBuilder.finishCurrentSegment();
                i_appendByteEscape = 0;
            }
            int i8 = i7 + 1;
            char cCharAt2 = str.charAt(i7);
            if (cCharAt2 <= 127) {
                i_appendByteEscape = _appendByteEscape(cCharAt2, iArr[cCharAt2], byteArrayBuilder, i_appendByteEscape);
                bArrResetAndGetFirstSegment = byteArrayBuilder.getCurrentSegment();
                i7 = i8;
            } else {
                if (cCharAt2 <= 2047) {
                    i2 = i_appendByteEscape + 1;
                    bArrResetAndGetFirstSegment[i_appendByteEscape] = (byte) ((cCharAt2 >> 6) | 192);
                    bArrFinishCurrentSegment = bArrResetAndGetFirstSegment;
                    i3 = (cCharAt2 & '?') | XMLChar.MASK_NCNAME;
                } else if (cCharAt2 < SURR1_FIRST || cCharAt2 > SURR2_LAST) {
                    int i9 = i_appendByteEscape + 1;
                    bArrResetAndGetFirstSegment[i_appendByteEscape] = (byte) ((cCharAt2 >> '\f') | 224);
                    if (i9 >= bArrResetAndGetFirstSegment.length) {
                        bArrResetAndGetFirstSegment = byteArrayBuilder.finishCurrentSegment();
                        i = 0;
                    } else {
                        i = i9;
                    }
                    i2 = i + 1;
                    bArrResetAndGetFirstSegment[i] = (byte) (((cCharAt2 >> 6) & 63) | XMLChar.MASK_NCNAME);
                    bArrFinishCurrentSegment = bArrResetAndGetFirstSegment;
                    i3 = (cCharAt2 & '?') | XMLChar.MASK_NCNAME;
                } else {
                    if (cCharAt2 > SURR1_LAST) {
                        _throwIllegalSurrogate(cCharAt2);
                    }
                    if (i8 >= length) {
                        _throwIllegalSurrogate(cCharAt2);
                    }
                    int i10 = i8 + 1;
                    int i_convertSurrogate = _convertSurrogate(cCharAt2, str.charAt(i8));
                    if (i_convertSurrogate > 1114111) {
                        _throwIllegalSurrogate(i_convertSurrogate);
                    }
                    int i11 = i_appendByteEscape + 1;
                    bArrResetAndGetFirstSegment[i_appendByteEscape] = (byte) ((i_convertSurrogate >> 18) | 240);
                    if (i11 >= bArrResetAndGetFirstSegment.length) {
                        bArrResetAndGetFirstSegment = byteArrayBuilder.finishCurrentSegment();
                        i4 = 0;
                    } else {
                        i4 = i11;
                    }
                    int i12 = i4 + 1;
                    bArrResetAndGetFirstSegment[i4] = (byte) (((i_convertSurrogate >> 12) & 63) | XMLChar.MASK_NCNAME);
                    if (i12 >= bArrResetAndGetFirstSegment.length) {
                        bArrResetAndGetFirstSegment = byteArrayBuilder.finishCurrentSegment();
                        i5 = 0;
                    } else {
                        i5 = i12;
                    }
                    i2 = i5 + 1;
                    bArrResetAndGetFirstSegment[i5] = (byte) (((i_convertSurrogate >> 6) & 63) | XMLChar.MASK_NCNAME);
                    int i13 = (i_convertSurrogate & 63) | XMLChar.MASK_NCNAME;
                    i8 = i10;
                    byte[] bArr = bArrResetAndGetFirstSegment;
                    i3 = i13;
                    bArrFinishCurrentSegment = bArr;
                }
                if (i2 >= bArrFinishCurrentSegment.length) {
                    bArrFinishCurrentSegment = byteArrayBuilder.finishCurrentSegment();
                    i2 = 0;
                }
                int i14 = i2 + 1;
                bArrFinishCurrentSegment[i2] = (byte) i3;
                bArrResetAndGetFirstSegment = bArrFinishCurrentSegment;
                i7 = i8;
                i_appendByteEscape = i14;
            }
        }
        return this._byteBuilder.completeAndCoalesce(i_appendByteEscape);
    }

    public byte[] encodeAsUTF8(String str) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        ByteArrayBuilder byteArrayBuilder = this._byteBuilder;
        if (byteArrayBuilder == null) {
            byteArrayBuilder = new ByteArrayBuilder((BufferRecycler) null);
            this._byteBuilder = byteArrayBuilder;
        }
        int length = str.length();
        byte[] bArrResetAndGetFirstSegment = byteArrayBuilder.resetAndGetFirstSegment();
        int length2 = bArrResetAndGetFirstSegment.length;
        int i6 = 0;
        int i7 = 0;
        while (i7 < length) {
            int i8 = i7 + 1;
            char cCharAt = str.charAt(i7);
            int i9 = length2;
            byte[] bArrFinishCurrentSegment = bArrResetAndGetFirstSegment;
            int i10 = i6;
            int length3 = i9;
            while (cCharAt <= 127) {
                if (i10 >= length3) {
                    bArrFinishCurrentSegment = byteArrayBuilder.finishCurrentSegment();
                    length3 = bArrFinishCurrentSegment.length;
                    i10 = 0;
                }
                int i11 = i10 + 1;
                bArrFinishCurrentSegment[i10] = (byte) cCharAt;
                if (i8 < length) {
                    cCharAt = str.charAt(i8);
                    i8++;
                    i10 = i11;
                } else {
                    i = i11;
                    return this._byteBuilder.completeAndCoalesce(i);
                }
            }
            if (i10 >= length3) {
                bArrFinishCurrentSegment = byteArrayBuilder.finishCurrentSegment();
                length3 = bArrFinishCurrentSegment.length;
                i2 = 0;
            } else {
                i2 = i10;
            }
            if (cCharAt < 2048) {
                i3 = i2 + 1;
                bArrFinishCurrentSegment[i2] = (byte) ((cCharAt >> 6) | 192);
                i4 = cCharAt;
                i7 = i8;
            } else if (cCharAt < SURR1_FIRST || cCharAt > SURR2_LAST) {
                int i12 = i2 + 1;
                bArrFinishCurrentSegment[i2] = (byte) ((cCharAt >> '\f') | 224);
                if (i12 >= length3) {
                    bArrFinishCurrentSegment = byteArrayBuilder.finishCurrentSegment();
                    length3 = bArrFinishCurrentSegment.length;
                    i12 = 0;
                }
                bArrFinishCurrentSegment[i12] = (byte) (((cCharAt >> 6) & 63) | XMLChar.MASK_NCNAME);
                i3 = i12 + 1;
                i4 = cCharAt;
                i7 = i8;
            } else {
                if (cCharAt > SURR1_LAST) {
                    _throwIllegalSurrogate(cCharAt);
                }
                if (i8 >= length) {
                    _throwIllegalSurrogate(cCharAt);
                }
                int i13 = i8 + 1;
                int i_convertSurrogate = _convertSurrogate(cCharAt, str.charAt(i8));
                if (i_convertSurrogate > 1114111) {
                    _throwIllegalSurrogate(i_convertSurrogate);
                }
                int i14 = i2 + 1;
                bArrFinishCurrentSegment[i2] = (byte) ((i_convertSurrogate >> 18) | 240);
                if (i14 >= length3) {
                    bArrFinishCurrentSegment = byteArrayBuilder.finishCurrentSegment();
                    length3 = bArrFinishCurrentSegment.length;
                    i14 = 0;
                }
                int i15 = i14 + 1;
                bArrFinishCurrentSegment[i14] = (byte) (((i_convertSurrogate >> 12) & 63) | XMLChar.MASK_NCNAME);
                if (i15 >= length3) {
                    bArrFinishCurrentSegment = byteArrayBuilder.finishCurrentSegment();
                    length3 = bArrFinishCurrentSegment.length;
                    i5 = 0;
                } else {
                    i5 = i15;
                }
                bArrFinishCurrentSegment[i5] = (byte) (((i_convertSurrogate >> 6) & 63) | XMLChar.MASK_NCNAME);
                i3 = i5 + 1;
                i4 = i_convertSurrogate;
                i7 = i13;
            }
            if (i3 >= length3) {
                bArrFinishCurrentSegment = byteArrayBuilder.finishCurrentSegment();
                length3 = bArrFinishCurrentSegment.length;
                i3 = 0;
            }
            int i16 = i3 + 1;
            bArrFinishCurrentSegment[i3] = (byte) ((i4 & 63) | XMLChar.MASK_NCNAME);
            bArrResetAndGetFirstSegment = bArrFinishCurrentSegment;
            length2 = length3;
            i6 = i16;
        }
        i = i6;
        return this._byteBuilder.completeAndCoalesce(i);
    }

    private int _appendSingleEscape(int i, char[] cArr) {
        if (i < 0) {
            int i2 = -(i + 1);
            cArr[1] = 'u';
            cArr[4] = HEX_CHARS[i2 >> 4];
            cArr[5] = HEX_CHARS[i2 & 15];
            return 6;
        }
        cArr[1] = (char) i;
        return 2;
    }

    private int _appendByteEscape(int i, int i2, ByteArrayBuilder byteArrayBuilder, int i3) {
        byteArrayBuilder.setCurrentSegmentLength(i3);
        byteArrayBuilder.append(INT_BACKSLASH);
        if (i2 < 0) {
            byteArrayBuilder.append(INT_U);
            if (i > 255) {
                int i4 = i >> 8;
                byteArrayBuilder.append(HEX_BYTES[i4 >> 4]);
                byteArrayBuilder.append(HEX_BYTES[i4 & 15]);
                i &= 255;
            } else {
                byteArrayBuilder.append(INT_0);
                byteArrayBuilder.append(INT_0);
            }
            byteArrayBuilder.append(HEX_BYTES[i >> 4]);
            byteArrayBuilder.append(HEX_BYTES[i & 15]);
        } else {
            byteArrayBuilder.append((byte) i2);
        }
        return byteArrayBuilder.getCurrentSegmentLength();
    }

    private int _convertSurrogate(int i, int i2) {
        if (i2 < SURR2_FIRST || i2 > SURR2_LAST) {
            throw new IllegalArgumentException("Broken surrogate pair: first char 0x" + Integer.toHexString(i) + ", second 0x" + Integer.toHexString(i2) + "; illegal combination");
        }
        return NativeProtocol.MESSAGE_GET_ACCESS_TOKEN_REQUEST + ((i - SURR1_FIRST) << 10) + (i2 - SURR2_FIRST);
    }

    private void _throwIllegalSurrogate(int i) {
        if (i > 1114111) {
            throw new IllegalArgumentException("Illegal character point (0x" + Integer.toHexString(i) + ") to output; max is 0x10FFFF as per RFC 4627");
        }
        if (i >= SURR1_FIRST) {
            if (i <= SURR1_LAST) {
                throw new IllegalArgumentException("Unmatched first part of surrogate pair (0x" + Integer.toHexString(i) + ")");
            }
            throw new IllegalArgumentException("Unmatched second part of surrogate pair (0x" + Integer.toHexString(i) + ")");
        }
        throw new IllegalArgumentException("Illegal character point (0x" + Integer.toHexString(i) + ") to output");
    }
}

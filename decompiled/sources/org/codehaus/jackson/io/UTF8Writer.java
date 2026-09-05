package org.codehaus.jackson.io;

import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import com.facebook.internal.NativeProtocol;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class UTF8Writer extends Writer {
    static final int SURR1_FIRST = 55296;
    static final int SURR1_LAST = 56319;
    static final int SURR2_FIRST = 56320;
    static final int SURR2_LAST = 57343;
    protected final IOContext _context;
    OutputStream _out;
    byte[] _outBuffer;
    final int _outBufferEnd;
    int _surrogate = 0;
    int _outPtr = 0;

    public UTF8Writer(IOContext iOContext, OutputStream outputStream) {
        this._context = iOContext;
        this._out = outputStream;
        this._outBuffer = iOContext.allocWriteEncodingBuffer();
        this._outBufferEnd = this._outBuffer.length - 4;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(char c) throws IOException {
        write(c);
        return this;
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this._out != null) {
            if (this._outPtr > 0) {
                this._out.write(this._outBuffer, 0, this._outPtr);
                this._outPtr = 0;
            }
            OutputStream outputStream = this._out;
            this._out = null;
            byte[] bArr = this._outBuffer;
            if (bArr != null) {
                this._outBuffer = null;
                this._context.releaseWriteEncodingBuffer(bArr);
            }
            outputStream.close();
            int i = this._surrogate;
            this._surrogate = 0;
            if (i > 0) {
                throwIllegal(i);
            }
        }
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        if (this._out != null) {
            if (this._outPtr > 0) {
                this._out.write(this._outBuffer, 0, this._outPtr);
                this._outPtr = 0;
            }
            this._out.flush();
        }
    }

    @Override // java.io.Writer
    public void write(char[] cArr) throws IOException {
        write(cArr, 0, cArr.length);
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) throws IOException {
        char c;
        if (i2 < 2) {
            if (i2 == 1) {
                write(cArr[i]);
                return;
            }
            return;
        }
        if (this._surrogate > 0) {
            i2--;
            write(convertSurrogate(cArr[i]));
            i++;
        }
        int i3 = this._outPtr;
        byte[] bArr = this._outBuffer;
        int i4 = this._outBufferEnd;
        int i5 = i2 + i;
        int i6 = i;
        while (i6 < i5) {
            if (i3 >= i4) {
                this._out.write(bArr, 0, i3);
                i3 = 0;
            }
            int i7 = i6 + 1;
            char c2 = cArr[i6];
            if (c2 < 128) {
                int i8 = i3 + 1;
                bArr[i3] = (byte) c2;
                int i9 = i5 - i7;
                int i10 = i4 - i8;
                if (i9 <= i10) {
                    i10 = i9;
                }
                int i11 = i10 + i7;
                int i12 = i8;
                int i13 = i7;
                while (true) {
                    if (i13 >= i11) {
                        int i14 = i12;
                        i6 = i13;
                        i3 = i14;
                    } else {
                        int i15 = i13 + 1;
                        char c3 = cArr[i13];
                        if (c3 >= 128) {
                            i3 = i12;
                            i6 = i15;
                            c = c3;
                        } else {
                            bArr[i12] = (byte) c3;
                            i12++;
                            i13 = i15;
                        }
                    }
                }
            } else {
                i6 = i7;
                c = c2;
            }
            if (c < 2048) {
                int i16 = i3 + 1;
                bArr[i3] = (byte) ((c >> 6) | 192);
                i3 = i16 + 1;
                bArr[i16] = (byte) ((c & '?') | XMLChar.MASK_NCNAME);
            } else if (c < SURR1_FIRST || c > SURR2_LAST) {
                int i17 = i3 + 1;
                bArr[i3] = (byte) ((c >> '\f') | 224);
                int i18 = i17 + 1;
                bArr[i17] = (byte) (((c >> 6) & 63) | XMLChar.MASK_NCNAME);
                i3 = i18 + 1;
                bArr[i18] = (byte) ((c & '?') | XMLChar.MASK_NCNAME);
            } else {
                if (c > SURR1_LAST) {
                    this._outPtr = i3;
                    throwIllegal(c);
                }
                this._surrogate = c;
                if (i6 >= i5) {
                    break;
                }
                int i19 = i6 + 1;
                int iConvertSurrogate = convertSurrogate(cArr[i6]);
                if (iConvertSurrogate > 1114111) {
                    this._outPtr = i3;
                    throwIllegal(iConvertSurrogate);
                }
                int i20 = i3 + 1;
                bArr[i3] = (byte) ((iConvertSurrogate >> 18) | 240);
                int i21 = i20 + 1;
                bArr[i20] = (byte) (((iConvertSurrogate >> 12) & 63) | XMLChar.MASK_NCNAME);
                int i22 = i21 + 1;
                bArr[i21] = (byte) (((iConvertSurrogate >> 6) & 63) | XMLChar.MASK_NCNAME);
                i3 = i22 + 1;
                bArr[i22] = (byte) ((iConvertSurrogate & 63) | XMLChar.MASK_NCNAME);
                i6 = i19;
            }
        }
        this._outPtr = i3;
    }

    @Override // java.io.Writer
    public void write(int i) throws IOException {
        int i2;
        if (this._surrogate > 0) {
            i = convertSurrogate(i);
        } else if (i >= SURR1_FIRST && i <= SURR2_LAST) {
            if (i > SURR1_LAST) {
                throwIllegal(i);
            }
            this._surrogate = i;
            return;
        }
        if (this._outPtr >= this._outBufferEnd) {
            this._out.write(this._outBuffer, 0, this._outPtr);
            this._outPtr = 0;
        }
        if (i < 128) {
            byte[] bArr = this._outBuffer;
            int i3 = this._outPtr;
            this._outPtr = i3 + 1;
            bArr[i3] = (byte) i;
            return;
        }
        int i4 = this._outPtr;
        if (i < 2048) {
            int i5 = i4 + 1;
            this._outBuffer[i4] = (byte) ((i >> 6) | 192);
            i2 = i5 + 1;
            this._outBuffer[i5] = (byte) ((i & 63) | XMLChar.MASK_NCNAME);
        } else if (i <= 65535) {
            int i6 = i4 + 1;
            this._outBuffer[i4] = (byte) ((i >> 12) | 224);
            int i7 = i6 + 1;
            this._outBuffer[i6] = (byte) (((i >> 6) & 63) | XMLChar.MASK_NCNAME);
            i2 = i7 + 1;
            this._outBuffer[i7] = (byte) ((i & 63) | XMLChar.MASK_NCNAME);
        } else {
            if (i > 1114111) {
                throwIllegal(i);
            }
            int i8 = i4 + 1;
            this._outBuffer[i4] = (byte) ((i >> 18) | 240);
            int i9 = i8 + 1;
            this._outBuffer[i8] = (byte) (((i >> 12) & 63) | XMLChar.MASK_NCNAME);
            int i10 = i9 + 1;
            this._outBuffer[i9] = (byte) (((i >> 6) & 63) | XMLChar.MASK_NCNAME);
            i2 = i10 + 1;
            this._outBuffer[i10] = (byte) ((i & 63) | XMLChar.MASK_NCNAME);
        }
        this._outPtr = i2;
    }

    @Override // java.io.Writer
    public void write(String str) throws IOException {
        write(str, 0, str.length());
    }

    @Override // java.io.Writer
    public void write(String str, int i, int i2) throws IOException {
        char c;
        if (i2 < 2) {
            if (i2 == 1) {
                write(str.charAt(i));
                return;
            }
            return;
        }
        if (this._surrogate > 0) {
            i2--;
            write(convertSurrogate(str.charAt(i)));
            i++;
        }
        int i3 = this._outPtr;
        byte[] bArr = this._outBuffer;
        int i4 = this._outBufferEnd;
        int i5 = i2 + i;
        int i6 = i;
        while (i6 < i5) {
            if (i3 >= i4) {
                this._out.write(bArr, 0, i3);
                i3 = 0;
            }
            int i7 = i6 + 1;
            char cCharAt = str.charAt(i6);
            if (cCharAt < 128) {
                int i8 = i3 + 1;
                bArr[i3] = (byte) cCharAt;
                int i9 = i5 - i7;
                int i10 = i4 - i8;
                if (i9 <= i10) {
                    i10 = i9;
                }
                int i11 = i10 + i7;
                int i12 = i8;
                int i13 = i7;
                while (true) {
                    if (i13 >= i11) {
                        int i14 = i12;
                        i6 = i13;
                        i3 = i14;
                    } else {
                        int i15 = i13 + 1;
                        char cCharAt2 = str.charAt(i13);
                        if (cCharAt2 >= 128) {
                            i3 = i12;
                            i6 = i15;
                            c = cCharAt2;
                        } else {
                            bArr[i12] = (byte) cCharAt2;
                            i12++;
                            i13 = i15;
                        }
                    }
                }
            } else {
                i6 = i7;
                c = cCharAt;
            }
            if (c < 2048) {
                int i16 = i3 + 1;
                bArr[i3] = (byte) ((c >> 6) | 192);
                i3 = i16 + 1;
                bArr[i16] = (byte) ((c & '?') | XMLChar.MASK_NCNAME);
            } else if (c < SURR1_FIRST || c > SURR2_LAST) {
                int i17 = i3 + 1;
                bArr[i3] = (byte) ((c >> '\f') | 224);
                int i18 = i17 + 1;
                bArr[i17] = (byte) (((c >> 6) & 63) | XMLChar.MASK_NCNAME);
                i3 = i18 + 1;
                bArr[i18] = (byte) ((c & '?') | XMLChar.MASK_NCNAME);
            } else {
                if (c > SURR1_LAST) {
                    this._outPtr = i3;
                    throwIllegal(c);
                }
                this._surrogate = c;
                if (i6 >= i5) {
                    break;
                }
                int i19 = i6 + 1;
                int iConvertSurrogate = convertSurrogate(str.charAt(i6));
                if (iConvertSurrogate > 1114111) {
                    this._outPtr = i3;
                    throwIllegal(iConvertSurrogate);
                }
                int i20 = i3 + 1;
                bArr[i3] = (byte) ((iConvertSurrogate >> 18) | 240);
                int i21 = i20 + 1;
                bArr[i20] = (byte) (((iConvertSurrogate >> 12) & 63) | XMLChar.MASK_NCNAME);
                int i22 = i21 + 1;
                bArr[i21] = (byte) (((iConvertSurrogate >> 6) & 63) | XMLChar.MASK_NCNAME);
                i3 = i22 + 1;
                bArr[i22] = (byte) ((iConvertSurrogate & 63) | XMLChar.MASK_NCNAME);
                i6 = i19;
            }
        }
        this._outPtr = i3;
    }

    private int convertSurrogate(int i) throws IOException {
        int i2 = this._surrogate;
        this._surrogate = 0;
        if (i < SURR2_FIRST || i > SURR2_LAST) {
            throw new IOException("Broken surrogate pair: first char 0x" + Integer.toHexString(i2) + ", second 0x" + Integer.toHexString(i) + "; illegal combination");
        }
        return ((i2 - SURR1_FIRST) << 10) + NativeProtocol.MESSAGE_GET_ACCESS_TOKEN_REQUEST + (i - SURR2_FIRST);
    }

    private void throwIllegal(int i) throws IOException {
        if (i > 1114111) {
            throw new IOException("Illegal character point (0x" + Integer.toHexString(i) + ") to output; max is 0x10FFFF as per RFC 4627");
        }
        if (i >= SURR1_FIRST) {
            if (i <= SURR1_LAST) {
                throw new IOException("Unmatched first part of surrogate pair (0x" + Integer.toHexString(i) + ")");
            }
            throw new IOException("Unmatched second part of surrogate pair (0x" + Integer.toHexString(i) + ")");
        }
        throw new IOException("Illegal character point (0x" + Integer.toHexString(i) + ") to output");
    }
}

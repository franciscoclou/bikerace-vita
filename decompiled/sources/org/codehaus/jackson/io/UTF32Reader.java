package org.codehaus.jackson.io;

import com.facebook.internal.NativeProtocol;
import com.flurry.android.Constants;
import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class UTF32Reader extends BaseReader {
    final boolean mBigEndian;
    int mByteCount;
    int mCharCount;
    char mSurrogate;

    @Override // org.codehaus.jackson.io.BaseReader, java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public /* bridge */ /* synthetic */ void close() throws IOException {
        super.close();
    }

    @Override // org.codehaus.jackson.io.BaseReader, java.io.Reader
    public /* bridge */ /* synthetic */ int read() {
        return super.read();
    }

    public UTF32Reader(IOContext iOContext, InputStream inputStream, byte[] bArr, int i, int i2, boolean z) {
        super(iOContext, inputStream, bArr, i, i2);
        this.mSurrogate = (char) 0;
        this.mCharCount = 0;
        this.mByteCount = 0;
        this.mBigEndian = z;
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws CharConversionException {
        int i3;
        int i4;
        int i5;
        if (this._buffer == null) {
            return -1;
        }
        if (i2 >= 1) {
            if (i < 0 || i + i2 > cArr.length) {
                reportBounds(cArr, i, i2);
            }
            int i6 = i2 + i;
            if (this.mSurrogate != 0) {
                i3 = i + 1;
                cArr[i] = this.mSurrogate;
                this.mSurrogate = (char) 0;
            } else {
                int i7 = this._length - this._ptr;
                if (i7 < 4 && !loadMore(i7)) {
                    return -1;
                }
                i3 = i;
            }
            while (i3 < i6) {
                int i8 = this._ptr;
                if (this.mBigEndian) {
                    i5 = (this._buffer[i8 + 3] & Constants.UNKNOWN) | (this._buffer[i8] << 24) | ((this._buffer[i8 + 1] & Constants.UNKNOWN) << 16) | ((this._buffer[i8 + 2] & Constants.UNKNOWN) << 8);
                } else {
                    i5 = (this._buffer[i8 + 3] << 24) | (this._buffer[i8] & Constants.UNKNOWN) | ((this._buffer[i8 + 1] & Constants.UNKNOWN) << 8) | ((this._buffer[i8 + 2] & Constants.UNKNOWN) << 16);
                }
                this._ptr += 4;
                if (i5 > 65535) {
                    if (i5 > 1114111) {
                        reportInvalid(i5, i3 - i, "(above " + Integer.toHexString(1114111) + ") ");
                    }
                    int i9 = i5 - NativeProtocol.MESSAGE_GET_ACCESS_TOKEN_REQUEST;
                    i4 = i3 + 1;
                    cArr[i3] = (char) (55296 + (i9 >> 10));
                    i5 = (i9 & 1023) | 56320;
                    if (i4 >= i6) {
                        this.mSurrogate = (char) i5;
                    }
                    int i10 = i4 - i;
                    this.mCharCount += i10;
                    return i10;
                }
                i4 = i3;
                i3 = i4 + 1;
                cArr[i4] = (char) i5;
                if (this._ptr >= this._length) {
                    i4 = i3;
                    int i11 = i4 - i;
                    this.mCharCount += i11;
                    return i11;
                }
            }
            i4 = i3;
            int i12 = i4 - i;
            this.mCharCount += i12;
            return i12;
        }
        return i2;
    }

    private void reportUnexpectedEOF(int i, int i2) throws CharConversionException {
        throw new CharConversionException("Unexpected EOF in the middle of a 4-byte UTF-32 char: got " + i + ", needed " + i2 + ", at char #" + this.mCharCount + ", byte #" + (this.mByteCount + i) + ")");
    }

    private void reportInvalid(int i, int i2, String str) throws CharConversionException {
        throw new CharConversionException("Invalid UTF-32 character 0x" + Integer.toHexString(i) + str + " at char #" + (this.mCharCount + i2) + ", byte #" + ((this.mByteCount + this._ptr) - 1) + ")");
    }

    private boolean loadMore(int i) throws IOException {
        this.mByteCount += this._length - i;
        if (i > 0) {
            if (this._ptr > 0) {
                for (int i2 = 0; i2 < i; i2++) {
                    this._buffer[i2] = this._buffer[this._ptr + i2];
                }
                this._ptr = 0;
            }
            this._length = i;
        } else {
            this._ptr = 0;
            int i3 = this._in.read(this._buffer);
            if (i3 < 1) {
                this._length = 0;
                if (i3 < 0) {
                    freeBuffers();
                    return false;
                }
                reportStrangeStream();
            }
            this._length = i3;
        }
        while (this._length < 4) {
            int i4 = this._in.read(this._buffer, this._length, this._buffer.length - this._length);
            if (i4 < 1) {
                if (i4 < 0) {
                    freeBuffers();
                    reportUnexpectedEOF(this._length, 4);
                }
                reportStrangeStream();
            }
            this._length = i4 + this._length;
        }
        return true;
    }
}

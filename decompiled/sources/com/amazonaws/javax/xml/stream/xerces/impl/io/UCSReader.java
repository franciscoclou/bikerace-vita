package com.amazonaws.javax.xml.stream.xerces.impl.io;

import com.amazonaws.javax.xml.stream.util.ThreadLocalBufferAllocator;
import com.flurry.android.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class UCSReader extends Reader {
    public static final int DEFAULT_BUFFER_SIZE = 8192;
    public static final short UCS2BE = 2;
    public static final short UCS2LE = 1;
    public static final short UCS4BE = 8;
    public static final short UCS4LE = 4;
    protected byte[] fBuffer;
    protected short fEncoding;
    protected InputStream fInputStream;

    public UCSReader(InputStream inputStream, short s) {
        this(inputStream, 8192, s);
    }

    public UCSReader(InputStream inputStream, int i, short s) {
        this.fInputStream = inputStream;
        this.fBuffer = ThreadLocalBufferAllocator.getBufferAllocator().getByteBuffer(i);
        if (this.fBuffer == null) {
            this.fBuffer = new byte[i];
        }
        this.fEncoding = s;
    }

    @Override // java.io.Reader
    public int read() {
        int i;
        int i2;
        int i3 = this.fInputStream.read() & 255;
        if (i3 == 255 || (i = this.fInputStream.read() & 255) == 255) {
            return -1;
        }
        if (this.fEncoding >= 4) {
            int i4 = this.fInputStream.read() & 255;
            if (i4 == 255 || (i2 = this.fInputStream.read() & 255) == 255) {
                return -1;
            }
            System.err.println(new StringBuffer().append("b0 is ").append(i3 & 255).append(" b1 ").append(i & 255).append(" b2 ").append(i4 & 255).append(" b3 ").append(i2 & 255).toString());
            if (this.fEncoding == 8) {
                return (i3 << 24) + (i << 16) + (i4 << 8) + i2;
            }
            return (i2 << 24) + (i4 << 16) + (i << 8) + i3;
        }
        if (this.fEncoding == 2) {
            return (i3 << 8) + i;
        }
        return (i << 8) + i3;
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        int i3 = 0;
        int length = i2 << (this.fEncoding >= 4 ? 2 : 1);
        if (length > this.fBuffer.length) {
            length = this.fBuffer.length;
        }
        int i4 = this.fInputStream.read(this.fBuffer, 0, length);
        if (i4 == -1) {
            return -1;
        }
        if (this.fEncoding >= 4) {
            int i5 = (4 - (i4 & 3)) & 3;
            for (int i6 = 0; i6 < i5; i6++) {
                int i7 = this.fInputStream.read();
                if (i7 == -1) {
                    for (int i8 = i6; i8 < i5; i8++) {
                        this.fBuffer[i4 + i8] = 0;
                    }
                    break;
                }
                this.fBuffer[i4 + i6] = (byte) i7;
            }
            i4 += i5;
        } else if ((i4 & 1) != 0) {
            i4++;
            int i9 = this.fInputStream.read();
            if (i9 == -1) {
                this.fBuffer[i4] = 0;
            } else {
                this.fBuffer[i4] = (byte) i9;
            }
        }
        int i10 = i4 >> (this.fEncoding >= 4 ? 2 : 1);
        int i11 = 0;
        while (i11 < i10) {
            int i12 = i3 + 1;
            int i13 = this.fBuffer[i3] & Constants.UNKNOWN;
            int i14 = i12 + 1;
            int i15 = this.fBuffer[i12] & Constants.UNKNOWN;
            if (this.fEncoding >= 4) {
                int i16 = i14 + 1;
                int i17 = this.fBuffer[i14] & Constants.UNKNOWN;
                i14 = i16 + 1;
                int i18 = this.fBuffer[i16] & Constants.UNKNOWN;
                if (this.fEncoding == 8) {
                    cArr[i + i11] = (char) ((i13 << 24) + (i15 << 16) + (i17 << 8) + i18);
                } else {
                    cArr[i + i11] = (char) (i13 + (i15 << 8) + (i17 << 16) + (i18 << 24));
                }
            } else if (this.fEncoding == 2) {
                cArr[i + i11] = (char) ((i13 << 8) + i15);
            } else {
                cArr[i + i11] = (char) (i13 + (i15 << 8));
            }
            i11++;
            i3 = i14;
        }
        return i10;
    }

    @Override // java.io.Reader
    public long skip(long j) throws IOException {
        int i = this.fEncoding >= 4 ? 2 : 1;
        long jSkip = this.fInputStream.skip(j << i);
        return (((long) (i | 1)) & jSkip) == 0 ? jSkip >> i : (jSkip >> i) + 1;
    }

    @Override // java.io.Reader
    public boolean ready() {
        return false;
    }

    @Override // java.io.Reader
    public boolean markSupported() {
        return this.fInputStream.markSupported();
    }

    @Override // java.io.Reader
    public void mark(int i) {
        this.fInputStream.mark(i);
    }

    @Override // java.io.Reader
    public void reset() throws IOException {
        this.fInputStream.reset();
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        ThreadLocalBufferAllocator.getBufferAllocator().returnByteBuffer(this.fBuffer);
        this.fBuffer = null;
        this.fInputStream.close();
    }
}

package com.amazonaws.javax.xml.stream.xerces.impl.io;

import com.amazonaws.javax.xml.stream.util.ThreadLocalBufferAllocator;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.amazonaws.javax.xml.stream.xerces.impl.msg.XMLMessageFormatter;
import com.amazonaws.javax.xml.stream.xerces.util.MessageFormatter;
import com.flurry.android.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UTFDataFormatException;
import java.util.Locale;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class UTF8Reader extends Reader {
    private static final boolean DEBUG_READ = false;
    public static final int DEFAULT_BUFFER_SIZE = 2048;
    protected byte[] fBuffer;
    private MessageFormatter fFormatter;
    protected InputStream fInputStream;
    private Locale fLocale;
    protected int fOffset;
    private int fSurrogate;

    public UTF8Reader(InputStream inputStream) {
        this(inputStream, 2048, new XMLMessageFormatter(), Locale.getDefault());
    }

    public UTF8Reader(InputStream inputStream, MessageFormatter messageFormatter, Locale locale) {
        this(inputStream, 2048, messageFormatter, locale);
    }

    public UTF8Reader(InputStream inputStream, int i, MessageFormatter messageFormatter, Locale locale) {
        this.fSurrogate = -1;
        this.fFormatter = null;
        this.fLocale = null;
        this.fInputStream = inputStream;
        this.fFormatter = messageFormatter;
        this.fLocale = locale;
        this.fBuffer = ThreadLocalBufferAllocator.getBufferAllocator().getByteBuffer(i);
        if (this.fBuffer == null) {
            this.fBuffer = new byte[i];
        }
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11 = this.fSurrogate;
        if (this.fSurrogate == -1) {
            if (0 == this.fOffset) {
                i = this.fInputStream.read();
                i2 = 0;
            } else {
                i = this.fBuffer[0] & Constants.UNKNOWN;
                i2 = 1;
            }
            if (i == -1) {
                return -1;
            }
            if (i < 128) {
                return (char) i;
            }
            if ((i & 224) == 192) {
                if (i2 == this.fOffset) {
                    i10 = this.fInputStream.read();
                } else {
                    int i12 = i2 + 1;
                    i10 = this.fBuffer[i2] & Constants.UNKNOWN;
                }
                if (i10 == -1) {
                    expectedByte(2, 2);
                }
                if ((i10 & 192) != 128) {
                    invalidByte(2, 2, i10);
                }
                return (i10 & 63) | ((i << 6) & 1984);
            }
            if ((i & 240) == 224) {
                if (i2 == this.fOffset) {
                    i8 = i2;
                    i7 = this.fInputStream.read();
                } else {
                    int i13 = i2 + 1;
                    i7 = this.fBuffer[i2] & Constants.UNKNOWN;
                    i8 = i13;
                }
                if (i7 == -1) {
                    expectedByte(2, 3);
                }
                if ((i7 & 192) != 128) {
                    invalidByte(2, 3, i7);
                }
                if (i8 == this.fOffset) {
                    i9 = this.fInputStream.read();
                } else {
                    int i14 = i8 + 1;
                    i9 = this.fBuffer[i8] & Constants.UNKNOWN;
                }
                if (i9 == -1) {
                    expectedByte(3, 3);
                }
                if ((i9 & 192) != 128) {
                    invalidByte(3, 3, i9);
                }
                return (i9 & 63) | ((i << 12) & 61440) | ((i7 << 6) & 4032);
            }
            if ((i & 248) == 240) {
                if (i2 == this.fOffset) {
                    i3 = this.fInputStream.read();
                } else {
                    i3 = this.fBuffer[i2] & Constants.UNKNOWN;
                    i2++;
                }
                if (i3 == -1) {
                    expectedByte(2, 4);
                }
                if ((i3 & 192) != 128) {
                    invalidByte(2, 3, i3);
                }
                if (i2 == this.fOffset) {
                    i5 = this.fInputStream.read();
                    i4 = i2;
                } else {
                    i4 = i2 + 1;
                    i5 = this.fBuffer[i2] & Constants.UNKNOWN;
                }
                if (i5 == -1) {
                    expectedByte(3, 4);
                }
                if ((i5 & 192) != 128) {
                    invalidByte(3, 3, i5);
                }
                if (i4 == this.fOffset) {
                    i6 = this.fInputStream.read();
                } else {
                    int i15 = i4 + 1;
                    i6 = this.fBuffer[i4] & Constants.UNKNOWN;
                }
                if (i6 == -1) {
                    expectedByte(4, 4);
                }
                if ((i6 & 192) != 128) {
                    invalidByte(4, 4, i6);
                }
                int i16 = ((i << 2) & 28) | ((i3 >> 4) & 3);
                if (i16 > 16) {
                    invalidSurrogate(i16);
                }
                int i17 = (((i16 - 1) << 6) & 960) | 55296 | ((i3 << 2) & 60) | ((i5 >> 4) & 3);
                this.fSurrogate = 56320 | ((i5 << 6) & 960) | (i6 & 63);
                return i17;
            }
            invalidByte(1, 1, i);
            return i11;
        }
        this.fSurrogate = -1;
        return i11;
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        if (this.fSurrogate != -1) {
            cArr[i + 1] = (char) this.fSurrogate;
            this.fSurrogate = -1;
            i2--;
            i3 = i + 1;
        } else {
            i3 = i;
        }
        if (this.fOffset == 0) {
            if (i2 > this.fBuffer.length) {
                i2 = this.fBuffer.length;
            }
            int i17 = this.fInputStream.read(this.fBuffer, 0, i2);
            if (i17 == -1) {
                return -1;
            }
            i4 = i17 + (i3 - i);
        } else {
            i4 = this.fOffset;
            this.fOffset = 0;
        }
        boolean z = true;
        int i18 = i3;
        int i19 = 0;
        while (i19 < i4) {
            int i20 = this.fBuffer[i19] & Constants.UNKNOWN;
            if (i20 < 128) {
                cArr[i18] = (char) i20;
                i19++;
                i18++;
            } else {
                z = DEBUG_READ;
                break;
            }
        }
        if (!z) {
            int i21 = i4;
            while (i19 < i4) {
                int i22 = this.fBuffer[i19] & Constants.UNKNOWN;
                if (i22 < 128) {
                    i5 = i18 + 1;
                    cArr[i18] = (char) i22;
                } else if ((i22 & 224) == 192) {
                    i19++;
                    if (i19 < i4) {
                        i15 = i21;
                        i16 = this.fBuffer[i19] & Constants.UNKNOWN;
                    } else {
                        int i23 = this.fInputStream.read();
                        if (i23 == -1) {
                            if (i18 > i) {
                                this.fBuffer[0] = (byte) i22;
                                this.fOffset = 1;
                                return i18 - i;
                            }
                            expectedByte(2, 2);
                        }
                        i15 = i21 + 1;
                        i16 = i23;
                    }
                    if ((i16 & 192) != 128) {
                        if (i18 > i) {
                            this.fBuffer[0] = (byte) i22;
                            this.fBuffer[1] = (byte) i16;
                            this.fOffset = 2;
                            return i18 - i;
                        }
                        invalidByte(2, 2, i16);
                    }
                    i5 = i18 + 1;
                    cArr[i18] = (char) ((i16 & 63) | ((i22 << 6) & 1984));
                    i21 = i15 - 1;
                } else if ((i22 & 240) == 224) {
                    int i24 = i19 + 1;
                    if (i24 < i4) {
                        i12 = this.fBuffer[i24] & Constants.UNKNOWN;
                        i11 = i21;
                    } else {
                        int i25 = this.fInputStream.read();
                        if (i25 == -1) {
                            if (i18 > i) {
                                this.fBuffer[0] = (byte) i22;
                                this.fOffset = 1;
                                return i18 - i;
                            }
                            expectedByte(2, 3);
                        }
                        i11 = i21 + 1;
                        i12 = i25;
                    }
                    if ((i12 & 192) != 128) {
                        if (i18 > i) {
                            this.fBuffer[0] = (byte) i22;
                            this.fBuffer[1] = (byte) i12;
                            this.fOffset = 2;
                            return i18 - i;
                        }
                        invalidByte(2, 3, i12);
                    }
                    i19 = i24 + 1;
                    if (i19 < i4) {
                        i13 = this.fBuffer[i19] & Constants.UNKNOWN;
                        i14 = i11;
                    } else {
                        i13 = this.fInputStream.read();
                        if (i13 == -1) {
                            if (i18 > i) {
                                this.fBuffer[0] = (byte) i22;
                                this.fBuffer[1] = (byte) i12;
                                this.fOffset = 2;
                                return i18 - i;
                            }
                            expectedByte(3, 3);
                        }
                        i14 = i11 + 1;
                    }
                    if ((i13 & 192) != 128) {
                        if (i18 > i) {
                            this.fBuffer[0] = (byte) i22;
                            this.fBuffer[1] = (byte) i12;
                            this.fBuffer[2] = (byte) i13;
                            this.fOffset = 3;
                            return i18 - i;
                        }
                        invalidByte(3, 3, i13);
                    }
                    i5 = i18 + 1;
                    cArr[i18] = (char) ((i13 & 63) | ((i22 << 12) & 61440) | ((i12 << 6) & 4032));
                    i21 = i14 - 2;
                } else if ((i22 & 248) == 240) {
                    int i26 = i19 + 1;
                    if (i26 < i4) {
                        i7 = this.fBuffer[i26] & Constants.UNKNOWN;
                        i6 = i21;
                    } else {
                        int i27 = this.fInputStream.read();
                        if (i27 == -1) {
                            if (i18 > i) {
                                this.fBuffer[0] = (byte) i22;
                                this.fOffset = 1;
                                return i18 - i;
                            }
                            expectedByte(2, 4);
                        }
                        i6 = i21 + 1;
                        i7 = i27;
                    }
                    if ((i7 & 192) != 128) {
                        if (i18 > i) {
                            this.fBuffer[0] = (byte) i22;
                            this.fBuffer[1] = (byte) i7;
                            this.fOffset = 2;
                            return i18 - i;
                        }
                        invalidByte(2, 4, i7);
                    }
                    int i28 = i26 + 1;
                    if (i28 < i4) {
                        i8 = this.fBuffer[i28] & Constants.UNKNOWN;
                    } else {
                        int i29 = this.fInputStream.read();
                        if (i29 == -1) {
                            if (i18 > i) {
                                this.fBuffer[0] = (byte) i22;
                                this.fBuffer[1] = (byte) i7;
                                this.fOffset = 2;
                                return i18 - i;
                            }
                            expectedByte(3, 4);
                        }
                        i6++;
                        i8 = i29;
                    }
                    if ((i8 & 192) != 128) {
                        if (i18 > i) {
                            this.fBuffer[0] = (byte) i22;
                            this.fBuffer[1] = (byte) i7;
                            this.fBuffer[2] = (byte) i8;
                            this.fOffset = 3;
                            return i18 - i;
                        }
                        invalidByte(3, 4, i8);
                    }
                    i19 = i28 + 1;
                    if (i19 < i4) {
                        i9 = this.fBuffer[i19] & Constants.UNKNOWN;
                        i10 = i6;
                    } else {
                        i9 = this.fInputStream.read();
                        if (i9 == -1) {
                            if (i18 > i) {
                                this.fBuffer[0] = (byte) i22;
                                this.fBuffer[1] = (byte) i7;
                                this.fBuffer[2] = (byte) i8;
                                this.fOffset = 3;
                                return i18 - i;
                            }
                            expectedByte(4, 4);
                        }
                        i10 = i6 + 1;
                    }
                    if ((i9 & 192) != 128) {
                        if (i18 > i) {
                            this.fBuffer[0] = (byte) i22;
                            this.fBuffer[1] = (byte) i7;
                            this.fBuffer[2] = (byte) i8;
                            this.fBuffer[3] = (byte) i9;
                            this.fOffset = 4;
                            return i18 - i;
                        }
                        invalidByte(4, 4, i8);
                    }
                    int i30 = ((i22 << 2) & 28) | ((i7 >> 4) & 3);
                    if (i30 > 16) {
                        invalidSurrogate(i30);
                    }
                    int i31 = i8 & 63;
                    int i32 = (((i30 - 1) << 6) & 960) | 55296 | ((i7 & 15) << 2) | (i31 >> 4);
                    int i33 = (i9 & 63) | ((i31 << 6) & 960) | 56320;
                    int i34 = i18 + 1;
                    cArr[i18] = (char) i32;
                    i5 = i34 + 1;
                    cArr[i34] = (char) i33;
                    i21 = i10 - 2;
                } else {
                    if (i18 > i) {
                        this.fBuffer[0] = (byte) i22;
                        this.fOffset = 1;
                        return i18 - i;
                    }
                    invalidByte(1, 1, i22);
                    i5 = i18;
                }
                i19++;
                i18 = i5;
            }
            return i21;
        }
        return i4;
    }

    @Override // java.io.Reader
    public long skip(long j) throws IOException {
        char[] cArr = new char[this.fBuffer.length];
        long j2 = j;
        do {
            int i = read(cArr, 0, ((long) cArr.length) < j2 ? cArr.length : (int) j2);
            if (i <= 0) {
                break;
            }
            j2 -= (long) i;
        } while (j2 > 0);
        return j - j2;
    }

    @Override // java.io.Reader
    public boolean ready() {
        return DEBUG_READ;
    }

    @Override // java.io.Reader
    public boolean markSupported() {
        return DEBUG_READ;
    }

    @Override // java.io.Reader
    public void mark(int i) throws IOException {
        throw new IOException(this.fFormatter.formatMessage(this.fLocale, "OperationNotSupported", new Object[]{"mark()", XMLStreamWriterImpl.UTF_8}));
    }

    @Override // java.io.Reader
    public void reset() {
        this.fOffset = 0;
        this.fSurrogate = -1;
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        ThreadLocalBufferAllocator.getBufferAllocator().returnByteBuffer(this.fBuffer);
        this.fBuffer = null;
        this.fInputStream.close();
    }

    private void expectedByte(int i, int i2) throws UTFDataFormatException {
        throw new UTFDataFormatException(this.fFormatter.formatMessage(this.fLocale, "ExpectedByte", new Object[]{Integer.toString(i), Integer.toString(i2)}));
    }

    private void invalidByte(int i, int i2, int i3) throws UTFDataFormatException {
        throw new UTFDataFormatException(this.fFormatter.formatMessage(this.fLocale, "InvalidByte", new Object[]{Integer.toString(i), Integer.toString(i2)}));
    }

    private void invalidSurrogate(int i) throws UTFDataFormatException {
        new StringBuffer().append("high surrogate bits in UTF-8 sequence must not exceed 0x10 but found 0x");
        throw new UTFDataFormatException(this.fFormatter.formatMessage(this.fLocale, "InvalidHighSurrogate", new Object[]{Integer.toHexString(i)}));
    }
}

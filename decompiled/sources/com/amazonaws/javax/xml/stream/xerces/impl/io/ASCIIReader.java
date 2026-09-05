package com.amazonaws.javax.xml.stream.xerces.impl.io;

import com.amazonaws.javax.xml.stream.util.ThreadLocalBufferAllocator;
import com.amazonaws.javax.xml.stream.xerces.util.MessageFormatter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Locale;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ASCIIReader extends Reader {
    public static final int DEFAULT_BUFFER_SIZE = 2048;
    protected byte[] fBuffer;
    private MessageFormatter fFormatter;
    protected InputStream fInputStream;
    private Locale fLocale;

    public ASCIIReader(InputStream inputStream, MessageFormatter messageFormatter, Locale locale) {
        this(inputStream, 2048, messageFormatter, locale);
    }

    public ASCIIReader(InputStream inputStream, int i, MessageFormatter messageFormatter, Locale locale) {
        this.fFormatter = null;
        this.fLocale = null;
        this.fInputStream = inputStream;
        this.fBuffer = ThreadLocalBufferAllocator.getBufferAllocator().getByteBuffer(i);
        if (this.fBuffer == null) {
            this.fBuffer = new byte[i];
        }
        this.fFormatter = messageFormatter;
        this.fLocale = locale;
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        int i = this.fInputStream.read();
        if (i > 128) {
            throw new IOException(this.fFormatter.formatMessage(this.fLocale, "InvalidASCII", new Object[]{Integer.toString(i)}));
        }
        return i;
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        if (i2 > this.fBuffer.length) {
            i2 = this.fBuffer.length;
        }
        int i3 = this.fInputStream.read(this.fBuffer, 0, i2);
        for (int i4 = 0; i4 < i3; i4++) {
            byte b = this.fBuffer[i4];
            if (b > 128) {
                throw new IOException(this.fFormatter.formatMessage(this.fLocale, "InvalidASCII", new Object[]{Integer.toString(b)}));
            }
            cArr[i + i4] = (char) b;
        }
        return i3;
    }

    @Override // java.io.Reader
    public long skip(long j) {
        return this.fInputStream.skip(j);
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

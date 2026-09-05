package com.amazonaws.javax.xml.stream.writers;

import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import com.facebook.internal.NativeProtocol;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class UTF8OutputStreamWriter extends Writer {
    int lastUTF16CodePoint = 0;
    OutputStream out;

    public UTF8OutputStreamWriter(OutputStream outputStream) {
        this.out = outputStream;
    }

    public String getEncoding() {
        return XMLStreamWriterImpl.UTF_8;
    }

    @Override // java.io.Writer
    public void write(int i) throws IOException {
        if (this.lastUTF16CodePoint != 0) {
            int i2 = (((this.lastUTF16CodePoint & 1023) << 10) | (i & 1023)) + NativeProtocol.MESSAGE_GET_ACCESS_TOKEN_REQUEST;
            if (i2 < 0 || i2 >= 2097152) {
                throw new IOException(new StringBuffer().append("Atttempting to write invalid Unicode code point '").append(i2).append("'").toString());
            }
            this.out.write((i2 >> 18) | 240);
            this.out.write(((i2 >> 12) & 63) | XMLChar.MASK_NCNAME);
            this.out.write(((i2 >> 6) & 63) | XMLChar.MASK_NCNAME);
            this.out.write((i2 & 63) | XMLChar.MASK_NCNAME);
            this.lastUTF16CodePoint = 0;
            return;
        }
        if (i < 128) {
            this.out.write(i);
            return;
        }
        if (i < 2048) {
            this.out.write((i >> 6) | 192);
            this.out.write((i & 63) | XMLChar.MASK_NCNAME);
        } else if (i <= 65535) {
            if (!XMLChar.isHighSurrogate(i) && !XMLChar.isLowSurrogate(i)) {
                this.out.write((i >> 12) | 224);
                this.out.write(((i >> 6) & 63) | XMLChar.MASK_NCNAME);
                this.out.write((i & 63) | XMLChar.MASK_NCNAME);
                return;
            }
            this.lastUTF16CodePoint = i;
        }
    }

    @Override // java.io.Writer
    public void write(char[] cArr) throws IOException {
        for (char c : cArr) {
            write(c);
        }
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) throws IOException {
        for (int i3 = 0; i3 < i2; i3++) {
            write(cArr[i + i3]);
        }
    }

    @Override // java.io.Writer
    public void write(String str) throws IOException {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            write(str.charAt(i));
        }
    }

    @Override // java.io.Writer
    public void write(String str, int i, int i2) throws IOException {
        for (int i3 = 0; i3 < i2; i3++) {
            write(str.charAt(i + i3));
        }
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.lastUTF16CodePoint != 0) {
            throw new IllegalStateException("Attempting to close a UTF8OutputStreamWriter while awaiting for a UTF-16 code unit");
        }
        this.out.close();
    }
}

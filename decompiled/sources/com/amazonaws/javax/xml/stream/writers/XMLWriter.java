package com.amazonaws.javax.xml.stream.writers;

import com.amazonaws.javax.xml.stream.xerces.util.XMLStringBuffer;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class XMLWriter extends Writer {
    private static final boolean DEBUG = false;
    private static final int THRESHHOLD_LENGTH = 4096;
    private XMLStringBuffer buffer;
    private int size;
    private Writer writer;

    public XMLWriter(Writer writer) {
        this(writer, THRESHHOLD_LENGTH);
    }

    public XMLWriter(Writer writer, int i) {
        this.buffer = new XMLStringBuffer(12288);
        this.writer = writer;
        this.size = i;
    }

    @Override // java.io.Writer
    public void write(int i) throws IOException {
        ensureOpen();
        this.buffer.append((char) i);
        conditionalWrite();
    }

    @Override // java.io.Writer
    public void write(char[] cArr) throws IOException {
        write(cArr, 0, cArr.length);
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) throws IOException {
        ensureOpen();
        if (i2 > this.size) {
            writeBufferedData();
            this.writer.write(cArr, i, i2);
        } else {
            this.buffer.append(cArr, i, i2);
            conditionalWrite();
        }
    }

    @Override // java.io.Writer
    public void write(String str, int i, int i2) throws IOException {
        write(str.toCharArray(), i, i2);
    }

    @Override // java.io.Writer
    public void write(String str) throws IOException {
        if (str.length() > this.size) {
            writeBufferedData();
            this.writer.write(str);
        } else {
            this.buffer.append(str);
            conditionalWrite();
        }
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.writer != null) {
            flush();
            this.writer.close();
            this.writer = null;
        }
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        ensureOpen();
        writeBufferedData();
        this.writer.flush();
    }

    public void reset() {
        this.writer = null;
        this.buffer.clear();
        this.size = THRESHHOLD_LENGTH;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
        this.buffer.clear();
        this.size = THRESHHOLD_LENGTH;
    }

    public void setWriter(Writer writer, int i) {
        this.writer = writer;
        this.size = i;
    }

    protected Writer getWriter() {
        return this.writer;
    }

    private void conditionalWrite() throws IOException {
        if (this.buffer.length > this.size) {
            writeBufferedData();
        }
    }

    private void writeBufferedData() throws IOException {
        this.writer.write(this.buffer.ch, this.buffer.offset, this.buffer.length);
        this.buffer.clear();
    }

    private void ensureOpen() throws IOException {
        if (this.writer == null) {
            throw new IOException("Stream closed");
        }
    }
}

package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class FileBufferManager extends BufferManager {
    static final boolean DEBUG = false;
    static final int DEFAULT_LENGTH = 8192;
    static final int THRESH_HOLD = 81920;
    boolean calledGetMore;
    CharsetDecoder decoder = null;
    FileChannel fChannel = null;
    CharBuffer charBuffer = null;
    long remaining = -1;
    long filepos = 0;
    long filesize = -1;

    public FileBufferManager(FileInputStream fileInputStream, String str) throws IOException {
        init(fileInputStream);
        setDecoder(XMLStreamWriterImpl.UTF_8);
    }

    void init(FileInputStream fileInputStream) {
        this.charBuffer = CharBuffer.allocate(16384);
        this.fChannel = fileInputStream.getChannel();
        this.filesize = this.fChannel.size();
        this.remaining = this.filesize;
    }

    @Override // com.amazonaws.javax.xml.stream.BufferManager
    public boolean arrangeCapacity(int i) throws IOException {
        if (!this.calledGetMore) {
            getMore();
        }
        if (getCharBuffer().limit() - getCharBuffer().position() >= i) {
            return true;
        }
        while (getCharBuffer().limit() - getCharBuffer().position() < i && !endOfStream()) {
            getMore();
        }
        if (getCharBuffer().limit() - getCharBuffer().position() < i) {
            return DEBUG;
        }
        return true;
    }

    public ByteBuffer getMoreBytes() throws IOException {
        ByteBuffer byteBufferAllocate;
        int length = getLength();
        if (this.endOfStream) {
            return ByteBuffer.allocate(0);
        }
        if (this.filesize > 81920) {
            byteBufferAllocate = this.fChannel.map(FileChannel.MapMode.READ_ONLY, this.filepos, length);
            this.filepos += (long) byteBufferAllocate.limit();
        } else {
            byteBufferAllocate = ByteBuffer.allocate(getLength());
            this.fChannel.read(byteBufferAllocate);
            this.filepos = this.fChannel.position();
            byteBufferAllocate.flip();
        }
        this.remaining = this.filesize - this.filepos;
        if (this.remaining < 1) {
            this.endOfStream = true;
            return byteBufferAllocate;
        }
        return byteBufferAllocate;
    }

    @Override // com.amazonaws.javax.xml.stream.BufferManager
    public boolean getMore() throws IOException {
        this.calledGetMore = true;
        if (this.endOfStream) {
            return DEBUG;
        }
        ByteBuffer moreBytes = getMoreBytes();
        if (this.charBuffer.position() != 0) {
            this.charBuffer.compact();
        } else {
            this.charBuffer.clear();
        }
        int iPosition = this.charBuffer.position();
        CoderResult coderResultDecode = this.decoder.decode(moreBytes, this.charBuffer, DEBUG);
        while (moreBytes.remaining() > 0) {
            if (coderResultDecode.isOverflow()) {
                resizeCharBuffer(this.charBuffer.limit() + moreBytes.remaining());
            }
            coderResultDecode = this.decoder.decode(moreBytes, this.charBuffer, true);
        }
        if (coderResultDecode.isUnderflow()) {
            this.decoder.decode(moreBytes, this.charBuffer, true);
            this.decoder.flush(this.charBuffer);
        }
        this.decoder.reset();
        if (this.charBuffer.position() <= iPosition) {
            return DEBUG;
        }
        this.charBuffer.flip();
        return true;
    }

    @Override // com.amazonaws.javax.xml.stream.BufferManager
    public CharBuffer getCharBuffer() {
        return this.charBuffer;
    }

    CharSequence getCharSequence() {
        return this.charBuffer.subSequence(0, this.charBuffer.remaining());
    }

    CharBuffer resizeCharBuffer(int i) {
        this.charBuffer = CharBuffer.allocate(i).put((CharBuffer) this.charBuffer.flip());
        return this.charBuffer;
    }

    int getLength() {
        if (this.remaining < 16384) {
            return (int) this.remaining;
        }
        return 16384;
    }

    void setDecoder(String str) throws IOException {
        if (str != null) {
            this.decoder = Charset.forName(str).newDecoder();
            return;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        this.fChannel.read(byteBufferAllocate);
        byte[] bArr = new byte[4];
        byteBufferAllocate.get(bArr);
        this.decoder = Charset.forName((String) getEncodingName(bArr, 4)[0]).newDecoder();
    }

    static void printByteBuffer(ByteBuffer byteBuffer) {
        System.out.println("------------ByteBuffer Details---------");
        System.out.println(new StringBuffer().append("bb.position = ").append(byteBuffer.position()).toString());
        System.out.println(new StringBuffer().append("bb.remaining() = ").append(byteBuffer.remaining()).toString());
        System.out.println(new StringBuffer().append("bb.limit = ").append(byteBuffer.limit()).toString());
        System.out.println(new StringBuffer().append("bb.capacity = ").append(byteBuffer.capacity()).toString());
    }

    static void printCharBuffer(CharBuffer charBuffer) {
        System.out.println("----------- CharBuffer Details---------");
        System.out.println(new StringBuffer().append("bb.position = ").append(charBuffer.position()).toString());
        System.out.println(new StringBuffer().append("bb.remaining() = ").append(charBuffer.remaining()).toString());
        System.out.println(new StringBuffer().append("bb.limit = ").append(charBuffer.limit()).toString());
        System.out.println(new StringBuffer().append("bb.capacity = ").append(charBuffer.capacity()).toString());
    }

    public static void main(String[] strArr) {
        int i = 0;
        try {
            FileBufferManager fileBufferManager = new FileBufferManager(new FileInputStream(strArr[0]), XMLStreamWriterImpl.UTF_8);
            CharBuffer charBuffer = fileBufferManager.getCharBuffer();
            while (fileBufferManager.getMore()) {
                System.out.println(new StringBuffer().append("Loop ").append(i).append(" = ").append(fileBufferManager.getCharBuffer().toString()).toString());
                System.out.println("------------Loop CharBuffer details--------");
                printCharBuffer(charBuffer);
                i++;
            }
            System.out.println(new StringBuffer().append("End of file reached = ").append(fileBufferManager.endOfStream()).toString());
            System.out.println(new StringBuffer().append("Total no. of loops required = ").append(i).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.amazonaws.javax.xml.stream.BufferManager
    public void close() {
        if (this.fChannel != null) {
            this.fChannel.close();
        }
    }

    @Override // com.amazonaws.javax.xml.stream.BufferManager
    public void setEncoding(String str) {
    }
}

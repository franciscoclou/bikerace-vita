package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.amazonaws.javax.xml.stream.xerces.impl.io.ASCIIReader;
import com.amazonaws.javax.xml.stream.xerces.impl.io.UCSReader;
import com.amazonaws.javax.xml.stream.xerces.impl.io.UTF8Reader;
import com.amazonaws.javax.xml.stream.xerces.util.EncodingMap;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.CharBuffer;
import java.util.Locale;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class StreamBufferManager extends BufferManager {
    static final boolean DEBUG = false;
    static final int DEFAULT_LENGTH = 8192;
    CharBuffer charBuffer = null;
    Reader fReader = null;
    boolean fAllowJavaEncodings = true;

    public StreamBufferManager(InputStream inputStream, String str) throws IOException {
        init(inputStream, str);
    }

    void init(InputStream inputStream, String str) throws IOException {
        RewindableInputStream rewindableInputStream = new RewindableInputStream(inputStream);
        if (str == null) {
            byte[] bArr = new byte[4];
            int i = 0;
            while (i < 4) {
                bArr[i] = (byte) rewindableInputStream.read();
                i++;
            }
            if (i == 4) {
                Object[] encodingName = getEncodingName(bArr, i);
                String str2 = (String) encodingName[0];
                Boolean bool = (Boolean) encodingName[1];
                rewindableInputStream.reset();
                if (i > 2 && str2.equals(XMLStreamWriterImpl.UTF_8)) {
                    int i2 = bArr[0] & com.flurry.android.Constants.UNKNOWN;
                    int i3 = bArr[1] & com.flurry.android.Constants.UNKNOWN;
                    int i4 = bArr[2] & com.flurry.android.Constants.UNKNOWN;
                    if (i2 == 239 && i3 == 187 && i4 == 191) {
                        rewindableInputStream.skip(3L);
                    }
                }
                this.fReader = createReader(rewindableInputStream, str2, bool);
            } else {
                this.fReader = createReader(rewindableInputStream, str, null);
            }
        } else {
            this.fReader = createReader(rewindableInputStream, str, null);
        }
        this.charBuffer = CharBuffer.allocate(8192);
    }

    @Override // com.amazonaws.javax.xml.stream.BufferManager
    public CharBuffer getCharBuffer() {
        return this.charBuffer;
    }

    @Override // com.amazonaws.javax.xml.stream.BufferManager
    public boolean getMore() throws IOException {
        if (this.charBuffer.position() != 0) {
            this.charBuffer.compact();
        }
        char[] cArrArray = this.charBuffer.array();
        int i = this.fReader.read(cArrArray, this.charBuffer.position(), this.charBuffer.capacity());
        if (i == -1) {
            this.endOfStream = true;
            return DEBUG;
        }
        CharBuffer charBuffer = this.charBuffer;
        this.charBuffer = CharBuffer.wrap(cArrArray);
        this.charBuffer.limit(i);
        if (i > 0) {
            return true;
        }
        return DEBUG;
    }

    protected Reader createReader(InputStream inputStream, String str, Boolean bool) throws IOException {
        if (str == null) {
            str = XMLStreamWriterImpl.UTF_8;
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        if (upperCase.equals(XMLStreamWriterImpl.UTF_8)) {
            return new UTF8Reader(inputStream, 8192, null, Locale.getDefault());
        }
        if (upperCase.equals("US-ASCII")) {
            return new ASCIIReader(inputStream, 8192, null, Locale.getDefault());
        }
        if (upperCase.equals("ISO-10646-UCS-4")) {
            if (bool != null) {
                if (bool.booleanValue()) {
                    return new UCSReader(inputStream, (short) 8);
                }
                return new UCSReader(inputStream, (short) 4);
            }
            throw new IOException("Encoding byte order not supported");
        }
        if (upperCase.equals("ISO-10646-UCS-2")) {
            if (bool != null) {
                if (bool.booleanValue()) {
                    return new UCSReader(inputStream, (short) 2);
                }
                return new UCSReader(inputStream, (short) 1);
            }
            throw new IOException("Encoding byte order not supported");
        }
        boolean zIsValidIANAEncoding = XMLChar.isValidIANAEncoding(str);
        boolean zIsValidJavaEncoding = XMLChar.isValidJavaEncoding(str);
        if (!zIsValidIANAEncoding || (this.fAllowJavaEncodings && !zIsValidJavaEncoding)) {
            throw new IOException(new StringBuffer().append("Encoding declaration ").append(str).append("not valid").toString());
        }
        String iANA2JavaMapping = EncodingMap.getIANA2JavaMapping(upperCase);
        if (iANA2JavaMapping != null) {
            str = iANA2JavaMapping;
        } else if (!this.fAllowJavaEncodings) {
            throw new IOException(new StringBuffer().append("Encoding ").append(str).append(" not supported").toString());
        }
        return new BufferedReader(new InputStreamReader(inputStream, str));
    }

    int getLength() {
        return 8192;
    }

    public static void main(String[] strArr) {
        int i = 0;
        try {
            File file = new File(strArr[0]);
            System.out.println(new StringBuffer().append("url parameter = ").append(file.toURI().toString()).toString());
            StreamBufferManager streamBufferManager = new StreamBufferManager(new URL(file.toURI().toString()).openStream(), XMLStreamWriterImpl.UTF_8);
            streamBufferManager.getCharBuffer();
            while (streamBufferManager.getMore()) {
                System.out.println(new StringBuffer().append("Loop ").append(i).append(" = ").append((Object) streamBufferManager.getCharBuffer()).toString());
                i++;
            }
            System.out.println(new StringBuffer().append("End of stream reached = ").append(streamBufferManager.endOfStream()).toString());
            System.out.println(new StringBuffer().append("Total no. of loops required = ").append(i).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.amazonaws.javax.xml.stream.BufferManager
    public void close() throws IOException {
        if (this.fReader != null) {
            this.fReader.close();
        }
    }

    @Override // com.amazonaws.javax.xml.stream.BufferManager
    public void setEncoding(String str) {
    }

    @Override // com.amazonaws.javax.xml.stream.BufferManager
    public boolean arrangeCapacity(int i) {
        return DEBUG;
    }

    public final class RewindableInputStream extends InputStream {
        static final int DEFAULT_XMLDECL_BUFFER_SIZE = 64;
        private InputStream fInputStream;
        private byte[] fData = new byte[64];
        private int fStartOffset = 0;
        private int fEndOffset = -1;
        private int fOffset = 0;
        private int fLength = 0;
        private int fMark = 0;

        public RewindableInputStream(InputStream inputStream) {
            this.fInputStream = inputStream;
        }

        public void setStartOffset(int i) {
            this.fStartOffset = i;
        }

        public void rewind() {
            this.fOffset = this.fStartOffset;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            if (this.fOffset < this.fLength) {
                byte[] bArr = this.fData;
                int i = this.fOffset;
                this.fOffset = i + 1;
                return bArr[i] & com.flurry.android.Constants.UNKNOWN;
            }
            if (this.fOffset == this.fEndOffset) {
                return -1;
            }
            if (this.fOffset == this.fData.length) {
                byte[] bArr2 = new byte[this.fOffset << 1];
                System.arraycopy(this.fData, 0, bArr2, 0, this.fOffset);
                this.fData = bArr2;
            }
            int i2 = this.fInputStream.read();
            if (i2 == -1) {
                this.fEndOffset = this.fOffset;
                return -1;
            }
            byte[] bArr3 = this.fData;
            int i3 = this.fLength;
            this.fLength = i3 + 1;
            bArr3[i3] = (byte) i2;
            this.fOffset++;
            return i2 & 255;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) {
            int i3 = this.fLength - this.fOffset;
            if (i3 == 0) {
                if (this.fOffset == this.fEndOffset) {
                    return -1;
                }
                return this.fInputStream.read(bArr, i, i2);
            }
            if (i2 < i3) {
                if (i2 <= 0) {
                    return 0;
                }
                i3 = i2;
            }
            if (bArr != null) {
                System.arraycopy(this.fData, this.fOffset, bArr, i, i3);
            }
            this.fOffset += i3;
            return i3;
        }

        @Override // java.io.InputStream
        public long skip(long j) {
            if (j <= 0) {
                return 0L;
            }
            int i = this.fLength - this.fOffset;
            if (i == 0) {
                if (this.fOffset == this.fEndOffset) {
                    return 0L;
                }
                return this.fInputStream.skip(j);
            }
            if (j <= i) {
                this.fOffset = (int) (((long) this.fOffset) + j);
                return j;
            }
            this.fOffset += i;
            if (this.fOffset == this.fEndOffset) {
                return i;
            }
            return this.fInputStream.skip(j - ((long) i)) + ((long) i);
        }

        @Override // java.io.InputStream
        public int available() {
            int i = this.fLength - this.fOffset;
            if (i == 0) {
                if (this.fOffset == this.fEndOffset) {
                    return -1;
                }
                return this.fInputStream.available();
            }
            return i;
        }

        @Override // java.io.InputStream
        public void mark(int i) {
            this.fMark = this.fOffset;
        }

        @Override // java.io.InputStream
        public void reset() {
            this.fOffset = this.fMark;
        }

        @Override // java.io.InputStream
        public boolean markSupported() {
            return true;
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.fInputStream != null) {
                this.fInputStream.close();
                this.fInputStream = null;
            }
        }
    }
}

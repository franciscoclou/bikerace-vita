package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLInputSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.CharBuffer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class BufferManager {
    static boolean DEBUG = false;
    protected boolean endOfStream = false;

    public abstract boolean arrangeCapacity(int i);

    public abstract void close();

    public abstract CharBuffer getCharBuffer();

    public abstract boolean getMore();

    public abstract void setEncoding(String str);

    public static BufferManager getBufferManager(XMLInputSource xMLInputSource) {
        InputStream byteStream = xMLInputSource.getByteStream();
        if (byteStream instanceof FileInputStream) {
            if (DEBUG) {
                System.out.println("Using FileBufferManager");
            }
            return new FileBufferManager((FileInputStream) byteStream, xMLInputSource.getEncoding());
        }
        if (DEBUG) {
            System.out.println("Using StreamBufferManager");
        }
        return new StreamBufferManager(byteStream, xMLInputSource.getEncoding());
    }

    public boolean endOfStream() {
        return this.endOfStream;
    }

    protected Object[] getEncodingName(byte[] bArr, int i) {
        if (i < 2) {
            return new Object[]{XMLStreamWriterImpl.UTF_8, null};
        }
        int i2 = bArr[0] & com.flurry.android.Constants.UNKNOWN;
        int i3 = bArr[1] & com.flurry.android.Constants.UNKNOWN;
        if (i2 == 254 && i3 == 255) {
            return new Object[]{"UTF-16BE", new Boolean(true)};
        }
        if (i2 == 255 && i3 == 254) {
            return new Object[]{"UTF-16LE", new Boolean(false)};
        }
        if (i < 3) {
            return new Object[]{XMLStreamWriterImpl.UTF_8, null};
        }
        int i4 = bArr[2] & com.flurry.android.Constants.UNKNOWN;
        if (i2 == 239 && i3 == 187 && i4 == 191) {
            return new Object[]{XMLStreamWriterImpl.UTF_8, null};
        }
        if (i < 4) {
            return new Object[]{XMLStreamWriterImpl.UTF_8, null};
        }
        int i5 = bArr[3] & com.flurry.android.Constants.UNKNOWN;
        if (i2 == 0 && i3 == 0 && i4 == 0 && i5 == 60) {
            return new Object[]{"ISO-10646-UCS-4", new Boolean(true)};
        }
        if (i2 == 60 && i3 == 0 && i4 == 0 && i5 == 0) {
            return new Object[]{"ISO-10646-UCS-4", new Boolean(false)};
        }
        if (i2 == 0 && i3 == 0 && i4 == 60 && i5 == 0) {
            return new Object[]{"ISO-10646-UCS-4", null};
        }
        if (i2 == 0 && i3 == 60 && i4 == 0 && i5 == 0) {
            return new Object[]{"ISO-10646-UCS-4", null};
        }
        if (i2 == 0 && i3 == 60 && i4 == 0 && i5 == 63) {
            return new Object[]{"UTF-16BE", new Boolean(true)};
        }
        if (i2 == 60 && i3 == 0 && i4 == 63 && i5 == 0) {
            return new Object[]{"UTF-16LE", new Boolean(false)};
        }
        if (i2 == 76 && i3 == 111 && i4 == 167 && i5 == 148) {
            return new Object[]{"CP037", null};
        }
        return new Object[]{XMLStreamWriterImpl.UTF_8, null};
    }

    public static void main(String[] strArr) {
        try {
            File file = new File(strArr[0]);
            System.out.println(new StringBuffer().append("url parameter = ").append(file.toURI().toString()).toString());
            new URL(file.toURI().toString());
            BufferManager bufferManager = getBufferManager(new XMLInputSource((String) null, (String) null, (String) null, new FileInputStream(file), XMLStreamWriterImpl.UTF_8));
            bufferManager.getCharBuffer();
            int i = 0;
            while (bufferManager.getMore()) {
                System.out.println(new StringBuffer().append("Loop ").append(i).append(" = ").append((Object) bufferManager.getCharBuffer()).toString());
                i++;
            }
            System.out.println(new StringBuffer().append("End of stream reached = ").append(bufferManager.endOfStream()).toString());
            System.out.println(new StringBuffer().append("Total no. of loops required = ").append(i).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.heyzap.a;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;

/* JADX INFO: compiled from: SimpleMultipartEntity.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class k implements HttpEntity {
    private static final char[] d = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    ByteArrayOutputStream f746a = new ByteArrayOutputStream();
    boolean b = false;
    boolean c = false;
    private String e;

    public k() {
        this.e = null;
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            stringBuffer.append(d[random.nextInt(d.length)]);
        }
        this.e = stringBuffer.toString();
    }

    public void a() {
        if (!this.c) {
            try {
                this.f746a.write(("--" + this.e + "\r\n").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.c = true;
    }

    public void b() {
        if (!this.b) {
            try {
                this.f746a.write(("\r\n--" + this.e + "--\r\n").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.b = true;
        }
    }

    public void a(String str, String str2) {
        a();
        try {
            this.f746a.write(("Content-Disposition: form-data; name=\"" + str + "\"\r\n\r\n").getBytes());
            this.f746a.write(str2.getBytes());
            this.f746a.write(("\r\n--" + this.e + "\r\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void a(String str, String str2, InputStream inputStream, boolean z) {
        a(str, str2, inputStream, "application/octet-stream", z);
    }

    public void a(String str, String str2, InputStream inputStream, String str3, boolean z) {
        a();
        try {
            this.f746a.write(("Content-Disposition: form-data; name=\"" + str + "\"; filename=\"" + str2 + "\"\r\n").getBytes());
            this.f746a.write(("Content-Type: " + str3 + "\r\n").getBytes());
            this.f746a.write("Content-Transfer-Encoding: binary\r\n\r\n".getBytes());
            byte[] bArr = new byte[4096];
            while (true) {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    break;
                } else {
                    this.f746a.write(bArr, 0, i);
                }
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (!z) {
                this.f746a.write(("\r\n--" + this.e + "\r\n").getBytes());
            }
            this.f746a.flush();
        } catch (IOException e2) {
            e2.printStackTrace();
        } finally {
            inputStream.close();
        }
    }

    @Override // org.apache.http.HttpEntity
    public long getContentLength() {
        b();
        return this.f746a.toByteArray().length;
    }

    @Override // org.apache.http.HttpEntity
    public Header getContentType() {
        return new BasicHeader("Content-Type", "multipart/form-data; boundary=" + this.e);
    }

    @Override // org.apache.http.HttpEntity
    public boolean isChunked() {
        return false;
    }

    @Override // org.apache.http.HttpEntity
    public boolean isRepeatable() {
        return false;
    }

    @Override // org.apache.http.HttpEntity
    public boolean isStreaming() {
        return false;
    }

    @Override // org.apache.http.HttpEntity
    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(this.f746a.toByteArray());
    }

    @Override // org.apache.http.HttpEntity
    public Header getContentEncoding() {
        return null;
    }

    @Override // org.apache.http.HttpEntity
    public void consumeContent() {
        if (isStreaming()) {
            throw new UnsupportedOperationException("Streaming entity does not implement #consumeContent()");
        }
    }

    @Override // org.apache.http.HttpEntity
    public InputStream getContent() {
        return new ByteArrayInputStream(this.f746a.toByteArray());
    }
}

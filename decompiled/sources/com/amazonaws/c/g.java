package com.amazonaws.c;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.methods.AbortableHttpRequest;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class g extends InputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Log f89a = LogFactory.getLog(g.class);
    private InputStream b;
    private HttpEntityEnclosingRequest c;
    private boolean d = false;
    private boolean e = false;

    public g(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        this.b = null;
        this.c = null;
        this.c = httpEntityEnclosingRequest;
        try {
            this.b = httpEntityEnclosingRequest.getEntity().getContent();
        } catch (IOException e) {
            if (f89a.isWarnEnabled()) {
                f89a.warn("Unable to obtain HttpMethod's response data stream", e);
            }
            try {
                httpEntityEnclosingRequest.getEntity().getContent().close();
            } catch (Exception e2) {
            }
            this.b = new ByteArrayInputStream(new byte[0]);
        }
    }

    protected void a() throws IOException {
        if (this.d) {
            return;
        }
        if (!this.e && (this.c instanceof AbortableHttpRequest)) {
            ((AbortableHttpRequest) this.c).abort();
        }
        this.b.close();
        this.d = true;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        try {
            return this.b.available();
        } catch (IOException e) {
            a();
            if (f89a.isDebugEnabled()) {
                f89a.debug("Released HttpMethod as its response data stream threw an exception", e);
            }
            throw e;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.d) {
            a();
            if (f89a.isDebugEnabled()) {
                f89a.debug("Released HttpMethod as its response data stream is closed");
            }
        }
        this.b.close();
    }

    protected void finalize() throws Throwable {
        if (!this.d) {
            if (f89a.isWarnEnabled()) {
                f89a.warn("Attempting to release HttpMethod in finalize() as its response data stream has gone out of scope. This attempt will not always succeed and cannot be relied upon! Please ensure S3 response data streams are always fully consumed or closed to avoid HTTP connection starvation.");
            }
            a();
            if (f89a.isWarnEnabled()) {
                f89a.warn("Successfully released HttpMethod in finalize(). You were lucky this time... Please ensure S3 response data streams are always fully consumed or closed.");
            }
        }
        super.finalize();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        try {
            int i = this.b.read();
            if (i == -1) {
                this.e = true;
                if (!this.d) {
                    a();
                    if (f89a.isDebugEnabled()) {
                        f89a.debug("Released HttpMethod as its response data stream is fully consumed");
                    }
                }
            }
            return i;
        } catch (IOException e) {
            a();
            if (f89a.isDebugEnabled()) {
                f89a.debug("Released HttpMethod as its response data stream threw an exception", e);
            }
            throw e;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        try {
            int i3 = this.b.read(bArr, i, i2);
            if (i3 == -1) {
                this.e = true;
                if (!this.d) {
                    a();
                    if (f89a.isDebugEnabled()) {
                        f89a.debug("Released HttpMethod as its response data stream is fully consumed");
                    }
                }
            }
            return i3;
        } catch (IOException e) {
            a();
            if (f89a.isDebugEnabled()) {
                f89a.debug("Released HttpMethod as its response data stream threw an exception", e);
            }
            throw e;
        }
    }
}

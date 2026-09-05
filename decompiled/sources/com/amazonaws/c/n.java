package com.amazonaws.c;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.InputStreamEntity;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class n extends BasicHttpEntity {
    private static final Log d = LogFactory.getLog(a.class);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f94a = true;
    private InputStreamEntity b;
    private InputStream c;
    private IOException e;

    n(com.amazonaws.j<?> jVar) {
        setChunked(false);
        long j = -1;
        try {
            String str = jVar.b().get("Content-Length");
            j = str != null ? Long.parseLong(str) : -1L;
        } catch (NumberFormatException e) {
            d.warn("Unable to parse content length from request.  Buffering contents in memory.");
        }
        String str2 = jVar.b().get("Content-Type");
        this.b = new InputStreamEntity(jVar.h(), j);
        this.b.setContentType(str2);
        this.c = jVar.h();
        setContent(this.c);
        setContentType(str2);
        setContentLength(j);
    }

    @Override // org.apache.http.entity.AbstractHttpEntity, org.apache.http.HttpEntity
    public boolean isChunked() {
        return false;
    }

    @Override // org.apache.http.entity.BasicHttpEntity, org.apache.http.HttpEntity
    public boolean isRepeatable() {
        return this.c.markSupported() || this.b.isRepeatable();
    }

    @Override // org.apache.http.entity.BasicHttpEntity, org.apache.http.HttpEntity
    public void writeTo(OutputStream outputStream) throws IOException {
        try {
            if (!this.f94a && isRepeatable()) {
                this.c.reset();
            }
            this.f94a = false;
            this.b.writeTo(outputStream);
        } catch (IOException e) {
            if (this.e == null) {
                this.e = e;
            }
            throw this.e;
        }
    }
}

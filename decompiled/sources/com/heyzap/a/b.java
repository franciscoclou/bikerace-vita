package com.heyzap.a;

import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;

/* JADX INFO: compiled from: AsyncHttpClient.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class b extends HttpEntityWrapper {
    public b(HttpEntity httpEntity) {
        super(httpEntity);
    }

    @Override // org.apache.http.entity.HttpEntityWrapper, org.apache.http.HttpEntity
    public InputStream getContent() {
        return new GZIPInputStream(this.wrappedEntity.getContent());
    }

    @Override // org.apache.http.entity.HttpEntityWrapper, org.apache.http.HttpEntity
    public long getContentLength() {
        return -1L;
    }
}

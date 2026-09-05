package com.heyzap.a;

import java.io.IOException;
import java.net.ConnectException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

/* JADX INFO: compiled from: AsyncHttpRequest.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class c implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AbstractHttpClient f738a;
    private final HttpContext b;
    private final HttpUriRequest c;
    private final d d;
    private int e;

    public c(AbstractHttpClient abstractHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, d dVar) {
        this.f738a = abstractHttpClient;
        this.b = httpContext;
        this.c = httpUriRequest;
        this.d = dVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.d != null) {
                this.d.c();
            }
            b();
            if (this.d != null) {
                this.d.d();
            }
        } catch (IOException e) {
            if (this.d != null) {
                this.d.d();
                this.d.b(e, null);
            }
        }
    }

    private void a() throws IOException {
        if (!Thread.currentThread().isInterrupted()) {
            HttpResponse httpResponseExecute = this.f738a.execute(this.c, this.b);
            if (!Thread.currentThread().isInterrupted() && this.d != null) {
                this.d.a(httpResponseExecute);
            }
        }
    }

    private void b() throws ConnectException {
        boolean zRetryRequest = true;
        IOException e = null;
        HttpRequestRetryHandler httpRequestRetryHandler = this.f738a.getHttpRequestRetryHandler();
        while (zRetryRequest) {
            try {
                a();
                return;
            } catch (IOException e2) {
                e = e2;
                int i = this.e + 1;
                this.e = i;
                zRetryRequest = httpRequestRetryHandler.retryRequest(e, i, this.b);
            } catch (NullPointerException e3) {
                e = new IOException("NPE in HttpClient" + String.valueOf(e3.getMessage()));
                int i2 = this.e + 1;
                this.e = i2;
                zRetryRequest = httpRequestRetryHandler.retryRequest(e, i2, this.b);
            }
        }
        ConnectException connectException = new ConnectException();
        connectException.initCause(e);
        throw connectException;
    }
}

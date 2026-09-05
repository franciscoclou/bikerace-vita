package com.heyzap.a;

import android.os.SystemClock;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import javax.net.ssl.SSLHandshakeException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

/* JADX INFO: compiled from: RetryHandler.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class i implements HttpRequestRetryHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static HashSet<Class<?>> f744a = new HashSet<>();
    private static HashSet<Class<?>> b = new HashSet<>();
    private final int c;

    static {
        f744a.add(NoHttpResponseException.class);
        f744a.add(UnknownHostException.class);
        f744a.add(SocketException.class);
        b.add(InterruptedIOException.class);
        b.add(SSLHandshakeException.class);
    }

    public i(int i) {
        this.c = i;
    }

    @Override // org.apache.http.client.HttpRequestRetryHandler
    public boolean retryRequest(IOException iOException, int i, HttpContext httpContext) {
        boolean z = false;
        Boolean bool = (Boolean) httpContext.getAttribute("http.request_sent");
        boolean z2 = bool != null && bool.booleanValue();
        if (i <= this.c && !b.contains(iOException.getClass()) && (f744a.contains(iOException.getClass()) || !z2 || !((HttpUriRequest) httpContext.getAttribute("http.request")).getMethod().equals("POST"))) {
            z = true;
        }
        if (z) {
            SystemClock.sleep(1500L);
        } else {
            iOException.printStackTrace();
        }
        return z;
    }
}

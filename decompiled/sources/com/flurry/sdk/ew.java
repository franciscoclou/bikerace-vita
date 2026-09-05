package com.flurry.sdk;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.params.HttpParams;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class ew {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static SchemeRegistry f556a;

    private static synchronized SchemeRegistry a() {
        SchemeRegistry schemeRegistry;
        if (f556a != null) {
            schemeRegistry = f556a;
        } else {
            f556a = new SchemeRegistry();
            f556a.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            f556a.register(new Scheme("https", new eu(), 443));
            schemeRegistry = f556a;
        }
        return schemeRegistry;
    }

    public static HttpClient a(HttpParams httpParams) {
        return new DefaultHttpClient(httpParams);
    }

    public static HttpClient b(HttpParams httpParams) {
        return new DefaultHttpClient(new SingleClientConnManager(httpParams, a()), httpParams);
    }
}

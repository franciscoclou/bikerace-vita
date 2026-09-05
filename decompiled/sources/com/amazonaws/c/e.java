package com.amazonaws.c;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class e {
    e() {
    }

    public HttpClient a(com.amazonaws.g gVar) {
        String strB = gVar.b();
        if (!strB.equals(com.amazonaws.g.f134a)) {
            strB = strB + ", " + com.amazonaws.g.f134a;
        }
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpClientParams.setRedirecting(basicHttpParams, false);
        HttpProtocolParams.setUserAgent(basicHttpParams, strB);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, gVar.k());
        HttpConnectionParams.setSoTimeout(basicHttpParams, gVar.j());
        HttpConnectionParams.setStaleCheckingEnabled(basicHttpParams, true);
        HttpConnectionParams.setTcpNoDelay(basicHttpParams, true);
        int i = gVar.l()[0];
        int i2 = gVar.l()[1];
        if (i > 0 || i2 > 0) {
            HttpConnectionParams.setSocketBufferSize(basicHttpParams, Math.max(i, i2));
        }
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient(b.a(gVar, basicHttpParams), basicHttpParams);
        String strC = gVar.c();
        int iD = gVar.d();
        if (strC != null && iD > 0) {
            a.f85a.info("Configuring Proxy. Proxy Host: " + strC + " Proxy Port: " + iD);
            defaultHttpClient.getParams().setParameter("http.route.default-proxy", new HttpHost(strC, iD));
            String strE = gVar.e();
            String strF = gVar.f();
            String strG = gVar.g();
            String strH = gVar.h();
            if (strE != null && strF != null) {
                defaultHttpClient.getCredentialsProvider().setCredentials(new AuthScope(strC, iD), new NTCredentials(strE, strF, strH, strG));
            }
        }
        return defaultHttpClient;
    }
}

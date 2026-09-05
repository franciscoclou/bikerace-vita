package com.heyzap.a;

import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;

/* JADX INFO: compiled from: AsyncHttpClient.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f735a = 10;
    private static int b = 10000;
    private final DefaultHttpClient c;
    private final HttpContext d;
    private ThreadPoolExecutor e;
    private final Map<Context, List<WeakReference<Future<?>>>> f;
    private final Map<String, String> g;

    public a() {
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        ConnManagerParams.setTimeout(basicHttpParams, b);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(f735a));
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 10);
        HttpConnectionParams.setSoTimeout(basicHttpParams, b);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, b);
        HttpConnectionParams.setTcpNoDelay(basicHttpParams, true);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, 8192);
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUserAgent(basicHttpParams, String.format("android-async-http/%s (http://loopj.com/android-async-http)", "1.3.2"));
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        ThreadSafeClientConnManager threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
        this.d = new SyncBasicHttpContext(new BasicHttpContext());
        this.c = new DefaultHttpClient(threadSafeClientConnManager, basicHttpParams);
        this.c.addRequestInterceptor(new HttpRequestInterceptor() { // from class: com.heyzap.a.a.1
            @Override // org.apache.http.HttpRequestInterceptor
            public void process(HttpRequest httpRequest, HttpContext httpContext) {
                if (!httpRequest.containsHeader("Accept-Encoding")) {
                    httpRequest.addHeader("Accept-Encoding", "gzip");
                }
                for (String str : a.this.g.keySet()) {
                    httpRequest.addHeader(str, (String) a.this.g.get(str));
                }
            }
        });
        this.c.addResponseInterceptor(new HttpResponseInterceptor() { // from class: com.heyzap.a.a.2
            @Override // org.apache.http.HttpResponseInterceptor
            public void process(HttpResponse httpResponse, HttpContext httpContext) {
                Header contentEncoding;
                HttpEntity entity = httpResponse.getEntity();
                if (entity != null && (contentEncoding = entity.getContentEncoding()) != null) {
                    HeaderElement[] elements = contentEncoding.getElements();
                    for (HeaderElement headerElement : elements) {
                        if (headerElement.getName().equalsIgnoreCase("gzip")) {
                            httpResponse.setEntity(new b(httpResponse.getEntity()));
                            return;
                        }
                    }
                }
            }
        });
        this.c.setHttpRequestRetryHandler(new i(5));
        this.e = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        this.f = new WeakHashMap();
        this.g = new HashMap();
    }

    public void a(CookieStore cookieStore) {
        this.d.setAttribute("http.cookie-store", cookieStore);
    }

    public void a(ThreadPoolExecutor threadPoolExecutor) {
        this.e = threadPoolExecutor;
    }

    public void a(Context context, String str, g gVar, d dVar) {
        a(this.c, this.d, new HttpGet(a(str, gVar)), null, dVar, context);
    }

    public void b(Context context, String str, g gVar, d dVar) {
        a(context, str, a(gVar), null, dVar);
    }

    public void a(Context context, String str, HttpEntity httpEntity, String str2, d dVar) {
        a(this.c, this.d, a(new HttpPost(str), httpEntity), str2, dVar, context);
    }

    private void a(DefaultHttpClient defaultHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, String str, d dVar, Context context) {
        if (str != null) {
            httpUriRequest.addHeader("Content-Type", str);
        }
        Future<?> futureSubmit = this.e.submit(new c(defaultHttpClient, httpContext, httpUriRequest, dVar));
        if (context != null) {
            List<WeakReference<Future<?>>> linkedList = this.f.get(context);
            if (linkedList == null) {
                linkedList = new LinkedList<>();
                this.f.put(context, linkedList);
            }
            linkedList.add(new WeakReference<>(futureSubmit));
        }
    }

    private String a(String str, g gVar) {
        if (gVar != null) {
            return str + "?" + gVar.c();
        }
        return str;
    }

    private HttpEntity a(g gVar) {
        if (gVar == null) {
            return null;
        }
        return gVar.a();
    }

    private HttpEntityEnclosingRequestBase a(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, HttpEntity httpEntity) {
        if (httpEntity != null) {
            httpEntityEnclosingRequestBase.setEntity(httpEntity);
        }
        return httpEntityEnclosingRequestBase;
    }
}

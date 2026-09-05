package com.amazonaws.c;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a {
    private final HttpClient c;
    private final com.amazonaws.g d;
    private final com.amazonaws.f.i e = new com.amazonaws.f.i(50);
    private static final Log b = LogFactory.getLog("com.amazonaws.request");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final Log f85a = LogFactory.getLog(a.class);
    private static final Random f = new Random();
    private static h g = new h();
    private static e h = new e();

    static {
        List listAsList = Arrays.asList("1.6.0_06", "1.6.0_13", "1.6.0_17");
        String property = System.getProperty("java.version");
        if (listAsList.contains(property)) {
            f85a.warn("Detected a possible problem with the current JVM version (" + property + ").  If you experience XML parsing problems using the SDK, try upgrading to a more recent JVM update.");
        }
    }

    public a(com.amazonaws.g gVar) {
        this.d = gVar;
        this.c = h.a(this.d);
    }

    private com.amazonaws.b a(com.amazonaws.j<?> jVar, j<com.amazonaws.b> jVar2, HttpRequestBase httpRequestBase, HttpResponse httpResponse) {
        com.amazonaws.b bVar;
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        i iVarA = a(httpRequestBase, jVar, httpResponse);
        if (jVar2.a() && (httpRequestBase instanceof HttpEntityEnclosingRequestBase)) {
            iVarA.a(new g((HttpEntityEnclosingRequestBase) httpRequestBase));
        }
        try {
            bVar = jVar2.b(iVarA);
            b.debug("Received error response: " + bVar.toString());
        } catch (Exception e) {
            if (statusCode == 413) {
                bVar = new com.amazonaws.b("Request entity too large");
                bVar.setServiceName(jVar.g());
                bVar.setStatusCode(413);
                bVar.setErrorType(com.amazonaws.c.Client);
                bVar.setErrorCode("Request entity too large");
            } else {
                if (statusCode != 503 || !"Service Unavailable".equalsIgnoreCase(httpResponse.getStatusLine().getReasonPhrase())) {
                    throw new com.amazonaws.a("Unable to unmarshall error response (" + e.getMessage() + ")", e);
                }
                bVar = new com.amazonaws.b("Service unavailable");
                bVar.setServiceName(jVar.g());
                bVar.setStatusCode(503);
                bVar.setErrorType(com.amazonaws.c.Service);
                bVar.setErrorCode("Service unavailable");
            }
        }
        bVar.setStatusCode(statusCode);
        bVar.setServiceName(jVar.g());
        bVar.fillInStackTrace();
        return bVar;
    }

    private i a(HttpRequestBase httpRequestBase, com.amazonaws.j<?> jVar, HttpResponse httpResponse) {
        i iVar = new i(jVar, httpRequestBase);
        if (httpResponse.getEntity() != null) {
            iVar.a(httpResponse.getEntity().getContent());
        }
        iVar.a(httpResponse.getStatusLine().getStatusCode());
        iVar.a(httpResponse.getStatusLine().getReasonPhrase());
        for (Header header : httpResponse.getAllHeaders()) {
            iVar.a(header.getName(), header.getValue());
        }
        return iVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> T a(com.amazonaws.j<?> jVar, j<com.amazonaws.f<T>> jVar2, HttpRequestBase httpRequestBase, HttpResponse httpResponse, d dVar) {
        com.amazonaws.f.d dVar2;
        i iVarA = a(httpRequestBase, jVar, httpResponse);
        if (jVar2.a() && (httpRequestBase instanceof HttpEntityEnclosingRequest)) {
            iVarA.a(new g((HttpEntityEnclosingRequest) httpRequestBase));
        }
        try {
            if (System.getProperty("com.amazonaws.sdk.enableRuntimeProfiling") != null) {
                com.amazonaws.f.d dVar3 = new com.amazonaws.f.d(iVarA.c());
                iVarA.a(dVar3);
                dVar2 = dVar3;
            } else {
                dVar2 = null;
            }
            com.amazonaws.f.a aVarC = dVar.c();
            aVarC.a(com.amazonaws.f.b.ResponseProcessingTime.name());
            com.amazonaws.f<T> fVarB = jVar2.b(iVarA);
            aVarC.b(com.amazonaws.f.b.ResponseProcessingTime.name());
            if (dVar2 != null) {
                aVarC.a(com.amazonaws.f.b.BytesProcessed.name(), dVar2.a());
            }
            if (fVarB == null) {
                throw new RuntimeException("Unable to unmarshall response metadata");
            }
            this.e.a(jVar.a(), fVarB.b());
            if (b.isDebugEnabled()) {
                b.debug("Received successful response: " + httpResponse.getStatusLine().getStatusCode() + ", AWS Request ID: " + fVarB.c());
            }
            aVarC.a(com.amazonaws.f.b.AWSRequestID.name(), fVarB.c());
            return fVarB.a();
        } catch (Exception e) {
            throw new com.amazonaws.a("Unable to unmarshall response (" + e.getMessage() + ")", e);
        }
    }

    private static String a(String str, String str2) {
        return str.contains(str2) ? str : str.trim() + " " + str2.trim();
    }

    private void a(int i, com.amazonaws.b bVar, com.amazonaws.d.a aVar) {
        long jNextInt;
        if (aVar != null) {
            jNextInt = aVar.a(i);
        } else {
            jNextInt = (long) ((a(bVar) ? f.nextInt(100) + 500 : 300L) * Math.pow(2.0d, i));
        }
        long jMin = Math.min(jNextInt, 20000L);
        if (f85a.isDebugEnabled()) {
            f85a.debug("Retriable error detected, will retry in " + jMin + "ms, attempt number: " + i);
        }
        try {
            Thread.sleep(jMin);
        } catch (InterruptedException e) {
            throw new com.amazonaws.a(e.getMessage(), e);
        }
    }

    private void a(com.amazonaws.j<?> jVar) {
        if (this.d.b() != null) {
            jVar.a("User-Agent", this.d.b());
        }
        if (jVar.a() == null || jVar.a().getRequestClientOptions() == null || jVar.a().getRequestClientOptions().a() == null) {
            return;
        }
        jVar.a("User-Agent", a(this.d.b(), jVar.a().getRequestClientOptions().a()));
    }

    private void a(com.amazonaws.j<?> jVar, Exception exc) {
        if (jVar.h() == null || !jVar.h().markSupported()) {
            return;
        }
        try {
            jVar.h().reset();
        } catch (IOException e) {
            throw new com.amazonaws.a("Encountered an exception and couldn't reset the stream to retry", exc);
        }
    }

    private boolean a(com.amazonaws.b bVar) {
        if (bVar == null) {
            return false;
        }
        return "Throttling".equals(bVar.getErrorCode()) || "ThrottlingException".equals(bVar.getErrorCode()) || "ProvisionedThroughputExceededException".equals(bVar.getErrorCode());
    }

    private boolean a(HttpResponse httpResponse) {
        return httpResponse.getStatusLine().getStatusCode() == 307 && httpResponse.getHeaders("Location") != null && httpResponse.getHeaders("Location").length > 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean a(HttpRequestBase httpRequestBase, Exception exc, int i) {
        HttpEntity entity;
        if (i >= this.d.i()) {
            return false;
        }
        if ((httpRequestBase instanceof HttpEntityEnclosingRequest) && (entity = ((HttpEntityEnclosingRequest) httpRequestBase).getEntity()) != null && !entity.isRepeatable()) {
            if (!f85a.isDebugEnabled()) {
                return false;
            }
            f85a.debug("Entity not repeatable");
            return false;
        }
        if (exc instanceof IOException) {
            if (f85a.isDebugEnabled()) {
                f85a.debug("Retrying on " + exc.getClass().getName() + ": " + exc.getMessage());
            }
            return true;
        }
        if (!(exc instanceof com.amazonaws.b)) {
            return false;
        }
        com.amazonaws.b bVar = (com.amazonaws.b) exc;
        if (bVar.getStatusCode() == 500 || bVar.getStatusCode() == 503) {
            return true;
        }
        return a(bVar);
    }

    /* JADX WARN: Code duplicated, block: B:104:0x0331  */
    /* JADX WARN: Code duplicated, block: B:111:0x014d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:117:0x02c7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:123:0x0243 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:125:0x01d4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:132:0x01ad A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:136:0x0252 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:22:0x00d1 A[Catch: IOException -> 0x0164, all -> 0x02d9, TryCatch #6 {IOException -> 0x0164, blocks: (B:20:0x00cc, B:22:0x00d1, B:24:0x00ee, B:26:0x00f4, B:28:0x00fa, B:40:0x0159, B:42:0x015f), top: B:115:0x00cc }] */
    /* JADX WARN: Code duplicated, block: B:24:0x00ee A[Catch: IOException -> 0x0164, all -> 0x02d9, TryCatch #6 {IOException -> 0x0164, blocks: (B:20:0x00cc, B:22:0x00d1, B:24:0x00ee, B:26:0x00f4, B:28:0x00fa, B:40:0x0159, B:42:0x015f), top: B:115:0x00cc }] */
    /* JADX WARN: Code duplicated, block: B:26:0x00f4 A[Catch: IOException -> 0x0164, all -> 0x02d9, TryCatch #6 {IOException -> 0x0164, blocks: (B:20:0x00cc, B:22:0x00d1, B:24:0x00ee, B:26:0x00f4, B:28:0x00fa, B:40:0x0159, B:42:0x015f), top: B:115:0x00cc }] */
    /* JADX WARN: Code duplicated, block: B:28:0x00fa A[Catch: IOException -> 0x0164, all -> 0x02d9, TRY_LEAVE, TryCatch #6 {IOException -> 0x0164, blocks: (B:20:0x00cc, B:22:0x00d1, B:24:0x00ee, B:26:0x00f4, B:28:0x00fa, B:40:0x0159, B:42:0x015f), top: B:115:0x00cc }] */
    /* JADX WARN: Code duplicated, block: B:33:0x0124 A[Catch: all -> 0x02d9, IOException -> 0x02e0, TRY_LEAVE, TryCatch #8 {all -> 0x02d9, blocks: (B:7:0x0061, B:9:0x0067, B:11:0x006d, B:12:0x008c, B:14:0x0094, B:15:0x00b0, B:16:0x00be, B:18:0x00c2, B:20:0x00cc, B:22:0x00d1, B:24:0x00ee, B:26:0x00f4, B:28:0x00fa, B:40:0x0159, B:42:0x015f, B:30:0x0102, B:31:0x0113, B:33:0x0124, B:56:0x01e0, B:58:0x01e8, B:65:0x0259, B:66:0x025d, B:67:0x0267, B:69:0x029a, B:72:0x02ab), top: B:109:0x0061 }] */
    /* JADX WARN: Code duplicated, block: B:40:0x0159 A[Catch: IOException -> 0x0164, all -> 0x02d9, TRY_ENTER, TryCatch #6 {IOException -> 0x0164, blocks: (B:20:0x00cc, B:22:0x00d1, B:24:0x00ee, B:26:0x00f4, B:28:0x00fa, B:40:0x0159, B:42:0x015f), top: B:115:0x00cc }] */
    /* JADX WARN: Code duplicated, block: B:42:0x015f A[Catch: IOException -> 0x0164, all -> 0x02d9, TRY_LEAVE, TryCatch #6 {IOException -> 0x0164, blocks: (B:20:0x00cc, B:22:0x00d1, B:24:0x00ee, B:26:0x00f4, B:28:0x00fa, B:40:0x0159, B:42:0x015f), top: B:115:0x00cc }] */
    /* JADX WARN: Code duplicated, block: B:56:0x01e0 A[Catch: all -> 0x02d9, IOException -> 0x02e0, TRY_ENTER, TryCatch #8 {all -> 0x02d9, blocks: (B:7:0x0061, B:9:0x0067, B:11:0x006d, B:12:0x008c, B:14:0x0094, B:15:0x00b0, B:16:0x00be, B:18:0x00c2, B:20:0x00cc, B:22:0x00d1, B:24:0x00ee, B:26:0x00f4, B:28:0x00fa, B:40:0x0159, B:42:0x015f, B:30:0x0102, B:31:0x0113, B:33:0x0124, B:56:0x01e0, B:58:0x01e8, B:65:0x0259, B:66:0x025d, B:67:0x0267, B:69:0x029a, B:72:0x02ab), top: B:109:0x0061 }] */
    /* JADX WARN: Code duplicated, block: B:58:0x01e8 A[Catch: all -> 0x02d9, IOException -> 0x02e0, TRY_LEAVE, TryCatch #8 {all -> 0x02d9, blocks: (B:7:0x0061, B:9:0x0067, B:11:0x006d, B:12:0x008c, B:14:0x0094, B:15:0x00b0, B:16:0x00be, B:18:0x00c2, B:20:0x00cc, B:22:0x00d1, B:24:0x00ee, B:26:0x00f4, B:28:0x00fa, B:40:0x0159, B:42:0x015f, B:30:0x0102, B:31:0x0113, B:33:0x0124, B:56:0x01e0, B:58:0x01e8, B:65:0x0259, B:66:0x025d, B:67:0x0267, B:69:0x029a, B:72:0x02ab), top: B:109:0x0061 }] */
    /* JADX WARN: Code duplicated, block: B:65:0x0259 A[Catch: all -> 0x02d9, IOException -> 0x02e0, TRY_ENTER, TRY_LEAVE, TryCatch #8 {all -> 0x02d9, blocks: (B:7:0x0061, B:9:0x0067, B:11:0x006d, B:12:0x008c, B:14:0x0094, B:15:0x00b0, B:16:0x00be, B:18:0x00c2, B:20:0x00cc, B:22:0x00d1, B:24:0x00ee, B:26:0x00f4, B:28:0x00fa, B:40:0x0159, B:42:0x015f, B:30:0x0102, B:31:0x0113, B:33:0x0124, B:56:0x01e0, B:58:0x01e8, B:65:0x0259, B:66:0x025d, B:67:0x0267, B:69:0x029a, B:72:0x02ab), top: B:109:0x0061 }] */
    /* JADX WARN: Code duplicated, block: B:69:0x029a A[Catch: IOException -> 0x029b, all -> 0x02d9, TryCatch #8 {all -> 0x02d9, blocks: (B:7:0x0061, B:9:0x0067, B:11:0x006d, B:12:0x008c, B:14:0x0094, B:15:0x00b0, B:16:0x00be, B:18:0x00c2, B:20:0x00cc, B:22:0x00d1, B:24:0x00ee, B:26:0x00f4, B:28:0x00fa, B:40:0x0159, B:42:0x015f, B:30:0x0102, B:31:0x0113, B:33:0x0124, B:56:0x01e0, B:58:0x01e8, B:65:0x0259, B:66:0x025d, B:67:0x0267, B:69:0x029a, B:72:0x02ab), top: B:109:0x0061 }] */
    /* JADX WARN: Code duplicated, block: B:72:0x02ab A[Catch: IOException -> 0x029b, all -> 0x02d9, TRY_LEAVE, TryCatch #8 {all -> 0x02d9, blocks: (B:7:0x0061, B:9:0x0067, B:11:0x006d, B:12:0x008c, B:14:0x0094, B:15:0x00b0, B:16:0x00be, B:18:0x00c2, B:20:0x00cc, B:22:0x00d1, B:24:0x00ee, B:26:0x00f4, B:28:0x00fa, B:40:0x0159, B:42:0x015f, B:30:0x0102, B:31:0x0113, B:33:0x0124, B:56:0x01e0, B:58:0x01e8, B:65:0x0259, B:66:0x025d, B:67:0x0267, B:69:0x029a, B:72:0x02ab), top: B:109:0x0061 }] */
    /* JADX WARN: Code duplicated, block: B:76:0x02bc A[Catch: all -> 0x01ca, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x01ca, blocks: (B:46:0x0172, B:48:0x01ad, B:49:0x01c9, B:76:0x02bc), top: B:107:0x0172 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r19v0, types: [com.amazonaws.c.a] */
    /* JADX WARN: Type inference failed for: r3v29, types: [org.apache.http.client.HttpClient] */
    /* JADX WARN: Type inference failed for: r6v9, types: [org.apache.http.client.methods.HttpRequestBase, org.apache.http.client.methods.HttpUriRequest] */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [org.apache.http.client.methods.HttpRequestBase] */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
    private <T> T b(com.amazonaws.j<?> jVar, j<com.amazonaws.f<T>> jVar2, j<com.amazonaws.b> jVar3, d dVar) throws Throwable {
        ?? r9;
        IOException iOException;
        HttpResponse httpResponse;
        boolean zA;
        com.amazonaws.b bVarA;
        boolean z;
        URI uri;
        int i;
        boolean zA2;
        InputStream content;
        boolean z2 = false;
        com.amazonaws.f.a aVarC = dVar.c();
        aVarC.a(com.amazonaws.f.b.ServiceName.name(), jVar.g());
        aVarC.a(com.amazonaws.f.b.ServiceEndpoint.name(), jVar.f());
        a(jVar);
        int i2 = 0;
        URI uri2 = null;
        HttpEntity httpEntity = null;
        com.amazonaws.b bVar = null;
        HashMap map = new HashMap();
        map.putAll(jVar.d());
        HashMap map2 = new HashMap();
        map2.putAll(jVar.b());
        while (true) {
            uri2 = uri2;
            int i3 = i2;
            boolean z3 = z2;
            HttpEntity httpEntity2 = httpEntity;
            com.amazonaws.b bVar2 = bVar;
            aVarC.a(com.amazonaws.f.b.AttemptCount.name(), i3 + 1);
            if (i3 > 0) {
                jVar.b(map);
                jVar.a(map2);
            }
            HttpResponse httpResponseExecute = null;
            try {
                try {
                    if (dVar.d() != null && dVar.e() != null) {
                        aVarC.a(com.amazonaws.f.b.RequestSigningTime.name());
                        dVar.d().sign(jVar, dVar.e());
                        aVarC.b(com.amazonaws.f.b.RequestSigningTime.name());
                    }
                    if (b.isDebugEnabled()) {
                        b.debug("Sending Request: " + jVar.toString());
                    }
                    ?? A = g.a(jVar, this.d, httpEntity2, dVar);
                    try {
                        HttpEntity entity = A instanceof HttpEntityEnclosingRequest ? ((HttpEntityEnclosingRequest) A).getEntity() : httpEntity2;
                        if (uri2 != null) {
                            try {
                                A.setURI(uri2);
                                if (i3 > 0) {
                                    aVarC.a(com.amazonaws.f.b.RetryPauseTime.name());
                                    a(i3, bVar2, dVar.f());
                                    aVarC.b(com.amazonaws.f.b.RetryPauseTime.name());
                                }
                                if (entity != null) {
                                    content = entity.getContent();
                                    if (i3 > 0) {
                                        if (content.markSupported()) {
                                            content.reset();
                                            content.mark(-1);
                                        }
                                    } else if (content.markSupported()) {
                                        content.mark(-1);
                                    }
                                }
                                try {
                                    aVarC.a(com.amazonaws.f.b.HttpRequestTime.name());
                                    httpResponseExecute = this.c.execute(A);
                                    try {
                                        aVarC.b(com.amazonaws.f.b.HttpRequestTime.name());
                                        if (b(httpResponseExecute)) {
                                            aVarC.a(com.amazonaws.f.b.StatusCode.name(), Integer.valueOf(httpResponseExecute.getStatusLine().getStatusCode()));
                                            zA2 = jVar2.a();
                                            try {
                                                T t = (T) a(jVar, jVar2, A, httpResponseExecute, dVar);
                                                int i4 = i3 + 1;
                                                if (!zA2) {
                                                    try {
                                                        httpResponseExecute.getEntity().getContent().close();
                                                    } catch (Throwable th) {
                                                    }
                                                }
                                                return t;
                                            } catch (IOException e) {
                                                httpResponse = httpResponseExecute;
                                                httpEntity = entity;
                                                r9 = A;
                                                z2 = zA2;
                                                iOException = e;
                                                bVar = null;
                                            } catch (Throwable th2) {
                                                th = th2;
                                                z3 = zA2;
                                                int i5 = i3 + 1;
                                                if (!z3) {
                                                    try {
                                                        httpResponseExecute.getEntity().getContent().close();
                                                    } catch (Throwable th3) {
                                                    }
                                                }
                                                throw th;
                                            }
                                        } else {
                                            if (a(httpResponseExecute)) {
                                                String value = httpResponseExecute.getHeaders("location")[0].getValue();
                                                f85a.debug("Redirecting to: " + value);
                                                URI uriCreate = URI.create(value);
                                                A.setURI(uriCreate);
                                                aVarC.a(com.amazonaws.f.b.StatusCode.name(), Integer.valueOf(httpResponseExecute.getStatusLine().getStatusCode()));
                                                aVarC.a(com.amazonaws.f.b.RedirectLocation.name(), value);
                                                aVarC.a(com.amazonaws.f.b.AWSRequestID.name(), (Object) null);
                                                bVar = null;
                                                uri = uriCreate;
                                                z = z3;
                                            } else {
                                                zA = jVar3.a();
                                                try {
                                                    bVarA = a(jVar, jVar3, A, httpResponseExecute);
                                                    try {
                                                        aVarC.a(com.amazonaws.f.b.AWSRequestID.name(), bVarA.getRequestId());
                                                        aVarC.a(com.amazonaws.f.b.AWSErrorCode.name(), bVarA.getErrorCode());
                                                        aVarC.a(com.amazonaws.f.b.StatusCode.name(), Integer.valueOf(bVarA.getStatusCode()));
                                                        if (!a(A, bVarA, i3)) {
                                                            throw bVarA;
                                                        }
                                                        a(jVar, bVarA);
                                                        bVar = bVarA;
                                                        z = zA;
                                                        uri = uri2;
                                                    } catch (IOException e2) {
                                                        z2 = zA;
                                                        httpResponse = httpResponseExecute;
                                                        httpEntity = entity;
                                                        r9 = A;
                                                        iOException = e2;
                                                        bVar = bVarA;
                                                        f85a.info("Unable to execute HTTP request: " + iOException.getMessage(), iOException);
                                                        aVarC.a(com.amazonaws.f.b.Exception.name(), iOException.toString());
                                                        aVarC.a(com.amazonaws.f.b.AWSRequestID.name(), (Object) null);
                                                        if (!a(r9, iOException, i3)) {
                                                            throw new com.amazonaws.a("Unable to execute HTTP request: " + iOException.getMessage(), iOException);
                                                        }
                                                        a(jVar, iOException);
                                                        i2 = i3 + 1;
                                                        if (!z2) {
                                                            httpResponse.getEntity().getContent().close();
                                                        }
                                                    }
                                                } catch (IOException e3) {
                                                    httpEntity = entity;
                                                    r9 = A;
                                                    iOException = e3;
                                                    bVar = null;
                                                    z2 = zA;
                                                    httpResponse = httpResponseExecute;
                                                }
                                            }
                                            i = i3 + 1;
                                            if (z) {
                                                z2 = z;
                                                i2 = i;
                                                uri2 = uri;
                                                httpEntity = entity;
                                            } else {
                                                try {
                                                    httpResponseExecute.getEntity().getContent().close();
                                                    z2 = z;
                                                    i2 = i;
                                                    uri2 = uri;
                                                    httpEntity = entity;
                                                } catch (Throwable th4) {
                                                    z2 = z;
                                                    i2 = i;
                                                    uri2 = uri;
                                                    httpEntity = entity;
                                                }
                                            }
                                        }
                                    } catch (IOException e4) {
                                        httpEntity = entity;
                                        r9 = A;
                                        iOException = e4;
                                        bVar = null;
                                        z2 = z3;
                                        httpResponse = httpResponseExecute;
                                    }
                                } catch (IOException e5) {
                                    httpEntity = entity;
                                    r9 = A;
                                    iOException = e5;
                                    bVar = null;
                                    z2 = z3;
                                    httpResponse = null;
                                }
                            } catch (IOException e6) {
                                z2 = z3;
                                httpResponse = null;
                                httpEntity = entity;
                                r9 = A;
                                iOException = e6;
                                bVar = bVar2;
                            }
                            try {
                                f85a.info("Unable to execute HTTP request: " + iOException.getMessage(), iOException);
                                aVarC.a(com.amazonaws.f.b.Exception.name(), iOException.toString());
                                aVarC.a(com.amazonaws.f.b.AWSRequestID.name(), (Object) null);
                                if (!a(r9, iOException, i3)) {
                                    throw new com.amazonaws.a("Unable to execute HTTP request: " + iOException.getMessage(), iOException);
                                }
                                a(jVar, iOException);
                                i2 = i3 + 1;
                                if (!z2) {
                                    httpResponse.getEntity().getContent().close();
                                }
                            } catch (Throwable th5) {
                                th = th5;
                                HttpResponse httpResponse2 = httpResponse;
                                z3 = z2;
                                httpResponseExecute = httpResponse2;
                                int i6 = i3 + 1;
                                if (!z3) {
                                    httpResponseExecute.getEntity().getContent().close();
                                }
                                throw th;
                            }
                        } else {
                            if (i3 > 0) {
                                aVarC.a(com.amazonaws.f.b.RetryPauseTime.name());
                                a(i3, bVar2, dVar.f());
                                aVarC.b(com.amazonaws.f.b.RetryPauseTime.name());
                            }
                            if (entity != null) {
                                content = entity.getContent();
                                if (i3 > 0) {
                                    if (content.markSupported()) {
                                        content.reset();
                                        content.mark(-1);
                                    }
                                } else if (content.markSupported()) {
                                    content.mark(-1);
                                }
                            }
                            aVarC.a(com.amazonaws.f.b.HttpRequestTime.name());
                            httpResponseExecute = this.c.execute(A);
                            aVarC.b(com.amazonaws.f.b.HttpRequestTime.name());
                            if (b(httpResponseExecute)) {
                                aVarC.a(com.amazonaws.f.b.StatusCode.name(), Integer.valueOf(httpResponseExecute.getStatusLine().getStatusCode()));
                                zA2 = jVar2.a();
                                T t2 = (T) a(jVar, jVar2, A, httpResponseExecute, dVar);
                                int i7 = i3 + 1;
                                if (!zA2) {
                                    httpResponseExecute.getEntity().getContent().close();
                                }
                                return t2;
                            }
                            if (a(httpResponseExecute)) {
                                String value2 = httpResponseExecute.getHeaders("location")[0].getValue();
                                f85a.debug("Redirecting to: " + value2);
                                URI uriCreate2 = URI.create(value2);
                                A.setURI(uriCreate2);
                                aVarC.a(com.amazonaws.f.b.StatusCode.name(), Integer.valueOf(httpResponseExecute.getStatusLine().getStatusCode()));
                                aVarC.a(com.amazonaws.f.b.RedirectLocation.name(), value2);
                                aVarC.a(com.amazonaws.f.b.AWSRequestID.name(), (Object) null);
                                bVar = null;
                                uri = uriCreate2;
                                z = z3;
                            } else {
                                zA = jVar3.a();
                                bVarA = a(jVar, jVar3, A, httpResponseExecute);
                                aVarC.a(com.amazonaws.f.b.AWSRequestID.name(), bVarA.getRequestId());
                                aVarC.a(com.amazonaws.f.b.AWSErrorCode.name(), bVarA.getErrorCode());
                                aVarC.a(com.amazonaws.f.b.StatusCode.name(), Integer.valueOf(bVarA.getStatusCode()));
                                if (!a(A, bVarA, i3)) {
                                    throw bVarA;
                                }
                                a(jVar, bVarA);
                                bVar = bVarA;
                                z = zA;
                                uri = uri2;
                            }
                            i = i3 + 1;
                            if (z) {
                                httpResponseExecute.getEntity().getContent().close();
                                z2 = z;
                                i2 = i;
                                uri2 = uri;
                                httpEntity = entity;
                            } else {
                                z2 = z;
                                i2 = i;
                                uri2 = uri;
                                httpEntity = entity;
                            }
                            f85a.info("Unable to execute HTTP request: " + iOException.getMessage(), iOException);
                            aVarC.a(com.amazonaws.f.b.Exception.name(), iOException.toString());
                            aVarC.a(com.amazonaws.f.b.AWSRequestID.name(), (Object) null);
                            if (!a(r9, iOException, i3)) {
                                throw new com.amazonaws.a("Unable to execute HTTP request: " + iOException.getMessage(), iOException);
                            }
                            a(jVar, iOException);
                            i2 = i3 + 1;
                            if (!z2) {
                                httpResponse.getEntity().getContent().close();
                            }
                        }
                    } catch (IOException e7) {
                        r9 = A;
                        iOException = e7;
                        bVar = bVar2;
                        httpEntity = httpEntity2;
                        z2 = z3;
                        httpResponse = null;
                    }
                } catch (IOException e8) {
                    r9 = 0;
                    iOException = e8;
                    bVar = bVar2;
                    httpEntity = httpEntity2;
                    z2 = z3;
                    httpResponse = null;
                }
            } catch (Throwable th6) {
                th = th6;
            }
        }
    }

    private boolean b(HttpResponse httpResponse) {
        return httpResponse.getStatusLine().getStatusCode() / 100 == 2;
    }

    public <T> T a(com.amazonaws.j<?> jVar, j<com.amazonaws.f<T>> jVar2, j<com.amazonaws.b> jVar3, d dVar) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (dVar == null) {
            throw new com.amazonaws.a("Internal SDK Error: No execution context parameter specified.");
        }
        List<com.amazonaws.b.c> listB = dVar.b();
        List<com.amazonaws.b.c> arrayList = listB == null ? new ArrayList() : listB;
        Iterator<com.amazonaws.b.c> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().beforeRequest(jVar);
        }
        try {
            com.amazonaws.f.l lVar = new com.amazonaws.f.l(jCurrentTimeMillis);
            T t = (T) b(jVar, jVar2, jVar3, dVar);
            lVar.a(System.currentTimeMillis());
            Iterator<com.amazonaws.b.c> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                try {
                    it2.next().afterResponse(jVar, t, lVar);
                } catch (ClassCastException e) {
                }
            }
            return t;
        } catch (com.amazonaws.a e2) {
            Iterator<com.amazonaws.b.c> it3 = arrayList.iterator();
            while (it3.hasNext()) {
                it3.next().afterError(jVar, e2);
            }
            throw e2;
        }
    }

    public void a() {
        k.a(this.c.getConnectionManager());
        this.c.getConnectionManager().shutdown();
    }

    protected void finalize() throws Throwable {
        a();
        super.finalize();
    }
}

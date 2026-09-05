package com.amazonaws.c;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.StringEntity;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class h {
    h() {
    }

    private String a(com.amazonaws.g gVar, String str) {
        return gVar.b().contains(str) ? gVar.b() : gVar.b() + " " + str;
    }

    private HttpEntity a(String str) {
        try {
            return new StringEntity(str);
        } catch (UnsupportedEncodingException e) {
            throw new com.amazonaws.a("Unable to create HTTP entity: " + e.getMessage(), e);
        }
    }

    private HttpEntity a(HttpEntity httpEntity) {
        try {
            return new BufferedHttpEntity(httpEntity);
        } catch (IOException e) {
            throw new com.amazonaws.a("Unable to create HTTP entity: " + e.getMessage(), e);
        }
    }

    private void a(HttpRequestBase httpRequestBase, com.amazonaws.j<?> jVar, d dVar, com.amazonaws.g gVar) {
        URI uriF = jVar.f();
        String host = uriF.getHost();
        if (com.amazonaws.f.f.a(uriF)) {
            host = host + ":" + uriF.getPort();
        }
        httpRequestBase.addHeader("Host", host);
        for (Map.Entry<String, String> entry : jVar.b().entrySet()) {
            if (!entry.getKey().equalsIgnoreCase("Content-Length") && !entry.getKey().equalsIgnoreCase("Host")) {
                httpRequestBase.addHeader(entry.getKey(), entry.getValue());
            }
        }
        if (httpRequestBase.getHeaders("Content-Type") == null || httpRequestBase.getHeaders("Content-Type").length == 0) {
            httpRequestBase.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=" + XMLStreamWriterImpl.UTF_8.toLowerCase());
        }
        if (dVar == null || dVar.a() == null) {
            return;
        }
        httpRequestBase.addHeader("User-Agent", a(gVar, dVar.a()));
    }

    HttpRequestBase a(com.amazonaws.j<?> jVar, com.amazonaws.g gVar, HttpEntity httpEntity, d dVar) {
        HttpRequestBase httpHead;
        String string = jVar.f().toString();
        if (jVar.c() != null && jVar.c().length() > 0) {
            if (!jVar.c().startsWith("/")) {
                string = string + "/";
            }
            string = string + jVar.c();
        } else if (!string.endsWith("/")) {
            string = string + "/";
        }
        String strB = com.amazonaws.f.f.b(jVar);
        boolean z = !(jVar.e() == f.POST) || (jVar.h() != null);
        if (strB != null && z) {
            string = string + "?" + strB;
        }
        if (jVar.e() == f.POST) {
            HttpPost httpPost = new HttpPost(string);
            if (jVar.h() != null || strB == null) {
                httpPost.setEntity(new n(jVar));
            } else {
                httpPost.setEntity(a(strB));
            }
            httpHead = httpPost;
        } else if (jVar.e() == f.PUT) {
            HttpPut httpPut = new HttpPut(string);
            httpPut.getParams().setParameter("http.protocol.expect-continue", true);
            if (httpEntity != null) {
                httpPut.setEntity(httpEntity);
            } else if (jVar.h() != null) {
                HttpEntity nVar = new n(jVar);
                if (jVar.b().get("Content-Length") == null) {
                    nVar = a(nVar);
                }
                httpPut.setEntity(nVar);
            }
            httpHead = httpPut;
        } else if (jVar.e() == f.GET) {
            httpHead = new HttpGet(string);
        } else if (jVar.e() == f.DELETE) {
            httpHead = new HttpDelete(string);
        } else {
            if (jVar.e() != f.HEAD) {
                throw new com.amazonaws.a("Unknown HTTP method name: " + jVar.e());
            }
            httpHead = new HttpHead(string);
        }
        a(httpHead, jVar, dVar, gVar);
        return httpHead;
    }
}

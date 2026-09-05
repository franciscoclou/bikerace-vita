package com.heyzap.a;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

/* JADX INFO: compiled from: RequestParams.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class g {
    private static String c = XMLStreamWriterImpl.UTF_8;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected ConcurrentHashMap<String, String> f742a;
    protected ConcurrentHashMap<String, h> b;

    public g() {
        d();
    }

    public g(Map<String, String> map) {
        d();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                a(entry.getKey(), entry.getValue());
            }
        }
    }

    public void a(String str, String str2) {
        if (str != null && str2 != null) {
            this.f742a.put(str, str2);
        }
    }

    public void a(String str, int i) {
        if (str != null) {
            this.f742a.put(str, String.valueOf(i));
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : this.f742a.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
        }
        for (Map.Entry<String, h> entry2 : this.b.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(entry2.getKey());
            sb.append("=");
            sb.append("FILE");
        }
        return sb.toString();
    }

    public HttpEntity a() {
        if (!this.b.isEmpty()) {
            k kVar = new k();
            for (Map.Entry<String, String> entry : this.f742a.entrySet()) {
                kVar.a(entry.getKey(), entry.getValue());
            }
            int size = this.b.entrySet().size() - 1;
            int i = 0;
            for (Map.Entry<String, h> entry2 : this.b.entrySet()) {
                h value = entry2.getValue();
                if (value.f743a != null) {
                    boolean z = i == size;
                    if (value.c != null) {
                        kVar.a(entry2.getKey(), value.a(), value.f743a, value.c, z);
                    } else {
                        kVar.a(entry2.getKey(), value.a(), value.f743a, z);
                    }
                }
                i++;
            }
            return kVar;
        }
        try {
            return new UrlEncodedFormEntity(b(), c);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void d() {
        this.f742a = new ConcurrentHashMap<>();
        this.b = new ConcurrentHashMap<>();
    }

    protected List<BasicNameValuePair> b() {
        LinkedList linkedList = new LinkedList();
        for (Map.Entry<String, String> entry : this.f742a.entrySet()) {
            linkedList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return linkedList;
    }

    protected String c() {
        return URLEncodedUtils.format(b(), c);
    }
}

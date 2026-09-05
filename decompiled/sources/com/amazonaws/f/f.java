package com.amazonaws.f;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class f {
    public static String a(String str, boolean z) {
        if (str == null) {
            return "";
        }
        try {
            String strReplace = URLEncoder.encode(str, XMLStreamWriterImpl.UTF_8).replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
            return z ? strReplace.replace("%2F", "/") : strReplace;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean a(com.amazonaws.j<?> jVar) {
        return com.amazonaws.c.f.POST.equals(jVar.e()) && (jVar.h() == null);
    }

    public static boolean a(URI uri) {
        String lowerCase = uri.getScheme().toLowerCase();
        int port = uri.getPort();
        if (port <= 0) {
            return false;
        }
        if (lowerCase.equals("http") && port == 80) {
            return false;
        }
        return (lowerCase.equals("https") && port == 443) ? false : true;
    }

    public static String b(com.amazonaws.j<?> jVar) {
        ArrayList arrayList;
        if (jVar.d().size() > 0) {
            ArrayList arrayList2 = new ArrayList(jVar.d().size());
            for (Map.Entry<String, String> entry : jVar.d().entrySet()) {
                arrayList2.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        if (arrayList != null) {
            return URLEncodedUtils.format(arrayList, XMLStreamWriterImpl.UTF_8);
        }
        return null;
    }
}

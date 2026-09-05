package com.google.a.a.a;

import android.text.TextUtils;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: HitBuilder.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
class ag {
    static Map<String, String> a(Map<String, String> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().startsWith("&") && entry.getValue() != null) {
                String strSubstring = entry.getKey().substring(1);
                if (!TextUtils.isEmpty(strSubstring)) {
                    map2.put(strSubstring, entry.getValue());
                }
            }
        }
        return map2;
    }

    static String a(af afVar, long j) {
        StringBuilder sb = new StringBuilder();
        sb.append(afVar.a());
        if (afVar.c() > 0) {
            long jC = j - afVar.c();
            if (jC >= 0) {
                sb.append("&qt").append("=").append(jC);
            }
        }
        sb.append("&z").append("=").append(afVar.b());
        return sb.toString();
    }

    static String a(String str) {
        try {
            return URLEncoder.encode(str, XMLStreamWriterImpl.UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError("URL encoding failed for: " + str);
        }
    }
}

package org.c.g;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: OAuthEncoder.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f1631a = XMLStreamWriterImpl.UTF_8;
    private static final Map<String, String> b;

    static {
        HashMap map = new HashMap();
        map.put("*", "%2A");
        map.put("+", "%20");
        map.put("%7E", "~");
        b = Collections.unmodifiableMap(map);
    }

    public static String a(String str) {
        c.a((Object) str, "Cannot encode null object");
        try {
            String strEncode = URLEncoder.encode(str, f1631a);
            Iterator<Map.Entry<String, String>> it = b.entrySet().iterator();
            while (true) {
                String str2 = strEncode;
                if (it.hasNext()) {
                    Map.Entry<String, String> next = it.next();
                    strEncode = a(str2, next.getKey(), next.getValue());
                } else {
                    return str2;
                }
            }
        } catch (UnsupportedEncodingException e) {
            throw new org.c.b.b("Charset not found while encoding string: " + f1631a, e);
        }
    }

    private static String a(String str, String str2, String str3) {
        return str.replaceAll(Pattern.quote(str2), str3);
    }

    public static String b(String str) {
        c.a((Object) str, "Cannot decode null object");
        try {
            return URLDecoder.decode(str, f1631a);
        } catch (UnsupportedEncodingException e) {
            throw new org.c.b.b("Charset not found while decoding string: " + f1631a, e);
        }
    }
}

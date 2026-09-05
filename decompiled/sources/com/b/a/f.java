package com.b.a;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.b.a.a.cm;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class f extends RuntimeException {
    f(String str, String str2) {
        super(a(str, str2));
    }

    private static String a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\nThis app relies on Crashlytics. Configure your build environment here: \n");
            sb.append(String.format("https://crashlytics.com/register/%s/android/%s", URLEncoder.encode(str, XMLStreamWriterImpl.UTF_8), URLEncoder.encode(str2, XMLStreamWriterImpl.UTF_8)) + "\n");
        } catch (UnsupportedEncodingException e) {
            cm.a().b().a("Crashlytics", "Could not find UTF-8 encoding.", e);
        }
        return sb.toString();
    }
}

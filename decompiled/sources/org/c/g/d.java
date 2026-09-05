package org.c.g;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.facebook.internal.NativeProtocol;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* JADX INFO: compiled from: StreamUtils.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class d {
    public static String a(InputStream inputStream) {
        int i;
        c.a(inputStream, "Cannot get String from a null object");
        try {
            char[] cArr = new char[NativeProtocol.MESSAGE_GET_ACCESS_TOKEN_REQUEST];
            StringBuilder sb = new StringBuilder();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, XMLStreamWriterImpl.UTF_8);
            do {
                i = inputStreamReader.read(cArr, 0, cArr.length);
                if (i > 0) {
                    sb.append(cArr, 0, i);
                }
            } while (i >= 0);
            inputStreamReader.close();
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException("Error while reading response body", e);
        }
    }
}

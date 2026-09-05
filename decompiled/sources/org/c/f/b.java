package org.c.f;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.io.UnsupportedEncodingException;

/* JADX INFO: compiled from: CommonsEncoder.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b extends a {
    @Override // org.c.f.a
    public String a(byte[] bArr) {
        try {
            return new String(org.apache.commons.a.a.a.a(bArr), XMLStreamWriterImpl.UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new org.c.b.d("Can't perform base64 encoding", e);
        }
    }

    @Override // org.c.f.a
    public String c() {
        return "CommonsCodec";
    }

    public static boolean d() {
        try {
            Class.forName("org.apache.commons.a.a.a");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}

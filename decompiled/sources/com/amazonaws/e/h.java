package com.amazonaws.e;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class h implements t<ByteBuffer, c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static h f104a;

    public static h a() {
        if (f104a == null) {
            f104a = new h();
        }
        return f104a;
    }

    @Override // com.amazonaws.e.t
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public ByteBuffer unmarshall(c cVar) {
        String strB = cVar.b();
        if (strB == null) {
            return null;
        }
        try {
            return ByteBuffer.wrap(org.apache.commons.a.a.a.b(strB.getBytes(XMLStreamWriterImpl.UTF_8)));
        } catch (UnsupportedEncodingException e) {
            throw new com.amazonaws.a("Unable to unmarshall XML data into a ByteBuffer", e);
        }
    }
}

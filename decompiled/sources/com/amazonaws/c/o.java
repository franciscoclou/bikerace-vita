package com.amazonaws.c;

import com.amazonaws.e.r;
import com.amazonaws.e.t;
import com.amazonaws.e.v;
import com.amazonaws.javax.xml.stream.XMLEventReader;
import com.amazonaws.javax.xml.stream.XMLInputFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class o<T> implements j<com.amazonaws.f<T>> {
    private static final Log b = LogFactory.getLog("com.amazonaws.request");
    private static XMLInputFactory c = XMLInputFactory.newInstance();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private t<T, r> f95a;

    public o(t<T, r> tVar) {
        this.f95a = tVar;
        if (this.f95a == null) {
            this.f95a = new v();
        }
    }

    @Override // com.amazonaws.c.j
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public com.amazonaws.f<T> b(i iVar) {
        b.trace("Parsing service response XML");
        InputStream inputStreamC = iVar.c();
        if (inputStreamC == null) {
            inputStreamC = new ByteArrayInputStream("<eof/>".getBytes());
        }
        XMLEventReader xMLEventReaderCreateXMLEventReader = c.createXMLEventReader(inputStreamC);
        try {
            com.amazonaws.f<T> fVar = new com.amazonaws.f<>();
            r rVar = new r(xMLEventReaderCreateXMLEventReader, iVar.b());
            rVar.a("ResponseMetadata/RequestId", 2, "AWS_REQUEST_ID");
            rVar.a("requestId", 2, "AWS_REQUEST_ID");
            a(rVar);
            fVar.a(this.f95a.unmarshall(rVar));
            fVar.a(new com.amazonaws.l(rVar.e()));
            b.trace("Done parsing service response");
            return fVar;
        } finally {
            try {
                xMLEventReaderCreateXMLEventReader.close();
            } catch (Exception e) {
            }
        }
    }

    protected void a(r rVar) {
    }

    @Override // com.amazonaws.c.j
    public boolean a() {
        return false;
    }
}

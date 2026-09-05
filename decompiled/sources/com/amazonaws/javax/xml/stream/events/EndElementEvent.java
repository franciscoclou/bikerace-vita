package com.amazonaws.javax.xml.stream.events;

import com.amazonaws.javax.xml.a.b;
import com.amazonaws.javax.xml.stream.util.ReadOnlyIterator;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class EndElementEvent extends DummyEvent implements EndElement {
    List fNamespaces;
    b fQName;

    public EndElementEvent() {
        this.fNamespaces = null;
        init();
    }

    protected void init() {
        setEventType(2);
        this.fNamespaces = new ArrayList();
    }

    public EndElementEvent(String str, String str2, String str3) {
        this(new b(str2, str3, str));
    }

    public EndElementEvent(b bVar) {
        this.fNamespaces = null;
        this.fQName = bVar;
        init();
    }

    @Override // com.amazonaws.javax.xml.stream.events.EndElement
    public b getName() {
        return this.fQName;
    }

    public void setName(b bVar) {
        this.fQName = bVar;
    }

    @Override // com.amazonaws.javax.xml.stream.events.DummyEvent
    protected void writeAsEncodedUnicodeEx(Writer writer) throws IOException {
        writer.write(XMLStreamWriterImpl.OPEN_END_TAG);
        String strC = this.fQName.c();
        if (strC != null && strC.length() > 0) {
            writer.write(strC);
            writer.write(58);
        }
        writer.write(this.fQName.b());
        writer.write(62);
    }

    @Override // com.amazonaws.javax.xml.stream.events.EndElement
    public Iterator getNamespaces() {
        if (this.fNamespaces != null) {
            this.fNamespaces.iterator();
        }
        return new ReadOnlyIterator();
    }

    void addNamespace(Namespace namespace) {
        if (namespace != null) {
            this.fNamespaces.add(namespace);
        }
    }

    public String toString() {
        return new StringBuffer().append(new StringBuffer().append(XMLStreamWriterImpl.OPEN_END_TAG).append(nameAsString()).toString()).append(">").toString();
    }

    public String nameAsString() {
        if ("".equals(this.fQName.a())) {
            return this.fQName.b();
        }
        if (this.fQName.c() != null) {
            return new StringBuffer().append("['").append(this.fQName.a()).append("']:").append(this.fQName.c()).append(":").append(this.fQName.b()).toString();
        }
        return new StringBuffer().append("['").append(this.fQName.a()).append("']:").append(this.fQName.b()).toString();
    }
}

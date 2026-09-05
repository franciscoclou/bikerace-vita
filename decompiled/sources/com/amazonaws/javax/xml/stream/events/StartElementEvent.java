package com.amazonaws.javax.xml.stream.events;

import com.amazonaws.javax.xml.a.a;
import com.amazonaws.javax.xml.a.b;
import com.amazonaws.javax.xml.stream.util.ReadOnlyIterator;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class StartElementEvent extends DummyEvent implements StartElement {
    private Map fAttributes;
    private a fNamespaceContext;
    private List fNamespaces;
    private b fQName;

    public StartElementEvent(String str, String str2, String str3) {
        this(new b(str2, str3, str));
    }

    public StartElementEvent(b bVar) {
        this.fNamespaceContext = null;
        this.fQName = bVar;
        init();
    }

    public StartElementEvent(StartElement startElement) {
        this(startElement.getName());
        addAttributes(startElement.getAttributes());
        addNamespaceAttributes(startElement.getNamespaces());
    }

    protected void init() {
        setEventType(1);
        this.fAttributes = new HashMap();
        this.fNamespaces = new ArrayList();
    }

    @Override // com.amazonaws.javax.xml.stream.events.StartElement
    public b getName() {
        return this.fQName;
    }

    public void setName(b bVar) {
        this.fQName = bVar;
    }

    @Override // com.amazonaws.javax.xml.stream.events.StartElement
    public Iterator getAttributes() {
        return this.fAttributes != null ? new ReadOnlyIterator(this.fAttributes.values().iterator()) : new ReadOnlyIterator();
    }

    @Override // com.amazonaws.javax.xml.stream.events.StartElement
    public Iterator getNamespaces() {
        return this.fNamespaces != null ? new ReadOnlyIterator(this.fNamespaces.iterator()) : new ReadOnlyIterator();
    }

    @Override // com.amazonaws.javax.xml.stream.events.StartElement
    public Attribute getAttributeByName(b bVar) {
        if (bVar == null) {
            return null;
        }
        return (Attribute) this.fAttributes.get(bVar);
    }

    public String getNamespace() {
        return this.fQName.a();
    }

    @Override // com.amazonaws.javax.xml.stream.events.StartElement
    public String getNamespaceURI(String str) {
        if (getNamespace() != null && this.fQName.c().equals(str)) {
            return getNamespace();
        }
        if (this.fNamespaceContext != null) {
            return this.fNamespaceContext.getNamespaceURI(str);
        }
        return null;
    }

    public String toString() {
        String string;
        String string2 = new StringBuffer().append("<").append(nameAsString()).toString();
        if (this.fAttributes != null) {
            Iterator attributes = getAttributes();
            while (true) {
                string = string2;
                if (!attributes.hasNext()) {
                    break;
                }
                string2 = new StringBuffer().append(string).append(" ").append(((Attribute) attributes.next()).toString()).toString();
            }
        } else {
            string = string2;
        }
        if (this.fNamespaces != null) {
            Iterator it = this.fNamespaces.iterator();
            while (it.hasNext()) {
                string = new StringBuffer().append(string).append(" ").append(((Namespace) it.next()).toString()).toString();
            }
        }
        return new StringBuffer().append(string).append(">").toString();
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

    @Override // com.amazonaws.javax.xml.stream.events.StartElement
    public a getNamespaceContext() {
        return this.fNamespaceContext;
    }

    public void setNamespaceContext(a aVar) {
        this.fNamespaceContext = aVar;
    }

    @Override // com.amazonaws.javax.xml.stream.events.DummyEvent
    protected void writeAsEncodedUnicodeEx(Writer writer) throws IOException {
        writer.write(toString());
    }

    void addAttribute(Attribute attribute) {
        if (attribute.isNamespace()) {
            this.fNamespaces.add(attribute);
        } else {
            this.fAttributes.put(attribute.getName(), attribute);
        }
    }

    void addAttributes(Iterator it) {
        if (it != null) {
            while (it.hasNext()) {
                Attribute attribute = (Attribute) it.next();
                this.fAttributes.put(attribute.getName(), attribute);
            }
        }
    }

    void addNamespaceAttribute(Namespace namespace) {
        if (namespace != null) {
            this.fNamespaces.add(namespace);
        }
    }

    void addNamespaceAttributes(Iterator it) {
        if (it != null) {
            while (it.hasNext()) {
                this.fNamespaces.add((Namespace) it.next());
            }
        }
    }
}

package com.amazonaws.javax.xml.stream.events;

import com.amazonaws.javax.xml.a.b;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class NamespaceImpl extends AttributeImpl implements Namespace {
    public NamespaceImpl() {
        init();
    }

    public NamespaceImpl(String str) {
        super("xmlns", "http://www.w3.org/2000/xmlns/", "", str, (String) null);
        init();
    }

    public NamespaceImpl(String str, String str2) {
        super("xmlns", "http://www.w3.org/2000/xmlns/", str, str2, (String) null);
        init();
    }

    @Override // com.amazonaws.javax.xml.stream.events.Namespace
    public boolean isDefaultNamespaceDeclaration() {
        b name = getName();
        return name != null && name.b().equals("");
    }

    void setPrefix(String str) {
        if (str == null) {
            setName(new b("http://www.w3.org/2000/xmlns/", "", "xmlns"));
        } else {
            setName(new b("http://www.w3.org/2000/xmlns/", str, "xmlns"));
        }
    }

    @Override // com.amazonaws.javax.xml.stream.events.Namespace
    public String getPrefix() {
        b name = getName();
        if (name != null) {
            return name.b();
        }
        return null;
    }

    @Override // com.amazonaws.javax.xml.stream.events.Namespace
    public String getNamespaceURI() {
        return getValue();
    }

    void setNamespaceURI(String str) {
        setValue(str);
    }

    @Override // com.amazonaws.javax.xml.stream.events.AttributeImpl
    protected void init() {
        setEventType(13);
    }

    @Override // com.amazonaws.javax.xml.stream.events.DummyEvent, com.amazonaws.javax.xml.stream.events.XMLEvent
    public int getEventType() {
        return 13;
    }

    @Override // com.amazonaws.javax.xml.stream.events.DummyEvent, com.amazonaws.javax.xml.stream.events.XMLEvent
    public boolean isNamespace() {
        return true;
    }
}

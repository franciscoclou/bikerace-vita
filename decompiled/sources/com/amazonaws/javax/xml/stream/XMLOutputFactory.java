package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.transform.d;
import java.io.OutputStream;
import java.io.Writer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class XMLOutputFactory {
    static final String DEFAULIMPL = "com.sun.xml.internal.stream.XMLOutputFactoryImpl";
    public static final String IS_REPAIRING_NAMESPACES = "com.amazonaws.javax.xml.stream.isRepairingNamespaces";

    public abstract XMLEventWriter createXMLEventWriter(d dVar);

    public abstract XMLEventWriter createXMLEventWriter(OutputStream outputStream);

    public abstract XMLEventWriter createXMLEventWriter(OutputStream outputStream, String str);

    public abstract XMLEventWriter createXMLEventWriter(Writer writer);

    public abstract XMLStreamWriter createXMLStreamWriter(d dVar);

    public abstract XMLStreamWriter createXMLStreamWriter(OutputStream outputStream);

    public abstract XMLStreamWriter createXMLStreamWriter(OutputStream outputStream, String str);

    public abstract XMLStreamWriter createXMLStreamWriter(Writer writer);

    public abstract Object getProperty(String str);

    public abstract boolean isPropertySupported(String str);

    public abstract void setProperty(String str, Object obj);

    protected XMLOutputFactory() {
    }

    public static XMLOutputFactory newInstance() {
        return (XMLOutputFactory) FactoryFinder.find("com.amazonaws.javax.xml.stream.XMLOutputFactory", DEFAULIMPL);
    }

    public static XMLOutputFactory newFactory() {
        return (XMLOutputFactory) FactoryFinder.find("com.amazonaws.javax.xml.stream.XMLOutputFactory", DEFAULIMPL);
    }

    public static XMLInputFactory newInstance(String str, ClassLoader classLoader) {
        try {
            return (XMLInputFactory) FactoryFinder.find(str, classLoader, null);
        } catch (FactoryFinder.ConfigurationError e) {
            throw new FactoryConfigurationError(e.getException(), e.getMessage());
        }
    }

    public static XMLOutputFactory newFactory(String str, ClassLoader classLoader) {
        try {
            return (XMLOutputFactory) FactoryFinder.find(str, classLoader, null);
        } catch (FactoryFinder.ConfigurationError e) {
            throw new FactoryConfigurationError(e.getException(), e.getMessage());
        }
    }
}

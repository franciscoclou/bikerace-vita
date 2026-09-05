package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.stream.util.XMLEventAllocator;
import com.amazonaws.javax.xml.transform.f;
import java.io.InputStream;
import java.io.Reader;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class XMLInputFactory {
    public static final String ALLOCATOR = "com.amazonaws.javax.xml.stream.allocator";
    static final String DEFAULIMPL = "com.amazonaws.javax.xml.stream.ZephyrParserFactory";
    public static final String IS_COALESCING = "com.amazonaws.javax.xml.stream.isCoalescing";
    public static final String IS_NAMESPACE_AWARE = "com.amazonaws.javax.xml.stream.isNamespaceAware";
    public static final String IS_REPLACING_ENTITY_REFERENCES = "com.amazonaws.javax.xml.stream.isReplacingEntityReferences";
    public static final String IS_SUPPORTING_EXTERNAL_ENTITIES = "com.amazonaws.javax.xml.stream.isSupportingExternalEntities";
    public static final String IS_VALIDATING = "com.amazonaws.javax.xml.stream.isValidating";
    public static final String REPORTER = "com.amazonaws.javax.xml.stream.reporter";
    public static final String RESOLVER = "com.amazonaws.javax.xml.stream.resolver";
    public static final String SUPPORT_DTD = "com.amazonaws.javax.xml.stream.supportDTD";

    public abstract XMLEventReader createFilteredReader(XMLEventReader xMLEventReader, EventFilter eventFilter);

    public abstract XMLStreamReader createFilteredReader(XMLStreamReader xMLStreamReader, StreamFilter streamFilter);

    public abstract XMLEventReader createXMLEventReader(XMLStreamReader xMLStreamReader);

    public abstract XMLEventReader createXMLEventReader(f fVar);

    public abstract XMLEventReader createXMLEventReader(InputStream inputStream);

    public abstract XMLEventReader createXMLEventReader(InputStream inputStream, String str);

    public abstract XMLEventReader createXMLEventReader(Reader reader);

    public abstract XMLEventReader createXMLEventReader(String str, InputStream inputStream);

    public abstract XMLEventReader createXMLEventReader(String str, Reader reader);

    public abstract XMLStreamReader createXMLStreamReader(f fVar);

    public abstract XMLStreamReader createXMLStreamReader(InputStream inputStream);

    public abstract XMLStreamReader createXMLStreamReader(InputStream inputStream, String str);

    public abstract XMLStreamReader createXMLStreamReader(Reader reader);

    public abstract XMLStreamReader createXMLStreamReader(String str, InputStream inputStream);

    public abstract XMLStreamReader createXMLStreamReader(String str, Reader reader);

    public abstract XMLEventAllocator getEventAllocator();

    public abstract Object getProperty(String str);

    public abstract XMLReporter getXMLReporter();

    public abstract XMLResolver getXMLResolver();

    public abstract boolean isPropertySupported(String str);

    public abstract void setEventAllocator(XMLEventAllocator xMLEventAllocator);

    public abstract void setProperty(String str, Object obj);

    public abstract void setXMLReporter(XMLReporter xMLReporter);

    public abstract void setXMLResolver(XMLResolver xMLResolver);

    protected XMLInputFactory() {
    }

    public static XMLInputFactory newInstance() {
        return (XMLInputFactory) FactoryFinder.find("com.amazonaws.javax.xml.stream.XMLInputFactory", DEFAULIMPL);
    }

    public static XMLInputFactory newFactory() {
        return (XMLInputFactory) FactoryFinder.find("com.amazonaws.javax.xml.stream.XMLInputFactory", DEFAULIMPL);
    }

    public static XMLInputFactory newInstance(String str, ClassLoader classLoader) {
        try {
            return (XMLInputFactory) FactoryFinder.find(str, classLoader, null);
        } catch (FactoryFinder.ConfigurationError e) {
            throw new FactoryConfigurationError(e.getException(), e.getMessage());
        }
    }

    public static XMLInputFactory newFactory(String str, ClassLoader classLoader) {
        try {
            return (XMLInputFactory) FactoryFinder.find(str, classLoader, null);
        } catch (FactoryFinder.ConfigurationError e) {
            throw new FactoryConfigurationError(e.getException(), e.getMessage());
        }
    }
}

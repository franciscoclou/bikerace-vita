package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.stream.util.XMLEventAllocator;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLInputSource;
import com.amazonaws.javax.xml.transform.f;
import java.io.InputStream;
import java.io.Reader;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ZephyrParserFactory extends XMLInputFactory {
    private static final boolean DEBUG = false;
    private PropertyManager fPropertyManager = new PropertyManager(1);
    private XMLReaderImpl fTempReader = null;
    boolean fPropertyChanged = false;
    boolean fReuseInstance = false;

    void initEventReader() {
        this.fPropertyChanged = true;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLInputFactory
    public XMLEventReader createXMLEventReader(InputStream inputStream) {
        initEventReader();
        return new XMLEventReaderImpl(createXMLStreamReader(inputStream));
    }

    @Override // com.amazonaws.javax.xml.stream.XMLInputFactory
    public XMLEventReader createXMLEventReader(Reader reader) {
        initEventReader();
        return new XMLEventReaderImpl(createXMLStreamReader(reader));
    }

    @Override // com.amazonaws.javax.xml.stream.XMLInputFactory
    public XMLEventReader createXMLEventReader(f fVar) {
        initEventReader();
        return new XMLEventReaderImpl(createXMLStreamReader(fVar));
    }

    @Override // com.amazonaws.javax.xml.stream.XMLInputFactory
    public XMLEventReader createXMLEventReader(String str, InputStream inputStream) {
        initEventReader();
        return new XMLEventReaderImpl(createXMLStreamReader(str, inputStream));
    }

    @Override // com.amazonaws.javax.xml.stream.XMLInputFactory
    public XMLEventReader createXMLEventReader(InputStream inputStream, String str) {
        initEventReader();
        return new XMLEventReaderImpl(createXMLStreamReader(inputStream, str));
    }

    @Override // com.amazonaws.javax.xml.stream.XMLInputFactory
    public XMLEventReader createXMLEventReader(String str, Reader reader) {
        initEventReader();
        return new XMLEventReaderImpl(createXMLStreamReader(str, reader));
    }

    @Override // com.amazonaws.javax.xml.stream.XMLInputFactory
    public XMLEventReader createXMLEventReader(XMLStreamReader xMLStreamReader) {
        initEventReader();
        return new XMLEventReaderImpl(xMLStreamReader);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLInputFactory
    public XMLStreamReader createXMLStreamReader(Reader reader) {
        return createXMLStreamReader((String) null, reader);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLInputFactory
    public XMLStreamReader createXMLStreamReader(String str, Reader reader) {
        return getXMLStreamReaderImpl(new XMLInputSource((String) null, str, (String) null, reader, (String) null));
    }

    @Override // com.amazonaws.javax.xml.stream.XMLInputFactory
    public XMLStreamReader createXMLStreamReader(f fVar) {
        return new XMLReaderImpl(jaxpSourcetoXMLInputSource(fVar), new PropertyManager(this.fPropertyManager));
    }

    @Override // com.amazonaws.javax.xml.stream.XMLInputFactory
    public XMLStreamReader createXMLStreamReader(InputStream inputStream) {
        return createXMLStreamReader(null, inputStream, null);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLInputFactory
    public XMLStreamReader createXMLStreamReader(String str, InputStream inputStream) {
        return createXMLStreamReader(str, inputStream, null);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLInputFactory
    public XMLStreamReader createXMLStreamReader(InputStream inputStream, String str) {
        return createXMLStreamReader(null, inputStream, str);
    }

    public XMLStreamReader createXMLStreamReader(String str, InputStream inputStream, String str2) {
        return getXMLStreamReaderImpl(new XMLInputSource((String) null, str, (String) null, inputStream, str2));
    }

    @Override // com.amazonaws.javax.xml.stream.XMLInputFactory
    public XMLEventAllocator getEventAllocator() {
        return (XMLEventAllocator) getProperty(XMLInputFactory.ALLOCATOR);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLInputFactory
    public XMLReporter getXMLReporter() {
        return (XMLReporter) this.fPropertyManager.getProperty(XMLInputFactory.REPORTER);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLInputFactory
    public XMLResolver getXMLResolver() {
        return (XMLResolver) this.fPropertyManager.getProperty(XMLInputFactory.RESOLVER);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLInputFactory
    public void setXMLReporter(XMLReporter xMLReporter) {
        this.fPropertyManager.setProperty(XMLInputFactory.REPORTER, xMLReporter);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLInputFactory
    public void setXMLResolver(XMLResolver xMLResolver) {
        this.fPropertyManager.setProperty(XMLInputFactory.RESOLVER, xMLResolver);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLInputFactory
    public XMLEventReader createFilteredReader(XMLEventReader xMLEventReader, EventFilter eventFilter) {
        return new EventFilterSupport(xMLEventReader, eventFilter);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLInputFactory
    public XMLStreamReader createFilteredReader(XMLStreamReader xMLStreamReader, StreamFilter streamFilter) {
        if (xMLStreamReader == null || streamFilter == null) {
            return null;
        }
        return new XMLStreamFilterImpl(xMLStreamReader, streamFilter);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLInputFactory
    public Object getProperty(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Property not supported");
        }
        if (this.fPropertyManager.containsProperty(str)) {
            return this.fPropertyManager.getProperty(str);
        }
        throw new IllegalArgumentException("Property not supported");
    }

    @Override // com.amazonaws.javax.xml.stream.XMLInputFactory
    public boolean isPropertySupported(String str) {
        if (str == null) {
            return false;
        }
        return this.fPropertyManager.containsProperty(str);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLInputFactory
    public void setEventAllocator(XMLEventAllocator xMLEventAllocator) {
        this.fPropertyManager.setProperty(XMLInputFactory.ALLOCATOR, xMLEventAllocator);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLInputFactory
    public void setProperty(String str, Object obj) {
        if (str == null || obj == null || !this.fPropertyManager.containsProperty(str)) {
            throw new IllegalArgumentException(new StringBuffer().append("Property ").append(str).append(" is not supported").toString());
        }
        if (str == Constants.REUSE_INSTANCE || str.equals(Constants.REUSE_INSTANCE)) {
            this.fReuseInstance = ((Boolean) obj).booleanValue();
        } else {
            this.fPropertyChanged = true;
        }
        this.fPropertyManager.setProperty(str, obj);
    }

    XMLStreamReader getXMLStreamReaderImpl(XMLInputSource xMLInputSource) throws XMLStreamException2 {
        if (this.fTempReader == null) {
            this.fPropertyChanged = false;
            XMLReaderImpl xMLReaderImpl = new XMLReaderImpl(xMLInputSource, new PropertyManager(this.fPropertyManager));
            this.fTempReader = xMLReaderImpl;
            return xMLReaderImpl;
        }
        if (this.fReuseInstance && this.fTempReader.canReuse() && !this.fPropertyChanged) {
            this.fTempReader.reset();
            this.fTempReader.setInputSource(xMLInputSource);
            this.fPropertyChanged = false;
            return this.fTempReader;
        }
        this.fPropertyChanged = false;
        XMLReaderImpl xMLReaderImpl2 = new XMLReaderImpl(xMLInputSource, new PropertyManager(this.fPropertyManager));
        this.fTempReader = xMLReaderImpl2;
        return xMLReaderImpl2;
    }

    XMLInputSource jaxpSourcetoXMLInputSource(f fVar) {
        throw new UnsupportedOperationException(new StringBuffer().append("Cannot create XMLStreamReader or XMLEventReader from a ").append(fVar.getClass().getName()).toString());
    }
}

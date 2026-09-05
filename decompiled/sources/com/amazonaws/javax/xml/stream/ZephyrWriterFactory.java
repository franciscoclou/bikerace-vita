package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.stream.writers.XMLDOMWriterImpl;
import com.amazonaws.javax.xml.stream.writers.XMLEventWriterImpl;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.amazonaws.javax.xml.transform.b.a;
import com.amazonaws.javax.xml.transform.d;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class ZephyrWriterFactory extends XMLOutputFactory {
    private static final boolean DEBUG = false;
    private boolean fPropertyChanged;
    private PropertyManager fPropertyManager = new PropertyManager(2);
    private XMLStreamWriterImpl fStreamWriter = null;
    boolean fReuseInstance = false;

    @Override // com.amazonaws.javax.xml.stream.XMLOutputFactory
    public XMLEventWriter createXMLEventWriter(OutputStream outputStream) {
        return createXMLEventWriter(outputStream, null);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLOutputFactory
    public XMLEventWriter createXMLEventWriter(OutputStream outputStream, String str) {
        return new XMLEventWriterImpl(createXMLStreamWriter(outputStream, str));
    }

    @Override // com.amazonaws.javax.xml.stream.XMLOutputFactory
    public XMLEventWriter createXMLEventWriter(d dVar) {
        return new XMLEventWriterImpl(createXMLStreamWriter(dVar));
    }

    @Override // com.amazonaws.javax.xml.stream.XMLOutputFactory
    public XMLEventWriter createXMLEventWriter(Writer writer) {
        return new XMLEventWriterImpl(createXMLStreamWriter(writer));
    }

    @Override // com.amazonaws.javax.xml.stream.XMLOutputFactory
    public XMLStreamWriter createXMLStreamWriter(d dVar) {
        if (dVar instanceof a) {
            return createXMLStreamWriter((a) dVar, (String) null);
        }
        if (dVar instanceof com.amazonaws.javax.xml.transform.a.a) {
            return new XMLDOMWriterImpl((com.amazonaws.javax.xml.transform.a.a) dVar);
        }
        if (dVar instanceof d) {
            return createXMLStreamWriter(new a(dVar.getSystemId()));
        }
        throw new UnsupportedOperationException(new StringBuffer().append("result of type ").append(dVar).append(" is not supported").toString());
    }

    @Override // com.amazonaws.javax.xml.stream.XMLOutputFactory
    public XMLStreamWriter createXMLStreamWriter(Writer writer) {
        return createXMLStreamWriter(toStreamResult(null, writer, null), (String) null);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLOutputFactory
    public XMLStreamWriter createXMLStreamWriter(OutputStream outputStream) {
        return createXMLStreamWriter(outputStream, (String) null);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLOutputFactory
    public XMLStreamWriter createXMLStreamWriter(OutputStream outputStream, String str) {
        return createXMLStreamWriter(toStreamResult(outputStream, null, null), str);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLOutputFactory
    public Object getProperty(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Property not supported");
        }
        if (this.fPropertyManager.containsProperty(str)) {
            return this.fPropertyManager.getProperty(str);
        }
        throw new IllegalArgumentException("Property not supported");
    }

    @Override // com.amazonaws.javax.xml.stream.XMLOutputFactory
    public boolean isPropertySupported(String str) {
        if (str == null) {
            return false;
        }
        return this.fPropertyManager.containsProperty(str);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLOutputFactory
    public void setProperty(String str, Object obj) {
        if (str == null || obj == null || !this.fPropertyManager.containsProperty(str)) {
            throw new IllegalArgumentException(new StringBuffer().append("Property ").append(str).append("is not supported").toString());
        }
        if (str == Constants.REUSE_INSTANCE || str.equals(Constants.REUSE_INSTANCE)) {
            this.fReuseInstance = ((Boolean) obj).booleanValue();
            if (this.fReuseInstance) {
                throw new IllegalArgumentException(new StringBuffer().append("Property ").append(str).append(" is not supported: XMLStreamWriters are not Thread safe").toString());
            }
        } else {
            this.fPropertyChanged = true;
        }
        this.fPropertyManager.setProperty(str, obj);
    }

    private a toStreamResult(OutputStream outputStream, Writer writer, String str) {
        a aVar = new a();
        aVar.a(outputStream);
        aVar.a(writer);
        aVar.a(str);
        return aVar;
    }

    XMLStreamWriter createXMLStreamWriter(a aVar, String str) throws XMLStreamException2 {
        try {
            if (this.fReuseInstance && this.fStreamWriter != null && this.fStreamWriter.canReuse() && !this.fPropertyChanged) {
                this.fStreamWriter.reset();
                this.fStreamWriter.setOutput(aVar, str);
                return this.fStreamWriter;
            }
            XMLStreamWriterImpl xMLStreamWriterImpl = new XMLStreamWriterImpl(aVar, str, new PropertyManager(this.fPropertyManager));
            this.fStreamWriter = xMLStreamWriterImpl;
            return xMLStreamWriterImpl;
        } catch (IOException e) {
            throw new XMLStreamException2(e);
        }
    }
}

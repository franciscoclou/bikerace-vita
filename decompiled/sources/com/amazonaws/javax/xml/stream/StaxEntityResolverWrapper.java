package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.stream.xerces.xni.XMLResourceIdentifier;
import com.amazonaws.javax.xml.stream.xerces.xni.XNIException;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLInputSource;
import java.io.InputStream;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class StaxEntityResolverWrapper {
    XMLResolver fStaxResolver;

    public StaxEntityResolverWrapper(XMLResolver xMLResolver) {
        this.fStaxResolver = xMLResolver;
    }

    public void setStaxEntityResolver(XMLResolver xMLResolver) {
        this.fStaxResolver = xMLResolver;
    }

    public XMLResolver getStaxEntityResolver() {
        return this.fStaxResolver;
    }

    public StaxXMLInputSource resolveEntity(XMLResourceIdentifier xMLResourceIdentifier) {
        try {
            return getStaxInputSource(this.fStaxResolver.resolveEntity(xMLResourceIdentifier.getPublicId(), xMLResourceIdentifier.getLiteralSystemId(), xMLResourceIdentifier.getBaseSystemId(), null));
        } catch (XMLStreamException e) {
            throw new XNIException(e);
        }
    }

    StaxXMLInputSource getStaxInputSource(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof InputStream) {
            return new StaxXMLInputSource(new XMLInputSource((String) null, (String) null, (String) null, (InputStream) obj, (String) null));
        }
        if (obj instanceof XMLStreamReader) {
            return new StaxXMLInputSource((XMLStreamReader) obj);
        }
        if (obj instanceof XMLEventReader) {
            return new StaxXMLInputSource((XMLEventReader) obj);
        }
        return null;
    }
}

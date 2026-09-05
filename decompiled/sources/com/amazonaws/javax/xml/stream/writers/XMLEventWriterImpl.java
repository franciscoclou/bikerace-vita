package com.amazonaws.javax.xml.stream.writers;

import com.amazonaws.javax.xml.a.a;
import com.amazonaws.javax.xml.a.b;
import com.amazonaws.javax.xml.stream.XMLEventReader;
import com.amazonaws.javax.xml.stream.XMLEventWriter;
import com.amazonaws.javax.xml.stream.XMLStreamConstants;
import com.amazonaws.javax.xml.stream.XMLStreamException;
import com.amazonaws.javax.xml.stream.XMLStreamException2;
import com.amazonaws.javax.xml.stream.XMLStreamWriter;
import com.amazonaws.javax.xml.stream.events.Attribute;
import com.amazonaws.javax.xml.stream.events.Characters;
import com.amazonaws.javax.xml.stream.events.Comment;
import com.amazonaws.javax.xml.stream.events.DTD;
import com.amazonaws.javax.xml.stream.events.EntityReference;
import com.amazonaws.javax.xml.stream.events.Namespace;
import com.amazonaws.javax.xml.stream.events.ProcessingInstruction;
import com.amazonaws.javax.xml.stream.events.StartDocument;
import com.amazonaws.javax.xml.stream.events.StartElement;
import com.amazonaws.javax.xml.stream.events.XMLEvent;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class XMLEventWriterImpl implements XMLEventWriter {
    private static final boolean DEBUG = false;
    private XMLStreamWriter fStreamWriter;

    public XMLEventWriterImpl(XMLStreamWriter xMLStreamWriter) {
        this.fStreamWriter = xMLStreamWriter;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventWriter
    public void add(XMLEventReader xMLEventReader) throws XMLStreamException2 {
        if (xMLEventReader == null) {
            throw new XMLStreamException2("Event reader shouldn't be null");
        }
        while (xMLEventReader.hasNext()) {
            add(xMLEventReader.nextEvent());
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventWriter, com.amazonaws.javax.xml.stream.util.XMLEventConsumer
    public void add(XMLEvent xMLEvent) {
        switch (xMLEvent.getEventType()) {
            case 1:
                StartElement startElementAsStartElement = xMLEvent.asStartElement();
                b name = startElementAsStartElement.getName();
                this.fStreamWriter.writeStartElement(name.c(), name.b(), name.a());
                Iterator namespaces = startElementAsStartElement.getNamespaces();
                while (namespaces.hasNext()) {
                    Namespace namespace = (Namespace) namespaces.next();
                    this.fStreamWriter.writeNamespace(namespace.getPrefix(), namespace.getNamespaceURI());
                }
                Iterator attributes = startElementAsStartElement.getAttributes();
                while (attributes.hasNext()) {
                    Attribute attribute = (Attribute) attributes.next();
                    b name2 = attribute.getName();
                    this.fStreamWriter.writeAttribute(name2.c(), name2.a(), name2.b(), attribute.getValue());
                }
                break;
            case 2:
                this.fStreamWriter.writeEndElement();
                break;
            case 3:
                ProcessingInstruction processingInstruction = (ProcessingInstruction) xMLEvent;
                this.fStreamWriter.writeProcessingInstruction(processingInstruction.getTarget(), processingInstruction.getData());
                break;
            case 4:
                Characters charactersAsCharacters = xMLEvent.asCharacters();
                if (charactersAsCharacters.isCData()) {
                    this.fStreamWriter.writeCData(charactersAsCharacters.getData());
                } else {
                    this.fStreamWriter.writeCharacters(charactersAsCharacters.getData());
                }
                break;
            case 5:
                this.fStreamWriter.writeComment(((Comment) xMLEvent).getText());
                break;
            case 7:
                StartDocument startDocument = (StartDocument) xMLEvent;
                try {
                    this.fStreamWriter.writeStartDocument(startDocument.getCharacterEncodingScheme(), startDocument.getVersion());
                } catch (XMLStreamException e) {
                    this.fStreamWriter.writeStartDocument(startDocument.getVersion());
                    return;
                }
                break;
            case 8:
                this.fStreamWriter.writeEndDocument();
                break;
            case 9:
                this.fStreamWriter.writeEntityRef(((EntityReference) xMLEvent).getName());
                break;
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
                Attribute attribute2 = (Attribute) xMLEvent;
                b name3 = attribute2.getName();
                this.fStreamWriter.writeAttribute(name3.c(), name3.a(), name3.b(), attribute2.getValue());
                break;
            case XMLStreamConstants.DTD /* 11 */:
                this.fStreamWriter.writeDTD(((DTD) xMLEvent).getDocumentTypeDeclaration());
                break;
            case XMLStreamConstants.CDATA /* 12 */:
                Characters characters = (Characters) xMLEvent;
                if (characters.isCData()) {
                    this.fStreamWriter.writeCData(characters.getData());
                }
                break;
            case XMLStreamConstants.NAMESPACE /* 13 */:
                Namespace namespace2 = (Namespace) xMLEvent;
                this.fStreamWriter.writeNamespace(namespace2.getPrefix(), namespace2.getNamespaceURI());
                break;
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventWriter
    public void close() {
        this.fStreamWriter.close();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventWriter
    public void flush() {
        this.fStreamWriter.flush();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventWriter
    public a getNamespaceContext() {
        return this.fStreamWriter.getNamespaceContext();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventWriter
    public String getPrefix(String str) {
        return this.fStreamWriter.getPrefix(str);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventWriter
    public void setDefaultNamespace(String str) {
        this.fStreamWriter.setDefaultNamespace(str);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventWriter
    public void setNamespaceContext(a aVar) {
        this.fStreamWriter.setNamespaceContext(aVar);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventWriter
    public void setPrefix(String str, String str2) {
        this.fStreamWriter.setPrefix(str, str2);
    }
}

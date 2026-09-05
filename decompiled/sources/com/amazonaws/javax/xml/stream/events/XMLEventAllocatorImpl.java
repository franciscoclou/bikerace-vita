package com.amazonaws.javax.xml.stream.events;

import com.amazonaws.javax.xml.a.b;
import com.amazonaws.javax.xml.stream.PropertyManager;
import com.amazonaws.javax.xml.stream.XMLInputFactory;
import com.amazonaws.javax.xml.stream.XMLStreamConstants;
import com.amazonaws.javax.xml.stream.XMLStreamException2;
import com.amazonaws.javax.xml.stream.XMLStreamReader;
import com.amazonaws.javax.xml.stream.util.XMLEventAllocator;
import com.amazonaws.javax.xml.stream.util.XMLEventConsumer;
import com.amazonaws.javax.xml.stream.xerces.util.NamespaceContextWrapper;
import com.amazonaws.javax.xml.stream.xerces.util.NamespaceSupport;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class XMLEventAllocatorImpl implements XMLEventAllocator {
    @Override // com.amazonaws.javax.xml.stream.util.XMLEventAllocator
    public XMLEvent allocate(XMLStreamReader xMLStreamReader) throws XMLStreamException2 {
        if (xMLStreamReader == null) {
            throw new XMLStreamException2("Reader cannot be null");
        }
        return getXMLEvent(xMLStreamReader);
    }

    @Override // com.amazonaws.javax.xml.stream.util.XMLEventAllocator
    public void allocate(XMLStreamReader xMLStreamReader, XMLEventConsumer xMLEventConsumer) {
        XMLEvent xMLEvent = getXMLEvent(xMLStreamReader);
        if (xMLEvent != null) {
            xMLEventConsumer.add(xMLEvent);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.util.XMLEventAllocator
    public XMLEventAllocator newInstance() {
        return new XMLEventAllocatorImpl();
    }

    XMLEvent getXMLEvent(XMLStreamReader xMLStreamReader) {
        switch (xMLStreamReader.getEventType()) {
            case 1:
                StartElementEvent startElementEvent = new StartElementEvent(getQName(xMLStreamReader));
                fillAttributes(startElementEvent, xMLStreamReader);
                if (((Boolean) xMLStreamReader.getProperty(XMLInputFactory.IS_NAMESPACE_AWARE)).booleanValue()) {
                    fillNamespaceAttributes(startElementEvent, xMLStreamReader);
                    setNamespaceContext(startElementEvent, xMLStreamReader);
                }
                startElementEvent.setLocation(xMLStreamReader.getLocation());
                return startElementEvent;
            case 2:
                EndElementEvent endElementEvent = new EndElementEvent(getQName(xMLStreamReader));
                endElementEvent.setLocation(xMLStreamReader.getLocation());
                if (((Boolean) xMLStreamReader.getProperty(XMLInputFactory.IS_NAMESPACE_AWARE)).booleanValue()) {
                    fillNamespaceAttributes(endElementEvent, xMLStreamReader);
                }
                return endElementEvent;
            case 3:
                ProcessingInstructionEvent processingInstructionEvent = new ProcessingInstructionEvent(xMLStreamReader.getPITarget(), xMLStreamReader.getPIData());
                processingInstructionEvent.setLocation(xMLStreamReader.getLocation());
                return processingInstructionEvent;
            case 4:
                CharacterEvent characterEvent = new CharacterEvent(xMLStreamReader.getText());
                characterEvent.setLocation(xMLStreamReader.getLocation());
                return characterEvent;
            case 5:
                CommentEvent commentEvent = new CommentEvent(xMLStreamReader.getText());
                commentEvent.setLocation(xMLStreamReader.getLocation());
                return commentEvent;
            case 6:
                CharacterEvent characterEvent2 = new CharacterEvent(xMLStreamReader.getText(), false, true);
                characterEvent2.setLocation(xMLStreamReader.getLocation());
                return characterEvent2;
            case 7:
                StartDocumentEvent startDocumentEvent = new StartDocumentEvent();
                startDocumentEvent.setVersion(xMLStreamReader.getVersion());
                startDocumentEvent.setEncoding(xMLStreamReader.getEncoding());
                if (xMLStreamReader.getCharacterEncodingScheme() != null) {
                    startDocumentEvent.setDeclaredEncoding(true);
                } else {
                    startDocumentEvent.setDeclaredEncoding(false);
                }
                startDocumentEvent.setStandalone(xMLStreamReader.isStandalone());
                startDocumentEvent.setLocation(xMLStreamReader.getLocation());
                return startDocumentEvent;
            case 8:
                EndDocumentEvent endDocumentEvent = new EndDocumentEvent();
                endDocumentEvent.setLocation(xMLStreamReader.getLocation());
                return endDocumentEvent;
            case 9:
                EntityReferenceEvent entityReferenceEvent = new EntityReferenceEvent(xMLStreamReader.getLocalName(), new EntityDeclarationImpl(xMLStreamReader.getLocalName(), xMLStreamReader.getText()));
                entityReferenceEvent.setLocation(xMLStreamReader.getLocation());
                return entityReferenceEvent;
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
            default:
                return null;
            case XMLStreamConstants.DTD /* 11 */:
                DTDEvent dTDEvent = new DTDEvent(xMLStreamReader.getText());
                dTDEvent.setLocation(xMLStreamReader.getLocation());
                List list = (List) xMLStreamReader.getProperty(PropertyManager.STAX_ENTITIES);
                if (list != null && list.size() != 0) {
                    dTDEvent.setEntities(list);
                }
                List list2 = (List) xMLStreamReader.getProperty(PropertyManager.STAX_NOTATIONS);
                if (list2 != null && list2.size() != 0) {
                    dTDEvent.setNotations(list2);
                }
                return dTDEvent;
            case XMLStreamConstants.CDATA /* 12 */:
                CharacterEvent characterEvent3 = new CharacterEvent(xMLStreamReader.getText(), true);
                characterEvent3.setLocation(xMLStreamReader.getLocation());
                return characterEvent3;
        }
    }

    protected XMLEvent getNextEvent(XMLStreamReader xMLStreamReader) {
        xMLStreamReader.next();
        return getXMLEvent(xMLStreamReader);
    }

    protected void fillAttributes(StartElementEvent startElementEvent, XMLStreamReader xMLStreamReader) {
        int attributeCount = xMLStreamReader.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            b attributeName = xMLStreamReader.getAttributeName(i);
            attributeName.c();
            attributeName.b();
            AttributeImpl attributeImpl = new AttributeImpl();
            attributeImpl.setName(attributeName);
            attributeImpl.setAttributeType(xMLStreamReader.getAttributeType(i));
            attributeImpl.setSpecified(xMLStreamReader.isAttributeSpecified(i));
            attributeImpl.setValue(xMLStreamReader.getAttributeValue(i));
            startElementEvent.addAttribute(attributeImpl);
        }
    }

    protected void fillNamespaceAttributes(StartElementEvent startElementEvent, XMLStreamReader xMLStreamReader) {
        int namespaceCount = xMLStreamReader.getNamespaceCount();
        for (int i = 0; i < namespaceCount; i++) {
            String namespaceURI = xMLStreamReader.getNamespaceURI(i);
            String namespacePrefix = xMLStreamReader.getNamespacePrefix(i);
            if (namespacePrefix == null) {
                namespacePrefix = "";
            }
            startElementEvent.addNamespaceAttribute(new NamespaceImpl(namespacePrefix, namespaceURI));
        }
    }

    protected void fillNamespaceAttributes(EndElementEvent endElementEvent, XMLStreamReader xMLStreamReader) {
        int namespaceCount = xMLStreamReader.getNamespaceCount();
        for (int i = 0; i < namespaceCount; i++) {
            String namespaceURI = xMLStreamReader.getNamespaceURI(i);
            String namespacePrefix = xMLStreamReader.getNamespacePrefix(i);
            if (namespacePrefix == null) {
                namespacePrefix = "";
            }
            endElementEvent.addNamespace(new NamespaceImpl(namespacePrefix, namespaceURI));
        }
    }

    private void setNamespaceContext(StartElementEvent startElementEvent, XMLStreamReader xMLStreamReader) {
        startElementEvent.setNamespaceContext(new NamespaceContextWrapper(new NamespaceSupport(((NamespaceContextWrapper) xMLStreamReader.getNamespaceContext()).getNamespaceContext())));
    }

    private b getQName(XMLStreamReader xMLStreamReader) {
        return new b(xMLStreamReader.getNamespaceURI(), xMLStreamReader.getLocalName(), xMLStreamReader.getPrefix());
    }
}

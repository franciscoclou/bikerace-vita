package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.stream.events.EntityReference;
import com.amazonaws.javax.xml.stream.events.XMLEvent;
import com.amazonaws.javax.xml.stream.events.XMLEventAllocatorImpl;
import com.amazonaws.javax.xml.stream.util.XMLEventAllocator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class XMLEventReaderImpl implements XMLEventReader {
    private XMLEvent fLastEvent;
    private XMLEvent fPeekedEvent;
    protected XMLEventAllocator fXMLEventAllocator;
    protected XMLStreamReader fXMLReader;

    public XMLEventReaderImpl(XMLStreamReader xMLStreamReader) {
        this.fXMLReader = xMLStreamReader;
        this.fXMLEventAllocator = (XMLEventAllocator) xMLStreamReader.getProperty(XMLInputFactory.ALLOCATOR);
        if (this.fXMLEventAllocator == null) {
            this.fXMLEventAllocator = new XMLEventAllocatorImpl();
        }
        this.fPeekedEvent = this.fXMLEventAllocator.allocate(this.fXMLReader);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventReader, java.util.Iterator
    public boolean hasNext() {
        if (this.fPeekedEvent != null) {
            return true;
        }
        try {
            return this.fXMLReader.hasNext();
        } catch (XMLStreamException e) {
            return false;
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventReader
    public XMLEvent nextEvent() {
        if (this.fPeekedEvent != null) {
            this.fLastEvent = this.fPeekedEvent;
            this.fPeekedEvent = null;
            return this.fLastEvent;
        }
        if (this.fXMLReader.hasNext()) {
            this.fXMLReader.next();
            XMLEvent xMLEventAllocate = this.fXMLEventAllocator.allocate(this.fXMLReader);
            this.fLastEvent = xMLEventAllocate;
            return xMLEventAllocate;
        }
        this.fLastEvent = null;
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventReader
    public void close() {
        this.fXMLReader.close();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventReader
    public String getElementText() throws XMLStreamException2 {
        String data;
        if (this.fLastEvent.getEventType() != 1) {
            throw new XMLStreamException2("parser must be on START_ELEMENT to read next text", this.fLastEvent.getLocation());
        }
        if (this.fPeekedEvent != null) {
            XMLEvent xMLEvent = this.fPeekedEvent;
            this.fPeekedEvent = null;
            int eventType = xMLEvent.getEventType();
            if (eventType == 4 || eventType == 6 || eventType == 12) {
                data = xMLEvent.asCharacters().getData();
            } else if (eventType == 9) {
                data = ((EntityReference) xMLEvent).getDeclaration().getReplacementText();
            } else if (eventType == 5 || eventType == 3) {
                data = null;
            } else {
                if (eventType == 1) {
                    throw new XMLStreamException2("elementGetText() function expects text only elment but START_ELEMENT was encountered.", xMLEvent.getLocation());
                }
                if (eventType == 2) {
                    return "";
                }
                data = null;
            }
            StringBuffer stringBuffer = new StringBuffer();
            if (data != null && data.length() > 0) {
                stringBuffer.append(data);
            }
            String data2 = data;
            XMLEvent xMLEventNextEvent = nextEvent();
            while (xMLEventNextEvent.getEventType() != 2) {
                if (eventType == 4 || eventType == 6 || eventType == 12) {
                    data2 = xMLEventNextEvent.asCharacters().getData();
                } else if (eventType == 9) {
                    data2 = ((EntityReference) xMLEventNextEvent).getDeclaration().getReplacementText();
                } else if (eventType != 5 && eventType != 3) {
                    if (eventType == 8) {
                        throw new XMLStreamException2("unexpected end of document when reading element text content");
                    }
                    if (eventType == 1) {
                        throw new XMLStreamException2("elementGetText() function expects text only elment but START_ELEMENT was encountered.", xMLEventNextEvent.getLocation());
                    }
                    throw new XMLStreamException2(new StringBuffer().append("Unexpected event type ").append(eventType).toString(), xMLEventNextEvent.getLocation());
                }
                if (data2 != null && data2.length() > 0) {
                    stringBuffer.append(data2);
                }
                xMLEventNextEvent = nextEvent();
            }
            return stringBuffer.toString();
        }
        String elementText = this.fXMLReader.getElementText();
        this.fLastEvent = this.fXMLEventAllocator.allocate(this.fXMLReader);
        return elementText;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventReader
    public Object getProperty(String str) {
        return this.fXMLReader.getProperty(str);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventReader
    public XMLEvent nextTag() throws XMLStreamException2 {
        if (this.fPeekedEvent != null) {
            XMLEvent xMLEventNextEvent = this.fPeekedEvent;
            this.fPeekedEvent = null;
            int eventType = xMLEventNextEvent.getEventType();
            if ((xMLEventNextEvent.isCharacters() && xMLEventNextEvent.asCharacters().isWhiteSpace()) || eventType == 3 || eventType == 5 || eventType == 7) {
                xMLEventNextEvent = nextEvent();
                eventType = xMLEventNextEvent.getEventType();
            }
            while (true) {
                if ((!xMLEventNextEvent.isCharacters() || !xMLEventNextEvent.asCharacters().isWhiteSpace()) && eventType != 3 && eventType != 5) {
                    break;
                }
                xMLEventNextEvent = nextEvent();
                eventType = xMLEventNextEvent.getEventType();
            }
            if (eventType == 1 || eventType == 2) {
                return xMLEventNextEvent;
            }
            throw new XMLStreamException2("expected start or end tag", xMLEventNextEvent.getLocation());
        }
        this.fXMLReader.nextTag();
        XMLEvent xMLEventAllocate = this.fXMLEventAllocator.allocate(this.fXMLReader);
        this.fLastEvent = xMLEventAllocate;
        return xMLEventAllocate;
    }

    @Override // java.util.Iterator
    public Object next() {
        try {
            return nextEvent();
        } catch (XMLStreamException e) {
            this.fLastEvent = null;
            throw new NoSuchElementException();
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEventReader
    public XMLEvent peek() {
        if (this.fPeekedEvent != null) {
            return this.fPeekedEvent;
        }
        if (hasNext()) {
            this.fXMLReader.next();
            this.fPeekedEvent = this.fXMLEventAllocator.allocate(this.fXMLReader);
            return this.fPeekedEvent;
        }
        return null;
    }
}

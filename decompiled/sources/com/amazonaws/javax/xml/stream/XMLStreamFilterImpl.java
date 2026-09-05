package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.a.a;
import com.amazonaws.javax.xml.a.b;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class XMLStreamFilterImpl implements XMLStreamReader {
    private int fCurrentEvent;
    private boolean fEventAccepted;
    private boolean fStreamAdvancedByHasNext = false;
    private StreamFilter fStreamFilter;
    private XMLStreamReader fStreamReader;

    public XMLStreamFilterImpl(XMLStreamReader xMLStreamReader, StreamFilter streamFilter) {
        this.fStreamFilter = null;
        this.fStreamReader = null;
        this.fEventAccepted = false;
        this.fStreamReader = xMLStreamReader;
        this.fStreamFilter = streamFilter;
        try {
            if (this.fStreamFilter.accept(this.fStreamReader)) {
                this.fEventAccepted = true;
            } else {
                findNextEvent();
            }
        } catch (XMLStreamException e) {
            System.err.println(new StringBuffer().append("Error while creating a stream Filter").append(e).toString());
        }
    }

    protected void setStreamFilter(StreamFilter streamFilter) {
        this.fStreamFilter = streamFilter;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public int next() {
        if (this.fStreamAdvancedByHasNext && this.fEventAccepted) {
            this.fStreamAdvancedByHasNext = false;
            return this.fCurrentEvent;
        }
        int iFindNextEvent = findNextEvent();
        if (iFindNextEvent != -1) {
            return iFindNextEvent;
        }
        throw new IllegalStateException("The stream reader has reached the end of the document, or there are no more  items to return");
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public int nextTag() {
        if (this.fStreamAdvancedByHasNext && this.fEventAccepted && (this.fCurrentEvent == 1 || this.fCurrentEvent == 1)) {
            this.fStreamAdvancedByHasNext = false;
            return this.fCurrentEvent;
        }
        int iFindNextTag = findNextTag();
        if (iFindNextTag != -1) {
            return iFindNextTag;
        }
        throw new IllegalStateException("The stream reader has reached the end of the document, or there are no more  items to return");
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean hasNext() {
        if (!this.fStreamReader.hasNext()) {
            return false;
        }
        if (!this.fEventAccepted) {
            int iFindNextEvent = findNextEvent();
            this.fCurrentEvent = iFindNextEvent;
            if (iFindNextEvent == -1) {
                return false;
            }
            this.fStreamAdvancedByHasNext = true;
        }
        return true;
    }

    private int findNextEvent() {
        this.fStreamAdvancedByHasNext = false;
        while (this.fStreamReader.hasNext()) {
            this.fCurrentEvent = this.fStreamReader.next();
            if (this.fStreamFilter.accept(this.fStreamReader)) {
                this.fEventAccepted = true;
                return this.fCurrentEvent;
            }
        }
        if (this.fCurrentEvent == 8) {
            return this.fCurrentEvent;
        }
        return -1;
    }

    private int findNextTag() {
        this.fStreamAdvancedByHasNext = false;
        while (this.fStreamReader.hasNext()) {
            this.fCurrentEvent = this.fStreamReader.nextTag();
            if (this.fStreamFilter.accept(this.fStreamReader)) {
                this.fEventAccepted = true;
                return this.fCurrentEvent;
            }
        }
        if (this.fCurrentEvent == 8) {
            return this.fCurrentEvent;
        }
        return -1;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public void close() {
        this.fStreamReader.close();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public int getAttributeCount() {
        return this.fStreamReader.getAttributeCount();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public b getAttributeName(int i) {
        return this.fStreamReader.getAttributeName(i);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getAttributeNamespace(int i) {
        return this.fStreamReader.getAttributeNamespace(i);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getAttributePrefix(int i) {
        return this.fStreamReader.getAttributePrefix(i);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getAttributeType(int i) {
        return this.fStreamReader.getAttributeType(i);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getAttributeValue(int i) {
        return this.fStreamReader.getAttributeValue(i);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getAttributeValue(String str, String str2) {
        return this.fStreamReader.getAttributeValue(str, str2);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getCharacterEncodingScheme() {
        return this.fStreamReader.getCharacterEncodingScheme();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getElementText() {
        return this.fStreamReader.getElementText();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getEncoding() {
        return this.fStreamReader.getEncoding();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public int getEventType() {
        return this.fStreamReader.getEventType();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getLocalName() {
        return this.fStreamReader.getLocalName();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public Location getLocation() {
        return this.fStreamReader.getLocation();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public b getName() {
        return this.fStreamReader.getName();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public a getNamespaceContext() {
        return this.fStreamReader.getNamespaceContext();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public int getNamespaceCount() {
        return this.fStreamReader.getNamespaceCount();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getNamespacePrefix(int i) {
        return this.fStreamReader.getNamespacePrefix(i);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getNamespaceURI() {
        return this.fStreamReader.getNamespaceURI();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getNamespaceURI(int i) {
        return this.fStreamReader.getNamespaceURI(i);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getNamespaceURI(String str) {
        return this.fStreamReader.getNamespaceURI(str);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getPIData() {
        return this.fStreamReader.getPIData();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getPITarget() {
        return this.fStreamReader.getPITarget();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getPrefix() {
        return this.fStreamReader.getPrefix();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public Object getProperty(String str) {
        return this.fStreamReader.getProperty(str);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getText() {
        return this.fStreamReader.getText();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public char[] getTextCharacters() {
        return this.fStreamReader.getTextCharacters();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public int getTextCharacters(int i, char[] cArr, int i2, int i3) {
        return this.fStreamReader.getTextCharacters(i, cArr, i2, i3);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public int getTextLength() {
        return this.fStreamReader.getTextLength();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public int getTextStart() {
        return this.fStreamReader.getTextStart();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getVersion() {
        return this.fStreamReader.getVersion();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean hasName() {
        return this.fStreamReader.hasName();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean hasText() {
        return this.fStreamReader.hasText();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean isAttributeSpecified(int i) {
        return this.fStreamReader.isAttributeSpecified(i);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean isCharacters() {
        return this.fStreamReader.isCharacters();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean isEndElement() {
        return this.fStreamReader.isEndElement();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean isStandalone() {
        return this.fStreamReader.isStandalone();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean isStartElement() {
        return this.fStreamReader.isStartElement();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean isWhiteSpace() {
        return this.fStreamReader.isWhiteSpace();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public void require(int i, String str, String str2) {
        this.fStreamReader.require(i, str, str2);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean standaloneSet() {
        return this.fStreamReader.standaloneSet();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getAttributeLocalName(int i) {
        return this.fStreamReader.getAttributeLocalName(i);
    }
}

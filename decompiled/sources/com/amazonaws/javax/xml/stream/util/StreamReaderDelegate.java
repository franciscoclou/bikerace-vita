package com.amazonaws.javax.xml.stream.util;

import com.amazonaws.javax.xml.a.a;
import com.amazonaws.javax.xml.a.b;
import com.amazonaws.javax.xml.stream.Location;
import com.amazonaws.javax.xml.stream.XMLStreamReader;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class StreamReaderDelegate implements XMLStreamReader {
    private XMLStreamReader reader;

    public StreamReaderDelegate() {
    }

    public StreamReaderDelegate(XMLStreamReader xMLStreamReader) {
        this.reader = xMLStreamReader;
    }

    public void setParent(XMLStreamReader xMLStreamReader) {
        this.reader = xMLStreamReader;
    }

    public XMLStreamReader getParent() {
        return this.reader;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public int next() {
        return this.reader.next();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public int nextTag() {
        return this.reader.nextTag();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getElementText() {
        return this.reader.getElementText();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public void require(int i, String str, String str2) {
        this.reader.require(i, str, str2);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean hasNext() {
        return this.reader.hasNext();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public void close() {
        this.reader.close();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getNamespaceURI(String str) {
        return this.reader.getNamespaceURI(str);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public a getNamespaceContext() {
        return this.reader.getNamespaceContext();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean isStartElement() {
        return this.reader.isStartElement();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean isEndElement() {
        return this.reader.isEndElement();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean isCharacters() {
        return this.reader.isCharacters();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean isWhiteSpace() {
        return this.reader.isWhiteSpace();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getAttributeValue(String str, String str2) {
        return this.reader.getAttributeValue(str, str2);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public int getAttributeCount() {
        return this.reader.getAttributeCount();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public b getAttributeName(int i) {
        return this.reader.getAttributeName(i);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getAttributePrefix(int i) {
        return this.reader.getAttributePrefix(i);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getAttributeNamespace(int i) {
        return this.reader.getAttributeNamespace(i);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getAttributeLocalName(int i) {
        return this.reader.getAttributeLocalName(i);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getAttributeType(int i) {
        return this.reader.getAttributeType(i);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getAttributeValue(int i) {
        return this.reader.getAttributeValue(i);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean isAttributeSpecified(int i) {
        return this.reader.isAttributeSpecified(i);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public int getNamespaceCount() {
        return this.reader.getNamespaceCount();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getNamespacePrefix(int i) {
        return this.reader.getNamespacePrefix(i);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getNamespaceURI(int i) {
        return this.reader.getNamespaceURI(i);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public int getEventType() {
        return this.reader.getEventType();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getText() {
        return this.reader.getText();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public int getTextCharacters(int i, char[] cArr, int i2, int i3) {
        return this.reader.getTextCharacters(i, cArr, i2, i3);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public char[] getTextCharacters() {
        return this.reader.getTextCharacters();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public int getTextStart() {
        return this.reader.getTextStart();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public int getTextLength() {
        return this.reader.getTextLength();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getEncoding() {
        return this.reader.getEncoding();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean hasText() {
        return this.reader.hasText();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public Location getLocation() {
        return this.reader.getLocation();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public b getName() {
        return this.reader.getName();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getLocalName() {
        return this.reader.getLocalName();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean hasName() {
        return this.reader.hasName();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getNamespaceURI() {
        return this.reader.getNamespaceURI();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getPrefix() {
        return this.reader.getPrefix();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getVersion() {
        return this.reader.getVersion();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean isStandalone() {
        return this.reader.isStandalone();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean standaloneSet() {
        return this.reader.standaloneSet();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getCharacterEncodingScheme() {
        return this.reader.getCharacterEncodingScheme();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getPITarget() {
        return this.reader.getPITarget();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getPIData() {
        return this.reader.getPIData();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public Object getProperty(String str) {
        return this.reader.getProperty(str);
    }
}

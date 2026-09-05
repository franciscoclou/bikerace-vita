package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLInputSource;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class StaxXMLInputSource {
    XMLEventReader fEventReader;
    XMLInputSource fInputSource;
    XMLStreamReader fStreamReader;

    public StaxXMLInputSource(XMLStreamReader xMLStreamReader) {
        this.fStreamReader = xMLStreamReader;
    }

    public StaxXMLInputSource(XMLEventReader xMLEventReader) {
        this.fEventReader = xMLEventReader;
    }

    public StaxXMLInputSource(XMLInputSource xMLInputSource) {
        this.fInputSource = xMLInputSource;
    }

    public XMLStreamReader getXMLStreamReader() {
        return this.fStreamReader;
    }

    public XMLEventReader getXMLEventReader() {
        return this.fEventReader;
    }

    public XMLInputSource getXMLInputSource() {
        return this.fInputSource;
    }

    public boolean hasXMLStreamOrXMLEventReader() {
        return (this.fStreamReader == null && this.fEventReader == null) ? false : true;
    }
}

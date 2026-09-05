package com.amazonaws.javax.xml.stream.events;

import com.amazonaws.javax.xml.stream.Location;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class StartDocumentEvent extends DummyEvent implements StartDocument {
    protected String fEncodingScheam;
    private boolean fEncodingSchemeSet;
    protected boolean fStandalone;
    private boolean fStandaloneSet;
    protected String fSystemId;
    protected String fVersion;
    private boolean nestedCall;

    public StartDocumentEvent() {
        this.fEncodingSchemeSet = false;
        this.fStandaloneSet = false;
        this.nestedCall = false;
        init(XMLStreamWriterImpl.UTF_8, XMLStreamWriterImpl.DEFAULT_XML_VERSION, true, null);
    }

    public StartDocumentEvent(String str) {
        this.fEncodingSchemeSet = false;
        this.fStandaloneSet = false;
        this.nestedCall = false;
        init(str, XMLStreamWriterImpl.DEFAULT_XML_VERSION, true, null);
    }

    public StartDocumentEvent(String str, String str2) {
        this.fEncodingSchemeSet = false;
        this.fStandaloneSet = false;
        this.nestedCall = false;
        init(str, str2, true, null);
    }

    public StartDocumentEvent(String str, String str2, boolean z) {
        this.fEncodingSchemeSet = false;
        this.fStandaloneSet = false;
        this.nestedCall = false;
        this.fStandaloneSet = true;
        init(str, str2, z, null);
    }

    public StartDocumentEvent(String str, String str2, boolean z, Location location) {
        this.fEncodingSchemeSet = false;
        this.fStandaloneSet = false;
        this.nestedCall = false;
        this.fStandaloneSet = true;
        init(str, str2, z, location);
    }

    protected void init(String str, String str2, boolean z, Location location) {
        setEventType(7);
        this.fEncodingScheam = str;
        this.fVersion = str2;
        this.fStandalone = z;
        if (str != null && !str.equals("")) {
            this.fEncodingSchemeSet = true;
        } else {
            this.fEncodingSchemeSet = false;
            this.fEncodingScheam = XMLStreamWriterImpl.UTF_8;
        }
        this.fLocation = location;
    }

    @Override // com.amazonaws.javax.xml.stream.events.StartDocument
    public String getSystemId() {
        return this.fLocation == null ? "" : this.fLocation.getSystemId();
    }

    @Override // com.amazonaws.javax.xml.stream.events.StartDocument
    public String getCharacterEncodingScheme() {
        return this.fEncodingScheam;
    }

    @Override // com.amazonaws.javax.xml.stream.events.StartDocument
    public boolean isStandalone() {
        return this.fStandalone;
    }

    @Override // com.amazonaws.javax.xml.stream.events.StartDocument
    public String getVersion() {
        return this.fVersion;
    }

    public void setStandalone(boolean z) {
        this.fStandaloneSet = true;
        this.fStandalone = z;
    }

    public void setStandalone(String str) {
        this.fStandaloneSet = true;
        if (str == null) {
            this.fStandalone = true;
        } else if (str.equals("yes")) {
            this.fStandalone = true;
        } else {
            this.fStandalone = false;
        }
    }

    @Override // com.amazonaws.javax.xml.stream.events.StartDocument
    public boolean encodingSet() {
        return this.fEncodingSchemeSet;
    }

    @Override // com.amazonaws.javax.xml.stream.events.StartDocument
    public boolean standaloneSet() {
        return this.fStandaloneSet;
    }

    public void setEncoding(String str) {
        this.fEncodingScheam = str;
    }

    void setDeclaredEncoding(boolean z) {
        this.fEncodingSchemeSet = z;
    }

    public void setVersion(String str) {
        this.fVersion = str;
    }

    void clear() {
        this.fEncodingScheam = XMLStreamWriterImpl.UTF_8;
        this.fStandalone = true;
        this.fVersion = XMLStreamWriterImpl.DEFAULT_XML_VERSION;
        this.fEncodingSchemeSet = false;
        this.fStandaloneSet = false;
    }

    public String toString() {
        String string = new StringBuffer().append(new StringBuffer().append("<?xml version=\"").append(this.fVersion).append("\"").toString()).append(" encoding='").append(this.fEncodingScheam).append("'").toString();
        if (this.fStandaloneSet) {
            if (this.fStandalone) {
                return new StringBuffer().append(string).append(" standalone='yes'?>").toString();
            }
            return new StringBuffer().append(string).append(" standalone='no'?>").toString();
        }
        return new StringBuffer().append(string).append("?>").toString();
    }

    @Override // com.amazonaws.javax.xml.stream.events.DummyEvent, com.amazonaws.javax.xml.stream.events.XMLEvent
    public boolean isStartDocument() {
        return true;
    }

    @Override // com.amazonaws.javax.xml.stream.events.DummyEvent
    protected void writeAsEncodedUnicodeEx(Writer writer) throws IOException {
        writer.write(toString());
    }
}

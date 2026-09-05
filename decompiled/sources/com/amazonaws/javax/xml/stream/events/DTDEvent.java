package com.amazonaws.javax.xml.stream.events;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class DTDEvent extends DummyEvent implements DTD {
    private String fDoctypeDeclaration;
    private List fEntities;
    private List fNotations;

    public DTDEvent() {
        init();
    }

    public DTDEvent(String str) {
        init();
        this.fDoctypeDeclaration = str;
    }

    public void setDocumentTypeDeclaration(String str) {
        this.fDoctypeDeclaration = str;
    }

    @Override // com.amazonaws.javax.xml.stream.events.DTD
    public String getDocumentTypeDeclaration() {
        return this.fDoctypeDeclaration;
    }

    public void setEntities(List list) {
        this.fEntities = list;
    }

    @Override // com.amazonaws.javax.xml.stream.events.DTD
    public List getEntities() {
        return this.fEntities;
    }

    public void setNotations(List list) {
        this.fNotations = list;
    }

    @Override // com.amazonaws.javax.xml.stream.events.DTD
    public List getNotations() {
        return this.fNotations;
    }

    @Override // com.amazonaws.javax.xml.stream.events.DTD
    public Object getProcessedDTD() {
        return null;
    }

    protected void init() {
        setEventType(11);
    }

    public String toString() {
        return this.fDoctypeDeclaration;
    }

    @Override // com.amazonaws.javax.xml.stream.events.DummyEvent
    protected void writeAsEncodedUnicodeEx(Writer writer) throws IOException {
        writer.write(this.fDoctypeDeclaration);
    }
}

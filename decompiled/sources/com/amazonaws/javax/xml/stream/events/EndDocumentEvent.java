package com.amazonaws.javax.xml.stream.events;

import java.io.Writer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class EndDocumentEvent extends DummyEvent implements EndDocument {
    public EndDocumentEvent() {
        init();
    }

    protected void init() {
        setEventType(8);
    }

    public String toString() {
        return "ENDDOCUMENT";
    }

    @Override // com.amazonaws.javax.xml.stream.events.DummyEvent
    protected void writeAsEncodedUnicodeEx(Writer writer) {
    }
}

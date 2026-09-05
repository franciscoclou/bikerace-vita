package com.amazonaws.javax.xml.stream.events;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class CommentEvent extends DummyEvent implements Comment {
    private String fText;

    public CommentEvent() {
        init();
    }

    public CommentEvent(String str) {
        init();
        this.fText = str;
    }

    protected void init() {
        setEventType(5);
    }

    public String toString() {
        return new StringBuffer().append(XMLStreamWriterImpl.START_COMMENT).append(getText()).append(XMLStreamWriterImpl.END_COMMENT).toString();
    }

    @Override // com.amazonaws.javax.xml.stream.events.Comment
    public String getText() {
        return this.fText;
    }

    @Override // com.amazonaws.javax.xml.stream.events.DummyEvent
    protected void writeAsEncodedUnicodeEx(Writer writer) throws IOException {
        writer.write(new StringBuffer().append(XMLStreamWriterImpl.START_COMMENT).append(getText()).append(XMLStreamWriterImpl.END_COMMENT).toString());
    }
}

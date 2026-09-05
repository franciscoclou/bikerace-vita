package com.amazonaws.javax.xml.stream.events;

import com.amazonaws.javax.xml.a.b;
import com.amazonaws.javax.xml.stream.Location;
import com.amazonaws.javax.xml.stream.XMLStreamException2;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class DummyEvent implements XMLEvent {
    private int fEventType;
    protected Location fLocation = null;

    protected abstract void writeAsEncodedUnicodeEx(Writer writer);

    public DummyEvent() {
    }

    public DummyEvent(int i) {
        this.fEventType = i;
    }

    @Override // com.amazonaws.javax.xml.stream.events.XMLEvent
    public int getEventType() {
        return this.fEventType;
    }

    protected void setEventType(int i) {
        this.fEventType = i;
    }

    @Override // com.amazonaws.javax.xml.stream.events.XMLEvent
    public boolean isStartElement() {
        return this.fEventType == 1;
    }

    @Override // com.amazonaws.javax.xml.stream.events.XMLEvent
    public boolean isEndElement() {
        return this.fEventType == 2;
    }

    @Override // com.amazonaws.javax.xml.stream.events.XMLEvent
    public boolean isEntityReference() {
        return this.fEventType == 9;
    }

    @Override // com.amazonaws.javax.xml.stream.events.XMLEvent
    public boolean isProcessingInstruction() {
        return this.fEventType == 3;
    }

    public boolean isCharacterData() {
        return this.fEventType == 4;
    }

    @Override // com.amazonaws.javax.xml.stream.events.XMLEvent
    public boolean isStartDocument() {
        return this.fEventType == 7;
    }

    @Override // com.amazonaws.javax.xml.stream.events.XMLEvent
    public boolean isEndDocument() {
        return this.fEventType == 8;
    }

    @Override // com.amazonaws.javax.xml.stream.events.XMLEvent
    public Location getLocation() {
        return this.fLocation;
    }

    void setLocation(Location location) {
        this.fLocation = location;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazonaws.javax.xml.stream.events.XMLEvent
    public Characters asCharacters() {
        return (Characters) this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazonaws.javax.xml.stream.events.XMLEvent
    public EndElement asEndElement() {
        return (EndElement) this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazonaws.javax.xml.stream.events.XMLEvent
    public StartElement asStartElement() {
        return (StartElement) this;
    }

    @Override // com.amazonaws.javax.xml.stream.events.XMLEvent
    public b getSchemaType() {
        return null;
    }

    @Override // com.amazonaws.javax.xml.stream.events.XMLEvent
    public boolean isAttribute() {
        return this.fEventType == 10;
    }

    @Override // com.amazonaws.javax.xml.stream.events.XMLEvent
    public boolean isCharacters() {
        return this.fEventType == 4;
    }

    @Override // com.amazonaws.javax.xml.stream.events.XMLEvent
    public boolean isNamespace() {
        return this.fEventType == 13;
    }

    @Override // com.amazonaws.javax.xml.stream.events.XMLEvent
    public void writeAsEncodedUnicode(Writer writer) throws XMLStreamException2 {
        try {
            writeAsEncodedUnicodeEx(writer);
        } catch (IOException e) {
            throw new XMLStreamException2(e);
        }
    }

    protected void charEncode(Writer writer, String str) throws IOException {
        int i = 0;
        if (str != null && str != "") {
            int length = str.length();
            for (int i2 = 0; i2 < length; i2++) {
                switch (str.charAt(i2)) {
                    case '\"':
                        writer.write(str, i, i2 - i);
                        writer.write("&quot;");
                        i = i2 + 1;
                        break;
                    case '&':
                        writer.write(str, i, i2 - i);
                        writer.write("&amp;");
                        i = i2 + 1;
                        break;
                    case '<':
                        writer.write(str, i, i2 - i);
                        writer.write("&lt;");
                        i = i2 + 1;
                        break;
                    case '>':
                        writer.write(str, i, i2 - i);
                        writer.write("&gt;");
                        i = i2 + 1;
                        break;
                }
            }
            writer.write(str, i, length - i);
        }
    }
}

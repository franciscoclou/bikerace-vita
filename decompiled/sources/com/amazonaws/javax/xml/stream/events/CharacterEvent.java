package com.amazonaws.javax.xml.stream.events;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class CharacterEvent extends DummyEvent implements Characters {
    private boolean fCheckIfSpaceNeeded;
    private String fData;
    private boolean fIsCData;
    private boolean fIsIgnorableWhitespace;
    private boolean fIsSpace;

    public CharacterEvent() {
        this.fIsSpace = false;
        this.fCheckIfSpaceNeeded = true;
        this.fIsCData = false;
        init();
    }

    public CharacterEvent(String str) {
        this.fIsSpace = false;
        this.fCheckIfSpaceNeeded = true;
        this.fIsCData = false;
        init();
        this.fData = str;
    }

    public CharacterEvent(String str, boolean z) {
        this.fIsSpace = false;
        this.fCheckIfSpaceNeeded = true;
        init();
        this.fData = str;
        this.fIsCData = z;
    }

    public CharacterEvent(String str, boolean z, boolean z2) {
        this.fIsSpace = false;
        this.fCheckIfSpaceNeeded = true;
        init();
        this.fData = str;
        this.fIsCData = z;
        this.fIsIgnorableWhitespace = z2;
    }

    protected void init() {
        setEventType(4);
    }

    @Override // com.amazonaws.javax.xml.stream.events.Characters
    public String getData() {
        return this.fData;
    }

    public void setData(String str) {
        this.fData = str;
        this.fCheckIfSpaceNeeded = true;
    }

    @Override // com.amazonaws.javax.xml.stream.events.Characters
    public boolean isCData() {
        return this.fIsCData;
    }

    public String toString() {
        return this.fIsCData ? new StringBuffer().append(XMLStreamWriterImpl.START_CDATA).append(getData()).append(XMLStreamWriterImpl.END_CDATA).toString() : this.fData;
    }

    @Override // com.amazonaws.javax.xml.stream.events.DummyEvent
    protected void writeAsEncodedUnicodeEx(Writer writer) throws IOException {
        if (this.fIsCData) {
            writer.write(new StringBuffer().append(XMLStreamWriterImpl.START_CDATA).append(getData()).append(XMLStreamWriterImpl.END_CDATA).toString());
        } else {
            charEncode(writer, this.fData);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.events.Characters
    public boolean isIgnorableWhiteSpace() {
        return this.fIsIgnorableWhitespace;
    }

    @Override // com.amazonaws.javax.xml.stream.events.Characters
    public boolean isWhiteSpace() {
        if (this.fCheckIfSpaceNeeded) {
            checkWhiteSpace();
            this.fCheckIfSpaceNeeded = false;
        }
        return this.fIsSpace;
    }

    private void checkWhiteSpace() {
        if (this.fData != null && this.fData.length() > 0) {
            this.fIsSpace = true;
            for (int i = 0; i < this.fData.length(); i++) {
                if (!XMLChar.isSpace(this.fData.charAt(i))) {
                    this.fIsSpace = false;
                    return;
                }
            }
        }
    }
}

package com.amazonaws.javax.xml.stream.events;

import com.amazonaws.javax.xml.stream.Location;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class LocationImpl implements Location {
    int charOffset;
    int colNo;
    int lineNo;
    String publicId;
    String systemId;

    LocationImpl(Location location) {
        this.systemId = location.getSystemId();
        this.publicId = location.getPublicId();
        this.lineNo = location.getLineNumber();
        this.colNo = location.getColumnNumber();
        this.charOffset = location.getCharacterOffset();
    }

    @Override // com.amazonaws.javax.xml.stream.Location
    public int getCharacterOffset() {
        return this.charOffset;
    }

    @Override // com.amazonaws.javax.xml.stream.Location
    public int getColumnNumber() {
        return this.colNo;
    }

    @Override // com.amazonaws.javax.xml.stream.Location
    public int getLineNumber() {
        return this.lineNo;
    }

    @Override // com.amazonaws.javax.xml.stream.Location
    public String getPublicId() {
        return this.publicId;
    }

    @Override // com.amazonaws.javax.xml.stream.Location
    public String getSystemId() {
        return this.systemId;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(new StringBuffer().append("Line number = ").append(getLineNumber()).toString());
        stringBuffer.append("\n");
        stringBuffer.append(new StringBuffer().append("Column number = ").append(getColumnNumber()).toString());
        stringBuffer.append("\n");
        stringBuffer.append(new StringBuffer().append("System Id = ").append(getSystemId()).toString());
        stringBuffer.append("\n");
        stringBuffer.append(new StringBuffer().append("Public Id = ").append(getPublicId()).toString());
        stringBuffer.append("\n");
        stringBuffer.append(new StringBuffer().append("CharacterOffset = ").append(getCharacterOffset()).toString());
        stringBuffer.append("\n");
        return stringBuffer.toString();
    }
}

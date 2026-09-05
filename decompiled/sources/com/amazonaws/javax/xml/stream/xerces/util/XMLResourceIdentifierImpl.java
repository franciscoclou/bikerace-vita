package com.amazonaws.javax.xml.stream.xerces.util;

import com.amazonaws.javax.xml.stream.xerces.xni.XMLResourceIdentifier;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class XMLResourceIdentifierImpl implements XMLResourceIdentifier {
    protected String fBaseSystemId;
    protected String fExpandedSystemId;
    protected String fLiteralSystemId;
    protected String fPublicId;

    public XMLResourceIdentifierImpl() {
    }

    public XMLResourceIdentifierImpl(String str, String str2, String str3, String str4) {
        setValues(str, str2, str3, str4);
    }

    public void setValues(String str, String str2, String str3, String str4) {
        this.fPublicId = str;
        this.fLiteralSystemId = str2;
        this.fBaseSystemId = str3;
        this.fExpandedSystemId = str4;
    }

    public void clear() {
        this.fPublicId = null;
        this.fLiteralSystemId = null;
        this.fBaseSystemId = null;
        this.fExpandedSystemId = null;
    }

    public void setPublicId(String str) {
        this.fPublicId = str;
    }

    public void setLiteralSystemId(String str) {
        this.fLiteralSystemId = str;
    }

    public void setBaseSystemId(String str) {
        this.fBaseSystemId = str;
    }

    public void setExpandedSystemId(String str) {
        this.fExpandedSystemId = str;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLResourceIdentifier
    public String getPublicId() {
        return this.fPublicId;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLResourceIdentifier
    public String getLiteralSystemId() {
        return this.fLiteralSystemId;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLResourceIdentifier
    public String getBaseSystemId() {
        return this.fBaseSystemId;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLResourceIdentifier
    public String getExpandedSystemId() {
        return this.fExpandedSystemId;
    }

    public int hashCode() {
        int iHashCode = this.fPublicId != null ? 0 + this.fPublicId.hashCode() : 0;
        if (this.fLiteralSystemId != null) {
            iHashCode += this.fLiteralSystemId.hashCode();
        }
        if (this.fBaseSystemId != null) {
            iHashCode += this.fBaseSystemId.hashCode();
        }
        if (this.fExpandedSystemId != null) {
            return iHashCode + this.fExpandedSystemId.hashCode();
        }
        return iHashCode;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.fPublicId != null) {
            stringBuffer.append(this.fPublicId);
        }
        stringBuffer.append(':');
        if (this.fLiteralSystemId != null) {
            stringBuffer.append(this.fLiteralSystemId);
        }
        stringBuffer.append(':');
        if (this.fBaseSystemId != null) {
            stringBuffer.append(this.fBaseSystemId);
        }
        stringBuffer.append(':');
        if (this.fExpandedSystemId != null) {
            stringBuffer.append(this.fExpandedSystemId);
        }
        return stringBuffer.toString();
    }
}

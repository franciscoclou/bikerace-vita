package com.amazonaws.javax.xml.stream.xerces.xni.parser;

import com.amazonaws.javax.xml.stream.xerces.xni.XNIException;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class XMLConfigurationException extends XNIException {
    public static final short NOT_RECOGNIZED = 0;
    public static final short NOT_SUPPORTED = 1;
    protected String fIdentifier;
    protected short fType;

    public XMLConfigurationException(short s, String str) {
        super(str);
        this.fType = s;
        this.fIdentifier = str;
    }

    public XMLConfigurationException(short s, String str, String str2) {
        super(str2);
        this.fType = s;
        this.fIdentifier = str;
    }

    public short getType() {
        return this.fType;
    }

    public String getIdentifier() {
        return this.fIdentifier;
    }
}

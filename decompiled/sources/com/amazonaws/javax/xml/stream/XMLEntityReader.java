package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.stream.xerces.util.XMLStringBuffer;
import com.amazonaws.javax.xml.stream.xerces.xni.QName;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLLocator;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLString;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class XMLEntityReader implements XMLLocator {
    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLLocator
    public abstract int getCharacterOffset();

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLLocator
    public abstract String getEncoding();

    public abstract String getVersion();

    public abstract boolean isExternal();

    public abstract int peekChar();

    public abstract void registerListener(XMLBufferListener xMLBufferListener);

    public abstract int scanChar();

    public abstract int scanContent(XMLString xMLString);

    public abstract boolean scanData(String str, XMLStringBuffer xMLStringBuffer);

    public abstract int scanLiteral(int i, XMLString xMLString);

    public abstract String scanName();

    public abstract String scanNmtoken();

    public abstract boolean scanQName(QName qName);

    public abstract void setEncoding(String str);

    public abstract void setVersion(String str);

    public abstract boolean skipChar(int i);

    public abstract boolean skipSpaces();

    public abstract boolean skipString(String str);
}

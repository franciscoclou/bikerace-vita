package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.a.a;
import com.amazonaws.javax.xml.a.b;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface XMLStreamReader extends XMLStreamConstants {
    void close();

    int getAttributeCount();

    String getAttributeLocalName(int i);

    b getAttributeName(int i);

    String getAttributeNamespace(int i);

    String getAttributePrefix(int i);

    String getAttributeType(int i);

    String getAttributeValue(int i);

    String getAttributeValue(String str, String str2);

    String getCharacterEncodingScheme();

    String getElementText();

    String getEncoding();

    int getEventType();

    String getLocalName();

    Location getLocation();

    b getName();

    a getNamespaceContext();

    int getNamespaceCount();

    String getNamespacePrefix(int i);

    String getNamespaceURI();

    String getNamespaceURI(int i);

    String getNamespaceURI(String str);

    String getPIData();

    String getPITarget();

    String getPrefix();

    Object getProperty(String str);

    String getText();

    int getTextCharacters(int i, char[] cArr, int i2, int i3);

    char[] getTextCharacters();

    int getTextLength();

    int getTextStart();

    String getVersion();

    boolean hasName();

    boolean hasNext();

    boolean hasText();

    boolean isAttributeSpecified(int i);

    boolean isCharacters();

    boolean isEndElement();

    boolean isStandalone();

    boolean isStartElement();

    boolean isWhiteSpace();

    int next();

    int nextTag();

    void require(int i, String str, String str2);

    boolean standaloneSet();
}

package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.a.a;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface XMLStreamWriter {
    void close();

    void flush();

    a getNamespaceContext();

    String getPrefix(String str);

    Object getProperty(String str);

    void setDefaultNamespace(String str);

    void setNamespaceContext(a aVar);

    void setPrefix(String str, String str2);

    void writeAttribute(String str, String str2);

    void writeAttribute(String str, String str2, String str3);

    void writeAttribute(String str, String str2, String str3, String str4);

    void writeCData(String str);

    void writeCharacters(String str);

    void writeCharacters(char[] cArr, int i, int i2);

    void writeComment(String str);

    void writeDTD(String str);

    void writeDefaultNamespace(String str);

    void writeEmptyElement(String str);

    void writeEmptyElement(String str, String str2);

    void writeEmptyElement(String str, String str2, String str3);

    void writeEndDocument();

    void writeEndElement();

    void writeEntityRef(String str);

    void writeNamespace(String str, String str2);

    void writeProcessingInstruction(String str);

    void writeProcessingInstruction(String str, String str2);

    void writeStartDocument();

    void writeStartDocument(String str);

    void writeStartDocument(String str, String str2);

    void writeStartElement(String str);

    void writeStartElement(String str, String str2);

    void writeStartElement(String str, String str2, String str3);
}

package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.a.a;
import com.amazonaws.javax.xml.stream.events.XMLEvent;
import com.amazonaws.javax.xml.stream.util.XMLEventConsumer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface XMLEventWriter extends XMLEventConsumer {
    void add(XMLEventReader xMLEventReader);

    @Override // com.amazonaws.javax.xml.stream.util.XMLEventConsumer
    void add(XMLEvent xMLEvent);

    void close();

    void flush();

    a getNamespaceContext();

    String getPrefix(String str);

    void setDefaultNamespace(String str);

    void setNamespaceContext(a aVar);

    void setPrefix(String str, String str2);
}

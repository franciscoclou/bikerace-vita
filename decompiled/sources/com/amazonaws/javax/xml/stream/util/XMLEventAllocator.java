package com.amazonaws.javax.xml.stream.util;

import com.amazonaws.javax.xml.stream.XMLStreamReader;
import com.amazonaws.javax.xml.stream.events.XMLEvent;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface XMLEventAllocator {
    XMLEvent allocate(XMLStreamReader xMLStreamReader);

    void allocate(XMLStreamReader xMLStreamReader, XMLEventConsumer xMLEventConsumer);

    XMLEventAllocator newInstance();
}

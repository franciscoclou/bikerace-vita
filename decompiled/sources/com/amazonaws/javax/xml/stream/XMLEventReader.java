package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.stream.events.XMLEvent;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface XMLEventReader extends Iterator {
    void close();

    String getElementText();

    Object getProperty(String str);

    @Override // java.util.Iterator
    boolean hasNext();

    XMLEvent nextEvent();

    XMLEvent nextTag();

    XMLEvent peek();
}

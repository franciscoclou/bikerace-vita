package com.amazonaws.javax.xml.stream.events;

import com.amazonaws.javax.xml.a.a;
import com.amazonaws.javax.xml.a.b;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface StartElement extends XMLEvent {
    Attribute getAttributeByName(b bVar);

    Iterator getAttributes();

    b getName();

    a getNamespaceContext();

    String getNamespaceURI(String str);

    Iterator getNamespaces();
}

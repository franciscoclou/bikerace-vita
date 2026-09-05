package com.amazonaws.javax.xml.stream.xerces.xni;

import java.util.Enumeration;
import java.util.Vector;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface NamespaceContext {
    public static final String XML_URI = "http://www.w3.org/XML/1998/namespace".intern();
    public static final String XMLNS_URI = "http://www.w3.org/2000/xmlns/".intern();

    boolean declarePrefix(String str, String str2);

    Enumeration getAllPrefixes();

    String getDeclaredPrefixAt(int i);

    int getDeclaredPrefixCount();

    String getPrefix(String str);

    Vector getPrefixes(String str);

    String getURI(String str);

    void popContext();

    void pushContext();

    void reset();
}

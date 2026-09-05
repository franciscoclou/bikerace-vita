package com.amazonaws.javax.xml.stream.xerces.xni.parser;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface XMLDocumentScanner extends XMLDocumentSource {
    int next();

    boolean scanDocument(boolean z);

    void setInputSource(XMLInputSource xMLInputSource);
}

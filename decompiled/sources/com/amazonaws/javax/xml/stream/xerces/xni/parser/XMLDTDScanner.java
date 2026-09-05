package com.amazonaws.javax.xml.stream.xerces.xni.parser;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface XMLDTDScanner extends XMLDTDContentModelSource, XMLDTDSource {
    boolean scanDTDExternalSubset(boolean z);

    boolean scanDTDInternalSubset(boolean z, boolean z2, boolean z3);

    void setInputSource(XMLInputSource xMLInputSource);
}

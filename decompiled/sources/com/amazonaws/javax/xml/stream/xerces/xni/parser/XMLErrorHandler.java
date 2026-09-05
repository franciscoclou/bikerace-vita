package com.amazonaws.javax.xml.stream.xerces.xni.parser;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface XMLErrorHandler {
    void error(String str, String str2, XMLParseException xMLParseException);

    void fatalError(String str, String str2, XMLParseException xMLParseException);

    void warning(String str, String str2, XMLParseException xMLParseException);
}

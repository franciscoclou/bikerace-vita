package com.amazonaws.javax.xml.stream.xerces.xni.parser;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface XMLPullParserConfiguration extends XMLParserConfiguration {
    void cleanup();

    boolean parse(boolean z);

    void setInputSource(XMLInputSource xMLInputSource);
}

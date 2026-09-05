package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.stream.xerces.xni.XMLResourceIdentifier;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface XMLEntityHandler {
    void endEntity(String str);

    void startEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2);
}

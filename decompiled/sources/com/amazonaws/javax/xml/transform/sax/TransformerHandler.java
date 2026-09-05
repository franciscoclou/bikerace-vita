package com.amazonaws.javax.xml.transform.sax;

import com.amazonaws.javax.xml.transform.d;
import com.amazonaws.javax.xml.transform.i;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.ext.LexicalHandler;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface TransformerHandler extends ContentHandler, DTDHandler, LexicalHandler {
    String getSystemId();

    i getTransformer();

    void setResult(d dVar);

    void setSystemId(String str);
}

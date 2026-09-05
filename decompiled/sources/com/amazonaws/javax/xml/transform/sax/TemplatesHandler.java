package com.amazonaws.javax.xml.transform.sax;

import com.amazonaws.javax.xml.transform.h;
import org.xml.sax.ContentHandler;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface TemplatesHandler extends ContentHandler {
    String getSystemId();

    h getTemplates();

    void setSystemId(String str);
}

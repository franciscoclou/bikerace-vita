package com.amazonaws.javax.xml.transform.sax;

import com.amazonaws.javax.xml.transform.f;
import com.amazonaws.javax.xml.transform.h;
import com.amazonaws.javax.xml.transform.l;
import org.xml.sax.XMLFilter;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class SAXTransformerFactory extends l {
    public static final String FEATURE = "http://javax.xml.transform.sax.SAXTransformerFactory/feature";
    public static final String FEATURE_XMLFILTER = "http://javax.xml.transform.sax.SAXTransformerFactory/feature/xmlfilter";

    public abstract TemplatesHandler newTemplatesHandler();

    public abstract TransformerHandler newTransformerHandler();

    public abstract TransformerHandler newTransformerHandler(f fVar);

    public abstract TransformerHandler newTransformerHandler(h hVar);

    public abstract XMLFilter newXMLFilter(f fVar);

    public abstract XMLFilter newXMLFilter(h hVar);

    protected SAXTransformerFactory() {
    }
}

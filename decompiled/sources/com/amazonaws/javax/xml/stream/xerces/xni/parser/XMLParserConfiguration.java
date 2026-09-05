package com.amazonaws.javax.xml.stream.xerces.xni.parser;

import com.amazonaws.javax.xml.stream.xerces.xni.XMLDTDContentModelHandler;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLDTDHandler;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLDocumentHandler;
import java.util.Locale;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface XMLParserConfiguration extends XMLComponentManager {
    void addRecognizedFeatures(String[] strArr);

    void addRecognizedProperties(String[] strArr);

    XMLDTDContentModelHandler getDTDContentModelHandler();

    XMLDTDHandler getDTDHandler();

    XMLDocumentHandler getDocumentHandler();

    XMLEntityResolver getEntityResolver();

    XMLErrorHandler getErrorHandler();

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponentManager
    boolean getFeature(String str);

    Locale getLocale();

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponentManager
    Object getProperty(String str);

    void parse(XMLInputSource xMLInputSource);

    void setDTDContentModelHandler(XMLDTDContentModelHandler xMLDTDContentModelHandler);

    void setDTDHandler(XMLDTDHandler xMLDTDHandler);

    void setDocumentHandler(XMLDocumentHandler xMLDocumentHandler);

    void setEntityResolver(XMLEntityResolver xMLEntityResolver);

    void setErrorHandler(XMLErrorHandler xMLErrorHandler);

    void setFeature(String str, boolean z);

    void setLocale(Locale locale);

    void setProperty(String str, Object obj);
}

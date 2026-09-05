package com.amazonaws.javax.xml.stream.xerces.xni;

import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLDocumentSource;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface XMLDocumentHandler {
    void characters(XMLString xMLString, Augmentations augmentations);

    void comment(XMLString xMLString, Augmentations augmentations);

    void doctypeDecl(String str, String str2, String str3, Augmentations augmentations);

    void emptyElement(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations);

    void endCDATA(Augmentations augmentations);

    void endDocument(Augmentations augmentations);

    void endElement(QName qName, Augmentations augmentations);

    void endGeneralEntity(String str, Augmentations augmentations);

    void endPrefixMapping(String str, Augmentations augmentations);

    XMLDocumentSource getDocumentSource();

    void ignorableWhitespace(XMLString xMLString, Augmentations augmentations);

    void processingInstruction(String str, XMLString xMLString, Augmentations augmentations);

    void setDocumentSource(XMLDocumentSource xMLDocumentSource);

    void startCDATA(Augmentations augmentations);

    void startDocument(XMLLocator xMLLocator, String str, NamespaceContext namespaceContext, Augmentations augmentations);

    void startElement(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations);

    void startGeneralEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations);

    void startPrefixMapping(String str, String str2, Augmentations augmentations);

    void textDecl(String str, String str2, Augmentations augmentations);

    void xmlDecl(String str, String str2, String str3, Augmentations augmentations);
}

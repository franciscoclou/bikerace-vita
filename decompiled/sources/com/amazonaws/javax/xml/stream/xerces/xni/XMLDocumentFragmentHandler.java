package com.amazonaws.javax.xml.stream.xerces.xni;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface XMLDocumentFragmentHandler {
    void characters(XMLString xMLString, Augmentations augmentations);

    void comment(XMLString xMLString, Augmentations augmentations);

    void emptyElement(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations);

    void endCDATA(Augmentations augmentations);

    void endDocumentFragment(Augmentations augmentations);

    void endElement(QName qName, Augmentations augmentations);

    void endGeneralEntity(String str, Augmentations augmentations);

    void endPrefixMapping(String str, Augmentations augmentations);

    void ignorableWhitespace(XMLString xMLString, Augmentations augmentations);

    void processingInstruction(String str, XMLString xMLString, Augmentations augmentations);

    void startCDATA(Augmentations augmentations);

    void startDocumentFragment(XMLLocator xMLLocator, NamespaceContext namespaceContext, Augmentations augmentations);

    void startElement(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations);

    void startGeneralEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations);

    void startPrefixMapping(String str, String str2, Augmentations augmentations);

    void textDecl(String str, String str2, Augmentations augmentations);
}

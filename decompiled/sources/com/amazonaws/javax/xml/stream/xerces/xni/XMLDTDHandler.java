package com.amazonaws.javax.xml.stream.xerces.xni;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface XMLDTDHandler {
    public static final short CONDITIONAL_IGNORE = 1;
    public static final short CONDITIONAL_INCLUDE = 0;

    void attributeDecl(String str, String str2, String str3, String[] strArr, String str4, XMLString xMLString, XMLString xMLString2, Augmentations augmentations);

    void comment(XMLString xMLString, Augmentations augmentations);

    void elementDecl(String str, String str2, Augmentations augmentations);

    void endAttlist(Augmentations augmentations);

    void endConditional(Augmentations augmentations);

    void endDTD(Augmentations augmentations);

    void endExternalSubset(Augmentations augmentations);

    void endParameterEntity(String str, Augmentations augmentations);

    void externalEntityDecl(String str, XMLResourceIdentifier xMLResourceIdentifier, Augmentations augmentations);

    void ignoredCharacters(XMLString xMLString, Augmentations augmentations);

    void internalEntityDecl(String str, XMLString xMLString, XMLString xMLString2, Augmentations augmentations);

    void notationDecl(String str, XMLResourceIdentifier xMLResourceIdentifier, Augmentations augmentations);

    void processingInstruction(String str, XMLString xMLString, Augmentations augmentations);

    void startAttlist(String str, Augmentations augmentations);

    void startConditional(short s, Augmentations augmentations);

    void startDTD(XMLLocator xMLLocator, Augmentations augmentations);

    void startExternalSubset(XMLResourceIdentifier xMLResourceIdentifier, Augmentations augmentations);

    void startParameterEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations);

    void textDecl(String str, String str2, Augmentations augmentations);

    void unparsedEntityDecl(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations);
}

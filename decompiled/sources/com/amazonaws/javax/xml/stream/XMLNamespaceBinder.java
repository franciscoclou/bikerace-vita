package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.stream.xerces.impl.msg.XMLMessageFormatter;
import com.amazonaws.javax.xml.stream.xerces.util.NamespaceSupport;
import com.amazonaws.javax.xml.stream.xerces.util.SymbolTable;
import com.amazonaws.javax.xml.stream.xerces.util.XMLSymbols;
import com.amazonaws.javax.xml.stream.xerces.xni.Augmentations;
import com.amazonaws.javax.xml.stream.xerces.xni.NamespaceContext;
import com.amazonaws.javax.xml.stream.xerces.xni.QName;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLDocumentHandler;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLLocator;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLResourceIdentifier;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLString;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponentManager;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLConfigurationException;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLDocumentFilter;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLDocumentSource;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class XMLNamespaceBinder implements XMLComponent, XMLDocumentFilter {
    private QName fAttributeQName;
    protected XMLDocumentHandler fDocumentHandler;
    protected XMLDocumentSource fDocumentSource;
    protected XMLErrorReporter fErrorReporter;
    private NamespaceContext fNamespaceContext;
    protected NamespaceSupport fNamespaceSupport;
    protected boolean fNamespaces;
    protected boolean fOnlyPassPrefixMappingEvents;
    protected SymbolTable fSymbolTable;
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    private static final String[] RECOGNIZED_FEATURES = {NAMESPACES};
    private static final Boolean[] FEATURE_DEFAULTS = {null};
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    private static final String[] RECOGNIZED_PROPERTIES = {SYMBOL_TABLE, ERROR_REPORTER};
    private static final Object[] PROPERTY_DEFAULTS = {null, null};

    public XMLNamespaceBinder() {
        this(null);
    }

    public XMLNamespaceBinder(NamespaceContext namespaceContext) {
        this.fNamespaceSupport = new NamespaceSupport();
        this.fAttributeQName = new QName();
        this.fNamespaceContext = namespaceContext;
    }

    public NamespaceContext getNamespaceContext() {
        return this.fNamespaceSupport;
    }

    public void setOnlyPassPrefixMappingEvents(boolean z) {
        this.fOnlyPassPrefixMappingEvents = z;
    }

    public boolean getOnlyPassPrefixMappingEvents() {
        return this.fOnlyPassPrefixMappingEvents;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public void reset(XMLComponentManager xMLComponentManager) {
        try {
            this.fNamespaces = xMLComponentManager.getFeature(NAMESPACES);
        } catch (XMLConfigurationException e) {
            this.fNamespaces = true;
        }
        this.fSymbolTable = (SymbolTable) xMLComponentManager.getProperty(SYMBOL_TABLE);
        this.fErrorReporter = (XMLErrorReporter) xMLComponentManager.getProperty(ERROR_REPORTER);
        this.fNamespaceSupport.reset();
        NamespaceContext namespaceContext = this.fNamespaceContext;
        while (namespaceContext != null) {
            int declaredPrefixCount = namespaceContext.getDeclaredPrefixCount();
            for (int i = 0; i < declaredPrefixCount; i++) {
                String declaredPrefixAt = namespaceContext.getDeclaredPrefixAt(i);
                if (this.fNamespaceSupport.getURI(declaredPrefixAt) == null) {
                    this.fNamespaceSupport.declarePrefix(declaredPrefixAt, namespaceContext.getURI(declaredPrefixAt));
                }
            }
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public String[] getRecognizedFeatures() {
        return (String[]) RECOGNIZED_FEATURES.clone();
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public void setFeature(String str, boolean z) {
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public String[] getRecognizedProperties() {
        return (String[]) RECOGNIZED_PROPERTIES.clone();
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public void setProperty(String str, Object obj) {
        if (str.startsWith(Constants.XERCES_PROPERTY_PREFIX)) {
            String strSubstring = str.substring(Constants.XERCES_PROPERTY_PREFIX.length());
            if (strSubstring.equals(Constants.SYMBOL_TABLE_PROPERTY)) {
                this.fSymbolTable = (SymbolTable) obj;
            } else if (strSubstring.equals(Constants.ERROR_REPORTER_PROPERTY)) {
                this.fErrorReporter = (XMLErrorReporter) obj;
            }
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public Boolean getFeatureDefault(String str) {
        for (int i = 0; i < RECOGNIZED_FEATURES.length; i++) {
            if (RECOGNIZED_FEATURES[i].equals(str)) {
                return FEATURE_DEFAULTS[i];
            }
        }
        return null;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public Object getPropertyDefault(String str) {
        for (int i = 0; i < RECOGNIZED_PROPERTIES.length; i++) {
            if (RECOGNIZED_PROPERTIES[i].equals(str)) {
                return PROPERTY_DEFAULTS[i];
            }
        }
        return null;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLDocumentSource
    public void setDocumentHandler(XMLDocumentHandler xMLDocumentHandler) {
        this.fDocumentHandler = xMLDocumentHandler;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLDocumentSource
    public XMLDocumentHandler getDocumentHandler() {
        return this.fDocumentHandler;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLDocumentHandler
    public void setDocumentSource(XMLDocumentSource xMLDocumentSource) {
        this.fDocumentSource = xMLDocumentSource;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLDocumentHandler
    public XMLDocumentSource getDocumentSource() {
        return this.fDocumentSource;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLDocumentHandler
    public void startGeneralEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) {
        if (this.fDocumentHandler != null && !this.fOnlyPassPrefixMappingEvents) {
            this.fDocumentHandler.startGeneralEntity(str, xMLResourceIdentifier, str2, augmentations);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLDocumentHandler
    public void textDecl(String str, String str2, Augmentations augmentations) {
        if (this.fDocumentHandler != null && !this.fOnlyPassPrefixMappingEvents) {
            this.fDocumentHandler.textDecl(str, str2, augmentations);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLDocumentHandler
    public void startDocument(XMLLocator xMLLocator, String str, NamespaceContext namespaceContext, Augmentations augmentations) {
        if (this.fDocumentHandler != null && !this.fOnlyPassPrefixMappingEvents) {
            this.fDocumentHandler.startDocument(xMLLocator, str, this.fNamespaceSupport, augmentations);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLDocumentHandler
    public void xmlDecl(String str, String str2, String str3, Augmentations augmentations) {
        if (this.fDocumentHandler != null && !this.fOnlyPassPrefixMappingEvents) {
            this.fDocumentHandler.xmlDecl(str, str2, str3, augmentations);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLDocumentHandler
    public void doctypeDecl(String str, String str2, String str3, Augmentations augmentations) {
        if (this.fDocumentHandler != null && !this.fOnlyPassPrefixMappingEvents) {
            this.fDocumentHandler.doctypeDecl(str, str2, str3, augmentations);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLDocumentHandler
    public void comment(XMLString xMLString, Augmentations augmentations) {
        if (this.fDocumentHandler != null && !this.fOnlyPassPrefixMappingEvents) {
            this.fDocumentHandler.comment(xMLString, augmentations);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLDocumentHandler
    public void processingInstruction(String str, XMLString xMLString, Augmentations augmentations) {
        if (this.fDocumentHandler != null && !this.fOnlyPassPrefixMappingEvents) {
            this.fDocumentHandler.processingInstruction(str, xMLString, augmentations);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLDocumentHandler
    public void startPrefixMapping(String str, String str2, Augmentations augmentations) {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startPrefixMapping(str, str2, augmentations);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLDocumentHandler
    public void startElement(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations) {
        if (this.fNamespaces) {
            handleStartElement(qName, xMLAttributes, augmentations, false);
        } else if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startElement(qName, xMLAttributes, augmentations);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLDocumentHandler
    public void emptyElement(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations) {
        if (this.fNamespaces) {
            handleStartElement(qName, xMLAttributes, augmentations, true);
            handleEndElement(qName, augmentations, true);
        } else if (this.fDocumentHandler != null) {
            this.fDocumentHandler.emptyElement(qName, xMLAttributes, augmentations);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLDocumentHandler
    public void characters(XMLString xMLString, Augmentations augmentations) {
        if (this.fDocumentHandler != null && !this.fOnlyPassPrefixMappingEvents) {
            this.fDocumentHandler.characters(xMLString, augmentations);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLDocumentHandler
    public void ignorableWhitespace(XMLString xMLString, Augmentations augmentations) {
        if (this.fDocumentHandler != null && !this.fOnlyPassPrefixMappingEvents) {
            this.fDocumentHandler.ignorableWhitespace(xMLString, augmentations);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLDocumentHandler
    public void endElement(QName qName, Augmentations augmentations) {
        if (this.fNamespaces) {
            handleEndElement(qName, augmentations, false);
        } else if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endElement(qName, augmentations);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLDocumentHandler
    public void endPrefixMapping(String str, Augmentations augmentations) {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endPrefixMapping(str, augmentations);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLDocumentHandler
    public void startCDATA(Augmentations augmentations) {
        if (this.fDocumentHandler != null && !this.fOnlyPassPrefixMappingEvents) {
            this.fDocumentHandler.startCDATA(augmentations);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLDocumentHandler
    public void endCDATA(Augmentations augmentations) {
        if (this.fDocumentHandler != null && !this.fOnlyPassPrefixMappingEvents) {
            this.fDocumentHandler.endCDATA(augmentations);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLDocumentHandler
    public void endDocument(Augmentations augmentations) {
        if (this.fDocumentHandler != null && !this.fOnlyPassPrefixMappingEvents) {
            this.fDocumentHandler.endDocument(augmentations);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLDocumentHandler
    public void endGeneralEntity(String str, Augmentations augmentations) {
        if (this.fDocumentHandler != null && !this.fOnlyPassPrefixMappingEvents) {
            this.fDocumentHandler.endGeneralEntity(str, augmentations);
        }
    }

    protected void handleStartElement(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations, boolean z) {
        this.fNamespaceSupport.pushContext();
        if (qName.prefix == XMLSymbols.PREFIX_XMLNS) {
            this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "ElementXMLNSPrefix", new Object[]{qName.rawname}, (short) 2);
        }
        int length = xMLAttributes.getLength();
        for (int i = 0; i < length; i++) {
            String localName = xMLAttributes.getLocalName(i);
            String prefix = xMLAttributes.getPrefix(i);
            if (prefix == XMLSymbols.PREFIX_XMLNS || (prefix == XMLSymbols.EMPTY_STRING && localName == XMLSymbols.PREFIX_XMLNS)) {
                String strAddSymbol = this.fSymbolTable.addSymbol(xMLAttributes.getValue(i));
                if (prefix == XMLSymbols.PREFIX_XMLNS && localName == XMLSymbols.PREFIX_XMLNS) {
                    this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "CantBindXMLNS", new Object[]{xMLAttributes.getQName(i)}, (short) 2);
                }
                if (strAddSymbol == NamespaceContext.XMLNS_URI) {
                    this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "CantBindXMLNS", new Object[]{xMLAttributes.getQName(i)}, (short) 2);
                }
                if (localName == XMLSymbols.PREFIX_XML) {
                    if (strAddSymbol != NamespaceContext.XML_URI) {
                        this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "CantBindXML", new Object[]{xMLAttributes.getQName(i)}, (short) 2);
                    }
                } else if (strAddSymbol == NamespaceContext.XML_URI) {
                    this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "CantBindXML", new Object[]{xMLAttributes.getQName(i)}, (short) 2);
                }
                String str = localName != XMLSymbols.PREFIX_XMLNS ? localName : XMLSymbols.EMPTY_STRING;
                if (strAddSymbol == XMLSymbols.EMPTY_STRING && localName != XMLSymbols.PREFIX_XMLNS) {
                    this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "EmptyPrefixedAttName", new Object[]{xMLAttributes.getQName(i)}, (short) 2);
                } else {
                    this.fNamespaceSupport.declarePrefix(str, strAddSymbol.length() != 0 ? strAddSymbol : null);
                    if (this.fDocumentHandler != null) {
                        this.fDocumentHandler.startPrefixMapping(str, strAddSymbol, augmentations);
                    }
                }
            }
        }
        qName.uri = this.fNamespaceSupport.getURI(qName.prefix != null ? qName.prefix : XMLSymbols.EMPTY_STRING);
        if (qName.prefix == null && qName.uri != null) {
            qName.prefix = XMLSymbols.EMPTY_STRING;
        }
        if (qName.prefix != null && qName.uri == null) {
            this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "ElementPrefixUnbound", new Object[]{qName.prefix, qName.rawname}, (short) 2);
        }
        for (int i2 = 0; i2 < length; i2++) {
            xMLAttributes.getName(i2, this.fAttributeQName);
            String str2 = this.fAttributeQName.prefix != null ? this.fAttributeQName.prefix : XMLSymbols.EMPTY_STRING;
            String str3 = this.fAttributeQName.rawname;
            if (str3 == XMLSymbols.PREFIX_XMLNS) {
                this.fAttributeQName.uri = this.fNamespaceSupport.getURI(XMLSymbols.PREFIX_XMLNS);
                xMLAttributes.setName(i2, this.fAttributeQName);
            } else if (str2 != XMLSymbols.EMPTY_STRING) {
                this.fAttributeQName.uri = this.fNamespaceSupport.getURI(str2);
                if (this.fAttributeQName.uri == null) {
                    this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "AttributePrefixUnbound", new Object[]{str2, str3}, (short) 2);
                }
                xMLAttributes.setName(i2, this.fAttributeQName);
            }
        }
        int length2 = xMLAttributes.getLength();
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= length2 - 1) {
                break;
            }
            String localName2 = xMLAttributes.getLocalName(i4);
            String uri = xMLAttributes.getURI(i4);
            for (int i5 = i4 + 1; i5 < length2; i5++) {
                String localName3 = xMLAttributes.getLocalName(i5);
                String uri2 = xMLAttributes.getURI(i5);
                if (localName2 == localName3 && uri == uri2) {
                    this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "AttributeNSNotUnique", new Object[]{qName.rawname, localName2, uri}, (short) 2);
                }
            }
            i3 = i4 + 1;
        }
        if (this.fDocumentHandler != null && !this.fOnlyPassPrefixMappingEvents) {
            if (z) {
                this.fDocumentHandler.emptyElement(qName, xMLAttributes, augmentations);
            } else {
                this.fDocumentHandler.startElement(qName, xMLAttributes, augmentations);
            }
        }
    }

    protected void handleEndElement(QName qName, Augmentations augmentations, boolean z) {
        String str = qName.prefix != null ? qName.prefix : XMLSymbols.EMPTY_STRING;
        qName.uri = this.fNamespaceSupport.getURI(str);
        if (qName.uri != null) {
            qName.prefix = str;
        }
        if (this.fDocumentHandler != null && !this.fOnlyPassPrefixMappingEvents && !z) {
            this.fDocumentHandler.endElement(qName, augmentations);
        }
        if (this.fDocumentHandler != null) {
            for (int declaredPrefixCount = this.fNamespaceSupport.getDeclaredPrefixCount() - 1; declaredPrefixCount >= 0; declaredPrefixCount--) {
                this.fDocumentHandler.endPrefixMapping(this.fNamespaceSupport.getDeclaredPrefixAt(declaredPrefixCount), augmentations);
            }
        }
        this.fNamespaceSupport.popContext();
    }
}

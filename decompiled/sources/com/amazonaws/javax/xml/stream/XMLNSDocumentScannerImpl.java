package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.stream.xerces.impl.msg.XMLMessageFormatter;
import com.amazonaws.javax.xml.stream.xerces.util.NamespaceSupport;
import com.amazonaws.javax.xml.stream.xerces.util.XMLAttributesImpl;
import com.amazonaws.javax.xml.stream.xerces.util.XMLStringBuffer;
import com.amazonaws.javax.xml.stream.xerces.util.XMLSymbols;
import com.amazonaws.javax.xml.stream.xerces.xni.NamespaceContext;
import com.amazonaws.javax.xml.stream.xerces.xni.QName;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLString;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponentManager;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class XMLNSDocumentScannerImpl extends XMLDocumentScannerImpl {
    protected boolean fPerformValidation;
    private boolean fEmptyElement = false;
    private XMLDocumentScannerImpl.XMLBufferListenerImpl listener = new XMLDocumentScannerImpl.XMLBufferListenerImpl();
    private boolean fXmlnsDeclared = false;

    @Override // com.amazonaws.javax.xml.stream.XMLDocumentScannerImpl, com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl, com.amazonaws.javax.xml.stream.XMLScanner
    public void reset(PropertyManager propertyManager) {
        setPropertyManager(propertyManager);
        super.reset(propertyManager);
        try {
            if (!this.fAttributeCacheInitDone) {
                for (int i = 0; i < this.initialCacheCount; i++) {
                    this.attributeValueCache.add(new XMLString());
                    this.stringBufferCache.add(new XMLStringBuffer());
                }
                this.fAttributeCacheInitDone = true;
            }
            this.fStringBufferIndex = 0;
            this.fAttributeCacheUsedCount = 0;
            this.fEntityScanner.registerListener(this.listener);
            this.dtdGrammarUtil = null;
        } catch (RuntimeException e) {
        }
    }

    public QName getElementQName() {
        if (this.fScannerLastState == 2) {
            this.fElementQName.setValues(this.fElementStack.getLastPoppedElement());
        }
        return this.fElementQName;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl
    protected boolean scanStartElement() throws IOException {
        QName qNameCheckDuplicatesNS;
        if (this.fSkip && !this.fAdd) {
            QName next = this.fElementStack.getNext();
            this.fSkip = this.fEntityScanner.skipString(next.characters);
            if (this.fSkip) {
                this.fElementStack.push();
                this.fElementQName = next;
            } else {
                this.fElementStack.reposition();
            }
        }
        if (!this.fSkip || this.fAdd) {
            this.fElementQName = this.fElementStack.nextElement();
            if (this.fBindNamespaces) {
                this.fEntityScanner.scanQName(this.fElementQName);
            } else {
                String strScanName = this.fEntityScanner.scanName();
                this.fElementQName.setValues(null, strScanName, strScanName, null);
                this.fElementQName.characters = this.fEntityScanner.scannedName;
            }
        }
        if (this.fAdd) {
            this.fElementStack.matchElement(this.fElementQName);
        }
        this.fCurrentElement = this.fElementQName;
        String str = this.fElementQName.rawname;
        if (this.fBindNamespaces) {
            this.fNamespaceContext.pushContext();
            if (this.fScannerState == 26 && this.fPerformValidation) {
                this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "MSG_GRAMMAR_NOT_FOUND", new Object[]{str}, (short) 1);
                if (this.fDoctypeName == null || !this.fDoctypeName.equals(str)) {
                    this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "RootElementTypeMustMatchDoctypedecl", new Object[]{this.fDoctypeName, str}, (short) 1);
                }
            }
        }
        this.fEmptyElement = false;
        this.fAttributes.removeAllAttributes();
        if (!seekCloseOfStartTag()) {
            this.fReadingAttributes = true;
            this.fAttributeCacheUsedCount = 0;
            this.fStringBufferIndex = 0;
            this.fAddDefaultAttr = true;
            this.fXmlnsDeclared = false;
            do {
                scanAttribute((XMLAttributesImpl) this.fAttributes);
            } while (!seekCloseOfStartTag());
            this.fReadingAttributes = false;
        }
        if (this.fBindNamespaces) {
            if (this.fElementQName.prefix == XMLSymbols.PREFIX_XMLNS) {
                this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "ElementXMLNSPrefix", new Object[]{this.fElementQName.rawname}, (short) 2);
            }
            this.fElementQName.uri = this.fNamespaceContext.getURI(this.fElementQName.prefix != null ? this.fElementQName.prefix : XMLSymbols.EMPTY_STRING);
            this.fCurrentElement.uri = this.fElementQName.uri;
            if (this.fElementQName.prefix == null && this.fElementQName.uri != null) {
                this.fElementQName.prefix = XMLSymbols.EMPTY_STRING;
            }
            if (this.fElementQName.prefix != null && this.fElementQName.uri == null) {
                this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "ElementPrefixUnbound", new Object[]{this.fElementQName.prefix, this.fElementQName.rawname}, (short) 2);
            }
            int length = this.fAttributes.getLength();
            for (int i = 0; i < length; i++) {
                this.fAttributes.getName(i, this.fAttributeQName);
                String str2 = this.fAttributeQName.prefix != null ? this.fAttributeQName.prefix : XMLSymbols.EMPTY_STRING;
                String uri = this.fNamespaceContext.getURI(str2);
                if ((this.fAttributeQName.uri == null || this.fAttributeQName.uri != uri) && str2 != XMLSymbols.EMPTY_STRING) {
                    this.fAttributeQName.uri = uri;
                    if (uri == null) {
                        this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "AttributePrefixUnbound", new Object[]{this.fElementQName.rawname, this.fAttributeQName.rawname, str2}, (short) 2);
                    }
                    this.fAttributes.setURI(i, uri);
                }
            }
            if (length > 1 && (qNameCheckDuplicatesNS = this.fAttributes.checkDuplicatesNS()) != null) {
                if (qNameCheckDuplicatesNS.uri != null) {
                    this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "AttributeNSNotUnique", new Object[]{this.fElementQName.rawname, qNameCheckDuplicatesNS.localpart, qNameCheckDuplicatesNS.uri}, (short) 2);
                } else {
                    this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "AttributeNotUnique", new Object[]{this.fElementQName.rawname, qNameCheckDuplicatesNS.rawname}, (short) 2);
                }
            }
        }
        if (this.fEmptyElement) {
            this.fMarkupDepth--;
            if (this.fMarkupDepth < this.fEntityStack[this.fEntityDepth - 1]) {
                reportFatalError("ElementEntityMismatch", new Object[]{this.fCurrentElement.rawname});
            }
            if (this.fDocumentHandler != null) {
                this.fDocumentHandler.emptyElement(this.fElementQName, this.fAttributes, null);
            }
            this.fScanEndElement = true;
            this.fElementStack.popElement();
        } else {
            if (this.dtdGrammarUtil != null) {
                this.dtdGrammarUtil.startElement(this.fElementQName, this.fAttributes);
            }
            if (this.fDocumentHandler != null) {
            }
        }
        return this.fEmptyElement;
    }

    private boolean seekCloseOfStartTag() throws IOException {
        boolean zSkipSpaces = this.fEntityScanner.skipSpaces();
        int iPeekChar = this.fEntityScanner.peekChar();
        if (iPeekChar == 62) {
            this.fEntityScanner.scanChar();
            return true;
        }
        if (iPeekChar == 47) {
            this.fEntityScanner.scanChar();
            if (!this.fEntityScanner.skipChar(62)) {
                reportFatalError("ElementUnterminated", new Object[]{this.fElementQName.rawname});
            }
            this.fEmptyElement = true;
            return true;
        }
        if (!isValidNameStartChar(iPeekChar) || !zSkipSpaces) {
            reportFatalError("ElementUnterminated", new Object[]{this.fElementQName.rawname});
        }
        return false;
    }

    protected void scanAttribute(XMLAttributesImpl xMLAttributesImpl) throws IOException {
        int iAddAttribute;
        this.fEntityScanner.scanQName(this.fAttributeQName);
        this.fEntityScanner.skipSpaces();
        if (!this.fEntityScanner.skipChar(61)) {
            reportFatalError("EqRequiredInAttribute", new Object[]{this.fCurrentElement.rawname, this.fAttributeQName.rawname});
        }
        this.fEntityScanner.skipSpaces();
        boolean z = this.fHasExternalDTD && !this.fStandalone;
        XMLString string = getString();
        scanAttributeValue(string, this.fTempString2, this.fAttributeQName.rawname, xMLAttributesImpl, 0, z);
        if (this.fBindNamespaces) {
            String str = this.fAttributeQName.localpart;
            String str2 = this.fAttributeQName.prefix != null ? this.fAttributeQName.prefix : XMLSymbols.EMPTY_STRING;
            if (str2 == XMLSymbols.PREFIX_XMLNS || (str2 == XMLSymbols.EMPTY_STRING && str == XMLSymbols.PREFIX_XMLNS)) {
                String strAddSymbol = this.fSymbolTable.addSymbol(string.ch, string.offset, string.length);
                if (str2 == XMLSymbols.PREFIX_XMLNS && str == XMLSymbols.PREFIX_XMLNS) {
                    this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "CantBindXMLNS", new Object[]{this.fAttributeQName}, (short) 2);
                }
                if (strAddSymbol == NamespaceContext.XMLNS_URI) {
                    this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "CantBindXMLNS", new Object[]{this.fAttributeQName}, (short) 2);
                }
                if (str == XMLSymbols.PREFIX_XML) {
                    if (strAddSymbol != NamespaceContext.XML_URI) {
                        this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "CantBindXML", new Object[]{this.fAttributeQName}, (short) 2);
                    }
                } else if (strAddSymbol == NamespaceContext.XML_URI) {
                    this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "CantBindXML", new Object[]{this.fAttributeQName}, (short) 2);
                }
                String str3 = str != XMLSymbols.PREFIX_XMLNS ? str : XMLSymbols.EMPTY_STRING;
                if (strAddSymbol == XMLSymbols.EMPTY_STRING && str != XMLSymbols.PREFIX_XMLNS) {
                    this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "EmptyPrefixedAttName", new Object[]{this.fAttributeQName}, (short) 2);
                }
                if (((NamespaceSupport) this.fNamespaceContext).containsPrefixInCurrentContext(str3)) {
                    reportFatalError("AttributeNotUnique", new Object[]{this.fCurrentElement.rawname, this.fAttributeQName.rawname});
                }
                if (!this.fNamespaceContext.declarePrefix(str3, strAddSymbol.length() != 0 ? strAddSymbol : null)) {
                    if (this.fXmlnsDeclared) {
                        reportFatalError("AttributeNotUnique", new Object[]{this.fCurrentElement.rawname, this.fAttributeQName.rawname});
                    }
                    this.fXmlnsDeclared = true;
                    return;
                }
                return;
            }
        }
        if (this.fBindNamespaces) {
            iAddAttribute = xMLAttributesImpl.getLength();
            xMLAttributesImpl.addAttributeNS(this.fAttributeQName, XMLSymbols.fCDATASymbol, null);
        } else {
            int length = xMLAttributesImpl.getLength();
            iAddAttribute = xMLAttributesImpl.addAttribute(this.fAttributeQName, XMLSymbols.fCDATASymbol, null);
            if (length == xMLAttributesImpl.getLength()) {
                reportFatalError("AttributeNotUnique", new Object[]{this.fCurrentElement.rawname, this.fAttributeQName.rawname});
            }
        }
        xMLAttributesImpl.setValue(iAddAttribute, null, string);
        xMLAttributesImpl.setSpecified(iAddAttribute, true);
        if (this.fAttributeQName.prefix != null) {
            xMLAttributesImpl.setURI(iAddAttribute, this.fNamespaceContext.getURI(this.fAttributeQName.prefix));
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl
    protected int scanEndElement() throws IOException {
        QName qNamePopElement = this.fElementStack.popElement();
        String str = qNamePopElement.rawname;
        if (!this.fEntityScanner.skipString(qNamePopElement.characters)) {
            reportFatalError("ETagRequired", new Object[]{str});
        }
        this.fEntityScanner.skipSpaces();
        if (!this.fEntityScanner.skipChar(62)) {
            reportFatalError("ETagUnterminated", new Object[]{str});
        }
        this.fMarkupDepth--;
        this.fMarkupDepth--;
        if (this.fMarkupDepth < this.fEntityStack[this.fEntityDepth - 1]) {
            reportFatalError("ElementEntityMismatch", new Object[]{str});
        }
        if (this.fDocumentHandler != null) {
        }
        if (this.dtdGrammarUtil != null) {
            this.dtdGrammarUtil.endElement(qNamePopElement);
        }
        this.fScanEndElement = true;
        return this.fMarkupDepth;
    }

    public NamespaceContext getNamespaceContext() {
        return this.fNamespaceContext;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLDocumentScannerImpl, com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl, com.amazonaws.javax.xml.stream.XMLScanner, com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public void reset(XMLComponentManager xMLComponentManager) {
        super.reset(xMLComponentManager);
        this.fPerformValidation = false;
        this.fBindNamespaces = false;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLDocumentScannerImpl, com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl
    protected XMLDocumentFragmentScannerImpl.Driver createContentDriver() {
        return new NSContentDriver();
    }

    public final class NSContentDriver extends XMLDocumentScannerImpl.ContentDriver {
        protected NSContentDriver() {
            super();
        }

        @Override // com.amazonaws.javax.xml.stream.XMLDocumentScannerImpl.ContentDriver, com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl.FragmentContentDriver
        protected boolean scanRootElementHook() {
            if (!XMLNSDocumentScannerImpl.this.scanStartElement()) {
                return false;
            }
            XMLNSDocumentScannerImpl.this.setScannerState(44);
            XMLNSDocumentScannerImpl.this.setDriver(XMLNSDocumentScannerImpl.this.fTrailingMiscDriver);
            return true;
        }
    }

    XMLString getString() {
        if (this.fAttributeCacheUsedCount < this.initialCacheCount || this.fAttributeCacheUsedCount < this.attributeValueCache.size()) {
            ArrayList arrayList = this.attributeValueCache;
            int i = this.fAttributeCacheUsedCount;
            this.fAttributeCacheUsedCount = i + 1;
            return (XMLString) arrayList.get(i);
        }
        XMLString xMLString = new XMLString();
        this.fAttributeCacheUsedCount++;
        this.attributeValueCache.add(xMLString);
        return xMLString;
    }

    public XMLStringBuffer getDTDDecl() {
        this.fDTDDecl.append(this.fEntityScanner.getCurrentEntity().ch, this.fStartPos, this.fEndPos - this.fStartPos);
        if (this.fSeenInternalSubset) {
            this.fDTDDecl.append("]>");
        }
        return this.fDTDDecl;
    }

    public String getCharacterEncodingScheme() {
        return this.fDeclaredEncoding;
    }
}

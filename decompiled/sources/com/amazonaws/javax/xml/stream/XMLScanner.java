package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.stream.events.XMLEvent;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.amazonaws.javax.xml.stream.xerces.impl.msg.XMLMessageFormatter;
import com.amazonaws.javax.xml.stream.xerces.util.SymbolTable;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import com.amazonaws.javax.xml.stream.xerces.util.XMLResourceIdentifierImpl;
import com.amazonaws.javax.xml.stream.xerces.util.XMLStringBuffer;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLResourceIdentifier;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLString;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponentManager;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLConfigurationException;
import com.facebook.internal.NativeProtocol;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public abstract class XMLScanner implements XMLComponent {
    protected static final boolean DEBUG_ATTR_NORMALIZATION = false;
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String NOTIFY_CHAR_REFS = "http://apache.org/xml/features/scanner/notify-char-refs";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    protected int fEntityDepth;
    protected XMLErrorReporter fErrorReporter;
    protected XMLEvent fEvent;
    protected boolean fReportEntity;
    protected boolean fScanningAttribute;
    protected SymbolTable fSymbolTable;
    protected static final String fVersionSymbol = NativeProtocol.PLATFORM_PROVIDER_VERSION_COLUMN.intern();
    protected static final String fEncodingSymbol = "encoding".intern();
    protected static final String fStandaloneSymbol = "standalone".intern();
    protected static final String fAmpSymbol = "amp".intern();
    protected static final String fLtSymbol = "lt".intern();
    protected static final String fGtSymbol = "gt".intern();
    protected static final String fQuotSymbol = "quot".intern();
    protected static final String fAposSymbol = "apos".intern();
    private boolean fNeedNonNormalizedValue = DEBUG_ATTR_NORMALIZATION;
    protected ArrayList attributeValueCache = new ArrayList();
    protected ArrayList stringBufferCache = new ArrayList();
    protected int fStringBufferIndex = 0;
    protected boolean fAttributeCacheInitDone = DEBUG_ATTR_NORMALIZATION;
    protected int fAttributeCacheUsedCount = 0;
    protected boolean fValidation = DEBUG_ATTR_NORMALIZATION;
    protected boolean fNotifyCharRefs = DEBUG_ATTR_NORMALIZATION;
    protected PropertyManager fPropertyManager = null;
    protected XMLEntityManager fEntityManager = null;
    protected XMLEntityStorage fEntityStore = null;
    protected XMLEntityReaderImpl fEntityScanner = null;
    protected String fCharRefLiteral = null;
    private XMLString fString = new XMLString();
    private XMLStringBuffer fStringBuffer = new XMLStringBuffer();
    private XMLStringBuffer fStringBuffer2 = new XMLStringBuffer();
    private XMLStringBuffer fStringBuffer3 = new XMLStringBuffer();
    protected XMLResourceIdentifierImpl fResourceIdentifier = new XMLResourceIdentifierImpl();
    int initialCacheCount = 6;

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public void reset(XMLComponentManager xMLComponentManager) {
        this.fSymbolTable = (SymbolTable) xMLComponentManager.getProperty(SYMBOL_TABLE);
        this.fErrorReporter = (XMLErrorReporter) xMLComponentManager.getProperty(ERROR_REPORTER);
        this.fEntityManager = (XMLEntityManager) xMLComponentManager.getProperty(ENTITY_MANAGER);
        init();
        try {
            this.fValidation = xMLComponentManager.getFeature(VALIDATION);
        } catch (XMLConfigurationException e) {
            this.fValidation = DEBUG_ATTR_NORMALIZATION;
        }
        try {
            this.fNotifyCharRefs = xMLComponentManager.getFeature(NOTIFY_CHAR_REFS);
        } catch (XMLConfigurationException e2) {
            this.fNotifyCharRefs = DEBUG_ATTR_NORMALIZATION;
        }
    }

    protected void setPropertyManager(PropertyManager propertyManager) {
        this.fPropertyManager = propertyManager;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public void setProperty(String str, Object obj) {
        if (str.startsWith(Constants.XERCES_PROPERTY_PREFIX)) {
            String strSubstring = str.substring(Constants.XERCES_PROPERTY_PREFIX.length());
            if (strSubstring.equals(Constants.SYMBOL_TABLE_PROPERTY)) {
                this.fSymbolTable = (SymbolTable) obj;
            } else if (strSubstring.equals(Constants.ERROR_REPORTER_PROPERTY)) {
                this.fErrorReporter = (XMLErrorReporter) obj;
            } else if (strSubstring.equals(Constants.ENTITY_MANAGER_PROPERTY)) {
                this.fEntityManager = (XMLEntityManager) obj;
            }
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public void setFeature(String str, boolean z) {
        if (VALIDATION.equals(str)) {
            this.fValidation = z;
        } else if (NOTIFY_CHAR_REFS.equals(str)) {
            this.fNotifyCharRefs = z;
        }
    }

    public boolean getFeature(String str) {
        if (VALIDATION.equals(str)) {
            return this.fValidation;
        }
        if (NOTIFY_CHAR_REFS.equals(str)) {
            return this.fNotifyCharRefs;
        }
        throw new XMLConfigurationException((short) 0, str);
    }

    public void reset(PropertyManager propertyManager) {
        init();
        this.fSymbolTable = (SymbolTable) propertyManager.getProperty(SYMBOL_TABLE);
        this.fErrorReporter = (XMLErrorReporter) propertyManager.getProperty(ERROR_REPORTER);
        this.fEntityManager = (XMLEntityManager) propertyManager.getProperty(ENTITY_MANAGER);
        this.fEntityStore = this.fEntityManager.getEntityStore();
        this.fEntityScanner = (XMLEntityReaderImpl) this.fEntityManager.getEntityReader();
        this.fValidation = DEBUG_ATTR_NORMALIZATION;
        this.fNotifyCharRefs = DEBUG_ATTR_NORMALIZATION;
    }

    protected void scanXMLDeclOrTextDecl(boolean z, String[] strArr) {
        String string;
        boolean zSkipSpaces = this.fEntityScanner.skipSpaces();
        boolean z2 = false;
        String str = null;
        String string2 = null;
        String str2 = null;
        char c = 0;
        while (this.fEntityScanner.peekChar() != 63) {
            String strScanPseudoAttribute = scanPseudoAttribute(z, this.fString);
            switch (c) {
                case 0:
                    if (strScanPseudoAttribute.equals(fVersionSymbol)) {
                        if (!zSkipSpaces) {
                            reportFatalError(z ? "SpaceRequiredBeforeVersionInTextDecl" : "SpaceRequiredBeforeVersionInXMLDecl", null);
                        }
                        String string3 = this.fString.toString();
                        if (versionSupported(string3)) {
                            str2 = string3;
                            c = 1;
                        } else {
                            reportFatalError("VersionNotSupported", new Object[]{string3});
                            str2 = string3;
                            c = 1;
                            continue;
                        }
                    } else if (strScanPseudoAttribute.equals(fEncodingSymbol)) {
                        if (!z) {
                            reportFatalError("VersionInfoRequired", null);
                        }
                        if (!zSkipSpaces) {
                            reportFatalError(z ? "SpaceRequiredBeforeEncodingInTextDecl" : "SpaceRequiredBeforeEncodingInXMLDecl", null);
                        }
                        string2 = this.fString.toString();
                        c = z ? (char) 3 : (char) 2;
                    } else if (z) {
                        reportFatalError("EncodingDeclRequired", null);
                    } else {
                        reportFatalError("VersionInfoRequired", null);
                    }
                    zSkipSpaces = this.fEntityScanner.skipSpaces();
                    z2 = true;
                    break;
                case 1:
                    if (strScanPseudoAttribute.equals(fEncodingSymbol)) {
                        if (!zSkipSpaces) {
                            reportFatalError(z ? "SpaceRequiredBeforeEncodingInTextDecl" : "SpaceRequiredBeforeEncodingInXMLDecl", null);
                        }
                        string2 = this.fString.toString();
                        c = z ? (char) 3 : (char) 2;
                    } else if (!z && strScanPseudoAttribute.equals(fStandaloneSymbol)) {
                        if (!zSkipSpaces) {
                            reportFatalError("SpaceRequiredBeforeStandalone", null);
                        }
                        string = this.fString.toString();
                        if (!string.equals("yes") && !string.equals("no")) {
                            reportFatalError("SDDeclInvalid", null);
                            str = string;
                            c = 3;
                        }
                    } else {
                        reportFatalError("EncodingDeclRequired", null);
                    }
                    zSkipSpaces = this.fEntityScanner.skipSpaces();
                    z2 = true;
                    break;
                case 2:
                    if (strScanPseudoAttribute.equals(fStandaloneSymbol)) {
                        if (!zSkipSpaces) {
                            reportFatalError("SpaceRequiredBeforeStandalone", null);
                        }
                        string = this.fString.toString();
                        if (!string.equals("yes") && !string.equals("no")) {
                            reportFatalError("SDDeclInvalid", null);
                            str = string;
                            c = 3;
                        }
                    } else {
                        reportFatalError("EncodingDeclRequired", null);
                    }
                    zSkipSpaces = this.fEntityScanner.skipSpaces();
                    z2 = true;
                    break;
                default:
                    reportFatalError("NoMorePseudoAttributes", null);
                    continue;
                    zSkipSpaces = this.fEntityScanner.skipSpaces();
                    z2 = true;
                    break;
            }
            str = string;
            c = 3;
            zSkipSpaces = this.fEntityScanner.skipSpaces();
            z2 = true;
        }
        if (z && c != 3) {
            reportFatalError("MorePseudoAttributes", null);
        }
        if (z) {
            if (!z2 && string2 == null) {
                reportFatalError("EncodingDeclRequired", null);
            }
        } else if (!z2 && str2 == null) {
            reportFatalError("VersionInfoRequired", null);
        }
        if (!this.fEntityScanner.skipChar(63)) {
            reportFatalError("XMLDeclUnterminated", null);
        }
        if (!this.fEntityScanner.skipChar(62)) {
            reportFatalError("XMLDeclUnterminated", null);
        }
        strArr[0] = str2;
        strArr[1] = string2;
        strArr[2] = str;
    }

    public String scanPseudoAttribute(boolean z, XMLString xMLString) throws IOException {
        String strScanName = this.fEntityScanner.scanName();
        if (strScanName == null) {
            reportFatalError("PseudoAttrNameExpected", null);
        }
        this.fEntityScanner.skipSpaces();
        if (!this.fEntityScanner.skipChar(61)) {
            reportFatalError(z ? "EqRequiredInTextDecl" : "EqRequiredInXMLDecl", new Object[]{strScanName});
        }
        this.fEntityScanner.skipSpaces();
        int iPeekChar = this.fEntityScanner.peekChar();
        if (iPeekChar != 39 && iPeekChar != 34) {
            reportFatalError(z ? "QuoteRequiredInTextDecl" : "QuoteRequiredInXMLDecl", new Object[]{strScanName});
        }
        this.fEntityScanner.scanChar();
        int iScanLiteral = this.fEntityScanner.scanLiteral(iPeekChar, xMLString);
        if (iScanLiteral != iPeekChar) {
            this.fStringBuffer2.clear();
            do {
                this.fStringBuffer2.append(xMLString);
                if (iScanLiteral != -1) {
                    if (iScanLiteral == 38 || iScanLiteral == 37 || iScanLiteral == 60 || iScanLiteral == 93) {
                        this.fStringBuffer2.append((char) this.fEntityScanner.scanChar());
                    } else if (XMLChar.isHighSurrogate(iScanLiteral)) {
                        scanSurrogates(this.fStringBuffer2);
                    } else if (isInvalidLiteral(iScanLiteral)) {
                        reportFatalError(z ? "InvalidCharInTextDecl" : "InvalidCharInXMLDecl", new Object[]{Integer.toString(iScanLiteral, 16)});
                        this.fEntityScanner.scanChar();
                    }
                }
                iScanLiteral = this.fEntityScanner.scanLiteral(iPeekChar, xMLString);
            } while (iScanLiteral != iPeekChar);
            this.fStringBuffer2.append(xMLString);
            xMLString.setValues(this.fStringBuffer2);
        }
        if (!this.fEntityScanner.skipChar(iPeekChar)) {
            reportFatalError(z ? "CloseQuoteMissingInTextDecl" : "CloseQuoteMissingInXMLDecl", new Object[]{strScanName});
        }
        return strScanName;
    }

    protected void scanPI(XMLStringBuffer xMLStringBuffer) throws IOException {
        this.fReportEntity = DEBUG_ATTR_NORMALIZATION;
        String strScanName = this.fEntityScanner.scanName();
        if (strScanName == null) {
            reportFatalError("PITargetRequired", null);
        }
        scanPIData(strScanName, xMLStringBuffer);
        this.fReportEntity = true;
    }

    protected void scanPIData(String str, XMLStringBuffer xMLStringBuffer) {
        if (str.length() == 3) {
            char lowerCase = Character.toLowerCase(str.charAt(0));
            char lowerCase2 = Character.toLowerCase(str.charAt(1));
            char lowerCase3 = Character.toLowerCase(str.charAt(2));
            if (lowerCase == 'x' && lowerCase2 == 'm' && lowerCase3 == 'l') {
                reportFatalError("ReservedPITarget", null);
            }
        }
        if (!this.fEntityScanner.skipSpaces()) {
            if (!this.fEntityScanner.skipString("?>")) {
                reportFatalError("SpaceRequiredInPI", null);
            } else {
                return;
            }
        }
        if (this.fEntityScanner.scanData("?>", xMLStringBuffer)) {
            do {
                int iPeekChar = this.fEntityScanner.peekChar();
                if (iPeekChar != -1) {
                    if (XMLChar.isHighSurrogate(iPeekChar)) {
                        scanSurrogates(xMLStringBuffer);
                    } else if (isInvalidLiteral(iPeekChar)) {
                        reportFatalError("InvalidCharInPI", new Object[]{Integer.toHexString(iPeekChar)});
                        this.fEntityScanner.scanChar();
                    }
                }
            } while (this.fEntityScanner.scanData("?>", xMLStringBuffer));
        }
    }

    protected void scanComment(XMLStringBuffer xMLStringBuffer) throws IOException {
        xMLStringBuffer.clear();
        while (this.fEntityScanner.scanData("--", xMLStringBuffer)) {
            int iPeekChar = this.fEntityScanner.peekChar();
            if (iPeekChar != -1) {
                if (XMLChar.isHighSurrogate(iPeekChar)) {
                    scanSurrogates(xMLStringBuffer);
                }
                if (isInvalidLiteral(iPeekChar)) {
                    reportFatalError("InvalidCharInComment", new Object[]{Integer.toHexString(iPeekChar)});
                    this.fEntityScanner.scanChar();
                }
            }
        }
        if (!this.fEntityScanner.skipChar(62)) {
            reportFatalError("DashDashInComment", null);
        }
    }

    protected void scanAttributeValue(XMLString xMLString, XMLString xMLString2, String str, XMLAttributes xMLAttributes, int i, boolean z) throws IOException {
        int iScanCharReferenceValue;
        int iPeekChar = this.fEntityScanner.peekChar();
        if (iPeekChar != 39 && iPeekChar != 34) {
            reportFatalError("OpenQuoteExpected", new Object[]{str});
        }
        this.fEntityScanner.scanChar();
        int i2 = this.fEntityDepth;
        int iScanLiteral = this.fEntityScanner.scanLiteral(iPeekChar, xMLString);
        if (this.fNeedNonNormalizedValue) {
            this.fStringBuffer2.clear();
            this.fStringBuffer2.append(xMLString);
        }
        if (this.fEntityScanner.whiteSpaceLen > 0) {
            normalizeWhitespace(xMLString);
        }
        if (iScanLiteral != iPeekChar) {
            this.fScanningAttribute = true;
            XMLStringBuffer stringBuffer = getStringBuffer();
            stringBuffer.clear();
            while (true) {
                stringBuffer.append(xMLString);
                if (iScanLiteral == 38) {
                    this.fEntityScanner.skipChar(38);
                    if (i2 == this.fEntityDepth && this.fNeedNonNormalizedValue) {
                        this.fStringBuffer2.append('&');
                    }
                    if (this.fEntityScanner.skipChar(35)) {
                        if (i2 == this.fEntityDepth && this.fNeedNonNormalizedValue) {
                            this.fStringBuffer2.append('#');
                        }
                        if (this.fNeedNonNormalizedValue) {
                            iScanCharReferenceValue = scanCharReferenceValue(stringBuffer, this.fStringBuffer2);
                        } else {
                            iScanCharReferenceValue = scanCharReferenceValue(stringBuffer, null);
                        }
                        if (iScanCharReferenceValue != -1) {
                        }
                    } else {
                        String strScanName = this.fEntityScanner.scanName();
                        if (strScanName == null) {
                            reportFatalError("NameRequiredInReference", null);
                        } else if (i2 == this.fEntityDepth && this.fNeedNonNormalizedValue) {
                            this.fStringBuffer2.append(strScanName);
                        }
                        if (!this.fEntityScanner.skipChar(59)) {
                            reportFatalError("SemicolonRequiredInReference", new Object[]{strScanName});
                        } else if (i2 == this.fEntityDepth && this.fNeedNonNormalizedValue) {
                            this.fStringBuffer2.append(';');
                        }
                        if (strScanName == fAmpSymbol) {
                            stringBuffer.append('&');
                        } else if (strScanName == fAposSymbol) {
                            stringBuffer.append('\'');
                        } else if (strScanName == fLtSymbol) {
                            stringBuffer.append(XMLStreamWriterImpl.OPEN_START_TAG);
                        } else if (strScanName == fGtSymbol) {
                            stringBuffer.append('>');
                        } else if (strScanName == fQuotSymbol) {
                            stringBuffer.append('\"');
                        } else if (this.fEntityStore.isExternalEntity(strScanName)) {
                            reportFatalError("ReferenceToExternalEntity", new Object[]{strScanName});
                        } else {
                            if (!this.fEntityStore.isDeclaredEntity(strScanName)) {
                                if (z) {
                                    if (this.fValidation) {
                                        this.fErrorReporter.reportError(this.fEntityScanner, XMLMessageFormatter.XML_DOMAIN, "EntityNotDeclared", new Object[]{strScanName}, (short) 1);
                                    }
                                } else {
                                    reportFatalError("EntityNotDeclared", new Object[]{strScanName});
                                }
                            }
                            this.fEntityManager.startEntity(strScanName, true);
                        }
                    }
                } else if (iScanLiteral == 60) {
                    reportFatalError("LessthanInAttValue", new Object[]{null, str});
                    this.fEntityScanner.scanChar();
                    if (i2 == this.fEntityDepth && this.fNeedNonNormalizedValue) {
                        this.fStringBuffer2.append((char) iScanLiteral);
                    }
                } else if (iScanLiteral == 37 || iScanLiteral == 93) {
                    this.fEntityScanner.scanChar();
                    stringBuffer.append((char) iScanLiteral);
                    if (i2 == this.fEntityDepth && this.fNeedNonNormalizedValue) {
                        this.fStringBuffer2.append((char) iScanLiteral);
                    }
                } else if (iScanLiteral == 10 || iScanLiteral == 13) {
                    this.fEntityScanner.scanChar();
                    stringBuffer.append(' ');
                    if (i2 == this.fEntityDepth && this.fNeedNonNormalizedValue) {
                        this.fStringBuffer2.append('\n');
                    }
                } else if (iScanLiteral != -1 && XMLChar.isHighSurrogate(iScanLiteral)) {
                    if (scanSurrogates(this.fStringBuffer3)) {
                        stringBuffer.append(this.fStringBuffer3);
                        if (i2 == this.fEntityDepth && this.fNeedNonNormalizedValue) {
                            this.fStringBuffer2.append(this.fStringBuffer3);
                        }
                    }
                } else if (iScanLiteral != -1 && isInvalidLiteral(iScanLiteral)) {
                    reportFatalError("InvalidCharInAttValue", new Object[]{Integer.toString(iScanLiteral, 16)});
                    this.fEntityScanner.scanChar();
                    if (i2 == this.fEntityDepth && this.fNeedNonNormalizedValue) {
                        this.fStringBuffer2.append((char) iScanLiteral);
                    }
                }
                iScanLiteral = this.fEntityScanner.scanLiteral(iPeekChar, xMLString);
                if (i2 == this.fEntityDepth && this.fNeedNonNormalizedValue) {
                    this.fStringBuffer2.append(xMLString);
                }
                if (this.fEntityScanner.whiteSpaceLen > 0) {
                    normalizeWhitespace(xMLString);
                }
                if (iScanLiteral == iPeekChar && i2 == this.fEntityDepth) {
                    break;
                }
            }
            stringBuffer.append(xMLString);
            xMLString.setValues(stringBuffer);
            this.fScanningAttribute = DEBUG_ATTR_NORMALIZATION;
        }
        if (this.fNeedNonNormalizedValue) {
            xMLString2.setValues(this.fStringBuffer2);
        }
        if (this.fEntityScanner.scanChar() != iPeekChar) {
            reportFatalError("CloseQuoteExpected", new Object[]{str});
        }
    }

    protected void scanExternalID(String[] strArr, boolean z) throws IOException {
        String str;
        String string;
        if (this.fEntityScanner.skipString("PUBLIC")) {
            if (!this.fEntityScanner.skipSpaces()) {
                reportFatalError("SpaceRequiredAfterPUBLIC", null);
            }
            scanPubidLiteral(this.fString);
            String string2 = this.fString.toString();
            if (!this.fEntityScanner.skipSpaces() && !z) {
                reportFatalError("SpaceRequiredBetweenPublicAndSystem", null);
            }
            str = string2;
        } else {
            str = null;
        }
        if (str != null || this.fEntityScanner.skipString("SYSTEM")) {
            if (str == null && !this.fEntityScanner.skipSpaces()) {
                reportFatalError("SpaceRequiredAfterSYSTEM", null);
            }
            int iPeekChar = this.fEntityScanner.peekChar();
            if (iPeekChar != 39 && iPeekChar != 34) {
                if (str != null && z) {
                    strArr[0] = null;
                    strArr[1] = str;
                    return;
                }
                reportFatalError("QuoteRequiredInSystemID", null);
            }
            this.fEntityScanner.scanChar();
            XMLString xMLString = this.fString;
            if (this.fEntityScanner.scanLiteral(iPeekChar, xMLString) != iPeekChar) {
                this.fStringBuffer.clear();
                do {
                    this.fStringBuffer.append(xMLString);
                    int iPeekChar2 = this.fEntityScanner.peekChar();
                    if (XMLChar.isMarkup(iPeekChar2) || iPeekChar2 == 93) {
                        this.fStringBuffer.append((char) this.fEntityScanner.scanChar());
                    }
                } while (this.fEntityScanner.scanLiteral(iPeekChar, xMLString) != iPeekChar);
                this.fStringBuffer.append(xMLString);
                xMLString = this.fStringBuffer;
            }
            string = xMLString.toString();
            if (!this.fEntityScanner.skipChar(iPeekChar)) {
                reportFatalError("SystemIDUnterminated", null);
            }
        } else {
            string = null;
        }
        strArr[0] = string;
        strArr[1] = str;
    }

    protected boolean scanPubidLiteral(XMLString xMLString) throws IOException {
        int iScanChar = this.fEntityScanner.scanChar();
        if (iScanChar != 39 && iScanChar != 34) {
            reportFatalError("QuoteRequiredInPublicID", null);
            return DEBUG_ATTR_NORMALIZATION;
        }
        this.fStringBuffer.clear();
        boolean z = true;
        boolean z2 = true;
        while (true) {
            int iScanChar2 = this.fEntityScanner.scanChar();
            if (iScanChar2 == 32 || iScanChar2 == 10 || iScanChar2 == 13) {
                if (!z2) {
                    this.fStringBuffer.append(' ');
                    z2 = true;
                }
            } else {
                if (iScanChar2 == iScanChar) {
                    if (z2) {
                        this.fStringBuffer.length--;
                    }
                    xMLString.setValues(this.fStringBuffer);
                    return z;
                }
                if (XMLChar.isPubid(iScanChar2)) {
                    this.fStringBuffer.append((char) iScanChar2);
                    z2 = false;
                } else {
                    if (iScanChar2 == -1) {
                        reportFatalError("PublicIDUnterminated", null);
                        return DEBUG_ATTR_NORMALIZATION;
                    }
                    reportFatalError("InvalidCharInPublicID", new Object[]{Integer.toHexString(iScanChar2)});
                    z = false;
                }
            }
        }
    }

    protected void normalizeWhitespace(XMLString xMLString) {
        int[] iArr = this.fEntityScanner.whiteSpaceLookup;
        int i = this.fEntityScanner.whiteSpaceLen;
        int i2 = xMLString.offset + xMLString.length;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = iArr[i3];
            if (i4 < i2) {
                xMLString.ch[i4] = ' ';
            }
        }
    }

    public void startEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2) {
        this.fEntityDepth++;
    }

    public void endEntity(String str) {
        this.fEntityDepth--;
    }

    protected int scanCharReferenceValue(XMLStringBuffer xMLStringBuffer, XMLStringBuffer xMLStringBuffer2) throws IOException {
        boolean z;
        boolean z2;
        int i;
        boolean z3;
        if (this.fEntityScanner.skipChar(120)) {
            if (xMLStringBuffer2 != null) {
                xMLStringBuffer2.append('x');
            }
            this.fStringBuffer3.clear();
            int iPeekChar = this.fEntityScanner.peekChar();
            if ((iPeekChar >= 48 && iPeekChar <= 57) || (iPeekChar >= 97 && iPeekChar <= 102) || (iPeekChar >= 65 && iPeekChar <= 70)) {
                if (xMLStringBuffer2 != null) {
                    xMLStringBuffer2.append((char) iPeekChar);
                }
                this.fEntityScanner.scanChar();
                this.fStringBuffer3.append((char) iPeekChar);
                do {
                    int iPeekChar2 = this.fEntityScanner.peekChar();
                    z3 = (iPeekChar2 >= 48 && iPeekChar2 <= 57) || (iPeekChar2 >= 97 && iPeekChar2 <= 102) || (iPeekChar2 >= 65 && iPeekChar2 <= 70);
                    if (z3) {
                        if (xMLStringBuffer2 != null) {
                            xMLStringBuffer2.append((char) iPeekChar2);
                        }
                        this.fEntityScanner.scanChar();
                        this.fStringBuffer3.append((char) iPeekChar2);
                    }
                } while (z3);
            } else {
                reportFatalError("HexdigitRequiredInCharRef", null);
            }
            z = true;
        } else {
            this.fStringBuffer3.clear();
            int iPeekChar3 = this.fEntityScanner.peekChar();
            if (iPeekChar3 >= 48 && iPeekChar3 <= 57) {
                if (xMLStringBuffer2 != null) {
                    xMLStringBuffer2.append((char) iPeekChar3);
                }
                this.fEntityScanner.scanChar();
                this.fStringBuffer3.append((char) iPeekChar3);
                do {
                    int iPeekChar4 = this.fEntityScanner.peekChar();
                    z2 = iPeekChar4 >= 48 && iPeekChar4 <= 57;
                    if (z2) {
                        if (xMLStringBuffer2 != null) {
                            xMLStringBuffer2.append((char) iPeekChar4);
                        }
                        this.fEntityScanner.scanChar();
                        this.fStringBuffer3.append((char) iPeekChar4);
                    }
                } while (z2);
                z = false;
            } else {
                reportFatalError("DigitRequiredInCharRef", null);
                z = false;
            }
        }
        if (!this.fEntityScanner.skipChar(59)) {
            reportFatalError("SemicolonRequiredInCharRef", null);
        }
        if (xMLStringBuffer2 != null) {
            xMLStringBuffer2.append(';');
        }
        int i2 = -1;
        try {
            i2 = Integer.parseInt(this.fStringBuffer3.toString(), z ? 16 : 10);
            if (isInvalid(i2)) {
                StringBuffer stringBuffer = new StringBuffer(this.fStringBuffer3.length + 1);
                if (z) {
                    stringBuffer.append('x');
                }
                stringBuffer.append(this.fStringBuffer3.ch, this.fStringBuffer3.offset, this.fStringBuffer3.length);
                reportFatalError("InvalidCharRef", new Object[]{stringBuffer.toString()});
            }
            i = i2;
        } catch (NumberFormatException e) {
            StringBuffer stringBuffer2 = new StringBuffer(this.fStringBuffer3.length + 1);
            if (z) {
                stringBuffer2.append('x');
            }
            stringBuffer2.append(this.fStringBuffer3.ch, this.fStringBuffer3.offset, this.fStringBuffer3.length);
            reportFatalError("InvalidCharRef", new Object[]{stringBuffer2.toString()});
            i = i2;
        }
        if (!XMLChar.isSupplemental(i)) {
            xMLStringBuffer.append((char) i);
        } else {
            xMLStringBuffer.append(XMLChar.highSurrogate(i));
            xMLStringBuffer.append(XMLChar.lowSurrogate(i));
        }
        if (this.fNotifyCharRefs && i != -1) {
            String string = new StringBuffer().append("#").append(z ? "x" : "").append(this.fStringBuffer3.toString()).toString();
            if (!this.fScanningAttribute) {
                this.fCharRefLiteral = string;
            }
        }
        return i;
    }

    protected static boolean isInvalid(int i) {
        return XMLChar.isInvalid(i);
    }

    protected static boolean isInvalidLiteral(int i) {
        return XMLChar.isInvalid(i);
    }

    protected static boolean isValidNameChar(int i) {
        return XMLChar.isName(i);
    }

    protected static boolean isValidNCName(int i) {
        return XMLChar.isNCName(i);
    }

    protected static boolean isValidNameStartChar(int i) {
        return XMLChar.isNameStart(i);
    }

    protected boolean versionSupported(String str) {
        return str.equals(XMLStreamWriterImpl.DEFAULT_XML_VERSION);
    }

    protected boolean scanSurrogates(XMLStringBuffer xMLStringBuffer) throws IOException {
        int iScanChar = this.fEntityScanner.scanChar();
        int iPeekChar = this.fEntityScanner.peekChar();
        if (!XMLChar.isLowSurrogate(iPeekChar)) {
            reportFatalError("InvalidCharInContent", new Object[]{Integer.toString(iScanChar, 16)});
            return DEBUG_ATTR_NORMALIZATION;
        }
        this.fEntityScanner.scanChar();
        int iSupplemental = XMLChar.supplemental((char) iScanChar, (char) iPeekChar);
        if (isInvalid(iSupplemental)) {
            reportFatalError("InvalidCharInContent", new Object[]{Integer.toString(iSupplemental, 16)});
            return DEBUG_ATTR_NORMALIZATION;
        }
        xMLStringBuffer.append((char) iScanChar);
        xMLStringBuffer.append((char) iPeekChar);
        return true;
    }

    protected void reportFatalError(String str, Object[] objArr) {
        this.fErrorReporter.reportError(this.fEntityScanner, XMLMessageFormatter.XML_DOMAIN, str, objArr, (short) 2);
    }

    private void init() {
        this.fEntityDepth = 0;
        this.fReportEntity = true;
        this.fResourceIdentifier.clear();
    }

    XMLStringBuffer getStringBuffer() {
        if (this.fStringBufferIndex < this.initialCacheCount || this.fStringBufferIndex < this.stringBufferCache.size()) {
            ArrayList arrayList = this.stringBufferCache;
            int i = this.fStringBufferIndex;
            this.fStringBufferIndex = i + 1;
            return (XMLStringBuffer) arrayList.get(i);
        }
        XMLStringBuffer xMLStringBuffer = new XMLStringBuffer();
        this.stringBufferCache.add(this.fStringBufferIndex, xMLStringBuffer);
        return xMLStringBuffer;
    }
}

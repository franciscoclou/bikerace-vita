package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.stream.dtd.nonvalidating.DTDGrammar;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.amazonaws.javax.xml.stream.xerces.impl.msg.XMLMessageFormatter;
import com.amazonaws.javax.xml.stream.xerces.util.SymbolTable;
import com.amazonaws.javax.xml.stream.xerces.util.XMLAttributesImpl;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import com.amazonaws.javax.xml.stream.xerces.util.XMLStringBuffer;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLDTDContentModelHandler;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLDTDHandler;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLResourceIdentifier;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLString;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponentManager;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLDTDScanner;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLInputSource;
import java.io.IOException;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class XMLDTDScannerImpl extends XMLScanner implements XMLEntityHandler, XMLComponent, XMLDTDScanner {
    private static final boolean DEBUG_SCANNER_STATE = false;
    protected static final int SCANNER_STATE_END_OF_INPUT = 0;
    protected static final int SCANNER_STATE_MARKUP_DECL = 2;
    protected static final int SCANNER_STATE_TEXT_DECL = 1;
    private XMLAttributesImpl fAttributes;
    private int fContentDepth;
    private int[] fContentStack;
    protected XMLDTDContentModelHandler fDTDContentModelHandler;
    public XMLDTDHandler fDTDHandler;
    private String[] fEnumeration;
    private int fEnumerationCount;
    private int fExtEntityDepth;
    private XMLStringBuffer fIgnoreConditionalBuffer;
    private int fIncludeSectDepth;
    private XMLString fLiteral;
    private XMLString fLiteral2;
    private int fMarkUpDepth;
    private int fPEDepth;
    private boolean[] fPEReport;
    private int[] fPEStack;
    protected int fScannerState;
    protected boolean fSeenExternalDTD;
    protected boolean fSeenExternalPE;
    protected boolean fStandalone;
    private boolean fStartDTDCalled;
    private XMLString fString;
    private XMLStringBuffer fStringBuffer;
    private XMLStringBuffer fStringBuffer2;
    private String[] fStrings;
    boolean nonValidatingMode;
    DTDGrammar nvGrammarInfo;
    private static final String[] RECOGNIZED_FEATURES = {"http://xml.org/sax/features/validation", "http://apache.org/xml/features/scanner/notify-char-refs"};
    private static final Boolean[] FEATURE_DEFAULTS = {null, Boolean.FALSE};
    private static final String[] RECOGNIZED_PROPERTIES = {"http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-manager"};
    private static final Object[] PROPERTY_DEFAULTS = {null, null, null};

    public XMLDTDScannerImpl() {
        this.fDTDHandler = null;
        this.fAttributes = new XMLAttributesImpl();
        this.fContentStack = new int[5];
        this.fPEStack = new int[5];
        this.fPEReport = new boolean[5];
        this.fStrings = new String[3];
        this.fString = new XMLString();
        this.fStringBuffer = new XMLStringBuffer();
        this.fStringBuffer2 = new XMLStringBuffer();
        this.fLiteral = new XMLString();
        this.fLiteral2 = new XMLString();
        this.fEnumeration = new String[5];
        this.fIgnoreConditionalBuffer = new XMLStringBuffer(XMLChar.MASK_NCNAME);
        this.nvGrammarInfo = null;
        this.nonValidatingMode = true;
    }

    public XMLDTDScannerImpl(SymbolTable symbolTable, XMLErrorReporter xMLErrorReporter, XMLEntityManager xMLEntityManager) {
        this.fDTDHandler = null;
        this.fAttributes = new XMLAttributesImpl();
        this.fContentStack = new int[5];
        this.fPEStack = new int[5];
        this.fPEReport = new boolean[5];
        this.fStrings = new String[3];
        this.fString = new XMLString();
        this.fStringBuffer = new XMLStringBuffer();
        this.fStringBuffer2 = new XMLStringBuffer();
        this.fLiteral = new XMLString();
        this.fLiteral2 = new XMLString();
        this.fEnumeration = new String[5];
        this.fIgnoreConditionalBuffer = new XMLStringBuffer(XMLChar.MASK_NCNAME);
        this.nvGrammarInfo = null;
        this.nonValidatingMode = true;
        this.fSymbolTable = symbolTable;
        this.fErrorReporter = xMLErrorReporter;
        this.fEntityManager = xMLEntityManager;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLDTDScanner
    public void setInputSource(XMLInputSource xMLInputSource) throws IOException {
        if (xMLInputSource == null) {
            if (this.fDTDHandler != null) {
                this.fDTDHandler.startDTD(null, null);
                this.fDTDHandler.endDTD(null);
            }
            if (this.nonValidatingMode) {
                this.nvGrammarInfo.startDTD(null, null);
                this.nvGrammarInfo.endDTD(null);
                return;
            }
            return;
        }
        this.fEntityManager.setEntityHandler(this);
        this.fEntityManager.startDTDEntity(xMLInputSource);
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLDTDScanner
    public boolean scanDTDExternalSubset(boolean z) {
        this.fEntityManager.setEntityHandler(this);
        if (this.fScannerState == 1) {
            this.fSeenExternalDTD = true;
            boolean zScanTextDecl = scanTextDecl();
            if (this.fScannerState == 0) {
                return DEBUG_SCANNER_STATE;
            }
            setScannerState(2);
            if (zScanTextDecl && !z) {
                return true;
            }
        }
        while (scanDecls(z)) {
            if (!z) {
                return true;
            }
        }
        return DEBUG_SCANNER_STATE;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLDTDScanner
    public boolean scanDTDInternalSubset(boolean z, boolean z2, boolean z3) {
        this.fEntityScanner = (XMLEntityReaderImpl) this.fEntityManager.getEntityReader();
        this.fEntityManager.setEntityHandler(this);
        this.fStandalone = z2;
        if (this.fScannerState == 1) {
            if (this.fDTDHandler != null) {
                this.fDTDHandler.startDTD(this.fEntityScanner, null);
                this.fStartDTDCalled = true;
            }
            if (this.nonValidatingMode) {
                this.fStartDTDCalled = true;
                this.nvGrammarInfo.startDTD(this.fEntityScanner, null);
            }
            setScannerState(2);
        }
        while (scanDecls(z)) {
            if (!z) {
                return true;
            }
        }
        if (this.fDTDHandler != null && !z3) {
            this.fDTDHandler.endDTD(null);
        }
        if (this.nonValidatingMode && !z3) {
            this.nvGrammarInfo.endDTD(null);
        }
        setScannerState(1);
        return DEBUG_SCANNER_STATE;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLScanner, com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public void reset(XMLComponentManager xMLComponentManager) {
        super.reset(xMLComponentManager);
        init();
    }

    public void reset() {
        init();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLScanner
    public void reset(PropertyManager propertyManager) {
        setPropertyManager(propertyManager);
        super.reset(propertyManager);
        if (!this.fAttributeCacheInitDone) {
            for (int i = 0; i < this.initialCacheCount; i++) {
                this.attributeValueCache.add(new XMLString());
                this.stringBufferCache.add(new XMLStringBuffer());
            }
            this.fAttributeCacheInitDone = true;
        }
        this.fStringBufferIndex = 0;
        this.fAttributeCacheUsedCount = 0;
        init();
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public String[] getRecognizedFeatures() {
        return (String[]) RECOGNIZED_FEATURES.clone();
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public String[] getRecognizedProperties() {
        return (String[]) RECOGNIZED_PROPERTIES.clone();
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

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLDTDSource
    public void setDTDHandler(XMLDTDHandler xMLDTDHandler) {
        this.fDTDHandler = xMLDTDHandler;
    }

    public XMLDTDHandler getDTDHandler() {
        return this.fDTDHandler;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLDTDContentModelSource
    public void setDTDContentModelHandler(XMLDTDContentModelHandler xMLDTDContentModelHandler) {
        this.fDTDContentModelHandler = xMLDTDContentModelHandler;
    }

    public XMLDTDContentModelHandler getDTDContentModelHandler() {
        return this.fDTDContentModelHandler;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLScanner, com.amazonaws.javax.xml.stream.XMLEntityHandler
    public void startEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2) {
        super.startEntity(str, xMLResourceIdentifier, str2);
        boolean zEquals = str.equals("[dtd]");
        if (zEquals) {
            if (this.fDTDHandler != null && !this.fStartDTDCalled) {
                this.fDTDHandler.startDTD(this.fEntityScanner, null);
            }
            if (this.fDTDHandler != null) {
                this.fDTDHandler.startExternalSubset(xMLResourceIdentifier, null);
            }
            this.fEntityManager.startExternalSubset();
            this.fExtEntityDepth++;
        } else if (str.charAt(0) == '%') {
            pushPEStack(this.fMarkUpDepth, this.fReportEntity);
            if (this.fEntityScanner.isExternal()) {
                this.fExtEntityDepth++;
            }
        }
        if (this.fDTDHandler != null && !zEquals && this.fReportEntity) {
            this.fDTDHandler.startParameterEntity(str, xMLResourceIdentifier, str2, null);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLScanner, com.amazonaws.javax.xml.stream.XMLEntityHandler
    public void endEntity(String str) {
        super.endEntity(str);
        if (this.fScannerState != 0) {
            boolean zPeekReportEntity = this.fReportEntity;
            if (str.startsWith("%")) {
                zPeekReportEntity = peekReportEntity();
                int iPopPEStack = popPEStack();
                if (iPopPEStack == 0 && iPopPEStack < this.fMarkUpDepth) {
                    this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "ILL_FORMED_PARAMETER_ENTITY_WHEN_USED_IN_DECL", new Object[]{this.fEntityManager.fCurrentEntity.name}, (short) 2);
                }
                if (iPopPEStack != this.fMarkUpDepth) {
                    if (this.fValidation) {
                        this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "ImproperDeclarationNesting", new Object[]{str}, (short) 1);
                    }
                    zPeekReportEntity = false;
                }
                if (this.fEntityScanner.isExternal()) {
                    this.fExtEntityDepth--;
                }
            }
            boolean zEquals = str.equals("[dtd]");
            if (this.fDTDHandler != null && !zEquals && zPeekReportEntity) {
                this.fDTDHandler.endParameterEntity(str, null);
            }
            if (zEquals) {
                if (this.fIncludeSectDepth != 0) {
                    reportFatalError("IncludeSectUnterminated", null);
                }
                this.fScannerState = 0;
                this.fEntityManager.endExternalSubset();
                if (this.fDTDHandler != null) {
                    this.fDTDHandler.endExternalSubset(null);
                    this.fDTDHandler.endDTD(null);
                }
                this.fExtEntityDepth--;
            }
        }
    }

    protected final void setScannerState(int i) {
        this.fScannerState = i;
    }

    private static String getScannerStateName(int i) {
        return new StringBuffer().append("??? (").append(i).append(')').toString();
    }

    protected final boolean scanningInternalSubset() {
        if (this.fExtEntityDepth == 0) {
            return true;
        }
        return DEBUG_SCANNER_STATE;
    }

    protected void startPE(String str, boolean z) throws IOException {
        int i = this.fPEDepth;
        String string = new StringBuffer().append("%").append(str).toString();
        if (this.fValidation && !this.fEntityStore.isDeclaredEntity(string)) {
            this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "EntityNotDeclared", new Object[]{str}, (short) 1);
        }
        this.fEntityManager.startEntity(this.fSymbolTable.addSymbol(string), z);
        if (i != this.fPEDepth && this.fEntityScanner.isExternal()) {
            scanTextDecl();
        }
    }

    protected final boolean scanTextDecl() {
        boolean z = DEBUG_SCANNER_STATE;
        if (this.fEntityScanner.skipString("<?xml")) {
            this.fMarkUpDepth++;
            if (isValidNameChar(this.fEntityScanner.peekChar())) {
                this.fStringBuffer.clear();
                this.fStringBuffer.append("xml");
                while (isValidNameChar(this.fEntityScanner.peekChar())) {
                    this.fStringBuffer.append((char) this.fEntityScanner.scanChar());
                }
                scanPIData(this.fSymbolTable.addSymbol(this.fStringBuffer.ch, this.fStringBuffer.offset, this.fStringBuffer.length), this.fString);
            } else {
                scanXMLDeclOrTextDecl(true, this.fStrings);
                this.fMarkUpDepth--;
                String str = this.fStrings[0];
                String str2 = this.fStrings[1];
                this.fEntityScanner.setEncoding(str2);
                if (this.fDTDHandler != null) {
                    this.fDTDHandler.textDecl(str, str2, null);
                }
                z = true;
            }
        }
        this.fEntityManager.fCurrentEntity.mayReadChunks = true;
        return z;
    }

    protected final void scanPIData(String str, XMLString xMLString) {
        this.fMarkUpDepth--;
        if (this.fDTDHandler != null) {
            this.fDTDHandler.processingInstruction(str, xMLString, null);
        }
    }

    protected final void scanComment() {
        this.fReportEntity = DEBUG_SCANNER_STATE;
        scanComment(this.fStringBuffer);
        this.fMarkUpDepth--;
        if (this.fDTDHandler != null) {
            this.fDTDHandler.comment(this.fStringBuffer, null);
        }
        this.fReportEntity = true;
    }

    protected final void scanElementDecl() throws IOException {
        String string;
        this.fReportEntity = DEBUG_SCANNER_STATE;
        if (!skipSeparator(true, !scanningInternalSubset())) {
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ELEMENT_TYPE_IN_ELEMENTDECL", null);
        }
        String strScanName = this.fEntityScanner.scanName();
        if (strScanName == null) {
            reportFatalError("MSG_ELEMENT_TYPE_REQUIRED_IN_ELEMENTDECL", null);
        }
        if (!skipSeparator(true, !scanningInternalSubset())) {
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_CONTENTSPEC_IN_ELEMENTDECL", new Object[]{strScanName});
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.startContentModel(strScanName, null);
        }
        this.fReportEntity = true;
        if (this.fEntityScanner.skipString("EMPTY")) {
            string = "EMPTY";
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.empty(null);
            }
        } else if (this.fEntityScanner.skipString("ANY")) {
            string = "ANY";
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.any(null);
            }
        } else {
            if (!this.fEntityScanner.skipChar(40)) {
                reportFatalError("MSG_OPEN_PAREN_OR_ELEMENT_TYPE_REQUIRED_IN_CHILDREN", new Object[]{strScanName});
            }
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.startGroup(null);
            }
            this.fStringBuffer.clear();
            this.fStringBuffer.append('(');
            this.fMarkUpDepth++;
            skipSeparator(DEBUG_SCANNER_STATE, !scanningInternalSubset());
            if (this.fEntityScanner.skipString("#PCDATA")) {
                scanMixed(strScanName);
            } else {
                scanChildren(strScanName);
            }
            string = this.fStringBuffer.toString();
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.endContentModel(null);
        }
        this.fReportEntity = DEBUG_SCANNER_STATE;
        skipSeparator(DEBUG_SCANNER_STATE, !scanningInternalSubset());
        if (!this.fEntityScanner.skipChar(62)) {
            reportFatalError("ElementDeclUnterminated", new Object[]{strScanName});
        }
        this.fReportEntity = true;
        this.fMarkUpDepth--;
        if (this.fDTDHandler != null) {
            this.fDTDHandler.elementDecl(strScanName, string, null);
        }
        if (this.nonValidatingMode) {
            this.nvGrammarInfo.elementDecl(strScanName, string, null);
        }
    }

    private final void scanMixed(String str) throws IOException {
        this.fStringBuffer.append("#PCDATA");
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.pcdata(null);
        }
        skipSeparator(DEBUG_SCANNER_STATE, !scanningInternalSubset());
        String str2 = null;
        while (this.fEntityScanner.skipChar(124)) {
            this.fStringBuffer.append('|');
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.separator((short) 0, null);
            }
            skipSeparator(DEBUG_SCANNER_STATE, !scanningInternalSubset());
            String strScanName = this.fEntityScanner.scanName();
            if (strScanName == null) {
                reportFatalError("MSG_ELEMENT_TYPE_REQUIRED_IN_MIXED_CONTENT", new Object[]{str});
            }
            this.fStringBuffer.append(strScanName);
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.element(strScanName, null);
            }
            skipSeparator(DEBUG_SCANNER_STATE, !scanningInternalSubset());
            str2 = strScanName;
        }
        if (this.fEntityScanner.skipString(")*")) {
            this.fStringBuffer.append(")*");
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.endGroup(null);
                this.fDTDContentModelHandler.occurrence((short) 3, null);
            }
        } else if (str2 != null) {
            reportFatalError("MixedContentUnterminated", new Object[]{str});
        } else if (this.fEntityScanner.skipChar(41)) {
            this.fStringBuffer.append(')');
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.endGroup(null);
            }
        } else {
            reportFatalError("MSG_CLOSE_PAREN_REQUIRED_IN_CHILDREN", new Object[]{str});
        }
        this.fMarkUpDepth--;
    }

    private final void scanChildren(String str) throws IOException {
        short s;
        this.fContentDepth = 0;
        pushContentStack(0);
        int iPeekChar = 0;
        while (true) {
            if (this.fEntityScanner.skipChar(40)) {
                this.fMarkUpDepth++;
                this.fStringBuffer.append('(');
                if (this.fDTDContentModelHandler != null) {
                    this.fDTDContentModelHandler.startGroup(null);
                }
                pushContentStack(iPeekChar);
                skipSeparator(DEBUG_SCANNER_STATE, !scanningInternalSubset());
                iPeekChar = 0;
            } else {
                skipSeparator(DEBUG_SCANNER_STATE, !scanningInternalSubset());
                String strScanName = this.fEntityScanner.scanName();
                if (strScanName == null) {
                    reportFatalError("MSG_OPEN_PAREN_OR_ELEMENT_TYPE_REQUIRED_IN_CHILDREN", new Object[]{str});
                    return;
                }
                if (this.fDTDContentModelHandler != null) {
                    this.fDTDContentModelHandler.element(strScanName, null);
                }
                this.fStringBuffer.append(strScanName);
                int iPeekChar2 = this.fEntityScanner.peekChar();
                if (iPeekChar2 == 63 || iPeekChar2 == 42 || iPeekChar2 == 43) {
                    if (this.fDTDContentModelHandler != null) {
                        if (iPeekChar2 == 63) {
                            s = 2;
                        } else if (iPeekChar2 == 42) {
                            s = 3;
                        } else {
                            s = 4;
                        }
                        this.fDTDContentModelHandler.occurrence(s, null);
                    }
                    this.fEntityScanner.scanChar();
                    this.fStringBuffer.append((char) iPeekChar2);
                }
                do {
                    skipSeparator(DEBUG_SCANNER_STATE, !scanningInternalSubset());
                    iPeekChar = this.fEntityScanner.peekChar();
                    if (iPeekChar == 44 && iPeekChar != 124) {
                        if (this.fDTDContentModelHandler != null) {
                            this.fDTDContentModelHandler.separator((short) 1, null);
                        }
                        this.fEntityScanner.scanChar();
                        this.fStringBuffer.append(',');
                    } else if (iPeekChar == 124 && iPeekChar != 44) {
                        if (this.fDTDContentModelHandler != null) {
                            this.fDTDContentModelHandler.separator((short) 0, null);
                        }
                        this.fEntityScanner.scanChar();
                        this.fStringBuffer.append('|');
                    } else {
                        if (iPeekChar != 41) {
                            reportFatalError("MSG_CLOSE_PAREN_REQUIRED_IN_CHILDREN", new Object[]{str});
                        }
                        if (this.fDTDContentModelHandler != null) {
                            this.fDTDContentModelHandler.endGroup(null);
                        }
                        iPeekChar = popContentStack();
                        if (this.fEntityScanner.skipString(")?")) {
                            this.fStringBuffer.append(")?");
                            if (this.fDTDContentModelHandler != null) {
                                this.fDTDContentModelHandler.occurrence((short) 2, null);
                            }
                        } else if (this.fEntityScanner.skipString(")+")) {
                            this.fStringBuffer.append(")+");
                            if (this.fDTDContentModelHandler != null) {
                                this.fDTDContentModelHandler.occurrence((short) 4, null);
                            }
                        } else if (this.fEntityScanner.skipString(")*")) {
                            this.fStringBuffer.append(")*");
                            if (this.fDTDContentModelHandler != null) {
                                this.fDTDContentModelHandler.occurrence((short) 3, null);
                            }
                        } else {
                            this.fEntityScanner.scanChar();
                            this.fStringBuffer.append(')');
                        }
                        this.fMarkUpDepth--;
                    }
                    skipSeparator(DEBUG_SCANNER_STATE, !scanningInternalSubset());
                } while (this.fContentDepth != 0);
                return;
            }
        }
    }

    protected final void scanAttlistDecl() throws IOException {
        this.fReportEntity = DEBUG_SCANNER_STATE;
        if (!skipSeparator(true, !scanningInternalSubset() ? true : DEBUG_SCANNER_STATE)) {
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ELEMENT_TYPE_IN_ATTLISTDECL", null);
        }
        String strScanName = this.fEntityScanner.scanName();
        if (strScanName == null) {
            reportFatalError("MSG_ELEMENT_TYPE_REQUIRED_IN_ATTLISTDECL", null);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.startAttlist(strScanName, null);
        }
        if (!skipSeparator(true, !scanningInternalSubset() ? true : DEBUG_SCANNER_STATE)) {
            if (this.fEntityScanner.skipChar(62)) {
                if (this.fDTDHandler != null) {
                    this.fDTDHandler.endAttlist(null);
                }
                this.fMarkUpDepth--;
                return;
            }
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ATTRIBUTE_NAME_IN_ATTDEF", new Object[]{strScanName});
        }
        while (!this.fEntityScanner.skipChar(62)) {
            String strScanName2 = this.fEntityScanner.scanName();
            if (strScanName2 == null) {
                reportFatalError("AttNameRequiredInAttDef", new Object[]{strScanName});
            }
            if (!skipSeparator(true, !scanningInternalSubset() ? true : DEBUG_SCANNER_STATE)) {
                reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ATTTYPE_IN_ATTDEF", new Object[]{strScanName, strScanName2});
            }
            String strScanAttType = scanAttType(strScanName, strScanName2);
            if (!skipSeparator(true, !scanningInternalSubset() ? true : DEBUG_SCANNER_STATE)) {
                reportFatalError("MSG_SPACE_REQUIRED_BEFORE_DEFAULTDECL_IN_ATTDEF", new Object[]{strScanName, strScanName2});
            }
            String strScanAttDefaultDecl = scanAttDefaultDecl(strScanName, strScanName2, strScanAttType, this.fLiteral, this.fLiteral2);
            String[] strArr = null;
            if ((this.fDTDHandler != null || this.nonValidatingMode) && this.fEnumerationCount != 0) {
                strArr = new String[this.fEnumerationCount];
                System.arraycopy(this.fEnumeration, 0, strArr, 0, this.fEnumerationCount);
            }
            if (strScanAttDefaultDecl != null && (strScanAttDefaultDecl.equals("#REQUIRED") || strScanAttDefaultDecl.equals("#IMPLIED"))) {
                if (this.fDTDHandler != null) {
                    this.fDTDHandler.attributeDecl(strScanName, strScanName2, strScanAttType, strArr, strScanAttDefaultDecl, null, null, null);
                }
                if (this.nonValidatingMode) {
                    this.nvGrammarInfo.attributeDecl(strScanName, strScanName2, strScanAttType, strArr, strScanAttDefaultDecl, null, null, null);
                }
            } else {
                if (this.fDTDHandler != null) {
                    this.fDTDHandler.attributeDecl(strScanName, strScanName2, strScanAttType, strArr, strScanAttDefaultDecl, this.fLiteral, this.fLiteral2, null);
                }
                if (this.nonValidatingMode) {
                    this.nvGrammarInfo.attributeDecl(strScanName, strScanName2, strScanAttType, strArr, strScanAttDefaultDecl, this.fLiteral, this.fLiteral2, null);
                }
            }
            skipSeparator(DEBUG_SCANNER_STATE, !scanningInternalSubset() ? true : DEBUG_SCANNER_STATE);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.endAttlist(null);
        }
        this.fMarkUpDepth--;
        this.fReportEntity = true;
    }

    private final String scanAttType(String str, String str2) throws IOException {
        int iScanChar;
        int iScanChar2;
        this.fEnumerationCount = 0;
        if (this.fEntityScanner.skipString("CDATA")) {
            return "CDATA";
        }
        if (this.fEntityScanner.skipString("IDREFS")) {
            return "IDREFS";
        }
        if (this.fEntityScanner.skipString("IDREF")) {
            return "IDREF";
        }
        if (this.fEntityScanner.skipString("ID")) {
            return "ID";
        }
        if (this.fEntityScanner.skipString("ENTITY")) {
            return "ENTITY";
        }
        if (this.fEntityScanner.skipString("ENTITIES")) {
            return "ENTITIES";
        }
        if (this.fEntityScanner.skipString("NMTOKENS")) {
            return "NMTOKENS";
        }
        if (this.fEntityScanner.skipString("NMTOKEN")) {
            return "NMTOKEN";
        }
        if (this.fEntityScanner.skipString("NOTATION")) {
            if (!skipSeparator(true, !scanningInternalSubset())) {
                reportFatalError("MSG_SPACE_REQUIRED_AFTER_NOTATION_IN_NOTATIONTYPE", new Object[]{str, str2});
            }
            if (this.fEntityScanner.scanChar() != 40) {
                reportFatalError("MSG_OPEN_PAREN_REQUIRED_IN_NOTATIONTYPE", new Object[]{str, str2});
            }
            this.fMarkUpDepth++;
            do {
                skipSeparator(DEBUG_SCANNER_STATE, !scanningInternalSubset());
                String strScanName = this.fEntityScanner.scanName();
                if (strScanName == null) {
                    reportFatalError("MSG_NAME_REQUIRED_IN_NOTATIONTYPE", new Object[]{str, str2});
                }
                ensureEnumerationSize(this.fEnumerationCount + 1);
                String[] strArr = this.fEnumeration;
                int i = this.fEnumerationCount;
                this.fEnumerationCount = i + 1;
                strArr[i] = strScanName;
                skipSeparator(DEBUG_SCANNER_STATE, !scanningInternalSubset());
                iScanChar2 = this.fEntityScanner.scanChar();
            } while (iScanChar2 == 124);
            if (iScanChar2 != 41) {
                reportFatalError("NotationTypeUnterminated", new Object[]{str, str2});
            }
            this.fMarkUpDepth--;
            return "NOTATION";
        }
        if (this.fEntityScanner.scanChar() != 40) {
            reportFatalError("AttTypeRequiredInAttDef", new Object[]{str, str2});
        }
        this.fMarkUpDepth++;
        do {
            skipSeparator(DEBUG_SCANNER_STATE, !scanningInternalSubset());
            String strScanNmtoken = this.fEntityScanner.scanNmtoken();
            if (strScanNmtoken == null) {
                reportFatalError("MSG_NMTOKEN_REQUIRED_IN_ENUMERATION", new Object[]{str, str2});
            }
            ensureEnumerationSize(this.fEnumerationCount + 1);
            String[] strArr2 = this.fEnumeration;
            int i2 = this.fEnumerationCount;
            this.fEnumerationCount = i2 + 1;
            strArr2[i2] = strScanNmtoken;
            skipSeparator(DEBUG_SCANNER_STATE, !scanningInternalSubset());
            iScanChar = this.fEntityScanner.scanChar();
        } while (iScanChar == 124);
        if (iScanChar != 41) {
            reportFatalError("EnumerationUnterminated", new Object[]{str, str2});
        }
        this.fMarkUpDepth--;
        return "ENUMERATION";
    }

    protected final String scanAttDefaultDecl(String str, String str2, String str3, XMLString xMLString, XMLString xMLString2) {
        String str4;
        this.fString.clear();
        xMLString.clear();
        if (this.fEntityScanner.skipString("#REQUIRED")) {
            return "#REQUIRED";
        }
        if (this.fEntityScanner.skipString("#IMPLIED")) {
            return "#IMPLIED";
        }
        if (!this.fEntityScanner.skipString("#FIXED")) {
            str4 = null;
        } else {
            if (!skipSeparator(true, !scanningInternalSubset())) {
                reportFatalError("MSG_SPACE_REQUIRED_AFTER_FIXED_IN_DEFAULTDECL", new Object[]{str, str2});
            }
            str4 = "#FIXED";
        }
        scanAttributeValue(xMLString, xMLString2, str2, this.fAttributes, 0, !this.fStandalone && (this.fSeenExternalDTD || this.fSeenExternalPE));
        return str4;
    }

    private final void scanEntityDecl() throws IOException {
        boolean z;
        boolean zSkipChar;
        String strScanName;
        this.fReportEntity = DEBUG_SCANNER_STATE;
        if (this.fEntityScanner.skipSpaces()) {
            if (this.fEntityScanner.skipChar(37)) {
                if (skipSeparator(true, !scanningInternalSubset())) {
                    z = false;
                    zSkipChar = true;
                } else if (scanningInternalSubset()) {
                    reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ENTITY_NAME_IN_ENTITYDECL", null);
                    z = false;
                    zSkipChar = true;
                } else if (this.fEntityScanner.peekChar() == 37) {
                    skipSeparator(DEBUG_SCANNER_STATE, !scanningInternalSubset());
                    z = false;
                    zSkipChar = true;
                } else {
                    z = true;
                    zSkipChar = false;
                }
            } else {
                z = false;
                zSkipChar = false;
            }
        } else if (scanningInternalSubset() || !this.fEntityScanner.skipChar(37)) {
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ENTITY_NAME_IN_ENTITYDECL", null);
            z = false;
            zSkipChar = false;
        } else if (this.fEntityScanner.skipSpaces()) {
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_PERCENT_IN_PEDECL", null);
            z = false;
            zSkipChar = false;
        } else {
            z = true;
            zSkipChar = false;
        }
        if (z) {
            while (true) {
                String strScanName2 = this.fEntityScanner.scanName();
                if (strScanName2 == null) {
                    reportFatalError("NameRequiredInPEReference", null);
                } else if (!this.fEntityScanner.skipChar(59)) {
                    reportFatalError("SemicolonRequiredInPEReference", new Object[]{strScanName2});
                } else {
                    startPE(strScanName2, DEBUG_SCANNER_STATE);
                }
                this.fEntityScanner.skipSpaces();
                if (!this.fEntityScanner.skipChar(37)) {
                    break;
                }
                if (!zSkipChar) {
                    if (skipSeparator(true, !scanningInternalSubset())) {
                        zSkipChar = true;
                        break;
                    }
                    zSkipChar = this.fEntityScanner.skipChar(37);
                }
            }
        }
        String strScanName3 = this.fEntityScanner.scanName();
        if (strScanName3 == null) {
            reportFatalError("MSG_ENTITY_NAME_REQUIRED_IN_ENTITYDECL", null);
        }
        if (!skipSeparator(true, !scanningInternalSubset())) {
            reportFatalError("MSG_SPACE_REQUIRED_AFTER_ENTITY_NAME_IN_ENTITYDECL", new Object[]{strScanName3});
        }
        scanExternalID(this.fStrings, DEBUG_SCANNER_STATE);
        String str = this.fStrings[0];
        String str2 = this.fStrings[1];
        if (zSkipChar && str != null) {
            this.fSeenExternalPE = true;
        }
        boolean zSkipSeparator = skipSeparator(true, !scanningInternalSubset());
        if (zSkipChar || !this.fEntityScanner.skipString("NDATA")) {
            strScanName = null;
        } else {
            if (!zSkipSeparator) {
                reportFatalError("MSG_SPACE_REQUIRED_BEFORE_NDATA_IN_UNPARSED_ENTITYDECL", new Object[]{strScanName3});
            }
            if (!skipSeparator(true, !scanningInternalSubset())) {
                reportFatalError("MSG_SPACE_REQUIRED_BEFORE_NOTATION_NAME_IN_UNPARSED_ENTITYDECL", new Object[]{strScanName3});
            }
            strScanName = this.fEntityScanner.scanName();
            if (strScanName == null) {
                reportFatalError("MSG_NOTATION_NAME_REQUIRED_FOR_UNPARSED_ENTITYDECL", new Object[]{strScanName3});
            }
        }
        if (str == null) {
            scanEntityValue(this.fLiteral, this.fLiteral2);
            this.fStringBuffer.clear();
            this.fStringBuffer2.clear();
            this.fStringBuffer.append(this.fLiteral.ch, this.fLiteral.offset, this.fLiteral.length);
            this.fStringBuffer2.append(this.fLiteral2.ch, this.fLiteral2.offset, this.fLiteral2.length);
        }
        skipSeparator(DEBUG_SCANNER_STATE, !scanningInternalSubset());
        if (!this.fEntityScanner.skipChar(62)) {
            reportFatalError("EntityDeclUnterminated", new Object[]{strScanName3});
        }
        this.fMarkUpDepth--;
        String string = zSkipChar ? new StringBuffer().append("%").append(strScanName3).toString() : strScanName3;
        if (str != null) {
            String baseSystemId = this.fEntityScanner.getBaseSystemId();
            if (strScanName != null) {
                this.fEntityStore.addUnparsedEntity(string, str2, str, baseSystemId, strScanName);
            } else {
                this.fEntityStore.addExternalEntity(string, str2, str, baseSystemId);
            }
            if (this.fDTDHandler != null) {
                this.fResourceIdentifier.setValues(str2, str, baseSystemId, XMLEntityManager.expandSystemId(str, baseSystemId));
                if (strScanName != null) {
                    this.fDTDHandler.unparsedEntityDecl(string, this.fResourceIdentifier, strScanName, null);
                } else {
                    this.fDTDHandler.externalEntityDecl(string, this.fResourceIdentifier, null);
                }
            }
        } else {
            this.fEntityStore.addInternalEntity(string, this.fStringBuffer.toString());
            if (this.fDTDHandler != null) {
                this.fDTDHandler.internalEntityDecl(string, this.fStringBuffer, this.fStringBuffer2, null);
            }
        }
        this.fReportEntity = true;
    }

    protected final void scanEntityValue(XMLString xMLString, XMLString xMLString2) throws IOException {
        int iScanChar = this.fEntityScanner.scanChar();
        if (iScanChar != 39 && iScanChar != 34) {
            reportFatalError("OpenQuoteMissingInDecl", null);
        }
        int i = this.fEntityDepth;
        XMLString xMLString3 = this.fString;
        XMLString xMLString4 = this.fString;
        if (this.fEntityScanner.scanLiteral(iScanChar, this.fString) != iScanChar) {
            this.fStringBuffer.clear();
            this.fStringBuffer2.clear();
            do {
                this.fStringBuffer.append(this.fString);
                this.fStringBuffer2.append(this.fString);
                if (this.fEntityScanner.skipChar(38)) {
                    if (this.fEntityScanner.skipChar(35)) {
                        this.fStringBuffer2.append("&#");
                        scanCharReferenceValue(this.fStringBuffer, this.fStringBuffer2);
                    } else {
                        this.fStringBuffer.append('&');
                        this.fStringBuffer2.append('&');
                        String strScanName = this.fEntityScanner.scanName();
                        if (strScanName == null) {
                            reportFatalError("NameRequiredInReference", null);
                        } else {
                            this.fStringBuffer.append(strScanName);
                            this.fStringBuffer2.append(strScanName);
                        }
                        if (!this.fEntityScanner.skipChar(59)) {
                            reportFatalError("SemicolonRequiredInReference", new Object[]{strScanName});
                        } else {
                            this.fStringBuffer.append(';');
                            this.fStringBuffer2.append(';');
                        }
                    }
                } else if (this.fEntityScanner.skipChar(37)) {
                    do {
                        this.fStringBuffer2.append('%');
                        String strScanName2 = this.fEntityScanner.scanName();
                        if (strScanName2 == null) {
                            reportFatalError("NameRequiredInPEReference", null);
                        } else if (!this.fEntityScanner.skipChar(59)) {
                            reportFatalError("SemicolonRequiredInPEReference", new Object[]{strScanName2});
                        } else {
                            if (scanningInternalSubset()) {
                                reportFatalError("PEReferenceWithinMarkup", new Object[]{strScanName2});
                            }
                            this.fStringBuffer2.append(strScanName2);
                            this.fStringBuffer2.append(';');
                        }
                        startPE(strScanName2, true);
                        this.fEntityScanner.skipSpaces();
                    } while (this.fEntityScanner.skipChar(37));
                } else {
                    int iPeekChar = this.fEntityScanner.peekChar();
                    if (XMLChar.isHighSurrogate(iPeekChar)) {
                        scanSurrogates(this.fStringBuffer2);
                    } else if (isInvalidLiteral(iPeekChar)) {
                        reportFatalError("InvalidCharInLiteral", new Object[]{Integer.toHexString(iPeekChar)});
                        this.fEntityScanner.scanChar();
                    } else if (iPeekChar != iScanChar || i != this.fEntityDepth) {
                        this.fStringBuffer.append((char) iPeekChar);
                        this.fStringBuffer2.append((char) iPeekChar);
                        this.fEntityScanner.scanChar();
                    }
                }
            } while (this.fEntityScanner.scanLiteral(iScanChar, this.fString) != iScanChar);
            this.fStringBuffer.append(this.fString);
            this.fStringBuffer2.append(this.fString);
            xMLString3 = this.fStringBuffer;
            xMLString4 = this.fStringBuffer2;
        }
        xMLString.setValues(xMLString3);
        xMLString2.setValues(xMLString4);
        if (!this.fEntityScanner.skipChar(iScanChar)) {
            reportFatalError("CloseQuoteMissingInDecl", null);
        }
    }

    private final void scanNotationDecl() throws IOException {
        this.fReportEntity = DEBUG_SCANNER_STATE;
        if (!skipSeparator(true, !scanningInternalSubset())) {
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_NOTATION_NAME_IN_NOTATIONDECL", null);
        }
        String strScanName = this.fEntityScanner.scanName();
        if (strScanName == null) {
            reportFatalError("MSG_NOTATION_NAME_REQUIRED_IN_NOTATIONDECL", null);
        }
        if (!skipSeparator(true, !scanningInternalSubset())) {
            reportFatalError("MSG_SPACE_REQUIRED_AFTER_NOTATION_NAME_IN_NOTATIONDECL", new Object[]{strScanName});
        }
        scanExternalID(this.fStrings, true);
        String str = this.fStrings[0];
        String str2 = this.fStrings[1];
        String baseSystemId = this.fEntityScanner.getBaseSystemId();
        if (str == null && str2 == null) {
            reportFatalError("ExternalIDorPublicIDRequired", new Object[]{strScanName});
        }
        skipSeparator(DEBUG_SCANNER_STATE, !scanningInternalSubset());
        if (!this.fEntityScanner.skipChar(62)) {
            reportFatalError("NotationDeclUnterminated", new Object[]{strScanName});
        }
        this.fMarkUpDepth--;
        this.fResourceIdentifier.setValues(str2, str, baseSystemId, XMLEntityManager.expandSystemId(str, baseSystemId));
        if (this.nonValidatingMode) {
            this.nvGrammarInfo.notationDecl(strScanName, this.fResourceIdentifier, null);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.notationDecl(strScanName, this.fResourceIdentifier, null);
        }
        this.fReportEntity = true;
    }

    private final void scanConditionalSect(int i) throws IOException {
        this.fReportEntity = DEBUG_SCANNER_STATE;
        skipSeparator(DEBUG_SCANNER_STATE, !scanningInternalSubset());
        if (this.fEntityScanner.skipString("INCLUDE")) {
            skipSeparator(DEBUG_SCANNER_STATE, !scanningInternalSubset());
            if (i != this.fPEDepth && this.fValidation) {
                this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "INVALID_PE_IN_CONDITIONAL", new Object[]{this.fEntityManager.fCurrentEntity.name}, (short) 1);
            }
            if (!this.fEntityScanner.skipChar(91)) {
                reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
            }
            if (this.fDTDHandler != null) {
                this.fDTDHandler.startConditional((short) 0, null);
            }
            this.fIncludeSectDepth++;
            this.fReportEntity = true;
            return;
        }
        if (this.fEntityScanner.skipString("IGNORE")) {
            skipSeparator(DEBUG_SCANNER_STATE, !scanningInternalSubset());
            if (i != this.fPEDepth && this.fValidation) {
                this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "INVALID_PE_IN_CONDITIONAL", new Object[]{this.fEntityManager.fCurrentEntity.name}, (short) 1);
            }
            if (this.fDTDHandler != null) {
                this.fDTDHandler.startConditional((short) 1, null);
            }
            if (!this.fEntityScanner.skipChar(91)) {
                reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
            }
            this.fReportEntity = true;
            int i2 = this.fIncludeSectDepth + 1;
            this.fIncludeSectDepth = i2;
            if (this.fDTDHandler != null) {
                this.fIgnoreConditionalBuffer.clear();
            }
            while (true) {
                if (this.fEntityScanner.skipChar(60)) {
                    if (this.fDTDHandler != null) {
                        this.fIgnoreConditionalBuffer.append(XMLStreamWriterImpl.OPEN_START_TAG);
                    }
                    if (this.fEntityScanner.skipChar(33)) {
                        if (this.fEntityScanner.skipChar(91)) {
                            if (this.fDTDHandler != null) {
                                this.fIgnoreConditionalBuffer.append("![");
                            }
                            this.fIncludeSectDepth++;
                        } else if (this.fDTDHandler != null) {
                            this.fIgnoreConditionalBuffer.append("!");
                        }
                    }
                } else if (this.fEntityScanner.skipChar(93)) {
                    if (this.fDTDHandler != null) {
                        this.fIgnoreConditionalBuffer.append(']');
                    }
                    if (this.fEntityScanner.skipChar(93)) {
                        if (this.fDTDHandler != null) {
                            this.fIgnoreConditionalBuffer.append(']');
                        }
                        while (this.fEntityScanner.skipChar(93)) {
                            if (this.fDTDHandler != null) {
                                this.fIgnoreConditionalBuffer.append(']');
                            }
                        }
                        if (this.fEntityScanner.skipChar(62)) {
                            int i3 = this.fIncludeSectDepth;
                            this.fIncludeSectDepth = i3 - 1;
                            if (i3 == i2) {
                                this.fMarkUpDepth--;
                                if (this.fDTDHandler != null) {
                                    this.fLiteral.setValues(this.fIgnoreConditionalBuffer.ch, 0, this.fIgnoreConditionalBuffer.length - 2);
                                    this.fDTDHandler.ignoredCharacters(this.fLiteral, null);
                                    this.fDTDHandler.endConditional(null);
                                    return;
                                }
                                return;
                            }
                            if (this.fDTDHandler != null) {
                                this.fIgnoreConditionalBuffer.append('>');
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                } else {
                    int iScanChar = this.fEntityScanner.scanChar();
                    if (this.fScannerState == 0) {
                        reportFatalError("IgnoreSectUnterminated", null);
                        return;
                    } else if (this.fDTDHandler != null) {
                        this.fIgnoreConditionalBuffer.append((char) iScanChar);
                    }
                }
            }
        } else {
            reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
        }
    }

    protected final boolean scanDecls(boolean z) throws IOException {
        skipSeparator(DEBUG_SCANNER_STATE, true);
        boolean z2 = true;
        while (z2 && this.fScannerState == 2) {
            if (this.fEntityScanner.skipChar(60)) {
                this.fMarkUpDepth++;
                if (this.fEntityScanner.skipChar(63)) {
                    this.fStringBuffer.clear();
                    scanPI(this.fStringBuffer);
                } else if (this.fEntityScanner.skipChar(33)) {
                    if (this.fEntityScanner.skipChar(45)) {
                        if (!this.fEntityScanner.skipChar(45)) {
                            reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
                        } else {
                            scanComment();
                        }
                    } else if (this.fEntityScanner.skipString("ELEMENT")) {
                        scanElementDecl();
                    } else if (this.fEntityScanner.skipString("ATTLIST")) {
                        scanAttlistDecl();
                    } else if (this.fEntityScanner.skipString("ENTITY")) {
                        scanEntityDecl();
                    } else if (this.fEntityScanner.skipString("NOTATION")) {
                        scanNotationDecl();
                    } else if (this.fEntityScanner.skipChar(91) && !scanningInternalSubset()) {
                        scanConditionalSect(this.fPEDepth);
                    } else {
                        this.fMarkUpDepth--;
                        reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
                    }
                } else {
                    this.fMarkUpDepth--;
                    reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
                }
            } else if (this.fIncludeSectDepth > 0 && this.fEntityScanner.skipChar(93)) {
                if (!this.fEntityScanner.skipChar(93) || !this.fEntityScanner.skipChar(62)) {
                    reportFatalError("IncludeSectUnterminated", null);
                }
                if (this.fDTDHandler != null) {
                    this.fDTDHandler.endConditional(null);
                }
                this.fIncludeSectDepth--;
                this.fMarkUpDepth--;
            } else {
                if (scanningInternalSubset() && this.fEntityScanner.peekChar() == 93) {
                    return DEBUG_SCANNER_STATE;
                }
                if (!this.fEntityScanner.skipSpaces()) {
                    reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
                }
            }
            skipSeparator(DEBUG_SCANNER_STATE, true);
            z2 = z;
        }
        return this.fScannerState != 0;
    }

    private boolean skipSeparator(boolean z, boolean z2) throws IOException {
        boolean z3 = DEBUG_SCANNER_STATE;
        int i = this.fPEDepth;
        boolean zSkipSpaces = this.fEntityScanner.skipSpaces();
        if (!z2 || !this.fEntityScanner.skipChar(37)) {
            if (!z || zSkipSpaces || i != this.fPEDepth) {
                z3 = true;
            }
            return z3;
        }
        do {
            String strScanName = this.fEntityScanner.scanName();
            if (strScanName == null) {
                reportFatalError("NameRequiredInPEReference", null);
            } else if (!this.fEntityScanner.skipChar(59)) {
                reportFatalError("SemicolonRequiredInPEReference", new Object[]{strScanName});
            }
            startPE(strScanName, DEBUG_SCANNER_STATE);
            this.fEntityScanner.skipSpaces();
        } while (this.fEntityScanner.skipChar(37));
        return true;
    }

    private final void pushContentStack(int i) {
        if (this.fContentStack.length == this.fContentDepth) {
            int[] iArr = new int[this.fContentDepth * 2];
            System.arraycopy(this.fContentStack, 0, iArr, 0, this.fContentDepth);
            this.fContentStack = iArr;
        }
        int[] iArr2 = this.fContentStack;
        int i2 = this.fContentDepth;
        this.fContentDepth = i2 + 1;
        iArr2[i2] = i;
    }

    private final int popContentStack() {
        int[] iArr = this.fContentStack;
        int i = this.fContentDepth - 1;
        this.fContentDepth = i;
        return iArr[i];
    }

    private final void pushPEStack(int i, boolean z) {
        if (this.fPEStack.length == this.fPEDepth) {
            int[] iArr = new int[this.fPEDepth * 2];
            System.arraycopy(this.fPEStack, 0, iArr, 0, this.fPEDepth);
            this.fPEStack = iArr;
            boolean[] zArr = new boolean[this.fPEDepth * 2];
            System.arraycopy(this.fPEReport, 0, zArr, 0, this.fPEDepth);
            this.fPEReport = zArr;
        }
        this.fPEReport[this.fPEDepth] = z;
        int[] iArr2 = this.fPEStack;
        int i2 = this.fPEDepth;
        this.fPEDepth = i2 + 1;
        iArr2[i2] = i;
    }

    private final int popPEStack() {
        int[] iArr = this.fPEStack;
        int i = this.fPEDepth - 1;
        this.fPEDepth = i;
        return iArr[i];
    }

    private final boolean peekReportEntity() {
        return this.fPEReport[this.fPEDepth - 1];
    }

    private final void ensureEnumerationSize(int i) {
        if (this.fEnumeration.length == i) {
            String[] strArr = new String[i * 2];
            System.arraycopy(this.fEnumeration, 0, strArr, 0, i);
            this.fEnumeration = strArr;
        }
    }

    private void init() {
        this.fStartDTDCalled = DEBUG_SCANNER_STATE;
        this.fExtEntityDepth = 0;
        this.fIncludeSectDepth = 0;
        this.fMarkUpDepth = 0;
        this.fPEDepth = 0;
        this.fStandalone = DEBUG_SCANNER_STATE;
        this.fSeenExternalDTD = DEBUG_SCANNER_STATE;
        this.fSeenExternalPE = DEBUG_SCANNER_STATE;
        this.fSymbolTable = (SymbolTable) this.fPropertyManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        setScannerState(1);
        this.nvGrammarInfo = new DTDGrammar(this.fSymbolTable);
    }

    public DTDGrammar getGrammar() {
        return this.nvGrammarInfo;
    }
}

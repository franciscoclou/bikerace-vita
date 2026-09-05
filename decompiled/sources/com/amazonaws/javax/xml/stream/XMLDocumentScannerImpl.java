package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.stream.dtd.DTDGrammarUtil;
import com.amazonaws.javax.xml.stream.xerces.util.NamespaceSupport;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import com.amazonaws.javax.xml.stream.xerces.util.XMLResourceIdentifierImpl;
import com.amazonaws.javax.xml.stream.xerces.util.XMLStringBuffer;
import com.amazonaws.javax.xml.stream.xerces.xni.NamespaceContext;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLResourceIdentifier;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLString;
import com.amazonaws.javax.xml.stream.xerces.xni.XNIException;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponentManager;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLConfigurationException;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLDTDScanner;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLInputSource;
import java.io.EOFException;
import java.io.IOException;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class XMLDocumentScannerImpl extends XMLDocumentFragmentScannerImpl {
    protected static final int SCANNER_STATE_DTD_EXTERNAL = 46;
    protected static final int SCANNER_STATE_DTD_EXTERNAL_DECLS = 47;
    protected static final int SCANNER_STATE_DTD_INTERNAL_DECLS = 45;
    protected static final int SCANNER_STATE_NO_SUCH_ELEMENT_EXCEPTION = 48;
    protected static final int SCANNER_STATE_PROLOG = 43;
    protected static final int SCANNER_STATE_TRAILING_MISC = 44;
    protected static final int SCANNER_STATE_XML_DECL = 42;
    protected boolean fBindNamespaces;
    protected String fDoctypeName;
    protected String fDoctypePublicId;
    protected String fDoctypeSystemId;
    protected boolean fEndOfDocument;
    protected boolean fScanEndElement;
    protected int fScannerLastState;
    protected boolean fSeenDoctypeDecl;
    protected static final String LOAD_EXTERNAL_DTD = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
    protected static final String DISALLOW_DOCTYPE_DECL_FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
    private static final String[] RECOGNIZED_FEATURES = {LOAD_EXTERNAL_DTD, DISALLOW_DOCTYPE_DECL_FEATURE};
    private static final Boolean[] FEATURE_DEFAULTS = {Boolean.TRUE, Boolean.FALSE};
    protected static final String DTD_SCANNER = "http://apache.org/xml/properties/internal/dtd-scanner";
    protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    private static final String[] RECOGNIZED_PROPERTIES = {DTD_SCANNER, VALIDATION_MANAGER};
    private static final Object[] PROPERTY_DEFAULTS = {null, null};
    public static final char[] DOCTYPE = {'D', 'O', 'C', 'T', 'Y', 'P', 'E'};
    public static final char[] COMMENTSTRING = {'-', '-'};
    protected XMLDTDScanner fDTDScanner = null;
    protected XMLStringBuffer fDTDDecl = null;
    protected boolean fReadingDTD = false;
    protected NamespaceContext fNamespaceContext = new NamespaceSupport();
    protected boolean fLoadExternalDTD = true;
    protected XMLDocumentFragmentScannerImpl.Driver fXMLDeclDriver = new XMLDeclDriver();
    protected XMLDocumentFragmentScannerImpl.Driver fPrologDriver = new PrologDriver();
    protected XMLDocumentFragmentScannerImpl.Driver fDTDDriver = null;
    protected XMLDocumentFragmentScannerImpl.Driver fTrailingMiscDriver = new TrailingMiscDriver();
    protected int fStartPos = 0;
    protected int fEndPos = 0;
    protected boolean fSeenInternalSubset = false;
    private String[] fStrings = new String[3];
    private XMLString fString = new XMLString();
    protected boolean fReadingAttributes = false;
    protected XMLBufferListenerImpl fScannerBufferlistener = new XMLBufferListenerImpl();

    @Override // com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl, com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLDocumentScanner
    public void setInputSource(XMLInputSource xMLInputSource) throws IOException {
        this.fEntityManager.setEntityHandler(this);
        this.fEntityManager.startDocumentEntity(xMLInputSource);
        setScannerState(7);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl, com.amazonaws.javax.xml.stream.XMLScanner
    public void reset(PropertyManager propertyManager) {
        super.reset(propertyManager);
        this.fDoctypeName = null;
        this.fDoctypePublicId = null;
        this.fDoctypeSystemId = null;
        this.fSeenDoctypeDecl = false;
        this.fNamespaceContext.reset();
        this.fDisallowDoctype = !((Boolean) propertyManager.getProperty(XMLInputFactory.SUPPORT_DTD)).booleanValue();
        this.fBindNamespaces = ((Boolean) propertyManager.getProperty(XMLInputFactory.IS_NAMESPACE_AWARE)).booleanValue();
        this.fLoadExternalDTD = ((Boolean) propertyManager.getProperty("http://java.sun.com/xml/stream/properties/ignore-external-dtd")).booleanValue() ? false : true;
        this.fEndOfDocument = false;
        setScannerState(7);
        setDriver(this.fXMLDeclDriver);
        this.fSeenInternalSubset = false;
        if (this.fDTDScanner != null) {
            ((XMLDTDScannerImpl) this.fDTDScanner).reset(propertyManager);
        }
        this.fEndPos = 0;
        this.fStartPos = 0;
        if (this.fDTDDecl != null) {
            this.fDTDDecl.clear();
        }
    }

    public int getScannetState() {
        return this.fScannerState;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl, com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLDocumentScanner
    public int next() {
        if (this.fScannerLastState == 2 && this.fBindNamespaces) {
            this.fScannerLastState = -1;
            this.fNamespaceContext.popContext();
        }
        int next = this.fDriver.next();
        this.fScannerLastState = next;
        return next;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl, com.amazonaws.javax.xml.stream.XMLScanner, com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public void reset(XMLComponentManager xMLComponentManager) {
        super.reset(xMLComponentManager);
        this.fDoctypeName = null;
        this.fDoctypePublicId = null;
        this.fDoctypeSystemId = null;
        this.fSeenDoctypeDecl = false;
        this.fNamespaceContext.reset();
        try {
            this.fLoadExternalDTD = xMLComponentManager.getFeature(LOAD_EXTERNAL_DTD);
        } catch (XMLConfigurationException e) {
            this.fLoadExternalDTD = true;
        }
        try {
            this.fDisallowDoctype = xMLComponentManager.getFeature(DISALLOW_DOCTYPE_DECL_FEATURE);
        } catch (XMLConfigurationException e2) {
            this.fDisallowDoctype = false;
        }
        this.fDTDScanner = (XMLDTDScanner) xMLComponentManager.getProperty(DTD_SCANNER);
        this.fEndPos = 0;
        this.fStartPos = 0;
        if (this.fDTDDecl != null) {
            this.fDTDDecl.clear();
        }
        setScannerState(SCANNER_STATE_XML_DECL);
        setDriver(this.fXMLDeclDriver);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl, com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public String[] getRecognizedFeatures() {
        String[] recognizedFeatures = super.getRecognizedFeatures();
        int length = recognizedFeatures != null ? recognizedFeatures.length : 0;
        String[] strArr = new String[RECOGNIZED_FEATURES.length + length];
        if (recognizedFeatures != null) {
            System.arraycopy(recognizedFeatures, 0, strArr, 0, recognizedFeatures.length);
        }
        System.arraycopy(RECOGNIZED_FEATURES, 0, strArr, length, RECOGNIZED_FEATURES.length);
        return strArr;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl, com.amazonaws.javax.xml.stream.XMLScanner, com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public void setFeature(String str, boolean z) {
        super.setFeature(str, z);
        if (str.startsWith(Constants.XERCES_FEATURE_PREFIX) && str.substring(Constants.XERCES_FEATURE_PREFIX.length()).equals(Constants.LOAD_EXTERNAL_DTD_FEATURE)) {
            this.fLoadExternalDTD = z;
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl, com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public String[] getRecognizedProperties() {
        String[] recognizedProperties = super.getRecognizedProperties();
        int length = recognizedProperties != null ? recognizedProperties.length : 0;
        String[] strArr = new String[RECOGNIZED_PROPERTIES.length + length];
        if (recognizedProperties != null) {
            System.arraycopy(recognizedProperties, 0, strArr, 0, recognizedProperties.length);
        }
        System.arraycopy(RECOGNIZED_PROPERTIES, 0, strArr, length, RECOGNIZED_PROPERTIES.length);
        return strArr;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl, com.amazonaws.javax.xml.stream.XMLScanner, com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public void setProperty(String str, Object obj) {
        super.setProperty(str, obj);
        if (str.startsWith(Constants.XERCES_PROPERTY_PREFIX) && str.substring(Constants.XERCES_PROPERTY_PREFIX.length()).equals(Constants.DTD_SCANNER_PROPERTY)) {
            this.fDTDScanner = (XMLDTDScanner) obj;
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl, com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public Boolean getFeatureDefault(String str) {
        for (int i = 0; i < RECOGNIZED_FEATURES.length; i++) {
            if (RECOGNIZED_FEATURES[i].equals(str)) {
                return FEATURE_DEFAULTS[i];
            }
        }
        return super.getFeatureDefault(str);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl, com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public Object getPropertyDefault(String str) {
        for (int i = 0; i < RECOGNIZED_PROPERTIES.length; i++) {
            if (RECOGNIZED_PROPERTIES[i].equals(str)) {
                return PROPERTY_DEFAULTS[i];
            }
        }
        return super.getPropertyDefault(str);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl, com.amazonaws.javax.xml.stream.XMLScanner, com.amazonaws.javax.xml.stream.XMLEntityHandler
    public void startEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2) {
        super.startEntity(str, xMLResourceIdentifier, str2);
        if (!str.equals("[xml]") && this.fEntityScanner.isExternal() && this.fReplaceEntityReferences) {
            setScannerState(36);
        }
        if (this.fDocumentHandler != null && str.equals("[xml]")) {
            this.fDocumentHandler.startDocument(this.fEntityScanner, str2, this.fNamespaceContext, null);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl, com.amazonaws.javax.xml.stream.XMLScanner, com.amazonaws.javax.xml.stream.XMLEntityHandler
    public void endEntity(String str) throws EOFException {
        super.endEntity(str);
        if (str.equals("[xml]")) {
            if (this.fMarkupDepth == 0 && this.fDriver == this.fTrailingMiscDriver) {
                setScannerState(34);
                if (this.fDocumentHandler != null) {
                    this.fDocumentHandler.endDocument(null);
                    return;
                }
                return;
            }
            throw new EOFException();
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl
    protected XMLDocumentFragmentScannerImpl.Driver createContentDriver() {
        return new ContentDriver();
    }

    protected boolean scanDoctypeDecl(boolean z) throws IOException {
        if (!this.fEntityScanner.skipSpaces()) {
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ROOT_ELEMENT_TYPE_IN_DOCTYPEDECL", null);
        }
        this.fDoctypeName = this.fEntityScanner.scanName();
        if (this.fDoctypeName == null) {
            reportFatalError("MSG_ROOT_ELEMENT_TYPE_REQUIRED", null);
        }
        if (this.fEntityScanner.skipSpaces()) {
            scanExternalID(this.fStrings, false);
            this.fDoctypeSystemId = this.fStrings[0];
            this.fDoctypePublicId = this.fStrings[1];
            this.fEntityScanner.skipSpaces();
        }
        this.fHasExternalDTD = this.fDoctypeSystemId != null;
        if (this.fDocumentHandler != null && !z) {
            this.fDocumentHandler.doctypeDecl(this.fDoctypeName, this.fDoctypePublicId, this.fDoctypeSystemId, null);
        }
        if (this.fEntityScanner.skipChar(91)) {
            return true;
        }
        this.fEntityScanner.skipSpaces();
        if (!this.fEntityScanner.skipChar(62)) {
            reportFatalError("DoctypedeclUnterminated", new Object[]{this.fDoctypeName});
        }
        this.fMarkupDepth--;
        return false;
    }

    protected void setEndDTDScanState() {
        setScannerState(SCANNER_STATE_PROLOG);
        setDriver(this.fPrologDriver);
        this.fEntityManager.setEntityHandler(this);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl
    protected String getScannerStateName(int i) {
        switch (i) {
            case SCANNER_STATE_XML_DECL /* 42 */:
                return "SCANNER_STATE_XML_DECL";
            case SCANNER_STATE_PROLOG /* 43 */:
                return "SCANNER_STATE_PROLOG";
            case SCANNER_STATE_TRAILING_MISC /* 44 */:
                return "SCANNER_STATE_TRAILING_MISC";
            case SCANNER_STATE_DTD_INTERNAL_DECLS /* 45 */:
                return "SCANNER_STATE_DTD_INTERNAL_DECLS";
            case SCANNER_STATE_DTD_EXTERNAL /* 46 */:
                return "SCANNER_STATE_DTD_EXTERNAL";
            case SCANNER_STATE_DTD_EXTERNAL_DECLS /* 47 */:
                return "SCANNER_STATE_DTD_EXTERNAL_DECLS";
            default:
                return super.getScannerStateName(i);
        }
    }

    public final class XMLDeclDriver implements XMLDocumentFragmentScannerImpl.Driver {
        protected XMLDeclDriver() {
        }

        @Override // com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl.Driver
        public int next() {
            XMLDocumentScannerImpl.this.setScannerState(XMLDocumentScannerImpl.SCANNER_STATE_PROLOG);
            XMLDocumentScannerImpl.this.setDriver(XMLDocumentScannerImpl.this.fPrologDriver);
            try {
                if (XMLDocumentScannerImpl.this.fEntityScanner.skipString(XMLDocumentFragmentScannerImpl.xmlDecl)) {
                    XMLDocumentScannerImpl.this.fMarkupDepth++;
                    if (XMLChar.isName(XMLDocumentScannerImpl.this.fEntityScanner.peekChar())) {
                        XMLDocumentScannerImpl.this.fStringBuffer.clear();
                        XMLDocumentScannerImpl.this.fStringBuffer.append("xml");
                        while (XMLChar.isName(XMLDocumentScannerImpl.this.fEntityScanner.peekChar())) {
                            XMLDocumentScannerImpl.this.fStringBuffer.append((char) XMLDocumentScannerImpl.this.fEntityScanner.scanChar());
                        }
                        String strAddSymbol = XMLDocumentScannerImpl.this.fSymbolTable.addSymbol(XMLDocumentScannerImpl.this.fStringBuffer.ch, XMLDocumentScannerImpl.this.fStringBuffer.offset, XMLDocumentScannerImpl.this.fStringBuffer.length);
                        XMLDocumentScannerImpl.this.fStringBuffer.clear();
                        XMLDocumentScannerImpl.this.scanPIData(strAddSymbol, XMLDocumentScannerImpl.this.fStringBuffer);
                        XMLDocumentScannerImpl.this.fEntityManager.fCurrentEntity.mayReadChunks = true;
                        return 3;
                    }
                    XMLDocumentScannerImpl.this.scanXMLDeclOrTextDecl(false);
                    XMLDocumentScannerImpl.this.fEntityManager.fCurrentEntity.mayReadChunks = true;
                    return 7;
                }
                XMLDocumentScannerImpl.this.fEntityManager.fCurrentEntity.mayReadChunks = true;
                return 7;
            } catch (EOFException e) {
                XMLDocumentScannerImpl.this.reportFatalError("PrematureEOF", null);
                return -1;
            }
        }
    }

    public final class PrologDriver implements XMLDocumentFragmentScannerImpl.Driver {
        protected PrologDriver() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl.Driver
        public int next() throws IOException {
            while (true) {
                try {
                    switch (XMLDocumentScannerImpl.this.fScannerState) {
                        case 21:
                            XMLDocumentScannerImpl.this.fMarkupDepth++;
                            if (XMLDocumentScannerImpl.this.fEntityScanner.skipChar(63)) {
                                XMLDocumentScannerImpl.this.setScannerState(23);
                            } else if (XMLDocumentScannerImpl.this.fEntityScanner.skipChar(33)) {
                                if (XMLDocumentScannerImpl.this.fEntityScanner.skipChar(XMLDocumentScannerImpl.SCANNER_STATE_DTD_INTERNAL_DECLS)) {
                                    if (!XMLDocumentScannerImpl.this.fEntityScanner.skipChar(XMLDocumentScannerImpl.SCANNER_STATE_DTD_INTERNAL_DECLS)) {
                                        XMLDocumentScannerImpl.this.reportFatalError("InvalidCommentStart", null);
                                    }
                                    XMLDocumentScannerImpl.this.setScannerState(27);
                                } else if (XMLDocumentScannerImpl.this.fEntityScanner.skipString(XMLDocumentScannerImpl.DOCTYPE)) {
                                    XMLDocumentScannerImpl.this.setScannerState(24);
                                    Entity.ScannedEntity currentEntity = XMLDocumentScannerImpl.this.fEntityScanner.getCurrentEntity();
                                    if (currentEntity instanceof Entity.ScannedEntity) {
                                        XMLDocumentScannerImpl.this.fStartPos = currentEntity.position;
                                    }
                                    XMLDocumentScannerImpl.this.fReadingDTD = true;
                                    if (XMLDocumentScannerImpl.this.fDTDDecl == null) {
                                        XMLDocumentScannerImpl.this.fDTDDecl = new XMLStringBuffer();
                                    }
                                    XMLDocumentScannerImpl.this.fDTDDecl.append("<!DOCTYPE");
                                } else {
                                    XMLDocumentScannerImpl.this.reportFatalError("MarkupNotRecognizedInProlog", null);
                                }
                            } else if (XMLChar.isNameStart(XMLDocumentScannerImpl.this.fEntityScanner.peekChar())) {
                                XMLDocumentScannerImpl.this.setScannerState(26);
                            } else {
                                XMLDocumentScannerImpl.this.reportFatalError("MarkupNotRecognizedInProlog", null);
                            }
                            break;
                        case XMLDocumentScannerImpl.SCANNER_STATE_PROLOG /* 43 */:
                            XMLDocumentScannerImpl.this.fEntityScanner.skipSpaces();
                            if (XMLDocumentScannerImpl.this.fEntityScanner.skipChar(60)) {
                                XMLDocumentScannerImpl.this.setScannerState(21);
                            } else if (XMLDocumentScannerImpl.this.fEntityScanner.skipChar(38)) {
                                XMLDocumentScannerImpl.this.setScannerState(28);
                            } else {
                                XMLDocumentScannerImpl.this.setScannerState(22);
                            }
                            break;
                    }
                    if (XMLDocumentScannerImpl.this.fScannerState != XMLDocumentScannerImpl.SCANNER_STATE_PROLOG && XMLDocumentScannerImpl.this.fScannerState != 21) {
                        switch (XMLDocumentScannerImpl.this.fScannerState) {
                            case 22:
                                XMLDocumentScannerImpl.this.reportFatalError("ContentIllegalInProlog", null);
                                XMLDocumentScannerImpl.this.fEntityScanner.scanChar();
                                break;
                            case 23:
                                XMLDocumentScannerImpl.this.fContentBuffer.clear();
                                XMLDocumentScannerImpl.this.scanPI(XMLDocumentScannerImpl.this.fContentBuffer);
                                XMLDocumentScannerImpl.this.setScannerState(XMLDocumentScannerImpl.SCANNER_STATE_PROLOG);
                                return 3;
                            case 24:
                                if (XMLDocumentScannerImpl.this.fSeenDoctypeDecl) {
                                    XMLDocumentScannerImpl.this.reportFatalError("AlreadySeenDoctype", null);
                                }
                                XMLDocumentScannerImpl.this.fSeenDoctypeDecl = true;
                                if (XMLDocumentScannerImpl.this.scanDoctypeDecl(XMLDocumentScannerImpl.this.fDisallowDoctype)) {
                                    XMLDocumentScannerImpl.this.setScannerState(XMLDocumentScannerImpl.SCANNER_STATE_DTD_INTERNAL_DECLS);
                                    XMLDocumentScannerImpl.this.fSeenInternalSubset = true;
                                    if (XMLDocumentScannerImpl.this.fDTDDriver == null) {
                                        XMLDocumentScannerImpl.this.fDTDDriver = XMLDocumentScannerImpl.this.new DTDDriver();
                                    }
                                    XMLDocumentScannerImpl.this.setDriver(XMLDocumentScannerImpl.this.fContentDriver);
                                    return XMLDocumentScannerImpl.this.fDTDDriver.next();
                                }
                                if (XMLDocumentScannerImpl.this.fSeenDoctypeDecl) {
                                    Entity.ScannedEntity currentEntity2 = XMLDocumentScannerImpl.this.fEntityScanner.getCurrentEntity();
                                    if (currentEntity2 instanceof Entity.ScannedEntity) {
                                        XMLDocumentScannerImpl.this.fEndPos = currentEntity2.position;
                                    }
                                    XMLDocumentScannerImpl.this.fReadingDTD = false;
                                }
                                if (XMLDocumentScannerImpl.this.fDoctypeSystemId != null && (XMLDocumentScannerImpl.this.fValidation || XMLDocumentScannerImpl.this.fLoadExternalDTD)) {
                                    if (!XMLDocumentScannerImpl.this.fDisallowDoctype) {
                                        XMLDocumentScannerImpl.this.setScannerState(XMLDocumentScannerImpl.SCANNER_STATE_DTD_EXTERNAL);
                                    } else {
                                        XMLDocumentScannerImpl.this.setScannerState(XMLDocumentScannerImpl.SCANNER_STATE_PROLOG);
                                    }
                                    XMLDocumentScannerImpl.this.setDriver(XMLDocumentScannerImpl.this.fContentDriver);
                                    if (XMLDocumentScannerImpl.this.fDTDDriver == null) {
                                        XMLDocumentScannerImpl.this.fDTDDriver = XMLDocumentScannerImpl.this.new DTDDriver();
                                    }
                                    return XMLDocumentScannerImpl.this.fDTDDriver.next();
                                }
                                if (XMLDocumentScannerImpl.this.fDTDScanner != null) {
                                    XMLDocumentScannerImpl.this.fDTDScanner.setInputSource(null);
                                }
                                XMLDocumentScannerImpl.this.setScannerState(XMLDocumentScannerImpl.SCANNER_STATE_PROLOG);
                                return 11;
                            case 25:
                            default:
                                return -1;
                            case 26:
                                XMLDocumentScannerImpl.this.setScannerState(38);
                                XMLDocumentScannerImpl.this.setDriver(XMLDocumentScannerImpl.this.fContentDriver);
                                return XMLDocumentScannerImpl.this.fContentDriver.next();
                            case 27:
                                XMLDocumentScannerImpl.this.scanComment();
                                XMLDocumentScannerImpl.this.setScannerState(XMLDocumentScannerImpl.SCANNER_STATE_PROLOG);
                                return 5;
                            case 28:
                                break;
                        }
                        XMLDocumentScannerImpl.this.reportFatalError("ReferenceIllegalInProlog", null);
                        return -1;
                    }
                } catch (EOFException e) {
                    XMLDocumentScannerImpl.this.reportFatalError("PrematureEOF", null);
                    return -1;
                }
            }
        }
    }

    public final class DTDDriver implements XMLDocumentFragmentScannerImpl.Driver {
        protected DTDDriver() {
        }

        @Override // com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl.Driver
        public int next() {
            dispatch(true);
            XMLDocumentScannerImpl.this.dtdGrammarUtil = new DTDGrammarUtil(((XMLDTDScannerImpl) XMLDocumentScannerImpl.this.fDTDScanner).getGrammar(), XMLDocumentScannerImpl.this.fSymbolTable, XMLDocumentScannerImpl.this.fNamespaceContext);
            return 11;
        }

        /* JADX WARN: Code duplicated, block: B:68:0x0144 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:72:0x0031 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
        public boolean dispatch(boolean z) {
            boolean z2;
            XMLDocumentScannerImpl.this.fEntityManager.setEntityHandler(null);
            try {
                try {
                    XMLResourceIdentifierImpl xMLResourceIdentifierImpl = new XMLResourceIdentifierImpl();
                    if (XMLDocumentScannerImpl.this.fDTDScanner == null) {
                        XMLDocumentScannerImpl.this.fDTDScanner = new XMLDTDScannerImpl();
                        if (XMLDocumentScannerImpl.this.fPropertyManager != null) {
                            ((XMLDTDScannerImpl) XMLDocumentScannerImpl.this.fDTDScanner).reset(XMLDocumentScannerImpl.this.fPropertyManager);
                        }
                    }
                    while (true) {
                        switch (XMLDocumentScannerImpl.this.fScannerState) {
                            case XMLDocumentScannerImpl.SCANNER_STATE_PROLOG /* 43 */:
                                XMLDocumentScannerImpl.this.setEndDTDScanState();
                                XMLDocumentScannerImpl.this.fEntityManager.setEntityHandler(XMLDocumentScannerImpl.this);
                                return true;
                            case XMLDocumentScannerImpl.SCANNER_STATE_TRAILING_MISC /* 44 */:
                            default:
                                throw new XNIException(new StringBuffer().append("DTDDriver#dispatch: scanner state=").append(XMLDocumentScannerImpl.this.fScannerState).append(" (").append(XMLDocumentScannerImpl.this.getScannerStateName(XMLDocumentScannerImpl.this.fScannerState)).append(')').toString());
                            case XMLDocumentScannerImpl.SCANNER_STATE_DTD_INTERNAL_DECLS /* 45 */:
                                boolean zScanDTDInternalSubset = XMLDocumentScannerImpl.this.fDTDScanner.scanDTDInternalSubset(true, XMLDocumentScannerImpl.this.fStandalone, XMLDocumentScannerImpl.this.fHasExternalDTD && XMLDocumentScannerImpl.this.fLoadExternalDTD);
                                Entity.ScannedEntity currentEntity = XMLDocumentScannerImpl.this.fEntityScanner.getCurrentEntity();
                                if (currentEntity instanceof Entity.ScannedEntity) {
                                    XMLDocumentScannerImpl.this.fEndPos = currentEntity.position;
                                }
                                XMLDocumentScannerImpl.this.fReadingDTD = false;
                                if (zScanDTDInternalSubset) {
                                    z2 = false;
                                } else {
                                    if (!XMLDocumentScannerImpl.this.fEntityScanner.skipChar(93)) {
                                        XMLDocumentScannerImpl.this.reportFatalError("EXPECTED_SQUARE_BRACKET_TO_CLOSE_INTERNAL_SUBSET", null);
                                    }
                                    XMLDocumentScannerImpl.this.fEntityScanner.skipSpaces();
                                    if (!XMLDocumentScannerImpl.this.fEntityScanner.skipChar(62)) {
                                        XMLDocumentScannerImpl.this.reportFatalError("DoctypedeclUnterminated", new Object[]{XMLDocumentScannerImpl.this.fDoctypeName});
                                    }
                                    XMLDocumentScannerImpl.this.fMarkupDepth--;
                                    if (XMLDocumentScannerImpl.this.fDisallowDoctype) {
                                        XMLDocumentScannerImpl.this.fEntityStore = XMLDocumentScannerImpl.this.fEntityManager.getEntityStore();
                                        XMLDocumentScannerImpl.this.fEntityStore.reset();
                                    } else if (XMLDocumentScannerImpl.this.fDoctypeSystemId != null && (XMLDocumentScannerImpl.this.fValidation || XMLDocumentScannerImpl.this.fLoadExternalDTD)) {
                                        XMLDocumentScannerImpl.this.setScannerState(XMLDocumentScannerImpl.SCANNER_STATE_DTD_EXTERNAL);
                                        z2 = false;
                                    }
                                }
                                if (z && !z2) {
                                    XMLDocumentScannerImpl.this.fEntityManager.setEntityHandler(XMLDocumentScannerImpl.this);
                                    return true;
                                }
                                break;
                                break;
                            case XMLDocumentScannerImpl.SCANNER_STATE_DTD_EXTERNAL /* 46 */:
                                xMLResourceIdentifierImpl.setValues(XMLDocumentScannerImpl.this.fDoctypePublicId, XMLDocumentScannerImpl.this.fDoctypeSystemId, null, null);
                                XMLDocumentScannerImpl.this.fDTDScanner.setInputSource(XMLDocumentScannerImpl.this.fEntityManager.resolveEntityAsPerStax(xMLResourceIdentifierImpl).getXMLInputSource());
                                XMLDocumentScannerImpl.this.setScannerState(XMLDocumentScannerImpl.SCANNER_STATE_DTD_EXTERNAL_DECLS);
                                z2 = true;
                                if (z) {
                                }
                                break;
                            case XMLDocumentScannerImpl.SCANNER_STATE_DTD_EXTERNAL_DECLS /* 47 */:
                                if (!XMLDocumentScannerImpl.this.fDTDScanner.scanDTDExternalSubset(true)) {
                                    XMLDocumentScannerImpl.this.setEndDTDScanState();
                                    XMLDocumentScannerImpl.this.fEntityManager.setEntityHandler(XMLDocumentScannerImpl.this);
                                    return true;
                                }
                                z2 = false;
                                if (z) {
                                }
                                break;
                        }
                    }
                    XMLDocumentScannerImpl.this.setEndDTDScanState();
                    XMLDocumentScannerImpl.this.fEntityManager.setEntityHandler(XMLDocumentScannerImpl.this);
                    return true;
                } catch (EOFException e) {
                    e.printStackTrace();
                    XMLDocumentScannerImpl.this.reportFatalError("PrematureEOF", null);
                    XMLDocumentScannerImpl.this.fEntityManager.setEntityHandler(XMLDocumentScannerImpl.this);
                    return false;
                }
            } catch (Throwable th) {
                XMLDocumentScannerImpl.this.fEntityManager.setEntityHandler(XMLDocumentScannerImpl.this);
                throw th;
            }
        }
    }

    public class ContentDriver extends XMLDocumentFragmentScannerImpl.FragmentContentDriver {
        protected ContentDriver() {
            super();
        }

        @Override // com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl.FragmentContentDriver
        protected boolean scanForDoctypeHook() {
            if (!XMLDocumentScannerImpl.this.fEntityScanner.skipString(XMLDocumentScannerImpl.DOCTYPE)) {
                return false;
            }
            XMLDocumentScannerImpl.this.setScannerState(24);
            return true;
        }

        @Override // com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl.FragmentContentDriver
        protected boolean elementDepthIsZeroHook() {
            XMLDocumentScannerImpl.this.setScannerState(XMLDocumentScannerImpl.SCANNER_STATE_TRAILING_MISC);
            XMLDocumentScannerImpl.this.setDriver(XMLDocumentScannerImpl.this.fTrailingMiscDriver);
            return true;
        }

        @Override // com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl.FragmentContentDriver
        protected boolean scanRootElementHook() {
            if (!XMLDocumentScannerImpl.this.scanStartElement()) {
                return false;
            }
            XMLDocumentScannerImpl.this.setScannerState(XMLDocumentScannerImpl.SCANNER_STATE_TRAILING_MISC);
            XMLDocumentScannerImpl.this.setDriver(XMLDocumentScannerImpl.this.fTrailingMiscDriver);
            return true;
        }

        @Override // com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl.FragmentContentDriver
        protected void endOfFileHook(EOFException eOFException) {
            XMLDocumentScannerImpl.this.reportFatalError("PrematureEOF", null);
        }
    }

    public final class TrailingMiscDriver implements XMLDocumentFragmentScannerImpl.Driver {
        protected TrailingMiscDriver() {
        }

        @Override // com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl.Driver
        public int next() throws IOException {
            try {
                if (XMLDocumentScannerImpl.this.fScannerState == 34) {
                    return 8;
                }
                while (true) {
                    switch (XMLDocumentScannerImpl.this.fScannerState) {
                        case 21:
                            XMLDocumentScannerImpl.this.fMarkupDepth++;
                            if (XMLDocumentScannerImpl.this.fEntityScanner.skipChar(63)) {
                                XMLDocumentScannerImpl.this.setScannerState(23);
                            } else if (XMLDocumentScannerImpl.this.fEntityScanner.skipChar(33)) {
                                XMLDocumentScannerImpl.this.setScannerState(27);
                            } else if (!XMLDocumentScannerImpl.this.fEntityScanner.skipChar(XMLDocumentScannerImpl.SCANNER_STATE_DTD_EXTERNAL_DECLS) && XMLChar.isNameStart(XMLDocumentScannerImpl.this.fEntityScanner.peekChar())) {
                                XMLDocumentScannerImpl.this.reportFatalError("MarkupNotRecognizedInMisc", null);
                                XMLDocumentScannerImpl.this.scanStartElement();
                                XMLDocumentScannerImpl.this.setScannerState(22);
                            } else {
                                XMLDocumentScannerImpl.this.reportFatalError("MarkupNotRecognizedInMisc", null);
                            }
                            break;
                        case XMLDocumentScannerImpl.SCANNER_STATE_TRAILING_MISC /* 44 */:
                            XMLDocumentScannerImpl.this.fEntityScanner.skipSpaces();
                            if (XMLDocumentScannerImpl.this.fScannerState == 34) {
                                return 8;
                            }
                            if (XMLDocumentScannerImpl.this.fEntityScanner.skipChar(60)) {
                                XMLDocumentScannerImpl.this.setScannerState(21);
                            } else {
                                XMLDocumentScannerImpl.this.setScannerState(22);
                            }
                            break;
                            break;
                    }
                    if (XMLDocumentScannerImpl.this.fScannerState != 21 && XMLDocumentScannerImpl.this.fScannerState != XMLDocumentScannerImpl.SCANNER_STATE_TRAILING_MISC) {
                        switch (XMLDocumentScannerImpl.this.fScannerState) {
                            case 22:
                                if (XMLDocumentScannerImpl.this.fEntityScanner.peekChar() == -1) {
                                    XMLDocumentScannerImpl.this.setScannerState(34);
                                    return 8;
                                }
                                XMLDocumentScannerImpl.this.reportFatalError("ContentIllegalInTrailingMisc", null);
                                XMLDocumentScannerImpl.this.fEntityScanner.scanChar();
                                XMLDocumentScannerImpl.this.setScannerState(XMLDocumentScannerImpl.SCANNER_STATE_TRAILING_MISC);
                                return 4;
                            case 23:
                                XMLDocumentScannerImpl.this.fContentBuffer.clear();
                                XMLDocumentScannerImpl.this.scanPI(XMLDocumentScannerImpl.this.fContentBuffer);
                                XMLDocumentScannerImpl.this.setScannerState(XMLDocumentScannerImpl.SCANNER_STATE_TRAILING_MISC);
                                return 3;
                            case 27:
                                if (!XMLDocumentScannerImpl.this.fEntityScanner.skipString(XMLDocumentScannerImpl.COMMENTSTRING)) {
                                    XMLDocumentScannerImpl.this.reportFatalError("InvalidCommentStart", null);
                                }
                                XMLDocumentScannerImpl.this.scanComment();
                                XMLDocumentScannerImpl.this.setScannerState(XMLDocumentScannerImpl.SCANNER_STATE_TRAILING_MISC);
                                return 5;
                            case 28:
                                XMLDocumentScannerImpl.this.reportFatalError("ReferenceIllegalInTrailingMisc", null);
                                XMLDocumentScannerImpl.this.setScannerState(XMLDocumentScannerImpl.SCANNER_STATE_TRAILING_MISC);
                                return 9;
                            case 34:
                                XMLDocumentScannerImpl.this.setScannerState(XMLDocumentScannerImpl.SCANNER_STATE_NO_SUCH_ELEMENT_EXCEPTION);
                                return 8;
                            case XMLDocumentScannerImpl.SCANNER_STATE_NO_SUCH_ELEMENT_EXCEPTION /* 48 */:
                                throw new NoSuchElementException("No more events to be parsed");
                            default:
                                throw new XNIException(new StringBuffer().append("Scanner State ").append(XMLDocumentScannerImpl.this.fScannerState).append(" not Recognized ").toString());
                        }
                    }
                }
            } catch (EOFException e) {
                if (XMLDocumentScannerImpl.this.fMarkupDepth != 0) {
                    XMLDocumentScannerImpl.this.reportFatalError("PrematureEOF", null);
                    return -1;
                }
                System.out.println("EOFException thrown");
                XMLDocumentScannerImpl.this.setScannerState(34);
                return 8;
            }
        }
    }

    public class XMLBufferListenerImpl implements XMLBufferListener {
        protected XMLBufferListenerImpl() {
        }

        @Override // com.amazonaws.javax.xml.stream.XMLBufferListener
        public void refresh() {
            refresh(0);
        }

        @Override // com.amazonaws.javax.xml.stream.XMLBufferListener
        public void refresh(int i) {
            if (XMLDocumentScannerImpl.this.fReadingAttributes) {
                XMLDocumentScannerImpl.this.fAttributes.refresh();
            }
            if (XMLDocumentScannerImpl.this.fReadingDTD) {
                Entity.ScannedEntity currentEntity = XMLDocumentScannerImpl.this.fEntityScanner.getCurrentEntity();
                if (currentEntity instanceof Entity.ScannedEntity) {
                    XMLDocumentScannerImpl.this.fEndPos = currentEntity.position;
                }
                XMLDocumentScannerImpl.this.fDTDDecl.append(currentEntity.ch, XMLDocumentScannerImpl.this.fStartPos, XMLDocumentScannerImpl.this.fEndPos - XMLDocumentScannerImpl.this.fStartPos);
                XMLDocumentScannerImpl.this.fStartPos = i;
            }
            if (XMLDocumentScannerImpl.this.fScannerState == 37) {
                XMLDocumentScannerImpl.this.fContentBuffer.append(XMLDocumentScannerImpl.this.fTempString);
                XMLDocumentScannerImpl.this.fTempString.length = 0;
                XMLDocumentScannerImpl.this.fUsebuffer = true;
            }
        }
    }
}

package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.stream.dtd.DTDGrammarUtil;
import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.amazonaws.javax.xml.stream.xerces.impl.msg.XMLMessageFormatter;
import com.amazonaws.javax.xml.stream.xerces.util.XMLAttributesIteratorImpl;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import com.amazonaws.javax.xml.stream.xerces.util.XMLStringBuffer;
import com.amazonaws.javax.xml.stream.xerces.util.XMLSymbols;
import com.amazonaws.javax.xml.stream.xerces.xni.QName;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLDocumentHandler;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLResourceIdentifier;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLString;
import com.amazonaws.javax.xml.stream.xerces.xni.XNIException;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponentManager;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLConfigurationException;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLDocumentScanner;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLInputSource;
import java.io.EOFException;
import java.io.IOException;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class XMLDocumentFragmentScannerImpl extends XMLScanner implements XMLEntityHandler, XMLComponent, XMLDocumentScanner {
    protected static final boolean DEBUG = false;
    protected static final boolean DEBUG_COALESCE = false;
    protected static final boolean DEBUG_CONTENT_SCANNING = false;
    private static final boolean DEBUG_DISPATCHER = false;
    protected static final boolean DEBUG_NEXT = false;
    private static final boolean DEBUG_SCANNER_STATE = false;
    static final boolean DEBUG_SKIP_ALGORITHM = false;
    static final short ELEMENT_ARRAY_LENGTH = 200;
    static final short MAX_DEPTH_LIMIT = 5;
    static final short MAX_POINTER_AT_A_DEPTH = 4;
    protected static final int SCANNER_STATE_ATTRIBUTE = 29;
    protected static final int SCANNER_STATE_ATTRIBUTE_VALUE = 30;
    protected static final int SCANNER_STATE_BUILT_IN_REFS = 41;
    protected static final int SCANNER_STATE_CDATA = 35;
    protected static final int SCANNER_STATE_CHARACTER_DATA = 37;
    protected static final int SCANNER_STATE_CHAR_REFERENCE = 40;
    protected static final int SCANNER_STATE_COMMENT = 27;
    protected static final int SCANNER_STATE_CONTENT = 22;
    protected static final int SCANNER_STATE_DOCTYPE = 24;
    protected static final int SCANNER_STATE_END_ELEMENT_TAG = 39;
    protected static final int SCANNER_STATE_END_OF_INPUT = 33;
    protected static final int SCANNER_STATE_PI = 23;
    protected static final int SCANNER_STATE_REFERENCE = 28;
    protected static final int SCANNER_STATE_ROOT_ELEMENT = 26;
    protected static final int SCANNER_STATE_START_ELEMENT_TAG = 38;
    protected static final int SCANNER_STATE_START_OF_MARKUP = 21;
    protected static final int SCANNER_STATE_TERMINATED = 34;
    protected static final int SCANNER_STATE_TEXT_DECL = 36;
    protected static final int SCANNER_STATE_XML_DECL = 25;
    protected QName fCurrentElement;
    protected XMLDocumentHandler fDocumentHandler;
    protected Driver fDriver;
    protected String fElementRawname;
    protected boolean fEmptyElement;
    protected XMLEntityStorage fEntityStore;
    protected boolean fHasExternalDTD;
    protected int fMarkupDepth;
    protected boolean fNamespaces;
    protected String fPITarget;
    protected int fScannerState;
    protected boolean fStandalone;
    protected boolean fStandaloneSet;
    boolean fUsebuffer;
    protected String fVersion;
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String NOTIFY_BUILTIN_REFS = "http://apache.org/xml/features/scanner/notify-builtin-refs";
    private static final String[] RECOGNIZED_FEATURES = {NAMESPACES, "http://xml.org/sax/features/validation", NOTIFY_BUILTIN_REFS, "http://apache.org/xml/features/scanner/notify-char-refs"};
    private static final Boolean[] FEATURE_DEFAULTS = {null, null, Boolean.FALSE, Boolean.FALSE};
    private static final String[] RECOGNIZED_PROPERTIES = {"http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-manager"};
    private static final Object[] PROPERTY_DEFAULTS = {null, null, null};
    protected static final char[] cdata = {'[', 'C', 'D', 'A', 'T', 'A', '['};
    protected static final char[] xmlDecl = {XMLStreamWriterImpl.OPEN_START_TAG, '?', 'x', 'm', 'l'};
    protected static final char[] endTag = {XMLStreamWriterImpl.OPEN_START_TAG, '/'};
    protected int[] fEntityStack = new int[4];
    protected boolean fInScanContent = false;
    protected boolean fLastSectionWasCData = false;
    protected boolean fLastSectionWasEntityReference = false;
    protected boolean fLastSectionWasCharacterData = false;
    protected ElementStack fElementStack = new ElementStack();
    protected ElementStack2 fElementStack2 = new ElementStack2();
    protected XMLString fPIData = new XMLString();
    protected boolean fNotifyBuiltInRefs = false;
    protected boolean fReplaceEntityReferences = true;
    protected boolean fSupportExternalEntities = false;
    protected boolean fReportCdataEvent = false;
    protected boolean fIsCoalesce = false;
    protected String fDeclaredEncoding = null;
    protected boolean fDisallowDoctype = false;
    protected Driver fContentDriver = createContentDriver();
    protected QName fElementQName = new QName();
    protected QName fAttributeQName = new QName();
    protected XMLAttributesIteratorImpl fAttributes = new XMLAttributesIteratorImpl();
    protected XMLString fTempString = new XMLString();
    protected XMLString fTempString2 = new XMLString();
    private String[] fStrings = new String[3];
    protected XMLStringBuffer fStringBuffer = new XMLStringBuffer();
    protected XMLStringBuffer fStringBuffer2 = new XMLStringBuffer();
    protected XMLStringBuffer fContentBuffer = new XMLStringBuffer();
    private final char[] fSingleChar = new char[1];
    private String fCurrentEntityName = null;
    protected boolean fScanToEnd = false;
    protected DTDGrammarUtil dtdGrammarUtil = null;
    protected boolean fAddDefaultAttr = false;
    protected boolean foundBuiltInRefs = false;
    String[] fElementArray = new String[200];
    short fLastPointerLocation = 0;
    short fElementPointer = 0;
    short[][] fPointerInfo = (short[][]) Array.newInstance((Class<?>) Short.TYPE, 5, 4);
    protected boolean fShouldSkip = false;
    protected boolean fAdd = false;
    protected boolean fSkip = false;

    public interface Driver {
        int next();
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLDocumentScanner
    public void setInputSource(XMLInputSource xMLInputSource) throws IOException {
        this.fEntityManager.setEntityHandler(this);
        this.fEntityManager.startEntity("$fragment$", xMLInputSource, false, true);
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLDocumentScanner
    public boolean scanDocument(boolean z) {
        this.fEntityManager.setEntityHandler(this);
        return true;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLDocumentScanner
    public int next() {
        return this.fDriver.next();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLScanner, com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public void reset(XMLComponentManager xMLComponentManager) {
        super.reset(xMLComponentManager);
        try {
            this.fNamespaces = xMLComponentManager.getFeature(NAMESPACES);
        } catch (XMLConfigurationException e) {
            this.fNamespaces = true;
        }
        try {
            this.fNotifyBuiltInRefs = xMLComponentManager.getFeature(NOTIFY_BUILTIN_REFS);
        } catch (XMLConfigurationException e2) {
            this.fNotifyBuiltInRefs = false;
        }
        this.fMarkupDepth = 0;
        this.fCurrentElement = null;
        this.fElementStack.clear();
        this.fHasExternalDTD = false;
        this.fStandaloneSet = false;
        this.fStandalone = false;
        setScannerState(SCANNER_STATE_CONTENT);
        setDriver(this.fContentDriver);
        this.fEntityStore = this.fEntityManager.getEntityStore();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLScanner
    public void reset(PropertyManager propertyManager) {
        super.reset(propertyManager);
        this.fNamespaces = false;
        this.fNotifyBuiltInRefs = false;
        this.fMarkupDepth = 0;
        this.fCurrentElement = null;
        this.fShouldSkip = false;
        this.fAdd = false;
        this.fSkip = false;
        this.fElementStack.clear();
        this.fHasExternalDTD = false;
        this.fStandaloneSet = false;
        this.fStandalone = false;
        this.fReplaceEntityReferences = ((Boolean) propertyManager.getProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES)).booleanValue();
        this.fSupportExternalEntities = ((Boolean) propertyManager.getProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES)).booleanValue();
        Boolean bool = (Boolean) propertyManager.getProperty("http://java.sun.com/xml/stream/properties/report-cdata-event");
        if (bool != null) {
            this.fReportCdataEvent = bool.booleanValue();
        }
        Boolean bool2 = (Boolean) propertyManager.getProperty(XMLInputFactory.IS_COALESCING);
        if (bool2 != null) {
            this.fIsCoalesce = bool2.booleanValue();
        }
        boolean z = !this.fIsCoalesce && this.fReportCdataEvent;
        this.fReportCdataEvent = z;
        this.fReplaceEntityReferences = this.fIsCoalesce ? true : this.fReplaceEntityReferences;
        this.fEntityStore = this.fEntityManager.getEntityStore();
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public String[] getRecognizedFeatures() {
        return (String[]) RECOGNIZED_FEATURES.clone();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLScanner, com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public void setFeature(String str, boolean z) {
        super.setFeature(str, z);
        if (str.startsWith(Constants.XERCES_FEATURE_PREFIX) && str.substring(Constants.XERCES_FEATURE_PREFIX.length()).equals(Constants.NOTIFY_BUILTIN_REFS_FEATURE)) {
            this.fNotifyBuiltInRefs = z;
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public String[] getRecognizedProperties() {
        return (String[]) RECOGNIZED_PROPERTIES.clone();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLScanner, com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public void setProperty(String str, Object obj) {
        super.setProperty(str, obj);
        if (str.startsWith(Constants.XERCES_PROPERTY_PREFIX) && str.substring(Constants.XERCES_PROPERTY_PREFIX.length()).equals(Constants.ENTITY_MANAGER_PROPERTY)) {
            this.fEntityManager = (XMLEntityManager) obj;
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

    @Override // com.amazonaws.javax.xml.stream.XMLScanner, com.amazonaws.javax.xml.stream.XMLEntityHandler
    public void startEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2) {
        if (this.fEntityDepth == this.fEntityStack.length) {
            int[] iArr = new int[this.fEntityStack.length * 2];
            System.arraycopy(this.fEntityStack, 0, iArr, 0, this.fEntityStack.length);
            this.fEntityStack = iArr;
        }
        this.fEntityStack[this.fEntityDepth] = this.fMarkupDepth;
        super.startEntity(str, xMLResourceIdentifier, str2);
        if (this.fStandalone && this.fEntityStore.isEntityDeclInExternalSubset(str)) {
            reportFatalError("MSG_REFERENCE_TO_EXTERNALLY_DECLARED_ENTITY_WHEN_STANDALONE", new Object[]{str});
        }
        if (this.fDocumentHandler != null && !this.fScanningAttribute && !str.equals("[xml]")) {
            this.fDocumentHandler.startGeneralEntity(str, xMLResourceIdentifier, str2, null);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLScanner, com.amazonaws.javax.xml.stream.XMLEntityHandler
    public void endEntity(String str) {
        super.endEntity(str);
        if (this.fMarkupDepth != this.fEntityStack[this.fEntityDepth]) {
            reportFatalError("MarkupEntityMismatch", null);
        }
        if (this.fDocumentHandler != null && !this.fScanningAttribute && !str.equals("[xml]")) {
            this.fDocumentHandler.endGeneralEntity(str, null);
        }
    }

    protected Driver createContentDriver() {
        return new FragmentContentDriver();
    }

    protected void scanXMLDeclOrTextDecl(boolean z) {
        super.scanXMLDeclOrTextDecl(z, this.fStrings);
        this.fMarkupDepth--;
        String str = this.fStrings[0];
        String str2 = this.fStrings[1];
        String str3 = this.fStrings[2];
        this.fDeclaredEncoding = str2;
        this.fStandaloneSet = str3 != null;
        this.fStandalone = this.fStandaloneSet && str3.equals("yes");
        this.fEntityManager.setStandalone(this.fStandalone);
        if (this.fDocumentHandler != null) {
            if (z) {
                this.fDocumentHandler.textDecl(str, str2, null);
            } else {
                this.fDocumentHandler.xmlDecl(str, str2, str3, null);
            }
        }
        if (str != null) {
            this.fEntityScanner.setVersion(str);
        }
        if (str2 != null) {
            this.fEntityScanner.setEncoding(str2);
        }
    }

    public String getPITarget() {
        return this.fPITarget;
    }

    public XMLStringBuffer getPIData() {
        return this.fContentBuffer;
    }

    public XMLString getCharacterData() {
        return this.fUsebuffer ? this.fContentBuffer : this.fTempString;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLScanner
    protected void scanPIData(String str, XMLStringBuffer xMLStringBuffer) {
        super.scanPIData(str, xMLStringBuffer);
        this.fPITarget = str;
        this.fMarkupDepth--;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.processingInstruction(str, xMLStringBuffer, null);
        }
    }

    protected void scanComment() {
        this.fContentBuffer.clear();
        scanComment(this.fContentBuffer);
        this.fUsebuffer = true;
        this.fMarkupDepth--;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.comment(this.fContentBuffer, null);
        }
    }

    public String getComment() {
        return this.fContentBuffer.toString();
    }

    void addElement(String str) {
        short sStorePointerForADepth;
        if (this.fElementPointer < 200) {
            this.fElementArray[this.fElementPointer] = str;
            if (this.fElementStack.fDepth < 5 && (sStorePointerForADepth = storePointerForADepth(this.fElementPointer)) > 0) {
                short elementPointer = getElementPointer((short) this.fElementStack.fDepth, (short) (sStorePointerForADepth - 1));
                if (str == this.fElementArray[elementPointer]) {
                    this.fShouldSkip = true;
                    this.fLastPointerLocation = elementPointer;
                    resetPointer((short) this.fElementStack.fDepth, sStorePointerForADepth);
                    this.fElementArray[this.fElementPointer] = null;
                    return;
                }
                this.fShouldSkip = false;
            }
            this.fElementPointer = (short) (this.fElementPointer + 1);
        }
    }

    void resetPointer(short s, short s2) {
        this.fPointerInfo[s][s2] = 0;
    }

    short storePointerForADepth(short s) {
        short s2 = (short) this.fElementStack.fDepth;
        for (short s3 = 0; s3 < 4; s3 = (short) (s3 + 1)) {
            if (canStore(s2, s3)) {
                this.fPointerInfo[s2][s3] = s;
                return s3;
            }
        }
        return (short) -1;
    }

    boolean canStore(short s, short s2) {
        return this.fPointerInfo[s][s2] == 0;
    }

    short getElementPointer(short s, short s2) {
        return this.fPointerInfo[s][s2];
    }

    boolean skipFromTheBuffer(String str) {
        if (!this.fEntityScanner.skipString(str)) {
            return false;
        }
        char cPeekChar = (char) this.fEntityScanner.peekChar();
        if (cPeekChar != ' ' && cPeekChar != '/' && cPeekChar != '>') {
            return false;
        }
        this.fElementRawname = str;
        return true;
    }

    boolean skipQElement(QName qName) {
        if (XMLChar.isName(this.fEntityScanner.getChar(qName.characters.length))) {
            return false;
        }
        return this.fEntityScanner.skipString(qName.characters);
    }

    boolean skipQElement(String str) {
        if (XMLChar.isName(this.fEntityScanner.getChar(str.length()))) {
            return false;
        }
        return this.fEntityScanner.skipString(str);
    }

    protected boolean skipElement() {
        if (!this.fShouldSkip) {
            return false;
        }
        if (this.fLastPointerLocation != 0) {
            String str = this.fElementArray[this.fLastPointerLocation + 1];
            if (str != null && skipFromTheBuffer(str)) {
                this.fLastPointerLocation = (short) (this.fLastPointerLocation + 1);
                return true;
            }
            this.fLastPointerLocation = (short) 0;
        }
        return this.fShouldSkip && skipElement((short) 0);
    }

    boolean skipElement(short s) {
        short s2 = (short) this.fElementStack.fDepth;
        if (s2 > 5) {
            this.fShouldSkip = false;
            return false;
        }
        while (s < 4) {
            short elementPointer = getElementPointer(s2, s);
            if (elementPointer == 0) {
                this.fShouldSkip = false;
                return false;
            }
            if (this.fElementArray[elementPointer] == null || !skipFromTheBuffer(this.fElementArray[elementPointer])) {
                s = (short) (s + 1);
            } else {
                this.fLastPointerLocation = elementPointer;
                this.fShouldSkip = true;
                return true;
            }
        }
        this.fShouldSkip = false;
        return false;
    }

    protected boolean scanStartElement() throws IOException {
        boolean z;
        if (this.fShouldSkip && !this.fAdd) {
            this.fShouldSkip = skipQElement(this.fElementStack2.getNext());
        }
        if (this.fAdd) {
            this.fElementQName = this.fElementStack2.nextElement();
        } else {
            this.fElementQName = this.fElementStack.nextElement();
        }
        this.fCurrentElement = this.fElementQName;
        if (!this.fShouldSkip || this.fAdd) {
            if (this.fNamespaces) {
                this.fEntityScanner.scanQName(this.fElementQName);
            } else {
                String strScanName = this.fEntityScanner.scanName();
                this.fElementQName.setValues(null, strScanName, strScanName, null);
                this.fElementQName.characters = this.fEntityScanner.scannedName;
            }
        }
        if (this.fAdd) {
            this.fElementStack2.matchElement(this.fElementQName);
        }
        String str = this.fElementQName.rawname;
        this.fAttributes.removeAllAttributes();
        while (true) {
            boolean zSkipSpaces = this.fEntityScanner.skipSpaces();
            int iPeekChar = this.fEntityScanner.peekChar();
            if (iPeekChar == 62) {
                this.fEntityScanner.scanChar();
                z = false;
                break;
            }
            if (iPeekChar == 47) {
                this.fEntityScanner.scanChar();
                if (!this.fEntityScanner.skipChar(62)) {
                    reportFatalError("ElementUnterminated", new Object[]{str});
                }
                z = true;
                break;
            }
            if (!isValidNameStartChar(iPeekChar) || !zSkipSpaces) {
                reportFatalError("ElementUnterminated", new Object[]{str});
            }
            scanAttribute(this.fAttributes);
        }
        if (z) {
            this.fMarkupDepth--;
            if (this.fMarkupDepth < this.fEntityStack[this.fEntityDepth - 1]) {
                reportFatalError("ElementEntityMismatch", new Object[]{this.fCurrentElement.rawname});
            }
            if (this.fDocumentHandler != null) {
            }
            this.fElementStack.popElement();
        } else if (this.fDocumentHandler != null) {
        }
        return z;
    }

    public boolean hasAttributes() {
        return this.fAttributes.getLength() > 0;
    }

    public XMLAttributesIteratorImpl getAttributeIterator() {
        if (this.dtdGrammarUtil != null && this.fAddDefaultAttr) {
            this.dtdGrammarUtil.addDTDDefaultAttrs(this.fElementQName, this.fAttributes);
            this.fAddDefaultAttr = false;
        }
        return this.fAttributes;
    }

    public boolean standaloneSet() {
        return this.fStandaloneSet;
    }

    public boolean isStandAlone() {
        return this.fStandalone;
    }

    protected void scanAttribute(XMLAttributes xMLAttributes) throws IOException {
        boolean z = false;
        if (this.fNamespaces) {
            this.fEntityScanner.scanQName(this.fAttributeQName);
        } else {
            String strScanName = this.fEntityScanner.scanName();
            this.fAttributeQName.setValues(null, strScanName, strScanName, null);
        }
        this.fEntityScanner.skipSpaces();
        if (!this.fEntityScanner.skipChar(61)) {
            reportFatalError("EqRequiredInAttribute", new Object[]{this.fAttributeQName.rawname});
        }
        this.fEntityScanner.skipSpaces();
        int length = xMLAttributes.getLength();
        xMLAttributes.addAttribute(this.fAttributeQName, XMLSymbols.fCDATASymbol, null);
        if (length == xMLAttributes.getLength()) {
            reportFatalError("AttributeNotUnique", new Object[]{this.fCurrentElement.rawname, this.fAttributeQName.rawname});
        }
        if (this.fHasExternalDTD && !this.fStandalone) {
            z = true;
        }
        scanAttributeValue(this.fTempString, this.fTempString2, this.fAttributeQName.rawname, xMLAttributes, length, z);
        xMLAttributes.setValue(length, this.fTempString.toString());
        xMLAttributes.setSpecified(length, true);
    }

    protected int scanContent(XMLStringBuffer xMLStringBuffer) throws IOException {
        int i = -1;
        this.fTempString.length = 0;
        int iScanContent = this.fEntityScanner.scanContent(this.fTempString);
        xMLStringBuffer.append(this.fTempString);
        this.fTempString.length = 0;
        if (iScanContent == 13) {
            this.fEntityScanner.scanChar();
            xMLStringBuffer.append((char) iScanContent);
        } else if (iScanContent == 93) {
            xMLStringBuffer.append((char) this.fEntityScanner.scanChar());
            this.fInScanContent = true;
            if (this.fEntityScanner.skipChar(93)) {
                xMLStringBuffer.append(']');
                while (this.fEntityScanner.skipChar(93)) {
                    xMLStringBuffer.append(']');
                }
                if (this.fEntityScanner.skipChar(62)) {
                    reportFatalError("CDEndInContent", null);
                }
            }
            this.fInScanContent = false;
        } else {
            i = iScanContent;
        }
        if (this.fDocumentHandler != null && xMLStringBuffer.length > 0) {
            this.fDocumentHandler.characters(xMLStringBuffer, null);
        }
        return i;
    }

    protected boolean scanCDATASection(XMLStringBuffer xMLStringBuffer, boolean z) throws IOException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startCDATA(null);
        }
        if (this.fEntityScanner.scanData(XMLStreamWriterImpl.END_CDATA, xMLStringBuffer)) {
            int iPeekChar = this.fEntityScanner.peekChar();
            if (iPeekChar != -1 && isInvalidLiteral(iPeekChar)) {
                if (XMLChar.isHighSurrogate(iPeekChar)) {
                    scanSurrogates(xMLStringBuffer);
                } else {
                    reportFatalError("InvalidCharInCDSect", new Object[]{Integer.toString(iPeekChar, 16)});
                    this.fEntityScanner.scanChar();
                }
            }
            if (this.fDocumentHandler != null) {
                this.fDocumentHandler.characters(xMLStringBuffer, null);
            }
        }
        this.fMarkupDepth--;
        if (this.fDocumentHandler != null && xMLStringBuffer.length > 0) {
            this.fDocumentHandler.characters(xMLStringBuffer, null);
        }
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endCDATA(null);
        }
        return true;
    }

    protected int scanEndElement() throws IOException {
        String str = this.fElementStack.popElement().rawname;
        if (!this.fEntityScanner.skipString(str)) {
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
        return this.fMarkupDepth;
    }

    protected void scanCharReference() {
        this.fStringBuffer2.clear();
        int iScanCharReferenceValue = scanCharReferenceValue(this.fStringBuffer2, null);
        this.fMarkupDepth--;
        if (iScanCharReferenceValue != -1 && this.fDocumentHandler != null) {
            if (this.fNotifyCharRefs) {
                this.fDocumentHandler.startGeneralEntity(this.fCharRefLiteral, null, null, null);
            }
            this.fDocumentHandler.characters(this.fStringBuffer2, null);
            if (this.fNotifyCharRefs) {
                this.fDocumentHandler.endGeneralEntity(this.fCharRefLiteral, null);
            }
        }
    }

    protected void scanEntityReference(XMLStringBuffer xMLStringBuffer) throws IOException {
        String strScanName = this.fEntityScanner.scanName();
        if (strScanName == null) {
            reportFatalError("NameRequiredInReference", null);
        }
        if (!this.fEntityScanner.skipChar(59)) {
            reportFatalError("SemicolonRequiredInReference", new Object[]{strScanName});
        }
        if (this.fEntityStore.isUnparsedEntity(strScanName)) {
            reportFatalError("ReferenceToUnparsedEntity", new Object[]{strScanName});
        }
        this.fMarkupDepth--;
        this.fCurrentEntityName = strScanName;
        if (strScanName == fAmpSymbol) {
            handleCharacter('&', fAmpSymbol, xMLStringBuffer);
            this.fScannerState = SCANNER_STATE_BUILT_IN_REFS;
            return;
        }
        if (strScanName == fLtSymbol) {
            handleCharacter(XMLStreamWriterImpl.OPEN_START_TAG, fLtSymbol, xMLStringBuffer);
            this.fScannerState = SCANNER_STATE_BUILT_IN_REFS;
            return;
        }
        if (strScanName == fGtSymbol) {
            handleCharacter('>', fGtSymbol, xMLStringBuffer);
            this.fScannerState = SCANNER_STATE_BUILT_IN_REFS;
            return;
        }
        if (strScanName == fQuotSymbol) {
            handleCharacter('\"', fQuotSymbol, xMLStringBuffer);
            this.fScannerState = SCANNER_STATE_BUILT_IN_REFS;
            return;
        }
        if (strScanName == fAposSymbol) {
            handleCharacter('\'', fAposSymbol, xMLStringBuffer);
            this.fScannerState = SCANNER_STATE_BUILT_IN_REFS;
            return;
        }
        if ((this.fEntityStore.isExternalEntity(strScanName) && !this.fSupportExternalEntities) || (!this.fEntityStore.isExternalEntity(strScanName) && !this.fReplaceEntityReferences)) {
            this.fScannerState = SCANNER_STATE_REFERENCE;
            return;
        }
        if (!this.fEntityStore.isDeclaredEntity(strScanName)) {
            if (this.fDisallowDoctype && this.fReplaceEntityReferences) {
                reportFatalError("EntityNotDeclared", new Object[]{strScanName});
                return;
            } else if (this.fHasExternalDTD && !this.fStandalone) {
                if (this.fValidation) {
                    this.fErrorReporter.reportError(this.fEntityScanner, XMLMessageFormatter.XML_DOMAIN, "EntityNotDeclared", new Object[]{strScanName}, (short) 1);
                }
            } else {
                reportFatalError("EntityNotDeclared", new Object[]{strScanName});
            }
        }
        this.fEntityManager.startEntity(strScanName, false);
    }

    private void handleCharacter(char c, String str, XMLStringBuffer xMLStringBuffer) {
        this.foundBuiltInRefs = true;
        xMLStringBuffer.append(c);
        if (this.fDocumentHandler != null) {
            this.fSingleChar[0] = c;
            if (this.fNotifyBuiltInRefs) {
                this.fDocumentHandler.startGeneralEntity(str, null, null, null);
            }
            this.fTempString.setValues(this.fSingleChar, 0, 1);
            this.fDocumentHandler.characters(this.fTempString, null);
            if (this.fNotifyBuiltInRefs) {
                this.fDocumentHandler.endGeneralEntity(str, null);
            }
        }
    }

    protected final void setScannerState(int i) {
        this.fScannerState = i;
    }

    protected final void setDriver(Driver driver) {
        this.fDriver = driver;
    }

    protected String getScannerStateName(int i) {
        switch (i) {
            case SCANNER_STATE_START_OF_MARKUP /* 21 */:
                return "SCANNER_STATE_START_OF_MARKUP";
            case SCANNER_STATE_CONTENT /* 22 */:
                return "SCANNER_STATE_CONTENT";
            case SCANNER_STATE_PI /* 23 */:
                return "SCANNER_STATE_PI";
            case SCANNER_STATE_DOCTYPE /* 24 */:
                return "SCANNER_STATE_DOCTYPE";
            case SCANNER_STATE_XML_DECL /* 25 */:
            case 31:
            case 32:
            default:
                return new StringBuffer().append("??? (").append(i).append(')').toString();
            case SCANNER_STATE_ROOT_ELEMENT /* 26 */:
                return "SCANNER_STATE_ROOT_ELEMENT";
            case SCANNER_STATE_COMMENT /* 27 */:
                return "SCANNER_STATE_COMMENT";
            case SCANNER_STATE_REFERENCE /* 28 */:
                return "SCANNER_STATE_REFERENCE";
            case SCANNER_STATE_ATTRIBUTE /* 29 */:
                return "SCANNER_STATE_ATTRIBUTE";
            case SCANNER_STATE_ATTRIBUTE_VALUE /* 30 */:
                return "SCANNER_STATE_ATTRIBUTE_VALUE";
            case SCANNER_STATE_END_OF_INPUT /* 33 */:
                return "SCANNER_STATE_END_OF_INPUT";
            case SCANNER_STATE_TERMINATED /* 34 */:
                return "SCANNER_STATE_TERMINATED";
            case SCANNER_STATE_CDATA /* 35 */:
                return "SCANNER_STATE_CDATA";
            case SCANNER_STATE_TEXT_DECL /* 36 */:
                return "SCANNER_STATE_TEXT_DECL";
            case SCANNER_STATE_CHARACTER_DATA /* 37 */:
                return "SCANNER_STATE_CHARACTER_DATA";
            case SCANNER_STATE_START_ELEMENT_TAG /* 38 */:
                return "SCANNER_STATE_START_ELEMENT_TAG";
            case SCANNER_STATE_END_ELEMENT_TAG /* 39 */:
                return "SCANNER_STATE_END_ELEMENT_TAG";
        }
    }

    public String getEntityName() {
        return this.fCurrentEntityName;
    }

    public String getDriverName(Driver driver) {
        return "null";
    }

    public final class Element {
        public char[] fRawname;
        public Element next;
        public QName qname;

        public Element(QName qName, Element element) {
            this.qname.setValues(qName);
            this.fRawname = qName.rawname.toCharArray();
            this.next = element;
        }
    }

    public class ElementStack2 {
        protected int fCount;
        protected int fDepth;
        protected int fLastDepth;
        protected int fMark;
        protected int fPosition;
        protected QName[] fQName = new QName[20];

        public ElementStack2() {
            for (int i = 0; i < this.fQName.length; i++) {
                this.fQName[i] = new QName();
            }
            this.fPosition = 1;
            this.fMark = 1;
        }

        public void resize() {
            int length = this.fQName.length;
            QName[] qNameArr = new QName[length * 2];
            System.arraycopy(this.fQName, 0, qNameArr, 0, length);
            this.fQName = qNameArr;
            while (length < this.fQName.length) {
                this.fQName[length] = new QName();
                length++;
            }
        }

        public boolean matchElement(QName qName) {
            boolean z = true;
            if (this.fLastDepth <= this.fDepth || this.fDepth > 2) {
                z = false;
            } else if (qName.rawname == this.fQName[this.fDepth].rawname) {
                XMLDocumentFragmentScannerImpl.this.fAdd = false;
                this.fMark = this.fDepth - 1;
                this.fPosition = this.fMark + 1;
                this.fCount--;
            } else {
                XMLDocumentFragmentScannerImpl.this.fAdd = true;
                z = false;
            }
            int i = this.fDepth;
            this.fDepth = i + 1;
            this.fLastDepth = i;
            return z;
        }

        public QName nextElement() {
            if (this.fCount != this.fQName.length) {
                QName[] qNameArr = this.fQName;
                int i = this.fCount;
                this.fCount = i + 1;
                return qNameArr[i];
            }
            XMLDocumentFragmentScannerImpl.this.fShouldSkip = false;
            XMLDocumentFragmentScannerImpl.this.fAdd = false;
            QName[] qNameArr2 = this.fQName;
            int i2 = this.fCount - 1;
            this.fCount = i2;
            return qNameArr2[i2];
        }

        public QName getNext() {
            if (this.fPosition == this.fCount) {
                this.fPosition = this.fMark;
            }
            QName[] qNameArr = this.fQName;
            int i = this.fPosition;
            this.fPosition = i + 1;
            return qNameArr[i];
        }

        public int popElement() {
            int i = this.fDepth;
            this.fDepth = i - 1;
            return i;
        }

        public void clear() {
            this.fLastDepth = 0;
            this.fDepth = 0;
            this.fCount = 0;
            this.fMark = 1;
            this.fPosition = 1;
        }
    }

    public class ElementStack {
        protected int fCount;
        protected int fDepth;
        protected int fLastDepth;
        protected int fMark;
        protected int fPosition;
        protected int[] fInt = new int[20];
        protected QName[] fElements = new QName[20];

        public ElementStack() {
            for (int i = 0; i < this.fElements.length; i++) {
                this.fElements[i] = new QName();
            }
        }

        public QName pushElement(QName qName) {
            if (this.fDepth == this.fElements.length) {
                QName[] qNameArr = new QName[this.fElements.length * 2];
                System.arraycopy(this.fElements, 0, qNameArr, 0, this.fDepth);
                this.fElements = qNameArr;
                for (int i = this.fDepth; i < this.fElements.length; i++) {
                    this.fElements[i] = new QName();
                }
            }
            this.fElements[this.fDepth].setValues(qName);
            QName[] qNameArr2 = this.fElements;
            int i2 = this.fDepth;
            this.fDepth = i2 + 1;
            return qNameArr2[i2];
        }

        public QName getNext() {
            if (this.fPosition == this.fCount) {
                this.fPosition = this.fMark;
            }
            return this.fElements[this.fPosition];
        }

        public void push() {
            int[] iArr = this.fInt;
            int i = this.fDepth + 1;
            this.fDepth = i;
            int i2 = this.fPosition;
            this.fPosition = i2 + 1;
            iArr[i] = i2;
        }

        public boolean matchElement(QName qName) {
            boolean z = true;
            if (this.fLastDepth <= this.fDepth || this.fDepth > 3) {
                z = false;
            } else if (qName.rawname == this.fElements[this.fDepth - 1].rawname) {
                XMLDocumentFragmentScannerImpl.this.fAdd = false;
                this.fMark = this.fDepth - 1;
                this.fPosition = this.fMark;
                this.fCount--;
            } else {
                XMLDocumentFragmentScannerImpl.this.fAdd = true;
                z = false;
            }
            if (z) {
                int[] iArr = this.fInt;
                int i = this.fDepth;
                int i2 = this.fPosition;
                this.fPosition = i2 + 1;
                iArr[i] = i2;
            } else {
                this.fInt[this.fDepth] = this.fCount - 1;
            }
            if (this.fCount == this.fElements.length) {
                XMLDocumentFragmentScannerImpl.this.fSkip = false;
                XMLDocumentFragmentScannerImpl.this.fAdd = false;
                reposition();
                return false;
            }
            this.fLastDepth = this.fDepth;
            return z;
        }

        public QName nextElement() {
            if (XMLDocumentFragmentScannerImpl.this.fSkip) {
                this.fDepth++;
                QName[] qNameArr = this.fElements;
                int i = this.fCount;
                this.fCount = i + 1;
                return qNameArr[i];
            }
            if (this.fDepth == this.fElements.length) {
                QName[] qNameArr2 = new QName[this.fElements.length * 2];
                System.arraycopy(this.fElements, 0, qNameArr2, 0, this.fDepth);
                this.fElements = qNameArr2;
                for (int i2 = this.fDepth; i2 < this.fElements.length; i2++) {
                    this.fElements[i2] = new QName();
                }
            }
            QName[] qNameArr3 = this.fElements;
            int i3 = this.fDepth;
            this.fDepth = i3 + 1;
            return qNameArr3[i3];
        }

        public QName popElement() {
            if (!XMLDocumentFragmentScannerImpl.this.fSkip && !XMLDocumentFragmentScannerImpl.this.fAdd) {
                QName[] qNameArr = this.fElements;
                int i = this.fDepth - 1;
                this.fDepth = i;
                return qNameArr[i];
            }
            QName[] qNameArr2 = this.fElements;
            int[] iArr = this.fInt;
            int i2 = this.fDepth;
            this.fDepth = i2 - 1;
            return qNameArr2[iArr[i2]];
        }

        public void reposition() {
            for (int i = 2; i <= this.fDepth; i++) {
                this.fElements[i - 1] = this.fElements[this.fInt[i]];
            }
        }

        public void clear() {
            this.fDepth = 0;
            this.fLastDepth = 0;
            this.fCount = 0;
            this.fMark = 1;
            this.fPosition = 1;
        }

        public QName getLastPoppedElement() {
            return this.fElements[this.fDepth];
        }
    }

    public class FragmentContentDriver implements Driver {
        private boolean fContinueDispatching = true;
        private boolean fScanningForMarkup = true;

        protected FragmentContentDriver() {
        }

        private void startOfMarkup() throws IOException {
            XMLDocumentFragmentScannerImpl.this.fMarkupDepth++;
            int iPeekChar = XMLDocumentFragmentScannerImpl.this.fEntityScanner.peekChar();
            switch (iPeekChar) {
                case XMLDocumentFragmentScannerImpl.SCANNER_STATE_END_OF_INPUT /* 33 */:
                    XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(iPeekChar);
                    if (XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(45)) {
                        if (!XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(45)) {
                            XMLDocumentFragmentScannerImpl.this.reportFatalError("InvalidCommentStart", null);
                        }
                        XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_COMMENT);
                    } else if (XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipString(XMLDocumentFragmentScannerImpl.cdata)) {
                        XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_CDATA);
                    } else if (!scanForDoctypeHook()) {
                        XMLDocumentFragmentScannerImpl.this.reportFatalError("MarkupNotRecognizedInContent", null);
                    }
                    break;
                case 47:
                    XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_END_ELEMENT_TAG);
                    XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(iPeekChar);
                    break;
                case 63:
                    XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_PI);
                    XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(iPeekChar);
                    break;
                default:
                    if (XMLScanner.isValidNameStartChar(iPeekChar)) {
                        XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_START_ELEMENT_TAG);
                    } else {
                        XMLDocumentFragmentScannerImpl.this.reportFatalError("MarkupNotRecognizedInContent", null);
                    }
                    break;
            }
        }

        private void startOfContent() {
            if (XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(60)) {
                XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_START_OF_MARKUP);
            } else if (XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(XMLDocumentFragmentScannerImpl.SCANNER_STATE_START_ELEMENT_TAG)) {
                XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_REFERENCE);
            } else {
                XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_CHARACTER_DATA);
            }
        }

        public void decideSubState() throws IOException {
            while (true) {
                if (XMLDocumentFragmentScannerImpl.this.fScannerState == XMLDocumentFragmentScannerImpl.SCANNER_STATE_CONTENT || XMLDocumentFragmentScannerImpl.this.fScannerState == XMLDocumentFragmentScannerImpl.SCANNER_STATE_START_OF_MARKUP) {
                    switch (XMLDocumentFragmentScannerImpl.this.fScannerState) {
                        case XMLDocumentFragmentScannerImpl.SCANNER_STATE_START_OF_MARKUP /* 21 */:
                            startOfMarkup();
                            break;
                        case XMLDocumentFragmentScannerImpl.SCANNER_STATE_CONTENT /* 22 */:
                            startOfContent();
                            break;
                    }
                } else {
                    return;
                }
            }
        }

        @Override // com.amazonaws.javax.xml.stream.XMLDocumentFragmentScannerImpl.Driver
        public int next() throws IOException {
            boolean z = true;
            try {
                switch (XMLDocumentFragmentScannerImpl.this.fScannerState) {
                    case XMLDocumentFragmentScannerImpl.SCANNER_STATE_CONTENT /* 22 */:
                        int iPeekChar = XMLDocumentFragmentScannerImpl.this.fEntityScanner.peekChar();
                        if (iPeekChar == 60) {
                            XMLDocumentFragmentScannerImpl.this.fEntityScanner.scanChar();
                            XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_START_OF_MARKUP);
                            break;
                        } else {
                            if (iPeekChar == XMLDocumentFragmentScannerImpl.SCANNER_STATE_START_ELEMENT_TAG) {
                                XMLDocumentFragmentScannerImpl.this.fEntityScanner.scanChar();
                                XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_REFERENCE);
                            } else {
                                XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_CHARACTER_DATA);
                            }
                            break;
                        }
                    case XMLDocumentFragmentScannerImpl.SCANNER_STATE_START_OF_MARKUP /* 21 */:
                        startOfMarkup();
                        break;
                }
                if (XMLDocumentFragmentScannerImpl.this.fIsCoalesce) {
                    XMLDocumentFragmentScannerImpl.this.fUsebuffer = true;
                    if (XMLDocumentFragmentScannerImpl.this.fLastSectionWasCharacterData) {
                        if (XMLDocumentFragmentScannerImpl.this.fScannerState != XMLDocumentFragmentScannerImpl.SCANNER_STATE_CDATA && XMLDocumentFragmentScannerImpl.this.fScannerState != XMLDocumentFragmentScannerImpl.SCANNER_STATE_REFERENCE && XMLDocumentFragmentScannerImpl.this.fScannerState != XMLDocumentFragmentScannerImpl.SCANNER_STATE_CHARACTER_DATA) {
                            XMLDocumentFragmentScannerImpl.this.fLastSectionWasCharacterData = false;
                            return 4;
                        }
                    } else if ((XMLDocumentFragmentScannerImpl.this.fLastSectionWasCData || XMLDocumentFragmentScannerImpl.this.fLastSectionWasEntityReference) && XMLDocumentFragmentScannerImpl.this.fScannerState != XMLDocumentFragmentScannerImpl.SCANNER_STATE_CDATA && XMLDocumentFragmentScannerImpl.this.fScannerState != XMLDocumentFragmentScannerImpl.SCANNER_STATE_REFERENCE && XMLDocumentFragmentScannerImpl.this.fScannerState != XMLDocumentFragmentScannerImpl.SCANNER_STATE_CHARACTER_DATA) {
                        XMLDocumentFragmentScannerImpl.this.fLastSectionWasCData = false;
                        XMLDocumentFragmentScannerImpl.this.fLastSectionWasEntityReference = false;
                        return 4;
                    }
                }
                switch (XMLDocumentFragmentScannerImpl.this.fScannerState) {
                    case 7:
                        return 7;
                    case XMLDocumentFragmentScannerImpl.SCANNER_STATE_PI /* 23 */:
                        XMLDocumentFragmentScannerImpl.this.fContentBuffer.clear();
                        XMLDocumentFragmentScannerImpl.this.scanPI(XMLDocumentFragmentScannerImpl.this.fContentBuffer);
                        XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_CONTENT);
                        return 3;
                    case XMLDocumentFragmentScannerImpl.SCANNER_STATE_ROOT_ELEMENT /* 26 */:
                        if (scanRootElementHook()) {
                            return -1;
                        }
                        XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_CONTENT);
                        return -1;
                    case XMLDocumentFragmentScannerImpl.SCANNER_STATE_COMMENT /* 27 */:
                        XMLDocumentFragmentScannerImpl.this.scanComment();
                        XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_CONTENT);
                        return 5;
                    case XMLDocumentFragmentScannerImpl.SCANNER_STATE_REFERENCE /* 28 */:
                        XMLDocumentFragmentScannerImpl.this.fMarkupDepth++;
                        XMLDocumentFragmentScannerImpl.this.foundBuiltInRefs = false;
                        if (XMLDocumentFragmentScannerImpl.this.fIsCoalesce && (XMLDocumentFragmentScannerImpl.this.fLastSectionWasEntityReference || XMLDocumentFragmentScannerImpl.this.fLastSectionWasCData || XMLDocumentFragmentScannerImpl.this.fLastSectionWasCharacterData)) {
                            XMLDocumentFragmentScannerImpl.this.fLastSectionWasEntityReference = true;
                            XMLDocumentFragmentScannerImpl.this.fLastSectionWasCData = false;
                            XMLDocumentFragmentScannerImpl.this.fLastSectionWasCharacterData = false;
                        } else {
                            XMLDocumentFragmentScannerImpl.this.fContentBuffer.clear();
                        }
                        XMLDocumentFragmentScannerImpl.this.fUsebuffer = true;
                        if (XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(XMLDocumentFragmentScannerImpl.SCANNER_STATE_CDATA)) {
                            XMLDocumentFragmentScannerImpl.this.scanCharReferenceValue(XMLDocumentFragmentScannerImpl.this.fContentBuffer, null);
                            XMLDocumentFragmentScannerImpl.this.fMarkupDepth--;
                            if (!XMLDocumentFragmentScannerImpl.this.fIsCoalesce) {
                                XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_CONTENT);
                                return 4;
                            }
                        } else {
                            XMLDocumentFragmentScannerImpl.this.scanEntityReference(XMLDocumentFragmentScannerImpl.this.fContentBuffer);
                        }
                        if (XMLDocumentFragmentScannerImpl.this.fScannerState == XMLDocumentFragmentScannerImpl.SCANNER_STATE_BUILT_IN_REFS && !XMLDocumentFragmentScannerImpl.this.fIsCoalesce) {
                            XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_CONTENT);
                            return 4;
                        }
                        if (XMLDocumentFragmentScannerImpl.this.fScannerState == XMLDocumentFragmentScannerImpl.SCANNER_STATE_TEXT_DECL) {
                            XMLDocumentFragmentScannerImpl.this.fLastSectionWasEntityReference = true;
                            return XMLDocumentFragmentScannerImpl.this.fDriver.next();
                        }
                        if (XMLDocumentFragmentScannerImpl.this.fScannerState == XMLDocumentFragmentScannerImpl.SCANNER_STATE_REFERENCE) {
                            XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_CONTENT);
                            if (!XMLDocumentFragmentScannerImpl.this.fReplaceEntityReferences) {
                                return 9;
                            }
                        }
                        XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_CONTENT);
                        XMLDocumentFragmentScannerImpl.this.fLastSectionWasEntityReference = true;
                        return XMLDocumentFragmentScannerImpl.this.fDriver.next();
                    case XMLDocumentFragmentScannerImpl.SCANNER_STATE_CDATA /* 35 */:
                        if (XMLDocumentFragmentScannerImpl.this.fIsCoalesce && (XMLDocumentFragmentScannerImpl.this.fLastSectionWasEntityReference || XMLDocumentFragmentScannerImpl.this.fLastSectionWasCData || XMLDocumentFragmentScannerImpl.this.fLastSectionWasCharacterData)) {
                            XMLDocumentFragmentScannerImpl.this.fLastSectionWasCData = true;
                            XMLDocumentFragmentScannerImpl.this.fLastSectionWasEntityReference = false;
                            XMLDocumentFragmentScannerImpl.this.fLastSectionWasCharacterData = false;
                        } else {
                            XMLDocumentFragmentScannerImpl.this.fContentBuffer.clear();
                        }
                        XMLDocumentFragmentScannerImpl.this.fUsebuffer = true;
                        XMLDocumentFragmentScannerImpl.this.scanCDATASection(XMLDocumentFragmentScannerImpl.this.fContentBuffer, true);
                        XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_CONTENT);
                        if (!XMLDocumentFragmentScannerImpl.this.fIsCoalesce) {
                            return XMLDocumentFragmentScannerImpl.this.fReportCdataEvent ? 12 : 4;
                        }
                        XMLDocumentFragmentScannerImpl.this.fLastSectionWasCData = true;
                        return XMLDocumentFragmentScannerImpl.this.fDriver.next();
                    case XMLDocumentFragmentScannerImpl.SCANNER_STATE_TEXT_DECL /* 36 */:
                        if (XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipString("<?xml")) {
                            XMLDocumentFragmentScannerImpl.this.fMarkupDepth++;
                            if (XMLScanner.isValidNameChar(XMLDocumentFragmentScannerImpl.this.fEntityScanner.peekChar())) {
                                XMLDocumentFragmentScannerImpl.this.fStringBuffer.clear();
                                XMLDocumentFragmentScannerImpl.this.fStringBuffer.append("xml");
                                if (XMLDocumentFragmentScannerImpl.this.fNamespaces) {
                                    while (XMLScanner.isValidNCName(XMLDocumentFragmentScannerImpl.this.fEntityScanner.peekChar())) {
                                        XMLDocumentFragmentScannerImpl.this.fStringBuffer.append((char) XMLDocumentFragmentScannerImpl.this.fEntityScanner.scanChar());
                                    }
                                } else {
                                    while (XMLScanner.isValidNameChar(XMLDocumentFragmentScannerImpl.this.fEntityScanner.peekChar())) {
                                        XMLDocumentFragmentScannerImpl.this.fStringBuffer.append((char) XMLDocumentFragmentScannerImpl.this.fEntityScanner.scanChar());
                                    }
                                }
                                String strAddSymbol = XMLDocumentFragmentScannerImpl.this.fSymbolTable.addSymbol(XMLDocumentFragmentScannerImpl.this.fStringBuffer.ch, XMLDocumentFragmentScannerImpl.this.fStringBuffer.offset, XMLDocumentFragmentScannerImpl.this.fStringBuffer.length);
                                XMLDocumentFragmentScannerImpl.this.fStringBuffer.clear();
                                XMLDocumentFragmentScannerImpl.this.scanPIData(strAddSymbol, XMLDocumentFragmentScannerImpl.this.fStringBuffer);
                            } else {
                                XMLDocumentFragmentScannerImpl.this.scanXMLDeclOrTextDecl(true);
                            }
                        }
                        XMLDocumentFragmentScannerImpl.this.fEntityManager.fCurrentEntity.mayReadChunks = true;
                        XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_CONTENT);
                        return XMLDocumentFragmentScannerImpl.this.fDriver.next();
                    case XMLDocumentFragmentScannerImpl.SCANNER_STATE_CHARACTER_DATA /* 37 */:
                        XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl = XMLDocumentFragmentScannerImpl.this;
                        if (!XMLDocumentFragmentScannerImpl.this.fLastSectionWasEntityReference && !XMLDocumentFragmentScannerImpl.this.fLastSectionWasCData && !XMLDocumentFragmentScannerImpl.this.fLastSectionWasCharacterData) {
                            z = false;
                        }
                        xMLDocumentFragmentScannerImpl.fUsebuffer = z;
                        if (XMLDocumentFragmentScannerImpl.this.fIsCoalesce && (XMLDocumentFragmentScannerImpl.this.fLastSectionWasEntityReference || XMLDocumentFragmentScannerImpl.this.fLastSectionWasCData || XMLDocumentFragmentScannerImpl.this.fLastSectionWasCharacterData)) {
                            XMLDocumentFragmentScannerImpl.this.fLastSectionWasEntityReference = false;
                            XMLDocumentFragmentScannerImpl.this.fLastSectionWasCData = false;
                            XMLDocumentFragmentScannerImpl.this.fLastSectionWasCharacterData = true;
                            XMLDocumentFragmentScannerImpl.this.fUsebuffer = true;
                        } else {
                            XMLDocumentFragmentScannerImpl.this.fContentBuffer.clear();
                        }
                        XMLDocumentFragmentScannerImpl.this.fTempString.length = 0;
                        int iScanContent = XMLDocumentFragmentScannerImpl.this.fEntityScanner.scanContent(XMLDocumentFragmentScannerImpl.this.fTempString);
                        if (XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(60)) {
                            if (XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(47)) {
                                XMLDocumentFragmentScannerImpl.this.fMarkupDepth++;
                                XMLDocumentFragmentScannerImpl.this.fLastSectionWasCharacterData = false;
                                XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_END_ELEMENT_TAG);
                            } else if (XMLChar.isNameStart(XMLDocumentFragmentScannerImpl.this.fEntityScanner.peekChar())) {
                                XMLDocumentFragmentScannerImpl.this.fMarkupDepth++;
                                XMLDocumentFragmentScannerImpl.this.fLastSectionWasCharacterData = false;
                                XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_START_ELEMENT_TAG);
                            } else {
                                XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_START_OF_MARKUP);
                                if (XMLDocumentFragmentScannerImpl.this.fIsCoalesce) {
                                    XMLDocumentFragmentScannerImpl.this.fUsebuffer = true;
                                    XMLDocumentFragmentScannerImpl.this.fLastSectionWasCharacterData = true;
                                    XMLDocumentFragmentScannerImpl.this.fContentBuffer.append(XMLDocumentFragmentScannerImpl.this.fTempString);
                                    XMLDocumentFragmentScannerImpl.this.fTempString.length = 0;
                                    return XMLDocumentFragmentScannerImpl.this.fDriver.next();
                                }
                            }
                            if (XMLDocumentFragmentScannerImpl.this.fUsebuffer) {
                                XMLDocumentFragmentScannerImpl.this.fContentBuffer.append(XMLDocumentFragmentScannerImpl.this.fTempString);
                                XMLDocumentFragmentScannerImpl.this.fTempString.length = 0;
                            }
                            return (XMLDocumentFragmentScannerImpl.this.dtdGrammarUtil == null || !XMLDocumentFragmentScannerImpl.this.dtdGrammarUtil.isIgnorableWhiteSpace(XMLDocumentFragmentScannerImpl.this.fContentBuffer)) ? 4 : 6;
                        }
                        XMLDocumentFragmentScannerImpl.this.fUsebuffer = true;
                        XMLDocumentFragmentScannerImpl.this.fContentBuffer.append(XMLDocumentFragmentScannerImpl.this.fTempString);
                        XMLDocumentFragmentScannerImpl.this.fTempString.length = 0;
                        if (iScanContent == 13) {
                            XMLDocumentFragmentScannerImpl.this.fEntityScanner.scanChar();
                            XMLDocumentFragmentScannerImpl.this.fUsebuffer = true;
                            XMLDocumentFragmentScannerImpl.this.fContentBuffer.append((char) iScanContent);
                            iScanContent = -1;
                        } else if (iScanContent == 93) {
                            XMLDocumentFragmentScannerImpl.this.fUsebuffer = true;
                            XMLDocumentFragmentScannerImpl.this.fContentBuffer.append((char) XMLDocumentFragmentScannerImpl.this.fEntityScanner.scanChar());
                            XMLDocumentFragmentScannerImpl.this.fInScanContent = true;
                            if (XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(93)) {
                                XMLDocumentFragmentScannerImpl.this.fContentBuffer.append(']');
                                while (XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(93)) {
                                    XMLDocumentFragmentScannerImpl.this.fContentBuffer.append(']');
                                }
                                if (XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(62)) {
                                    XMLDocumentFragmentScannerImpl.this.reportFatalError("CDEndInContent", null);
                                }
                            }
                            XMLDocumentFragmentScannerImpl.this.fInScanContent = false;
                            iScanContent = -1;
                        }
                        while (true) {
                            if (iScanContent == 60) {
                                XMLDocumentFragmentScannerImpl.this.fEntityScanner.scanChar();
                                XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_START_OF_MARKUP);
                            } else if (iScanContent == XMLDocumentFragmentScannerImpl.SCANNER_STATE_START_ELEMENT_TAG) {
                                XMLDocumentFragmentScannerImpl.this.fEntityScanner.scanChar();
                                XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_REFERENCE);
                            } else if (iScanContent != -1 && XMLScanner.isInvalidLiteral(iScanContent)) {
                                if (XMLChar.isHighSurrogate(iScanContent)) {
                                    XMLDocumentFragmentScannerImpl.this.scanSurrogates(XMLDocumentFragmentScannerImpl.this.fContentBuffer);
                                    XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_CONTENT);
                                } else {
                                    XMLDocumentFragmentScannerImpl.this.reportFatalError("InvalidCharInContent", new Object[]{Integer.toString(iScanContent, 16)});
                                    XMLDocumentFragmentScannerImpl.this.fEntityScanner.scanChar();
                                }
                            } else {
                                iScanContent = XMLDocumentFragmentScannerImpl.this.scanContent(XMLDocumentFragmentScannerImpl.this.fContentBuffer);
                                if (!XMLDocumentFragmentScannerImpl.this.fIsCoalesce) {
                                    XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_CONTENT);
                                }
                            }
                        }
                        if (!XMLDocumentFragmentScannerImpl.this.fIsCoalesce) {
                            return (XMLDocumentFragmentScannerImpl.this.dtdGrammarUtil == null || !XMLDocumentFragmentScannerImpl.this.dtdGrammarUtil.isIgnorableWhiteSpace(XMLDocumentFragmentScannerImpl.this.fContentBuffer)) ? 4 : 6;
                        }
                        XMLDocumentFragmentScannerImpl.this.fLastSectionWasCharacterData = true;
                        return XMLDocumentFragmentScannerImpl.this.fDriver.next();
                    case XMLDocumentFragmentScannerImpl.SCANNER_STATE_START_ELEMENT_TAG /* 38 */:
                        XMLDocumentFragmentScannerImpl.this.fEmptyElement = XMLDocumentFragmentScannerImpl.this.scanStartElement();
                        if (XMLDocumentFragmentScannerImpl.this.fEmptyElement) {
                            XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_END_ELEMENT_TAG);
                        } else {
                            XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_CONTENT);
                        }
                        return 1;
                    case XMLDocumentFragmentScannerImpl.SCANNER_STATE_END_ELEMENT_TAG /* 39 */:
                        if (XMLDocumentFragmentScannerImpl.this.fEmptyElement) {
                            XMLDocumentFragmentScannerImpl.this.fEmptyElement = false;
                            XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_CONTENT);
                            return (XMLDocumentFragmentScannerImpl.this.fMarkupDepth == 0 && elementDepthIsZeroHook()) ? 2 : 2;
                        }
                        if (XMLDocumentFragmentScannerImpl.this.scanEndElement() == 0 && elementDepthIsZeroHook()) {
                            return 2;
                        }
                        XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_CONTENT);
                        return 2;
                    case XMLDocumentFragmentScannerImpl.SCANNER_STATE_CHAR_REFERENCE /* 40 */:
                        XMLDocumentFragmentScannerImpl.this.fContentBuffer.clear();
                        XMLDocumentFragmentScannerImpl.this.scanCharReferenceValue(XMLDocumentFragmentScannerImpl.this.fContentBuffer, null);
                        XMLDocumentFragmentScannerImpl.this.fMarkupDepth--;
                        XMLDocumentFragmentScannerImpl.this.setScannerState(XMLDocumentFragmentScannerImpl.SCANNER_STATE_CONTENT);
                        return 4;
                    default:
                        throw new XNIException(new StringBuffer().append("Scanner State ").append(XMLDocumentFragmentScannerImpl.this.fScannerState).append(" not Recognized ").toString());
                }
            } catch (EOFException e) {
                endOfFileHook(e);
                return -1;
            }
        }

        protected boolean scanForDoctypeHook() {
            return false;
        }

        protected boolean elementDepthIsZeroHook() {
            return false;
        }

        protected boolean scanRootElementHook() {
            return false;
        }

        protected void endOfFileHook(EOFException eOFException) {
            if (XMLDocumentFragmentScannerImpl.this.fMarkupDepth != 0) {
                XMLDocumentFragmentScannerImpl.this.reportFatalError("PrematureEOF", null);
            }
        }
    }

    static void pr(String str) {
        System.out.println(str);
    }
}

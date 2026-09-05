package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.a.a;
import com.amazonaws.javax.xml.a.b;
import com.amazonaws.javax.xml.stream.dtd.nonvalidating.DTDGrammar;
import com.amazonaws.javax.xml.stream.dtd.nonvalidating.XMLNotationDecl;
import com.amazonaws.javax.xml.stream.events.EntityDeclarationImpl;
import com.amazonaws.javax.xml.stream.events.NotationDeclarationImpl;
import com.amazonaws.javax.xml.stream.xerces.util.NamespaceContextWrapper;
import com.amazonaws.javax.xml.stream.xerces.util.SymbolTable;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import com.amazonaws.javax.xml.stream.xerces.xni.QName;
import com.amazonaws.javax.xml.stream.xerces.xni.XNIException;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLInputSource;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.NoSuchElementException;
import org.xml.sax.InputSource;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class XMLReaderImpl implements XMLStreamReader {
    static final boolean DEBUG = false;
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    private int fEventType;
    private SymbolTable fSymbolTable = new SymbolTable();
    protected XMLNSDocumentScannerImpl fScanner = new XMLNSDocumentScannerImpl();
    protected NamespaceContextWrapper fNamespaceContextWrapper = new NamespaceContextWrapper(this.fScanner.getNamespaceContext());
    protected XMLEntityManager fEntityManager = new XMLEntityManager();
    protected StaxErrorReporter fErrorReporter = new StaxErrorReporter();
    protected XMLEntityReaderImpl fEntityScanner = null;
    protected XMLInputSource fInputSource = null;
    protected PropertyManager fPropertyManager = null;
    private boolean fReuse = true;
    private boolean fBindNamespaces = true;
    private String fDTDDecl = null;

    public XMLReaderImpl(InputStream inputStream, PropertyManager propertyManager) throws XMLStreamException2 {
        init(propertyManager);
        setInputSource(new XMLInputSource((String) null, (String) null, (String) null, inputStream, (String) null));
    }

    public XMLReaderImpl(String str, PropertyManager propertyManager) throws XMLStreamException2 {
        init(propertyManager);
        setInputSource(new XMLInputSource(null, str, null));
    }

    public XMLReaderImpl(InputStream inputStream, String str, PropertyManager propertyManager) throws XMLStreamException2 {
        init(propertyManager);
        setInputSource(new XMLInputSource((String) null, (String) null, (String) null, new BufferedInputStream(inputStream), str));
    }

    public XMLReaderImpl(Reader reader, PropertyManager propertyManager) throws XMLStreamException2 {
        init(propertyManager);
        setInputSource(new XMLInputSource((String) null, (String) null, (String) null, new BufferedReader(reader), (String) null));
    }

    public XMLReaderImpl(XMLInputSource xMLInputSource, PropertyManager propertyManager) throws XMLStreamException2 {
        init(propertyManager);
        setInputSource(xMLInputSource);
    }

    public void setInputSource(XMLInputSource xMLInputSource) throws XMLStreamException2 {
        this.fReuse = DEBUG;
        try {
            this.fScanner.setInputSource(xMLInputSource);
            this.fEventType = this.fScanner.next();
        } catch (XNIException e) {
            throw new XMLStreamException2(e.getMessage(), getLocation(), e.getException());
        } catch (IOException e2) {
            throw new XMLStreamException2(e2);
        }
    }

    public void setInputSource(InputSource inputSource) throws XMLStreamException2 {
        setInputSource(convertSAXInputSource2XMLInputSource(inputSource));
    }

    XMLInputSource convertSAXInputSource2XMLInputSource(InputSource inputSource) {
        XMLInputSource xMLInputSource = new XMLInputSource(inputSource.getPublicId(), inputSource.getSystemId(), null);
        InputStream byteStream = inputSource.getByteStream();
        xMLInputSource.setByteStream((byteStream == null || (byteStream instanceof ByteArrayInputStream) || (byteStream instanceof BufferedInputStream)) ? byteStream : new BufferedInputStream(byteStream));
        Reader characterStream = inputSource.getCharacterStream();
        xMLInputSource.setCharacterStream((characterStream == null || (characterStream instanceof BufferedReader) || (characterStream instanceof CharArrayReader) || (characterStream instanceof StringReader)) ? characterStream : new BufferedReader(characterStream));
        xMLInputSource.setEncoding(inputSource.getEncoding());
        return xMLInputSource;
    }

    void init(PropertyManager propertyManager) {
        this.fPropertyManager = propertyManager;
        propertyManager.setProperty(SYMBOL_TABLE, this.fSymbolTable);
        propertyManager.setProperty(ERROR_REPORTER, this.fErrorReporter);
        propertyManager.setProperty(ENTITY_MANAGER, this.fEntityManager);
        reset();
    }

    public boolean canReuse() {
        return this.fReuse;
    }

    public void reset() {
        this.fReuse = true;
        this.fEventType = 0;
        this.fEntityManager.reset(this.fPropertyManager);
        this.fScanner.reset(this.fPropertyManager);
        this.fDTDDecl = null;
        this.fEntityScanner = (XMLEntityReaderImpl) this.fEntityManager.getEntityReader();
        this.fBindNamespaces = ((Boolean) this.fPropertyManager.getProperty(XMLInputFactory.IS_NAMESPACE_AWARE)).booleanValue();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public void close() {
        this.fReuse = true;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getCharacterEncodingScheme() {
        return this.fScanner.getCharacterEncodingScheme();
    }

    public int getColumnNumber() {
        return this.fEntityScanner.getColumnNumber();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getEncoding() {
        return this.fEntityScanner.getEncoding();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public int getEventType() {
        return this.fEventType;
    }

    public int getLineNumber() {
        return this.fEntityScanner.getLineNumber();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getLocalName() {
        if (this.fEventType == 1 || this.fEventType == 2) {
            return this.fScanner.getElementQName().localpart;
        }
        if (this.fEventType == 9) {
            return this.fScanner.getEntityName();
        }
        throw new IllegalStateException(new StringBuffer().append("Method getLocalName() cannot be called for ").append(getEventTypeString(this.fEventType)).append(" event.").toString());
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getNamespaceURI() {
        if (this.fEventType == 1 || this.fEventType == 2) {
            return this.fScanner.getElementQName().uri;
        }
        return null;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getPIData() {
        if (this.fEventType == 3) {
            return this.fScanner.getPIData().toString();
        }
        throw new IllegalStateException(new StringBuffer().append("Current state of the parser is ").append(getEventTypeString(this.fEventType)).append(" But expected state is ").append(getEventTypeString(3)).toString());
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getPITarget() {
        if (this.fEventType == 3) {
            return this.fScanner.getPITarget();
        }
        throw new IllegalStateException(new StringBuffer().append("Current state of the parser is ").append(getEventTypeString(this.fEventType)).append(" But expected state is ").append(getEventTypeString(3)).toString());
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getPrefix() {
        if (this.fEventType != 1 && this.fEventType != 2) {
            return null;
        }
        String str = this.fScanner.getElementQName().prefix;
        return str == null ? "" : str;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public char[] getTextCharacters() {
        if (this.fEventType == 4 || this.fEventType == 5 || this.fEventType == 12 || this.fEventType == 6) {
            return this.fScanner.getCharacterData().ch;
        }
        throw new IllegalStateException(new StringBuffer().append("Current state = ").append(getEventTypeString(this.fEventType)).append(" is not among the states ").append(getEventTypeString(4)).append(" , ").append(getEventTypeString(5)).append(" , ").append(getEventTypeString(12)).append(" , ").append(getEventTypeString(6)).append(" valid for getTextCharacters() ").toString());
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public int getTextLength() {
        if (this.fEventType == 4 || this.fEventType == 5 || this.fEventType == 12 || this.fEventType == 6) {
            return this.fScanner.getCharacterData().length;
        }
        throw new IllegalStateException(new StringBuffer().append("Current state = ").append(getEventTypeString(this.fEventType)).append(" is not among the states ").append(getEventTypeString(4)).append(" , ").append(getEventTypeString(5)).append(" , ").append(getEventTypeString(12)).append(" , ").append(getEventTypeString(6)).append(" valid for getTextLength() ").toString());
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public int getTextStart() {
        if (this.fEventType == 4 || this.fEventType == 5 || this.fEventType == 12 || this.fEventType == 6) {
            return this.fScanner.getCharacterData().offset;
        }
        throw new IllegalStateException(new StringBuffer().append("Current state = ").append(getEventTypeString(this.fEventType)).append(" is not among the states ").append(getEventTypeString(4)).append(" , ").append(getEventTypeString(5)).append(" , ").append(getEventTypeString(12)).append(" , ").append(getEventTypeString(6)).append(" valid for getTextStart() ").toString());
    }

    public String getValue() {
        if (this.fEventType == 3) {
            return this.fScanner.getPIData().toString();
        }
        if (this.fEventType == 5) {
            return this.fScanner.getComment();
        }
        if (this.fEventType == 1 || this.fEventType == 2) {
            return this.fScanner.getElementQName().localpart;
        }
        if (this.fEventType == 4) {
            return this.fScanner.getCharacterData().toString();
        }
        return null;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getVersion() {
        return this.fEntityScanner.getVersion();
    }

    public boolean hasAttributes() {
        if (this.fScanner.getAttributeIterator().getLength() > 0) {
            return true;
        }
        return DEBUG;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean hasName() {
        if (this.fEventType == 1 || this.fEventType == 2) {
            return true;
        }
        return DEBUG;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean hasNext() {
        if (this.fEventType == -1 || this.fEventType == 8) {
            return DEBUG;
        }
        return true;
    }

    public boolean hasValue() {
        if (this.fEventType == 1 || this.fEventType == 2 || this.fEventType == 9 || this.fEventType == 3 || this.fEventType == 5 || this.fEventType == 4) {
            return true;
        }
        return DEBUG;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean isEndElement() {
        if (this.fEventType == 2) {
            return true;
        }
        return DEBUG;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean isStandalone() {
        return this.fScanner.isStandAlone();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean isStartElement() {
        if (this.fEventType == 1) {
            return true;
        }
        return DEBUG;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean isWhiteSpace() {
        if (!isCharacters() && this.fEventType != 12) {
            return DEBUG;
        }
        char[] textCharacters = getTextCharacters();
        int textStart = getTextStart();
        int textLength = getTextLength() + textStart;
        while (textStart < textLength) {
            if (!XMLChar.isSpace(textCharacters[textStart])) {
                return DEBUG;
            }
            textStart++;
        }
        return true;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public int next() throws XMLStreamException {
        Boolean bool;
        if (!hasNext()) {
            if (this.fEventType != -1) {
                throw new NoSuchElementException("END_DOCUMENT reached: no more elements on the stream.");
            }
            throw new XMLStreamException("Error processing input source. The input stream is not complete.");
        }
        try {
            int next = this.fScanner.next();
            this.fEventType = next;
            return next;
        } catch (XNIException e) {
            throw new XMLStreamException2(e.getMessage(), getLocation(), e.getException());
        } catch (IOException e2) {
            int i = this.fScanner.fScannerState;
            XMLNSDocumentScannerImpl xMLNSDocumentScannerImpl = this.fScanner;
            if (i == 46 && (bool = (Boolean) this.fPropertyManager.getProperty(XMLInputFactory.IS_VALIDATING)) != null && !bool.booleanValue()) {
                this.fEventType = 11;
                XMLNSDocumentScannerImpl xMLNSDocumentScannerImpl2 = this.fScanner;
                XMLNSDocumentScannerImpl xMLNSDocumentScannerImpl3 = this.fScanner;
                xMLNSDocumentScannerImpl2.setScannerState(43);
                this.fScanner.setDriver(this.fScanner.fPrologDriver);
                if (this.fDTDDecl == null || this.fDTDDecl.length() == 0) {
                    this.fDTDDecl = "<!-- Exception scanning External DTD Subset.  True contents of DTD cannot be determined.  Processing will continue as XMLInputFactory.IS_VALIDATING == false. -->";
                }
                return 11;
            }
            throw new XMLStreamException2(e2.getMessage(), getLocation(), e2);
        }
    }

    static final String getEventTypeString(int i) {
        switch (i) {
            case 1:
                return "START_ELEMENT";
            case 2:
                return "END_ELEMENT";
            case 3:
                return "PROCESSING_INSTRUCTION";
            case 4:
                return "CHARACTERS";
            case 5:
                return "COMMENT";
            case 6:
                return "SPACE";
            case 7:
                return "START_DOCUMENT";
            case 8:
                return "END_DOCUMENT";
            case 9:
                return "ENTITY_REFERENCE";
            case XMLStreamConstants.ATTRIBUTE /* 10 */:
                return "ATTRIBUTE";
            case XMLStreamConstants.DTD /* 11 */:
                return "DTD";
            case XMLStreamConstants.CDATA /* 12 */:
                return "CDATA";
            default:
                return new StringBuffer().append("UNKNOWN_EVENT_TYPE , ").append(String.valueOf(i)).toString();
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public int getAttributeCount() {
        if (this.fEventType == 1 || this.fEventType == 10) {
            return this.fScanner.getAttributeIterator().getLength();
        }
        throw new IllegalStateException(new StringBuffer().append("Current state is not among the states ").append(getEventTypeString(1)).append(" , ").append(getEventTypeString(10)).append("valid for getAttributeCount()").toString());
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public b getAttributeName(int i) {
        if (this.fEventType == 1 || this.fEventType == 10) {
            return convertXNIQNametoJavaxQName(this.fScanner.getAttributeIterator().getQualifiedName(i));
        }
        throw new IllegalStateException(new StringBuffer().append("Current state is not among the states ").append(getEventTypeString(1)).append(" , ").append(getEventTypeString(10)).append("valid for getAttributeName()").toString());
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getAttributeLocalName(int i) {
        if (this.fEventType == 1 || this.fEventType == 10) {
            return this.fScanner.getAttributeIterator().getLocalName(i);
        }
        throw new IllegalStateException(new StringBuffer().append("Current state is not among the states ").append(getEventTypeString(1)).append(" , ").append(getEventTypeString(10)).append("valid for getAttributeLocalName()").toString());
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getAttributeNamespace(int i) {
        if (this.fEventType == 1 || this.fEventType == 10) {
            return this.fScanner.getAttributeIterator().getURI(i);
        }
        throw new IllegalStateException(new StringBuffer().append("Current state is not among the states ").append(getEventTypeString(1)).append(" , ").append(getEventTypeString(10)).append("valid for getAttributeNamespace()").toString());
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getAttributePrefix(int i) {
        if (this.fEventType == 1 || this.fEventType == 10) {
            return this.fScanner.getAttributeIterator().getPrefix(i);
        }
        throw new IllegalStateException(new StringBuffer().append("Current state is not among the states ").append(getEventTypeString(1)).append(" , ").append(getEventTypeString(10)).append("valid for getAttributePrefix()").toString());
    }

    public b getAttributeQName(int i) {
        if (this.fEventType == 1 || this.fEventType == 10) {
            return new b(this.fScanner.getAttributeIterator().getURI(i), this.fScanner.getAttributeIterator().getLocalName(i));
        }
        throw new IllegalStateException(new StringBuffer().append("Current state is not among the states ").append(getEventTypeString(1)).append(" , ").append(getEventTypeString(10)).append("valid for getAttributeQName()").toString());
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getAttributeType(int i) {
        if (this.fEventType == 1 || this.fEventType == 10) {
            return this.fScanner.getAttributeIterator().getType(i);
        }
        throw new IllegalStateException(new StringBuffer().append("Current state is not among the states ").append(getEventTypeString(1)).append(" , ").append(getEventTypeString(10)).append("valid for getAttributeType()").toString());
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getAttributeValue(int i) {
        if (this.fEventType == 1 || this.fEventType == 10) {
            return this.fScanner.getAttributeIterator().getValue(i);
        }
        throw new IllegalStateException(new StringBuffer().append("Current state is not among the states ").append(getEventTypeString(1)).append(" , ").append(getEventTypeString(10)).append("valid for getAttributeValue()").toString());
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getAttributeValue(String str, String str2) {
        if (this.fEventType == 1 || this.fEventType == 10) {
            return this.fScanner.getAttributeIterator().getValue(str, str2);
        }
        throw new IllegalStateException(new StringBuffer().append("Current state is not among the states ").append(getEventTypeString(1)).append(" , ").append(getEventTypeString(10)).append("valid for getAttributeValue()").toString());
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getElementText() throws XMLStreamException {
        if (getEventType() != 1) {
            throw new XMLStreamException2("parser must be on START_ELEMENT to read next text", getLocation());
        }
        int next = next();
        StringBuffer stringBuffer = new StringBuffer();
        while (next != 2) {
            if (next == 4 || next == 12 || next == 6 || next == 9) {
                stringBuffer.append(getText());
            } else if (next != 3 && next != 5) {
                if (next == 8) {
                    throw new XMLStreamException2("unexpected end of document when reading element text content");
                }
                if (next == 1) {
                    throw new XMLStreamException2("elementGetText() function expects text only elment but START_ELEMENT was encountered.", getLocation());
                }
                throw new XMLStreamException2(new StringBuffer().append("Unexpected event type ").append(next).toString(), getLocation());
            }
            next = next();
        }
        return stringBuffer.toString();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public Location getLocation() {
        return new Location() { // from class: com.amazonaws.javax.xml.stream.XMLReaderImpl.1
            int _columnNumber;
            int _lineNumber;
            int _offset;
            String _publicId;
            String _systemId;

            public String getLocationURI() {
                return this._systemId;
            }

            @Override // com.amazonaws.javax.xml.stream.Location
            public int getCharacterOffset() {
                return this._offset;
            }

            @Override // com.amazonaws.javax.xml.stream.Location
            public int getColumnNumber() {
                return this._columnNumber;
            }

            @Override // com.amazonaws.javax.xml.stream.Location
            public int getLineNumber() {
                return this._lineNumber;
            }

            @Override // com.amazonaws.javax.xml.stream.Location
            public String getPublicId() {
                return this._publicId;
            }

            @Override // com.amazonaws.javax.xml.stream.Location
            public String getSystemId() {
                return this._systemId;
            }

            {
                this._systemId = XMLReaderImpl.this.fEntityScanner.getExpandedSystemId();
                this._publicId = XMLReaderImpl.this.fEntityScanner.getPublicId();
                this._offset = XMLReaderImpl.this.fEntityScanner.getCharacterOffset();
                this._columnNumber = XMLReaderImpl.this.fEntityScanner.getColumnNumber();
                this._lineNumber = XMLReaderImpl.this.fEntityScanner.getLineNumber();
            }

            public String toString() {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(new StringBuffer().append("Line number = ").append(getLineNumber()).toString());
                stringBuffer.append("\n");
                stringBuffer.append(new StringBuffer().append("Column number = ").append(getColumnNumber()).toString());
                stringBuffer.append("\n");
                stringBuffer.append(new StringBuffer().append("System Id = ").append(getSystemId()).toString());
                stringBuffer.append("\n");
                stringBuffer.append(new StringBuffer().append("Public Id = ").append(getPublicId()).toString());
                stringBuffer.append("\n");
                stringBuffer.append(new StringBuffer().append("Location Uri= ").append(getLocationURI()).toString());
                stringBuffer.append("\n");
                stringBuffer.append(new StringBuffer().append("CharacterOffset = ").append(getCharacterOffset()).toString());
                stringBuffer.append("\n");
                return stringBuffer.toString();
            }
        };
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public b getName() {
        if (this.fEventType == 1 || this.fEventType == 2) {
            return convertXNIQNametoJavaxQName(this.fScanner.getElementQName());
        }
        throw new IllegalStateException(new StringBuffer().append("Illegal to call getName() when event type is ").append(getEventTypeString(this.fEventType)).append(".").append(" Valid states are ").append(getEventTypeString(1)).append(", ").append(getEventTypeString(2)).toString());
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public a getNamespaceContext() {
        return this.fNamespaceContextWrapper;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public int getNamespaceCount() {
        if (this.fEventType == 1 || this.fEventType == 2 || this.fEventType == 13) {
            return this.fScanner.getNamespaceContext().getDeclaredPrefixCount();
        }
        throw new IllegalStateException(new StringBuffer().append("Current state ").append(getEventTypeString(this.fEventType)).append(" is not among the states ").append(getEventTypeString(1)).append(", ").append(getEventTypeString(2)).append(", ").append(getEventTypeString(13)).append(" valid for getNamespaceCount().").toString());
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getNamespacePrefix(int i) {
        if (this.fEventType == 1 || this.fEventType == 2 || this.fEventType == 13) {
            String declaredPrefixAt = this.fScanner.getNamespaceContext().getDeclaredPrefixAt(i);
            if (declaredPrefixAt.equals("")) {
                return null;
            }
            return declaredPrefixAt;
        }
        throw new IllegalStateException(new StringBuffer().append("Current state ").append(getEventTypeString(this.fEventType)).append(" is not among the states ").append(getEventTypeString(1)).append(", ").append(getEventTypeString(2)).append(", ").append(getEventTypeString(13)).append(" valid for getNamespacePrefix().").toString());
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getNamespaceURI(int i) {
        if (this.fEventType == 1 || this.fEventType == 2 || this.fEventType == 13) {
            return this.fScanner.getNamespaceContext().getURI(this.fScanner.getNamespaceContext().getDeclaredPrefixAt(i));
        }
        throw new IllegalStateException(new StringBuffer().append("Current state ").append(getEventTypeString(this.fEventType)).append(" is not among the states ").append(getEventTypeString(1)).append(", ").append(getEventTypeString(2)).append(", ").append(getEventTypeString(13)).append(" valid for getNamespaceURI().").toString());
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public Object getProperty(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        if (this.fPropertyManager != null) {
            PropertyManager propertyManager = this.fPropertyManager;
            if (str.equals(PropertyManager.STAX_NOTATIONS)) {
                return getNotationDecls();
            }
            PropertyManager propertyManager2 = this.fPropertyManager;
            if (str.equals(PropertyManager.STAX_ENTITIES)) {
                return getEntityDecls();
            }
            return this.fPropertyManager.getProperty(str);
        }
        return null;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getText() {
        if (this.fEventType == 4 || this.fEventType == 5 || this.fEventType == 12 || this.fEventType == 6) {
            return this.fScanner.getCharacterData().toString();
        }
        if (this.fEventType == 9) {
            String entityName = this.fScanner.getEntityName();
            if (entityName != null) {
                if (this.fScanner.foundBuiltInRefs) {
                    return this.fScanner.getCharacterData().toString();
                }
                Entity entity = (Entity) this.fEntityManager.getEntityStore().getDeclaredEntities().get(entityName);
                if (entity == null) {
                    return null;
                }
                if (entity.isExternal()) {
                    return ((Entity.ExternalEntity) entity).entityLocation.getExpandedSystemId();
                }
                return ((Entity.InternalEntity) entity).text;
            }
            return null;
        }
        if (this.fEventType == 11) {
            if (this.fDTDDecl != null) {
                return this.fDTDDecl;
            }
            this.fDTDDecl = this.fScanner.getDTDDecl().toString();
            return this.fDTDDecl;
        }
        throw new IllegalStateException(new StringBuffer().append("Current state ").append(getEventTypeString(this.fEventType)).append(" is not among the states").append(getEventTypeString(4)).append(", ").append(getEventTypeString(5)).append(", ").append(getEventTypeString(12)).append(", ").append(getEventTypeString(6)).append(", ").append(getEventTypeString(9)).append(", ").append(getEventTypeString(11)).append(" valid for getText() ").toString());
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public void require(int i, String str, String str2) throws XMLStreamException2 {
        if (i != this.fEventType) {
            throw new XMLStreamException2(new StringBuffer().append("Event type ").append(getEventTypeString(i)).append(" specified did ").append("not match with current parser event ").append(getEventTypeString(this.fEventType)).toString());
        }
        if (str != null && !str.equals(getNamespaceURI())) {
            throw new XMLStreamException2(new StringBuffer().append("Namespace URI ").append(str).append(" specified did not match ").append("with current namespace URI").toString());
        }
        if (str2 != null && !str2.equals(getLocalName())) {
            throw new XMLStreamException2(new StringBuffer().append("LocalName ").append(str2).append(" specified did not match with ").append("current local name").toString());
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public int getTextCharacters(int i, char[] cArr, int i2, int i3) {
        if (cArr == null) {
            throw new NullPointerException("target char array can't be null");
        }
        if (i2 < 0 || i3 < 0 || i < 0 || i2 >= cArr.length || i2 + i3 > cArr.length) {
            throw new IndexOutOfBoundsException();
        }
        int textLength = getTextLength() - i;
        if (textLength < 0) {
            throw new IndexOutOfBoundsException("sourceStart is greater thannumber of characters associated with this event");
        }
        if (textLength < i3) {
            i3 = textLength;
        }
        System.arraycopy(getTextCharacters(), getTextStart() + i, cArr, i2, i3);
        return i3;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean hasText() {
        if (this.fEventType == 4 || this.fEventType == 5 || this.fEventType == 12) {
            return this.fScanner.getCharacterData().length > 0;
        }
        if (this.fEventType != 9) {
            return this.fEventType == 11 ? this.fScanner.fSeenDoctypeDecl : DEBUG;
        }
        String entityName = this.fScanner.getEntityName();
        if (entityName == null) {
            return DEBUG;
        }
        if (this.fScanner.foundBuiltInRefs) {
            return true;
        }
        Entity entity = (Entity) this.fEntityManager.getEntityStore().getDeclaredEntities().get(entityName);
        if (entity == null) {
            return DEBUG;
        }
        if (entity.isExternal()) {
            if (((Entity.ExternalEntity) entity).entityLocation.getExpandedSystemId() == null) {
                return DEBUG;
            }
            return true;
        }
        if (((Entity.InternalEntity) entity).text == null) {
            return DEBUG;
        }
        return true;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean isAttributeSpecified(int i) {
        if (this.fEventType == 1 || this.fEventType == 10) {
            return this.fScanner.getAttributeIterator().isSpecified(i);
        }
        throw new IllegalStateException(new StringBuffer().append("Current state is not among the states ").append(getEventTypeString(1)).append(" , ").append(getEventTypeString(10)).append("valid for isAttributeSpecified()").toString());
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean isCharacters() {
        if (this.fEventType == 4) {
            return true;
        }
        return DEBUG;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public int nextTag() throws XMLStreamException {
        int next = next();
        while (true) {
            if ((next != 4 || !isWhiteSpace()) && ((next != 12 || !isWhiteSpace()) && next != 6 && next != 3 && next != 5)) {
                break;
            }
            next = next();
        }
        if (next != 1 && next != 2) {
            throw new XMLStreamException2("expected start or end tag", getLocation());
        }
        return next;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public boolean standaloneSet() {
        return this.fScanner.standaloneSet();
    }

    public b convertXNIQNametoJavaxQName(QName qName) {
        if (qName == null) {
            return null;
        }
        if (qName.prefix == null) {
            return new b(qName.uri, qName.localpart);
        }
        return new b(qName.uri, qName.localpart, qName.prefix);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamReader
    public String getNamespaceURI(String str) {
        if (str == null) {
            throw new IllegalArgumentException("getNamespaceURI(String prefix) is called with a null prefix.");
        }
        return this.fScanner.getNamespaceContext().getURI(this.fSymbolTable.addSymbol(str));
    }

    protected void setPropertyManager(PropertyManager propertyManager) {
        this.fPropertyManager = propertyManager;
        this.fScanner.setProperty(Constants.STAX_PROPERTIES, propertyManager);
        this.fScanner.setPropertyManager(propertyManager);
    }

    protected PropertyManager getPropertyManager() {
        return this.fPropertyManager;
    }

    static void pr(String str) {
        System.out.println(str);
    }

    protected List getEntityDecls() {
        Hashtable declaredEntities;
        if (this.fEventType != 11 || (declaredEntities = this.fEntityManager.getEntityStore().getDeclaredEntities()) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(declaredEntities.size());
        Enumeration enumerationKeys = declaredEntities.keys();
        while (enumerationKeys.hasMoreElements()) {
            String str = (String) enumerationKeys.nextElement();
            Entity entity = (Entity) declaredEntities.get(str);
            EntityDeclarationImpl entityDeclarationImpl = new EntityDeclarationImpl();
            entityDeclarationImpl.setEntityName(str);
            if (entity.isExternal()) {
                entityDeclarationImpl.setXMLResourceIdentifier(((Entity.ExternalEntity) entity).entityLocation);
                entityDeclarationImpl.setNotationName(((Entity.ExternalEntity) entity).notation);
            } else {
                entityDeclarationImpl.setEntityReplacementText(((Entity.InternalEntity) entity).text);
            }
            arrayList.add(entityDeclarationImpl);
        }
        return arrayList;
    }

    protected List getNotationDecls() {
        DTDGrammar grammar;
        if (this.fEventType == 11 && this.fScanner.fDTDScanner != null && (grammar = ((XMLDTDScannerImpl) this.fScanner.fDTDScanner).getGrammar()) != null) {
            ArrayList arrayList = new ArrayList();
            for (XMLNotationDecl xMLNotationDecl : grammar.getNotationDecls()) {
                if (xMLNotationDecl != null) {
                    arrayList.add(new NotationDeclarationImpl(xMLNotationDecl));
                }
            }
            return arrayList;
        }
        return null;
    }
}

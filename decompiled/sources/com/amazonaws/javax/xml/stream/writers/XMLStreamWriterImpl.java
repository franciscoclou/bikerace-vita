package com.amazonaws.javax.xml.stream.writers;

import com.amazonaws.javax.xml.stream.Constants;
import com.amazonaws.javax.xml.stream.PropertyManager;
import com.amazonaws.javax.xml.stream.XMLOutputFactory;
import com.amazonaws.javax.xml.stream.XMLStreamException2;
import com.amazonaws.javax.xml.stream.XMLStreamWriter;
import com.amazonaws.javax.xml.stream.util.ReadOnlyIterator;
import com.amazonaws.javax.xml.stream.xerces.util.NamespaceSupport;
import com.amazonaws.javax.xml.stream.xerces.util.SymbolTable;
import com.amazonaws.javax.xml.stream.xerces.xni.QName;
import com.amazonaws.javax.xml.transform.b.a;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public final class XMLStreamWriterImpl extends AbstractMap implements XMLStreamWriter {
    public static final String CLOSE_EMPTY_ELEMENT = "/>";
    public static final char CLOSE_END_TAG = '>';
    public static final char CLOSE_START_TAG = '>';
    public static final String DEFAULT_ENCODING = " encoding=\"utf-8\"";
    public static final String DEFAULT_XMLDECL = "<?xml version=\"1.0\" ?>";
    public static final String DEFAULT_XML_VERSION = "1.0";
    public static final String END_CDATA = "]]>";
    public static final String END_COMMENT = "-->";
    public static final String OPEN_END_TAG = "</";
    public static final char OPEN_START_TAG = '<';
    public static final String OUTPUTSTREAM_PROPERTY = "sjsxp-outputstream";
    public static final String SPACE = " ";
    public static final String START_CDATA = "<![CDATA[";
    public static final String START_COMMENT = "<!--";
    public static final String UTF_8 = "UTF-8";
    private final String DEFAULT_PREFIX;
    HashMap fAttrNamespace;
    private ArrayList fAttributeCache;
    private ElementStack fElementStack;
    private CharsetEncoder fEncoder;
    boolean fEscapeCharacters;
    private NamespaceSupport fInternalNamespaceContext;
    private boolean fIsRepairingNamespace;
    private NamespaceContextImpl fNamespaceContext;
    private ArrayList fNamespaceDecls;
    private OutputStream fOutputStream;
    private Random fPrefixGen;
    private PropertyManager fPropertyManager;
    private final ReadOnlyIterator fReadOnlyIterator;
    private boolean fReuse;
    private boolean fStartTagOpened;
    private SymbolTable fSymbolTable;
    private Writer fWriter;

    public XMLStreamWriterImpl(OutputStream outputStream, PropertyManager propertyManager) {
        this(new OutputStreamWriter(outputStream), propertyManager);
    }

    public XMLStreamWriterImpl(OutputStream outputStream, String str, PropertyManager propertyManager) {
        this(new a(outputStream), str, propertyManager);
    }

    public XMLStreamWriterImpl(Writer writer, PropertyManager propertyManager) {
        this(new a(writer), (String) null, propertyManager);
    }

    public XMLStreamWriterImpl(a aVar, String str, PropertyManager propertyManager) {
        this.fEscapeCharacters = true;
        this.fIsRepairingNamespace = false;
        this.fOutputStream = null;
        this.fNamespaceContext = null;
        this.fInternalNamespaceContext = null;
        this.fPrefixGen = null;
        this.fPropertyManager = null;
        this.fStartTagOpened = false;
        this.fSymbolTable = new SymbolTable();
        this.fElementStack = new ElementStack();
        this.DEFAULT_PREFIX = this.fSymbolTable.addSymbol("");
        this.fReadOnlyIterator = new ReadOnlyIterator();
        this.fEncoder = null;
        this.fAttrNamespace = null;
        setOutput(aVar, str);
        this.fPropertyManager = propertyManager;
        init();
    }

    private void init() {
        this.fReuse = false;
        this.fNamespaceDecls = new ArrayList();
        this.fPrefixGen = new Random();
        this.fInternalNamespaceContext = new NamespaceSupport();
        this.fNamespaceContext = new NamespaceContextImpl();
        this.fNamespaceContext.internalContext = this.fInternalNamespaceContext;
        this.fIsRepairingNamespace = ((Boolean) this.fPropertyManager.getProperty(XMLOutputFactory.IS_REPAIRING_NAMESPACES)).booleanValue();
        if (this.fIsRepairingNamespace) {
            this.fAttributeCache = new ArrayList();
        }
        setEscapeCharacters(((Boolean) this.fPropertyManager.getProperty(Constants.ESCAPE_CHARACTERS)).booleanValue());
    }

    public void reset() {
        reset(false);
    }

    void reset(boolean z) {
        if (!this.fReuse) {
            throw new IllegalStateException("close() Must be called before calling reset()");
        }
        this.fReuse = false;
        this.fNamespaceDecls.clear();
        if (this.fIsRepairingNamespace) {
            this.fAttributeCache.clear();
        }
        this.fElementStack.clear();
        this.fInternalNamespaceContext.reset();
        this.fStartTagOpened = false;
        this.fNamespaceContext.userContext = null;
        if (z) {
            this.fIsRepairingNamespace = ((Boolean) this.fPropertyManager.getProperty(XMLOutputFactory.IS_REPAIRING_NAMESPACES)).booleanValue();
            setEscapeCharacters(((Boolean) this.fPropertyManager.getProperty(Constants.ESCAPE_CHARACTERS)).booleanValue());
        }
    }

    public void setOutput(a aVar, String str) {
        if (aVar.a() != null) {
            setOutputUsingStream(aVar.a(), str);
        } else if (aVar.b() != null) {
            setOutputUsingWriter(aVar.b());
        } else if (aVar.getSystemId() != null) {
            setOutputUsingStream(new FileOutputStream(aVar.getSystemId()), str);
        }
    }

    private void setOutputUsingWriter(Writer writer) {
        String encoding;
        this.fWriter = writer;
        if ((writer instanceof OutputStreamWriter) && (encoding = ((OutputStreamWriter) writer).getEncoding()) != null && !encoding.equalsIgnoreCase("utf-8")) {
            this.fEncoder = Charset.forName(encoding).newEncoder();
        }
    }

    private void setOutputUsingStream(OutputStream outputStream, String str) {
        this.fOutputStream = outputStream;
        if (str != null) {
            if (str.equalsIgnoreCase("utf-8")) {
                this.fWriter = new UTF8OutputStreamWriter(outputStream);
                return;
            } else {
                this.fWriter = new XMLWriter(new OutputStreamWriter(outputStream, str));
                this.fEncoder = Charset.forName(str).newEncoder();
                return;
            }
        }
        String property = System.getProperty("file.encoding");
        if (property != null && property.equalsIgnoreCase("utf-8")) {
            this.fWriter = new UTF8OutputStreamWriter(outputStream);
        } else {
            this.fWriter = new XMLWriter(new OutputStreamWriter(outputStream));
        }
    }

    public boolean canReuse() {
        return this.fReuse;
    }

    public void setEscapeCharacters(boolean z) {
        this.fEscapeCharacters = z;
    }

    public boolean getEscapeCharacters() {
        return this.fEscapeCharacters;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void close() throws XMLStreamException2 {
        if (this.fWriter != null) {
            try {
                this.fWriter.flush();
            } catch (IOException e) {
                throw new XMLStreamException2(e);
            }
        }
        this.fWriter = null;
        this.fOutputStream = null;
        this.fNamespaceDecls.clear();
        if (this.fIsRepairingNamespace) {
            this.fAttributeCache.clear();
        }
        this.fElementStack.clear();
        this.fInternalNamespaceContext.reset();
        this.fReuse = true;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void flush() throws XMLStreamException2 {
        try {
            this.fWriter.flush();
        } catch (IOException e) {
            throw new XMLStreamException2(e);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public com.amazonaws.javax.xml.a.a getNamespaceContext() {
        return this.fNamespaceContext;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public String getPrefix(String str) {
        return this.fNamespaceContext.getPrefix(str);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public Object getProperty(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        if (this.fPropertyManager.containsProperty(str)) {
            return str.equals("http://java.sun.com/xml/stream/properties/outputstream") ? this.fOutputStream : this.fPropertyManager.getProperty(str);
        }
        throw new IllegalArgumentException(new StringBuffer().append("Property '").append(str).append("' is not supported").toString());
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void setDefaultNamespace(String str) {
        if (str != null) {
            str = this.fSymbolTable.addSymbol(str);
        }
        if (this.fIsRepairingNamespace) {
            if (!isDefaultNamespace(str)) {
                QName qName = new QName();
                qName.setValues(this.DEFAULT_PREFIX, "xmlns", null, str);
                this.fNamespaceDecls.add(qName);
                return;
            }
            return;
        }
        this.fInternalNamespaceContext.declarePrefix(this.DEFAULT_PREFIX, str);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void setNamespaceContext(com.amazonaws.javax.xml.a.a aVar) {
        this.fNamespaceContext.userContext = aVar;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void setPrefix(String str, String str2) throws XMLStreamException2 {
        if (str == null) {
            throw new XMLStreamException2("Prefix cannot be null");
        }
        if (str2 == null) {
            throw new XMLStreamException2("URI cannot be null");
        }
        String strAddSymbol = this.fSymbolTable.addSymbol(str);
        String strAddSymbol2 = this.fSymbolTable.addSymbol(str2);
        if (this.fIsRepairingNamespace) {
            String uri = this.fInternalNamespaceContext.getURI(strAddSymbol);
            if ((uri == null || uri != strAddSymbol2) && !checkUserNamespaceContext(strAddSymbol, strAddSymbol2)) {
                QName qName = new QName();
                qName.setValues(strAddSymbol, "xmlns", null, strAddSymbol2);
                this.fNamespaceDecls.add(qName);
                return;
            }
            return;
        }
        this.fInternalNamespaceContext.declarePrefix(strAddSymbol, strAddSymbol2);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeAttribute(String str, String str2) throws XMLStreamException2 {
        try {
            if (!this.fStartTagOpened) {
                throw new XMLStreamException2("Attribute not associated with any element");
            }
            if (this.fIsRepairingNamespace) {
                Attribute attribute = new Attribute(str2);
                attribute.setValues(null, str, null, null);
                this.fAttributeCache.add(attribute);
            } else {
                this.fWriter.write(" ");
                this.fWriter.write(str);
                this.fWriter.write("=\"");
                writeXMLContent(str2, true, true);
                this.fWriter.write("\"");
            }
        } catch (IOException e) {
            throw new XMLStreamException2(e);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeAttribute(String str, String str2, String str3) throws XMLStreamException2 {
        try {
            if (!this.fStartTagOpened) {
                throw new XMLStreamException2("Attribute not associated with any element");
            }
            if (str == null) {
                throw new XMLStreamException2("NamespaceURI cannot be null");
            }
            String strAddSymbol = this.fSymbolTable.addSymbol(str);
            String prefix = this.fInternalNamespaceContext.getPrefix(strAddSymbol);
            if (!this.fIsRepairingNamespace) {
                if (prefix == null) {
                    throw new XMLStreamException2("Prefix cannot be null");
                }
                writeAttributeWithPrefix(prefix, str2, str3);
            } else {
                Attribute attribute = new Attribute(str3);
                attribute.setValues(null, str2, null, strAddSymbol);
                this.fAttributeCache.add(attribute);
            }
        } catch (IOException e) {
            throw new XMLStreamException2(e);
        }
    }

    private void writeAttributeWithPrefix(String str, String str2, String str3) throws IOException {
        this.fWriter.write(" ");
        if (str != null && str != "") {
            this.fWriter.write(str);
            this.fWriter.write(":");
        }
        this.fWriter.write(str2);
        this.fWriter.write("=\"");
        writeXMLContent(str3, true, true);
        this.fWriter.write("\"");
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeAttribute(String str, String str2, String str3, String str4) throws XMLStreamException2 {
        String uri;
        try {
            if (!this.fStartTagOpened) {
                throw new XMLStreamException2("Attribute not associated with any element");
            }
            if (str2 == null) {
                throw new XMLStreamException2("NamespaceURI cannot be null");
            }
            if (str3 == null) {
                throw new XMLStreamException2("Local name cannot be null");
            }
            if (!this.fIsRepairingNamespace) {
                if (str == null || str.equals("")) {
                    if (!str2.equals("")) {
                        throw new XMLStreamException2("prefix cannot be null or empty");
                    }
                    writeAttributeWithPrefix(null, str3, str4);
                    return;
                }
                if (!str.equals("xml") || !str2.equals("http://www.w3.org/XML/1998/namespace")) {
                    str = this.fSymbolTable.addSymbol(str);
                    String strAddSymbol = this.fSymbolTable.addSymbol(str2);
                    if (this.fInternalNamespaceContext.containsPrefixInCurrentContext(str) && (uri = this.fInternalNamespaceContext.getURI(str)) != null && uri != strAddSymbol) {
                        throw new XMLStreamException2(new StringBuffer().append("Prefix ").append(str).append(" is ").append("already bound to ").append(uri).append(". Trying to rebind it to ").append(strAddSymbol).append(" is an error.").toString());
                    }
                    this.fInternalNamespaceContext.declarePrefix(str, strAddSymbol);
                }
                writeAttributeWithPrefix(str, str3, str4);
                return;
            }
            if (str != null) {
                str = this.fSymbolTable.addSymbol(str);
            }
            String strAddSymbol2 = this.fSymbolTable.addSymbol(str2);
            Attribute attribute = new Attribute(str4);
            attribute.setValues(str, str3, null, strAddSymbol2);
            this.fAttributeCache.add(attribute);
        } catch (IOException e) {
            throw new XMLStreamException2(e);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeCData(String str) throws XMLStreamException2 {
        try {
            if (str == null) {
                throw new XMLStreamException2("cdata cannot be null");
            }
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            this.fWriter.write(START_CDATA);
            this.fWriter.write(str);
            this.fWriter.write(END_CDATA);
        } catch (IOException e) {
            throw new XMLStreamException2(e);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeCharacters(String str) throws XMLStreamException2 {
        try {
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            writeXMLContent(str);
        } catch (IOException e) {
            throw new XMLStreamException2(e);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeCharacters(char[] cArr, int i, int i2) throws XMLStreamException2 {
        try {
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            writeXMLContent(cArr, i, i2, this.fEscapeCharacters);
        } catch (IOException e) {
            throw new XMLStreamException2(e);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeComment(String str) throws XMLStreamException2 {
        try {
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            this.fWriter.write(START_COMMENT);
            if (str != null) {
                this.fWriter.write(str);
            }
            this.fWriter.write(END_COMMENT);
        } catch (IOException e) {
            throw new XMLStreamException2(e);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeDTD(String str) throws XMLStreamException2 {
        try {
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            this.fWriter.write(str);
        } catch (IOException e) {
            throw new XMLStreamException2(e);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeDefaultNamespace(String str) throws XMLStreamException2 {
        String uri;
        if (str == null) {
            str = "";
        }
        try {
            if (!this.fStartTagOpened) {
                throw new IllegalStateException("Namespace Attribute not associated with any element");
            }
            if (this.fIsRepairingNamespace) {
                QName qName = new QName();
                qName.setValues("", "xmlns", null, str);
                this.fNamespaceDecls.add(qName);
                return;
            }
            String strAddSymbol = this.fSymbolTable.addSymbol(str);
            if (this.fInternalNamespaceContext.containsPrefixInCurrentContext("") && (uri = this.fInternalNamespaceContext.getURI("")) != null && uri != strAddSymbol) {
                throw new XMLStreamException2(new StringBuffer().append("xmlns has been already bound to ").append(uri).append(". Rebinding it to ").append(strAddSymbol).append(" is an error").toString());
            }
            this.fInternalNamespaceContext.declarePrefix("", strAddSymbol);
            writenamespace(null, strAddSymbol);
        } catch (IOException e) {
            throw new XMLStreamException2(e);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeEmptyElement(String str) throws XMLStreamException2 {
        try {
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            openStartTag();
            this.fElementStack.push(null, str, null, null, true);
            this.fInternalNamespaceContext.pushContext();
            if (!this.fIsRepairingNamespace) {
                this.fWriter.write(str);
            }
        } catch (IOException e) {
            throw new XMLStreamException2(e);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeEmptyElement(String str, String str2) throws XMLStreamException2 {
        if (str == null) {
            throw new XMLStreamException2("NamespaceURI cannot be null");
        }
        String strAddSymbol = this.fSymbolTable.addSymbol(str);
        writeEmptyElement(this.fNamespaceContext.getPrefix(strAddSymbol), str2, strAddSymbol);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeEmptyElement(String str, String str2, String str3) throws XMLStreamException2 {
        try {
            if (str2 == null) {
                throw new XMLStreamException2("Local Name cannot be null");
            }
            if (str3 == null) {
                throw new XMLStreamException2("NamespaceURI cannot be null");
            }
            String strAddSymbol = str != null ? this.fSymbolTable.addSymbol(str) : str;
            String strAddSymbol2 = this.fSymbolTable.addSymbol(str3);
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            openStartTag();
            this.fElementStack.push(strAddSymbol, str2, null, strAddSymbol2, true);
            this.fInternalNamespaceContext.pushContext();
            if (!this.fIsRepairingNamespace) {
                if (strAddSymbol == null) {
                    throw new XMLStreamException2(new StringBuffer().append("NamespaceURI ").append(strAddSymbol2).append(" has not been bound to any prefix").toString());
                }
                if (strAddSymbol != null && strAddSymbol != "") {
                    this.fWriter.write(strAddSymbol);
                    this.fWriter.write(":");
                }
                this.fWriter.write(str2);
            }
        } catch (IOException e) {
            throw new XMLStreamException2(e);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeEndDocument() throws XMLStreamException2 {
        try {
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            while (!this.fElementStack.empty()) {
                ElementState elementStatePop = this.fElementStack.pop();
                this.fInternalNamespaceContext.popContext();
                if (!elementStatePop.isEmpty) {
                    this.fWriter.write(OPEN_END_TAG);
                    if (elementStatePop.prefix != null && !elementStatePop.prefix.equals("")) {
                        this.fWriter.write(elementStatePop.prefix);
                        this.fWriter.write(":");
                    }
                    this.fWriter.write(elementStatePop.localpart);
                    this.fWriter.write(62);
                }
            }
        } catch (IOException e) {
            throw new XMLStreamException2(e);
        } catch (ArrayIndexOutOfBoundsException e2) {
            throw new XMLStreamException2("No more elements to write");
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeEndElement() throws XMLStreamException2 {
        try {
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            ElementState elementStatePop = this.fElementStack.pop();
            if (elementStatePop == null) {
                throw new XMLStreamException2("No element was found to write");
            }
            if (!elementStatePop.isEmpty) {
                this.fWriter.write(OPEN_END_TAG);
                if (elementStatePop.prefix != null && !elementStatePop.prefix.equals("")) {
                    this.fWriter.write(elementStatePop.prefix);
                    this.fWriter.write(":");
                }
                this.fWriter.write(elementStatePop.localpart);
                this.fWriter.write(62);
                this.fInternalNamespaceContext.popContext();
            }
        } catch (IOException e) {
            throw new XMLStreamException2(e);
        } catch (ArrayIndexOutOfBoundsException e2) {
            throw new XMLStreamException2(new StringBuffer().append("No element was found to write: ").append(e2.toString()).toString(), e2);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeEntityRef(String str) throws XMLStreamException2 {
        try {
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            this.fWriter.write(38);
            this.fWriter.write(str);
            this.fWriter.write(59);
        } catch (IOException e) {
            throw new XMLStreamException2(e);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeNamespace(String str, String str2) throws XMLStreamException2 {
        String uri;
        if (str2 == null) {
            str2 = "";
        }
        try {
            if (!this.fStartTagOpened) {
                throw new IllegalStateException(new StringBuffer().append("Invalid state: start tag is not opened at writeNamespace(").append(str).append(", ").append(str2).append(")").toString());
            }
            if (str == null || str.equals("") || str.equals("xmlns")) {
                writeDefaultNamespace(str2);
                return;
            }
            if (!str.equals("xml") || !str2.equals("http://www.w3.org/XML/1998/namespace")) {
                String strAddSymbol = this.fSymbolTable.addSymbol(str);
                String strAddSymbol2 = this.fSymbolTable.addSymbol(str2);
                if (this.fIsRepairingNamespace) {
                    String uri2 = this.fInternalNamespaceContext.getURI(strAddSymbol);
                    if (uri2 == null || uri2 != strAddSymbol2) {
                        QName qName = new QName();
                        qName.setValues(strAddSymbol, "xmlns", null, strAddSymbol2);
                        this.fNamespaceDecls.add(qName);
                        return;
                    }
                    return;
                }
                if (this.fInternalNamespaceContext.containsPrefixInCurrentContext(strAddSymbol) && (uri = this.fInternalNamespaceContext.getURI(strAddSymbol)) != null && uri != strAddSymbol2) {
                    throw new XMLStreamException2(new StringBuffer().append("prefix ").append(strAddSymbol).append(" has been already bound to ").append(uri).append(". Rebinding it to ").append(strAddSymbol2).append(" is an error").toString());
                }
                this.fInternalNamespaceContext.declarePrefix(strAddSymbol, strAddSymbol2);
                writenamespace(strAddSymbol, strAddSymbol2);
            }
        } catch (IOException e) {
            throw new XMLStreamException2(e);
        }
    }

    private void writenamespace(String str, String str2) throws IOException {
        this.fWriter.write(" xmlns");
        if (str != null && str != "") {
            this.fWriter.write(":");
            this.fWriter.write(str);
        }
        this.fWriter.write("=\"");
        writeXMLContent(str2, true, true);
        this.fWriter.write("\"");
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeProcessingInstruction(String str) throws XMLStreamException2 {
        try {
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            if (str != null) {
                this.fWriter.write("<?");
                this.fWriter.write(str);
                this.fWriter.write("?>");
                return;
            }
            throw new XMLStreamException2("PI target cannot be null");
        } catch (IOException e) {
            throw new XMLStreamException2(e);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeProcessingInstruction(String str, String str2) throws XMLStreamException2 {
        try {
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            if (str == null || str2 == null) {
                throw new XMLStreamException2("PI target cannot be null");
            }
            this.fWriter.write("<?");
            this.fWriter.write(str);
            this.fWriter.write(" ");
            this.fWriter.write(str2);
            this.fWriter.write("?>");
        } catch (IOException e) {
            throw new XMLStreamException2(e);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeStartDocument() throws XMLStreamException2 {
        try {
            this.fWriter.write(DEFAULT_XMLDECL);
        } catch (IOException e) {
            throw new XMLStreamException2(e);
        }
    }

    /* JADX WARN: Code duplicated, block: B:5:0x000a A[Catch: IOException -> 0x0029, TryCatch #0 {IOException -> 0x0029, blocks: (B:3:0x0002, B:7:0x000e, B:5:0x000a), top: B:13:0x0002 }] */
    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeStartDocument(String str) throws XMLStreamException2 {
        if (str == null) {
            writeStartDocument();
        } else {
            try {
                if (str.equals("")) {
                    writeStartDocument();
                } else {
                    this.fWriter.write("<?xml version=\"");
                    this.fWriter.write(str);
                    this.fWriter.write("\"");
                    this.fWriter.write("?>");
                }
            } catch (IOException e) {
                throw new XMLStreamException2(e);
            }
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeStartDocument(String str, String str2) throws XMLStreamException2 {
        String encoding;
        try {
            if (str == null && str2 == null) {
                writeStartDocument();
                return;
            }
            if (str == null) {
                writeStartDocument(str2);
                return;
            }
            if (this.fWriter instanceof OutputStreamWriter) {
                encoding = ((OutputStreamWriter) this.fWriter).getEncoding();
            } else if (this.fWriter instanceof UTF8OutputStreamWriter) {
                encoding = ((UTF8OutputStreamWriter) this.fWriter).getEncoding();
            } else if (!(this.fWriter instanceof XMLWriter)) {
                encoding = null;
            } else {
                encoding = ((OutputStreamWriter) ((XMLWriter) this.fWriter).getWriter()).getEncoding();
            }
            if (encoding != null && !encoding.equalsIgnoreCase(str)) {
                Iterator<String> it = Charset.forName(str).aliases().iterator();
                boolean z = false;
                while (!z && it.hasNext()) {
                    if (encoding.equalsIgnoreCase(it.next())) {
                        z = true;
                    }
                }
                if (!z) {
                    throw new XMLStreamException2(new StringBuffer().append("Underlying stream encoding '").append(encoding).append("' and input paramter for writeStartDocument() method '").append(str).append("' do not match.").toString());
                }
            }
            this.fWriter.write("<?xml version=\"");
            if (str2 == null || str2.equals("")) {
                this.fWriter.write(DEFAULT_XML_VERSION);
            } else {
                this.fWriter.write(str2);
            }
            if (!str.equals("")) {
                this.fWriter.write("\" encoding=\"");
                this.fWriter.write(str);
            }
            this.fWriter.write("\"?>");
        } catch (IOException e) {
            throw new XMLStreamException2(e);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeStartElement(String str) throws XMLStreamException2 {
        try {
            if (str == null) {
                throw new XMLStreamException2("Local Name cannot be null");
            }
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            openStartTag();
            this.fElementStack.push(null, str, null, null, false);
            this.fInternalNamespaceContext.pushContext();
            if (!this.fIsRepairingNamespace) {
                this.fWriter.write(str);
            }
        } catch (IOException e) {
            throw new XMLStreamException2(e);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeStartElement(String str, String str2) throws XMLStreamException2 {
        if (str2 == null) {
            throw new XMLStreamException2("Local Name cannot be null");
        }
        if (str == null) {
            throw new XMLStreamException2("NamespaceURI cannot be null");
        }
        String strAddSymbol = this.fSymbolTable.addSymbol(str);
        String prefix = null;
        if (!this.fIsRepairingNamespace && (prefix = this.fNamespaceContext.getPrefix(strAddSymbol)) != null) {
            prefix = this.fSymbolTable.addSymbol(prefix);
        }
        writeStartElement(prefix, str2, strAddSymbol);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeStartElement(String str, String str2, String str3) throws XMLStreamException2 {
        try {
            if (str2 == null) {
                throw new XMLStreamException2("Local Name cannot be null");
            }
            if (str3 == null) {
                throw new XMLStreamException2("NamespaceURI cannot be null");
            }
            if (!this.fIsRepairingNamespace && str == null) {
                throw new XMLStreamException2("Prefix cannot be null");
            }
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            openStartTag();
            String strAddSymbol = this.fSymbolTable.addSymbol(str3);
            String strAddSymbol2 = str != null ? this.fSymbolTable.addSymbol(str) : str;
            this.fElementStack.push(strAddSymbol2, str2, null, strAddSymbol, false);
            this.fInternalNamespaceContext.pushContext();
            String prefix = this.fNamespaceContext.getPrefix(strAddSymbol);
            if (strAddSymbol2 != null && (prefix == null || !strAddSymbol2.equals(prefix))) {
                this.fInternalNamespaceContext.declarePrefix(strAddSymbol2, strAddSymbol);
            }
            if (this.fIsRepairingNamespace) {
                if (strAddSymbol2 != null) {
                    if (prefix == null || !strAddSymbol2.equals(prefix)) {
                        QName qName = new QName();
                        qName.setValues(strAddSymbol2, "xmlns", null, strAddSymbol);
                        this.fNamespaceDecls.add(qName);
                        return;
                    }
                    return;
                }
                return;
            }
            if (strAddSymbol2 != null && strAddSymbol2 != "") {
                this.fWriter.write(strAddSymbol2);
                this.fWriter.write(":");
            }
            this.fWriter.write(str2);
        } catch (IOException e) {
            throw new XMLStreamException2(e);
        }
    }

    private void writeXMLContent(char[] cArr, int i, int i2, boolean z) throws IOException {
        if (!z) {
            this.fWriter.write(cArr, i, i2);
            return;
        }
        int i3 = i + i2;
        int i4 = i;
        while (i < i3) {
            char c = cArr[i];
            if (this.fEncoder != null && !this.fEncoder.canEncode(c)) {
                this.fWriter.write(cArr, i4, i - i4);
                this.fWriter.write("&#x");
                this.fWriter.write(Integer.toHexString(c));
                this.fWriter.write(59);
                i4 = i + 1;
            } else {
                switch (c) {
                    case '&':
                        this.fWriter.write(cArr, i4, i - i4);
                        this.fWriter.write("&amp;");
                        i4 = i + 1;
                        break;
                    case '<':
                        this.fWriter.write(cArr, i4, i - i4);
                        this.fWriter.write("&lt;");
                        i4 = i + 1;
                        break;
                    case '>':
                        this.fWriter.write(cArr, i4, i - i4);
                        this.fWriter.write("&gt;");
                        i4 = i + 1;
                        break;
                }
            }
            i++;
        }
        this.fWriter.write(cArr, i4, i3 - i4);
    }

    private void writeXMLContent(String str) throws IOException {
        if (str != null && str.length() > 0) {
            writeXMLContent(str, this.fEscapeCharacters, false);
        }
    }

    private void writeXMLContent(String str, boolean z, boolean z2) throws IOException {
        int i = 0;
        if (!z) {
            this.fWriter.write(str);
            return;
        }
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (this.fEncoder != null && !this.fEncoder.canEncode(cCharAt)) {
                this.fWriter.write(str, i, i2 - i);
                this.fWriter.write("&#x");
                this.fWriter.write(Integer.toHexString(cCharAt));
                this.fWriter.write(59);
                i = i2 + 1;
            } else {
                switch (cCharAt) {
                    case '\"':
                        this.fWriter.write(str, i, i2 - i);
                        if (z2) {
                            this.fWriter.write("&quot;");
                        } else {
                            this.fWriter.write(34);
                        }
                        i = i2 + 1;
                        break;
                    case '&':
                        this.fWriter.write(str, i, i2 - i);
                        this.fWriter.write("&amp;");
                        i = i2 + 1;
                        break;
                    case '<':
                        this.fWriter.write(str, i, i2 - i);
                        this.fWriter.write("&lt;");
                        i = i2 + 1;
                        break;
                    case '>':
                        this.fWriter.write(str, i, i2 - i);
                        this.fWriter.write("&gt;");
                        i = i2 + 1;
                        break;
                }
            }
        }
        this.fWriter.write(str, i, length - i);
    }

    private void closeStartTag() throws XMLStreamException2 {
        String prefix;
        try {
            ElementState elementStatePeek = this.fElementStack.peek();
            if (this.fIsRepairingNamespace) {
                repair();
                correctPrefix(elementStatePeek, 1);
                if (elementStatePeek.prefix != null && elementStatePeek.prefix != "") {
                    this.fWriter.write(elementStatePeek.prefix);
                    this.fWriter.write(":");
                }
                this.fWriter.write(elementStatePeek.localpart);
                int size = this.fNamespaceDecls.size();
                for (int i = 0; i < size; i++) {
                    QName qName = (QName) this.fNamespaceDecls.get(i);
                    if (qName != null && this.fInternalNamespaceContext.declarePrefix(qName.prefix, qName.uri)) {
                        writenamespace(qName.prefix, qName.uri);
                    }
                }
                this.fNamespaceDecls.clear();
                for (int i2 = 0; i2 < this.fAttributeCache.size(); i2++) {
                    Attribute attribute = (Attribute) this.fAttributeCache.get(i2);
                    if (attribute.prefix != null && attribute.uri != null && !attribute.prefix.equals("") && !attribute.uri.equals("") && ((prefix = this.fInternalNamespaceContext.getPrefix(attribute.uri)) == null || prefix != attribute.prefix)) {
                        if (getAttrPrefix(attribute.uri) == null) {
                            if (this.fInternalNamespaceContext.declarePrefix(attribute.prefix, attribute.uri)) {
                                writenamespace(attribute.prefix, attribute.uri);
                            }
                        } else {
                            writenamespace(attribute.prefix, attribute.uri);
                        }
                    }
                    writeAttributeWithPrefix(attribute.prefix, attribute.localpart, attribute.value);
                }
                this.fAttrNamespace = null;
                this.fAttributeCache.clear();
            }
            if (elementStatePeek.isEmpty) {
                this.fElementStack.pop();
                this.fInternalNamespaceContext.popContext();
                this.fWriter.write(CLOSE_EMPTY_ELEMENT);
            } else {
                this.fWriter.write(62);
            }
            this.fStartTagOpened = false;
        } catch (IOException e) {
            this.fStartTagOpened = false;
            throw new XMLStreamException2(e);
        }
    }

    private void openStartTag() throws IOException {
        this.fStartTagOpened = true;
        this.fWriter.write(60);
    }

    /* JADX WARN: Code duplicated, block: B:38:0x00a7  */
    private void correctPrefix(QName qName, int i) {
        boolean z;
        String attrPrefix;
        String strAddSymbol = qName.prefix;
        String str = qName.uri;
        if (strAddSymbol == null || strAddSymbol.equals("")) {
            if (str != null) {
                if (strAddSymbol != "" || str != "") {
                    String strAddSymbol2 = this.fSymbolTable.addSymbol(str);
                    for (int i2 = 0; i2 < this.fNamespaceDecls.size(); i2++) {
                        QName qName2 = (QName) this.fNamespaceDecls.get(i2);
                        if (qName2 != null && qName2.uri == qName.uri) {
                            qName.prefix = qName2.prefix;
                            return;
                        }
                    }
                    String prefix = this.fNamespaceContext.getPrefix(strAddSymbol2);
                    if (prefix != "") {
                        z = false;
                        attrPrefix = prefix;
                    } else if (i != 1) {
                        if (i == 10) {
                            z = true;
                            attrPrefix = getAttrPrefix(strAddSymbol2);
                        } else {
                            z = false;
                            attrPrefix = prefix;
                        }
                    } else {
                        return;
                    }
                    if (attrPrefix == null) {
                        StringBuffer stringBuffer = new StringBuffer("zdef");
                        for (int i3 = 0; i3 < 1; i3++) {
                            stringBuffer.append(this.fPrefixGen.nextInt());
                        }
                        strAddSymbol = this.fSymbolTable.addSymbol(stringBuffer.toString());
                    } else {
                        strAddSymbol = this.fSymbolTable.addSymbol(attrPrefix);
                    }
                    if (attrPrefix == null) {
                        if (z) {
                            addAttrNamespace(strAddSymbol, strAddSymbol2);
                        } else {
                            QName qName3 = new QName();
                            qName3.setValues(strAddSymbol, "xmlns", null, strAddSymbol2);
                            this.fNamespaceDecls.add(qName3);
                            this.fInternalNamespaceContext.declarePrefix(this.fSymbolTable.addSymbol(strAddSymbol), strAddSymbol2);
                        }
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
        qName.prefix = strAddSymbol;
    }

    private String getAttrPrefix(String str) {
        if (this.fAttrNamespace != null) {
            return (String) this.fAttrNamespace.get(str);
        }
        return null;
    }

    private void addAttrNamespace(String str, String str2) {
        if (this.fAttrNamespace == null) {
            this.fAttrNamespace = new HashMap();
        }
        this.fAttrNamespace.put(str, str2);
    }

    private boolean isDefaultNamespace(String str) {
        return str == this.fInternalNamespaceContext.getURI(this.DEFAULT_PREFIX);
    }

    private boolean checkUserNamespaceContext(String str, String str2) {
        String namespaceURI;
        return (this.fNamespaceContext.userContext == null || (namespaceURI = this.fNamespaceContext.userContext.getNamespaceURI(str)) == null || !namespaceURI.equals(str2)) ? false : true;
    }

    protected void repair() {
        ElementState elementStatePeek = this.fElementStack.peek();
        removeDuplicateDecls();
        for (int i = 0; i < this.fAttributeCache.size(); i++) {
            Attribute attribute = (Attribute) this.fAttributeCache.get(i);
            if ((attribute.prefix != null && !attribute.prefix.equals("")) || (attribute.uri != null && !attribute.uri.equals(""))) {
                correctPrefix(elementStatePeek, attribute);
            }
        }
        if (!isDeclared(elementStatePeek) && elementStatePeek.prefix != null && elementStatePeek.uri != null && !elementStatePeek.prefix.equals("") && !elementStatePeek.uri.equals("")) {
            this.fNamespaceDecls.add(elementStatePeek);
        }
        for (int i2 = 0; i2 < this.fAttributeCache.size(); i2++) {
            Attribute attribute2 = (Attribute) this.fAttributeCache.get(i2);
            int i3 = i2 + 1;
            while (true) {
                int i4 = i3;
                if (i4 < this.fAttributeCache.size()) {
                    Attribute attribute3 = (Attribute) this.fAttributeCache.get(i4);
                    if (!"".equals(attribute2.prefix) && !"".equals(attribute3.prefix)) {
                        correctPrefix(attribute2, attribute3);
                    }
                    i3 = i4 + 1;
                }
            }
        }
        repairNamespaceDecl(elementStatePeek);
        for (int i5 = 0; i5 < this.fAttributeCache.size(); i5++) {
            Attribute attribute4 = (Attribute) this.fAttributeCache.get(i5);
            if (attribute4.prefix != null && attribute4.prefix.equals("") && attribute4.uri != null && attribute4.uri.equals("")) {
                repairNamespaceDecl(attribute4);
            }
        }
        for (int i6 = 0; i6 < this.fNamespaceDecls.size(); i6++) {
            QName qName = (QName) this.fNamespaceDecls.get(i6);
            if (qName != null) {
                this.fInternalNamespaceContext.declarePrefix(qName.prefix, qName.uri);
            }
        }
        for (int i7 = 0; i7 < this.fAttributeCache.size(); i7++) {
            correctPrefix((Attribute) this.fAttributeCache.get(i7), 10);
        }
    }

    void correctPrefix(QName qName, QName qName2) {
        checkForNull(qName);
        checkForNull(qName2);
        if (qName.prefix.equals(qName2.prefix) && !qName.uri.equals(qName2.uri)) {
            String prefix = this.fNamespaceContext.getPrefix(qName2.uri);
            if (prefix != null) {
                qName2.prefix = this.fSymbolTable.addSymbol(prefix);
                return;
            }
            for (int i = 0; i < this.fNamespaceDecls.size(); i++) {
                QName qName3 = (QName) this.fNamespaceDecls.get(i);
                if (qName3 != null && qName3.uri == qName2.uri) {
                    qName2.prefix = qName3.prefix;
                    return;
                }
            }
            StringBuffer stringBuffer = new StringBuffer("zdef");
            for (int i2 = 0; i2 < 1; i2++) {
                stringBuffer.append(this.fPrefixGen.nextInt());
            }
            String strAddSymbol = this.fSymbolTable.addSymbol(stringBuffer.toString());
            qName2.prefix = strAddSymbol;
            QName qName4 = new QName();
            qName4.setValues(strAddSymbol, "xmlns", null, qName2.uri);
            this.fNamespaceDecls.add(qName4);
        }
    }

    void checkForNull(QName qName) {
        if (qName.prefix == null) {
            qName.prefix = "";
        }
        if (qName.uri == null) {
            qName.uri = "";
        }
    }

    void removeDuplicateDecls() {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.fNamespaceDecls.size()) {
                QName qName = (QName) this.fNamespaceDecls.get(i2);
                if (qName != null) {
                    int i3 = i2 + 1;
                    while (true) {
                        int i4 = i3;
                        if (i4 < this.fNamespaceDecls.size()) {
                            QName qName2 = (QName) this.fNamespaceDecls.get(i4);
                            if (qName2 != null && qName.prefix.equals(qName2.prefix) && qName.uri.equals(qName2.uri)) {
                                this.fNamespaceDecls.remove(i4);
                            }
                            i3 = i4 + 1;
                        }
                    }
                }
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    void repairNamespaceDecl(QName qName) {
        String namespaceURI;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.fNamespaceDecls.size()) {
                QName qName2 = (QName) this.fNamespaceDecls.get(i2);
                if (qName2 != null && qName.prefix != null && qName.prefix.equals(qName2.prefix) && !qName.uri.equals(qName2.uri) && (namespaceURI = this.fNamespaceContext.getNamespaceURI(qName.prefix)) != null) {
                    if (namespaceURI.equals(qName.uri)) {
                        this.fNamespaceDecls.set(i2, null);
                    } else {
                        qName2.uri = qName.uri;
                    }
                }
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    boolean isDeclared(QName qName) {
        for (int i = 0; i < this.fNamespaceDecls.size(); i++) {
            QName qName2 = (QName) this.fNamespaceDecls.get(i);
            if (qName.prefix != null && qName.prefix == qName2.prefix && qName2.uri == qName.uri) {
                return true;
            }
        }
        return (qName.uri == null || this.fNamespaceContext.getPrefix(qName.uri) == null) ? false : true;
    }

    public class ElementStack {
        protected short fDepth;
        protected ElementState[] fElements = new ElementState[10];

        public ElementStack() {
            for (int i = 0; i < this.fElements.length; i++) {
                this.fElements[i] = XMLStreamWriterImpl.this.new ElementState();
            }
        }

        public ElementState push(ElementState elementState) {
            if (this.fDepth == this.fElements.length) {
                ElementState[] elementStateArr = new ElementState[this.fElements.length * 2];
                System.arraycopy(this.fElements, 0, elementStateArr, 0, this.fDepth);
                this.fElements = elementStateArr;
                for (int i = this.fDepth; i < this.fElements.length; i++) {
                    this.fElements[i] = XMLStreamWriterImpl.this.new ElementState();
                }
            }
            this.fElements[this.fDepth].setValues(elementState);
            ElementState[] elementStateArr2 = this.fElements;
            short s = this.fDepth;
            this.fDepth = (short) (s + 1);
            return elementStateArr2[s];
        }

        public ElementState push(String str, String str2, String str3, String str4, boolean z) {
            if (this.fDepth == this.fElements.length) {
                ElementState[] elementStateArr = new ElementState[this.fElements.length * 2];
                System.arraycopy(this.fElements, 0, elementStateArr, 0, this.fDepth);
                this.fElements = elementStateArr;
                for (int i = this.fDepth; i < this.fElements.length; i++) {
                    this.fElements[i] = XMLStreamWriterImpl.this.new ElementState();
                }
            }
            this.fElements[this.fDepth].setValues(str, str2, str3, str4, z);
            ElementState[] elementStateArr2 = this.fElements;
            short s = this.fDepth;
            this.fDepth = (short) (s + 1);
            return elementStateArr2[s];
        }

        public ElementState pop() {
            ElementState[] elementStateArr = this.fElements;
            short s = (short) (this.fDepth - 1);
            this.fDepth = s;
            return elementStateArr[s];
        }

        public void clear() {
            this.fDepth = (short) 0;
        }

        public ElementState peek() {
            return this.fElements[this.fDepth - 1];
        }

        public boolean empty() {
            return this.fDepth <= 0;
        }
    }

    class ElementState extends QName {
        public boolean isEmpty;

        public ElementState() {
            this.isEmpty = false;
        }

        public ElementState(String str, String str2, String str3, String str4) {
            super(str, str2, str3, str4);
            this.isEmpty = false;
        }

        public void setValues(String str, String str2, String str3, String str4, boolean z) {
            super.setValues(str, str2, str3, str4);
            this.isEmpty = z;
        }
    }

    class Attribute extends QName {
        String value;

        Attribute(String str) {
            this.value = str;
        }
    }

    class NamespaceContextImpl implements com.amazonaws.javax.xml.a.a {
        com.amazonaws.javax.xml.a.a userContext = null;
        NamespaceSupport internalContext = null;

        NamespaceContextImpl() {
        }

        @Override // com.amazonaws.javax.xml.a.a
        public String getNamespaceURI(String str) {
            String uri;
            if (str != null) {
                str = XMLStreamWriterImpl.this.fSymbolTable.addSymbol(str);
            }
            if (this.internalContext == null || (uri = this.internalContext.getURI(str)) == null) {
                if (this.userContext != null) {
                    return this.userContext.getNamespaceURI(str);
                }
                return null;
            }
            return uri;
        }

        @Override // com.amazonaws.javax.xml.a.a
        public String getPrefix(String str) {
            String prefix;
            if (str != null) {
                str = XMLStreamWriterImpl.this.fSymbolTable.addSymbol(str);
            }
            if (this.internalContext == null || (prefix = this.internalContext.getPrefix(str)) == null) {
                if (this.userContext != null) {
                    return this.userContext.getPrefix(str);
                }
                return null;
            }
            return prefix;
        }

        @Override // com.amazonaws.javax.xml.a.a
        public Iterator getPrefixes(String str) {
            if (str != null) {
                str = XMLStreamWriterImpl.this.fSymbolTable.addSymbol(str);
            }
            Iterator prefixes = this.userContext != null ? this.userContext.getPrefixes(str) : null;
            Vector prefixes2 = this.internalContext != null ? this.internalContext.getPrefixes(str) : null;
            if (prefixes2 != null || prefixes == null) {
                if (prefixes2 != null && prefixes == null) {
                    return new ReadOnlyIterator(prefixes2.iterator());
                }
                if (prefixes2 == null || prefixes == null) {
                    return XMLStreamWriterImpl.this.fReadOnlyIterator;
                }
                while (prefixes.hasNext()) {
                    String strAddSymbol = (String) prefixes.next();
                    if (strAddSymbol != null) {
                        strAddSymbol = XMLStreamWriterImpl.this.fSymbolTable.addSymbol(strAddSymbol);
                    }
                    if (!prefixes2.contains(strAddSymbol)) {
                        prefixes2.add(strAddSymbol);
                    }
                }
                return new ReadOnlyIterator(prefixes2.iterator());
            }
            return prefixes;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return obj.equals(OUTPUTSTREAM_PROPERTY);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object get(Object obj) {
        if (obj.equals(OUTPUTSTREAM_PROPERTY)) {
            return this.fOutputStream;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set entrySet() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractMap
    public String toString() {
        return new StringBuffer().append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).toString();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        return this.fElementStack.hashCode();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        return this == obj;
    }
}

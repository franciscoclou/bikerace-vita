package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.amazonaws.javax.xml.stream.xerces.impl.io.ASCIIReader;
import com.amazonaws.javax.xml.stream.xerces.impl.io.UCSReader;
import com.amazonaws.javax.xml.stream.xerces.impl.io.UTF8Reader;
import com.amazonaws.javax.xml.stream.xerces.impl.msg.XMLMessageFormatter;
import com.amazonaws.javax.xml.stream.xerces.util.EncodingMap;
import com.amazonaws.javax.xml.stream.xerces.util.SymbolTable;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import com.amazonaws.javax.xml.stream.xerces.util.XMLStringBuffer;
import com.amazonaws.javax.xml.stream.xerces.xni.QName;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLString;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponentManager;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Locale;
import java.util.Vector;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class XMLEntityReaderImpl extends XMLEntityReader {
    protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
    private static final boolean DEBUG_BUFFER = false;
    private static final boolean DEBUG_ENCODINGS = false;
    private static final boolean DEBUG_SKIP_STRING = false;
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    public static final boolean[] validContent = new boolean[127];
    public static final boolean[] validNames = new boolean[127];
    protected boolean fAllowJavaEncodings;
    protected Entity.ScannedEntity fCurrentEntity;
    protected XMLEntityManager fEntityManager;
    protected XMLErrorReporter fErrorReporter;
    protected PropertyManager fPropertyManager;
    protected SymbolTable fSymbolTable;
    boolean isExternal;
    private Vector listeners;
    char[] scannedName;
    boolean whiteSpaceInfoNeeded;
    int whiteSpaceLen;
    int[] whiteSpaceLookup;

    static {
        for (char c = ' '; c < 127; c = (char) (c + 1)) {
            validContent[c] = true;
        }
        validContent[9] = true;
        validContent[38] = false;
        validContent[60] = false;
        validContent[93] = false;
        for (int i = 65; i <= 90; i++) {
            validNames[i] = true;
        }
        for (int i2 = 97; i2 <= 122; i2++) {
            validNames[i2] = true;
        }
        for (int i3 = 48; i3 <= 57; i3++) {
            validNames[i3] = true;
        }
        validNames[45] = true;
        validNames[46] = true;
        validNames[58] = true;
        validNames[95] = true;
    }

    public XMLEntityReaderImpl(XMLEntityManager xMLEntityManager) {
        this.fCurrentEntity = null;
        this.listeners = new Vector();
        this.fSymbolTable = null;
        this.fErrorReporter = null;
        this.whiteSpaceLookup = new int[100];
        this.whiteSpaceLen = 0;
        this.whiteSpaceInfoNeeded = true;
        this.scannedName = null;
        this.fPropertyManager = null;
        this.isExternal = false;
        this.fEntityManager = xMLEntityManager;
    }

    public XMLEntityReaderImpl(PropertyManager propertyManager, XMLEntityManager xMLEntityManager) {
        this.fCurrentEntity = null;
        this.listeners = new Vector();
        this.fSymbolTable = null;
        this.fErrorReporter = null;
        this.whiteSpaceLookup = new int[100];
        this.whiteSpaceLen = 0;
        this.whiteSpaceInfoNeeded = true;
        this.scannedName = null;
        this.fPropertyManager = null;
        this.isExternal = false;
        this.fEntityManager = xMLEntityManager;
        reset(propertyManager);
    }

    public void reset(PropertyManager propertyManager) {
        this.fSymbolTable = (SymbolTable) propertyManager.getProperty(SYMBOL_TABLE);
        this.fErrorReporter = (XMLErrorReporter) propertyManager.getProperty(ERROR_REPORTER);
        this.fCurrentEntity = null;
        this.whiteSpaceLen = 0;
        this.whiteSpaceInfoNeeded = true;
        this.scannedName = null;
        this.listeners.clear();
    }

    public void reset(XMLComponentManager xMLComponentManager) {
        try {
            this.fAllowJavaEncodings = xMLComponentManager.getFeature(ALLOW_JAVA_ENCODINGS);
        } catch (XMLConfigurationException e) {
            this.fAllowJavaEncodings = false;
        }
        this.fSymbolTable = (SymbolTable) xMLComponentManager.getProperty(SYMBOL_TABLE);
        this.fErrorReporter = (XMLErrorReporter) xMLComponentManager.getProperty(ERROR_REPORTER);
    }

    public void setCurrentEntity(Entity.ScannedEntity scannedEntity) {
        this.fCurrentEntity = scannedEntity;
        if (this.fCurrentEntity != null) {
            this.isExternal = this.fCurrentEntity.isExternal();
        }
    }

    public Entity.ScannedEntity getCurrentEntity() {
        return this.fCurrentEntity;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLResourceIdentifier
    public String getBaseSystemId() {
        if (this.fCurrentEntity == null || this.fCurrentEntity.entityLocation == null) {
            return null;
        }
        return this.fCurrentEntity.entityLocation.getExpandedSystemId();
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLLocator
    public int getLineNumber() {
        if (this.fCurrentEntity != null) {
            return this.fCurrentEntity.lineNumber;
        }
        return -1;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLLocator
    public int getColumnNumber() {
        if (this.fCurrentEntity != null) {
            return this.fCurrentEntity.columnNumber;
        }
        return -1;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEntityReader, com.amazonaws.javax.xml.stream.xerces.xni.XMLLocator
    public int getCharacterOffset() {
        if (this.fCurrentEntity != null) {
            return this.fCurrentEntity.fTotalCountTillLastLoad + this.fCurrentEntity.position;
        }
        return -1;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLResourceIdentifier
    public String getExpandedSystemId() {
        if (this.fCurrentEntity == null || this.fCurrentEntity.entityLocation == null) {
            return null;
        }
        return this.fCurrentEntity.entityLocation.getExpandedSystemId();
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLResourceIdentifier
    public String getLiteralSystemId() {
        if (this.fCurrentEntity == null || this.fCurrentEntity.entityLocation == null) {
            return null;
        }
        return this.fCurrentEntity.entityLocation.getLiteralSystemId();
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.XMLResourceIdentifier
    public String getPublicId() {
        if (this.fCurrentEntity == null || this.fCurrentEntity.entityLocation == null) {
            return null;
        }
        return this.fCurrentEntity.entityLocation.getPublicId();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEntityReader
    public void setVersion(String str) {
        this.fCurrentEntity.version = str;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEntityReader
    public String getVersion() {
        return this.fCurrentEntity.version;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEntityReader, com.amazonaws.javax.xml.stream.xerces.xni.XMLLocator
    public String getEncoding() {
        return this.fCurrentEntity.encoding;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEntityReader
    public void setEncoding(String str) {
        if (this.fCurrentEntity.stream != null) {
            if (this.fCurrentEntity.encoding == null || !this.fCurrentEntity.encoding.equals(str)) {
                if (this.fCurrentEntity.encoding != null && this.fCurrentEntity.encoding.startsWith("UTF-16")) {
                    String upperCase = str.toUpperCase(Locale.ENGLISH);
                    if (!upperCase.equals("UTF-16")) {
                        if (upperCase.equals("ISO-10646-UCS-4")) {
                            if (this.fCurrentEntity.encoding.equals("UTF-16BE")) {
                                this.fCurrentEntity.reader = new UCSReader(this.fCurrentEntity.stream, (short) 8);
                                return;
                            } else {
                                this.fCurrentEntity.reader = new UCSReader(this.fCurrentEntity.stream, (short) 4);
                                return;
                            }
                        }
                        if (upperCase.equals("ISO-10646-UCS-2")) {
                            if (this.fCurrentEntity.encoding.equals("UTF-16BE")) {
                                this.fCurrentEntity.reader = new UCSReader(this.fCurrentEntity.stream, (short) 2);
                                return;
                            } else {
                                this.fCurrentEntity.reader = new UCSReader(this.fCurrentEntity.stream, (short) 1);
                                return;
                            }
                        }
                    } else {
                        return;
                    }
                }
                this.fCurrentEntity.reader = createReader(this.fCurrentEntity.stream, str, null);
                this.fCurrentEntity.encoding = str;
            }
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEntityReader
    public boolean isExternal() {
        return this.fCurrentEntity.isExternal();
    }

    public int getChar(int i) {
        if (arrangeCapacity(i + 1, false)) {
            return this.fCurrentEntity.ch[this.fCurrentEntity.position + i];
        }
        return -1;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEntityReader
    public int peekChar() throws IOException {
        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            invokeListeners(0);
            load(0, true);
        }
        char c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
        if (this.isExternal && c == '\r') {
            return 10;
        }
        return c;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEntityReader
    public int scanChar() throws IOException {
        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            invokeListeners(0);
            load(0, true);
        }
        char[] cArr = this.fCurrentEntity.ch;
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        int i = scannedEntity.position;
        scannedEntity.position = i + 1;
        char c = cArr[i];
        if (c == '\n' || (c == '\r' && this.isExternal)) {
            this.fCurrentEntity.lineNumber++;
            this.fCurrentEntity.columnNumber = 1;
            if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                invokeListeners(1);
                this.fCurrentEntity.ch[0] = c;
                load(1, false);
            }
            if (c == '\r' && this.isExternal) {
                char[] cArr2 = this.fCurrentEntity.ch;
                Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
                int i2 = scannedEntity2.position;
                scannedEntity2.position = i2 + 1;
                if (cArr2[i2] != '\n') {
                    this.fCurrentEntity.position--;
                }
                c = '\n';
            }
        }
        this.fCurrentEntity.columnNumber++;
        return c;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEntityReader
    public String scanNmtoken() throws IOException {
        boolean zIsName;
        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            invokeListeners(0);
            load(0, true);
        }
        int i = this.fCurrentEntity.position;
        while (true) {
            char c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
            if (c < 127) {
                zIsName = validNames[c];
            } else {
                zIsName = XMLChar.isName(c);
            }
            if (!zIsName) {
                break;
            }
            Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
            int i2 = scannedEntity.position + 1;
            scannedEntity.position = i2;
            if (i2 == this.fCurrentEntity.count) {
                int i3 = this.fCurrentEntity.position - i;
                invokeListeners(i3);
                if (i3 == this.fCurrentEntity.fBufferSize) {
                    char[] cArr = new char[this.fCurrentEntity.fBufferSize * 2];
                    System.arraycopy(this.fCurrentEntity.ch, i, cArr, 0, i3);
                    this.fCurrentEntity.ch = cArr;
                    this.fCurrentEntity.fBufferSize *= 2;
                } else {
                    System.arraycopy(this.fCurrentEntity.ch, i, this.fCurrentEntity.ch, 0, i3);
                }
                if (load(i3, false)) {
                    i = 0;
                    break;
                }
                i = 0;
            }
        }
        int i4 = this.fCurrentEntity.position - i;
        this.fCurrentEntity.columnNumber += i4;
        if (i4 <= 0) {
            return null;
        }
        return this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, i, i4);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEntityReader
    public String scanName() throws IOException {
        boolean zIsName;
        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            invokeListeners(0);
            load(0, true);
        }
        int i = this.fCurrentEntity.position;
        if (XMLChar.isNameStart(this.fCurrentEntity.ch[i])) {
            Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
            int i2 = scannedEntity.position + 1;
            scannedEntity.position = i2;
            if (i2 == this.fCurrentEntity.count) {
                invokeListeners(1);
                this.fCurrentEntity.ch[0] = this.fCurrentEntity.ch[i];
                if (load(1, false)) {
                    this.fCurrentEntity.columnNumber++;
                    String strAddSymbol = this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, 0, 1);
                    this.scannedName = this.fSymbolTable.getCharArray();
                    return strAddSymbol;
                }
                i = 0;
            }
            while (true) {
                char c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
                if (c < 127) {
                    zIsName = validNames[c];
                } else {
                    zIsName = XMLChar.isName(c);
                }
                if (!zIsName) {
                    break;
                }
                Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
                int i3 = scannedEntity2.position + 1;
                scannedEntity2.position = i3;
                if (i3 == this.fCurrentEntity.count) {
                    int i4 = this.fCurrentEntity.position - i;
                    invokeListeners(i4);
                    if (i4 == this.fCurrentEntity.fBufferSize) {
                        char[] cArr = new char[this.fCurrentEntity.fBufferSize * 2];
                        System.arraycopy(this.fCurrentEntity.ch, i, cArr, 0, i4);
                        this.fCurrentEntity.ch = cArr;
                        this.fCurrentEntity.fBufferSize *= 2;
                    } else {
                        System.arraycopy(this.fCurrentEntity.ch, i, this.fCurrentEntity.ch, 0, i4);
                    }
                    if (load(i4, false)) {
                        i = 0;
                        break;
                    }
                    i = 0;
                }
            }
        }
        int i5 = this.fCurrentEntity.position - i;
        this.fCurrentEntity.columnNumber += i5;
        if (i5 <= 0) {
            return null;
        }
        String strAddSymbol2 = this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, i, i5);
        this.scannedName = this.fSymbolTable.getCharArray();
        return strAddSymbol2;
    }

    /* JADX WARN: Code duplicated, block: B:34:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:36:0x0108  */
    /* JADX WARN: Code duplicated, block: B:40:0x0112  */
    /* JADX WARN: Code duplicated, block: B:42:0x0121  */
    /* JADX WARN: Code duplicated, block: B:45:0x00dd A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:46:0x010f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:48:0x0069 A[SYNTHETIC] */
    @Override // com.amazonaws.javax.xml.stream.XMLEntityReader
    public boolean scanQName(QName qName) throws IOException {
        boolean zIsName;
        String strAddSymbol;
        String strAddSymbol2;
        int i;
        int i2;
        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            invokeListeners(0);
            load(0, true);
        }
        int i3 = this.fCurrentEntity.position;
        if (!XMLChar.isNameStart(this.fCurrentEntity.ch[i3])) {
            return false;
        }
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        int i4 = scannedEntity.position + 1;
        scannedEntity.position = i4;
        if (i4 == this.fCurrentEntity.count) {
            invokeListeners(1);
            this.fCurrentEntity.ch[0] = this.fCurrentEntity.ch[i3];
            if (load(1, false)) {
                this.fCurrentEntity.columnNumber++;
                String strAddSymbol3 = this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, 0, 1);
                qName.setValues(null, strAddSymbol3, strAddSymbol3, null);
                qName.characters = this.fSymbolTable.getCharArray();
                return true;
            }
            i3 = 0;
        }
        int i5 = i3;
        int i6 = -1;
        while (true) {
            char c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
            if (c < 127) {
                zIsName = validNames[c];
            } else {
                zIsName = XMLChar.isName(c);
            }
            if (!zIsName) {
                break;
            }
            if (c == ':') {
                if (i6 != -1) {
                    break;
                }
                i6 = this.fCurrentEntity.position;
                Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
                i = scannedEntity2.position + 1;
                scannedEntity2.position = i;
                if (i == this.fCurrentEntity.count) {
                    i2 = this.fCurrentEntity.position - i5;
                    invokeListeners(i2);
                    if (i2 == this.fCurrentEntity.fBufferSize) {
                        char[] cArr = new char[this.fCurrentEntity.fBufferSize * 2];
                        System.arraycopy(this.fCurrentEntity.ch, i5, cArr, 0, i2);
                        this.fCurrentEntity.ch = cArr;
                        this.fCurrentEntity.fBufferSize *= 2;
                    } else {
                        System.arraycopy(this.fCurrentEntity.ch, i5, this.fCurrentEntity.ch, 0, i2);
                    }
                    if (i6 != -1) {
                        i6 -= i5;
                    }
                    if (load(i2, false)) {
                        i5 = 0;
                        break;
                    }
                    i5 = 0;
                }
            } else {
                Entity.ScannedEntity scannedEntity3 = this.fCurrentEntity;
                i = scannedEntity3.position + 1;
                scannedEntity3.position = i;
                if (i == this.fCurrentEntity.count) {
                    i2 = this.fCurrentEntity.position - i5;
                    invokeListeners(i2);
                    if (i2 == this.fCurrentEntity.fBufferSize) {
                        char[] cArr2 = new char[this.fCurrentEntity.fBufferSize * 2];
                        System.arraycopy(this.fCurrentEntity.ch, i5, cArr2, 0, i2);
                        this.fCurrentEntity.ch = cArr2;
                        this.fCurrentEntity.fBufferSize *= 2;
                    } else {
                        System.arraycopy(this.fCurrentEntity.ch, i5, this.fCurrentEntity.ch, 0, i2);
                    }
                    if (i6 != -1) {
                        i6 -= i5;
                    }
                    if (load(i2, false)) {
                        i5 = 0;
                        break;
                    }
                    i5 = 0;
                }
            }
        }
        int i7 = this.fCurrentEntity.position - i5;
        this.fCurrentEntity.columnNumber += i7;
        if (i7 <= 0) {
            return false;
        }
        String strAddSymbol4 = this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, i5, i7);
        qName.characters = this.fSymbolTable.getCharArray();
        if (i6 != -1) {
            int i8 = i6 - i5;
            strAddSymbol2 = this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, i5, i8);
            strAddSymbol = this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, i6 + 1, (i7 - i8) - 1);
        } else {
            strAddSymbol = strAddSymbol4;
            strAddSymbol2 = null;
        }
        qName.setValues(strAddSymbol2, strAddSymbol, strAddSymbol4, null);
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:72:0x00c1 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:73:? A[LOOP:1: B:11:0x002b->B:73:?, LOOP_END, SYNTHETIC] */
    @Override // com.amazonaws.javax.xml.stream.XMLEntityReader
    public int scanContent(XMLString xMLString) throws IOException {
        byte b;
        char c;
        boolean zIsContent;
        int i = 0;
        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            invokeListeners(0);
            load(0, true);
        } else if (this.fCurrentEntity.position == this.fCurrentEntity.count - 1) {
            invokeListeners(0);
            this.fCurrentEntity.ch[0] = this.fCurrentEntity.ch[this.fCurrentEntity.count - 1];
            load(1, false);
            this.fCurrentEntity.position = 0;
        }
        int i2 = this.fCurrentEntity.position;
        char c2 = this.fCurrentEntity.ch[i2];
        if (c2 == '\n' || (c2 == '\r' && this.isExternal)) {
            int i3 = i2;
            int i4 = 0;
            while (true) {
                char[] cArr = this.fCurrentEntity.ch;
                Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
                int i5 = scannedEntity.position;
                scannedEntity.position = i5 + 1;
                char c3 = cArr[i5];
                if (c3 == '\r' && this.isExternal) {
                    i4++;
                    this.fCurrentEntity.lineNumber++;
                    this.fCurrentEntity.columnNumber = 1;
                    if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                        invokeListeners(i4);
                        this.fCurrentEntity.position = i4;
                        if (load(i4, false)) {
                            break;
                        }
                        i3 = 0;
                    }
                    if (this.fCurrentEntity.ch[this.fCurrentEntity.position] == '\n') {
                        this.fCurrentEntity.position++;
                        i3++;
                    } else {
                        i4++;
                    }
                    if (this.fCurrentEntity.position >= this.fCurrentEntity.count - 1) {
                        i = i3;
                        break;
                    }
                } else {
                    if (c3 != '\n') {
                        this.fCurrentEntity.position--;
                        i = i3;
                        break;
                    }
                    i4++;
                    this.fCurrentEntity.lineNumber++;
                    this.fCurrentEntity.columnNumber = 1;
                    if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                        invokeListeners(i4);
                        this.fCurrentEntity.position = i4;
                        if (load(i4, false)) {
                            break;
                        }
                        i3 = 0;
                        if (this.fCurrentEntity.position >= this.fCurrentEntity.count - 1) {
                            i = i3;
                            break;
                        }
                    } else if (this.fCurrentEntity.position >= this.fCurrentEntity.count - 1) {
                        i = i3;
                        break;
                    }
                }
            }
            for (int i6 = i; i6 < this.fCurrentEntity.position; i6++) {
                this.fCurrentEntity.ch[i6] = '\n';
            }
            int i7 = this.fCurrentEntity.position - i;
            if (this.fCurrentEntity.position == this.fCurrentEntity.count - 1) {
                xMLString.setValues(this.fCurrentEntity.ch, i, i7);
                return -1;
            }
            int i8 = i4;
            i2 = i;
            i = i8;
        }
        while (this.fCurrentEntity.position < this.fCurrentEntity.count) {
            char[] cArr2 = this.fCurrentEntity.ch;
            Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
            int i9 = scannedEntity2.position;
            scannedEntity2.position = i9 + 1;
            char c4 = cArr2[i9];
            if (c4 < 127) {
                zIsContent = validContent[c4];
            } else {
                zIsContent = XMLChar.isContent(c4);
            }
            if (!zIsContent) {
                this.fCurrentEntity.position--;
                break;
            }
        }
        int i10 = this.fCurrentEntity.position - i2;
        Entity.ScannedEntity scannedEntity3 = this.fCurrentEntity;
        scannedEntity3.columnNumber = (i10 - i) + scannedEntity3.columnNumber;
        xMLString.setValues(this.fCurrentEntity.ch, i2, i10);
        if (this.fCurrentEntity.position != this.fCurrentEntity.count) {
            c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
            if (c == '\r' && this.isExternal) {
                b = c;
                b = c;
                b = 10;
            }
        } else {
            b = -1;
        }
        b = c;
        b = c;
        b = c;
        return b == true ? 1 : 0;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEntityReader
    public int scanLiteral(int i, XMLString xMLString) throws IOException {
        int i2;
        int i3;
        byte b;
        char c;
        boolean zIsContent;
        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            invokeListeners(0);
            load(0, true);
        } else if (this.fCurrentEntity.position == this.fCurrentEntity.count - 1) {
            invokeListeners(0);
            this.fCurrentEntity.ch[0] = this.fCurrentEntity.ch[this.fCurrentEntity.count - 1];
            load(1, false);
            this.fCurrentEntity.position = 0;
        }
        int i4 = this.fCurrentEntity.position;
        char c2 = this.fCurrentEntity.ch[i4];
        if (this.whiteSpaceInfoNeeded) {
            this.whiteSpaceLen = 0;
        }
        if (c2 == '\n' || (c2 == '\r' && this.isExternal)) {
            i2 = i4;
            i3 = 0;
            do {
                char[] cArr = this.fCurrentEntity.ch;
                Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
                int i5 = scannedEntity.position;
                scannedEntity.position = i5 + 1;
                char c3 = cArr[i5];
                if (c3 == '\r' && this.isExternal) {
                    i3++;
                    this.fCurrentEntity.lineNumber++;
                    this.fCurrentEntity.columnNumber = 1;
                    if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                        invokeListeners(i3);
                        this.fCurrentEntity.position = i3;
                        if (load(i3, false)) {
                            i2 = 0;
                            break;
                        }
                        i2 = 0;
                    }
                    if (this.fCurrentEntity.ch[this.fCurrentEntity.position] == '\n') {
                        this.fCurrentEntity.position++;
                        i2++;
                    } else {
                        i3++;
                    }
                } else {
                    if (c3 != '\n') {
                        this.fCurrentEntity.position--;
                        break;
                    }
                    i3++;
                    this.fCurrentEntity.lineNumber++;
                    this.fCurrentEntity.columnNumber = 1;
                    if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                        invokeListeners(i3);
                        this.fCurrentEntity.position = i3;
                        if (load(i3, false)) {
                            i2 = 0;
                            break;
                        }
                        i2 = 0;
                    }
                }
            } while (this.fCurrentEntity.position < this.fCurrentEntity.count - 1);
            for (int i6 = i2; i6 < this.fCurrentEntity.position; i6++) {
                this.fCurrentEntity.ch[i6] = '\n';
                int[] iArr = this.whiteSpaceLookup;
                int i7 = this.whiteSpaceLen;
                this.whiteSpaceLen = i7 + 1;
                iArr[i7] = i6;
            }
            int i8 = this.fCurrentEntity.position - i2;
            if (this.fCurrentEntity.position == this.fCurrentEntity.count - 1) {
                xMLString.setValues(this.fCurrentEntity.ch, i2, i8);
                return -1;
            }
        } else {
            i2 = i4;
            i3 = 0;
        }
        while (this.fCurrentEntity.position < this.fCurrentEntity.count) {
            char[] cArr2 = this.fCurrentEntity.ch;
            Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
            int i9 = scannedEntity2.position;
            scannedEntity2.position = i9 + 1;
            char c4 = cArr2[i9];
            if ((c4 == i && (!this.fCurrentEntity.literal || this.isExternal)) || c4 == '%') {
                this.fCurrentEntity.position--;
                break;
            }
            if (c4 < 127) {
                zIsContent = validContent[c4];
            } else {
                zIsContent = XMLChar.isContent(c4);
            }
            if (!zIsContent) {
                this.fCurrentEntity.position--;
                break;
            }
            if (this.whiteSpaceInfoNeeded && (c4 == ' ' || c4 == '\t')) {
                if (this.whiteSpaceLen < this.whiteSpaceLookup.length) {
                    int[] iArr2 = this.whiteSpaceLookup;
                    int i10 = this.whiteSpaceLen;
                    this.whiteSpaceLen = i10 + 1;
                    iArr2[i10] = this.fCurrentEntity.position - 1;
                } else {
                    int[] iArr3 = new int[this.whiteSpaceLookup.length + 20];
                    System.arraycopy(this.whiteSpaceLookup, 0, iArr3, 0, this.whiteSpaceLookup.length);
                    this.whiteSpaceLookup = iArr3;
                    int[] iArr4 = this.whiteSpaceLookup;
                    int i11 = this.whiteSpaceLen;
                    this.whiteSpaceLen = i11 + 1;
                    iArr4[i11] = this.fCurrentEntity.position - 1;
                }
            }
        }
        int i12 = this.fCurrentEntity.position - i2;
        Entity.ScannedEntity scannedEntity3 = this.fCurrentEntity;
        scannedEntity3.columnNumber = (i12 - i3) + scannedEntity3.columnNumber;
        xMLString.setValues(this.fCurrentEntity.ch, i2, i12);
        if (this.fCurrentEntity.position != this.fCurrentEntity.count) {
            c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
            if (c == i && this.fCurrentEntity.literal) {
                b = c;
                b = c;
                b = -1;
            }
        } else {
            b = -1;
        }
        b = c;
        b = c;
        b = c;
        return b == true ? 1 : 0;
    }

    /* JADX WARN: Code duplicated, block: B:34:0x011c A[PHI: r1 r2
      0x011c: PHI (r1v31 int) = (r1v30 int), (r1v30 int), (r1v34 int), (r1v33 int) binds: [B:41:0x0147, B:43:0x015a, B:37:0x0129, B:33:0x0112] A[DONT_GENERATE, DONT_INLINE]
      0x011c: PHI (r2v23 int) = (r2v21 int), (r2v22 int), (r2v25 int), (r2v26 int) binds: [B:41:0x0147, B:43:0x015a, B:37:0x0129, B:33:0x0112] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.amazonaws.javax.xml.stream.XMLEntityReader
    public boolean scanData(String str, XMLStringBuffer xMLStringBuffer) throws IOException {
        boolean z = false;
        int length = str.length();
        char cCharAt = str.charAt(0);
        do {
            if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                invokeListeners(0);
                load(0, true);
            } else if (this.fCurrentEntity.position >= this.fCurrentEntity.count - length) {
                invokeListeners(this.fCurrentEntity.count - this.fCurrentEntity.position);
                System.arraycopy(this.fCurrentEntity.ch, this.fCurrentEntity.position, this.fCurrentEntity.ch, 0, this.fCurrentEntity.count - this.fCurrentEntity.position);
                load(this.fCurrentEntity.count - this.fCurrentEntity.position, false);
                this.fCurrentEntity.position = 0;
            }
            if (this.fCurrentEntity.position >= this.fCurrentEntity.count - length) {
                invokeListeners(0);
                xMLStringBuffer.append(this.fCurrentEntity.ch, this.fCurrentEntity.position, this.fCurrentEntity.count - this.fCurrentEntity.position);
                this.fCurrentEntity.columnNumber += this.fCurrentEntity.count;
                this.fCurrentEntity.position = this.fCurrentEntity.count;
                load(0, true);
                return false;
            }
            int i = this.fCurrentEntity.position;
            char c = this.fCurrentEntity.ch[i];
            int i2 = 0;
            if (c == '\n' || (c == '\r' && this.isExternal)) {
                do {
                    char[] cArr = this.fCurrentEntity.ch;
                    Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
                    int i3 = scannedEntity.position;
                    scannedEntity.position = i3 + 1;
                    char c2 = cArr[i3];
                    if (c2 == '\r' && this.isExternal) {
                        i2++;
                        this.fCurrentEntity.lineNumber++;
                        this.fCurrentEntity.columnNumber = 1;
                        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                            i = 0;
                            invokeListeners(i2);
                            this.fCurrentEntity.position = i2;
                            if (!load(i2, false)) {
                                break;
                            }
                            break;
                        }
                        if (this.fCurrentEntity.ch[this.fCurrentEntity.position] == '\n') {
                            this.fCurrentEntity.position++;
                            i++;
                        } else {
                            i2++;
                        }
                    } else {
                        if (c2 != '\n') {
                            this.fCurrentEntity.position--;
                            break;
                        }
                        i2++;
                        this.fCurrentEntity.lineNumber++;
                        this.fCurrentEntity.columnNumber = 1;
                        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                            i = 0;
                            invokeListeners(i2);
                            this.fCurrentEntity.position = i2;
                            this.fCurrentEntity.count = i2;
                            if (load(i2, false)) {
                                break;
                            }
                        }
                    }
                } while (this.fCurrentEntity.position < this.fCurrentEntity.count - 1);
                for (int i4 = i; i4 < this.fCurrentEntity.position; i4++) {
                    this.fCurrentEntity.ch[i4] = '\n';
                }
                int i5 = this.fCurrentEntity.position - i;
                if (this.fCurrentEntity.position == this.fCurrentEntity.count - 1) {
                    xMLStringBuffer.append(this.fCurrentEntity.ch, i, i5);
                    return true;
                }
            }
            while (this.fCurrentEntity.position < this.fCurrentEntity.count) {
                char[] cArr2 = this.fCurrentEntity.ch;
                Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
                int i6 = scannedEntity2.position;
                scannedEntity2.position = i6 + 1;
                char c3 = cArr2[i6];
                if (c3 == cCharAt) {
                    int i7 = this.fCurrentEntity.position - 1;
                    for (int i8 = 1; i8 < length; i8++) {
                        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                            this.fCurrentEntity.position -= i8;
                            break;
                        }
                        char[] cArr3 = this.fCurrentEntity.ch;
                        Entity.ScannedEntity scannedEntity3 = this.fCurrentEntity;
                        int i9 = scannedEntity3.position;
                        scannedEntity3.position = i9 + 1;
                        if (str.charAt(i8) != cArr3[i9]) {
                            this.fCurrentEntity.position -= i8;
                            break;
                        }
                    }
                    if (this.fCurrentEntity.position == i7 + length) {
                        z = true;
                        break;
                    }
                } else {
                    if (c3 == '\n' || (this.isExternal && c3 == '\r')) {
                        this.fCurrentEntity.position--;
                        break;
                    }
                    if (XMLChar.isInvalid(c3)) {
                        this.fCurrentEntity.position--;
                        int i10 = this.fCurrentEntity.position - i;
                        Entity.ScannedEntity scannedEntity4 = this.fCurrentEntity;
                        scannedEntity4.columnNumber = (i10 - i2) + scannedEntity4.columnNumber;
                        xMLStringBuffer.append(this.fCurrentEntity.ch, i, i10);
                        return true;
                    }
                }
            }
            int i11 = this.fCurrentEntity.position - i;
            Entity.ScannedEntity scannedEntity5 = this.fCurrentEntity;
            scannedEntity5.columnNumber = (i11 - i2) + scannedEntity5.columnNumber;
            xMLStringBuffer.append(this.fCurrentEntity.ch, i, z ? i11 - length : i11);
        } while (!z);
        return !z;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEntityReader
    public boolean skipChar(int i) throws IOException {
        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            invokeListeners(0);
            load(0, true);
        }
        char c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
        if (c == i) {
            this.fCurrentEntity.position++;
            if (i == 10) {
                this.fCurrentEntity.lineNumber++;
                this.fCurrentEntity.columnNumber = 1;
                return true;
            }
            this.fCurrentEntity.columnNumber++;
            return true;
        }
        if (i != 10 || c != '\r' || !this.isExternal) {
            return false;
        }
        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            invokeListeners(1);
            this.fCurrentEntity.ch[0] = c;
            load(1, false);
        }
        this.fCurrentEntity.position++;
        if (this.fCurrentEntity.ch[this.fCurrentEntity.position] == '\n') {
            this.fCurrentEntity.position++;
        }
        this.fCurrentEntity.lineNumber++;
        this.fCurrentEntity.columnNumber = 1;
        return true;
    }

    public boolean isSpace(char c) {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r';
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEntityReader
    public boolean skipSpaces() throws IOException {
        boolean zLoad;
        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            invokeListeners(0);
            load(0, true);
        }
        if (this.fCurrentEntity == null) {
            return false;
        }
        char c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
        if (!XMLChar.isSpace(c)) {
            return false;
        }
        do {
            char c2 = c;
            if (c2 == '\n' || (this.isExternal && c2 == '\r')) {
                this.fCurrentEntity.lineNumber++;
                this.fCurrentEntity.columnNumber = 1;
                if (this.fCurrentEntity.position == this.fCurrentEntity.count - 1) {
                    invokeListeners(0);
                    this.fCurrentEntity.ch[0] = c2;
                    zLoad = load(1, true);
                    if (!zLoad) {
                        this.fCurrentEntity.position = 0;
                    } else if (this.fCurrentEntity == null) {
                        return true;
                    }
                } else {
                    zLoad = false;
                }
                if (c2 == '\r' && this.isExternal) {
                    char[] cArr = this.fCurrentEntity.ch;
                    Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
                    int i = scannedEntity.position + 1;
                    scannedEntity.position = i;
                    if (cArr[i] != '\n') {
                        this.fCurrentEntity.position--;
                    }
                }
            } else {
                this.fCurrentEntity.columnNumber++;
                zLoad = false;
            }
            if (!zLoad) {
                this.fCurrentEntity.position++;
            }
            if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                invokeListeners(0);
                load(0, true);
                if (this.fCurrentEntity == null) {
                    return true;
                }
            }
            c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
        } while (XMLChar.isSpace(c));
        return true;
    }

    public boolean arrangeCapacity(int i) {
        return arrangeCapacity(i, false);
    }

    public boolean arrangeCapacity(int i, boolean z) throws IOException {
        if (this.fCurrentEntity.count - this.fCurrentEntity.position >= i) {
            return true;
        }
        while (this.fCurrentEntity.count - this.fCurrentEntity.position < i) {
            if (this.fCurrentEntity.ch.length - this.fCurrentEntity.position < i) {
                invokeListeners(0);
                System.arraycopy(this.fCurrentEntity.ch, this.fCurrentEntity.position, this.fCurrentEntity.ch, 0, this.fCurrentEntity.count - this.fCurrentEntity.position);
                this.fCurrentEntity.count -= this.fCurrentEntity.position;
                this.fCurrentEntity.position = 0;
            }
            if (this.fCurrentEntity.count - this.fCurrentEntity.position < i) {
                int i2 = this.fCurrentEntity.position;
                invokeListeners(i2);
                boolean zLoad = load(this.fCurrentEntity.count, z);
                this.fCurrentEntity.position = i2;
                if (zLoad) {
                    break;
                }
            }
        }
        return this.fCurrentEntity.count - this.fCurrentEntity.position >= i;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEntityReader
    public boolean skipString(String str) {
        int length = str.length();
        if (arrangeCapacity(length, false)) {
            int i = this.fCurrentEntity.position;
            int i2 = length - 1;
            int i3 = (this.fCurrentEntity.position + length) - 1;
            while (true) {
                int i4 = i2 - 1;
                if (str.charAt(i2) != this.fCurrentEntity.ch[i3]) {
                    break;
                }
                int i5 = i3 - 1;
                if (i3 == i) {
                    this.fCurrentEntity.position += length;
                    this.fCurrentEntity.columnNumber += length;
                    return true;
                }
                i3 = i5;
                i2 = i4;
            }
        }
        return false;
    }

    public boolean skipString(char[] cArr) {
        int length = cArr.length;
        if (!arrangeCapacity(length, false)) {
            return false;
        }
        int i = this.fCurrentEntity.position;
        int i2 = this.fCurrentEntity.position + length;
        int i3 = i;
        int i4 = 0;
        while (i4 < length) {
            int i5 = i3 + 1;
            if (this.fCurrentEntity.ch[i3] != cArr[i4]) {
                return false;
            }
            i4++;
            i3 = i5;
        }
        this.fCurrentEntity.position += length;
        this.fCurrentEntity.columnNumber += length;
        return true;
    }

    final boolean load(int i, boolean z) throws IOException {
        int length;
        boolean z2;
        this.fCurrentEntity.fTotalCountTillLastLoad += this.fCurrentEntity.fLastCount;
        if (this.fCurrentEntity.mayReadChunks) {
            length = this.fCurrentEntity.ch.length - i;
        } else {
            Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
            length = 64;
        }
        int i2 = this.fCurrentEntity.reader.read(this.fCurrentEntity.ch, i, length);
        if (i2 != -1) {
            if (i2 != 0) {
                this.fCurrentEntity.fLastCount = i2;
                this.fCurrentEntity.count = i2 + i;
                this.fCurrentEntity.position = i;
                z2 = false;
            } else {
                z2 = false;
            }
        } else {
            this.fCurrentEntity.count = i;
            this.fCurrentEntity.position = i;
            if (z) {
                this.fEntityManager.endEntity();
                if (this.fCurrentEntity == null) {
                    return true;
                }
                if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                    load(0, true);
                }
            }
            z2 = true;
        }
        return z2;
    }

    protected Reader createReader(InputStream inputStream, String str, Boolean bool) {
        if (str == null) {
            str = XMLStreamWriterImpl.UTF_8;
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        if (upperCase.equals(XMLStreamWriterImpl.UTF_8)) {
            return new UTF8Reader(inputStream, this.fCurrentEntity.fBufferSize, this.fErrorReporter.getMessageFormatter(XMLMessageFormatter.XML_DOMAIN), this.fErrorReporter.getLocale());
        }
        if (upperCase.equals("US-ASCII")) {
            return new ASCIIReader(inputStream, this.fCurrentEntity.fBufferSize, this.fErrorReporter.getMessageFormatter(XMLMessageFormatter.XML_DOMAIN), this.fErrorReporter.getLocale());
        }
        if (upperCase.equals("ISO-10646-UCS-4")) {
            if (bool != null) {
                if (bool.booleanValue()) {
                    return new UCSReader(inputStream, (short) 8);
                }
                return new UCSReader(inputStream, (short) 4);
            }
            this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "EncodingByteOrderUnsupported", new Object[]{str}, (short) 2);
        }
        if (upperCase.equals("ISO-10646-UCS-2")) {
            if (bool != null) {
                if (bool.booleanValue()) {
                    return new UCSReader(inputStream, (short) 2);
                }
                return new UCSReader(inputStream, (short) 1);
            }
            this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "EncodingByteOrderUnsupported", new Object[]{str}, (short) 2);
        }
        boolean zIsValidIANAEncoding = XMLChar.isValidIANAEncoding(str);
        boolean zIsValidJavaEncoding = XMLChar.isValidJavaEncoding(str);
        if (!zIsValidIANAEncoding || (this.fAllowJavaEncodings && !zIsValidJavaEncoding)) {
            this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "EncodingDeclInvalid", new Object[]{str}, (short) 2);
            str = "ISO-8859-1";
        }
        String iANA2JavaMapping = EncodingMap.getIANA2JavaMapping(upperCase);
        if (iANA2JavaMapping != null) {
            str = iANA2JavaMapping;
        } else if (!this.fAllowJavaEncodings) {
            this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "EncodingDeclInvalid", new Object[]{str}, (short) 2);
            str = "ISO8859_1";
        }
        return new InputStreamReader(inputStream, str);
    }

    protected Object[] getEncodingName(byte[] bArr, int i) {
        if (i < 2) {
            return new Object[]{XMLStreamWriterImpl.UTF_8, null};
        }
        int i2 = bArr[0] & com.flurry.android.Constants.UNKNOWN;
        int i3 = bArr[1] & com.flurry.android.Constants.UNKNOWN;
        if (i2 == 254 && i3 == 255) {
            return new Object[]{"UTF-16BE", new Boolean(true)};
        }
        if (i2 == 255 && i3 == 254) {
            return new Object[]{"UTF-16LE", new Boolean(false)};
        }
        if (i < 3) {
            return new Object[]{XMLStreamWriterImpl.UTF_8, null};
        }
        int i4 = bArr[2] & com.flurry.android.Constants.UNKNOWN;
        if (i2 == 239 && i3 == 187 && i4 == 191) {
            return new Object[]{XMLStreamWriterImpl.UTF_8, null};
        }
        if (i < 4) {
            return new Object[]{XMLStreamWriterImpl.UTF_8, null};
        }
        int i5 = bArr[3] & com.flurry.android.Constants.UNKNOWN;
        if (i2 == 0 && i3 == 0 && i4 == 0 && i5 == 60) {
            return new Object[]{"ISO-10646-UCS-4", new Boolean(true)};
        }
        if (i2 == 60 && i3 == 0 && i4 == 0 && i5 == 0) {
            return new Object[]{"ISO-10646-UCS-4", new Boolean(false)};
        }
        if (i2 == 0 && i3 == 0 && i4 == 60 && i5 == 0) {
            return new Object[]{"ISO-10646-UCS-4", null};
        }
        if (i2 == 0 && i3 == 60 && i4 == 0 && i5 == 0) {
            return new Object[]{"ISO-10646-UCS-4", null};
        }
        if (i2 == 0 && i3 == 60 && i4 == 0 && i5 == 63) {
            return new Object[]{"UTF-16BE", new Boolean(true)};
        }
        if (i2 == 60 && i3 == 0 && i4 == 63 && i5 == 0) {
            return new Object[]{"UTF-16LE", new Boolean(false)};
        }
        if (i2 == 76 && i3 == 111 && i4 == 167 && i5 == 148) {
            return new Object[]{"CP037", null};
        }
        return new Object[]{XMLStreamWriterImpl.UTF_8, null};
    }

    final void print() {
    }

    @Override // com.amazonaws.javax.xml.stream.XMLEntityReader
    public void registerListener(XMLBufferListener xMLBufferListener) {
        if (!this.listeners.contains(xMLBufferListener)) {
            this.listeners.add(xMLBufferListener);
        }
    }

    private void invokeListeners(int i) {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 < this.listeners.size()) {
                ((XMLBufferListener) this.listeners.get(i3)).refresh(i);
                i2 = i3 + 1;
            } else {
                return;
            }
        }
    }
}

package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.amazonaws.javax.xml.stream.xerces.impl.io.ASCIIReader;
import com.amazonaws.javax.xml.stream.xerces.impl.io.UCSReader;
import com.amazonaws.javax.xml.stream.xerces.impl.io.UTF8Reader;
import com.amazonaws.javax.xml.stream.xerces.impl.msg.XMLMessageFormatter;
import com.amazonaws.javax.xml.stream.xerces.util.EncodingMap;
import com.amazonaws.javax.xml.stream.xerces.util.SymbolTable;
import com.amazonaws.javax.xml.stream.xerces.util.URI;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import com.amazonaws.javax.xml.stream.xerces.util.XMLResourceIdentifierImpl;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLResourceIdentifier;
import com.amazonaws.javax.xml.stream.xerces.xni.XNIException;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponentManager;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLConfigurationException;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLEntityResolver;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLInputSource;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Stack;
import java.util.Vector;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class XMLEntityManager implements XMLComponent, XMLEntityResolver {
    private static final boolean DEBUG_BUFFER = false;
    private static final boolean DEBUG_ENCODINGS = false;
    private static final boolean DEBUG_ENTITIES = false;
    private static final boolean DEBUG_RESOLVER = false;
    public static final int DEFAULT_BUFFER_SIZE = 8192;
    public static final int DEFAULT_INTERNAL_BUFFER_SIZE = 1024;
    public static final int DEFAULT_XMLDECL_BUFFER_SIZE = 64;
    protected static final String STAX_ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/stax-entity-resolver";
    private static String gEscapedUserDir;
    private static String gUserDir;
    protected final Object[] defaultEncoding;
    protected boolean fAllowJavaEncodings;
    protected int fBufferSize;
    protected Entity.ScannedEntity fCurrentEntity;
    protected Hashtable fDeclaredEntities;
    protected Hashtable fEntities;
    protected XMLEntityHandler fEntityHandler;
    protected XMLEntityReaderImpl fEntityReader;
    protected XMLEntityResolver fEntityResolver;
    protected Stack fEntityStack;
    protected XMLEntityStorage fEntityStorage;
    protected XMLErrorReporter fErrorReporter;
    protected boolean fExternalGeneralEntities;
    protected boolean fExternalParameterEntities;
    protected boolean fInExternalSubset;
    protected Vector fOwnReaders;
    protected PropertyManager fPropertyManager;
    private final XMLResourceIdentifierImpl fResourceIdentifier;
    protected boolean fStandalone;
    protected StaxEntityResolverWrapper fStaxEntityResolver;
    protected SymbolTable fSymbolTable;
    protected boolean fValidation;
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String EXTERNAL_GENERAL_ENTITIES = "http://xml.org/sax/features/external-general-entities";
    protected static final String EXTERNAL_PARAMETER_ENTITIES = "http://xml.org/sax/features/external-parameter-entities";
    protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
    protected static final String WARN_ON_DUPLICATE_ENTITYDEF = "http://apache.org/xml/features/warn-on-duplicate-entitydef";
    private static final String[] RECOGNIZED_FEATURES = {VALIDATION, EXTERNAL_GENERAL_ENTITIES, EXTERNAL_PARAMETER_ENTITIES, ALLOW_JAVA_ENCODINGS, WARN_ON_DUPLICATE_ENTITYDEF};
    private static final Boolean[] FEATURE_DEFAULTS = {null, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE};
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    protected static final String BUFFER_SIZE = "http://apache.org/xml/properties/input-buffer-size";
    private static final String[] RECOGNIZED_PROPERTIES = {SYMBOL_TABLE, ERROR_REPORTER, ENTITY_RESOLVER, VALIDATION_MANAGER, BUFFER_SIZE};
    private static final Object[] PROPERTY_DEFAULTS = {null, null, null, null, new Integer(8192)};
    private static final String XMLEntity = "[xml]".intern();
    private static final String DTDEntity = "[dtd]".intern();
    private static boolean[] gNeedEscaping = new boolean[XMLChar.MASK_NCNAME];
    private static char[] gAfterEscaping1 = new char[XMLChar.MASK_NCNAME];
    private static char[] gAfterEscaping2 = new char[XMLChar.MASK_NCNAME];
    private static char[] gHexChs = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    static {
        for (int i = 0; i <= 31; i++) {
            gNeedEscaping[i] = true;
            gAfterEscaping1[i] = gHexChs[i >> 4];
            gAfterEscaping2[i] = gHexChs[i & 15];
        }
        gNeedEscaping[127] = true;
        gAfterEscaping1[127] = '7';
        gAfterEscaping2[127] = 'F';
        for (char c : new char[]{' ', XMLStreamWriterImpl.OPEN_START_TAG, '>', '#', '%', '\"', '{', '}', '|', '\\', '^', '~', '[', ']', '`'}) {
            gNeedEscaping[c] = true;
            gAfterEscaping1[c] = gHexChs[c >> 4];
            gAfterEscaping2[c] = gHexChs[c & 15];
        }
    }

    public XMLEntityManager() {
        this.fAllowJavaEncodings = true;
        this.fBufferSize = 8192;
        this.fInExternalSubset = false;
        this.fEntities = new Hashtable();
        this.fEntityStack = new Stack();
        this.fCurrentEntity = null;
        this.defaultEncoding = new Object[]{XMLStreamWriterImpl.UTF_8, null};
        this.fResourceIdentifier = new XMLResourceIdentifierImpl();
        this.fOwnReaders = new Vector();
        this.fEntityStorage = new XMLEntityStorage(this);
        this.fEntityReader = new XMLEntityReaderImpl(this);
    }

    public XMLEntityManager(PropertyManager propertyManager) {
        this.fAllowJavaEncodings = true;
        this.fBufferSize = 8192;
        this.fInExternalSubset = false;
        this.fEntities = new Hashtable();
        this.fEntityStack = new Stack();
        this.fCurrentEntity = null;
        this.defaultEncoding = new Object[]{XMLStreamWriterImpl.UTF_8, null};
        this.fResourceIdentifier = new XMLResourceIdentifierImpl();
        this.fOwnReaders = new Vector();
        this.fPropertyManager = propertyManager;
        this.fEntityStorage = new XMLEntityStorage(this);
        this.fEntityReader = new XMLEntityReaderImpl(propertyManager, this);
        reset(propertyManager);
    }

    public XMLEntityStorage getEntityStore() {
        return this.fEntityStorage;
    }

    public XMLEntityReader getEntityReader() {
        return this.fEntityReader;
    }

    public void setStandalone(boolean z) {
        this.fStandalone = z;
    }

    public boolean isStandalone() {
        return this.fStandalone;
    }

    public void setEntityHandler(XMLEntityHandler xMLEntityHandler) {
        this.fEntityHandler = xMLEntityHandler;
    }

    public StaxXMLInputSource resolveEntityAsPerStax(XMLResourceIdentifier xMLResourceIdentifier) {
        String str;
        StaxXMLInputSource staxXMLInputSourceResolveEntity;
        XMLResourceIdentifierImpl xMLResourceIdentifierImpl;
        boolean z = true;
        if (xMLResourceIdentifier == null) {
            return null;
        }
        String publicId = xMLResourceIdentifier.getPublicId();
        String literalSystemId = xMLResourceIdentifier.getLiteralSystemId();
        String baseSystemId = xMLResourceIdentifier.getBaseSystemId();
        String expandedSystemId = xMLResourceIdentifier.getExpandedSystemId();
        boolean z2 = expandedSystemId == null;
        if (baseSystemId != null || this.fCurrentEntity == null || this.fCurrentEntity.entityLocation == null || (baseSystemId = this.fCurrentEntity.entityLocation.getExpandedSystemId()) == null) {
            z = z2;
            str = baseSystemId;
        } else {
            str = baseSystemId;
        }
        if (z) {
            expandedSystemId = expandSystemId(literalSystemId, str);
        }
        if (this.fStaxEntityResolver != null) {
            if (xMLResourceIdentifier instanceof XMLResourceIdentifierImpl) {
                xMLResourceIdentifierImpl = (XMLResourceIdentifierImpl) xMLResourceIdentifier;
            } else {
                this.fResourceIdentifier.clear();
                xMLResourceIdentifierImpl = this.fResourceIdentifier;
            }
            xMLResourceIdentifierImpl.setValues(publicId, literalSystemId, str, expandedSystemId);
            staxXMLInputSourceResolveEntity = this.fStaxEntityResolver.resolveEntity(xMLResourceIdentifierImpl);
        } else {
            staxXMLInputSourceResolveEntity = null;
        }
        if (staxXMLInputSourceResolveEntity == null) {
            return new StaxXMLInputSource(new XMLInputSource(publicId, literalSystemId, str));
        }
        if (staxXMLInputSourceResolveEntity.hasXMLStreamOrXMLEventReader()) {
        }
        return staxXMLInputSourceResolveEntity;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLEntityResolver
    public XMLInputSource resolveEntity(XMLResourceIdentifier xMLResourceIdentifier) {
        String str;
        XMLInputSource xMLInputSourceResolveEntity;
        XMLResourceIdentifierImpl xMLResourceIdentifierImpl;
        boolean z = true;
        if (xMLResourceIdentifier == null) {
            return null;
        }
        String publicId = xMLResourceIdentifier.getPublicId();
        String literalSystemId = xMLResourceIdentifier.getLiteralSystemId();
        String baseSystemId = xMLResourceIdentifier.getBaseSystemId();
        String expandedSystemId = xMLResourceIdentifier.getExpandedSystemId();
        boolean z2 = expandedSystemId == null;
        if (baseSystemId != null || this.fCurrentEntity == null || this.fCurrentEntity.entityLocation == null || (baseSystemId = this.fCurrentEntity.entityLocation.getExpandedSystemId()) == null) {
            z = z2;
            str = baseSystemId;
        } else {
            str = baseSystemId;
        }
        if (z) {
            expandedSystemId = expandSystemId(literalSystemId, str);
        }
        if (this.fEntityResolver != null) {
            if (xMLResourceIdentifier instanceof XMLResourceIdentifierImpl) {
                xMLResourceIdentifierImpl = (XMLResourceIdentifierImpl) xMLResourceIdentifier;
            } else {
                this.fResourceIdentifier.clear();
                xMLResourceIdentifierImpl = this.fResourceIdentifier;
            }
            xMLResourceIdentifierImpl.setValues(publicId, literalSystemId, str, expandedSystemId);
            xMLInputSourceResolveEntity = this.fEntityResolver.resolveEntity(xMLResourceIdentifierImpl);
        } else {
            xMLInputSourceResolveEntity = null;
        }
        if (xMLInputSourceResolveEntity == null) {
            return new XMLInputSource(publicId, literalSystemId, str);
        }
        return xMLInputSourceResolveEntity;
    }

    public void startEntity(String str, boolean z) throws IOException {
        XMLInputSource xMLInputSource;
        Entity entity = (Entity) this.fEntityStorage.getDeclaredEntities().get(str);
        if (entity == null) {
            if (this.fEntityHandler != null) {
                this.fResourceIdentifier.clear();
                this.fEntityHandler.startEntity(str, this.fResourceIdentifier, null);
                this.fEntityHandler.endEntity(str);
                return;
            }
            return;
        }
        boolean zIsExternal = entity.isExternal();
        if (zIsExternal) {
            boolean zIsUnparsed = entity.isUnparsed();
            boolean zStartsWith = str.startsWith("%");
            boolean z2 = !zStartsWith;
            if (zIsUnparsed || ((z2 && !this.fExternalGeneralEntities) || (zStartsWith && !this.fExternalParameterEntities))) {
                if (this.fEntityHandler != null) {
                    this.fResourceIdentifier.clear();
                    Entity.ExternalEntity externalEntity = (Entity.ExternalEntity) entity;
                    String literalSystemId = externalEntity.entityLocation != null ? externalEntity.entityLocation.getLiteralSystemId() : null;
                    String baseSystemId = externalEntity.entityLocation != null ? externalEntity.entityLocation.getBaseSystemId() : null;
                    this.fResourceIdentifier.setValues(externalEntity.entityLocation != null ? externalEntity.entityLocation.getPublicId() : null, literalSystemId, baseSystemId, expandSystemId(literalSystemId, baseSystemId));
                    this.fEntityHandler.startEntity(str, this.fResourceIdentifier, null);
                    this.fEntityHandler.endEntity(str);
                    return;
                }
                return;
            }
        }
        int size = this.fEntityStack.size();
        int i = size;
        while (i >= 0) {
            if ((i == size ? this.fCurrentEntity : (Entity) this.fEntityStack.elementAt(i)).name != str) {
                i--;
            } else {
                String string = str;
                for (int i2 = i + 1; i2 < size; i2++) {
                    string = new StringBuffer().append(string).append(" -> ").append(((Entity) this.fEntityStack.elementAt(i2)).name).toString();
                }
                this.fErrorReporter.reportError(getEntityReader(), XMLMessageFormatter.XML_DOMAIN, "RecursiveReference", new Object[]{str, new StringBuffer().append(new StringBuffer().append(string).append(" -> ").append(this.fCurrentEntity.name).toString()).append(" -> ").append(str).toString()}, (short) 2);
                if (this.fEntityHandler != null) {
                    this.fResourceIdentifier.clear();
                    if (zIsExternal) {
                        Entity.ExternalEntity externalEntity2 = (Entity.ExternalEntity) entity;
                        String literalSystemId2 = externalEntity2.entityLocation != null ? externalEntity2.entityLocation.getLiteralSystemId() : null;
                        String baseSystemId2 = externalEntity2.entityLocation != null ? externalEntity2.entityLocation.getBaseSystemId() : null;
                        this.fResourceIdentifier.setValues(externalEntity2.entityLocation != null ? externalEntity2.entityLocation.getPublicId() : null, literalSystemId2, baseSystemId2, expandSystemId(literalSystemId2, baseSystemId2));
                    }
                    this.fEntityHandler.startEntity(str, this.fResourceIdentifier, null);
                    this.fEntityHandler.endEntity(str);
                    return;
                }
                return;
            }
        }
        if (zIsExternal) {
            xMLInputSource = resolveEntityAsPerStax(((Entity.ExternalEntity) entity).entityLocation).getXMLInputSource();
        } else {
            xMLInputSource = new XMLInputSource((String) null, (String) null, (String) null, new StringReader(((Entity.InternalEntity) entity).text), (String) null);
        }
        startEntity(str, xMLInputSource, z, zIsExternal);
    }

    public void startDocumentEntity(XMLInputSource xMLInputSource) throws IOException {
        startEntity(XMLEntity, xMLInputSource, false, true);
    }

    public void startDTDEntity(XMLInputSource xMLInputSource) throws IOException {
        startEntity(DTDEntity, xMLInputSource, false, true);
    }

    public void startExternalSubset() {
        this.fInExternalSubset = true;
    }

    public void endExternalSubset() {
        this.fInExternalSubset = false;
    }

    public void startEntity(String str, XMLInputSource xMLInputSource, boolean z, boolean z2) throws IOException {
        String str2;
        int i;
        Reader readerCreateReader;
        String publicId = xMLInputSource.getPublicId();
        String systemId = xMLInputSource.getSystemId();
        String baseSystemId = xMLInputSource.getBaseSystemId();
        String encoding = xMLInputSource.getEncoding();
        RewindableInputStream rewindableInputStream = null;
        Reader characterStream = xMLInputSource.getCharacterStream();
        String strExpandSystemId = expandSystemId(systemId, baseSystemId);
        String str3 = baseSystemId == null ? strExpandSystemId : baseSystemId;
        if (characterStream == null) {
            InputStream byteStream = xMLInputSource.getByteStream();
            if (byteStream == null) {
                byteStream = new BufferedInputStream(new URL(strExpandSystemId).openStream());
            }
            rewindableInputStream = new RewindableInputStream(byteStream);
            if (encoding == null) {
                byte[] bArr = new byte[4];
                int i2 = 0;
                while (true) {
                    i = i2;
                    if (i >= 4) {
                        break;
                    }
                    bArr[i] = (byte) rewindableInputStream.read();
                    i2 = i + 1;
                }
                if (i == 4) {
                    Object[] encodingName = getEncodingName(bArr, i);
                    String str4 = (String) encodingName[0];
                    Boolean bool = (Boolean) encodingName[1];
                    rewindableInputStream.reset();
                    if (i > 2 && str4.equals(XMLStreamWriterImpl.UTF_8)) {
                        int i3 = bArr[0] & com.flurry.android.Constants.UNKNOWN;
                        int i4 = bArr[1] & com.flurry.android.Constants.UNKNOWN;
                        int i5 = bArr[2] & com.flurry.android.Constants.UNKNOWN;
                        if (i3 == 239 && i4 == 187 && i5 == 191) {
                            rewindableInputStream.skip(3L);
                        }
                    }
                    Reader readerCreateReader2 = createReader(rewindableInputStream, str4, bool);
                    encoding = str4;
                    readerCreateReader = readerCreateReader2;
                } else {
                    readerCreateReader = createReader(rewindableInputStream, encoding, null);
                }
                characterStream = readerCreateReader;
                str2 = encoding;
            } else {
                characterStream = createReader(rewindableInputStream, encoding, null);
                str2 = encoding;
            }
        } else {
            str2 = encoding;
        }
        if (this.fCurrentEntity != null) {
            this.fEntityStack.push(this.fCurrentEntity);
        }
        this.fCurrentEntity = new Entity.ScannedEntity(str, new XMLResourceIdentifierImpl(publicId, systemId, str3, strExpandSystemId), rewindableInputStream, characterStream, str2, z, false, z2);
        this.fEntityReader.setCurrentEntity(this.fCurrentEntity);
        this.fResourceIdentifier.setValues(publicId, systemId, str3, strExpandSystemId);
        if (this.fEntityHandler != null) {
            this.fEntityHandler.startEntity(str, this.fResourceIdentifier, str2);
        }
    }

    public Entity.ScannedEntity getCurrentEntity() {
        return this.fCurrentEntity;
    }

    public void closeReaders() {
        for (int size = this.fOwnReaders.size() - 1; size >= 0; size--) {
            try {
                ((Reader) this.fOwnReaders.elementAt(size)).close();
            } catch (IOException e) {
            }
        }
        this.fOwnReaders.removeAllElements();
    }

    public void endEntity() {
        if (this.fEntityHandler != null) {
            this.fEntityHandler.endEntity(this.fCurrentEntity.name);
        }
        if (this.fCurrentEntity != null) {
            try {
                this.fCurrentEntity.close();
            } catch (IOException e) {
                throw new XNIException(e);
            }
        }
        this.fCurrentEntity = this.fEntityStack.size() > 0 ? (Entity.ScannedEntity) this.fEntityStack.pop() : null;
        this.fEntityReader.setCurrentEntity(this.fCurrentEntity);
    }

    public void reset(PropertyManager propertyManager) {
        this.fEntityStorage.reset(propertyManager);
        this.fEntityReader.reset(propertyManager);
        this.fSymbolTable = (SymbolTable) propertyManager.getProperty(SYMBOL_TABLE);
        this.fErrorReporter = (XMLErrorReporter) propertyManager.getProperty(ERROR_REPORTER);
        try {
            this.fStaxEntityResolver = (StaxEntityResolverWrapper) propertyManager.getProperty(STAX_ENTITY_RESOLVER);
        } catch (XMLConfigurationException e) {
            this.fStaxEntityResolver = null;
        }
        this.fEntities.clear();
        this.fEntityStack.removeAllElements();
        this.fCurrentEntity = null;
        this.fValidation = false;
        this.fExternalGeneralEntities = true;
        this.fExternalParameterEntities = true;
        this.fAllowJavaEncodings = true;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public void reset(XMLComponentManager xMLComponentManager) {
        try {
            this.fValidation = xMLComponentManager.getFeature(VALIDATION);
        } catch (XMLConfigurationException e) {
            this.fValidation = false;
        }
        try {
            this.fExternalGeneralEntities = xMLComponentManager.getFeature(EXTERNAL_GENERAL_ENTITIES);
        } catch (XMLConfigurationException e2) {
            this.fExternalGeneralEntities = true;
        }
        try {
            this.fExternalParameterEntities = xMLComponentManager.getFeature(EXTERNAL_PARAMETER_ENTITIES);
        } catch (XMLConfigurationException e3) {
            this.fExternalParameterEntities = true;
        }
        try {
            this.fAllowJavaEncodings = xMLComponentManager.getFeature(ALLOW_JAVA_ENCODINGS);
        } catch (XMLConfigurationException e4) {
            this.fAllowJavaEncodings = false;
        }
        this.fSymbolTable = (SymbolTable) xMLComponentManager.getProperty(SYMBOL_TABLE);
        this.fErrorReporter = (XMLErrorReporter) xMLComponentManager.getProperty(ERROR_REPORTER);
        try {
            this.fEntityResolver = (XMLEntityResolver) xMLComponentManager.getProperty(ENTITY_RESOLVER);
        } catch (XMLConfigurationException e5) {
            this.fEntityResolver = null;
        }
        try {
            this.fStaxEntityResolver = (StaxEntityResolverWrapper) xMLComponentManager.getProperty(STAX_ENTITY_RESOLVER);
        } catch (XMLConfigurationException e6) {
            this.fStaxEntityResolver = null;
        }
        this.fStandalone = false;
        this.fEntities.clear();
        this.fEntityStack.removeAllElements();
        this.fCurrentEntity = null;
        if (this.fDeclaredEntities != null) {
            Enumeration enumerationKeys = this.fDeclaredEntities.keys();
            while (enumerationKeys.hasMoreElements()) {
                Object objNextElement = enumerationKeys.nextElement();
                this.fEntities.put(objNextElement, this.fDeclaredEntities.get(objNextElement));
            }
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public String[] getRecognizedFeatures() {
        return (String[]) RECOGNIZED_FEATURES.clone();
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public void setFeature(String str, boolean z) {
        if (str.startsWith(Constants.XERCES_FEATURE_PREFIX) && str.substring(Constants.XERCES_FEATURE_PREFIX.length()).equals(Constants.ALLOW_JAVA_ENCODINGS_FEATURE)) {
            this.fAllowJavaEncodings = z;
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponent
    public void setProperty(String str, Object obj) {
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

    public static String expandSystemId(String str) {
        return expandSystemId(str, null);
    }

    private static synchronized String getUserDir() {
        String strReplace;
        char upperCase;
        synchronized (XMLEntityManager.class) {
            String property = "";
            try {
                property = System.getProperty("user.dir");
            } catch (SecurityException e) {
            }
            if (property.length() == 0) {
                strReplace = "";
            } else if (property.equals(gUserDir)) {
                strReplace = gEscapedUserDir;
            } else {
                gUserDir = property;
                strReplace = property.replace(File.separatorChar, '/');
                int length = strReplace.length();
                StringBuffer stringBuffer = new StringBuffer(length * 3);
                if (length >= 2 && strReplace.charAt(1) == ':' && (upperCase = Character.toUpperCase(strReplace.charAt(0))) >= 'A' && upperCase <= 'Z') {
                    stringBuffer.append('/');
                }
                int i = 0;
                while (i < length) {
                    char cCharAt = strReplace.charAt(i);
                    if (cCharAt >= 128) {
                        break;
                    }
                    if (gNeedEscaping[cCharAt]) {
                        stringBuffer.append('%');
                        stringBuffer.append(gAfterEscaping1[cCharAt]);
                        stringBuffer.append(gAfterEscaping2[cCharAt]);
                    } else {
                        stringBuffer.append(cCharAt);
                    }
                    i++;
                }
                if (i < length) {
                    try {
                        for (byte b : strReplace.substring(i).getBytes(XMLStreamWriterImpl.UTF_8)) {
                            if (b < 0) {
                                int i2 = b + com.flurry.android.Constants.FEMALE;
                                stringBuffer.append('%');
                                stringBuffer.append(gHexChs[i2 >> 4]);
                                stringBuffer.append(gHexChs[i2 & 15]);
                            } else if (gNeedEscaping[b]) {
                                stringBuffer.append('%');
                                stringBuffer.append(gAfterEscaping1[b]);
                                stringBuffer.append(gAfterEscaping2[b]);
                            } else {
                                stringBuffer.append((char) b);
                            }
                        }
                    } catch (UnsupportedEncodingException e2) {
                    }
                }
                if (!strReplace.endsWith("/")) {
                    stringBuffer.append('/');
                }
                gEscapedUserDir = stringBuffer.toString();
                strReplace = gEscapedUserDir;
            }
        }
        return strReplace;
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0023 A[Catch: Exception -> 0x008b, TryCatch #0 {Exception -> 0x008b, blocks: (B:11:0x0017, B:13:0x001d, B:19:0x003f, B:16:0x0033, B:23:0x004d, B:25:0x0054, B:26:0x0065, B:15:0x0023), top: B:32:0x0017, inners: #1 }] */
    public static String expandSystemId(String str, String str2) {
        URI uri;
        URI uri2;
        if (str != null && str.length() != 0) {
            try {
                if (new URI(str) != null) {
                    return str;
                }
            } catch (URI.MalformedURIException e) {
            }
            String strFixURI = fixURI(str);
            if (str2 == null) {
                uri = new URI("file", "", getUserDir(), null, null);
                uri2 = new URI(uri, strFixURI);
            } else {
                try {
                    if (str2.length() == 0 || str2.equals(str)) {
                        uri = new URI("file", "", getUserDir(), null, null);
                    } else {
                        try {
                            uri = new URI(fixURI(str2));
                        } catch (URI.MalformedURIException e2) {
                            if (str2.indexOf(58) != -1) {
                                uri = new URI("file", "", fixURI(str2), null, null);
                            } else {
                                uri = new URI("file", "", new StringBuffer().append(getUserDir()).append(fixURI(str2)).toString(), null, null);
                            }
                        }
                    }
                    uri2 = new URI(uri, strFixURI);
                } catch (Exception e3) {
                    uri2 = null;
                }
            }
            return uri2 != null ? uri2.toString() : str;
        }
        return str;
    }

    protected Object[] getEncodingName(byte[] bArr, int i) {
        if (i < 2) {
            return this.defaultEncoding;
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
            return this.defaultEncoding;
        }
        int i4 = bArr[2] & com.flurry.android.Constants.UNKNOWN;
        if (i2 == 239 && i3 == 187 && i4 == 191) {
            return this.defaultEncoding;
        }
        if (i < 4) {
            return this.defaultEncoding;
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
        return this.defaultEncoding;
    }

    protected Reader createReader(InputStream inputStream, String str, Boolean bool) {
        if (str == null) {
            str = XMLStreamWriterImpl.UTF_8;
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        if (upperCase.equals(XMLStreamWriterImpl.UTF_8)) {
            return new UTF8Reader(inputStream, this.fBufferSize, this.fErrorReporter.getMessageFormatter(XMLMessageFormatter.XML_DOMAIN), this.fErrorReporter.getLocale());
        }
        if (upperCase.equals("US-ASCII")) {
            return new ASCIIReader(inputStream, this.fBufferSize, this.fErrorReporter.getMessageFormatter(XMLMessageFormatter.XML_DOMAIN), this.fErrorReporter.getLocale());
        }
        if (upperCase.equals("ISO-10646-UCS-4")) {
            if (bool != null) {
                if (bool.booleanValue()) {
                    return new UCSReader(inputStream, (short) 8);
                }
                return new UCSReader(inputStream, (short) 4);
            }
            this.fErrorReporter.reportError(getEntityReader(), XMLMessageFormatter.XML_DOMAIN, "EncodingByteOrderUnsupported", new Object[]{str}, (short) 2);
        }
        if (upperCase.equals("ISO-10646-UCS-2")) {
            if (bool != null) {
                if (bool.booleanValue()) {
                    return new UCSReader(inputStream, (short) 2);
                }
                return new UCSReader(inputStream, (short) 1);
            }
            this.fErrorReporter.reportError(getEntityReader(), XMLMessageFormatter.XML_DOMAIN, "EncodingByteOrderUnsupported", new Object[]{str}, (short) 2);
        }
        boolean zIsValidIANAEncoding = XMLChar.isValidIANAEncoding(str);
        boolean zIsValidJavaEncoding = XMLChar.isValidJavaEncoding(str);
        if (!zIsValidIANAEncoding || (this.fAllowJavaEncodings && !zIsValidJavaEncoding)) {
            this.fErrorReporter.reportError(getEntityReader(), XMLMessageFormatter.XML_DOMAIN, "EncodingDeclInvalid", new Object[]{str}, (short) 2);
            str = "ISO-8859-1";
        }
        String iANA2JavaMapping = EncodingMap.getIANA2JavaMapping(upperCase);
        if (iANA2JavaMapping != null) {
            str = iANA2JavaMapping;
        } else if (!this.fAllowJavaEncodings) {
            this.fErrorReporter.reportError(getEntityReader(), XMLMessageFormatter.XML_DOMAIN, "EncodingDeclInvalid", new Object[]{str}, (short) 2);
            str = "ISO8859_1";
        }
        return new BufferedReader(new InputStreamReader(inputStream, str));
    }

    public String getPublicId() {
        if (this.fCurrentEntity == null || this.fCurrentEntity.entityLocation == null) {
            return null;
        }
        return this.fCurrentEntity.entityLocation.getPublicId();
    }

    public String getExpandedSystemId() {
        if (this.fCurrentEntity != null) {
            if (this.fCurrentEntity.entityLocation != null && this.fCurrentEntity.entityLocation.getExpandedSystemId() != null) {
                return this.fCurrentEntity.entityLocation.getExpandedSystemId();
            }
            for (int size = this.fEntityStack.size() - 1; size >= 0; size--) {
                Entity.ScannedEntity scannedEntity = (Entity.ScannedEntity) this.fEntityStack.elementAt(size);
                if (scannedEntity.entityLocation != null && scannedEntity.entityLocation.getExpandedSystemId() != null) {
                    return scannedEntity.entityLocation.getExpandedSystemId();
                }
            }
        }
        return null;
    }

    public String getLiteralSystemId() {
        if (this.fCurrentEntity != null) {
            if (this.fCurrentEntity.entityLocation != null && this.fCurrentEntity.entityLocation.getLiteralSystemId() != null) {
                return this.fCurrentEntity.entityLocation.getLiteralSystemId();
            }
            for (int size = this.fEntityStack.size() - 1; size >= 0; size--) {
                Entity.ScannedEntity scannedEntity = (Entity.ScannedEntity) this.fEntityStack.elementAt(size);
                if (scannedEntity.entityLocation != null && scannedEntity.entityLocation.getLiteralSystemId() != null) {
                    return scannedEntity.entityLocation.getLiteralSystemId();
                }
            }
        }
        return null;
    }

    public int getLineNumber() {
        if (this.fCurrentEntity != null) {
            if (this.fCurrentEntity.isExternal()) {
                return this.fCurrentEntity.lineNumber;
            }
            for (int size = this.fEntityStack.size() - 1; size > 0; size--) {
                Entity.ScannedEntity scannedEntity = (Entity.ScannedEntity) this.fEntityStack.elementAt(size);
                if (scannedEntity.isExternal()) {
                    return scannedEntity.lineNumber;
                }
            }
        }
        return -1;
    }

    public int getColumnNumber() {
        if (this.fCurrentEntity != null) {
            if (this.fCurrentEntity.isExternal()) {
                return this.fCurrentEntity.columnNumber;
            }
            for (int size = this.fEntityStack.size() - 1; size > 0; size--) {
                Entity.ScannedEntity scannedEntity = (Entity.ScannedEntity) this.fEntityStack.elementAt(size);
                if (scannedEntity.isExternal()) {
                    return scannedEntity.columnNumber;
                }
            }
        }
        return -1;
    }

    protected static String fixURI(String str) {
        String strReplace = str.replace(File.separatorChar, '/');
        if (strReplace.length() >= 2) {
            char cCharAt = strReplace.charAt(1);
            if (cCharAt == ':') {
                char upperCase = Character.toUpperCase(strReplace.charAt(0));
                if (upperCase >= 'A' && upperCase <= 'Z') {
                    return new StringBuffer().append("/").append(strReplace).toString();
                }
                return strReplace;
            }
            if (cCharAt == '/' && strReplace.charAt(0) == '/') {
                return new StringBuffer().append("file:").append(strReplace).toString();
            }
            return strReplace;
        }
        return strReplace;
    }

    final void print() {
    }

    public final class RewindableInputStream extends InputStream {
        private InputStream fInputStream;
        private byte[] fData = new byte[64];
        private int fStartOffset = 0;
        private int fEndOffset = -1;
        private int fOffset = 0;
        private int fLength = 0;
        private int fMark = 0;

        public RewindableInputStream(InputStream inputStream) {
            this.fInputStream = inputStream;
        }

        public void setStartOffset(int i) {
            this.fStartOffset = i;
        }

        public void rewind() {
            this.fOffset = this.fStartOffset;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            if (this.fOffset < this.fLength) {
                byte[] bArr = this.fData;
                int i = this.fOffset;
                this.fOffset = i + 1;
                return bArr[i] & com.flurry.android.Constants.UNKNOWN;
            }
            if (this.fOffset == this.fEndOffset) {
                return -1;
            }
            if (this.fOffset == this.fData.length) {
                byte[] bArr2 = new byte[this.fOffset << 1];
                System.arraycopy(this.fData, 0, bArr2, 0, this.fOffset);
                this.fData = bArr2;
            }
            int i2 = this.fInputStream.read();
            if (i2 == -1) {
                this.fEndOffset = this.fOffset;
                return -1;
            }
            byte[] bArr3 = this.fData;
            int i3 = this.fLength;
            this.fLength = i3 + 1;
            bArr3[i3] = (byte) i2;
            this.fOffset++;
            return i2 & 255;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) {
            int i3 = this.fLength - this.fOffset;
            if (i3 == 0) {
                if (this.fOffset == this.fEndOffset) {
                    return -1;
                }
                return this.fInputStream.read(bArr, i, i2);
            }
            if (i2 < i3) {
                if (i2 <= 0) {
                    return 0;
                }
                i3 = i2;
            }
            if (bArr != null) {
                System.arraycopy(this.fData, this.fOffset, bArr, i, i3);
            }
            this.fOffset += i3;
            return i3;
        }

        @Override // java.io.InputStream
        public long skip(long j) {
            if (j <= 0) {
                return 0L;
            }
            int i = this.fLength - this.fOffset;
            if (i == 0) {
                if (this.fOffset == this.fEndOffset) {
                    return 0L;
                }
                return this.fInputStream.skip(j);
            }
            if (j <= i) {
                this.fOffset = (int) (((long) this.fOffset) + j);
                return j;
            }
            this.fOffset += i;
            if (this.fOffset == this.fEndOffset) {
                return i;
            }
            return this.fInputStream.skip(j - ((long) i)) + ((long) i);
        }

        @Override // java.io.InputStream
        public int available() {
            int i = this.fLength - this.fOffset;
            if (i == 0) {
                if (this.fOffset == this.fEndOffset) {
                    return -1;
                }
                if (XMLEntityManager.this.fCurrentEntity.mayReadChunks) {
                    return this.fInputStream.available();
                }
                return 0;
            }
            return i;
        }

        @Override // java.io.InputStream
        public void mark(int i) {
            this.fMark = this.fOffset;
        }

        @Override // java.io.InputStream
        public void reset() {
            this.fOffset = this.fMark;
        }

        @Override // java.io.InputStream
        public boolean markSupported() {
            return true;
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.fInputStream != null) {
                this.fInputStream.close();
                this.fInputStream = null;
            }
        }
    }

    public void test() {
        this.fEntityStorage.addExternalEntity("entityUsecase1", null, "/space/home/stax/sun/6thJan2004/zephyr/data/test.txt", "/space/home/stax/sun/6thJan2004/zephyr/data/entity.xml");
        this.fEntityStorage.addInternalEntity("entityUsecase2", "<Test>value</Test>");
        this.fEntityStorage.addInternalEntity("entityUsecase3", "value3");
        this.fEntityStorage.addInternalEntity("text", "Hello World.");
        this.fEntityStorage.addInternalEntity("empty-element", "<foo/>");
        this.fEntityStorage.addInternalEntity("balanced-element", "<foo></foo>");
        this.fEntityStorage.addInternalEntity("balanced-element-with-text", "<foo>Hello, World</foo>");
        this.fEntityStorage.addInternalEntity("balanced-element-with-entity", "<foo>&text;</foo>");
        this.fEntityStorage.addInternalEntity("unbalanced-entity", "<foo>");
        this.fEntityStorage.addInternalEntity("recursive-entity", "<foo>&recursive-entity2;</foo>");
        this.fEntityStorage.addInternalEntity("recursive-entity2", "<bar>&recursive-entity3;</bar>");
        this.fEntityStorage.addInternalEntity("recursive-entity3", "<baz>&recursive-entity;</baz>");
        this.fEntityStorage.addInternalEntity("ch", "&#x00A9;");
        this.fEntityStorage.addInternalEntity("ch1", "&#84;");
        this.fEntityStorage.addInternalEntity("% ch2", "param");
    }
}

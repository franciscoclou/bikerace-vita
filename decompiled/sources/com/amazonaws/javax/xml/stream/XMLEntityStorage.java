package com.amazonaws.javax.xml.stream;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import com.amazonaws.javax.xml.stream.xerces.impl.msg.XMLMessageFormatter;
import com.amazonaws.javax.xml.stream.xerces.util.URI;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import com.amazonaws.javax.xml.stream.xerces.util.XMLResourceIdentifierImpl;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponentManager;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLConfigurationException;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class XMLEntityStorage {
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String WARN_ON_DUPLICATE_ENTITYDEF = "http://apache.org/xml/features/warn-on-duplicate-entitydef";
    private static String gEscapedUserDir;
    private static String gUserDir;
    protected Entity.ScannedEntity fCurrentEntity;
    protected Hashtable fEntities = new Hashtable();
    private XMLEntityManager fEntityManager;
    protected XMLErrorReporter fErrorReporter;
    protected PropertyManager fPropertyManager;
    protected boolean fWarnDuplicateEntityDef;
    private static boolean[] gNeedEscaping = new boolean[XMLChar.MASK_NCNAME];
    private static char[] gAfterEscaping1 = new char[XMLChar.MASK_NCNAME];
    private static char[] gAfterEscaping2 = new char[XMLChar.MASK_NCNAME];
    private static char[] gHexChs = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public XMLEntityStorage(PropertyManager propertyManager) {
        this.fPropertyManager = propertyManager;
    }

    public XMLEntityStorage(XMLEntityManager xMLEntityManager) {
        this.fEntityManager = xMLEntityManager;
    }

    public void reset(PropertyManager propertyManager) {
        this.fErrorReporter = (XMLErrorReporter) propertyManager.getProperty(ERROR_REPORTER);
        this.fEntities.clear();
        this.fCurrentEntity = null;
    }

    public void reset() {
        this.fEntities.clear();
        this.fCurrentEntity = null;
    }

    public void reset(XMLComponentManager xMLComponentManager) {
        try {
            this.fWarnDuplicateEntityDef = xMLComponentManager.getFeature(WARN_ON_DUPLICATE_ENTITYDEF);
        } catch (XMLConfigurationException e) {
            this.fWarnDuplicateEntityDef = false;
        }
        this.fErrorReporter = (XMLErrorReporter) xMLComponentManager.getProperty(ERROR_REPORTER);
        this.fEntities.clear();
        this.fCurrentEntity = null;
    }

    public Hashtable getDeclaredEntities() {
        return this.fEntities;
    }

    public void addInternalEntity(String str, String str2) {
        if (!this.fEntities.containsKey(str)) {
            this.fCurrentEntity = this.fEntityManager.getCurrentEntity();
            this.fEntities.put(str, new Entity.InternalEntity(str, str2, false));
        } else if (this.fWarnDuplicateEntityDef) {
            this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "MSG_DUPLICATE_ENTITY_DEFINITION", new Object[]{str}, (short) 0);
        }
    }

    public void addExternalEntity(String str, String str2, String str3, String str4) {
        if (!this.fEntities.containsKey(str)) {
            if (str4 == null && this.fCurrentEntity != null && this.fCurrentEntity.entityLocation != null) {
                str4 = this.fCurrentEntity.entityLocation.getExpandedSystemId();
            }
            this.fCurrentEntity = this.fEntityManager.getCurrentEntity();
            this.fEntities.put(str, new Entity.ExternalEntity(str, new XMLResourceIdentifierImpl(str2, str3, str4, expandSystemId(str3, str4)), null, true));
            return;
        }
        if (this.fWarnDuplicateEntityDef) {
            this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "MSG_DUPLICATE_ENTITY_DEFINITION", new Object[]{str}, (short) 0);
        }
    }

    public boolean isExternalEntity(String str) {
        Entity entity = (Entity) this.fEntities.get(str);
        if (entity == null) {
            return false;
        }
        return entity.isExternal();
    }

    public boolean isEntityDeclInExternalSubset(String str) {
        Entity entity = (Entity) this.fEntities.get(str);
        if (entity == null) {
            return false;
        }
        return entity.isEntityDeclInExternalSubset();
    }

    public void addUnparsedEntity(String str, String str2, String str3, String str4, String str5) {
        this.fCurrentEntity = this.fEntityManager.getCurrentEntity();
        if (!this.fEntities.containsKey(str)) {
            this.fEntities.put(str, new Entity.ExternalEntity(str, new XMLResourceIdentifierImpl(str2, str3, str4, null), str5, false));
        } else if (this.fWarnDuplicateEntityDef) {
            this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "MSG_DUPLICATE_ENTITY_DEFINITION", new Object[]{str}, (short) 0);
        }
    }

    public boolean isUnparsedEntity(String str) {
        Entity entity = (Entity) this.fEntities.get(str);
        if (entity == null) {
            return false;
        }
        return entity.isUnparsed();
    }

    public boolean isDeclaredEntity(String str) {
        return ((Entity) this.fEntities.get(str)) != null;
    }

    public static String expandSystemId(String str) {
        return expandSystemId(str, null);
    }

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

    private static synchronized String getUserDir() {
        String strReplace;
        char upperCase;
        synchronized (XMLEntityStorage.class) {
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
}

package com.amazonaws.javax.xml.stream.dtd.nonvalidating;

import com.amazonaws.javax.xml.stream.xerces.util.SymbolTable;
import com.amazonaws.javax.xml.stream.xerces.util.XMLSymbols;
import com.amazonaws.javax.xml.stream.xerces.xni.Augmentations;
import com.amazonaws.javax.xml.stream.xerces.xni.QName;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLLocator;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLResourceIdentifier;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLString;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLDTDContentModelSource;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLDTDSource;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class DTDGrammar {
    private static final int CHUNK_MASK = 255;
    private static final int CHUNK_SHIFT = 8;
    private static final int CHUNK_SIZE = 256;
    private static final boolean DEBUG = false;
    private static final int INITIAL_CHUNK_COUNT = 4;
    private static final short LIST_FLAG = 128;
    private static final short LIST_MASK = -129;
    public static final int TOP_LEVEL_SCOPE = -1;
    protected int fCurrentAttributeIndex;
    protected int fCurrentElementIndex;
    private SymbolTable fSymbolTable;
    protected XMLDTDSource fDTDSource = null;
    protected XMLDTDContentModelSource fDTDContentModelSource = null;
    protected boolean fReadingExternalDTD = DEBUG;
    private ArrayList notationDecls = new ArrayList();
    private int fElementDeclCount = 0;
    private QName[][] fElementDeclName = new QName[4][];
    private short[][] fElementDeclType = new short[4][];
    private int[][] fElementDeclFirstAttributeDeclIndex = new int[4][];
    private int[][] fElementDeclLastAttributeDeclIndex = new int[4][];
    private int fAttributeDeclCount = 0;
    private QName[][] fAttributeDeclName = new QName[4][];
    private boolean fIsImmutable = DEBUG;
    private short[][] fAttributeDeclType = new short[4][];
    private String[][][] fAttributeDeclEnumeration = new String[4][][];
    private short[][] fAttributeDeclDefaultType = new short[4][];
    private String[][] fAttributeDeclDefaultValue = new String[4][];
    private String[][] fAttributeDeclNonNormalizedDefaultValue = new String[4][];
    private int[][] fAttributeDeclNextAttributeDeclIndex = new int[4][];
    private QNameHashtable fElementIndexMap = new QNameHashtable();
    private QName fQName = new QName();
    private QName fQName2 = new QName();
    protected XMLAttributeDecl fAttributeDecl = new XMLAttributeDecl();
    private int fLeafCount = 0;
    private int fEpsilonIndex = -1;
    private XMLElementDecl fElementDecl = new XMLElementDecl();
    private XMLSimpleType fSimpleType = new XMLSimpleType();
    Hashtable fElementDeclTab = new Hashtable();
    private short[] fOpStack = null;
    private int[] fNodeIndexStack = null;
    private int[] fPrevNodeIndexStack = null;
    private int fDepth = 0;
    int valueIndex = -1;
    int prevNodeIndex = -1;
    int nodeIndex = -1;

    public DTDGrammar(SymbolTable symbolTable) {
        this.fSymbolTable = symbolTable;
    }

    public int getAttributeDeclIndex(int i, String str) {
        if (i == -1) {
            return -1;
        }
        int firstAttributeDeclIndex = getFirstAttributeDeclIndex(i);
        while (firstAttributeDeclIndex != -1) {
            getAttributeDecl(firstAttributeDeclIndex, this.fAttributeDecl);
            if (this.fAttributeDecl.name.rawname == str || str.equals(this.fAttributeDecl.name.rawname)) {
                return firstAttributeDeclIndex;
            }
            firstAttributeDeclIndex = getNextAttributeDeclIndex(firstAttributeDeclIndex);
        }
        return -1;
    }

    public void startDTD(XMLLocator xMLLocator, Augmentations augmentations) {
        this.fOpStack = null;
        this.fNodeIndexStack = null;
        this.fPrevNodeIndexStack = null;
    }

    public void elementDecl(String str, String str2, Augmentations augmentations) {
        XMLElementDecl xMLElementDecl = (XMLElementDecl) this.fElementDeclTab.get(str);
        if (xMLElementDecl != null) {
            if (xMLElementDecl.type == -1) {
                this.fCurrentElementIndex = getElementDeclIndex(str);
            } else {
                return;
            }
        } else {
            this.fCurrentElementIndex = createElementDecl();
        }
        XMLElementDecl xMLElementDecl2 = new XMLElementDecl();
        xMLElementDecl2.name.setValues(new QName(null, str, str, null));
        xMLElementDecl2.scope = -1;
        if (str2.equals("EMPTY")) {
            xMLElementDecl2.type = (short) 1;
        } else if (str2.equals("ANY")) {
            xMLElementDecl2.type = (short) 0;
        } else if (str2.startsWith("(")) {
            if (str2.indexOf("#PCDATA") > 0) {
                xMLElementDecl2.type = (short) 2;
            } else {
                xMLElementDecl2.type = (short) 3;
            }
        }
        this.fElementDeclTab.put(str, xMLElementDecl2);
        this.fElementDecl = xMLElementDecl2;
        setElementDecl(this.fCurrentElementIndex, this.fElementDecl);
        int i = this.fCurrentElementIndex >> 8;
        int i2 = this.fCurrentElementIndex & CHUNK_MASK;
        ensureElementDeclCapacity(i);
    }

    public void attributeDecl(String str, String str2, String str3, String[] strArr, String str4, XMLString xMLString, XMLString xMLString2, Augmentations augmentations) {
        if (str3 != XMLSymbols.fCDATASymbol && xMLString != null) {
            normalizeDefaultAttrValue(xMLString);
        }
        if (!this.fElementDeclTab.containsKey(str)) {
            this.fCurrentElementIndex = createElementDecl();
            XMLElementDecl xMLElementDecl = new XMLElementDecl();
            xMLElementDecl.name.setValues(null, str, str, null);
            xMLElementDecl.scope = -1;
            this.fElementDeclTab.put(str, xMLElementDecl);
            setElementDecl(this.fCurrentElementIndex, xMLElementDecl);
        }
        int elementDeclIndex = getElementDeclIndex(str);
        if (getAttributeDeclIndex(elementDeclIndex, str2) == -1) {
            this.fCurrentAttributeIndex = createAttributeDecl();
            this.fSimpleType.clear();
            if (str4 != null) {
                if (str4.equals("#FIXED")) {
                    XMLSimpleType xMLSimpleType = this.fSimpleType;
                    XMLSimpleType xMLSimpleType2 = this.fSimpleType;
                    xMLSimpleType.defaultType = (short) 1;
                } else if (str4.equals("#IMPLIED")) {
                    XMLSimpleType xMLSimpleType3 = this.fSimpleType;
                    XMLSimpleType xMLSimpleType4 = this.fSimpleType;
                    xMLSimpleType3.defaultType = (short) 0;
                } else if (str4.equals("#REQUIRED")) {
                    XMLSimpleType xMLSimpleType5 = this.fSimpleType;
                    XMLSimpleType xMLSimpleType6 = this.fSimpleType;
                    xMLSimpleType5.defaultType = (short) 2;
                }
            }
            this.fSimpleType.defaultValue = xMLString != null ? xMLString.toString() : null;
            this.fSimpleType.nonNormalizedDefaultValue = xMLString2 != null ? xMLString2.toString() : null;
            this.fSimpleType.enumeration = strArr;
            if (str3.equals("CDATA")) {
                this.fSimpleType.type = (short) 0;
            } else if (str3.equals("ID")) {
                this.fSimpleType.type = (short) 3;
            } else if (str3.startsWith("IDREF")) {
                this.fSimpleType.type = (short) 4;
                if (str3.indexOf("S") > 0) {
                    this.fSimpleType.list = true;
                }
            } else if (str3.equals("ENTITIES")) {
                this.fSimpleType.type = (short) 1;
                this.fSimpleType.list = true;
            } else if (str3.equals("ENTITY")) {
                this.fSimpleType.type = (short) 1;
            } else if (str3.equals("NMTOKENS")) {
                this.fSimpleType.type = (short) 5;
                this.fSimpleType.list = true;
            } else if (str3.equals("NMTOKEN")) {
                this.fSimpleType.type = (short) 5;
            } else if (str3.startsWith("NOTATION")) {
                this.fSimpleType.type = (short) 6;
            } else if (str3.startsWith("ENUMERATION")) {
                this.fSimpleType.type = (short) 2;
            } else {
                System.err.println(new StringBuffer().append("!!! unknown attribute type ").append(str3).toString());
            }
            this.fQName.setValues(null, str2, str2, null);
            this.fAttributeDecl.setValues(this.fQName, this.fSimpleType, DEBUG);
            setAttributeDecl(elementDeclIndex, this.fCurrentAttributeIndex, this.fAttributeDecl);
            int i = this.fCurrentAttributeIndex >> 8;
            int i2 = this.fCurrentAttributeIndex & CHUNK_MASK;
            ensureAttributeDeclCapacity(i);
        }
    }

    public SymbolTable getSymbolTable() {
        return this.fSymbolTable;
    }

    public int getFirstElementDeclIndex() {
        return this.fElementDeclCount >= 0 ? 0 : -1;
    }

    public int getNextElementDeclIndex(int i) {
        if (i < this.fElementDeclCount - 1) {
            return i + 1;
        }
        return -1;
    }

    public int getElementDeclIndex(String str) {
        return this.fElementIndexMap.get(str);
    }

    public int getElementDeclIndex(QName qName) {
        return getElementDeclIndex(qName.rawname);
    }

    public short getContentSpecType(int i) {
        if (i < 0 || i >= this.fElementDeclCount) {
            return (short) -1;
        }
        int i2 = i >> 8;
        int i3 = i & CHUNK_MASK;
        if (this.fElementDeclType[i2][i3] != -1) {
            return (short) (this.fElementDeclType[i2][i3] & LIST_MASK);
        }
        return (short) -1;
    }

    public boolean getElementDecl(int i, XMLElementDecl xMLElementDecl) {
        boolean z = DEBUG;
        if (i < 0 || i >= this.fElementDeclCount) {
            return DEBUG;
        }
        int i2 = i >> 8;
        int i3 = i & CHUNK_MASK;
        xMLElementDecl.name.setValues(this.fElementDeclName[i2][i3]);
        if (this.fElementDeclType[i2][i3] == -1) {
            xMLElementDecl.type = (short) -1;
            xMLElementDecl.simpleType.list = DEBUG;
        } else {
            xMLElementDecl.type = (short) (this.fElementDeclType[i2][i3] & LIST_MASK);
            XMLSimpleType xMLSimpleType = xMLElementDecl.simpleType;
            if ((this.fElementDeclType[i2][i3] & LIST_FLAG) != 0) {
                z = true;
            }
            xMLSimpleType.list = z;
        }
        xMLElementDecl.simpleType.defaultType = (short) -1;
        xMLElementDecl.simpleType.defaultValue = null;
        return true;
    }

    public int getFirstAttributeDeclIndex(int i) {
        return this.fElementDeclFirstAttributeDeclIndex[i >> 8][i & CHUNK_MASK];
    }

    public int getNextAttributeDeclIndex(int i) {
        return this.fAttributeDeclNextAttributeDeclIndex[i >> 8][i & CHUNK_MASK];
    }

    public boolean getAttributeDecl(int i, XMLAttributeDecl xMLAttributeDecl) {
        boolean z;
        short s = -1;
        boolean z2 = DEBUG;
        if (i < 0 || i >= this.fAttributeDeclCount) {
            return DEBUG;
        }
        int i2 = i >> 8;
        int i3 = i & CHUNK_MASK;
        xMLAttributeDecl.name.setValues(this.fAttributeDeclName[i2][i3]);
        if (this.fAttributeDeclType[i2][i3] == -1) {
            z = false;
        } else {
            s = (short) (this.fAttributeDeclType[i2][i3] & LIST_MASK);
            if ((this.fAttributeDeclType[i2][i3] & LIST_FLAG) != 0) {
                z2 = true;
            }
            z = z2;
        }
        xMLAttributeDecl.simpleType.setValues(s, this.fAttributeDeclName[i2][i3].localpart, this.fAttributeDeclEnumeration[i2][i3], z, this.fAttributeDeclDefaultType[i2][i3], this.fAttributeDeclDefaultValue[i2][i3], this.fAttributeDeclNonNormalizedDefaultValue[i2][i3]);
        return true;
    }

    public boolean isCDATAAttribute(QName qName, QName qName2) {
        int elementDeclIndex = getElementDeclIndex(qName);
        getAttributeDeclIndex(elementDeclIndex, qName2.rawname);
        if (!getAttributeDecl(elementDeclIndex, this.fAttributeDecl) || this.fAttributeDecl.simpleType.type == 0) {
            return true;
        }
        return DEBUG;
    }

    public void printElements() {
        int i = 0;
        XMLElementDecl xMLElementDecl = new XMLElementDecl();
        while (true) {
            int i2 = i + 1;
            if (getElementDecl(i, xMLElementDecl)) {
                System.out.println(new StringBuffer().append("element decl: ").append(xMLElementDecl.name).append(", ").append(xMLElementDecl.name.rawname).toString());
                i = i2;
            } else {
                return;
            }
        }
    }

    public void printAttributes(int i) {
        int firstAttributeDeclIndex = getFirstAttributeDeclIndex(i);
        System.out.print(i);
        System.out.print(" [");
        while (firstAttributeDeclIndex != -1) {
            System.out.print(' ');
            System.out.print(firstAttributeDeclIndex);
            printAttribute(firstAttributeDeclIndex);
            firstAttributeDeclIndex = getNextAttributeDeclIndex(firstAttributeDeclIndex);
            if (firstAttributeDeclIndex != -1) {
                System.out.print(",");
            }
        }
        System.out.println(" ]");
    }

    protected int createElementDecl() {
        int i = this.fElementDeclCount >> 8;
        int i2 = this.fElementDeclCount & CHUNK_MASK;
        ensureElementDeclCapacity(i);
        this.fElementDeclName[i][i2] = new QName();
        this.fElementDeclType[i][i2] = -1;
        this.fElementDeclFirstAttributeDeclIndex[i][i2] = -1;
        this.fElementDeclLastAttributeDeclIndex[i][i2] = -1;
        int i3 = this.fElementDeclCount;
        this.fElementDeclCount = i3 + 1;
        return i3;
    }

    protected void setElementDecl(int i, XMLElementDecl xMLElementDecl) {
        if (i >= 0 && i < this.fElementDeclCount) {
            int i2 = i >> 8;
            int i3 = i & CHUNK_MASK;
            int i4 = xMLElementDecl.scope;
            this.fElementDeclName[i2][i3].setValues(xMLElementDecl.name);
            this.fElementDeclType[i2][i3] = xMLElementDecl.type;
            if (xMLElementDecl.simpleType.list) {
                short[] sArr = this.fElementDeclType[i2];
                sArr[i3] = (short) (sArr[i3] | LIST_FLAG);
            }
            this.fElementIndexMap.put(xMLElementDecl.name.rawname, i);
        }
    }

    protected void setFirstAttributeDeclIndex(int i, int i2) {
        if (i >= 0 && i < this.fElementDeclCount) {
            this.fElementDeclFirstAttributeDeclIndex[i >> 8][i & CHUNK_MASK] = i2;
        }
    }

    protected int createAttributeDecl() {
        int i = this.fAttributeDeclCount >> 8;
        int i2 = this.fAttributeDeclCount & CHUNK_MASK;
        ensureAttributeDeclCapacity(i);
        this.fAttributeDeclName[i][i2] = new QName();
        this.fAttributeDeclType[i][i2] = -1;
        this.fAttributeDeclEnumeration[i][i2] = null;
        this.fAttributeDeclDefaultType[i][i2] = 0;
        this.fAttributeDeclDefaultValue[i][i2] = null;
        this.fAttributeDeclNonNormalizedDefaultValue[i][i2] = null;
        this.fAttributeDeclNextAttributeDeclIndex[i][i2] = -1;
        int i3 = this.fAttributeDeclCount;
        this.fAttributeDeclCount = i3 + 1;
        return i3;
    }

    protected void setAttributeDecl(int i, int i2, XMLAttributeDecl xMLAttributeDecl) {
        int i3 = i2 >> 8;
        int i4 = i2 & CHUNK_MASK;
        this.fAttributeDeclName[i3][i4].setValues(xMLAttributeDecl.name);
        this.fAttributeDeclType[i3][i4] = xMLAttributeDecl.simpleType.type;
        if (xMLAttributeDecl.simpleType.list) {
            short[] sArr = this.fAttributeDeclType[i3];
            sArr[i4] = (short) (sArr[i4] | LIST_FLAG);
        }
        this.fAttributeDeclEnumeration[i3][i4] = xMLAttributeDecl.simpleType.enumeration;
        this.fAttributeDeclDefaultType[i3][i4] = xMLAttributeDecl.simpleType.defaultType;
        this.fAttributeDeclDefaultValue[i3][i4] = xMLAttributeDecl.simpleType.defaultValue;
        this.fAttributeDeclNonNormalizedDefaultValue[i3][i4] = xMLAttributeDecl.simpleType.nonNormalizedDefaultValue;
        int i5 = i >> 8;
        int i6 = i & CHUNK_MASK;
        int i7 = this.fElementDeclFirstAttributeDeclIndex[i5][i6];
        while (i7 != -1 && i7 != i2) {
            int i8 = i7 >> 8;
            i7 = this.fAttributeDeclNextAttributeDeclIndex[i8][i7 & CHUNK_MASK];
        }
        if (i7 == -1) {
            if (this.fElementDeclFirstAttributeDeclIndex[i5][i6] == -1) {
                this.fElementDeclFirstAttributeDeclIndex[i5][i6] = i2;
            } else {
                int i9 = this.fElementDeclLastAttributeDeclIndex[i5][i6];
                int i10 = i9 >> 8;
                this.fAttributeDeclNextAttributeDeclIndex[i10][i9 & CHUNK_MASK] = i2;
            }
            this.fElementDeclLastAttributeDeclIndex[i5][i6] = i2;
        }
    }

    public void notationDecl(String str, XMLResourceIdentifier xMLResourceIdentifier, Augmentations augmentations) {
        XMLNotationDecl xMLNotationDecl = new XMLNotationDecl();
        xMLNotationDecl.setValues(str, xMLResourceIdentifier.getPublicId(), xMLResourceIdentifier.getLiteralSystemId(), xMLResourceIdentifier.getBaseSystemId());
        this.notationDecls.add(xMLNotationDecl);
    }

    public List getNotationDecls() {
        return this.notationDecls;
    }

    private void printAttribute(int i) {
        XMLAttributeDecl xMLAttributeDecl = new XMLAttributeDecl();
        if (getAttributeDecl(i, xMLAttributeDecl)) {
            System.out.print(" { ");
            System.out.print(xMLAttributeDecl.name.localpart);
            System.out.print(" }");
        }
    }

    private void ensureElementDeclCapacity(int i) {
        if (i >= this.fElementDeclName.length) {
            this.fElementDeclName = resize(this.fElementDeclName, this.fElementDeclName.length * 2);
            this.fElementDeclType = resize(this.fElementDeclType, this.fElementDeclType.length * 2);
            this.fElementDeclFirstAttributeDeclIndex = resize(this.fElementDeclFirstAttributeDeclIndex, this.fElementDeclFirstAttributeDeclIndex.length * 2);
            this.fElementDeclLastAttributeDeclIndex = resize(this.fElementDeclLastAttributeDeclIndex, this.fElementDeclLastAttributeDeclIndex.length * 2);
        } else if (this.fElementDeclName[i] != null) {
            return;
        }
        this.fElementDeclName[i] = new QName[CHUNK_SIZE];
        this.fElementDeclType[i] = new short[CHUNK_SIZE];
        this.fElementDeclFirstAttributeDeclIndex[i] = new int[CHUNK_SIZE];
        this.fElementDeclLastAttributeDeclIndex[i] = new int[CHUNK_SIZE];
    }

    private void ensureAttributeDeclCapacity(int i) {
        if (i >= this.fAttributeDeclName.length) {
            this.fAttributeDeclName = resize(this.fAttributeDeclName, this.fAttributeDeclName.length * 2);
            this.fAttributeDeclType = resize(this.fAttributeDeclType, this.fAttributeDeclType.length * 2);
            this.fAttributeDeclEnumeration = resize(this.fAttributeDeclEnumeration, this.fAttributeDeclEnumeration.length * 2);
            this.fAttributeDeclDefaultType = resize(this.fAttributeDeclDefaultType, this.fAttributeDeclDefaultType.length * 2);
            this.fAttributeDeclDefaultValue = resize(this.fAttributeDeclDefaultValue, this.fAttributeDeclDefaultValue.length * 2);
            this.fAttributeDeclNonNormalizedDefaultValue = resize(this.fAttributeDeclNonNormalizedDefaultValue, this.fAttributeDeclNonNormalizedDefaultValue.length * 2);
            this.fAttributeDeclNextAttributeDeclIndex = resize(this.fAttributeDeclNextAttributeDeclIndex, this.fAttributeDeclNextAttributeDeclIndex.length * 2);
        } else if (this.fAttributeDeclName[i] != null) {
            return;
        }
        this.fAttributeDeclName[i] = new QName[CHUNK_SIZE];
        this.fAttributeDeclType[i] = new short[CHUNK_SIZE];
        this.fAttributeDeclEnumeration[i] = new String[CHUNK_SIZE][];
        this.fAttributeDeclDefaultType[i] = new short[CHUNK_SIZE];
        this.fAttributeDeclDefaultValue[i] = new String[CHUNK_SIZE];
        this.fAttributeDeclNonNormalizedDefaultValue[i] = new String[CHUNK_SIZE];
        this.fAttributeDeclNextAttributeDeclIndex[i] = new int[CHUNK_SIZE];
    }

    private static byte[][] resize(byte[][] bArr, int i) {
        byte[][] bArr2 = new byte[i][];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private static short[][] resize(short[][] sArr, int i) {
        short[][] sArr2 = new short[i][];
        System.arraycopy(sArr, 0, sArr2, 0, sArr.length);
        return sArr2;
    }

    private static int[][] resize(int[][] iArr, int i) {
        int[][] iArr2 = new int[i][];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        return iArr2;
    }

    private static Object[][] resize(Object[][] objArr, int i) {
        Object[][] objArr2 = new Object[i][];
        System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
        return objArr2;
    }

    private static QName[][] resize(QName[][] qNameArr, int i) {
        QName[][] qNameArr2 = new QName[i][];
        System.arraycopy(qNameArr, 0, qNameArr2, 0, qNameArr.length);
        return qNameArr2;
    }

    private static String[][] resize(String[][] strArr, int i) {
        String[][] strArr2 = new String[i][];
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        return strArr2;
    }

    private static String[][][] resize(String[][][] strArr, int i) {
        String[][][] strArr2 = new String[i][][];
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        return strArr2;
    }

    public final class QNameHashtable {
        private static final int HASHTABLE_SIZE = 101;
        private static final int INITIAL_BUCKET_SIZE = 4;
        public static final boolean UNIQUE_STRINGS = true;
        private Object[][] fHashTable = new Object[HASHTABLE_SIZE][];

        protected QNameHashtable() {
        }

        public void put(String str, int i) {
            boolean z;
            int iHash = (hash(str) + 2) % HASHTABLE_SIZE;
            Object[] objArr = this.fHashTable[iHash];
            if (objArr == null) {
                Object[] objArr2 = new Object[9];
                objArr2[0] = new int[]{1};
                objArr2[1] = str;
                objArr2[2] = new int[]{i};
                this.fHashTable[iHash] = objArr2;
                return;
            }
            int i2 = ((int[]) objArr[0])[0];
            int i3 = (i2 * 2) + 1;
            if (i3 == objArr.length) {
                Object[] objArr3 = new Object[((i2 + 4) * 2) + 1];
                System.arraycopy(objArr, 0, objArr3, 0, i3);
                this.fHashTable[iHash] = objArr3;
                objArr = objArr3;
            }
            int i4 = 0;
            int i5 = 1;
            while (true) {
                if (i4 >= i2) {
                    z = false;
                    break;
                } else if (((String) objArr[i5]) == str) {
                    ((int[]) objArr[i5 + 1])[0] = i;
                    z = true;
                    break;
                } else {
                    i5 += 2;
                    i4++;
                }
            }
            if (!z) {
                objArr[i3] = str;
                objArr[i3 + 1] = new int[]{i};
                ((int[]) objArr[0])[0] = i2 + 1;
            }
        }

        public int get(String str) {
            Object[] objArr = this.fHashTable[(hash(str) + 2) % HASHTABLE_SIZE];
            if (objArr == null) {
                return -1;
            }
            int i = ((int[]) objArr[0])[0];
            int i2 = 1;
            for (int i3 = 0; i3 < i; i3++) {
                if (((String) objArr[i2]) == str) {
                    return ((int[]) objArr[i2 + 1])[0];
                }
                i2 += 2;
            }
            return -1;
        }

        protected int hash(String str) {
            if (str == null) {
                return 0;
            }
            int length = str.length();
            int iCharAt = 0;
            for (int i = 0; i < length; i++) {
                iCharAt = (iCharAt * 37) + str.charAt(i);
            }
            return 134217727 & iCharAt;
        }
    }

    private boolean normalizeDefaultAttrValue(XMLString xMLString) {
        int i;
        boolean z;
        int i2 = xMLString.length;
        int i3 = xMLString.offset;
        int i4 = xMLString.offset + xMLString.length;
        int i5 = xMLString.offset;
        boolean z2 = true;
        while (i5 < i4) {
            if (xMLString.ch[i5] == ' ') {
                if (z2) {
                    int i6 = i3;
                    z = z2;
                    i = i6;
                } else {
                    i = i3 + 1;
                    xMLString.ch[i3] = ' ';
                    z = true;
                }
            } else {
                if (i3 != i5) {
                    xMLString.ch[i3] = xMLString.ch[i5];
                }
                i = i3 + 1;
                z = false;
            }
            i5++;
            int i7 = i;
            z2 = z;
            i3 = i7;
        }
        if (i3 == i4) {
            return DEBUG;
        }
        if (z2) {
            i3--;
        }
        xMLString.length = i3 - xMLString.offset;
        return true;
    }

    public void endDTD(Augmentations augmentations) {
    }
}

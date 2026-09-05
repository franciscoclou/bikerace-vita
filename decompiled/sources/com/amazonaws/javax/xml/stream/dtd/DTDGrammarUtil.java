package com.amazonaws.javax.xml.stream.dtd;

import com.amazonaws.javax.xml.stream.dtd.nonvalidating.DTDGrammar;
import com.amazonaws.javax.xml.stream.dtd.nonvalidating.XMLAttributeDecl;
import com.amazonaws.javax.xml.stream.xerces.util.NamespaceSupport;
import com.amazonaws.javax.xml.stream.xerces.util.SymbolTable;
import com.amazonaws.javax.xml.stream.xerces.util.XMLChar;
import com.amazonaws.javax.xml.stream.xerces.util.XMLSymbols;
import com.amazonaws.javax.xml.stream.xerces.xni.Augmentations;
import com.amazonaws.javax.xml.stream.xerces.xni.NamespaceContext;
import com.amazonaws.javax.xml.stream.xerces.xni.QName;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLAttributes;
import com.amazonaws.javax.xml.stream.xerces.xni.XMLString;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLComponentManager;
import com.amazonaws.javax.xml.stream.xerces.xni.parser.XMLConfigurationException;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class DTDGrammarUtil {
    private static final boolean DEBUG_ATTRIBUTES = false;
    private static final boolean DEBUG_ELEMENT_CHILDREN = false;
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    private StringBuffer fBuffer;
    private int fCurrentContentSpecType;
    private int fCurrentElementIndex;
    protected DTDGrammar fDTDGrammar;
    private boolean[] fElementContentState;
    private int fElementDepth;
    private boolean fInCDATASection;
    private boolean fInElementContent;
    private NamespaceContext fNamespaceContext;
    protected boolean fNamespaces;
    protected SymbolTable fSymbolTable;
    private XMLAttributeDecl fTempAttDecl;
    private QName fTempQName;

    public DTDGrammarUtil(SymbolTable symbolTable) {
        this.fDTDGrammar = null;
        this.fSymbolTable = null;
        this.fCurrentElementIndex = -1;
        this.fCurrentContentSpecType = -1;
        this.fInCDATASection = false;
        this.fElementContentState = new boolean[8];
        this.fElementDepth = -1;
        this.fInElementContent = false;
        this.fTempAttDecl = new XMLAttributeDecl();
        this.fTempQName = new QName();
        this.fBuffer = new StringBuffer();
        this.fNamespaceContext = null;
        this.fSymbolTable = symbolTable;
    }

    public DTDGrammarUtil(DTDGrammar dTDGrammar, SymbolTable symbolTable) {
        this.fDTDGrammar = null;
        this.fSymbolTable = null;
        this.fCurrentElementIndex = -1;
        this.fCurrentContentSpecType = -1;
        this.fInCDATASection = false;
        this.fElementContentState = new boolean[8];
        this.fElementDepth = -1;
        this.fInElementContent = false;
        this.fTempAttDecl = new XMLAttributeDecl();
        this.fTempQName = new QName();
        this.fBuffer = new StringBuffer();
        this.fNamespaceContext = null;
        this.fDTDGrammar = dTDGrammar;
        this.fSymbolTable = symbolTable;
    }

    public DTDGrammarUtil(DTDGrammar dTDGrammar, SymbolTable symbolTable, NamespaceContext namespaceContext) {
        this.fDTDGrammar = null;
        this.fSymbolTable = null;
        this.fCurrentElementIndex = -1;
        this.fCurrentContentSpecType = -1;
        this.fInCDATASection = false;
        this.fElementContentState = new boolean[8];
        this.fElementDepth = -1;
        this.fInElementContent = false;
        this.fTempAttDecl = new XMLAttributeDecl();
        this.fTempQName = new QName();
        this.fBuffer = new StringBuffer();
        this.fNamespaceContext = null;
        this.fDTDGrammar = dTDGrammar;
        this.fSymbolTable = symbolTable;
        this.fNamespaceContext = namespaceContext;
    }

    public void reset(XMLComponentManager xMLComponentManager) {
        this.fDTDGrammar = null;
        this.fInCDATASection = false;
        this.fInElementContent = false;
        this.fCurrentElementIndex = -1;
        this.fCurrentContentSpecType = -1;
        try {
            this.fNamespaces = xMLComponentManager.getFeature(NAMESPACES);
        } catch (XMLConfigurationException e) {
            this.fNamespaces = true;
        }
        this.fSymbolTable = (SymbolTable) xMLComponentManager.getProperty(SYMBOL_TABLE);
        this.fElementDepth = -1;
    }

    public void startElement(QName qName, XMLAttributes xMLAttributes) {
        handleStartElement(qName, xMLAttributes);
    }

    public void endElement(QName qName) {
        handleEndElement(qName);
    }

    public void startCDATA(Augmentations augmentations) {
        this.fInCDATASection = true;
    }

    public void endCDATA(Augmentations augmentations) {
        this.fInCDATASection = false;
    }

    public void addDTDDefaultAttrs(QName qName, XMLAttributes xMLAttributes) {
        String str;
        boolean z;
        String strAddSymbol;
        String strAddSymbol2;
        int iIndexOf;
        int elementDeclIndex = this.fDTDGrammar.getElementDeclIndex(qName);
        if (elementDeclIndex != -1 && this.fDTDGrammar != null) {
            int firstAttributeDeclIndex = this.fDTDGrammar.getFirstAttributeDeclIndex(elementDeclIndex);
            while (true) {
                int i = firstAttributeDeclIndex;
                if (i == -1) {
                    break;
                }
                this.fDTDGrammar.getAttributeDecl(i, this.fTempAttDecl);
                String str2 = this.fTempAttDecl.name.prefix;
                String str3 = this.fTempAttDecl.name.localpart;
                String str4 = this.fTempAttDecl.name.rawname;
                String attributeTypeName = getAttributeTypeName(this.fTempAttDecl);
                short s = this.fTempAttDecl.simpleType.defaultType;
                if (this.fTempAttDecl.simpleType.defaultValue == null) {
                    str = null;
                } else {
                    str = this.fTempAttDecl.simpleType.defaultValue;
                }
                boolean z2 = s == 2;
                if ((attributeTypeName == XMLSymbols.fCDATASymbol) && !z2 && str == null) {
                    z = false;
                    break;
                }
                if (this.fNamespaceContext != null && str4.startsWith("xmlns")) {
                    int iIndexOf2 = str4.indexOf(58);
                    String strAddSymbol3 = this.fSymbolTable.addSymbol(iIndexOf2 != -1 ? str4.substring(0, iIndexOf2) : str4);
                    if (!((NamespaceSupport) this.fNamespaceContext).containsPrefixInCurrentContext(strAddSymbol3)) {
                        this.fNamespaceContext.declarePrefix(strAddSymbol3, str);
                    }
                    z = true;
                } else {
                    int length = xMLAttributes.getLength();
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            z = false;
                            break;
                        } else if (xMLAttributes.getQName(i2) != str4) {
                            i2++;
                        } else {
                            z = true;
                            break;
                        }
                    }
                }
                if (!z && str != null) {
                    if (!this.fNamespaces || (iIndexOf = str4.indexOf(58)) == -1) {
                        strAddSymbol = str3;
                        strAddSymbol2 = str2;
                    } else {
                        strAddSymbol2 = this.fSymbolTable.addSymbol(str4.substring(0, iIndexOf));
                        strAddSymbol = this.fSymbolTable.addSymbol(str4.substring(iIndexOf + 1));
                    }
                    this.fTempQName.setValues(strAddSymbol2, strAddSymbol, str4, this.fTempAttDecl.name.uri);
                    xMLAttributes.addAttribute(this.fTempQName, attributeTypeName, str);
                }
                firstAttributeDeclIndex = this.fDTDGrammar.getNextAttributeDeclIndex(i);
            }
            int length2 = xMLAttributes.getLength();
            for (int i3 = 0; i3 < length2; i3++) {
                String qName2 = xMLAttributes.getQName(i3);
                boolean z3 = false;
                int firstAttributeDeclIndex2 = this.fDTDGrammar.getFirstAttributeDeclIndex(elementDeclIndex);
                while (firstAttributeDeclIndex2 != -1) {
                    this.fDTDGrammar.getAttributeDecl(firstAttributeDeclIndex2, this.fTempAttDecl);
                    if (this.fTempAttDecl.name.rawname == qName2) {
                        z3 = true;
                        break;
                    }
                    firstAttributeDeclIndex2 = this.fDTDGrammar.getNextAttributeDeclIndex(firstAttributeDeclIndex2);
                }
                if (z3) {
                    String attributeTypeName2 = getAttributeTypeName(this.fTempAttDecl);
                    xMLAttributes.setType(i3, attributeTypeName2);
                    xMLAttributes.getValue(i3);
                    if (xMLAttributes.isSpecified(i3) && attributeTypeName2 != XMLSymbols.fCDATASymbol) {
                        normalizeAttrValue(xMLAttributes, i3);
                        xMLAttributes.getValue(i3);
                    }
                }
            }
        }
    }

    private boolean normalizeAttrValue(XMLAttributes xMLAttributes, int i) {
        String value = xMLAttributes.getValue(i);
        char[] cArr = new char[value.length()];
        this.fBuffer.setLength(0);
        value.getChars(0, value.length(), cArr, 0);
        int i2 = 0;
        int i3 = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = true;
        for (int i4 = 0; i4 < cArr.length; i4++) {
            if (cArr[i4] == ' ') {
                if (z) {
                    z = false;
                    z2 = true;
                }
                if (z2 && !z3) {
                    this.fBuffer.append(cArr[i4]);
                    i3++;
                    z2 = false;
                } else if (z3 || !z2) {
                    i2++;
                }
            } else {
                this.fBuffer.append(cArr[i4]);
                i3++;
                z = true;
                z2 = false;
                z3 = false;
            }
        }
        if (i3 > 0 && this.fBuffer.charAt(i3 - 1) == ' ') {
            this.fBuffer.setLength(i3 - 1);
        }
        String string = this.fBuffer.toString();
        xMLAttributes.setValue(i, string);
        return !value.equals(string);
    }

    private String getAttributeTypeName(XMLAttributeDecl xMLAttributeDecl) {
        switch (xMLAttributeDecl.simpleType.type) {
            case 1:
                return xMLAttributeDecl.simpleType.list ? XMLSymbols.fENTITIESSymbol : XMLSymbols.fENTITYSymbol;
            case 2:
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append('(');
                for (int i = 0; i < xMLAttributeDecl.simpleType.enumeration.length; i++) {
                    if (i > 0) {
                        stringBuffer.append("|");
                    }
                    stringBuffer.append(xMLAttributeDecl.simpleType.enumeration[i]);
                }
                stringBuffer.append(')');
                return this.fSymbolTable.addSymbol(stringBuffer.toString());
            case 3:
                return XMLSymbols.fIDSymbol;
            case 4:
                return xMLAttributeDecl.simpleType.list ? XMLSymbols.fIDREFSSymbol : XMLSymbols.fIDREFSymbol;
            case 5:
                return xMLAttributeDecl.simpleType.list ? XMLSymbols.fNMTOKENSSymbol : XMLSymbols.fNMTOKENSymbol;
            case 6:
                return XMLSymbols.fNOTATIONSymbol;
            default:
                return XMLSymbols.fCDATASymbol;
        }
    }

    private void ensureStackCapacity(int i) {
        if (i == this.fElementContentState.length) {
            boolean[] zArr = new boolean[i * 2];
            System.arraycopy(this.fElementContentState, 0, zArr, 0, i);
            this.fElementContentState = zArr;
        }
    }

    protected void handleStartElement(QName qName, XMLAttributes xMLAttributes) {
        if (this.fDTDGrammar == null) {
            this.fCurrentElementIndex = -1;
            this.fCurrentContentSpecType = -1;
            this.fInElementContent = false;
            return;
        }
        this.fCurrentElementIndex = this.fDTDGrammar.getElementDeclIndex(qName);
        this.fCurrentContentSpecType = this.fDTDGrammar.getContentSpecType(this.fCurrentElementIndex);
        addDTDDefaultAttrs(qName, xMLAttributes);
        this.fInElementContent = this.fCurrentContentSpecType == 3;
        this.fElementDepth++;
        ensureStackCapacity(this.fElementDepth);
        this.fElementContentState[this.fElementDepth] = this.fInElementContent;
    }

    protected void handleEndElement(QName qName) {
        this.fElementDepth--;
        if (this.fElementDepth < -1) {
            throw new RuntimeException("FWK008 Element stack underflow");
        }
        if (this.fElementDepth < 0) {
            this.fCurrentElementIndex = -1;
            this.fCurrentContentSpecType = -1;
            this.fInElementContent = false;
            return;
        }
        this.fInElementContent = this.fElementContentState[this.fElementDepth];
    }

    public boolean isInElementContent() {
        return this.fInElementContent;
    }

    public boolean isIgnorableWhiteSpace(XMLString xMLString) {
        if (!isInElementContent()) {
            return false;
        }
        for (int i = xMLString.offset; i < xMLString.offset + xMLString.length; i++) {
            if (!XMLChar.isSpace(xMLString.ch[i])) {
                return false;
            }
        }
        return true;
    }
}

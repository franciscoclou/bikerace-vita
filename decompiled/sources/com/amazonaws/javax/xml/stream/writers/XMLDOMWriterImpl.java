package com.amazonaws.javax.xml.stream.writers;

import com.amazonaws.javax.xml.stream.XMLStreamException2;
import com.amazonaws.javax.xml.stream.XMLStreamWriter;
import com.amazonaws.javax.xml.transform.a.a;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.helpers.NamespaceSupport;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class XMLDOMWriterImpl implements XMLStreamWriter {
    static /* synthetic */ Class class$java$lang$String;
    private Node currentNode;
    private NamespaceSupport namespaceContext;
    private boolean[] needContextPop;
    private Node node;
    private Document ownerDoc;
    private StringBuffer stringBuffer;
    private Method mXmlVersion = null;
    private int resizeValue = 20;
    private int depth = 0;

    public XMLDOMWriterImpl(a aVar) throws Throwable {
        this.ownerDoc = null;
        this.currentNode = null;
        this.node = null;
        this.namespaceContext = null;
        this.needContextPop = null;
        this.stringBuffer = null;
        this.node = aVar.a();
        if (this.node.getNodeType() == 9) {
            this.ownerDoc = (Document) this.node;
            this.currentNode = this.ownerDoc;
        } else {
            this.ownerDoc = this.node.getOwnerDocument();
            this.currentNode = this.node;
        }
        getDLThreeMethods();
        this.stringBuffer = new StringBuffer();
        this.needContextPop = new boolean[this.resizeValue];
        this.namespaceContext = new NamespaceSupport();
    }

    static /* synthetic */ Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    private void getDLThreeMethods() throws Throwable {
        Class<?> clsClass$;
        try {
            Class<?> cls = this.ownerDoc.getClass();
            Class<?>[] clsArr = new Class[1];
            if (class$java$lang$String == null) {
                clsClass$ = class$("java.lang.String");
                class$java$lang$String = clsClass$;
            } else {
                clsClass$ = class$java$lang$String;
            }
            clsArr[0] = clsClass$;
            this.mXmlVersion = cls.getMethod("setXmlVersion", clsArr);
        } catch (NoSuchMethodException e) {
            this.mXmlVersion = null;
        } catch (SecurityException e2) {
            this.mXmlVersion = null;
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void close() {
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void flush() {
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public com.amazonaws.javax.xml.a.a getNamespaceContext() {
        return null;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public String getPrefix(String str) {
        if (this.namespaceContext == null) {
            return null;
        }
        return this.namespaceContext.getPrefix(str);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public Object getProperty(String str) {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void setDefaultNamespace(String str) {
        this.namespaceContext.declarePrefix("", str);
        if (!this.needContextPop[this.depth]) {
            this.needContextPop[this.depth] = true;
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void setNamespaceContext(com.amazonaws.javax.xml.a.a aVar) {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void setPrefix(String str, String str2) throws XMLStreamException2 {
        if (str == null) {
            throw new XMLStreamException2("Prefix cannot be null");
        }
        this.namespaceContext.declarePrefix(str, str2);
        if (!this.needContextPop[this.depth]) {
            this.needContextPop[this.depth] = true;
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeAttribute(String str, String str2) {
        if (this.currentNode.getNodeType() == 1) {
            Attr attrCreateAttribute = this.ownerDoc.createAttribute(str);
            attrCreateAttribute.setValue(str2);
            ((Element) this.currentNode).setAttributeNode(attrCreateAttribute);
            return;
        }
        throw new IllegalStateException(new StringBuffer().append("Current DOM Node type  is ").append((int) this.currentNode.getNodeType()).append("and does not allow attributes to be set ").toString());
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeAttribute(String str, String str2, String str3) throws XMLStreamException2 {
        if (this.currentNode.getNodeType() == 1) {
            String prefix = null;
            if (str == null) {
                throw new XMLStreamException2("NamespaceURI cannot be null");
            }
            if (str2 == null) {
                throw new XMLStreamException2("Local name cannot be null");
            }
            if (this.namespaceContext != null) {
                prefix = this.namespaceContext.getPrefix(str);
            }
            if (prefix == null) {
                throw new XMLStreamException2(new StringBuffer().append("Namespace URI ").append(str).append("is not bound to any prefix").toString());
            }
            if (!prefix.equals("")) {
                str2 = getQName(prefix, str2);
            }
            Attr attrCreateAttributeNS = this.ownerDoc.createAttributeNS(str, str2);
            attrCreateAttributeNS.setValue(str3);
            ((Element) this.currentNode).setAttributeNode(attrCreateAttributeNS);
            return;
        }
        throw new IllegalStateException(new StringBuffer().append("Current DOM Node type  is ").append((int) this.currentNode.getNodeType()).append("and does not allow attributes to be set ").toString());
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeAttribute(String str, String str2, String str3, String str4) throws XMLStreamException2 {
        if (this.currentNode.getNodeType() == 1) {
            if (str2 == null) {
                throw new XMLStreamException2("NamespaceURI cannot be null");
            }
            if (str3 == null) {
                throw new XMLStreamException2("Local name cannot be null");
            }
            if (str == null) {
                throw new XMLStreamException2("prefix cannot be null");
            }
            if (!str.equals("")) {
                str3 = getQName(str, str3);
            }
            Attr attrCreateAttributeNS = this.ownerDoc.createAttributeNS(str2, str3);
            attrCreateAttributeNS.setValue(str4);
            ((Element) this.currentNode).setAttributeNodeNS(attrCreateAttributeNS);
            return;
        }
        throw new IllegalStateException(new StringBuffer().append("Current DOM Node type  is ").append((int) this.currentNode.getNodeType()).append("and does not allow attributes to be set ").toString());
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeCData(String str) throws XMLStreamException2 {
        if (str == null) {
            throw new XMLStreamException2("CDATA cannot be null");
        }
        getNode().appendChild(this.ownerDoc.createCDATASection(str));
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeCharacters(String str) {
        this.currentNode.appendChild(this.ownerDoc.createTextNode(str));
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeCharacters(char[] cArr, int i, int i2) {
        this.currentNode.appendChild(this.ownerDoc.createTextNode(new String(cArr, i, i2)));
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeComment(String str) {
        getNode().appendChild(this.ownerDoc.createComment(str));
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeDTD(String str) {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeDefaultNamespace(String str) {
        if (this.currentNode.getNodeType() == 1) {
            ((Element) this.currentNode).setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns", str);
            return;
        }
        throw new IllegalStateException(new StringBuffer().append("Current DOM Node type  is ").append((int) this.currentNode.getNodeType()).append("and does not allow attributes to be set ").toString());
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeEmptyElement(String str) {
        if (this.ownerDoc != null) {
            Element elementCreateElement = this.ownerDoc.createElement(str);
            if (this.currentNode != null) {
                this.currentNode.appendChild(elementCreateElement);
            } else {
                this.ownerDoc.appendChild(elementCreateElement);
            }
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeEmptyElement(String str, String str2) throws XMLStreamException2 {
        if (this.ownerDoc != null) {
            String prefix = null;
            if (str == null) {
                throw new XMLStreamException2("NamespaceURI cannot be null");
            }
            if (str2 == null) {
                throw new XMLStreamException2("Local name cannot be null");
            }
            if (this.namespaceContext != null) {
                prefix = this.namespaceContext.getPrefix(str);
            }
            if (prefix == null) {
                throw new XMLStreamException2(new StringBuffer().append("Namespace URI ").append(str).append("is not bound to any prefix").toString());
            }
            if (!"".equals(prefix)) {
                str2 = getQName(prefix, str2);
            }
            Element elementCreateElementNS = this.ownerDoc.createElementNS(str, str2);
            if (this.currentNode != null) {
                this.currentNode.appendChild(elementCreateElementNS);
            } else {
                this.ownerDoc.appendChild(elementCreateElementNS);
            }
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeEmptyElement(String str, String str2, String str3) throws XMLStreamException2 {
        if (this.ownerDoc != null) {
            if (str3 == null) {
                throw new XMLStreamException2("NamespaceURI cannot be null");
            }
            if (str2 == null) {
                throw new XMLStreamException2("Local name cannot be null");
            }
            if (str == null) {
                throw new XMLStreamException2("Prefix cannot be null");
            }
            if (!"".equals(str)) {
                str2 = getQName(str, str2);
            }
            Element elementCreateElementNS = this.ownerDoc.createElementNS(str3, str2);
            if (this.currentNode != null) {
                this.currentNode.appendChild(elementCreateElementNS);
            } else {
                this.ownerDoc.appendChild(elementCreateElementNS);
            }
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeEndDocument() {
        this.currentNode = null;
        for (int i = 0; i < this.depth; i++) {
            if (this.needContextPop[this.depth]) {
                this.needContextPop[this.depth] = false;
                this.namespaceContext.popContext();
            }
            this.depth--;
        }
        this.depth = 0;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeEndElement() {
        Node parentNode = this.currentNode.getParentNode();
        if (this.currentNode.getNodeType() == 9) {
            this.currentNode = null;
        } else {
            this.currentNode = parentNode;
        }
        if (this.needContextPop[this.depth]) {
            this.needContextPop[this.depth] = false;
            this.namespaceContext.popContext();
        }
        this.depth--;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeEntityRef(String str) {
        this.currentNode.appendChild(this.ownerDoc.createEntityReference(str));
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeNamespace(String str, String str2) throws XMLStreamException2 {
        String qName;
        if (str == null) {
            throw new XMLStreamException2("prefix cannot be null");
        }
        if (str2 == null) {
            throw new XMLStreamException2("NamespaceURI cannot be null");
        }
        if (str.equals("")) {
            qName = "xmlns";
        } else {
            qName = getQName("xmlns", str);
        }
        ((Element) this.currentNode).setAttributeNS("http://www.w3.org/2000/xmlns/", qName, str2);
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeProcessingInstruction(String str) throws XMLStreamException2 {
        if (str == null) {
            throw new XMLStreamException2("Target cannot be null");
        }
        this.currentNode.appendChild(this.ownerDoc.createProcessingInstruction(str, ""));
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeProcessingInstruction(String str, String str2) throws XMLStreamException2 {
        if (str == null) {
            throw new XMLStreamException2("Target cannot be null");
        }
        this.currentNode.appendChild(this.ownerDoc.createProcessingInstruction(str, str2));
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeStartDocument() throws XMLStreamException2 {
        try {
            if (this.mXmlVersion != null) {
                this.mXmlVersion.invoke(this.ownerDoc, XMLStreamWriterImpl.DEFAULT_XML_VERSION);
            }
        } catch (IllegalAccessException e) {
            throw new XMLStreamException2(e);
        } catch (InvocationTargetException e2) {
            throw new XMLStreamException2(e2);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeStartDocument(String str) throws XMLStreamException2 {
        try {
            if (this.mXmlVersion != null) {
                this.mXmlVersion.invoke(this.ownerDoc, str);
            }
        } catch (IllegalAccessException e) {
            throw new XMLStreamException2(e);
        } catch (InvocationTargetException e2) {
            throw new XMLStreamException2(e2);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeStartDocument(String str, String str2) throws XMLStreamException2 {
        try {
            if (this.mXmlVersion != null) {
                this.mXmlVersion.invoke(this.ownerDoc, str2);
            }
        } catch (IllegalAccessException e) {
            throw new XMLStreamException2(e);
        } catch (InvocationTargetException e2) {
            throw new XMLStreamException2(e2);
        }
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeStartElement(String str) {
        if (this.ownerDoc != null) {
            Element elementCreateElement = this.ownerDoc.createElement(str);
            if (this.currentNode != null) {
                this.currentNode.appendChild(elementCreateElement);
            } else {
                this.ownerDoc.appendChild(elementCreateElement);
            }
            this.currentNode = elementCreateElement;
        }
        if (this.needContextPop[this.depth]) {
            this.namespaceContext.pushContext();
        }
        this.depth++;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeStartElement(String str, String str2) throws XMLStreamException2 {
        if (this.ownerDoc != null) {
            String prefix = null;
            if (str == null) {
                throw new XMLStreamException2("NamespaceURI cannot be null");
            }
            if (str2 == null) {
                throw new XMLStreamException2("Local name cannot be null");
            }
            if (this.namespaceContext != null) {
                prefix = this.namespaceContext.getPrefix(str);
            }
            if (prefix == null) {
                throw new XMLStreamException2(new StringBuffer().append("Namespace URI ").append(str).append("is not bound to any prefix").toString());
            }
            if (!"".equals(prefix)) {
                str2 = getQName(prefix, str2);
            }
            Element elementCreateElementNS = this.ownerDoc.createElementNS(str, str2);
            if (this.currentNode != null) {
                this.currentNode.appendChild(elementCreateElementNS);
            } else {
                this.ownerDoc.appendChild(elementCreateElementNS);
            }
            this.currentNode = elementCreateElementNS;
        }
        if (this.needContextPop[this.depth]) {
            this.namespaceContext.pushContext();
        }
        this.depth++;
    }

    @Override // com.amazonaws.javax.xml.stream.XMLStreamWriter
    public void writeStartElement(String str, String str2, String str3) throws XMLStreamException2 {
        if (this.ownerDoc != null) {
            if (str3 == null) {
                throw new XMLStreamException2("NamespaceURI cannot be null");
            }
            if (str2 == null) {
                throw new XMLStreamException2("Local name cannot be null");
            }
            if (str == null) {
                throw new XMLStreamException2("Prefix cannot be null");
            }
            if (!str.equals("")) {
                str2 = getQName(str, str2);
            }
            Element elementCreateElementNS = this.ownerDoc.createElementNS(str3, str2);
            if (this.currentNode != null) {
                this.currentNode.appendChild(elementCreateElementNS);
            } else {
                this.ownerDoc.appendChild(elementCreateElementNS);
            }
            this.currentNode = elementCreateElementNS;
            if (this.needContextPop[this.depth]) {
                this.namespaceContext.pushContext();
            }
            this.depth++;
        }
    }

    private String getQName(String str, String str2) {
        this.stringBuffer.setLength(0);
        this.stringBuffer.append(str);
        this.stringBuffer.append(":");
        this.stringBuffer.append(str2);
        return this.stringBuffer.toString();
    }

    private Node getNode() {
        return this.currentNode == null ? this.ownerDoc : this.currentNode;
    }
}

package com.amazonaws.javax.xml.stream.xerces.util;

import com.amazonaws.javax.xml.stream.xerces.xni.NamespaceContext;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class NamespaceSupport implements NamespaceContext {
    protected int[] fContext;
    protected int fCurrentContext;
    protected String[] fNamespace;
    protected int fNamespaceSize;
    protected String[] fPrefixes;

    public NamespaceSupport() {
        this.fNamespace = new String[32];
        this.fContext = new int[8];
        this.fPrefixes = new String[16];
    }

    public NamespaceSupport(NamespaceContext namespaceContext) {
        this.fNamespace = new String[32];
        this.fContext = new int[8];
        this.fPrefixes = new String[16];
        pushContext();
        Enumeration allPrefixes = namespaceContext.getAllPrefixes();
        while (allPrefixes.hasMoreElements()) {
            String str = (String) allPrefixes.nextElement();
            declarePrefix(str, namespaceContext.getURI(str));
        }
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.NamespaceContext
    public void reset() {
        this.fNamespaceSize = 0;
        this.fCurrentContext = 0;
        String[] strArr = this.fNamespace;
        int i = this.fNamespaceSize;
        this.fNamespaceSize = i + 1;
        strArr[i] = XMLSymbols.PREFIX_XML;
        String[] strArr2 = this.fNamespace;
        int i2 = this.fNamespaceSize;
        this.fNamespaceSize = i2 + 1;
        strArr2[i2] = NamespaceContext.XML_URI;
        String[] strArr3 = this.fNamespace;
        int i3 = this.fNamespaceSize;
        this.fNamespaceSize = i3 + 1;
        strArr3[i3] = XMLSymbols.PREFIX_XMLNS;
        String[] strArr4 = this.fNamespace;
        int i4 = this.fNamespaceSize;
        this.fNamespaceSize = i4 + 1;
        strArr4[i4] = NamespaceContext.XMLNS_URI;
        this.fContext[this.fCurrentContext] = this.fNamespaceSize;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.NamespaceContext
    public void pushContext() {
        if (this.fCurrentContext + 1 == this.fContext.length) {
            int[] iArr = new int[this.fContext.length * 2];
            System.arraycopy(this.fContext, 0, iArr, 0, this.fContext.length);
            this.fContext = iArr;
        }
        int[] iArr2 = this.fContext;
        int i = this.fCurrentContext + 1;
        this.fCurrentContext = i;
        iArr2[i] = this.fNamespaceSize;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.NamespaceContext
    public void popContext() {
        int[] iArr = this.fContext;
        int i = this.fCurrentContext;
        this.fCurrentContext = i - 1;
        this.fNamespaceSize = iArr[i];
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.NamespaceContext
    public boolean declarePrefix(String str, String str2) {
        if (str == XMLSymbols.PREFIX_XML || str == XMLSymbols.PREFIX_XMLNS) {
            return false;
        }
        for (int i = this.fNamespaceSize; i > this.fContext[this.fCurrentContext]; i -= 2) {
            if (this.fNamespace[i - 2] == str) {
                this.fNamespace[i - 1] = str2;
                return true;
            }
        }
        if (this.fNamespaceSize == this.fNamespace.length) {
            String[] strArr = new String[this.fNamespaceSize * 2];
            System.arraycopy(this.fNamespace, 0, strArr, 0, this.fNamespaceSize);
            this.fNamespace = strArr;
        }
        String[] strArr2 = this.fNamespace;
        int i2 = this.fNamespaceSize;
        this.fNamespaceSize = i2 + 1;
        strArr2[i2] = str;
        String[] strArr3 = this.fNamespace;
        int i3 = this.fNamespaceSize;
        this.fNamespaceSize = i3 + 1;
        strArr3[i3] = str2;
        return true;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.NamespaceContext
    public String getURI(String str) {
        for (int i = this.fNamespaceSize; i > 0; i -= 2) {
            if (this.fNamespace[i - 2] == str) {
                return this.fNamespace[i - 1];
            }
        }
        return null;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.NamespaceContext
    public String getPrefix(String str) {
        for (int i = this.fNamespaceSize; i > 0; i -= 2) {
            if (this.fNamespace[i - 1] == str && getURI(this.fNamespace[i - 2]) == str) {
                return this.fNamespace[i - 2];
            }
        }
        return null;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.NamespaceContext
    public int getDeclaredPrefixCount() {
        return (this.fNamespaceSize - this.fContext[this.fCurrentContext]) / 2;
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.NamespaceContext
    public String getDeclaredPrefixAt(int i) {
        return this.fNamespace[this.fContext[this.fCurrentContext] + (i * 2)];
    }

    public Iterator getPrefixes() {
        boolean z;
        int i;
        if (this.fPrefixes.length < this.fNamespace.length / 2) {
            this.fPrefixes = new String[this.fNamespaceSize];
        }
        int i2 = 2;
        int i3 = 0;
        while (i2 < this.fNamespaceSize - 2) {
            String str = this.fNamespace[i2 + 2];
            int i4 = 0;
            while (true) {
                if (i4 >= i3) {
                    z = true;
                    break;
                }
                if (this.fPrefixes[i4] == str) {
                    z = false;
                    break;
                }
                i4++;
            }
            if (z) {
                i = i3 + 1;
                this.fPrefixes[i3] = str;
            } else {
                i = i3;
            }
            i2 += 2;
            i3 = i;
        }
        return new IteratorPrefixes(this.fPrefixes, i3);
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.NamespaceContext
    public Enumeration getAllPrefixes() {
        boolean z;
        int i;
        if (this.fPrefixes.length < this.fNamespace.length / 2) {
            this.fPrefixes = new String[this.fNamespaceSize];
        }
        int i2 = 2;
        int i3 = 0;
        while (i2 < this.fNamespaceSize - 2) {
            String str = this.fNamespace[i2 + 2];
            int i4 = 0;
            while (true) {
                if (i4 >= i3) {
                    z = true;
                    break;
                }
                if (this.fPrefixes[i4] == str) {
                    z = false;
                    break;
                }
                i4++;
            }
            if (z) {
                i = i3 + 1;
                this.fPrefixes[i3] = str;
            } else {
                i = i3;
            }
            i2 += 2;
            i3 = i;
        }
        return new Prefixes(this.fPrefixes, i3);
    }

    @Override // com.amazonaws.javax.xml.stream.xerces.xni.NamespaceContext
    public Vector getPrefixes(String str) {
        Vector vector = new Vector();
        for (int i = this.fNamespaceSize; i > 0; i -= 2) {
            if (this.fNamespace[i - 1] == str && !vector.contains(this.fNamespace[i - 2])) {
                vector.add(this.fNamespace[i - 2]);
            }
        }
        return vector;
    }

    public boolean containsPrefix(String str) {
        for (int i = this.fNamespaceSize; i > 0; i -= 2) {
            if (this.fNamespace[i - 2] == str) {
                return true;
            }
        }
        return false;
    }

    public boolean containsPrefixInCurrentContext(String str) {
        for (int i = this.fContext[this.fCurrentContext]; i < this.fNamespaceSize; i += 2) {
            if (this.fNamespace[i] == str) {
                return true;
            }
        }
        return false;
    }

    public final class IteratorPrefixes implements Iterator {
        private int counter = 0;
        private String[] prefixes;
        private int size;

        public IteratorPrefixes(String[] strArr, int i) {
            this.size = 0;
            this.prefixes = strArr;
            this.size = i;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.counter < this.size;
        }

        @Override // java.util.Iterator
        public Object next() {
            if (this.counter < this.size) {
                String[] strArr = NamespaceSupport.this.fPrefixes;
                int i = this.counter;
                this.counter = i + 1;
                return strArr[i];
            }
            throw new NoSuchElementException("Illegal access to Namespace prefixes enumeration.");
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < this.size; i++) {
                stringBuffer.append(this.prefixes[i]);
                stringBuffer.append(" ");
            }
            return stringBuffer.toString();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public final class Prefixes implements Enumeration {
        private int counter = 0;
        private String[] prefixes;
        private int size;

        public Prefixes(String[] strArr, int i) {
            this.size = 0;
            this.prefixes = strArr;
            this.size = i;
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return this.counter < this.size;
        }

        @Override // java.util.Enumeration
        public Object nextElement() {
            if (this.counter < this.size) {
                String[] strArr = NamespaceSupport.this.fPrefixes;
                int i = this.counter;
                this.counter = i + 1;
                return strArr[i];
            }
            throw new NoSuchElementException("Illegal access to Namespace prefixes enumeration.");
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < this.size; i++) {
                stringBuffer.append(this.prefixes[i]);
                stringBuffer.append(" ");
            }
            return stringBuffer.toString();
        }
    }
}

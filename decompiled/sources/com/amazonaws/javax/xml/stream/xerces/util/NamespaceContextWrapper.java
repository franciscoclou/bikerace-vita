package com.amazonaws.javax.xml.stream.xerces.util;

import com.amazonaws.javax.xml.a.a;
import com.amazonaws.javax.xml.stream.xerces.xni.NamespaceContext;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class NamespaceContextWrapper implements a {
    private NamespaceContext fNamespaceContext;

    public NamespaceContextWrapper(NamespaceContext namespaceContext) {
        this.fNamespaceContext = namespaceContext;
    }

    @Override // com.amazonaws.javax.xml.a.a
    public String getNamespaceURI(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Prefix can't be null");
        }
        return this.fNamespaceContext.getURI(str.intern());
    }

    @Override // com.amazonaws.javax.xml.a.a
    public String getPrefix(String str) {
        if (str == null) {
            throw new IllegalArgumentException("URI can't be null");
        }
        return this.fNamespaceContext.getPrefix(str.intern());
    }

    @Override // com.amazonaws.javax.xml.a.a
    public Iterator getPrefixes(String str) {
        if (str == null) {
            throw new IllegalArgumentException("URI can't be null");
        }
        return ((NamespaceSupport) this.fNamespaceContext).getPrefixes(str.intern()).iterator();
    }

    public NamespaceContext getNamespaceContext() {
        return this.fNamespaceContext;
    }
}

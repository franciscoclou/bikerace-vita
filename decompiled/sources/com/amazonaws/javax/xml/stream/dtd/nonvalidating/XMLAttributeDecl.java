package com.amazonaws.javax.xml.stream.dtd.nonvalidating;

import com.amazonaws.javax.xml.stream.xerces.xni.QName;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class XMLAttributeDecl {
    public boolean optional;
    public final QName name = new QName();
    public final XMLSimpleType simpleType = new XMLSimpleType();

    public void setValues(QName qName, XMLSimpleType xMLSimpleType, boolean z) {
        this.name.setValues(qName);
        this.simpleType.setValues(xMLSimpleType);
        this.optional = z;
    }

    public void clear() {
        this.name.clear();
        this.simpleType.clear();
        this.optional = false;
    }
}

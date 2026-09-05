package com.amazonaws.javax.xml.stream.events;

import com.amazonaws.javax.xml.a.b;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AttributeImpl extends DummyEvent implements Attribute {
    private String fAttributeType;
    private boolean fIsSpecified;
    private String fNonNormalizedvalue;
    private b fQName;
    private String fValue;

    public AttributeImpl() {
        this.fAttributeType = "CDATA";
        init();
    }

    public AttributeImpl(String str, String str2) {
        this.fAttributeType = "CDATA";
        init();
        this.fQName = new b(str);
        this.fValue = str2;
    }

    public AttributeImpl(String str, String str2, String str3) {
        this(str, null, str2, str3, null, null, false);
    }

    public AttributeImpl(String str, String str2, String str3, String str4, String str5) {
        this(str, str2, str3, str4, null, str5, false);
    }

    public AttributeImpl(String str, String str2, String str3, String str4, String str5, String str6, boolean z) {
        this(new b(str2, str3, str), str4, str5, str6, z);
    }

    public AttributeImpl(b bVar, String str, String str2, String str3, boolean z) {
        this.fAttributeType = "CDATA";
        init();
        this.fQName = bVar;
        this.fValue = str;
        if (str3 != null && !str3.equals("")) {
            this.fAttributeType = str3;
        }
        this.fNonNormalizedvalue = str2;
        this.fIsSpecified = z;
    }

    public String toString() {
        return (this.fQName.c() == null || this.fQName.c().length() <= 0) ? new StringBuffer().append(this.fQName.b()).append("='").append(this.fValue).append("'").toString() : new StringBuffer().append(this.fQName.c()).append(":").append(this.fQName.b()).append("='").append(this.fValue).append("'").toString();
    }

    public void setName(b bVar) {
        this.fQName = bVar;
    }

    @Override // com.amazonaws.javax.xml.stream.events.Attribute
    public b getName() {
        return this.fQName;
    }

    public void setValue(String str) {
        this.fValue = str;
    }

    @Override // com.amazonaws.javax.xml.stream.events.Attribute
    public String getValue() {
        return this.fValue;
    }

    public void setNonNormalizedValue(String str) {
        this.fNonNormalizedvalue = str;
    }

    public String getNonNormalizedValue() {
        return this.fNonNormalizedvalue;
    }

    public void setAttributeType(String str) {
        this.fAttributeType = str;
    }

    @Override // com.amazonaws.javax.xml.stream.events.Attribute
    public String getDTDType() {
        return this.fAttributeType;
    }

    public void setSpecified(boolean z) {
        this.fIsSpecified = z;
    }

    @Override // com.amazonaws.javax.xml.stream.events.Attribute
    public boolean isSpecified() {
        return this.fIsSpecified;
    }

    @Override // com.amazonaws.javax.xml.stream.events.DummyEvent
    protected void writeAsEncodedUnicodeEx(Writer writer) throws IOException {
        writer.write(toString());
    }

    protected void init() {
        setEventType(10);
    }
}

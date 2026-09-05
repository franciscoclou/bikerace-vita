package com.amazonaws.javax.xml.stream.events;

import com.amazonaws.javax.xml.stream.dtd.nonvalidating.XMLNotationDecl;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class NotationDeclarationImpl extends DummyEvent implements NotationDeclaration {
    String fName;
    String fPublicId;
    String fSystemId;

    public NotationDeclarationImpl() {
        this.fName = null;
        this.fPublicId = null;
        this.fSystemId = null;
        setEventType(14);
    }

    public NotationDeclarationImpl(String str, String str2, String str3) {
        this.fName = null;
        this.fPublicId = null;
        this.fSystemId = null;
        this.fName = str;
        this.fPublicId = str2;
        this.fSystemId = str3;
        setEventType(14);
    }

    public NotationDeclarationImpl(XMLNotationDecl xMLNotationDecl) {
        this.fName = null;
        this.fPublicId = null;
        this.fSystemId = null;
        this.fName = xMLNotationDecl.name;
        this.fPublicId = xMLNotationDecl.publicId;
        this.fSystemId = xMLNotationDecl.systemId;
        setEventType(14);
    }

    @Override // com.amazonaws.javax.xml.stream.events.NotationDeclaration
    public String getName() {
        return this.fName;
    }

    @Override // com.amazonaws.javax.xml.stream.events.NotationDeclaration
    public String getPublicId() {
        return this.fPublicId;
    }

    @Override // com.amazonaws.javax.xml.stream.events.NotationDeclaration
    public String getSystemId() {
        return this.fSystemId;
    }

    void setPublicId(String str) {
        this.fPublicId = str;
    }

    void setSystemId(String str) {
        this.fSystemId = str;
    }

    void setName(String str) {
        this.fName = str;
    }

    @Override // com.amazonaws.javax.xml.stream.events.DummyEvent
    protected void writeAsEncodedUnicodeEx(Writer writer) throws IOException {
        writer.write("<!NOTATION ");
        writer.write(getName());
        if (this.fPublicId != null) {
            writer.write(" PUBLIC \"");
            writer.write(this.fPublicId);
            writer.write("\"");
        } else if (this.fSystemId != null) {
            writer.write(" SYSTEM");
            writer.write(" \"");
            writer.write(this.fSystemId);
            writer.write("\"");
        }
        writer.write(62);
    }
}

package com.amazonaws.javax.xml.stream.events;

import com.amazonaws.javax.xml.stream.xerces.xni.XMLResourceIdentifier;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class EntityDeclarationImpl extends DummyEvent implements EntityDeclaration {
    private String fEntityName;
    private String fNotationName;
    private String fReplacementText;
    private XMLResourceIdentifier fXMLResourceIdentifier;

    public EntityDeclarationImpl() {
        init();
    }

    public EntityDeclarationImpl(String str, String str2) {
        this(str, str2, null);
    }

    public EntityDeclarationImpl(String str, String str2, XMLResourceIdentifier xMLResourceIdentifier) {
        init();
        this.fEntityName = str;
        this.fReplacementText = str2;
        this.fXMLResourceIdentifier = xMLResourceIdentifier;
    }

    public void setEntityName(String str) {
        this.fEntityName = str;
    }

    public String getEntityName() {
        return this.fEntityName;
    }

    public void setEntityReplacementText(String str) {
        this.fReplacementText = str;
    }

    public void setXMLResourceIdentifier(XMLResourceIdentifier xMLResourceIdentifier) {
        this.fXMLResourceIdentifier = xMLResourceIdentifier;
    }

    public XMLResourceIdentifier getXMLResourceIdentifier() {
        return this.fXMLResourceIdentifier;
    }

    @Override // com.amazonaws.javax.xml.stream.events.EntityDeclaration
    public String getSystemId() {
        if (this.fXMLResourceIdentifier != null) {
            return this.fXMLResourceIdentifier.getLiteralSystemId();
        }
        return null;
    }

    @Override // com.amazonaws.javax.xml.stream.events.EntityDeclaration
    public String getPublicId() {
        if (this.fXMLResourceIdentifier != null) {
            return this.fXMLResourceIdentifier.getPublicId();
        }
        return null;
    }

    @Override // com.amazonaws.javax.xml.stream.events.EntityDeclaration
    public String getBaseURI() {
        if (this.fXMLResourceIdentifier != null) {
            return this.fXMLResourceIdentifier.getBaseSystemId();
        }
        return null;
    }

    @Override // com.amazonaws.javax.xml.stream.events.EntityDeclaration
    public String getName() {
        return this.fEntityName;
    }

    @Override // com.amazonaws.javax.xml.stream.events.EntityDeclaration
    public String getNotationName() {
        return this.fNotationName;
    }

    public void setNotationName(String str) {
        this.fNotationName = str;
    }

    @Override // com.amazonaws.javax.xml.stream.events.EntityDeclaration
    public String getReplacementText() {
        return this.fReplacementText;
    }

    protected void init() {
        setEventType(15);
    }

    @Override // com.amazonaws.javax.xml.stream.events.DummyEvent
    protected void writeAsEncodedUnicodeEx(Writer writer) throws IOException {
        writer.write("<!ENTITY ");
        writer.write(this.fEntityName);
        if (this.fReplacementText != null) {
            writer.write(" \"");
            charEncode(writer, this.fReplacementText);
        } else {
            String publicId = getPublicId();
            if (publicId != null) {
                writer.write(" PUBLIC \"");
                writer.write(publicId);
            } else {
                writer.write(" SYSTEM \"");
                writer.write(getSystemId());
            }
        }
        writer.write("\"");
        if (this.fNotationName != null) {
            writer.write(" NDATA ");
            writer.write(this.fNotationName);
        }
        writer.write(">");
    }
}

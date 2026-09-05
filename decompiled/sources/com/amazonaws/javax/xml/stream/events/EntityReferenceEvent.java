package com.amazonaws.javax.xml.stream.events;

import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class EntityReferenceEvent extends DummyEvent implements EntityReference {
    private EntityDeclaration fEntityDeclaration;
    private String fEntityName;

    public EntityReferenceEvent() {
        init();
    }

    public EntityReferenceEvent(String str, EntityDeclaration entityDeclaration) {
        init();
        this.fEntityName = str;
        this.fEntityDeclaration = entityDeclaration;
    }

    @Override // com.amazonaws.javax.xml.stream.events.EntityReference
    public String getName() {
        return this.fEntityName;
    }

    public String toString() {
        String replacementText = this.fEntityDeclaration.getReplacementText();
        if (replacementText == null) {
            replacementText = "";
        }
        return new StringBuffer().append("&").append(getName()).append(";='").append(replacementText).append("'").toString();
    }

    @Override // com.amazonaws.javax.xml.stream.events.DummyEvent
    protected void writeAsEncodedUnicodeEx(Writer writer) throws IOException {
        writer.write(38);
        writer.write(getName());
        writer.write(59);
    }

    @Override // com.amazonaws.javax.xml.stream.events.EntityReference
    public EntityDeclaration getDeclaration() {
        return this.fEntityDeclaration;
    }

    protected void init() {
        setEventType(9);
    }
}

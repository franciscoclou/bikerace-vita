package com.amazonaws.javax.xml.stream.events;

import com.amazonaws.javax.xml.a.b;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class NamedEvent extends DummyEvent {
    private b name;

    public NamedEvent() {
    }

    public NamedEvent(b bVar) {
        this.name = bVar;
    }

    public NamedEvent(String str, String str2, String str3) {
        this.name = new b(str2, str3, str);
    }

    public String getPrefix() {
        return this.name.c();
    }

    public b getName() {
        return this.name;
    }

    public void setName(b bVar) {
        this.name = bVar;
    }

    public String nameAsString() {
        if ("".equals(this.name.a())) {
            return this.name.b();
        }
        if (this.name.c() != null) {
            return new StringBuffer().append("['").append(this.name.a()).append("']:").append(getPrefix()).append(":").append(this.name.b()).toString();
        }
        return new StringBuffer().append("['").append(this.name.a()).append("']:").append(this.name.b()).toString();
    }

    public String getNamespace() {
        return this.name.a();
    }

    @Override // com.amazonaws.javax.xml.stream.events.DummyEvent
    protected void writeAsEncodedUnicodeEx(Writer writer) throws IOException {
        writer.write(nameAsString());
    }
}

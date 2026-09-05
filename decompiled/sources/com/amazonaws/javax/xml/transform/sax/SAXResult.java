package com.amazonaws.javax.xml.transform.sax;

import com.amazonaws.javax.xml.transform.d;
import org.xml.sax.ContentHandler;
import org.xml.sax.ext.LexicalHandler;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class SAXResult implements d {
    public static final String FEATURE = "http://javax.xml.transform.sax.SAXResult/feature";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ContentHandler f150a;
    private LexicalHandler b;
    private String c;

    public SAXResult() {
    }

    public SAXResult(ContentHandler contentHandler) {
        setHandler(contentHandler);
    }

    public void setHandler(ContentHandler contentHandler) {
        this.f150a = contentHandler;
    }

    public ContentHandler getHandler() {
        return this.f150a;
    }

    public void setLexicalHandler(LexicalHandler lexicalHandler) {
        this.b = lexicalHandler;
    }

    public LexicalHandler getLexicalHandler() {
        return this.b;
    }

    public void setSystemId(String str) {
        this.c = str;
    }

    @Override // com.amazonaws.javax.xml.transform.d
    public String getSystemId() {
        return this.c;
    }
}

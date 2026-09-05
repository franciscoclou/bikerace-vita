package com.amazonaws.javax.xml.transform.b;

import com.amazonaws.javax.xml.transform.d;
import java.io.OutputStream;
import java.io.Writer;

/* JADX INFO: compiled from: StreamResult.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class a implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f140a;
    private OutputStream b;
    private Writer c;

    public a() {
    }

    public a(OutputStream outputStream) {
        a(outputStream);
    }

    public a(Writer writer) {
        a(writer);
    }

    public a(String str) {
        this.f140a = str;
    }

    public void a(OutputStream outputStream) {
        this.b = outputStream;
    }

    public OutputStream a() {
        return this.b;
    }

    public void a(Writer writer) {
        this.c = writer;
    }

    public Writer b() {
        return this.c;
    }

    public void a(String str) {
        this.f140a = str;
    }

    @Override // com.amazonaws.javax.xml.transform.d
    public String getSystemId() {
        return this.f140a;
    }
}

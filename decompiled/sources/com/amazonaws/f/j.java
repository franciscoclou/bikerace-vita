package com.amazonaws.f;

import com.amazonaws.javax.xml.stream.writers.XMLStreamWriterImpl;
import java.io.ByteArrayInputStream;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class j extends ByteArrayInputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f129a;

    public j(String str) {
        super(str.getBytes(XMLStreamWriterImpl.UTF_8));
        this.f129a = str;
    }
}

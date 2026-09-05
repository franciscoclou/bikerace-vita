package com.amazonaws.javax.xml.transform.sax;

import com.amazonaws.javax.xml.transform.b.b;
import com.amazonaws.javax.xml.transform.f;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class SAXSource implements f {
    public static final String FEATURE = "http://javax.xml.transform.sax.SAXSource/feature";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private XMLReader f151a;
    private InputSource b;

    public SAXSource() {
    }

    public SAXSource(XMLReader xMLReader, InputSource inputSource) {
        this.f151a = xMLReader;
        this.b = inputSource;
    }

    public SAXSource(InputSource inputSource) {
        this.b = inputSource;
    }

    public void setXMLReader(XMLReader xMLReader) {
        this.f151a = xMLReader;
    }

    public XMLReader getXMLReader() {
        return this.f151a;
    }

    public void setInputSource(InputSource inputSource) {
        this.b = inputSource;
    }

    public InputSource getInputSource() {
        return this.b;
    }

    public void setSystemId(String str) {
        if (this.b == null) {
            this.b = new InputSource(str);
        } else {
            this.b.setSystemId(str);
        }
    }

    public String getSystemId() {
        if (this.b == null) {
            return null;
        }
        return this.b.getSystemId();
    }

    public static InputSource sourceToInputSource(f fVar) {
        if (fVar instanceof SAXSource) {
            return ((SAXSource) fVar).getInputSource();
        }
        if (fVar instanceof b) {
            b bVar = (b) fVar;
            InputSource inputSource = new InputSource(bVar.d());
            inputSource.setByteStream(bVar.a());
            inputSource.setCharacterStream(bVar.b());
            inputSource.setPublicId(bVar.c());
            return inputSource;
        }
        return null;
    }
}

package com.amazonaws.javax.xml.stream;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class XMLStreamException2 extends XMLStreamException {
    public XMLStreamException2(String str) {
        super(str);
    }

    public XMLStreamException2(Throwable th) {
        super(th);
    }

    public XMLStreamException2(String str, Throwable th) {
        super(str, th);
    }

    public XMLStreamException2(String str, Location location) {
        super(str, location);
    }

    public XMLStreamException2(String str, Location location, Throwable th) {
        super(str, location, th);
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return getNestedException();
    }
}

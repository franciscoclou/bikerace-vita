package com.amazonaws.javax.xml.stream;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class XMLStreamException extends Exception {
    protected Location location;
    protected Throwable nested;

    public XMLStreamException() {
    }

    public XMLStreamException(String str) {
        super(str);
    }

    public XMLStreamException(Throwable th) {
        super(th);
        this.nested = th;
    }

    public XMLStreamException(String str, Throwable th) {
        super(str, th);
        this.nested = th;
    }

    public XMLStreamException(String str, Location location, Throwable th) {
        super("ParseError at [row,col]:[" + location.getLineNumber() + "," + location.getColumnNumber() + "]\nMessage: " + str);
        this.nested = th;
        this.location = location;
    }

    public XMLStreamException(String str, Location location) {
        super("ParseError at [row,col]:[" + location.getLineNumber() + "," + location.getColumnNumber() + "]\nMessage: " + str);
        this.location = location;
    }

    public Throwable getNestedException() {
        return this.nested;
    }

    public Location getLocation() {
        return this.location;
    }
}

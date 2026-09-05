package com.amazonaws.javax.xml.stream.xerces.xni;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class XNIException extends RuntimeException {
    private Exception fException;

    public XNIException(String str) {
        super(str);
    }

    public XNIException(Exception exc) {
        super(exc.getMessage());
        this.fException = exc;
    }

    public XNIException(String str, Exception exc) {
        super(str);
        this.fException = exc;
    }

    public Exception getException() {
        return this.fException;
    }
}

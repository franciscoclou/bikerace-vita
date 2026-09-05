package com.amazonaws.javax.xml.transform;

/* JADX INFO: compiled from: TransformerFactoryConfigurationError.java */
/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class m extends Error {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Exception f149a;

    public m() {
        this.f149a = null;
    }

    public m(Exception exc, String str) {
        super(str);
        this.f149a = exc;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        String message = super.getMessage();
        if (message == null && this.f149a != null) {
            return this.f149a.getMessage();
        }
        return message;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f149a;
    }
}

package com.amazonaws.javax.xml.stream;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class FactoryConfigurationError extends Error {
    Exception nested;

    public FactoryConfigurationError() {
    }

    public FactoryConfigurationError(Exception exc) {
        this.nested = exc;
    }

    public FactoryConfigurationError(Exception exc, String str) {
        super(str);
        this.nested = exc;
    }

    public FactoryConfigurationError(String str, Exception exc) {
        super(str);
        this.nested = exc;
    }

    public FactoryConfigurationError(String str) {
        super(str);
    }

    public Exception getException() {
        return this.nested;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.nested;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        String message = super.getMessage();
        if (message == null && this.nested != null) {
            String message2 = this.nested.getMessage();
            if (message2 == null) {
                return this.nested.getClass().toString();
            }
            return message2;
        }
        return message;
    }
}

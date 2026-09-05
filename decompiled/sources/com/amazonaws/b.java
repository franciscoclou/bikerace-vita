package com.amazonaws;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class b extends a {
    private static final long serialVersionUID = 1;
    private String errorCode;
    private c errorType;
    private String requestId;
    private String serviceName;
    private int statusCode;

    public b(String str) {
        super(str);
        this.errorType = c.Unknown;
    }

    public b(String str, Exception exc) {
        super(str, exc);
        this.errorType = c.Unknown;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public c getErrorType() {
        return this.errorType;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setErrorCode(String str) {
        this.errorCode = str;
    }

    public void setErrorType(c cVar) {
        this.errorType = cVar;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public void setServiceName(String str) {
        this.serviceName = str;
    }

    public void setStatusCode(int i) {
        this.statusCode = i;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return "Status Code: " + getStatusCode() + ", AWS Service: " + getServiceName() + ", AWS Request ID: " + getRequestId() + ", AWS Error Code: " + getErrorCode() + ", AWS Error Message: " + getMessage();
    }
}

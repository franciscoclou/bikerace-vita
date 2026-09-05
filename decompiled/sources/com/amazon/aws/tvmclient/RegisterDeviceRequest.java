package com.amazon.aws.tvmclient;

import com.amazonaws.f.f;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class RegisterDeviceRequest extends Request {
    private final String endpoint;
    private final String key;
    private final String uid;
    private final boolean useSSL;

    public RegisterDeviceRequest(String str, boolean z, String str2, String str3) {
        this.endpoint = str;
        this.useSSL = z;
        this.uid = str2;
        this.key = str3;
    }

    @Override // com.amazon.aws.tvmclient.Request
    public String buildRequestUrl() {
        StringBuilder sb = new StringBuilder(this.useSSL ? "https://" : "http://");
        sb.append(this.endpoint);
        sb.append("/");
        sb.append("registerdevice");
        sb.append("?uid=" + f.a(this.uid, false));
        sb.append("&key=" + f.a(this.key, false));
        return sb.toString();
    }
}

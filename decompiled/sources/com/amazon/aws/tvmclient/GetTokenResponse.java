package com.amazon.aws.tvmclient;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class GetTokenResponse extends Response {
    private final String accessKey;
    private final String expirationDate;
    private final String secretKey;
    private final String securityToken;

    public GetTokenResponse(int i, String str) {
        super(i, str);
        this.accessKey = null;
        this.secretKey = null;
        this.securityToken = null;
        this.expirationDate = null;
    }

    public GetTokenResponse(String str, String str2, String str3, String str4) {
        super(200, null);
        this.accessKey = str;
        this.secretKey = str2;
        this.securityToken = str3;
        this.expirationDate = str4;
    }

    public String getAccessKey() {
        return this.accessKey;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public String getSecurityToken() {
        return this.securityToken;
    }

    public String getExpirationDate() {
        return this.expirationDate;
    }
}

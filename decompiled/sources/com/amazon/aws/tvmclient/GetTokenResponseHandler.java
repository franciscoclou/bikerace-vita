package com.amazon.aws.tvmclient;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class GetTokenResponseHandler extends ResponseHandler {
    private final String key;

    public GetTokenResponseHandler(String str) {
        this.key = str;
    }

    @Override // com.amazon.aws.tvmclient.ResponseHandler
    public Response handleResponse(int i, String str) {
        if (i == 200) {
            try {
                String strUnwrap = AESEncryption.unwrap(str, this.key);
                return new GetTokenResponse(Utilities.extractElement(strUnwrap, "accessKey"), Utilities.extractElement(strUnwrap, "secretKey"), Utilities.extractElement(strUnwrap, "securityToken"), Utilities.extractElement(strUnwrap, "expirationDate"));
            } catch (Exception e) {
                return new GetTokenResponse(500, e.getMessage());
            }
        }
        return new GetTokenResponse(i, str);
    }
}

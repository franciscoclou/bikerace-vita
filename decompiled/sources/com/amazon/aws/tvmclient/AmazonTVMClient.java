package com.amazon.aws.tvmclient;

import android.content.SharedPreferences;
import android.util.Log;
import com.topfreegames.bikerace.bm;
import java.security.SecureRandom;
import org.apache.commons.a.a.b;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class AmazonTVMClient {
    private static final String LOG_TAG = "AmazonTVMClient";
    private String endpoint;
    private SharedPreferences sharedPreferences;
    private boolean useSSL;

    public AmazonTVMClient(SharedPreferences sharedPreferences, String str, boolean z) {
        this.endpoint = getEndpointDomainName(str.toLowerCase());
        this.useSSL = z;
        this.sharedPreferences = sharedPreferences;
    }

    public Response anonymousRegister() throws Throwable {
        Response responseProcessRequest = Response.SUCCESSFUL;
        if (AmazonSharedPreferencesWrapper.getUidForDevice(this.sharedPreferences) == null) {
            String strGenerateRandomString = generateRandomString();
            String strGenerateRandomString2 = generateRandomString();
            responseProcessRequest = processRequest(new RegisterDeviceRequest(this.endpoint, this.useSSL, strGenerateRandomString, strGenerateRandomString2), new ResponseHandler());
            if (responseProcessRequest.requestWasSuccessful()) {
                AmazonSharedPreferencesWrapper.registerDeviceId(this.sharedPreferences, strGenerateRandomString, strGenerateRandomString2);
            }
        }
        return responseProcessRequest;
    }

    public Response getToken() {
        String uidForDevice = AmazonSharedPreferencesWrapper.getUidForDevice(this.sharedPreferences);
        String keyForDevice = AmazonSharedPreferencesWrapper.getKeyForDevice(this.sharedPreferences);
        GetTokenResponse getTokenResponse = (GetTokenResponse) processRequest(new GetTokenRequest(this.endpoint, this.useSSL, uidForDevice, keyForDevice), new GetTokenResponseHandler(keyForDevice));
        if (getTokenResponse.requestWasSuccessful()) {
            AmazonSharedPreferencesWrapper.storeCredentialsInSharedPreferences(this.sharedPreferences, getTokenResponse.getAccessKey(), getTokenResponse.getSecretKey(), getTokenResponse.getSecurityToken(), getTokenResponse.getExpirationDate());
        }
        return getTokenResponse;
    }

    protected Response processRequest(Request request, ResponseHandler responseHandler) throws Throwable {
        int i = 2;
        while (true) {
            Response responseSendRequest = TokenVendingMachineService.sendRequest(request, responseHandler);
            if (responseSendRequest.requestWasSuccessful()) {
                return responseSendRequest;
            }
            bm.c();
            Log.w(LOG_TAG, "Request to Token Vending Machine failed with Code: [" + responseSendRequest.getResponseCode() + "] Message: [" + responseSendRequest.getResponseMessage() + "]");
            int i2 = i - 1;
            if (i <= 0) {
                return responseSendRequest;
            }
            i = i2;
        }
    }

    public String generateRandomString() {
        return new String(b.a(new SecureRandom().generateSeed(16)));
    }

    private String getEndpointDomainName(String str) {
        int iIndexOf;
        int length;
        if (str.startsWith("http://") || str.startsWith("https://")) {
            iIndexOf = str.indexOf("://") + 3;
        } else {
            iIndexOf = 0;
        }
        if (str.charAt(str.length() - 1) == '/') {
            length = str.length() - 1;
        } else {
            length = str.length();
        }
        return str.substring(iIndexOf, length);
    }
}

package com.amazon.aws.tvmclient;

import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class TokenVendingMachineService {
    private static final int CONNECTION_TIMEOUT_MILISECONDS = 5000;
    private static final String ERROR = "Internal Server Error";
    private static final String LOG_TAG = "TokenVendingMachineService";

    public static Response sendRequest(Request request, ResponseHandler responseHandler) throws Throwable {
        String strBuildRequestUrl;
        String response = null;
        int responseCode = 0;
        try {
            try {
                strBuildRequestUrl = request.buildRequestUrl();
                try {
                    Log.i(LOG_TAG, "Sending Request : [" + strBuildRequestUrl + "]");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strBuildRequestUrl).openConnection();
                    httpURLConnection.setConnectTimeout(CONNECTION_TIMEOUT_MILISECONDS);
                    httpURLConnection.setReadTimeout(CONNECTION_TIMEOUT_MILISECONDS);
                    responseCode = httpURLConnection.getResponseCode();
                    response = getResponse(httpURLConnection);
                    Log.i(LOG_TAG, "Response : [" + response + "]");
                    return responseHandler.handleResponse(responseCode, response);
                } catch (IOException e) {
                    e = e;
                    Log.w(LOG_TAG, e);
                    String message = e.getMessage();
                    if (message != null && message.equals("Received authentication challenge is null")) {
                        return responseHandler.handleResponse(401, "Unauthorized token request");
                    }
                    return responseHandler.handleResponse(404, "Unable to reach resource at [" + strBuildRequestUrl + "]");
                }
            } catch (Exception e2) {
                Log.w(LOG_TAG, e2);
                return responseHandler.handleResponse(responseCode, response);
            }
        } catch (IOException e3) {
            e = e3;
            strBuildRequestUrl = null;
        }
    }

    protected static String getResponse(HttpURLConnection httpURLConnection) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        InputStream errorStream;
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(1024);
        try {
            byteArrayOutputStream = new ByteArrayOutputStream(1024);
            try {
                try {
                    byte[] bArr = new byte[1024];
                    if (httpURLConnection.getResponseCode() == 200) {
                        errorStream = httpURLConnection.getInputStream();
                    } else {
                        errorStream = httpURLConnection.getErrorStream();
                    }
                    while (true) {
                        int i = errorStream.read(bArr);
                        if (i != -1) {
                            byteArrayOutputStream.write(bArr, 0, i);
                        } else {
                            String string = byteArrayOutputStream.toString();
                            try {
                                byteArrayOutputStream.close();
                                return string;
                            } catch (Exception e) {
                                Log.w(LOG_TAG, e);
                                return string;
                            }
                        }
                        th = th;
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e2) {
                            Log.w(LOG_TAG, e2);
                        }
                        throw th;
                    }
                } catch (Exception e3) {
                    e = e3;
                    Log.w(LOG_TAG, e);
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e4) {
                        Log.w(LOG_TAG, e4);
                    }
                    return ERROR;
                }
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e5) {
            e = e5;
            byteArrayOutputStream = byteArrayOutputStream2;
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = byteArrayOutputStream2;
        }
    }
}

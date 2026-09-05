package com.amazon.aws.tvmclient;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class Response {
    public static final Response SUCCESSFUL = new Response(200, "OK");
    private final int responseCode;
    private final String responseMessage;

    public Response(int i, String str) {
        this.responseCode = i;
        this.responseMessage = str;
    }

    public boolean requestWasSuccessful() {
        return getResponseCode() == 200;
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }
}
